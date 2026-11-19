package com.gsdagustavo.entities.statement;

import java.time.LocalDateTime;

public interface StatementEntry {
    LocalDateTime getStatementDateTime();

    double getStatementValue();

    String getStatementDescription();
}
