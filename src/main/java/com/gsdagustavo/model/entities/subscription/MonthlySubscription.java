package com.gsdagustavo.model.entities.subscription;

import com.gsdagustavo.model.entities.vehicle.Vehicle;

public class MonthlySubscription extends Subscription {
    public static final int FREE_MINUTES = 15;

    @Override
    protected double calculatePrice(Vehicle vehicle, long minutes) {
        long chargedMinutes = minutes - FREE_MINUTES;
        if (chargedMinutes < 0) {
            chargedMinutes = 0;
        }
        return chargedMinutes * vehicle.getCostPerMinuteUsed();
    }

    @Override
    public String getName() {
        return "Mensal";
    }
}
