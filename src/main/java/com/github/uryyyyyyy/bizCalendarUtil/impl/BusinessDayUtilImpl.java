package com.github.uryyyyyyy.bizCalendarUtil.impl;


import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.github.uryyyyyyy.bizCalendarUtil.spec.BusinessDayUtil;

public class BusinessDayUtilImpl implements BusinessDayUtil {

    @Override
    public List<LocalDate> calcBusinessDaysOfEndOfQuarter(Set<LocalDate> holidays, Month beginningOfPeriod) {
        if (holidays.isEmpty()) return new LinkedList<>();
        LocalDate minDay = ImplUtil.minDay(holidays);
        LocalDate maxDay = ImplUtil.maxDay(holidays);

        int m = beginningOfPeriod.getValue()-1;
        String targetMonths = ImplUtil.quotient12(m) + "," + ImplUtil.quotient12(m + 3) + "," + ImplUtil.quotient12(m + 6) + "," + ImplUtil.quotient12(m + 9);
        String cronPattern = "0 0 0 L " + targetMonths + " ?";

        List<LocalDate> cronDayList = ImplUtil.calcCron(cronPattern, minDay, maxDay);
        List<LocalDate> businessDayList = ImplUtil.calcHolidayBefore(cronDayList, holidays);
        return ImplUtil.trim(businessDayList, minDay, maxDay);
    }

    @Override
    public List<LocalDate> calcBusinessDaysOfEndOfMonth(Set<LocalDate> holidays) {
        if (holidays.isEmpty()) return new LinkedList<>();
        LocalDate minDay = ImplUtil.minDay(holidays);
        LocalDate maxDay = ImplUtil.maxDay(holidays);

        String cronPattern = "0 0 0 L * ?";

        List<LocalDate> cronDayList = ImplUtil.calcCron(cronPattern, minDay, maxDay);
        List<LocalDate> businessDayList = ImplUtil.calcHolidayBefore(cronDayList, holidays);
        return ImplUtil.trim(businessDayList, minDay, maxDay);
    }

    @Override
    public LocalDate calcBusinessDayBeforeXDay(Set<LocalDate> holidays, LocalDate targetDate, int x) {
        if(x < 1) throw new IllegalArgumentException("x must be plus");
        LocalDate acc = targetDate;
        for(int i=0; i<x; i++){
            acc = ImplUtil.recursiveBefore(acc.minusDays(1), holidays);
        }
        return acc;
    }

    @Override
    public LocalDate calcBusinessDayAfterXDay(Set<LocalDate> holidays, LocalDate targetDate, int x) {
        if(x < 1) throw new IllegalArgumentException("x must be plus");
        LocalDate acc = targetDate;
        for(int i=0; i<x; i++){
            acc = ImplUtil.recursiveAfter(acc.plusDays(1), holidays);
        }
        return acc;
    }

    @Override
    public LocalDate calcNearestDateBefore(Set<LocalDate> holidays, LocalDate targetDate) {
        return ImplUtil.recursiveBefore(targetDate, holidays);
    }

    @Override
    public LocalDate calcNearestDateAfter(Set<LocalDate> holidays, LocalDate target) {
        return ImplUtil.recursiveAfter(target, holidays);
    }

    @Override
    public List<LocalDate> calcFirstDateOfMonth(Set<LocalDate> holidays, Set<Year> years, Set<Month> month) {
        List<LocalDate> targetList = years.stream()
                .flatMap(y -> month.stream().map(m -> LocalDate.of(y.getValue(), m.getValue(), 1)))
                .sorted((l,r) -> l.isAfter(r) ? 1 : -1)
                .collect(Collectors.toList());
        return ImplUtil.calcHolidayAfter(targetList, holidays);
    }

    @Override
    public List<LocalDate> calcLastDateOfMonth(Set<LocalDate> holidays, Set<Year> years, Set<Month> month) {
        List<LocalDate> targetList = years.stream()
                .flatMap(y -> month.stream().map(m -> LocalDate.of(y.getValue(), m.getValue()+1, 1).minusDays(1)))
                .sorted((l,r) -> l.isAfter(r) ? 1 : -1)
                .collect(Collectors.toList());
        return ImplUtil.calcHolidayBefore(targetList, holidays);
    }

    @Override
    public List<LocalDate> calcNearestDatesByCronPatternAfter(Set<LocalDate> holidays, String cronPattern) {
        if (holidays.isEmpty()) return new LinkedList<>();
        LocalDate minDay = ImplUtil.minDay(holidays);
        LocalDate maxDay = ImplUtil.maxDay(holidays);

        List<LocalDate> cronDayList = ImplUtil.calcCron(cronPattern, minDay, maxDay);
        return ImplUtil.calcHolidayAfter(cronDayList, holidays);
    }

    @Override
    public List<LocalDate> calcNearestDatesByCronPatternBefore(Set<LocalDate> holidays, String cronPattern) {
        if (holidays.isEmpty()) return new LinkedList<>();
        LocalDate minDay = ImplUtil.minDay(holidays);
        LocalDate maxDay = ImplUtil.maxDay(holidays);

        List<LocalDate> cronDayList = ImplUtil.calcCron(cronPattern, minDay, maxDay);
        return ImplUtil.calcHolidayBefore(cronDayList, holidays);
    }
}
