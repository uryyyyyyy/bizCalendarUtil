package com.github.uryyyyyyy.bizCalendarUtil.spec;

import java.time.Month;
import java.time.Year;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Set;

import com.github.uryyyyyyy.bizCalendarUtil.util.ZonedDateTimeRange;

public interface BusinessHourUtil {

    ZonedDateTimeRange calcNearestTimeAfter(Set<ZonedDateTimeRange> zonedDateTimeRanges, ZonedDateTime target);

    ZonedDateTimeRange calcNearestTimeBefore(Set<ZonedDateTimeRange> zonedDateTimeRanges, ZonedDateTime target);

    List<ZonedDateTimeRange> calcLastTimesOfMonth(Set<ZonedDateTimeRange> zonedDateTimeRanges, Set<Year> years, Set<Month> month, ZoneId zoneId);

    List<ZonedDateTimeRange> calcFirstTimesOfMonth(Set<ZonedDateTimeRange> zonedDateTimeRanges, Set<Year> years, Set<Month> month, ZoneId zoneId);

    List<ZonedDateTimeRange> calcNearestDatesByCronPatternBefore(Set<ZonedDateTimeRange> zonedDateTimeRanges, String cronPattern, ZoneId zoneId);

    List<ZonedDateTimeRange> calcNearestDatesByCronPatternAfter(Set<ZonedDateTimeRange> zonedDateTimeRanges, String cronPattern, ZoneId zoneId);

}
