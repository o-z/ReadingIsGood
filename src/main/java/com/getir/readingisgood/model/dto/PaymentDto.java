package com.getir.readingisgood.model.dto;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Builder
public class PaymentDto implements Serializable {
  private Long orderId;
  private Long masterPassId;
  private BigDecimal amount;
}
