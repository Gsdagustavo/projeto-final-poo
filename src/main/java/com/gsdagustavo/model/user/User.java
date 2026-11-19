package com.gsdagustavo.model.user;

import com.gsdagustavo.model.exception.InsufficientBalanceException;
import com.gsdagustavo.model.exception.InvalidDateException;
import com.gsdagustavo.model.exception.InvalidValueException;
import com.gsdagustavo.model.exception.NoTravelInProgressException;
import com.gsdagustavo.model.exception.TravelInProgressException;
import com.gsdagustavo.model.portfolio.CreditPortfolio;
import com.gsdagustavo.model.portfolio.CreditPortfolioRecharge;
import com.gsdagustavo.model.statement.StatementEntry;
import com.gsdagustavo.model.statement.StatementLine;
import com.gsdagustavo.model.station.Station;
import com.gsdagustavo.model.subscription.Subscription;
import com.gsdagustavo.model.travel.Travel;
import com.gsdagustavo.model.util.MoneyUtils;
import com.gsdagustavo.model.vehicle.Vehicle;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class User {
    private final String name;
    private final String email;
    private final Subscription subscription;
    private final CreditPortfolio creditPortfolio;
    private final List<Travel> finishedTravels;
    private Travel currentTravel;

    public User(String name, String email, Subscription subscription) {
        if (name == null || name.isBlank()) {
            throw new InvalidValueException("O nome do usuário é obrigatório.");
        }
        if (email == null || email.isBlank() || !email.contains("@")) {
            throw new InvalidValueException("O e-mail do usuário é inválido.");
        }
        if (subscription == null) {
            throw new InvalidValueException("O plano do usuário é obrigatório.");
        }
        this.name = name.trim();
        this.email = email.trim();
        this.subscription = subscription;
        this.creditPortfolio = new CreditPortfolio();
        this.finishedTravels = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public Subscription getSubscription() {
        return subscription;
    }

    public CreditPortfolio getCreditPortfolio() {
        return creditPortfolio;
    }

    public Travel getCurrentTravel() {
        return currentTravel;
    }

    public List<Travel> getFinishedTravels() {
        return finishedTravels;
    }

    public boolean hasTravelInProgress() {
        return currentTravel != null;
    }

    public CreditPortfolioRecharge recharge(LocalDateTime rechargedAt, double value) {
        return creditPortfolio.recharge(rechargedAt, value);
    }

    public Travel startTravel(Station originStation, String vehicleCode, LocalDateTime startDate) throws TravelInProgressException, InsufficientBalanceException {
        if (originStation == null) {
            throw new InvalidValueException("A estação de origem é obrigatória.");
        }
        if (startDate == null) {
            throw new InvalidDateException("A data e hora de início são obrigatórias.");
        }
        if (hasTravelInProgress()) {
            throw new TravelInProgressException("O usuário já possui uma viagem em andamento.");
        }
        if (!creditPortfolio.hasMinimumBalanceToTravel()) {
            throw new InsufficientBalanceException("Saldo insuficiente. É necessário ter pelo menos R$ 5,00 para iniciar uma viagem.");
        }

        Vehicle vehicle = originStation.takeVehicle(vehicleCode);
        this.currentTravel = new Travel(vehicle, originStation, startDate);
        return currentTravel;
    }

    public Travel finishTravel(Station destinationStation, LocalDateTime endDate) throws NoTravelInProgressException {
        if (!hasTravelInProgress()) {
            throw new NoTravelInProgressException("O usuário não possui viagem em andamento.");
        }

        Travel travel = currentTravel;
        travel.finish(destinationStation, endDate, subscription);
        creditPortfolio.debit(travel.getPrice());
        finishedTravels.add(travel);
        this.currentTravel = null;
        return travel;
    }

    public List<StatementLine> getStatement() {
        List<StatementEntry> entries = new ArrayList<>();
        for (final var it : creditPortfolio.getRecharges()) {
            entries.add(it);
        }
        for (final var it : finishedTravels) {
            entries.add(it);
        }

        for (int i = 1; i < entries.size(); i++) {
            StatementEntry current = entries.get(i);
            int j = i - 1;
            while (j >= 0 && entries.get(j).getStatementDateTime().isAfter(current.getStatementDateTime())) {
                entries.set(j + 1, entries.get(j));
                j--;
            }
            entries.set(j + 1, current);
        }

        List<StatementLine> lines = new ArrayList<>();
        double balance = 0;
        for (final var it : entries) {
            balance = MoneyUtils.round(balance + it.getStatementValue());
            lines.add(new StatementLine(it, balance));
        }
        return lines;
    }
}
