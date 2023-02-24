package com.getir.readingisgood.service;

import com.getir.readingisgood.exception.ErrorCodeEnum;
import com.getir.readingisgood.exception.ReadingIsGoodException;
import com.getir.readingisgood.kafka.producer.StatisticsProducerService;
import com.getir.readingisgood.model.converter.OrderConverter;
import com.getir.readingisgood.model.dto.BookDto;
import com.getir.readingisgood.model.dto.OrderDto;
import com.getir.readingisgood.model.dto.PaymentDto;
import com.getir.readingisgood.model.dto.StatisticMessageDto;
import com.getir.readingisgood.model.entity.OrderEntity;
import com.getir.readingisgood.model.enums.BookStatus;
import com.getir.readingisgood.model.enums.OrderStatus;
import com.getir.readingisgood.model.enums.StockUpdateType;
import com.getir.readingisgood.model.request.OrderCreateRequest;
import com.getir.readingisgood.repository.OrderRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.LockModeType;
import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {

  private final OrderRepository orderRepository;
  private final OrderConverter orderConverter;
  private final BookService bookService;
  private final PaymentService paymentService;
  private final StatisticsProducerService statisticsProducerService;

  @CircuitBreaker(name = "distributeCB", fallbackMethod = "getOrderBack")
  @Lock(LockModeType.PESSIMISTIC_WRITE)
  @Transactional
  public OrderDto createOrder(final OrderCreateRequest orderCreateRequest) {
    BookDto book = bookService
            .getBook(orderCreateRequest.getBookId());
    validateBook(book,orderCreateRequest);
    BigDecimal totalAmount = getTotalAmount(orderCreateRequest,book);

    OrderEntity savedOrderEntity = saveOrderEntity(orderCreateRequest, totalAmount);

    paymentService.pay(
        PaymentDto.builder()
            .orderId(savedOrderEntity.getId())
            .amount(totalAmount)
            .masterPassId(orderCreateRequest.getMasterPassId())
            .build());

    bookService.stockUpdate(
        orderCreateRequest.getBookId(), orderCreateRequest.getCount(), StockUpdateType.DECREASE);

    OrderEntity updatedOrderEntity = setOrderStatusToSuccess(savedOrderEntity);

    sendStatistic(savedOrderEntity);

    return orderConverter.convertFrom(updatedOrderEntity);
  }



  @Transactional(readOnly = true)
  public OrderDto getOrder(final Long id) {
    OrderEntity orderEntity =
        orderRepository
            .findById(id)
            .orElseThrow(() -> new ReadingIsGoodException(ErrorCodeEnum.ORDER_NOT_FOUND_ERROR));
    return orderConverter.convertFrom(orderEntity);
  }
  private void validateBook(BookDto book,OrderCreateRequest orderCreateRequest) {
    if (book.getStatus().equals(BookStatus.PASSIVE) && book.getCount()<orderCreateRequest.getCount()){
      throw new ReadingIsGoodException(ErrorCodeEnum.ORDER_NOT_VALID_ERROR);
    }
  }

  private BigDecimal getTotalAmount(OrderCreateRequest orderCreateRequest, BookDto bookDto) {
    return bookDto
        .getPrice()
        .multiply(BigDecimal.valueOf(orderCreateRequest.getCount()));
  }

  private OrderEntity saveOrderEntity(
      OrderCreateRequest orderCreateRequest, BigDecimal totalAmount) {
    OrderEntity orderEntity =
        OrderEntity.builder()
            .customerId(orderCreateRequest.getCustomerId())
            .bookId(orderCreateRequest.getBookId())
            .count(orderCreateRequest.getCount())
            .totalAmount(totalAmount)
            .status(OrderStatus.WAITING)
            .addressId(orderCreateRequest.getAddressId())
            .build();
    return orderRepository.saveAndFlush(orderEntity);
  }

  private OrderEntity setOrderStatusToSuccess(OrderEntity savedOrderEntity) {
    savedOrderEntity.setStatus(OrderStatus.SUCCESS);
    return orderRepository.saveAndFlush(savedOrderEntity);
  }

  private void sendStatistic(OrderEntity savedOrderEntity) {
    StatisticMessageDto statisticMessageDto =
        StatisticMessageDto.builder()
            .orderId(savedOrderEntity.getId())
            .bookId(savedOrderEntity.getBookId())
            .count(savedOrderEntity.getCount())
            .build();
    statisticsProducerService.sendMessage(statisticMessageDto);
  }


  private OrderDto getOrderBack(Exception e) throws Exception {
    log.error("Order error : ", e);
    throw new ReadingIsGoodException(ErrorCodeEnum.ORDER_ERROR);
  }
}
