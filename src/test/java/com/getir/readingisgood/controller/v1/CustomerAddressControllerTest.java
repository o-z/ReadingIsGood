package com.getir.readingisgood.controller.v1;

import com.getir.readingisgood.model.dto.CustomerAddressDto;
import com.getir.readingisgood.model.request.CustomerAddressRequest;
import org.junit.jupiter.api.Test;
import org.springframework.http.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class CustomerAddressControllerTest extends BaseMySQLContainerTest {

  @Test
  public void testCreate() {
    CustomerAddressRequest customerAddressRequest = new CustomerAddressRequest();
    customerAddressRequest.setName("EV");
    customerAddressRequest.setDetail("EV");
    customerAddressRequest.setCustomerId(1L);
    HttpHeaders headers = getHttpHeadersWithToken();
    HttpEntity<CustomerAddressRequest> request = new HttpEntity<>(customerAddressRequest, headers);
    ResponseEntity<CustomerAddressDto> response =
        testRestTemplate.postForEntity("/v1/customer-addresses", request, CustomerAddressDto.class);
    CustomerAddressDto customerAddressDto = response.getBody();

    assertNotNull(response);
    assertEquals(HttpStatus.CREATED, response.getStatusCode());
    assertEquals(2, customerAddressDto.getId());
    assertEquals("EV", customerAddressDto.getName());
    assertEquals("EV", customerAddressDto.getDetail());
    assertEquals(1, customerAddressDto.getCustomerId());
  }

  @Test
  public void testGetById() {
    HttpHeaders headers = getHttpHeadersWithToken();
    HttpEntity<String> requestHttpEntity = new HttpEntity<>(headers);
    ResponseEntity<CustomerAddressDto> response =
        testRestTemplate.exchange(
            "/v1/customer-addresses/{id}",
            HttpMethod.GET,
            requestHttpEntity,
            CustomerAddressDto.class,
            "1");
    CustomerAddressDto customerAddressDto = response.getBody();

    assertNotNull(response);
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(1, customerAddressDto.getId());
    assertEquals("EV", customerAddressDto.getName());
    assertEquals("EV", customerAddressDto.getDetail());
    assertEquals(1, customerAddressDto.getCustomerId());
  }
}
