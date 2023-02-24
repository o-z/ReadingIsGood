package com.getir.readingisgood.scheduler;

import com.getir.readingisgood.model.entity.BookEntity;
import com.getir.readingisgood.model.enums.BookStatus;
import com.getir.readingisgood.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.javacrumbs.shedlock.core.SchedulerLock;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Service
public class BookScheduler {

  private final BookRepository bookRepository;

  @Transactional
  @Scheduled(cron = "0 0/1 * * * ?")
  public void computeExpiredBooks() {
    try {
      log.info("Book Scheduler started by time:{}", LocalDate.now());

      List<BookEntity> expiredBooks =
          bookRepository.searchBookEntitiesByStatusAndEndDateIsBefore(
              BookStatus.ACTIVE, LocalDateTime.now());
      expiredBooks.forEach(b -> b.setStatus(BookStatus.PASSIVE));
      bookRepository.saveAll(expiredBooks);

      log.info("Book Scheduler finished by time:{}", LocalDate.now());
    } catch (Exception e) {
      log.info("Book Scheduler error is occurred by exception:{}", e);
    }
  }
}
