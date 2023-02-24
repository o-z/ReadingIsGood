package com.getir.readingisgood.service;

import com.getir.readingisgood.exception.ErrorCodeEnum;
import com.getir.readingisgood.exception.ReadingIsGoodException;
import com.getir.readingisgood.model.converter.BookConverter;
import com.getir.readingisgood.model.dto.BookDto;
import com.getir.readingisgood.model.entity.BookEntity;
import com.getir.readingisgood.model.enums.StockUpdateType;
import com.getir.readingisgood.model.request.BookCreateRequest;
import com.getir.readingisgood.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookService {
  private final BookRepository bookRepository;
  private final CategoryService categoryService;
  private final BookConverter bookConverter;

  @Transactional
  public BookDto createBook(final BookCreateRequest bookCreateRequest) {
    BookEntity bookEntity =
        BookEntity.builder()
            .name(bookCreateRequest.getName())
            .detail(bookCreateRequest.getDetail())
            .price(bookCreateRequest.getPrice())
            .count(bookCreateRequest.getCount())
            .status(bookCreateRequest.getStatus())
            .categoryEntity(categoryService.getCategoryEntity(bookCreateRequest.getCategoryId()))
            .endDate(bookCreateRequest.getEndDate())
            .build();
    return bookConverter.convertFrom(bookRepository.save(bookEntity));
  }

  @Transactional(readOnly = true)
  public BookDto getBook(final Long id) {
    BookEntity bookEntity =
        bookRepository
            .findById(id)
            .orElseThrow(() -> new ReadingIsGoodException(ErrorCodeEnum.BOOK_NOT_FOUND_ERROR));
    return bookConverter.convertFrom(bookEntity);
  }

  @Transactional
  public void stockUpdate(Long bookId, Long count, StockUpdateType updateType) {
    BookEntity bookEntity =
        bookRepository
            .findById(bookId)
            .orElseThrow(() -> new ReadingIsGoodException(ErrorCodeEnum.BOOK_NOT_FOUND_ERROR));
    if (updateType.equals(StockUpdateType.DECREASE) && bookEntity.getCount() > 0) {
      bookEntity.setCount(bookEntity.getCount() - count);
    } else if (updateType.equals(StockUpdateType.INCREASE)) {
      bookEntity.setCount(bookEntity.getCount() + count);
    } else {
      log.info(
          "Book stock not updated because update type and count not valid bookId : {}", bookId);
      throw new ReadingIsGoodException(ErrorCodeEnum.BOOK_COUNT_NOT_UPDATED_ERROR);
    }
    bookRepository.save(bookEntity);
  }
}
