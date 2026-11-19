package com.gsdagustavo.entities.exception;

public class InsufficientBalanceException extends GiroBluException {
    public InsufficientBalanceException(String message) {
        super(message);
    }
}
