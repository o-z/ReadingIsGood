package com.getir.readingisgood.controller.v1;

import com.getir.readingisgood.model.dto.CustomerAddressDto;
import com.getir.readingisgood.model.request.CustomerAddressRequest;
import com.getir.readingisgood.service.CustomerAddressService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/customer-addresses")
@RequiredArgsConstructor
@Slf4j
@SecurityRequirement(name = "bearerAuth")
public class CustomerAddressController {
  private final CustomerAddressService customerAddressService;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public CustomerAddressDto createCustomerAddress(
      @Valid @RequestBody CustomerAddressRequest request) {
    return customerAddressService.createCustomerAddress(request);
  }

  @GetMapping("/{id}")
  public ResponseEntity<CustomerAddressDto> getCustomerAddress(@PathVariable("id") Long id) {
    return ResponseEntity.ok(customerAddressService.getCustomerAddress(id));
  }
}
