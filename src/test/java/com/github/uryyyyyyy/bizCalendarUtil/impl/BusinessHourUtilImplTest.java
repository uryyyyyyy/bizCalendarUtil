package com.github.uryyyyyyy.bizCalendarUtil.impl;

import java.time.Month;
import java.time.Year;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

import org.junit.Test;

import com.github.uryyyyyyy.bizCalendarUtil.spec.BusinessHourUtil;
import com.github.uryyyyyyy.bizCalendarUtil.util.ZonedDateTimeRange;
import com.github.uryyyyyyy.bizCalendarUtil.util.JavaTimeConverter;
import com.google.common.collect.ImmutableSet;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.contains;
import static org.junit.Assert.assertThat;


public class BusinessHourUtilImplTest {

    private static final BusinessHourUtil businessHourUtil = new BusinessHourUtilImpl();

    @Test
    public void testCalcNearestTimeAfter() throws Exception {
        ZonedDateTimeRange h1 = new ZonedDateTimeRange(
                JavaTimeConverter.toZonedDateTime("2015-01-01T18:00:00", "Japan"),
                JavaTimeConverter.toZonedDateTime("2015-01-01T20:00:00", "Japan"));
        ZonedDateTimeRange h2 = new ZonedDateTimeRange(
                JavaTimeConverter.toZonedDateTime("2015-01-02T18:00:00", "Japan"),
                JavaTimeConverter.toZonedDateTime("2015-01-02T20:00:00", "Japan"));
        ZonedDateTimeRange h3 = new ZonedDateTimeRange(
                JavaTimeConverter.toZonedDateTime("2015-01-03T18:00:00", "Japan"),
                JavaTimeConverter.toZonedDateTime("2015-01-03T20:00:00", "Japan"));

        ZonedDateTime target = JavaTimeConverter.toZonedDateTime("2015-01-01T17:00:00", "Japan");
        ZonedDateTime res = businessHourUtil.calcNearestTimeAfter(ImmutableSet.of(h1, h2, h3), target);
        ZonedDateTime ans = JavaTimeConverter.toZonedDateTime("2015-01-01T18:00:00", "Japan");
        assertThat(res, is(ans));

        ZonedDateTime target2 = JavaTimeConverter.toZonedDateTime("2015-01-01T19:00:00", "Japan");
        ZonedDateTime res2 = businessHourUtil.calcNearestTimeAfter(ImmutableSet.of(h1, h2, h3), target2);
        ZonedDateTime ans2 = JavaTimeConverter.toZonedDateTime("2015-01-01T19:00:00", "Japan");
        assertThat(res2, is(ans2));

        ZonedDateTime target3 = JavaTimeConverter.toZonedDateTime("2015-01-02T22:00:00", "Japan");
        ZonedDateTime res3 = businessHourUtil.calcNearestTimeAfter(ImmutableSet.of(h1, h2, h3), target3);
        ZonedDateTime ans3 = JavaTimeConverter.toZonedDateTime("2015-01-03T18:00:00", "Japan");
        assertThat(res3, is(ans3));

        ZonedDateTime target4 = JavaTimeConverter.toZonedDateTime("2015-01-02T22:00:00", "Europe/London");
        ZonedDateTime res4 = businessHourUtil.calcNearestTimeAfter(ImmutableSet.of(h1, h2, h3), target4);
        ZonedDateTime ans4 = JavaTimeConverter.toZonedDateTime("2015-01-03T09:00:00", "Europe/London");
        assertThat(res4, is(ans4));
    }

    @Test
    public void testCalcNearestTimeBefore() throws Exception {
        ZonedDateTimeRange h1 = new ZonedDateTimeRange(
                JavaTimeConverter.toZonedDateTime("2015-01-01T18:00:00", "Japan"),
                JavaTimeConverter.toZonedDateTime("2015-01-01T20:00:00", "Japan"));
        ZonedDateTimeRange h2 = new ZonedDateTimeRange(
                JavaTimeConverter.toZonedDateTime("2015-01-02T18:00:00", "Japan"),
                JavaTimeConverter.toZonedDateTime("2015-01-02T20:00:00", "Japan"));
        ZonedDateTimeRange h3 = new ZonedDateTimeRange(
                JavaTimeConverter.toZonedDateTime("2015-01-03T18:00:00", "Japan"),
                JavaTimeConverter.toZonedDateTime("2015-01-03T20:00:00", "Japan"));

        ZonedDateTime target = JavaTimeConverter.toZonedDateTime("2015-01-03T17:00:00", "Japan");
        ZonedDateTime res = businessHourUtil.calcNearestTimeBefore(ImmutableSet.of(h1, h2, h3), target);
        ZonedDateTime ans = JavaTimeConverter.toZonedDateTime("2015-01-02T20:00:00", "Japan");
        assertThat(res, is(ans));

        ZonedDateTime target2 = JavaTimeConverter.toZonedDateTime("2015-01-01T19:00:00", "Japan");
        ZonedDateTime res2 = businessHourUtil.calcNearestTimeBefore(ImmutableSet.of(h1, h2, h3), target2);
        ZonedDateTime ans2 = JavaTimeConverter.toZonedDateTime("2015-01-01T19:00:00", "Japan");
        assertThat(res2, is(ans2));

        ZonedDateTime target3 = JavaTimeConverter.toZonedDateTime("2015-01-02T22:00:00", "Japan");
        ZonedDateTime res3 = businessHourUtil.calcNearestTimeBefore(ImmutableSet.of(h1, h2, h3), target3);
        ZonedDateTime ans3 = JavaTimeConverter.toZonedDateTime("2015-01-02T20:00:00", "Japan");
        assertThat(res3, is(ans3));

        ZonedDateTime target4 = JavaTimeConverter.toZonedDateTime("2015-01-02T22:00:00", "Europe/London");
        ZonedDateTime res4 = businessHourUtil.calcNearestTimeBefore(ImmutableSet.of(h1, h2, h3), target4);
        ZonedDateTime ans4 = JavaTimeConverter.toZonedDateTime("2015-01-02T11:00:00", "Europe/London");
        assertThat(res4, is(ans4));
    }

    @Test
    public void testCalcLastTimesOfMonth() throws Exception {
        ZonedDateTimeRange h1 = new ZonedDateTimeRange(
                JavaTimeConverter.toZonedDateTime("2015-01-01T18:00:00", "Japan"),
                JavaTimeConverter.toZonedDateTime("2015-01-01T20:00:00", "Japan"));
        ZonedDateTimeRange h2 = new ZonedDateTimeRange(
                JavaTimeConverter.toZonedDateTime("2015-01-02T18:00:00", "Japan"),
                JavaTimeConverter.toZonedDateTime("2015-01-02T20:00:00", "Japan"));
        ZonedDateTimeRange h3 = new ZonedDateTimeRange(
                JavaTimeConverter.toZonedDateTime("2015-01-03T18:00:00", "Japan"),
                JavaTimeConverter.toZonedDateTime("2015-01-03T20:00:00", "Japan"));

        ZonedDateTimeRange h4 = new ZonedDateTimeRange(
                JavaTimeConverter.toZonedDateTime("2015-02-01T18:00:00", "Japan"),
                JavaTimeConverter.toZonedDateTime("2015-02-01T20:00:00", "Japan"));
        ZonedDateTimeRange h5 = new ZonedDateTimeRange(
                JavaTimeConverter.toZonedDateTime("2015-02-22T19:00:00", "Japan"),
                JavaTimeConverter.toZonedDateTime("2015-02-22T21:00:00", "Japan"));

        List<ZonedDateTime> res = businessHourUtil.calcLastTimesOfMonth(ImmutableSet.of(h1, h2, h3, h4, h5),
                ImmutableSet.of(Year.of(2015)),
                ImmutableSet.of(Month.JANUARY, Month.FEBRUARY),
                ZoneId.of("Japan")
        );

        ZonedDateTime ans1 = JavaTimeConverter.toZonedDateTime("2015-01-03T20:00:00", "Japan");
        ZonedDateTime ans2 = JavaTimeConverter.toZonedDateTime("2015-02-22T21:00:00", "Japan");
        JavaTimeConverter.print(res);
        assertThat(res, contains(ans1, ans2));
    }

    @Test
    public void testCalcLastTimesOfMonth2() throws Exception {
        ZonedDateTimeRange h1 = new ZonedDateTimeRange(
                JavaTimeConverter.toZonedDateTime("2015-01-01T18:00:00", "Japan"),
                JavaTimeConverter.toZonedDateTime("2015-01-01T20:00:00", "Japan"));
        ZonedDateTimeRange h2 = new ZonedDateTimeRange(
                JavaTimeConverter.toZonedDateTime("2015-01-02T18:00:00", "Japan"),
                JavaTimeConverter.toZonedDateTime("2015-01-02T20:00:00", "Japan"));
        ZonedDateTimeRange h3 = new ZonedDateTimeRange(
                JavaTimeConverter.toZonedDateTime("2015-01-03T18:00:00", "Japan"),
                JavaTimeConverter.toZonedDateTime("2015-01-03T20:00:00", "Japan"));

        ZonedDateTimeRange h4 = new ZonedDateTimeRange(
                JavaTimeConverter.toZonedDateTime("2015-02-01T18:00:00", "Japan"),
                JavaTimeConverter.toZonedDateTime("2015-02-01T20:00:00", "Japan"));
        ZonedDateTimeRange h5 = new ZonedDateTimeRange(
                JavaTimeConverter.toZonedDateTime("2015-02-22T19:00:00", "Japan"),
                JavaTimeConverter.toZonedDateTime("2015-02-22T21:00:00", "Japan"));

        List<ZonedDateTime> res = businessHourUtil.calcLastTimesOfMonth(ImmutableSet.of(h1, h2, h3, h4, h5),
                ImmutableSet.of(Year.of(2015)),
                ImmutableSet.of(Month.JANUARY, Month.FEBRUARY),
                ZoneId.of("Europe/London")
        );

        ZonedDateTime ans1 = JavaTimeConverter.toZonedDateTime("2015-01-03T11:00:00", "Europe/London");
        ZonedDateTime ans2 = JavaTimeConverter.toZonedDateTime("2015-02-22T12:00:00", "Europe/London");
        JavaTimeConverter.print(res);
        assertThat(res, contains(ans1, ans2));
    }

    @Test
    public void testCalcFirstTimesOfMonth() throws Exception {
        ZonedDateTimeRange h1 = new ZonedDateTimeRange(
                JavaTimeConverter.toZonedDateTime("2015-01-01T18:00:00", "Japan"),
                JavaTimeConverter.toZonedDateTime("2015-01-01T20:00:00", "Japan"));
        ZonedDateTimeRange h2 = new ZonedDateTimeRange(
                JavaTimeConverter.toZonedDateTime("2015-01-02T18:00:00", "Japan"),
                JavaTimeConverter.toZonedDateTime("2015-01-02T20:00:00", "Japan"));
        ZonedDateTimeRange h3 = new ZonedDateTimeRange(
                JavaTimeConverter.toZonedDateTime("2015-01-03T18:00:00", "Japan"),
                JavaTimeConverter.toZonedDateTime("2015-01-03T20:00:00", "Japan"));

        ZonedDateTimeRange h4 = new ZonedDateTimeRange(
                JavaTimeConverter.toZonedDateTime("2015-02-01T18:00:00", "Japan"),
                JavaTimeConverter.toZonedDateTime("2015-02-01T20:00:00", "Japan"));
        ZonedDateTimeRange h5 = new ZonedDateTimeRange(
                JavaTimeConverter.toZonedDateTime("2015-02-22T19:00:00", "Japan"),
                JavaTimeConverter.toZonedDateTime("2015-02-22T21:00:00", "Japan"));

        List<ZonedDateTime> res = businessHourUtil.calcFirstTimesOfMonth(ImmutableSet.of(h1, h2, h3, h4, h5),
                ImmutableSet.of(Year.of(2015)),
                ImmutableSet.of(Month.JANUARY, Month.FEBRUARY),
                ZoneId.of("Japan")
        );

        ZonedDateTime ans1 = JavaTimeConverter.toZonedDateTime("2015-01-01T18:00:00", "Japan");
        ZonedDateTime ans2 = JavaTimeConverter.toZonedDateTime("2015-02-01T18:00:00", "Japan");
        JavaTimeConverter.print(res);
        assertThat(res, contains(ans1, ans2));
    }

    @Test
    public void testCreateAllDatesByCronPatternBefore() throws Exception {
        ZonedDateTimeRange h1 = new ZonedDateTimeRange(
                JavaTimeConverter.toZonedDateTime("2015-01-01T18:00:00", "Japan"),
                JavaTimeConverter.toZonedDateTime("2015-01-01T20:00:00", "Japan"));
        ZonedDateTimeRange h2 = new ZonedDateTimeRange(
                JavaTimeConverter.toZonedDateTime("2015-01-02T18:00:00", "Japan"),
                JavaTimeConverter.toZonedDateTime("2015-01-02T20:00:00", "Japan"));
        ZonedDateTimeRange h3 = new ZonedDateTimeRange(
                JavaTimeConverter.toZonedDateTime("2015-01-03T18:00:00", "Japan"),
                JavaTimeConverter.toZonedDateTime("2015-01-03T20:00:00", "Japan"));

        ZonedDateTimeRange h4 = new ZonedDateTimeRange(
                JavaTimeConverter.toZonedDateTime("2015-02-01T18:00:00", "Japan"),
                JavaTimeConverter.toZonedDateTime("2015-02-01T20:00:00", "Japan"));
        ZonedDateTimeRange h5 = new ZonedDateTimeRange(
                JavaTimeConverter.toZonedDateTime("2015-02-22T19:00:00", "Japan"),
                JavaTimeConverter.toZonedDateTime("2015-02-22T21:00:00", "Japan"));

        List<ZonedDateTime> res = businessHourUtil.calcNearestDatesByCronPatternBefore(ImmutableSet.of(h1, h2, h3, h4, h5),
                "0 0 22 1 1,2 ?", ZoneId.of("Japan"));

        ZonedDateTime ans1 = JavaTimeConverter.toZonedDateTime("2015-01-01T20:00:00", "Japan");
        ZonedDateTime ans2 = JavaTimeConverter.toZonedDateTime("2015-02-01T20:00:00", "Japan");
        JavaTimeConverter.print(res);
        assertThat(res, contains(ans1, ans2));
    }

    @Test
    public void testCreateAllDatesByCronPatternAfter() throws Exception {
        ZonedDateTimeRange h1 = new ZonedDateTimeRange(
                JavaTimeConverter.toZonedDateTime("2015-01-01T18:00:00", "Japan"),
                JavaTimeConverter.toZonedDateTime("2015-01-01T20:00:00", "Japan"));
        ZonedDateTimeRange h2 = new ZonedDateTimeRange(
                JavaTimeConverter.toZonedDateTime("2015-01-02T18:00:00", "Japan"),
                JavaTimeConverter.toZonedDateTime("2015-01-02T20:00:00", "Japan"));
        ZonedDateTimeRange h3 = new ZonedDateTimeRange(
                JavaTimeConverter.toZonedDateTime("2015-01-03T18:00:00", "Japan"),
                JavaTimeConverter.toZonedDateTime("2015-01-03T20:00:00", "Japan"));

        ZonedDateTimeRange h4 = new ZonedDateTimeRange(
                JavaTimeConverter.toZonedDateTime("2015-02-01T18:00:00", "Japan"),
                JavaTimeConverter.toZonedDateTime("2015-02-01T20:00:00", "Japan"));
        ZonedDateTimeRange h5 = new ZonedDateTimeRange(
                JavaTimeConverter.toZonedDateTime("2015-02-22T19:00:00", "Japan"),
                JavaTimeConverter.toZonedDateTime("2015-02-22T21:00:00", "Japan"));

        List<ZonedDateTime> res = businessHourUtil.calcNearestDatesByCronPatternAfter(ImmutableSet.of(h1, h2, h3, h4, h5),
                "0 0 22 1 1,2 ?", ZoneId.of("Japan"));

        ZonedDateTime ans1 = JavaTimeConverter.toZonedDateTime("2015-01-02T18:00:00", "Japan");
        ZonedDateTime ans2 = JavaTimeConverter.toZonedDateTime("2015-02-22T19:00:00", "Japan");
        JavaTimeConverter.print(res);
        assertThat(res, contains(ans1, ans2));
    }
}