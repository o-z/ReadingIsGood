package com.getir.readingisgood.model.converter;

import com.getir.readingisgood.model.dto.BookDto;
import com.getir.readingisgood.model.entity.BookEntity;
import org.springframework.stereotype.Component;

@Component
public class BookConverter {

  public BookDto convertFrom(final BookEntity entity) {
    return BookDto.builder()
        .id(entity.getId())
        .name(entity.getName())
        .detail(entity.getDetail())
        .status(entity.getStatus())
        .categoryId(entity.getCategoryEntity().getId())
        .endDate(entity.getEndDate())
        .price(entity.getPrice())
        .count(entity.getCount())
        .createDate(entity.getCreateDate())
        .updateDate(entity.getUpdateDate())
        .build();
  }
}
