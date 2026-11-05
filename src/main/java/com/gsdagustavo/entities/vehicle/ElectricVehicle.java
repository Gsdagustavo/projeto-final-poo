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
        switch (this.getVehicleStatus()) {
            case AVAILABLE, IN_MAINTENANCE:
                this.batteryPercentage = 100;
                break;
            case IN_USE:
                throw new VehicleNotAvailableForRechargingException("Não é possível recarregar o veículo pois ele está em uso");
        }
    }
}
