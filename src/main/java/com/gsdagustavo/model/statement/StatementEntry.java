package com.gsdagustavo.model.statement;

import java.time.LocalDateTime;

public interface StatementEntry {
    LocalDateTime getStatementDateTime();

    double getStatementValue();

    String getStatementDescription();
}
