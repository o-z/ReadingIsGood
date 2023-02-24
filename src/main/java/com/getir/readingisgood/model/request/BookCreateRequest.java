package com.getir.readingisgood.model.request;

import com.getir.readingisgood.model.enums.BookStatus;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Data
public class BookCreateRequest implements Serializable {
  @NotEmpty private String name;
  @NotEmpty private String detail;
  private BookStatus status;
  @NotNull private Long categoryId;
  private LocalDateTime endDate;
  @NotNull private BigDecimal price;
  @NotNull private Long count;
}
