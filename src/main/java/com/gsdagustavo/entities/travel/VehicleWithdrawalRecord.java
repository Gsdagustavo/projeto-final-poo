package com.gsdagustavo.entities.travel;

import com.gsdagustavo.entities.user.User;
import com.gsdagustavo.entities.vehicle.Vehicle;

import java.time.LocalDateTime;

public record VehicleWithdrawalRecord(Vehicle vehicle, User user, LocalDateTime withdrawnAt, LocalDateTime vehicleStoredAt) {
}
