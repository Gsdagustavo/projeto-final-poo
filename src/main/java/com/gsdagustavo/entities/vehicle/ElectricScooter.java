package com.gsdagustavo.entities.vehicle;

public class ElectricScooter extends ElectricVehicle {
    public ElectricScooter(String model, VehicleStatus vehicleStatus, int batteryPercentage) {
        super(model, vehicleStatus, 3.00, 0.40, 2, batteryPercentage);
    }
}
