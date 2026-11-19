package com.gsdagustavo.model.travel;

import com.gsdagustavo.model.exception.InvalidDateException;
import com.gsdagustavo.model.exception.InvalidValueException;
import com.gsdagustavo.model.exception.NoTravelInProgressException;
import com.gsdagustavo.model.exception.StationFullException;
import com.gsdagustavo.model.statement.StatementEntry;
import com.gsdagustavo.model.station.Station;
import com.gsdagustavo.model.subscription.Subscription;
import com.gsdagustavo.model.vehicle.Vehicle;

import java.time.Duration;
import java.time.LocalDateTime;

public class Travel implements StatementEntry {
    private final Vehicle vehicle;
    private final Station originStation;
    private final LocalDateTime startDate;
    private Station destinationStation;
    private LocalDateTime endDate;
    private long durationInMinutes;
    private double price;

    public Travel(Vehicle vehicle, Station originStation, LocalDateTime startDate) {
        if (vehicle == null) {
            throw new InvalidValueException("O veículo da viagem é obrigatório.");
        }
        if (originStation == null) {
            throw new InvalidValueException("A estação de origem é obrigatória.");
        }
        if (startDate == null) {
            throw new InvalidDateException("A data e hora de início são obrigatórias.");
        }
        this.vehicle = vehicle;
        this.originStation = originStation;
        this.startDate = startDate;
    }

    public void finish(Station destinationStation, LocalDateTime endDate, Subscription subscription) throws InvalidDateException, StationFullException {
        if (isFinished()) {
            throw new NoTravelInProgressException("A viagem já foi encerrada.");
        }
        if (destinationStation == null) {
            throw new InvalidValueException("A estação de destino é obrigatória.");
        }
        if (subscription == null) {
            throw new InvalidValueException("O plano do usuário é obrigatório.");
        }
        if (endDate == null || !endDate.isAfter(startDate)) {
            throw new InvalidDateException("A data e hora de término devem ser posteriores ao início da viagem.");
        }
        if (destinationStation.isFull()) {
            throw new StationFullException("A estação " + destinationStation.getName() + " está lotada.");
        }

        long minutes = Duration.between(startDate, endDate).toMinutes();
        double priceToPay = subscription.calculatePriceToPay(vehicle, minutes);

        vehicle.finishUse(minutes);
        destinationStation.storeVehicle(vehicle);

        this.destinationStation = destinationStation;
        this.endDate = endDate;
        this.durationInMinutes = minutes;
        this.price = priceToPay;
    }

    public boolean isFinished() {
        return endDate != null;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public Station getOriginStation() {
        return originStation;
    }

    public Station getDestinationStation() {
        return destinationStation;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public long getDurationInMinutes() {
        return durationInMinutes;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public LocalDateTime getStatementDateTime() {
        return endDate;
    }

    @Override
    public double getStatementValue() {
        return -price;
    }

    @Override
    public String getStatementDescription() {
        return "Viagem - " + vehicle.getTypeName() + " " + vehicle.getCode() + " (" + durationInMinutes + " min)";
    }
}
