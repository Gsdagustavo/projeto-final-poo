package com.gsdagustavo.entities.vehicle;

public class ElectricBicycle extends ElectricVehicle {
    public ElectricBicycle(String model, VehicleStatus vehicleStatus, int batteryPercentage) {
        super(model, vehicleStatus, VehicleConstants.ELECTRIC_BICYCLE_UNLOCKING_FEE, VehicleConstants.ELECTRIC_BICYCLE_COST_PER_MINUTE_USED, VehicleConstants.ELECTRIC_BICYCLE_BATTERY_USAGE_PER_MINUTE, batteryPercentage);
    }
}
