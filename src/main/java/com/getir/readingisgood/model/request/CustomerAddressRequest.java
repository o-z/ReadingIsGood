package com.getir.readingisgood.model.request;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class CustomerAddressRequest implements Serializable {
  @NotNull private Long customerId;
  @NotEmpty private String name;
  @NotEmpty private String detail;
}
