package com.example.util.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

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

    public static String toString(Date d){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss E");
        return formatter.format(d);
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

    public static ZonedDateTime toZonedDateTime(Date d) {
        return d.toInstant().atZone(ZoneId.systemDefault());
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
