package com.example.util.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Set;

import org.quartz.CronExpression;

public class Util {

    public static Date createDate(String s){
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

    public static LocalTime toLocalTime(String str) {
        return LocalTime.parse(str, DateTimeFormatter.ISO_LOCAL_TIME);
    }

    public static ZonedDateTime toZonedDateTime(Date d) {
        return d.toInstant().atZone(ZoneId.systemDefault());
    }

    public static ZonedDateTime toZonedDateTime(String str, String str2) {
        LocalDateTime l = LocalDateTime.parse(str, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        ZoneId z = ZoneId.of(str2);
        return ZonedDateTime.of(l, z);
    }

    public static ZonedDateTime getNextValidTimeAfter(String cron, ZonedDateTime target) {
        try{
            CronExpression cronExpression = new CronExpression(cron);
            Date n = cronExpression.getNextValidTimeAfter(Util.toDate(target));
            return Util.toZonedDateTime(n);
        }catch (ParseException e){
            throw new RuntimeException("cron pattern error: " + cron,e);
        }
    }

    public static LocalDate getNextValidTimeAfter(String cron, LocalDate target) {
        try{
            CronExpression cronExpression = new CronExpression(cron);
            Date n = cronExpression.getNextValidTimeAfter(Util.toDate(target));
            return Util.toLocalDate(n);
        }catch (ParseException e){
            throw new RuntimeException("cron pattern error: " + cron,e);
        }
    }
}
