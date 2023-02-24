package com.getir.readingisgood.service;

import com.getir.readingisgood.model.dto.PaymentDto;
import com.getir.readingisgood.model.dto.PaymentResultDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentService {
  public PaymentResultDto pay(PaymentDto paymentDto) {
    log.info("Payment success orderId : {}", paymentDto.getOrderId());
    return PaymentResultDto.builder().status(true).desc("MOCK").build();
  }
}
