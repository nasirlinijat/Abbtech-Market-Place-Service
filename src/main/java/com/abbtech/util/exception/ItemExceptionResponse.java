package com.abbtech.util.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class ItemExceptionResponse {
    private String message;
    private int statusCode;
    private LocalDateTime timeStamp;
    private String path;
}
