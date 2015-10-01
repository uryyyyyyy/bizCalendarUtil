package com.example.util.impl;

import java.time.ZonedDateTime;

import org.junit.Test;

import com.example.util.spec.BusinessHourUtil;
import com.example.util.util.ZonedDateTimeRange;
import com.example.util.util.Util;
import com.google.common.collect.ImmutableSet;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;


public class BusinessHourUtilImplTest {

    private static final BusinessHourUtil businessHourUtil = new BusinessHourUtilImpl();

    @Test
    public void testCalcNearestTimeAfter() throws Exception {
        ZonedDateTimeRange h1 = new ZonedDateTimeRange(
                Util.toZonedDateTime("2015-01-01T18:00:00", "Japan"),
                Util.toZonedDateTime("2015-01-01T20:00:00", "Japan"));
        ZonedDateTimeRange h2 = new ZonedDateTimeRange(
                Util.toZonedDateTime("2015-01-02T18:00:00", "Japan"),
                Util.toZonedDateTime("2015-01-02T20:00:00", "Japan"));
        ZonedDateTimeRange h3 = new ZonedDateTimeRange(
                Util.toZonedDateTime("2015-01-03T18:00:00", "Japan"),
                Util.toZonedDateTime("2015-01-03T20:00:00", "Japan"));

        ZonedDateTime target = Util.toZonedDateTime("2015-01-01T17:00:00", "Japan");
        ZonedDateTime res = businessHourUtil.calcNearestTimeAfter(ImmutableSet.of(h1, h2, h3), target);
        ZonedDateTime ans = Util.toZonedDateTime("2015-01-01T18:00:00", "Japan");
        assertThat(res, is(ans));

        ZonedDateTime target2 = Util.toZonedDateTime("2015-01-01T19:00:00", "Japan");
        ZonedDateTime res2 = businessHourUtil.calcNearestTimeAfter(ImmutableSet.of(h1, h2, h3), target2);
        ZonedDateTime ans2 = Util.toZonedDateTime("2015-01-01T19:00:00", "Japan");
        assertThat(res2, is(ans2));

        ZonedDateTime target3 = Util.toZonedDateTime("2015-01-02T22:00:00", "Japan");
        ZonedDateTime res3 = businessHourUtil.calcNearestTimeAfter(ImmutableSet.of(h1, h2, h3), target3);
        ZonedDateTime ans3 = Util.toZonedDateTime("2015-01-03T18:00:00", "Japan");
        assertThat(res3, is(ans3));

        ZonedDateTime target4 = Util.toZonedDateTime("2015-01-02T22:00:00", "Europe/London");
        ZonedDateTime res4 = businessHourUtil.calcNearestTimeAfter(ImmutableSet.of(h1, h2, h3), target4);
        ZonedDateTime ans4 = Util.toZonedDateTime("2015-01-03T09:00:00", "Europe/London");
        assertThat(res4, is(ans4));
    }

    @Test
    public void testCalcNearestTimeBefore() throws Exception {

    }

    @Test
    public void testCalcLastTimesOfMonth() throws Exception {

    }

    @Test
    public void testCalcFirstTimesOfMonth() throws Exception {

    }

    @Test
    public void testCreateAllDatesByCronPattern() throws Exception {

    }
}