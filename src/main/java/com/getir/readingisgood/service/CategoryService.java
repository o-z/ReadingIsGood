package com.getir.readingisgood.service;

import com.getir.readingisgood.exception.ErrorCodeEnum;
import com.getir.readingisgood.exception.ReadingIsGoodException;
import com.getir.readingisgood.model.converter.CategoryConverter;
import com.getir.readingisgood.model.dto.CategoryDto;
import com.getir.readingisgood.model.entity.CategoryEntity;
import com.getir.readingisgood.model.request.CategoryCreateRequest;
import com.getir.readingisgood.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CategoryService {
  private final CategoryRepository categoryRepository;
  private final CategoryConverter categoryConverter;

  @CachePut(
      value = "category",
      key = "#result.id",
      cacheManager = "tenMinuteCacheManager",
      unless = "#result==null")
  @Transactional
  public CategoryDto createCategory(final CategoryCreateRequest categoryCreateRequest) {
    CategoryEntity categoryEntity =
        CategoryEntity.builder()
            .name(categoryCreateRequest.getName())
            .detail(categoryCreateRequest.getDetail())
            .build();
    return categoryConverter.convertFrom(categoryRepository.save(categoryEntity));
  }

  @Cacheable(
      value = "category",
      key = "#id",
      cacheManager = "tenMinuteCacheManager",
      unless = "#result==null")
  @Transactional(readOnly = true)
  public CategoryDto getCategory(final Long id) {
    CategoryEntity categoryEntity =
        categoryRepository
            .findById(id)
            .orElseThrow(
                () -> new ReadingIsGoodException(ErrorCodeEnum.CATEGORY_NOT_FOUND_ERROR));
    return categoryConverter.convertFrom(categoryEntity);
  }

  @Transactional(readOnly = true)
  public CategoryEntity getCategoryEntity(Long categoryId) {
    return categoryRepository
        .findById(categoryId)
        .orElseThrow(() -> new ReadingIsGoodException(ErrorCodeEnum.CATEGORY_NOT_FOUND_ERROR));
  }
}
