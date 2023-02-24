package com.getir.readingisgood.model.converter;

import com.getir.readingisgood.model.dto.OrderDto;
import com.getir.readingisgood.model.entity.OrderEntity;
import org.springframework.stereotype.Component;

@Component
public class OrderConverter {

  public OrderDto convertFrom(OrderEntity entity) {
    return OrderDto.builder()
        .id(entity.getId())
        .customerId(entity.getCustomerId())
        .bookId(entity.getBookId())
        .count(entity.getCount())
        .totalAmount(entity.getTotalAmount())
        .status(entity.getStatus())
        .addressId(entity.getAddressId())
        .createDate(entity.getCreateDate())
        .updateDate(entity.getUpdateDate())
        .build();
  }
}
