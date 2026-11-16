package com.gsdagustavo.entities.subscription;

import com.gsdagustavo.entities.travel.Travel;
import com.gsdagustavo.entities.vehicle.ElectricScooter;
import com.gsdagustavo.entities.vehicle.Vehicle;

public class StudentSubscription {
    public double calculatePriceToPay(Travel travel) {
        var totalPrice = 0.0;
        for (final var withdrawal : travel.getWithdrawalRecords()) {
            final var vehicle = withdrawal.vehicle();
            if (vehicle instanceof ElectricScooter) {

            }
        }
    }
}
