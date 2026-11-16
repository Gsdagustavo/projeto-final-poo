package com.gsdagustavo.entities.exception;

public class VehicleAlreadyInUseException extends RuntimeException {
    public VehicleAlreadyInUseException(String message) {
        super(message);
    }
}
