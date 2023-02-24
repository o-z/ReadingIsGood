package com.getir.readingisgood.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StatisticDto implements Serializable {
  private Long soldBookCount;
  private Integer orderCount;
  private String month;
}
