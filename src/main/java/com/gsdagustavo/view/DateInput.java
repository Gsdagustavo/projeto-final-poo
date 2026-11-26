package com.gsdagustavo.view;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateInput {
    public static final String DATE_TIME_PATTERN = "dd/MM/yyyy HH:mm";
    public static final String DATE_PATTERN = "dd/MM/yyyy";

    private static final DateTimeFormatter DATE_TIME_FORMAT = DateTimeFormatter.ofPattern(DATE_TIME_PATTERN);
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern(DATE_PATTERN);

    public static LocalDateTime parseDateTime(String text) {
        return LocalDateTime.parse(text.trim(), DATE_TIME_FORMAT);
    }

    public static LocalDate parseDate(String text) {
        return LocalDate.parse(text.trim(), DATE_FORMAT);
    }

    public static String format(LocalDateTime dateTime) {
        return dateTime.format(DATE_TIME_FORMAT);
    }

    public static String format(LocalDate date) {
        return date.format(DATE_FORMAT);
    }
}
