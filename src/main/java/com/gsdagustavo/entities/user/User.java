package com.gsdagustavo.entities.user;

import com.gsdagustavo.entities.portfolio.CreditPortfolio;
import com.gsdagustavo.entities.subscription.Subscription;

public class User {
    private String name;
    private String email;
    private Subscription subscription;
    private CreditPortfolio creditPortfolio;

    public User(String name, String email, Subscription subscription, CreditPortfolio creditPortfolio) {
        this.name = name;
        this.email = email;
        this.subscription = subscription;
        this.creditPortfolio = creditPortfolio;
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
}
