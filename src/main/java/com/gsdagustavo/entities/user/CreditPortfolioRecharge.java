package com.gsdagustavo.entities.user;

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

    public void setRechargedAt(LocalDateTime rechargedAt) {
        this.rechargedAt = rechargedAt;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
