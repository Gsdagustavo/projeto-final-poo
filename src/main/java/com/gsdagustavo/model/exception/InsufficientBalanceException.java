package com.gsdagustavo.model.exception;

public class InsufficientBalanceException extends GiroBluException {
    public InsufficientBalanceException(String message) {
        super(message);
    }
}
