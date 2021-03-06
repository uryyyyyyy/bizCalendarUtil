package com.github.uryyyyyyy.bizCalendarUtil.impl;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.quartz.CronExpression;

import com.github.uryyyyyyy.bizCalendarUtil.util.JavaTimeConverter;
import com.github.uryyyyyyy.bizCalendarUtil.util.ZonedDateTimeRange;

class ImplUtil {

    static List<LocalDate> calcCron(String cronPattern, LocalDate minDay, LocalDate maxDay) {
        LocalDate acc = minDay;
        List<LocalDate> cronList = new LinkedList<>();
        while(acc.isBefore(maxDay)){
            LocalDate newOne = getNextValidTimeAfter(cronPattern, acc);
            cronList.add(newOne);
            acc = newOne;
        }
        //last one is out of range... but it may used by business day
        return trim(cronList, minDay, maxDay);
    }

    static List<ZonedDateTime> calcCron(String cronPattern, ZonedDateTime minDay, ZonedDateTime maxDay, ZoneId zoneId) {
        ZonedDateTime acc = minDay.withZoneSameInstant(zoneId);
        List<ZonedDateTime> cronList = new LinkedList<>();
        while(acc.isBefore(maxDay)){
            ZonedDateTime newOne = getNextValidTimeAfter(cronPattern, acc);
            cronList.add(newOne);
            acc = newOne;
        }
        //last one is out of range... but it may used by business day
        return trim(cronList, minDay, maxDay);
    }

    static List<LocalDate> trim(List<LocalDate> holidayList, LocalDate minDay, LocalDate maxDay) {
        return holidayList.stream()
                .filter(v -> !minDay.isAfter(v))
                .filter(v -> !maxDay.isBefore(v))
                .distinct().collect(Collectors.toList());
    }

    static List<ZonedDateTime> trim(List<ZonedDateTime> targetList, ZonedDateTime minDay, ZonedDateTime maxDay) {
        return targetList.stream()
                .filter(v -> !minDay.isAfter(v))
                .filter(v -> !maxDay.isBefore(v))
                .distinct().collect(Collectors.toList());
    }

    static List<LocalDate> calcHolidayAfter(List<LocalDate> targetList, Set<LocalDate> holidays) {
        return targetList.stream().map(v -> recursiveAfter(v, holidays)).collect(Collectors.toList());
    }

    static List<LocalDate> calcHolidayBefore(List<LocalDate> targetList, Set<LocalDate> holidays) {
        return targetList.stream().map(v -> recursiveBefore(v, holidays)).collect(Collectors.toList());
    }

    static List<LocalDate> calcHolidayIgnore(List<LocalDate> targetList, Set<LocalDate> holidays) {
        return targetList.stream().filter(v -> !holidays.contains(v)).collect(Collectors.toList());
    }

    static LocalDate recursiveBefore(LocalDate v, Set<LocalDate> holidays) {
        LocalDate acc = v;
        while(holidays.contains(acc)){//for performance
            acc = acc.minusDays(1);
        }
        return acc;
    }

    static List<ZonedDateTimeRange> calcBusinessTimeBefore(List<ZonedDateTime> targetList, Set<ZonedDateTimeRange> businessDays) {
        return targetList.stream().map(v -> recursiveBefore_(v, businessDays)).collect(Collectors.toList());
    }

    static List<ZonedDateTimeRange> calcBusinessTimeAfter(List<ZonedDateTime> targetList, Set<ZonedDateTimeRange> businessDays) {
        return targetList.stream().map(v -> recursiveAfter_(v, businessDays)).collect(Collectors.toList());
    }

    static ZonedDateTimeRange recursiveBefore_(ZonedDateTime target, Set<ZonedDateTimeRange> zonedDateTimeRanges) {
        Optional<ZonedDateTimeRange> optRange = zonedDateTimeRanges.stream()
                .filter(v -> v.start.isBefore(target))
                .sorted((l, r) -> l.end.isBefore(r.end) ? 1 : -1)
                .findFirst();

        if(!optRange.isPresent()){
            throw new IllegalArgumentException("target:(" + target +") is not in businessRange");
        }
        return optRange.get();
    }

    static LocalDate recursiveAfter(LocalDate v, Set<LocalDate> holidays) {
        LocalDate acc = v;
        while(holidays.contains(acc)){//for performance
            acc = acc.plusDays(1);
        }
        return acc;
    }

    static ZonedDateTimeRange recursiveAfter_(ZonedDateTime target, Set<ZonedDateTimeRange> zonedDateTimeRanges) {
        Optional<ZonedDateTimeRange> optRange = zonedDateTimeRanges.stream()
                .filter(v -> v.end.isAfter(target))
                .sorted((l, r) -> l.start.isAfter(r.start) ? 1 : -1)
                .findFirst();

        if(!optRange.isPresent()){
            throw new IllegalArgumentException("target:(" + target +") is not in businessRange");
        }
        return optRange.get();
    }

    static int quotient12(int m) {
        if (m > 12) return (m-12);
        return m;
    }

    static LocalDate minDay(Set<LocalDate> holidays) {
        return holidays.stream().min((l, r) -> l.isAfter(r) ? 1 : -1).get();
    }

    static LocalDate maxDay(Set<LocalDate> holidays) {
        return holidays.stream().max((l, r) -> l.isAfter(r) ? 1 : -1).get();
    }

    static ZonedDateTime minDay_(Set<ZonedDateTimeRange> range) {
        return range.stream().map(v -> v.start).min((l, r) -> l.isAfter(r) ? 1 : -1).get();
    }

    static ZonedDateTime maxDay_(Set<ZonedDateTimeRange> range) {
        return range.stream().map(v -> v.end).max((l, r) -> l.isAfter(r) ? 1 : -1).get();
    }

    static ZonedDateTime getNextValidTimeAfter(String cron, ZonedDateTime target) {
        try{
            CronExpression cronExpression = new CronExpression(cron);
            Date n = cronExpression.getNextValidTimeAfter(JavaTimeConverter.toDate(target));
            return JavaTimeConverter.toZonedDateTime(n, target.getZone());
        }catch (ParseException e){
            throw new RuntimeException("cron pattern error: " + cron,e);
        }
    }

    static LocalDate getNextValidTimeAfter(String cron, LocalDate target) {
        try{
            CronExpression cronExpression = new CronExpression(cron);
            Date n = cronExpression.getNextValidTimeAfter(JavaTimeConverter.toDate(target));
            return JavaTimeConverter.toLocalDate(n);
        }catch (ParseException e){
            throw new RuntimeException("cron pattern error: " + cron,e);
        }
    }
}
