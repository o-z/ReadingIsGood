package com.getir.readingisgood.kafka.consumer;

import com.getir.readingisgood.model.dto.StatisticMessageDto;
import com.getir.readingisgood.service.StatisticService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class StatisticsConsumerService {

  private final StatisticService statisticService;

  @KafkaListener(
      topics = "${spring.kafka.topic.statistic}",
      groupId = "${spring.kafka.consumer.group-id}",
      containerFactory = "kafkaPaymentCreateContainerFactory")
  public void listen(@Payload final StatisticMessageDto statisticMessageDto) {
    log.info("Consume statistic orderId : {}", statisticMessageDto.getOrderId());
    statisticService.saveStatistic(statisticMessageDto);
  }
}
