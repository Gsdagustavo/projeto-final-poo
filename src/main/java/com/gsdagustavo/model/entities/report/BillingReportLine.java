package com.gsdagustavo.model.entities.report;

import com.gsdagustavo.model.entities.util.MoneyUtils;

public class BillingReportLine {
    private final String vehicleTypeName;
    private int travelCount;
    private double totalAmount;

    public BillingReportLine(String vehicleTypeName) {
        this.vehicleTypeName = vehicleTypeName;
        this.travelCount = 0;
        this.totalAmount = 0;
    }

    public void addTravel(double price) {
        this.travelCount++;
        this.totalAmount = MoneyUtils.round(totalAmount + price);
    }

    public String getVehicleTypeName() {
        return vehicleTypeName;
    }

    public int getTravelCount() {
        return travelCount;
    }

    public double getTotalAmount() {
        return totalAmount;
    }
}
