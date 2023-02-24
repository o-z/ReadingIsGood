package com.getir.readingisgood.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCodeEnum {
  INTERNAL_SERVER_ERROR(1000, "Internal server error.", HttpStatus.INTERNAL_SERVER_ERROR),
  FIELD_VALIDATION_ERROR(1001, "Field validation error.", HttpStatus.BAD_REQUEST),
  CATEGORY_NOT_FOUND_ERROR(1002, "Category not found.", HttpStatus.BAD_REQUEST),
  BOOK_NOT_FOUND_ERROR(1003, "Book not found.", HttpStatus.BAD_REQUEST),
  CUSTOMER_ADDRESS_NOT_FOUND_ERROR(1004, "Customer address not found.", HttpStatus.BAD_REQUEST),
  ORDER_NOT_FOUND_ERROR(1005, "Order not found.", HttpStatus.BAD_REQUEST),
  BOOK_COUNT_NOT_UPDATED_ERROR(1006, "Book count not updated.", HttpStatus.BAD_REQUEST),
  ORDER_ERROR(1007, "Order exception", HttpStatus.BAD_REQUEST),
  ORDER_NOT_VALID_ERROR(1008, "Order not valid.", HttpStatus.BAD_REQUEST);



  private final int code;
  private final String message;
  private final HttpStatus httpStatus;
}
