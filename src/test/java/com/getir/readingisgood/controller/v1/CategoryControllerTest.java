package com.getir.readingisgood.controller.v1;

import com.getir.readingisgood.model.dto.CategoryDto;
import com.getir.readingisgood.model.request.CategoryCreateRequest;
import org.junit.jupiter.api.Test;
import org.springframework.http.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class CategoryControllerTest extends BaseMySQLContainerTest {

  @Test
  public void testCreate() {
    CategoryCreateRequest categoryCreateRequest = new CategoryCreateRequest();
    categoryCreateRequest.setName("TARIH");
    categoryCreateRequest.setDetail("TARIH");
    HttpHeaders headers = getHttpHeadersWithToken();
    HttpEntity<CategoryCreateRequest> request = new HttpEntity<>(categoryCreateRequest, headers);
    ResponseEntity<CategoryDto> response =
        testRestTemplate.postForEntity("/v1/categories", request, CategoryDto.class);
    CategoryDto categoryDto = response.getBody();

    assertNotNull(response);
    assertEquals(HttpStatus.CREATED, response.getStatusCode());
    assertEquals(2, categoryDto.getId());
    assertEquals("TARIH", categoryDto.getName());
    assertEquals("TARIH", categoryDto.getDetail());
  }

  @Test
  public void testGetById() {
    HttpHeaders headers = getHttpHeadersWithToken();
    HttpEntity<String> requestHttpEntity = new HttpEntity<>(headers);
    ResponseEntity<CategoryDto> response =
        testRestTemplate.exchange(
            "/v1/categories/{id}", HttpMethod.GET, requestHttpEntity, CategoryDto.class, "1");
    CategoryDto categoryDto = response.getBody();

    assertNotNull(response);
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(1, categoryDto.getId());
    assertEquals("TARİH", categoryDto.getName());
    assertEquals("TARİH", categoryDto.getDetail());
  }
}
