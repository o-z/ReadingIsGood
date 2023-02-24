package com.getir.readingisgood.model.entity;

import com.getir.readingisgood.model.enums.OrderStatus;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "CUSTOMER_ORDER", schema = "GETIR")
@EntityListeners(AuditingEntityListener.class)
public class OrderEntity extends BaseEntity implements Serializable {

  @Column(name = "CUSTOMER_ID")
  private Long customerId;

  @Column(name = "BOOK_ID")
  private Long bookId;

  @Column(name = "COUNT")
  private Long count;

  @Column(name = "TOTAL_AMOUNT")
  private BigDecimal totalAmount;

  @Enumerated(EnumType.STRING)
  @Column(name = "STATUS")
  private OrderStatus status;

  @Column(name = "ADDRESS_ID")
  private Long addressId;
}
