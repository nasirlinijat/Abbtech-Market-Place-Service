package com.abbtech.exception;

import java.io.Serial;

public class ProductException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 112312L;

    private String message;

    public ProductException(String message) {
        super(message);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}
