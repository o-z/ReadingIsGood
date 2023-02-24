package com.getir.readingisgood.kafka.producer;

import com.getir.readingisgood.model.dto.StatisticMessageDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class StatisticsProducerService {

  @Autowired private KafkaTemplate<String, Object> kafkaTemplate;

  @Value("${spring.kafka.topic.statistic}")
  private String statisticTopic;

  public void sendMessage(final StatisticMessageDto statisticMessageDto) {
    log.info("Send statistic orderId : {}", statisticMessageDto.getOrderId());
    kafkaTemplate.send(statisticTopic, statisticMessageDto);
  }
}
