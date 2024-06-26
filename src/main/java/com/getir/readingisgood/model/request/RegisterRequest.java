package com.getir.readingisgood.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

  @NotEmpty private String firstname;
  @NotEmpty private String lastname;
  @NotEmpty private String email;
  @NotEmpty private String password;
}
