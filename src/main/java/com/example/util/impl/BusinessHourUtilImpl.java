package com.example.util.impl;


import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.time.ZonedDateTime;
import java.util.Set;

import com.example.util.util.BusinessHour;
import com.example.util.spec.BusinessHourUtil;

public class BusinessHourUtilImpl implements BusinessHourUtil {

    @Override
    public ZonedDateTime calcNearestTimeAfter(Set<BusinessHour> businessHours, ZonedDateTime target) {
        return null;
    }

    @Override
    public ZonedDateTime calcNearestTimeBefore(Set<BusinessHour> businessHours, ZonedDateTime target) {
        return null;
    }

    @Override
    public Set<LocalDate> calcLastTimesOfMonth(Set<BusinessHour> businessHours, ZonedDateTime from, ZonedDateTime to, Set<Year> years, Set<Month> month) {
        return null;
    }

    @Override
    public Set<LocalDate> calcFirstTimesOfMonth(Set<BusinessHour> businessHours, ZonedDateTime from, ZonedDateTime to, Set<Year> years, Set<Month> month) {
        return null;
    }

    @Override
    public Set<ZonedDateTime> createAllDatesByCronPattern(Set<LocalDate> holidays, ZonedDateTime from, ZonedDateTime to, String cronPattern) {
        return null;
    }
}
