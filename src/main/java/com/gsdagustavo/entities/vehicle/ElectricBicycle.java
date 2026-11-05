package com.gsdagustavo.entities.vehicle;

public class ElectricBicycle extends ElectricVehicle {
    public ElectricBicycle(String model, VehicleStatus vehicleStatus, int batteryPercentage) {
        super(model, vehicleStatus, 2.00, 0.30, 1, batteryPercentage);
    }
}
