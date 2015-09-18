package com.example.util.impl;


import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import com.example.util.spec.BusinessDayUtil;
import com.example.util.util.HolidayPattern;

import static com.example.util.impl.ImplUtil.calcCron;
import static com.example.util.impl.ImplUtil.calcHoliday;
import static com.example.util.impl.ImplUtil.minDay;
import static com.example.util.impl.ImplUtil.maxDay;
import static com.example.util.impl.ImplUtil.quotient12;
import static com.example.util.impl.ImplUtil.trim;

public class BusinessDayUtilImpl implements BusinessDayUtil {

    @Override
    public List<LocalDate> calcBusinessDaysOfEndOfQuarter(Set<LocalDate> holidays, Month beginningOfPeriod) {
        if (holidays.isEmpty()) return new LinkedList<>();
        LocalDate minDay = minDay(holidays);
        LocalDate maxDay = maxDay(holidays);

        int m = beginningOfPeriod.getValue()-1;
        String targetMonths = quotient12(m) + "," + quotient12(m+3) + "," + quotient12(m+6) + "," + quotient12(m+9);
        String cronPattern = "0 0 0 L " + targetMonths + " ?";

        List<LocalDate> cronDayList = calcCron(cronPattern, minDay, maxDay);
        List<LocalDate> businessDayList = calcHoliday(cronDayList, holidays, HolidayPattern.BEFORE);
        return trim(businessDayList, minDay, maxDay);
    }

    @Override
    public List<LocalDate> calcBusinessDaysOfEndOfMonth(Set<LocalDate> holidays) {
        if (holidays.isEmpty()) return new LinkedList<>();
        LocalDate minDay = minDay(holidays);
        LocalDate maxDay = maxDay(holidays);

        String cronPattern = "0 0 0 L * ?";

        List<LocalDate> cronDayList = calcCron(cronPattern, minDay, maxDay);
        List<LocalDate> businessDayList = calcHoliday(cronDayList, holidays, HolidayPattern.BEFORE);
        return trim(businessDayList, minDay, maxDay);
    }

    @Override
    public LocalDate calcBusinessDayBeforeXDay(Set<LocalDate> holidays, LocalDate targetDay, int x) {
        return null;
    }

    @Override
    public LocalDate calcBusinessDayAfterXDay(Set<LocalDate> holidays, LocalDate targetDate, int x) {
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
    public LocalDate calcFirstDateOfMonth(Set<LocalDate> holidays, Set<Year> years, Set<Month> month) {
        return null;
    }

    @Override
    public LocalDate calcLastDateOfMonth(Set<LocalDate> holidays, Set<Year> years, Set<Month> month) {
        return null;
    }

    @Override
    public List<LocalDate> calcNearestDatesByCronPatternAfter(Set<LocalDate> holidays, String cronPattern) {
        return null;
    }

    @Override
    public List<LocalDate> calcNearestDatesByCronPatternBefore(Set<LocalDate> holidays, String cronPattern) {
        return null;
    }
}
