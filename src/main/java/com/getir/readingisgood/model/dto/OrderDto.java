package com.getir.readingisgood.model.dto;

import com.getir.readingisgood.model.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto implements Serializable {

  private Long id;
  private Long customerId;
  private Long bookId;
  private Long count;
  private BigDecimal totalAmount;
  private OrderStatus status;
  private Long addressId;
  private LocalDateTime createDate;
  private LocalDateTime updateDate;
}
