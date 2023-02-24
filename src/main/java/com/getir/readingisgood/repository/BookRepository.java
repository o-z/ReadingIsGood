package com.getir.readingisgood.repository;

import com.getir.readingisgood.model.entity.BookEntity;
import com.getir.readingisgood.model.enums.BookStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Repository
public interface BookRepository extends JpaRepository<BookEntity, Long> {
    List<BookEntity> searchBookEntitiesByStatusAndEndDateIsBefore(BookStatus status, LocalDateTime date);


}
