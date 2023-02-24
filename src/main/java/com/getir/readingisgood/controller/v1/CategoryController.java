package com.getir.readingisgood.controller.v1;

import com.getir.readingisgood.model.dto.CategoryDto;
import com.getir.readingisgood.model.request.CategoryCreateRequest;
import com.getir.readingisgood.service.CategoryService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/categories")
@RequiredArgsConstructor
@Slf4j
@SecurityRequirement(name = "bearerAuth")
public class CategoryController {
  private final CategoryService categoryService;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public CategoryDto createCategory(@Valid @RequestBody CategoryCreateRequest request) {
    return categoryService.createCategory(request);
  }

  @GetMapping("/{id}")
  public ResponseEntity<CategoryDto> getCategory(@PathVariable("id") Long id) {
    return ResponseEntity.ok(categoryService.getCategory(id));
  }
}
