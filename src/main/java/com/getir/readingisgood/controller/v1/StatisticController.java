package com.getir.readingisgood.controller.v1;

import com.getir.readingisgood.model.dto.StatisticDto;
import com.getir.readingisgood.service.StatisticService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/statistics")
@RequiredArgsConstructor
@Slf4j
@SecurityRequirement(name = "bearerAuth")
public class StatisticController {
  private final StatisticService statisticService;

  @GetMapping
  public ResponseEntity<List<StatisticDto>> getStatistics() {
    return ResponseEntity.ok(statisticService.getStatistics());
  }
}
