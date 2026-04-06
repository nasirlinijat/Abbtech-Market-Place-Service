package com.abbtech.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({ProductException.class})
    public ResponseEntity<RespExceptionDto> handleException(ProductException exception, WebRequest webRequest) {
        return ResponseEntity.badRequest().body(new RespExceptionDto(exception.getMessage(), webRequest.getContextPath(), LocalDateTime.now(), 400));
    }


    @ExceptionHandler({Throwable.class})
    public ResponseEntity<RespExceptionDto> handleException(Throwable throwable, WebRequest webRequest) {
        return ResponseEntity.internalServerError().body(new RespExceptionDto(throwable.getMessage(), webRequest.getContextPath(), LocalDateTime.now(), 500));
    }

}
