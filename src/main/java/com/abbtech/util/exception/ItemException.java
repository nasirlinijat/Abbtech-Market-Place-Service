package com.abbtech.util.exception;

public class ItemException extends RuntimeException{
    private final String message;

    public ItemException(String message) {
        super(message);
        this.message = message;
    }
}
