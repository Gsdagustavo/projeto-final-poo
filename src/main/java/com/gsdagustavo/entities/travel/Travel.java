package com.gsdagustavo.entities.travel;

import java.time.LocalDateTime;
import java.util.List;

public class Travel {
    private final LocalDateTime startDate;
    private LocalDateTime endDate;
    private List<VehicleWithdrawalRecord> withdrawalRecords;

    public Travel(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public void finishTravel() {
        this.endDate = LocalDateTime.now();
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public List<VehicleWithdrawalRecord> getWithdrawalRecords() {
        return withdrawalRecords;
    }
}
