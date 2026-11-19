package com.gsdagustavo.model.subscription;

import com.gsdagustavo.model.vehicle.Vehicle;

public class SingleSubscription extends Subscription {
    @Override
    protected double calculatePrice(Vehicle vehicle, long minutes) {
        return vehicle.calculateFullPrice(minutes);
    }

    @Override
    public String getName() {
        return "Avulso";
    }
}
