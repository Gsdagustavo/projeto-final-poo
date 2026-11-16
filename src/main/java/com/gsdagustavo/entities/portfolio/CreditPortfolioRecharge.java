package com.gsdagustavo.entities.portfolio;

import java.time.LocalDateTime;

public class CreditPortfolioRecharge {
    private LocalDateTime rechargedAt;
    private double value;

    public CreditPortfolioRecharge(LocalDateTime rechargedAt, double value) {
        this.rechargedAt = rechargedAt;
        this.value = value;
    }

    public LocalDateTime getRechargedAt() {
        return rechargedAt;
    }

    public double getValue() {
        return value;
    }
}
