package com.getir.readingisgood.model.converter;

import com.getir.readingisgood.model.dto.StatisticDto;
import com.getir.readingisgood.model.entity.StatisticEntity;
import org.springframework.stereotype.Component;

import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class StatisticConverter {

  public List<StatisticDto> convertFrom(List<StatisticEntity> entities) {
    Map<YearMonth, List<StatisticEntity>> monthListMap =
        entities.stream().collect(Collectors.groupingBy(p -> YearMonth.from(p.getCreateDate())));
    List<StatisticDto> statisticDtos = new ArrayList<>();
    monthListMap.forEach(
        (k, v) -> {
          statisticDtos.add(
              StatisticDto.builder()
                  .orderCount(v.size())
                  .soldBookCount(v.stream().mapToLong(StatisticEntity::getCount).sum())
                  .month(k.getMonth().name())
                  .build());
          v.forEach(statistic -> {});
        });
    return statisticDtos;
  }
}
