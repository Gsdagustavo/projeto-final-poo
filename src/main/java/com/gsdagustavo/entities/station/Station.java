package com.gsdagustavo.entities.station;

import com.gsdagustavo.entities.exception.StationFullException;
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

    public Vehicle takeVehicle(int vehicleCode) {
        for (final var it : vehicles) {
            if (it.getCode() == vehicleCode) {
                vehicles.remove(it);
                return it;
            }
        }
        return null;
    }

    public void storeVehicle(Vehicle vehicle) throws StationFullException {
        if (vehicles.size() >= vacancies) {
            throw new StationFullException("A estação está lotada.");
        }
        vehicles.add(vehicle);
    }
}
