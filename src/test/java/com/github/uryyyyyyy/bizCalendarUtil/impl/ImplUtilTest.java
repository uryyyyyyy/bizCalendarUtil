package com.github.uryyyyyyy.bizCalendarUtil.impl;

import java.time.ZonedDateTime;

import org.junit.Test;

import com.github.uryyyyyyy.bizCalendarUtil.util.JavaTimeConverter;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ImplUtilTest {

    @Test
    public void testCron() throws Exception {
        ZonedDateTime now = JavaTimeConverter.toZonedDateTime("2015-01-01T10:00:00", "Japan");
        ZonedDateTime next = ImplUtil.getNextValidTimeAfter("0 0 16 8 * ?", now);
        ZonedDateTime ans = JavaTimeConverter.toZonedDateTime("2015-01-08T16:00:00", "Japan");
        assertThat(next, is(ans));
    }

}