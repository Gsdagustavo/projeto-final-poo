package com.gsdagustavo.entities.vehicle;

public class ElectricBicycle extends ElectricVehicle {
    public static final double UNLOCKING_FEE = 2.0;
    public static final double COST_PER_MINUTE_USED = 0.30;
    public static final int BATTERY_USAGE_PERCENTAGE_PER_MINUTE = 1;

    public ElectricBicycle(String code, String model, int batteryPercentage) {
        super(code, model, batteryPercentage);
    }

    @Override
    public String getTypeName() {
        return "Bicicleta elétrica";
    }

    @Override
    public double getUnlockingFee() {
        return UNLOCKING_FEE;
    }

    @Override
    public double getCostPerMinuteUsed() {
        return COST_PER_MINUTE_USED;
    }

    @Override
    public int getBatteryUsagePercentagePerMinute() {
        return BATTERY_USAGE_PERCENTAGE_PER_MINUTE;
    }
}
