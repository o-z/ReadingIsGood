package com.getir.readingisgood;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableJpaAuditing
@EnableKafka
@EnableCaching
@EnableScheduling
public class ReadingIsGoodApplication {

  public static void main(String[] args) {
    SpringApplication.run(ReadingIsGoodApplication.class, args);
  }
}
