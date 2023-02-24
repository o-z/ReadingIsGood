package com.getir.readingisgood.model.dto;

import com.getir.readingisgood.model.enums.BookStatus;
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
public class BookDto implements Serializable {

  private Long id;
  private String name;
  private String detail;
  private BigDecimal price;
  private Long count;
  private BookStatus status;
  private Long categoryId;
  private LocalDateTime endDate;
  private LocalDateTime createDate;
  private LocalDateTime updateDate;
}
