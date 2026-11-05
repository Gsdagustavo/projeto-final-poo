package com.gsdagustavo.entities.vehicle;

public abstract class Vehicle {
    private static int CODE = 1;
    private final int code;
    private final String model;
    private final double unlockingFee;
    private final double costPerMinuteUsed;
    private VehicleStatus vehicleStatus;

    public Vehicle(String model, VehicleStatus vehicleStatus, double unlockingFee, double costPerMinuteUsed) {
        this.model = model;
        this.vehicleStatus = vehicleStatus;
        this.unlockingFee = unlockingFee;
        this.costPerMinuteUsed = costPerMinuteUsed;
        this.code = CODE;
        CODE++;
    }

    public int getCode() {
        return code;
    }

    public String getModel() {
        return model;
    }

    public VehicleStatus getVehicleStatus() {
        return vehicleStatus;
    }

    public void setVehicleStatus(VehicleStatus vehicleStatus) {
        this.vehicleStatus = vehicleStatus;
    }

    public double getUnlockingFee() {
        return unlockingFee;
    }

    public double getCostPerMinuteUsed() {
        return costPerMinuteUsed;
    }

    public boolean isInUse() {
        return vehicleStatus == VehicleStatus.IN_USE;
    }
}
