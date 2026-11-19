package com.gsdagustavo.entities.vehicle;

import com.gsdagustavo.entities.exception.InvalidValueException;
import com.gsdagustavo.entities.exception.VehicleAlreadyInUseException;
import com.gsdagustavo.entities.exception.VehicleHasNoBatteryException;
import com.gsdagustavo.entities.exception.VehicleInMaintenanceException;
import com.gsdagustavo.entities.exception.VehicleNotInMaintenanceException;
import com.gsdagustavo.entities.exception.VehicleNotInUseException;

public abstract class Vehicle {
    private final String code;
    private final String model;
    private VehicleStatus vehicleStatus;

    public Vehicle(String code, String model) {
        if (code == null || code.isBlank()) {
            throw new InvalidValueException("O código do veículo é obrigatório.");
        }
        if (model == null || model.isBlank()) {
            throw new InvalidValueException("O modelo do veículo é obrigatório.");
        }
        this.code = code.trim();
        this.model = model.trim();
        this.vehicleStatus = VehicleStatus.AVAILABLE;
    }

    public abstract String getTypeName();

    public abstract double getUnlockingFee();

    public abstract double getCostPerMinuteUsed();

    public String getCode() {
        return code;
    }

    public String getModel() {
        return model;
    }

    public VehicleStatus getVehicleStatus() {
        return vehicleStatus;
    }

    public boolean isInUse() {
        return vehicleStatus == VehicleStatus.IN_USE;
    }

    public boolean isAvailable() {
        return vehicleStatus == VehicleStatus.AVAILABLE;
    }

    public boolean isInMaintenance() {
        return vehicleStatus == VehicleStatus.IN_MAINTENANCE;
    }

    public boolean acceptsStudentDiscount() {
        return true;
    }

    public double calculateFullPrice(long minutes) {
        if (minutes < 0) {
            throw new InvalidValueException("A duração da viagem não pode ser negativa.");
        }
        return getUnlockingFee() + minutes * getCostPerMinuteUsed();
    }

    public void validateUnlock() throws VehicleInMaintenanceException, VehicleAlreadyInUseException {
        if (isInMaintenance()) {
            throw new VehicleInMaintenanceException("O veículo está em manutenção.");
        }
        if (isInUse()) {
            throw new VehicleAlreadyInUseException("O veículo já está em uso por outra pessoa.");
        }
    }

    public void unlock() {
        validateUnlock();
        this.vehicleStatus = VehicleStatus.IN_USE;
    }

    public void finishUse(long minutes) throws VehicleNotInUseException {
        if (!isInUse()) {
            throw new VehicleNotInUseException("O veículo não está em uso.");
        }
        if (minutes < 0) {
            throw new InvalidValueException("A duração da viagem não pode ser negativa.");
        }
        consumeEnergy(minutes);
        this.vehicleStatus = VehicleStatus.AVAILABLE;
    }

    protected void consumeEnergy(long minutes) {
    }

    public void rechargeBattery() throws VehicleHasNoBatteryException {
        throw new VehicleHasNoBatteryException("O veículo " + code + " não possui bateria.");
    }

    public void sendToMaintenance() throws VehicleAlreadyInUseException, VehicleInMaintenanceException {
        if (isInUse()) {
            throw new VehicleAlreadyInUseException("Não é possível enviar para manutenção um veículo em uso.");
        }
        if (isInMaintenance()) {
            throw new VehicleInMaintenanceException("O veículo já está em manutenção.");
        }
        this.vehicleStatus = VehicleStatus.IN_MAINTENANCE;
    }

    public void returnFromMaintenance() throws VehicleNotInMaintenanceException {
        if (!isInMaintenance()) {
            throw new VehicleNotInMaintenanceException("O veículo não está em manutenção.");
        }
        this.vehicleStatus = VehicleStatus.AVAILABLE;
    }

    public String getDescription() {
        return getTypeName() + " | código: " + code + " | modelo: " + model;
    }
}
