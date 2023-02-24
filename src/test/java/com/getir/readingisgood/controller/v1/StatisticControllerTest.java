package com.getir.readingisgood.controller.v1;

import com.getir.readingisgood.model.dto.StatisticDto;
import org.junit.jupiter.api.Test;
import org.springframework.http.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class StatisticControllerTest extends BaseMySQLContainerTest {

  @Test
  public void testGet() {
    HttpHeaders headers = getHttpHeadersWithToken();
    HttpEntity<String> requestHttpEntity = new HttpEntity<>(headers);
    ResponseEntity<StatisticDto[]> response =
        testRestTemplate.exchange(
            "/v1/statistics", HttpMethod.GET, requestHttpEntity, StatisticDto[].class);
    StatisticDto[] statisticDtos = response.getBody();

    assertNotNull(response);
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals("FEBRUARY", statisticDtos[0].getMonth());
    assertEquals(1, statisticDtos[0].getOrderCount());
    assertEquals(2, statisticDtos[0].getSoldBookCount());
  }
}
