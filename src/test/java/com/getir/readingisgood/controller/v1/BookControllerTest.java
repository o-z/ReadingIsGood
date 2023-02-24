package com.getir.readingisgood.controller.v1;

import com.getir.readingisgood.model.dto.BookDto;
import com.getir.readingisgood.model.enums.BookStatus;
import com.getir.readingisgood.model.request.BookCreateRequest;
import org.junit.jupiter.api.Test;
import org.springframework.http.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class BookControllerTest extends BaseMySQLContainerTest {

  @Test
  public void testCreate() {
    BookCreateRequest bookCreateRequest = new BookCreateRequest();
    bookCreateRequest.setName("Nutuk 1");
    bookCreateRequest.setDetail("Nutuk 1");
    bookCreateRequest.setCategoryId(1L);
    bookCreateRequest.setPrice(new BigDecimal(110));
    bookCreateRequest.setStatus(BookStatus.ACTIVE);
    bookCreateRequest.setCount(10L);
    bookCreateRequest.setEndDate(LocalDateTime.now());
    HttpHeaders headers = getHttpHeadersWithToken();
    HttpEntity<BookCreateRequest> request = new HttpEntity<>(bookCreateRequest, headers);
    ResponseEntity<BookDto> response =
        testRestTemplate.postForEntity("/v1/books", request, BookDto.class);
    BookDto bookResponse = response.getBody();

    assertNotNull(bookResponse);
    assertEquals(HttpStatus.CREATED, response.getStatusCode());
    assertEquals(2, bookResponse.getId());
    assertEquals("Nutuk 1", bookResponse.getName());
    assertEquals("Nutuk 1", bookResponse.getDetail());
    assertEquals(1L, bookResponse.getCategoryId());
    assertEquals(new BigDecimal(110), bookResponse.getPrice());
    assertEquals(10L, bookResponse.getCount());
    assertEquals(BookStatus.ACTIVE, bookResponse.getStatus());
    assertEquals(bookCreateRequest.getEndDate(), bookResponse.getEndDate());
  }

  @Test
  public void testGetById() {
    HttpHeaders headers = getHttpHeadersWithToken();
    HttpEntity<BookCreateRequest> requestHttpEntity = new HttpEntity<>( headers);
    ResponseEntity<BookDto> response =
            testRestTemplate.exchange(
                    "/v1/books/{id}", HttpMethod.GET, requestHttpEntity, BookDto.class, "1");
    BookDto bookResponse = response.getBody();

    assertNotNull(bookResponse);
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(1, bookResponse.getId());
    assertEquals("Nutuk", bookResponse.getName());
    assertEquals("Nutuk", bookResponse.getDetail());
    assertEquals(1L, bookResponse.getCategoryId());
    assertEquals(new BigDecimal("100.00"), bookResponse.getPrice());
    assertEquals(8L, bookResponse.getCount());
    assertEquals(BookStatus.ACTIVE, bookResponse.getStatus());
  }
}
