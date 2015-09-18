package com.example.util.impl;


import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.Year;
import java.util.Set;

import com.example.util.spec.BusinessDayUtil;

public class BusinessDayUtilImpl implements BusinessDayUtil {
    @Override
    public Set<LocalDate> calcBusinessDaysOfEndOfQuarter(Set<LocalDate> holidays, Month beginningOfPeriod) {
        return null;
    }

    @Override
    public Set<LocalDate> calcBusinessDaysOfEndOfMonth(Set<LocalDate> holidays) {
        return null;
    }

    @Override
    public LocalDate calcBusinessDayBeforeXDay(Set<LocalDate> holidays, LocalDate targetDay, int x) {
        return null;
    }

    @Override
    public LocalDate calcDateAfter(Set<LocalDate> holidays, LocalDate targetDate, int after) {
        return null;
    }

    @Override
    public LocalDate calcDateBefore(Set<LocalDate> holidays, LocalDate targetDate, int before) {
        return null;
    }

    @Override
    public LocalDate calcNearestDateBefore(Set<LocalDate> holidays, LocalDate targetDate) {
        return null;
    }

    @Override
    public LocalDate calcNearestDateAfter(Set<LocalDate> holidays, LocalDate targetDate) {
        return null;
    }

    @Override
    public LocalDate calcFirstDateOfMonth(Set<LocalDate> holidays, Year year, Month month) {
        return null;
    }

    @Override
    public LocalDate calcLastDateOfMonth(Set<LocalDate> holidays, Year year, Month month) {
        return null;
    }

    @Override
    public Set<LocalDate> calcNearestDatesByCronPatternAfter(Set<LocalDate> holidays, LocalDate from, LocalDate to, Set<Year> years, Set<Month> month, Set<Short> day, Set<DayOfWeek> weeks) {
        return null;
    }

    @Override
    public Set<LocalDate> calcNearestDatesByCronPatternBefore(Set<LocalDate> holidays, LocalDate from, LocalDate to, Set<Year> years, Set<Month> month, Set<Short> day, Set<DayOfWeek> weeks) {
        return null;
    }

    @Override
    public Set<LocalDateTime> createAllDatesByCronPattern(Set<LocalDate> holidays, LocalDate from, LocalDate to, String cronPattern) {
        return null;
    }

    @Override
    public Set<LocalDate> calcLastDatesOfMonth(Set<LocalDate> holidays, LocalDate from, LocalDate to, Set<Year> years, Set<Month> month) {
        return null;
    }

    @Override
    public Set<LocalDate> calcFirstDatesOfMonth(Set<LocalDate> holidays, LocalDate from, LocalDate to, Set<Year> years, Set<Month> month) {
        return null;
    }
}
