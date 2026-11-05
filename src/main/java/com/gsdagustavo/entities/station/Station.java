package com.gsdagustavo.entities.station;

import com.gsdagustavo.entities.exception.ElectricVehicleChargeTooLow;
import com.gsdagustavo.entities.exception.StationFullException;
import com.gsdagustavo.entities.vehicle.ElectricVehicle;
import com.gsdagustavo.entities.vehicle.Vehicle;

import java.util.List;

public class Station {
    private final int vacancies;
    private final String name;
    private List<Vehicle> vehicles;

    public Station(String name, int vacancies) {
        this.name = name;
        this.vacancies = vacancies;
    }

    public String getName() {
        return name;
    }

    public int getTotalVacancies() {
        return vacancies;
    }

    public int getAvailableVacancies() {
        var total = 0;
        for (final var it : vehicles) {
            if (!it.isInUse()) {
                total++;
            }
        }
        return total;
    }

    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    public Vehicle takeVehicle(int vehicleCode) throws ElectricVehicleChargeTooLow {
        Vehicle vehicle;
        for (final var it : vehicles) {
            if (it.getCode() == vehicleCode) {
                vehicle = it;
            }
        }

        if (vehicle == null) {
            return null;
        }

        if (vehicle instanceof ElectricVehicle) {
            if (((ElectricVehicle) vehicle).getBatteryPercentage() < 20) {
                throw new ElectricVehicleChargeTooLow("A carga do veículo está baixa, não é possível alugá-lo");
            }
        }

        vehicles.remove(vehicle);

        return vehicle;
    }

    public void storeVehicle(Vehicle vehicle) throws StationFullException {
        if (vehicles.size() >= vacancies) {
            throw new StationFullException("A estação está lotada.");
        }
        vehicles.add(vehicle);
    }
}
