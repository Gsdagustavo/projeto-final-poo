package com.gsdagustavo.model.entities.report;

import com.gsdagustavo.model.entities.exception.InvalidDateException;
import com.gsdagustavo.model.entities.travel.Travel;
import com.gsdagustavo.model.entities.util.MoneyUtils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BillingReport {
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final List<BillingReportLine> lines;
    private int totalTravelCount;
    private double totalAmount;

    public BillingReport(LocalDate startDate, LocalDate endDate) {
        if (startDate == null || endDate == null) {
            throw new InvalidDateException("As datas inicial e final são obrigatórias.");
        }
        if (endDate.isBefore(startDate)) {
            throw new InvalidDateException("A data final não pode ser anterior à data inicial.");
        }
        this.startDate = startDate;
        this.endDate = endDate;
        this.lines = new ArrayList<>();
        this.totalTravelCount = 0;
        this.totalAmount = 0;
    }

    public boolean isInPeriod(Travel travel) {
        if (travel == null || !travel.isFinished()) {
            return false;
        }
        LocalDate travelDate = travel.getEndDate().toLocalDate();
        return !travelDate.isBefore(startDate) && !travelDate.isAfter(endDate);
    }

    public void addTravel(Travel travel) throws InvalidDateException {
        if (!isInPeriod(travel)) {
            throw new InvalidDateException("A viagem não foi encerrada dentro do período do relatório.");
        }

        String typeName = travel.getVehicle().getTypeName();
        BillingReportLine line = null;
        for (final var it : lines) {
            if (it.getVehicleTypeName().equals(typeName)) {
                line = it;
            }
        }
        if (line == null) {
            line = new BillingReportLine(typeName);
            lines.add(line);
        }

        line.addTravel(travel.getPrice());
        this.totalTravelCount++;
        this.totalAmount = MoneyUtils.round(totalAmount + travel.getPrice());
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public List<BillingReportLine> getLines() {
        return lines;
    }

    public int getTotalTravelCount() {
        return totalTravelCount;
    }

    public double getTotalAmount() {
        return totalAmount;
    }
}
