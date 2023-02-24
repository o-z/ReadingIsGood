package com.getir.readingisgood.controller.v1;

import com.getir.readingisgood.model.dto.OrderDto;
import com.getir.readingisgood.model.request.OrderCreateRequest;
import com.getir.readingisgood.service.OrderService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/orders")
@RequiredArgsConstructor
@Slf4j
@SecurityRequirement(name = "bearerAuth")
public class OrderController {

  private final OrderService orderService;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public OrderDto createOrder(@Valid @RequestBody OrderCreateRequest request) {
    return orderService.createOrder(request);
  }

  @GetMapping("/{id}")
  public ResponseEntity<OrderDto> getOrder(@PathVariable("id") Long id) {
    return ResponseEntity.ok(orderService.getOrder(id));
  }
}
