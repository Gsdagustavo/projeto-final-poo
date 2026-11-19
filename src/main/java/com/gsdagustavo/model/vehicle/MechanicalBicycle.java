package com.gsdagustavo.model.vehicle;

public class MechanicalBicycle extends Vehicle {
    public static final double UNLOCKING_FEE = 1.0;
    public static final double COST_PER_MINUTE_USED = 0.15;

    public MechanicalBicycle(String code, String model) {
        super(code, model);
    }

    @Override
    public String getTypeName() {
        return "Bicicleta mecânica";
    }

    @Override
    public double getUnlockingFee() {
        return UNLOCKING_FEE;
    }

    @Override
    public double getCostPerMinuteUsed() {
        return COST_PER_MINUTE_USED;
    }
}
