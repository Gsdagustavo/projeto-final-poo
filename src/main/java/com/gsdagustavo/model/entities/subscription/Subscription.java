package com.gsdagustavo.entities.subscription;

import com.gsdagustavo.entities.exception.InvalidValueException;
import com.gsdagustavo.entities.util.MoneyUtils;
import com.gsdagustavo.entities.vehicle.Vehicle;

public abstract class Subscription {
    public double calculatePriceToPay(Vehicle vehicle, long minutes) {
        if (vehicle == null) {
            throw new InvalidValueException("O veículo é obrigatório.");
        }
        if (minutes < 0) {
            throw new InvalidValueException("A duração da viagem não pode ser negativa.");
        }
        return MoneyUtils.round(calculatePrice(vehicle, minutes));
    }

    protected abstract double calculatePrice(Vehicle vehicle, long minutes);

    public abstract String getName();
}
