package com.gsdagustavo.model.entities.exception;

public class VehicleAlreadyInUseException extends GiroBluException {
    public VehicleAlreadyInUseException(String message) {
        super(message);
    }
}
