package com.getir.readingisgood.controller.v1;

import com.getir.readingisgood.model.dto.CategoryDto;
import com.getir.readingisgood.model.dto.OrderDto;
import com.getir.readingisgood.model.enums.OrderStatus;
import com.getir.readingisgood.model.request.OrderCreateRequest;
import org.junit.jupiter.api.Test;
import org.springframework.http.*;
import org.springframework.kafka.test.context.EmbeddedKafka;
import scala.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@EmbeddedKafka(
        partitions = 1,
        brokerProperties = {"listeners=PLAINTEXT://localhost:29092", "port=29092"},
        topics = "statistic")
class OrderControllerTest extends BaseMySQLContainerTest {

  @Test
  public void testCreate() {
    OrderCreateRequest orderCreateRequest = new OrderCreateRequest();
    orderCreateRequest.setCustomerId(1L);
    orderCreateRequest.setBookId(1L);
    orderCreateRequest.setCount(1L);
    orderCreateRequest.setAddressId(1L);
    orderCreateRequest.setMasterPassId(1L);

    HttpHeaders headers = getHttpHeadersWithToken();
    HttpEntity<OrderCreateRequest> request = new HttpEntity<>(orderCreateRequest, headers);
    ResponseEntity<OrderDto> response =
        testRestTemplate.postForEntity("/v1/orders", request, OrderDto.class);
    OrderDto orderDto = response.getBody();

    assertNotNull(response);
    assertEquals(HttpStatus.CREATED, response.getStatusCode());
    assertEquals(2L, orderDto.getId());
    assertEquals(OrderStatus.SUCCESS, orderDto.getStatus());
    assertEquals(new java.math.BigDecimal("100.00"), orderDto.getTotalAmount());
  }

  @Test
  public void testGetById() {
    HttpHeaders headers = getHttpHeadersWithToken();
    HttpEntity<String> requestHttpEntity = new HttpEntity<>(headers);
    ResponseEntity<OrderDto> response =
        testRestTemplate.exchange(
            "/v1/orders/{id}", HttpMethod.GET, requestHttpEntity, OrderDto.class, "1");
      OrderDto orderDto = response.getBody();

    assertNotNull(response);
      assertEquals(HttpStatus.OK, response.getStatusCode());
      assertEquals(1L, orderDto.getId());
      assertEquals(OrderStatus.SUCCESS, orderDto.getStatus());
      assertEquals(new java.math.BigDecimal("200.00"), orderDto.getTotalAmount());

  }
}
