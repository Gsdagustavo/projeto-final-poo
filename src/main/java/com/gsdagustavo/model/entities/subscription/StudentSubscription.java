package com.gsdagustavo.entities.subscription;

import com.gsdagustavo.entities.vehicle.Vehicle;

public class StudentSubscription extends Subscription {
    public static final double DISCOUNT_PERCENTAGE = 0.5;

    @Override
    protected double calculatePrice(Vehicle vehicle, long minutes) {
        double fullPrice = vehicle.calculateFullPrice(minutes);
        if (vehicle.acceptsStudentDiscount()) {
            return fullPrice * (1 - DISCOUNT_PERCENTAGE);
        }
        return fullPrice;
    }

    @Override
    public String getName() {
        return "Universitário";
    }
}
