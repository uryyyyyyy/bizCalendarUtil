package com.github.uryyyyyyy.bizCalendarUtil.util;

import java.time.ZonedDateTime;

import junit.framework.TestCase;

public class UtilTest extends TestCase {

    public void testToZonedDateTime() throws Exception {
        ZonedDateTime d = Util.toZonedDateTime("2015-01-01T00:00:00", "Europe/Paris");
        Util.print(Util.toString(d));

        ZonedDateTime d1 = Util.toZonedDateTime("2015-01-01T00:00:00", "Japan");
        Util.print(Util.toString(d1));
        Util.print(d1.toLocalDate());
    }
}