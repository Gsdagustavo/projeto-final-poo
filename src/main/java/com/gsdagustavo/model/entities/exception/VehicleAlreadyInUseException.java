package com.gsdagustavo.entities.exception;

public class VehicleAlreadyInUseException extends GiroBluException {
    public VehicleAlreadyInUseException(String message) {
        super(message);
    }
}
