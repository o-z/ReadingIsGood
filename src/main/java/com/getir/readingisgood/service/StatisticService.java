package com.getir.readingisgood.service;

import com.getir.readingisgood.model.converter.StatisticConverter;
import com.getir.readingisgood.model.dto.StatisticDto;
import com.getir.readingisgood.model.dto.StatisticMessageDto;
import com.getir.readingisgood.model.entity.StatisticEntity;
import com.getir.readingisgood.repository.StatisticRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class StatisticService {
  private final StatisticRepository statisticRepository;
  private final StatisticConverter statisticConverter;

  @Transactional
  public Long saveStatistic(final StatisticMessageDto statisticMessageDto) {
    StatisticEntity statisticEntity =
        StatisticEntity.builder()
            .orderId(statisticMessageDto.getOrderId())
            .bookId(statisticMessageDto.getBookId())
            .count(statisticMessageDto.getCount())
            .build();
    return statisticRepository.save(statisticEntity).getBookId();
  }

  @Transactional(readOnly = true)
  public List<StatisticDto> getStatistics() {
    List<StatisticEntity> statisticEntities = statisticRepository.findAll();
    return statisticConverter.convertFrom(statisticEntities);
  }
}
