package com.github.uryyyyyyy.bizCalendarUtil.impl;


import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.time.ZonedDateTime;
import java.util.Set;

import com.github.uryyyyyyy.bizCalendarUtil.spec.BusinessHourUtil;
import com.github.uryyyyyyy.bizCalendarUtil.util.ZonedDateTimeRange;

public class BusinessHourUtilImpl implements BusinessHourUtil {

    @Override
    public ZonedDateTime calcNearestTimeAfter(Set<ZonedDateTimeRange> zonedDateTimeRanges, ZonedDateTime target) {
        return ImplUtil.recursiveAfter_(target, zonedDateTimeRanges);
    }

    @Override
    public ZonedDateTime calcNearestTimeBefore(Set<ZonedDateTimeRange> zonedDateTimeRanges, ZonedDateTime target) {
        return null;
    }

    @Override
    public Set<LocalDate> calcLastTimesOfMonth(Set<ZonedDateTimeRange> zonedDateTimeRanges, ZonedDateTime from, ZonedDateTime to, Set<Year> years, Set<Month> month) {
        return null;
    }

    @Override
    public Set<LocalDate> calcFirstTimesOfMonth(Set<ZonedDateTimeRange> zonedDateTimeRanges, ZonedDateTime from, ZonedDateTime to, Set<Year> years, Set<Month> month) {
        return null;
    }

    @Override
    public Set<ZonedDateTime> createAllDatesByCronPattern(Set<LocalDate> holidays, ZonedDateTime from, ZonedDateTime to, String cronPattern) {
        return null;
    }
}
