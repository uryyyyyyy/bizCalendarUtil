package com.github.uryyyyyyy.bizCalendarUtil.util;

import java.time.ZonedDateTime;

import junit.framework.TestCase;

public class UtilTest extends TestCase {

    public void testToZonedDateTime() throws Exception {
        ZonedDateTime d = JavaTimeConverter.toZonedDateTime("2015-01-01T00:00:00", "Europe/Paris");
        JavaTimeConverter.print(JavaTimeConverter.toString(d));

        ZonedDateTime d1 = JavaTimeConverter.toZonedDateTime("2015-01-01T00:00:00", "Japan");
        JavaTimeConverter.print(JavaTimeConverter.toString(d1));
        JavaTimeConverter.print(d1.toLocalDate());
    }
}