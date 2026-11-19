package com.gsdagustavo.model.portfolio;

import com.gsdagustavo.model.exception.InvalidDateException;
import com.gsdagustavo.model.exception.InvalidValueException;
import com.gsdagustavo.model.statement.StatementEntry;
import com.gsdagustavo.model.util.MoneyUtils;

import java.time.LocalDateTime;

public class CreditPortfolioRecharge implements StatementEntry {
    private final LocalDateTime rechargedAt;
    private final double value;

    public CreditPortfolioRecharge(LocalDateTime rechargedAt, double value) {
        if (rechargedAt == null) {
            throw new InvalidDateException("A data e hora da recarga são obrigatórias.");
        }
        double roundedValue = MoneyUtils.round(value);
        if (roundedValue <= 0) {
            throw new InvalidValueException("O valor da recarga deve ser positivo.");
        }
        this.rechargedAt = rechargedAt;
        this.value = roundedValue;
    }

    public LocalDateTime getRechargedAt() {
        return rechargedAt;
    }

    public double getValue() {
        return value;
    }

    @Override
    public LocalDateTime getStatementDateTime() {
        return rechargedAt;
    }

    @Override
    public double getStatementValue() {
        return value;
    }

    @Override
    public String getStatementDescription() {
        return "Recarga";
    }
}
