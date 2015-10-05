package com.github.uryyyyyyy.bizCalendarUtil.impl;


import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.time.Year;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.github.uryyyyyyy.bizCalendarUtil.spec.BusinessHourUtil;
import com.github.uryyyyyyy.bizCalendarUtil.util.ZonedDateTimeRange;

public class BusinessHourUtilImpl implements BusinessHourUtil {

    @Override
    public ZonedDateTimeRange calcNearestTimeAfter(Set<ZonedDateTimeRange> zonedDateTimeRanges, ZonedDateTime target) {
        return ImplUtil.recursiveAfter_(target, zonedDateTimeRanges);
    }

    @Override
    public ZonedDateTimeRange calcNearestTimeBefore(Set<ZonedDateTimeRange> zonedDateTimeRanges, ZonedDateTime target) {
        return ImplUtil.recursiveBefore_(target, zonedDateTimeRanges);
    }

    @Override
    public List<ZonedDateTimeRange> calcLastTimesOfMonth(Set<ZonedDateTimeRange> zonedDateTimeRanges, Set<Year> years, Set<Month> month, ZoneId zoneId) {
        List<ZonedDateTime> targetList = years.stream()
                .flatMap(y -> month.stream().map(m -> LocalDate.of(y.getValue(), m.getValue()+1, 1)))
                .sorted((l, r) -> l.isAfter(r) ? 1 : -1)
                .map(v -> ZonedDateTime.of(v, LocalTime.of(0,0), zoneId))
                .collect(Collectors.toList());
        return ImplUtil.calcBusinessTimeBefore(targetList, zonedDateTimeRanges);
    }

    @Override
    public List<ZonedDateTimeRange> calcFirstTimesOfMonth(Set<ZonedDateTimeRange> zonedDateTimeRanges, Set<Year> years, Set<Month> month, ZoneId zoneId) {
        List<ZonedDateTime> targetList = years.stream()
                .flatMap(y -> month.stream().map(m -> LocalDate.of(y.getValue(), m.getValue(), 1)))
                .sorted((l, r) -> l.isAfter(r) ? 1 : -1)
                .map(v -> ZonedDateTime.of(v, LocalTime.of(0, 0), zoneId))
                .collect(Collectors.toList());
        return ImplUtil.calcBusinessTimeAfter(targetList, zonedDateTimeRanges);
    }

    @Override
    public List<ZonedDateTimeRange> calcNearestDatesByCronPatternBefore(Set<ZonedDateTimeRange> zonedDateTimeRanges, String cronPattern, ZoneId zoneId) {
        if (zonedDateTimeRanges.isEmpty()) return new LinkedList<>();
        ZonedDateTime minDay = ImplUtil.minDay_(zonedDateTimeRanges);
        ZonedDateTime maxDay = ImplUtil.maxDay_(zonedDateTimeRanges);

        List<ZonedDateTime> cronDayList = ImplUtil.calcCron(cronPattern, minDay, maxDay, zoneId);
        return ImplUtil.calcBusinessTimeBefore(cronDayList, zonedDateTimeRanges);
    }

    @Override
    public List<ZonedDateTimeRange> calcNearestDatesByCronPatternAfter(Set<ZonedDateTimeRange> zonedDateTimeRanges, String cronPattern, ZoneId zoneId) {
        if (zonedDateTimeRanges.isEmpty()) return new LinkedList<>();
        ZonedDateTime minDay = ImplUtil.minDay_(zonedDateTimeRanges);
        ZonedDateTime maxDay = ImplUtil.maxDay_(zonedDateTimeRanges);

        List<ZonedDateTime> cronDayList = ImplUtil.calcCron(cronPattern, minDay, maxDay, zoneId);
        return ImplUtil.calcBusinessTimeAfter(cronDayList, zonedDateTimeRanges);
    }
}
