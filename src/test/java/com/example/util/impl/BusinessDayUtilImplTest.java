package com.example.util.impl;

import java.time.LocalDate;
import java.time.Month;
import java.time.ZonedDateTime;
import java.util.List;

import org.junit.Test;

import com.example.util.util.Util;
import com.google.common.collect.ImmutableSet;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.contains;
import static org.junit.Assert.assertThat;

public class BusinessDayUtilImplTest {

    private static final BusinessDayUtilImpl businessDayUtil = new BusinessDayUtilImpl();

    @Test
    public void testCron() throws Exception {
        ZonedDateTime now = Util.toZonedDateTime(Util.createDate("2015/01/01 10:00:00"));
        ZonedDateTime next = Util.getNextValidTimeAfter("0 0 16 8 * ?", now);
        ZonedDateTime ans = Util.toZonedDateTime(Util.createDate("2015/01/08 16:00:00"));
        assertThat(next, is(ans));
    }

    @Test
    public void testCalcBusinessDaysOfEndOfQuarter() throws Exception {
        LocalDate hol1 = Util.toLocalDate("2015-01-01");
        LocalDate hol2 = Util.toLocalDate("2015-06-30");
        LocalDate hol3 = Util.toLocalDate("2016-01-01");
        List<LocalDate> res = businessDayUtil.calcBusinessDaysOfEndOfQuarter(ImmutableSet.of(hol1, hol2, hol3), Month.APRIL);

        LocalDate ans1 = Util.toLocalDate("2015-03-31");
        LocalDate ans2 = Util.toLocalDate("2015-06-29");
        LocalDate ans3 = Util.toLocalDate("2015-09-30");
        LocalDate ans4 = Util.toLocalDate("2015-12-31");
        Util.print(res);
        assertThat(res, contains(ans1, ans2, ans3, ans4));
    }

    @Test
    public void testCalcBusinessDaysOfEndOfMonth() throws Exception {
        LocalDate hol1 = Util.toLocalDate("2015-01-01");
        LocalDate hol2 = Util.toLocalDate("2015-02-28");
        LocalDate hol3 = Util.toLocalDate("2015-02-27");
        LocalDate hol4 = Util.toLocalDate("2015-04-01");
        List<LocalDate> res = businessDayUtil.calcBusinessDaysOfEndOfMonth(ImmutableSet.of(hol1, hol2, hol3, hol4));

        LocalDate ans1 = Util.toLocalDate("2015-01-31");
        LocalDate ans2 = Util.toLocalDate("2015-02-26");
        LocalDate ans3 = Util.toLocalDate("2015-03-31");
        Util.print(res);
        assertThat(res, contains(ans1, ans2, ans3));
    }


    @Test
    public void testCalcBusinessDayBeforeXDay() throws Exception {
        LocalDate hol1 = Util.toLocalDate("2015-01-31");
        LocalDate hol2 = Util.toLocalDate("2015-01-30");
        LocalDate hol3 = Util.toLocalDate("2015-01-27");

        LocalDate target = Util.toLocalDate("2015-02-01");

        LocalDate res = businessDayUtil.calcBusinessDayBeforeXDay(ImmutableSet.of(hol1, hol2, hol3), target, 3);
        LocalDate ans = Util.toLocalDate("2015-01-26");
        Util.print(res);
        assertThat(res, is(ans));
    }

    @Test
    public void testCalcBusinessDayAfterXDay() throws Exception {
        LocalDate hol1 = Util.toLocalDate("2015-01-01");
        LocalDate hol2 = Util.toLocalDate("2015-01-02");
        LocalDate hol3 = Util.toLocalDate("2015-01-04");
        LocalDate target = Util.toLocalDate("2015-01-01");

        LocalDate res = businessDayUtil.calcBusinessDayAfterXDay(ImmutableSet.of(hol1, hol2, hol3), target, 1);
        LocalDate ans = Util.toLocalDate("2015-01-03");
        Util.print(res);
        assertThat(res, is(ans));

        LocalDate res2 = businessDayUtil.calcBusinessDayAfterXDay(ImmutableSet.of(hol1, hol2, hol3), target, 2);
        LocalDate ans2 = Util.toLocalDate("2015-01-05");
        Util.print(res2);
        assertThat(res2, is(ans2));
    }

    @Test
    public void testCalcDateAfter() throws Exception {

    }

    @Test
    public void testCalcDateBefore() throws Exception {

    }

    @Test
    public void testCalcNearestDateBefore() throws Exception {
        LocalDate hol1 = Util.toLocalDate("2015-02-01");
        LocalDate hol2 = Util.toLocalDate("2015-01-30");
        LocalDate hol3 = Util.toLocalDate("2015-01-29");

        LocalDate target = Util.toLocalDate("2015-02-01");
        LocalDate res = businessDayUtil.calcNearestDateBefore(ImmutableSet.of(hol1, hol2, hol3), target);
        LocalDate ans = Util.toLocalDate("2015-01-31");
        assertThat(res, is(ans));

        LocalDate target2 = Util.toLocalDate("2015-01-30");
        LocalDate res2 = businessDayUtil.calcNearestDateBefore(ImmutableSet.of(hol1, hol2, hol3), target2);
        LocalDate ans2 = Util.toLocalDate("2015-01-28");
        assertThat(res2, is(ans2));
    }

    @Test
    public void testCalcNearestDateAfter() throws Exception {
        LocalDate hol1 = Util.toLocalDate("2015-02-01");
        LocalDate hol2 = Util.toLocalDate("2015-01-30");
        LocalDate hol3 = Util.toLocalDate("2015-01-29");

        LocalDate target = Util.toLocalDate("2015-02-01");
        LocalDate res = businessDayUtil.calcNearestDateAfter(ImmutableSet.of(hol1, hol2, hol3), target);
        LocalDate ans = Util.toLocalDate("2015-02-02");
        assertThat(res, is(ans));

        LocalDate target2 = Util.toLocalDate("2015-01-31");
        LocalDate res2 = businessDayUtil.calcNearestDateAfter(ImmutableSet.of(hol1, hol2, hol3), target2);
        LocalDate ans2 = Util.toLocalDate("2015-01-31");
        assertThat(res2, is(ans2));
    }

    @Test
    public void testCalcFirstDateOfMonth() throws Exception {

    }

    @Test
    public void testCalcLastDateOfMonth() throws Exception {

    }

    @Test
    public void testCalcNearestDatesByCronPatternAfter() throws Exception {

    }

    @Test
    public void testCalcNearestDatesByCronPatternBefore() throws Exception {

    }

    @Test
    public void testCreateAllDatesByCronPattern() throws Exception {

    }

    @Test
    public void testCalcLastDatesOfMonth() throws Exception {

    }

    @Test
    public void testCalcFirstDatesOfMonth() throws Exception {

    }
}