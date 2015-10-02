package com.github.uryyyyyyy.bizCalendarUtil.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Set;

public class JavaTimeConverter {

    public static Date toDate(String s){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        try {
            return formatter.parse(s);
        } catch (ParseException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    public static Set<String> getAvailableZoneId(){
        return ZoneId.getAvailableZoneIds();
    }


    public static String toString(Date d){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss E");
        return formatter.format(d);
    }

    public static String toString(ZonedDateTime d){
        return d.format(DateTimeFormatter.ISO_ZONED_DATE_TIME);
    }

    public static void print(Object s){
        System.out.println(s);
    }

    public static Date toDate(ZonedDateTime z) {
        return Date.from(z.toInstant());
    }

    public static Date toDate(LocalDate localDate) {
        return Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
    }

    public static LocalDate toLocalDate(Date date) {
        return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
    }

    public static LocalDate toLocalDate(String str) {
        return LocalDate.parse(str, DateTimeFormatter.ISO_LOCAL_DATE);
    }

    public static ZonedDateTime toZonedDateTime(Date d) {
        return d.toInstant().atZone(ZoneId.systemDefault());
    }

    public static ZonedDateTime toZonedDateTime(String str, String str2) {
        LocalDateTime l = LocalDateTime.parse(str, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        ZoneId z = ZoneId.of(str2);
        return ZonedDateTime.of(l, z);
    }
}
