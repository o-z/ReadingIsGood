package com.getir.readingisgood.service;

import com.getir.readingisgood.exception.ErrorCodeEnum;
import com.getir.readingisgood.exception.ReadingIsGoodException;
import com.getir.readingisgood.model.converter.CustomerAddressConverter;
import com.getir.readingisgood.model.dto.CustomerAddressDto;
import com.getir.readingisgood.model.entity.CustomerAddressEntity;
import com.getir.readingisgood.model.request.CustomerAddressRequest;
import com.getir.readingisgood.repository.CustomerAddressRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerAddressService {

  private final CustomerAddressRepository customerAddressRepository;
  private final CustomerAddressConverter customerAddressConverter;

  @Transactional
  public CustomerAddressDto createCustomerAddress(
      final CustomerAddressRequest customerAddressRequest) {
    CustomerAddressEntity categoryEntity =
        CustomerAddressEntity.builder()
                .customerId(customerAddressRequest.getCustomerId())
            .name(customerAddressRequest.getName())
            .detail(customerAddressRequest.getDetail())
            .build();
    return customerAddressConverter.convertFrom(customerAddressRepository.save(categoryEntity));
  }

  @Transactional(readOnly = true)
  public CustomerAddressDto getCustomerAddress(final Long id) {
    CustomerAddressEntity categoryEntity =
        customerAddressRepository
            .findById(id)
            .orElseThrow(
                () -> new ReadingIsGoodException(ErrorCodeEnum.CUSTOMER_ADDRESS_NOT_FOUND_ERROR));
    return customerAddressConverter.convertFrom(categoryEntity);
  }
}
