package com.gsdagustavo.entities.exception;

public class StationFullException extends RuntimeException {
    public StationFullException(String message) {
        super(message);
    }
}
