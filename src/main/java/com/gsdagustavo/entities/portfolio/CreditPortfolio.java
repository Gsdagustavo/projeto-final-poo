package com.gsdagustavo.entities.portfolio;

import java.util.ArrayList;
import java.util.List;

public class CreditPortfolio {
    private double balance;
    private List<CreditPortfolioRecharge> recharges;

    public CreditPortfolio() {
        this.balance = 0;
        this.recharges = new ArrayList<>();
    }
}
