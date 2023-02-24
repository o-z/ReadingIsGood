package com.getir.readingisgood.model.entity;

import com.getir.readingisgood.model.enums.BookStatus;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "BOOK", schema = "GETIR")
@EntityListeners(AuditingEntityListener.class)
public class BookEntity extends BaseEntity implements Serializable {

  @Column(name = "NAME", length = 50)
  private String name;

  @Column(name = "DETAIL", length = 200)
  private String detail;

  @Enumerated(EnumType.STRING)
  @Column(name = "STATUS")
  private BookStatus status;

  @Column(name = "PRICE")
  private BigDecimal price;

  @Column(name = "COUNT")
  private Long count;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "CATEGORY_ID")
  private CategoryEntity categoryEntity;

  @Column(name = "END_DATE")
  private LocalDateTime endDate;
}
