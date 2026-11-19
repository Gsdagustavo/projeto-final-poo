package com.gsdagustavo.model.entities.portfolio;

import com.gsdagustavo.model.entities.exception.InvalidValueException;
import com.gsdagustavo.model.entities.util.MoneyUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CreditPortfolio {
    public static final double MINIMUM_BALANCE_TO_TRAVEL = 5.0;
    private final List<CreditPortfolioRecharge> recharges;
    private double balance;

    public CreditPortfolio() {
        this.balance = 0;
        this.recharges = new ArrayList<>();
    }

    public double getBalance() {
        return balance;
    }

    public List<CreditPortfolioRecharge> getRecharges() {
        return recharges;
    }

    public CreditPortfolioRecharge recharge(LocalDateTime rechargedAt, double value) {
        CreditPortfolioRecharge recharge = new CreditPortfolioRecharge(rechargedAt, value);
        recharges.add(recharge);
        this.balance = MoneyUtils.round(balance + recharge.getValue());
        return recharge;
    }

    public void debit(double value) {
        if (value < 0) {
            throw new InvalidValueException("O valor do débito não pode ser negativo.");
        }
        this.balance = MoneyUtils.round(balance - value);
    }

    public boolean hasMinimumBalanceToTravel() {
        return balance >= MINIMUM_BALANCE_TO_TRAVEL;
    }
}
