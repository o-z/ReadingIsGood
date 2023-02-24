package com.getir.readingisgood.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ReadingIsGoodException extends RuntimeException {

  private final Integer code;
  private final String message;
  private final HttpStatus status;

  public ReadingIsGoodException(ErrorCodeEnum errorCodeEnum) {
    super(errorCodeEnum.getMessage());
    this.code = errorCodeEnum.getCode();
    this.message = errorCodeEnum.getMessage();
    this.status = errorCodeEnum.getHttpStatus();
  }

  @Override
  public synchronized Throwable fillInStackTrace() {
    return null;
  }
}
