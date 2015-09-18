package com.example.util.util;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Locale;

public class BusinessHour {
    public final LocalDate date;
    public final LocalTime beginTime;
    public final LocalTime endTime;
    public final Locale locale;

    public BusinessHour(LocalDate date, LocalTime beginTime, LocalTime endTime, Locale locale) {
        this.date = date;
        this.beginTime = beginTime;
        this.endTime = endTime;
        this.locale = locale;
    }
}
