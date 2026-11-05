package com.gsdagustavo.entities.vehicle;

public class ElectricScooter extends ElectricVehicle {
    public ElectricScooter(String model, VehicleStatus vehicleStatus, int batteryPercentage) {
        super(model, vehicleStatus, VehicleConstants.ELECTRIC_SCOOTER_UNLOCKING_FEE, VehicleConstants.ELECTRIC_SCOOTER_COST_PER_MINUTE_USED, VehicleConstants.ELECTRIC_SCOOTER_BATTERY_USAGE_PER_MINUTE, batteryPercentage);
    }
}
