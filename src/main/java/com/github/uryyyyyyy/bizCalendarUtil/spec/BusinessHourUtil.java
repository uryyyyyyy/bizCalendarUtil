package com.github.uryyyyyyy.bizCalendarUtil.spec;

import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.time.ZonedDateTime;
import java.util.Set;

import com.github.uryyyyyyy.bizCalendarUtil.util.ZonedDateTimeRange;

public interface BusinessHourUtil {

    ZonedDateTime calcNearestTimeAfter(Set<ZonedDateTimeRange> zonedDateTimeRanges, ZonedDateTime target);

    ZonedDateTime calcNearestTimeBefore(Set<ZonedDateTimeRange> zonedDateTimeRanges, ZonedDateTime target);

    Set<LocalDate> calcLastTimesOfMonth(Set<ZonedDateTimeRange> zonedDateTimeRanges, ZonedDateTime from, ZonedDateTime to, Set<Year> years, Set<Month> month);

    Set<LocalDate> calcFirstTimesOfMonth(Set<ZonedDateTimeRange> zonedDateTimeRanges, ZonedDateTime from, ZonedDateTime to, Set<Year> years, Set<Month> month);

    Set<ZonedDateTime> createAllDatesByCronPattern(Set<LocalDate> holidays, ZonedDateTime from, ZonedDateTime to, String cronPattern);

}
