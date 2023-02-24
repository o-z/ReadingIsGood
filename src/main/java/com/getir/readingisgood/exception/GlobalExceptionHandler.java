package com.getir.readingisgood.exception;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@ResponseBody
@Slf4j
public class GlobalExceptionHandler {

  private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

  @ExceptionHandler(value = ReadingIsGoodException.class)
  public ResponseEntity ReadingIsGoodExceptionHandler(ReadingIsGoodException e) {
    logger.info("Reading Is Good Api related exception => {}", e.getMessage());
    return new ResponseEntity(e, e.getStatus());
  }

  @ExceptionHandler(value = RuntimeException.class)
  public ResponseEntity runtimeExceptionHandler(RuntimeException e) {
    logger.error(e.getMessage());
    return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
