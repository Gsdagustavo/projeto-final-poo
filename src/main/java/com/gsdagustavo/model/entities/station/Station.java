package com.gsdagustavo.entities.station;

import com.gsdagustavo.entities.exception.InvalidValueException;
import com.gsdagustavo.entities.exception.NotFoundException;
import com.gsdagustavo.entities.exception.StationFullException;
import com.gsdagustavo.entities.exception.VehicleAlreadyInUseException;
import com.gsdagustavo.entities.vehicle.Vehicle;

import java.util.ArrayList;
import java.util.List;

public class Station {
    private final int vacancies;
    private final String name;
    private final List<Vehicle> vehicles;

    public Station(String name, int vacancies) {
        if (name == null || name.isBlank()) {
            throw new InvalidValueException("O nome da estação é obrigatório.");
        }
        if (vacancies <= 0) {
            throw new InvalidValueException("A quantidade de vagas deve ser maior que zero.");
        }
        this.name = name.trim();
        this.vacancies = vacancies;
        this.vehicles = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public int getTotalVacancies() {
        return vacancies;
    }

    public int getOccupiedVacancies() {
        return vehicles.size();
    }

    public int getAvailableVacancies() {
        return vacancies - vehicles.size();
    }

    public boolean isFull() {
        return vehicles.size() >= vacancies;
    }

    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    public List<Vehicle> getAvailableVehicles() {
        List<Vehicle> availableVehicles = new ArrayList<>();
        for (final var it : vehicles) {
            if (it.isAvailable()) {
                availableVehicles.add(it);
            }
        }
        return availableVehicles;
    }

    public Vehicle findVehicle(String vehicleCode) {
        if (vehicleCode == null) {
            return null;
        }
        for (final var it : vehicles) {
            if (it.getCode().equals(vehicleCode.trim())) {
                return it;
            }
        }
        return null;
    }

    public Vehicle takeVehicle(String vehicleCode) throws NotFoundException {
        Vehicle vehicle = findVehicle(vehicleCode);
        if (vehicle == null) {
            throw new NotFoundException("O veículo " + vehicleCode + " não está na estação " + name + ".");
        }
        vehicle.unlock();
        vehicles.remove(vehicle);
        return vehicle;
    }

    public void storeVehicle(Vehicle vehicle) throws StationFullException, VehicleAlreadyInUseException {
        if (vehicle == null) {
            throw new InvalidValueException("O veículo é obrigatório.");
        }
        if (vehicles.contains(vehicle)) {
            return;
        }
        if (vehicle.isInUse()) {
            throw new VehicleAlreadyInUseException("Um veículo em uso não pode ser guardado na estação.");
        }
        if (isFull()) {
            throw new StationFullException("A estação " + name + " está lotada.");
        }
        vehicles.add(vehicle);
    }
}
