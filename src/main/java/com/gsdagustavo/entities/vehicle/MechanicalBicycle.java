package com.gsdagustavo.entities.vehicle;

public class MechanicalBicycle extends Vehicle {
    public MechanicalBicycle(String model, VehicleStatus vehicleStatus) {
        super(model, vehicleStatus, VehicleConstants.MECHANICAL_BICYCLE_UNLOCKING_FEE, VehicleConstants.MECHANICAL_BICYCLE_COST_PER_MINUTE_USED);
    }
}
