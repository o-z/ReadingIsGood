package com.getir.readingisgood.model.request;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class OrderCreateRequest implements Serializable {
  @NotNull private Long customerId;
  @NotNull private Long bookId;
  @NotNull private Long count;
  @NotNull private Long addressId;
  @NotNull private Long masterPassId;
}
