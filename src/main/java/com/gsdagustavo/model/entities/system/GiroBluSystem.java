package com.gsdagustavo.entities.system;

import com.gsdagustavo.entities.exception.DuplicateRegistrationException;
import com.gsdagustavo.entities.exception.InvalidValueException;
import com.gsdagustavo.entities.exception.NotFoundException;
import com.gsdagustavo.entities.portfolio.CreditPortfolioRecharge;
import com.gsdagustavo.entities.report.BillingReport;
import com.gsdagustavo.entities.statement.StatementLine;
import com.gsdagustavo.entities.station.Station;
import com.gsdagustavo.entities.subscription.Subscription;
import com.gsdagustavo.entities.travel.Travel;
import com.gsdagustavo.entities.user.User;
import com.gsdagustavo.entities.vehicle.Vehicle;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class GiroBluSystem {
    private final List<Station> stations;
    private final List<User> users;

    public GiroBluSystem() {
        this.stations = new ArrayList<>();
        this.users = new ArrayList<>();
    }

    public List<Station> getStations() {
        return stations;
    }

    public List<User> getUsers() {
        return users;
    }

    public Station registerStation(String name, int vacancies) throws DuplicateRegistrationException {
        Station station = new Station(name, vacancies);
        if (findStationOrNull(station.getName()) != null) {
            throw new DuplicateRegistrationException("Já existe uma estação com o nome " + station.getName() + ".");
        }
        stations.add(station);
        return station;
    }

    public void registerVehicle(Vehicle vehicle, String stationName) throws DuplicateRegistrationException {
        if (vehicle == null) {
            throw new InvalidValueException("O veículo é obrigatório.");
        }
        if (findVehicleOrNull(vehicle.getCode()) != null) {
            throw new DuplicateRegistrationException("Já existe um veículo com o código " + vehicle.getCode() + ".");
        }
        Station station = findStation(stationName);
        station.storeVehicle(vehicle);
    }

    public User registerUser(String name, String email, Subscription subscription) throws DuplicateRegistrationException {
        User user = new User(name, email, subscription);
        if (findUserOrNull(user.getEmail()) != null) {
            throw new DuplicateRegistrationException("Já existe um usuário com o e-mail " + user.getEmail() + ".");
        }
        users.add(user);
        return user;
    }

    public Station findStation(String name) throws NotFoundException {
        Station station = findStationOrNull(name);
        if (station == null) {
            throw new NotFoundException("Estação " + name + " não encontrada.");
        }
        return station;
    }

    public User findUser(String email) throws NotFoundException {
        User user = findUserOrNull(email);
        if (user == null) {
            throw new NotFoundException("Usuário com e-mail " + email + " não encontrado.");
        }
        return user;
    }

    public Vehicle findVehicle(String code) throws NotFoundException {
        Vehicle vehicle = findVehicleOrNull(code);
        if (vehicle == null) {
            throw new NotFoundException("Veículo " + code + " não encontrado.");
        }
        return vehicle;
    }

    public CreditPortfolioRecharge rechargeUserCredits(String email, LocalDateTime rechargedAt, double value) {
        return findUser(email).recharge(rechargedAt, value);
    }

    public Travel startTravel(String email, String vehicleCode, String stationName, LocalDateTime startDate) {
        User user = findUser(email);
        Station station = findStation(stationName);
        return user.startTravel(station, vehicleCode, startDate);
    }

    public Travel finishTravel(String email, String stationName, LocalDateTime endDate) {
        User user = findUser(email);
        Station station = findStation(stationName);
        return user.finishTravel(station, endDate);
    }

    public void sendVehicleToMaintenance(String vehicleCode) {
        findVehicle(vehicleCode).sendToMaintenance();
    }

    public void returnVehicleFromMaintenance(String vehicleCode) {
        findVehicle(vehicleCode).returnFromMaintenance();
    }

    public void rechargeVehicleBattery(String vehicleCode) {
        findVehicle(vehicleCode).rechargeBattery();
    }

    public List<Vehicle> getAvailableVehicles(String stationName) {
        return findStation(stationName).getAvailableVehicles();
    }

    public List<StatementLine> getStatement(String email) {
        return findUser(email).getStatement();
    }

    public BillingReport getBillingReport(LocalDate startDate, LocalDate endDate) {
        BillingReport report = new BillingReport(startDate, endDate);
        for (final var user : users) {
            for (final var travel : user.getFinishedTravels()) {
                if (report.isInPeriod(travel)) {
                    report.addTravel(travel);
                }
            }
        }
        return report;
    }

    private Station findStationOrNull(String name) {
        if (name == null) {
            return null;
        }
        for (final var it : stations) {
            if (it.getName().equalsIgnoreCase(name.trim())) {
                return it;
            }
        }
        return null;
    }

    private User findUserOrNull(String email) {
        if (email == null) {
            return null;
        }
        for (final var it : users) {
            if (it.getEmail().equalsIgnoreCase(email.trim())) {
                return it;
            }
        }
        return null;
    }

    private Vehicle findVehicleOrNull(String code) {
        if (code == null) {
            return null;
        }
        for (final var station : stations) {
            Vehicle vehicle = station.findVehicle(code);
            if (vehicle != null) {
                return vehicle;
            }
        }
        for (final var user : users) {
            if (user.hasTravelInProgress() && user.getCurrentTravel().getVehicle().getCode().equals(code.trim())) {
                return user.getCurrentTravel().getVehicle();
            }
        }
        return null;
    }
}
