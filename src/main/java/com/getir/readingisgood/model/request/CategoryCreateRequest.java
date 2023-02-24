package com.getir.readingisgood.model.request;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Data
public class CategoryCreateRequest implements Serializable {
  @NotEmpty private String name;
  @NotEmpty private String detail;
}
