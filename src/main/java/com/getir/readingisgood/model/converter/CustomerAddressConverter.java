package com.getir.readingisgood.model.converter;

import com.getir.readingisgood.model.dto.CustomerAddressDto;
import com.getir.readingisgood.model.entity.CustomerAddressEntity;
import org.springframework.stereotype.Component;

@Component
public class CustomerAddressConverter {

  public CustomerAddressDto convertFrom(final CustomerAddressEntity entity) {
    return CustomerAddressDto.builder()
        .id(entity.getId())
        .customerId(entity.getCustomerId())
        .name(entity.getName())
        .detail(entity.getDetail())
        .createDate(entity.getCreateDate())
        .updateDate(entity.getUpdateDate())
        .build();
  }
}
