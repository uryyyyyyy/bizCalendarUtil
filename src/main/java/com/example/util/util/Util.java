package com.example.util.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
}
