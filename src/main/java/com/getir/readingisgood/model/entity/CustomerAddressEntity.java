package com.getir.readingisgood.model.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "CUSTOMER_ADDRESS", schema = "GETIR")
@EntityListeners(AuditingEntityListener.class)
public class CustomerAddressEntity extends BaseEntity implements Serializable {

  @Column(name = "CUSTOMER_ID")
  private Long customerId;

  @Column(name = "NAME", length = 50)
  private String name;

  @Column(name = "DETAIL", length = 200)
  private String detail;
}
