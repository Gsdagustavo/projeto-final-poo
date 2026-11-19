package com.gsdagustavo.entities.vehicle;

import com.gsdagustavo.entities.exception.ElectricVehicleChargeTooLowException;
import com.gsdagustavo.entities.exception.InvalidValueException;
import com.gsdagustavo.entities.exception.VehicleNotAvailableForRechargingException;

public abstract class ElectricVehicle extends Vehicle {
    public static final int MINIMUM_BATTERY_PERCENTAGE_TO_UNLOCK = 20;
    public static final int FULL_BATTERY_PERCENTAGE = 100;

    private int batteryPercentage;

    public ElectricVehicle(String code, String model, int batteryPercentage) {
        super(code, model);
        if (batteryPercentage < 0 || batteryPercentage > FULL_BATTERY_PERCENTAGE) {
            throw new InvalidValueException("A carga da bateria deve estar entre 0% e 100%.");
        }
        this.batteryPercentage = batteryPercentage;
    }

    public abstract int getBatteryUsagePercentagePerMinute();

    public int getBatteryPercentage() {
        return batteryPercentage;
    }

    @Override
    public void validateUnlock() {
        super.validateUnlock();
        if (batteryPercentage < MINIMUM_BATTERY_PERCENTAGE_TO_UNLOCK) {
            throw new ElectricVehicleChargeTooLowException("A carga do veículo está abaixo de " + MINIMUM_BATTERY_PERCENTAGE_TO_UNLOCK + "%, não é possível liberá-lo.");
        }
    }

    @Override
    protected void consumeEnergy(long minutes) {
        long consumed = minutes * getBatteryUsagePercentagePerMinute();
        if (consumed >= batteryPercentage) {
            this.batteryPercentage = 0;
        } else {
            this.batteryPercentage = batteryPercentage - (int) consumed;
        }
    }

    @Override
    public void rechargeBattery() throws VehicleNotAvailableForRechargingException {
        if (isInUse()) {
            throw new VehicleNotAvailableForRechargingException("Não é possível recarregar o veículo pois ele está em uso.");
        }
        this.batteryPercentage = FULL_BATTERY_PERCENTAGE;
    }

    @Override
    public String getDescription() {
        return super.getDescription() + " | bateria: " + batteryPercentage + "%";
    }
}
