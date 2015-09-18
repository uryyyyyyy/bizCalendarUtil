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
        LocalDate hol1 = Util.toLocalDate(Util.createDate("2015/01/01 10:00:00"));
        LocalDate hol2 = Util.toLocalDate(Util.createDate("2015/06/30 10:00:00"));
        LocalDate hol3 = Util.toLocalDate(Util.createDate("2016/01/01 10:00:00"));
        List<LocalDate> res = businessDayUtil.calcBusinessDaysOfEndOfQuarter(ImmutableSet.of(hol1, hol2, hol3), Month.APRIL);

        LocalDate ans1 = Util.toLocalDate(Util.createDate("2015/03/31 10:00:00"));
        LocalDate ans2 = Util.toLocalDate(Util.createDate("2015/06/29 10:00:00"));
        LocalDate ans3 = Util.toLocalDate(Util.createDate("2015/09/30 10:00:00"));
        LocalDate ans4 = Util.toLocalDate(Util.createDate("2015/12/31 10:00:00"));
        Util.print(res);
        assertThat(res, contains(ans1, ans2, ans3, ans4));
    }

    @Test
    public void testCalcBusinessDaysOfEndOfMonth() throws Exception {
        LocalDate hol1 = Util.toLocalDate(Util.createDate("2015/01/01 10:00:00"));
        LocalDate hol2 = Util.toLocalDate(Util.createDate("2015/02/28 10:00:00"));
        LocalDate hol3 = Util.toLocalDate(Util.createDate("2015/02/27 10:00:00"));
        LocalDate hol4 = Util.toLocalDate(Util.createDate("2015/04/1 10:00:00"));
        List<LocalDate> res = businessDayUtil.calcBusinessDaysOfEndOfMonth(ImmutableSet.of(hol1, hol2, hol3, hol4));

        LocalDate ans1 = Util.toLocalDate(Util.createDate("2015/01/31 10:00:00"));
        LocalDate ans2 = Util.toLocalDate(Util.createDate("2015/02/26 10:00:00"));
        LocalDate ans3 = Util.toLocalDate(Util.createDate("2015/03/31 10:00:00"));
        Util.print(res);
        assertThat(res, contains(ans1, ans2, ans3));
    }

    @Test
    public void testCalcBusinessDayBeforeXDay() throws Exception {

    }

    @Test
    public void testCalcDateAfter() throws Exception {

    }

    @Test
    public void testCalcDateBefore() throws Exception {

    }

    @Test
    public void testCalcNearestDateBefore() throws Exception {

    }

    @Test
    public void testCalcNearestDateAfter() throws Exception {

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