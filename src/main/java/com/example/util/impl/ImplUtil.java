package com.example.util.impl;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.example.util.util.HolidayPattern;
import com.example.util.util.Util;

class ImplUtil {

    static List<LocalDate> calcCron(String cronPattern, LocalDate minDay, LocalDate maxDay) {
        LocalDate acc = minDay;
        List<LocalDate> cronList = new LinkedList<>();
        while(acc.isBefore(maxDay)){
            LocalDate newOne = Util.getNextValidTimeAfter(cronPattern, acc);
            cronList.add(newOne);
            acc = newOne;
        }
        //last one is out of range... but it may used by business day
        return cronList;
    }

    static List<LocalDate> trim(List<LocalDate> holidaiedList, LocalDate minDay, LocalDate maxDay) {
        return holidaiedList.stream()
                .filter(v -> minDay.isBefore(v))
                .filter(v -> maxDay.isAfter(v))
                .distinct().collect(Collectors.toList());
    }

    static List<LocalDate> calcHoliday(List<LocalDate> targetList, Set<LocalDate> holidays, HolidayPattern pattern) {
        if(pattern == HolidayPattern.AFTER){
            return targetList.stream().map(v -> recursiveAfter(v, holidays)).collect(Collectors.toList());
        }else if(pattern == HolidayPattern.BEFORE){
            return targetList.stream().map(v -> recursiveBefore(v, holidays)).collect(Collectors.toList());
        }else if(pattern == HolidayPattern.IGNORE){
            return targetList.stream().filter(v -> !holidays.contains(v)).collect(Collectors.toList());
        }else{
            throw new RuntimeException("illegal HolidayPattern: " + pattern);
        }
    }

    private static LocalDate recursiveBefore(LocalDate v, Set<LocalDate> holidays) {
        LocalDate acc = v;
        while(holidays.contains(acc)){//for performance
            acc = acc.minusDays(1);
        }
        return acc;
    }

    private static LocalDate recursiveAfter(LocalDate v, Set<LocalDate> holidays) {
        LocalDate acc = v;
        while(holidays.contains(acc)){//for performance
            acc = acc.plusDays(1);
        }
        return acc;
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
}