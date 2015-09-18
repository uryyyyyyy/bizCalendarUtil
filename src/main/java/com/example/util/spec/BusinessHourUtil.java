package com.example.util.spec;

import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.time.ZonedDateTime;
import java.util.Set;

import com.example.util.util.BusinessHour;

public interface BusinessHourUtil {

    ZonedDateTime calcNearestTimeAfter(Set<BusinessHour> businessHours, ZonedDateTime target);

    ZonedDateTime calcNearestTimeBefore(Set<BusinessHour> businessHours, ZonedDateTime target);

    Set<LocalDate> calcLastTimesOfMonth(Set<BusinessHour> businessHours, ZonedDateTime from, ZonedDateTime to, Set<Year> years, Set<Month> month);

    Set<LocalDate> calcFirstTimesOfMonth(Set<BusinessHour> businessHours, ZonedDateTime from, ZonedDateTime to, Set<Year> years, Set<Month> month);

    Set<ZonedDateTime> createAllDatesByCronPattern(Set<LocalDate> holidays, ZonedDateTime from, ZonedDateTime to, String cronPattern);

}
