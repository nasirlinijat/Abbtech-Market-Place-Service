package com.abbtech.util.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ItemException.class)
    public ResponseEntity<ItemExceptionResponse> handleItemException(ItemException itemException, WebRequest webRequest) {

        return ResponseEntity.badRequest().body(new ItemExceptionResponse(
                itemException.getMessage(),
                400,
                LocalDateTime.now(),
                webRequest.getContextPath()));
    }
}
