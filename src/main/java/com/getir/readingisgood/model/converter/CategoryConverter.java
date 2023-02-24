package com.getir.readingisgood.model.converter;

import com.getir.readingisgood.model.dto.CategoryDto;
import com.getir.readingisgood.model.entity.CategoryEntity;
import org.springframework.stereotype.Component;

@Component
public class CategoryConverter {

  public CategoryDto convertFrom(final CategoryEntity entity) {
    return CategoryDto.builder()
        .id(entity.getId())
        .name(entity.getName())
        .detail(entity.getDetail())
        .createDate(entity.getCreateDate())
        .updateDate(entity.getUpdateDate())
        .build();
  }
}
