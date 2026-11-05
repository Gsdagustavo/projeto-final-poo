package com.gsdagustavo.entities.vehicle;

import com.gsdagustavo.entities.exception.VehicleNotAvailableForRechargingException;

public abstract class ElectricVehicle extends Vehicle {
    private final int batteryUsagePercentagePerMinute;
    private int batteryPercentage;

    public ElectricVehicle(String model, VehicleStatus vehicleStatus, double unlockingFee, double costPerMinuteUsed, int batteryUsagePercentagePerMinute, int batteryPercentage) {
        super(model, vehicleStatus, unlockingFee, costPerMinuteUsed);
        this.batteryUsagePercentagePerMinute = batteryUsagePercentagePerMinute;
        this.batteryPercentage = batteryPercentage;
    }

    public int getBatteryPercentage() {
        return batteryPercentage;
    }

    public int getBatteryUsagePercentagePerMinute() {
        return batteryUsagePercentagePerMinute;
    }

    public void rechargeBattery() throws VehicleNotAvailableForRechargingException {
        if (this.isInUse()) {
            throw new VehicleNotAvailableForRechargingException("Não é possível recarregar o veículo pois ele está em uso");
        }
        this.batteryPercentage = 100;
    }
}
