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
@Table(name = "CATEGORY", schema = "GETIR")
@EntityListeners(AuditingEntityListener.class)
public class CategoryEntity extends BaseEntity implements Serializable {

  @Column(name = "NAME", length = 50)
  private String name;

  @Column(name = "DETAIL", length = 200)
  private String detail;
}
