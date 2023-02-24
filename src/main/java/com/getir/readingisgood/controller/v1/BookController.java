package com.getir.readingisgood.controller.v1;

import com.getir.readingisgood.model.dto.BookDto;
import com.getir.readingisgood.model.request.BookCreateRequest;
import com.getir.readingisgood.service.BookService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/books")
@RequiredArgsConstructor
@Slf4j
@SecurityRequirement(name = "bearerAuth")
public class BookController {

  private final BookService bookService;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public BookDto createBook(@Valid @RequestBody BookCreateRequest request) {
    return bookService.createBook(request);
  }

  @GetMapping("/{id}")
  public ResponseEntity<BookDto> getBook(@PathVariable("id") Long id) {
    return ResponseEntity.ok(bookService.getBook(id));
  }
}
