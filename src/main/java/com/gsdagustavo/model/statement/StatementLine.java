package com.gsdagustavo.model.statement;

import java.time.LocalDateTime;

public class StatementLine {
    private final StatementEntry entry;
    private final double balanceAfter;

    public StatementLine(StatementEntry entry, double balanceAfter) {
        this.entry = entry;
        this.balanceAfter = balanceAfter;
    }

    public LocalDateTime getDateTime() {
        return entry.getStatementDateTime();
    }

    public String getDescription() {
        return entry.getStatementDescription();
    }

    public double getValue() {
        return entry.getStatementValue();
    }

    public boolean isCredit() {
        return entry.getStatementValue() >= 0;
    }

    public double getBalanceAfter() {
        return balanceAfter;
    }
}
