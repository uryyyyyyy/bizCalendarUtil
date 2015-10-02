package com.github.uryyyyyyy.bizCalendarUtil.spec;

import java.time.Month;
import java.time.Year;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Set;

import com.github.uryyyyyyy.bizCalendarUtil.util.ZonedDateTimeRange;

public interface BusinessHourUtil {

    ZonedDateTime calcNearestTimeAfter(Set<ZonedDateTimeRange> zonedDateTimeRanges, ZonedDateTime target);

    ZonedDateTime calcNearestTimeBefore(Set<ZonedDateTimeRange> zonedDateTimeRanges, ZonedDateTime target);

    List<ZonedDateTime> calcLastTimesOfMonth(Set<ZonedDateTimeRange> zonedDateTimeRanges, Set<Year> years, Set<Month> month, ZoneId zoneId);

    List<ZonedDateTime> calcFirstTimesOfMonth(Set<ZonedDateTimeRange> zonedDateTimeRanges, Set<Year> years, Set<Month> month, ZoneId zoneId);

    List<ZonedDateTime> calcNearestDatesByCronPatternBefore(Set<ZonedDateTimeRange> zonedDateTimeRanges, String cronPattern, ZoneId zoneId);

    List<ZonedDateTime> calcNearestDatesByCronPatternAfter(Set<ZonedDateTimeRange> zonedDateTimeRanges, String cronPattern, ZoneId zoneId);

}
