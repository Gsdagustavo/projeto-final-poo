package com.gsdagustavo.model.entities.statement;

import java.time.LocalDateTime;

public interface StatementEntry {
    LocalDateTime getStatementDateTime();

    double getStatementValue();

    String getStatementDescription();
}
