package com.gsdagustavo.entities.subscription;

import com.gsdagustavo.entities.vehicle.Vehicle;

public abstract class Subscription {
    public abstract double calculatePriceToPay(Vehicle vehicle);
}
