package com.gsdagustavo.model.vehicle;

public class ElectricScooter extends ElectricVehicle {
    public static final double UNLOCKING_FEE = 3.0;
    public static final double COST_PER_MINUTE_USED = 0.40;
    public static final int BATTERY_USAGE_PERCENTAGE_PER_MINUTE = 2;

    public ElectricScooter(String code, String model, int batteryPercentage) {
        super(code, model, batteryPercentage);
    }

    @Override
    public String getTypeName() {
        return "Patinete elétrico";
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

    @Override
    public boolean acceptsStudentDiscount() {
        return false;
    }
}
