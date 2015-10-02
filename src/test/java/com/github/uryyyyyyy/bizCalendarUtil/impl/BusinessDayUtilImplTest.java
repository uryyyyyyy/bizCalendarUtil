package com.github.uryyyyyyy.bizCalendarUtil.impl;

import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.util.List;

import org.junit.Test;

import com.github.uryyyyyyy.bizCalendarUtil.spec.BusinessDayUtil;
import com.github.uryyyyyyy.bizCalendarUtil.util.JavaTimeConverter;
import com.google.common.collect.ImmutableSet;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.contains;
import static org.junit.Assert.assertThat;

public class BusinessDayUtilImplTest {

    private static final BusinessDayUtil businessDayUtil = new BusinessDayUtilImpl();

    @Test
    public void testCalcBusinessDaysOfEndOfQuarter() throws Exception {
        LocalDate hol1 = JavaTimeConverter.toLocalDate("2015-01-01");
        LocalDate hol2 = JavaTimeConverter.toLocalDate("2015-06-30");
        LocalDate hol3 = JavaTimeConverter.toLocalDate("2016-01-01");
        List<LocalDate> res = businessDayUtil.calcBusinessDaysOfEndOfQuarter(ImmutableSet.of(hol1, hol2, hol3), Month.APRIL);

        LocalDate ans1 = JavaTimeConverter.toLocalDate("2015-03-31");
        LocalDate ans2 = JavaTimeConverter.toLocalDate("2015-06-29");
        LocalDate ans3 = JavaTimeConverter.toLocalDate("2015-09-30");
        LocalDate ans4 = JavaTimeConverter.toLocalDate("2015-12-31");
        JavaTimeConverter.print(res);
        assertThat(res, contains(ans1, ans2, ans3, ans4));
    }

    @Test
    public void testCalcBusinessDaysOfEndOfMonth() throws Exception {
        LocalDate hol1 = JavaTimeConverter.toLocalDate("2015-01-01");
        LocalDate hol2 = JavaTimeConverter.toLocalDate("2015-02-28");
        LocalDate hol3 = JavaTimeConverter.toLocalDate("2015-02-27");
        LocalDate hol4 = JavaTimeConverter.toLocalDate("2015-04-01");
        List<LocalDate> res = businessDayUtil.calcBusinessDaysOfEndOfMonth(ImmutableSet.of(hol1, hol2, hol3, hol4));

        LocalDate ans1 = JavaTimeConverter.toLocalDate("2015-01-31");
        LocalDate ans2 = JavaTimeConverter.toLocalDate("2015-02-26");
        LocalDate ans3 = JavaTimeConverter.toLocalDate("2015-03-31");
        JavaTimeConverter.print(res);
        assertThat(res, contains(ans1, ans2, ans3));
    }


    @Test
    public void testCalcBusinessDayBeforeXDay() throws Exception {
        LocalDate hol1 = JavaTimeConverter.toLocalDate("2015-01-31");
        LocalDate hol2 = JavaTimeConverter.toLocalDate("2015-01-30");
        LocalDate hol3 = JavaTimeConverter.toLocalDate("2015-01-27");

        LocalDate target = JavaTimeConverter.toLocalDate("2015-02-01");

        LocalDate res = businessDayUtil.calcBusinessDayBeforeXDay(ImmutableSet.of(hol1, hol2, hol3), target, 3);
        LocalDate ans = JavaTimeConverter.toLocalDate("2015-01-26");
        JavaTimeConverter.print(res);
        assertThat(res, is(ans));
    }

    @Test
    public void testCalcBusinessDayAfterXDay() throws Exception {
        LocalDate hol1 = JavaTimeConverter.toLocalDate("2015-01-01");
        LocalDate hol2 = JavaTimeConverter.toLocalDate("2015-01-02");
        LocalDate hol3 = JavaTimeConverter.toLocalDate("2015-01-04");
        LocalDate target = JavaTimeConverter.toLocalDate("2015-01-01");

        LocalDate res = businessDayUtil.calcBusinessDayAfterXDay(ImmutableSet.of(hol1, hol2, hol3), target, 1);
        LocalDate ans = JavaTimeConverter.toLocalDate("2015-01-03");
        JavaTimeConverter.print(res);
        assertThat(res, is(ans));

        LocalDate res2 = businessDayUtil.calcBusinessDayAfterXDay(ImmutableSet.of(hol1, hol2, hol3), target, 2);
        LocalDate ans2 = JavaTimeConverter.toLocalDate("2015-01-05");
        JavaTimeConverter.print(res2);
        assertThat(res2, is(ans2));
    }

    @Test
    public void testCalcNearestDateBefore() throws Exception {
        LocalDate hol1 = JavaTimeConverter.toLocalDate("2015-02-01");
        LocalDate hol2 = JavaTimeConverter.toLocalDate("2015-01-30");
        LocalDate hol3 = JavaTimeConverter.toLocalDate("2015-01-29");

        LocalDate target = JavaTimeConverter.toLocalDate("2015-02-01");
        LocalDate res = businessDayUtil.calcNearestDateBefore(ImmutableSet.of(hol1, hol2, hol3), target);
        LocalDate ans = JavaTimeConverter.toLocalDate("2015-01-31");
        assertThat(res, is(ans));

        LocalDate target2 = JavaTimeConverter.toLocalDate("2015-01-30");
        LocalDate res2 = businessDayUtil.calcNearestDateBefore(ImmutableSet.of(hol1, hol2, hol3), target2);
        LocalDate ans2 = JavaTimeConverter.toLocalDate("2015-01-28");
        assertThat(res2, is(ans2));
    }

    @Test
    public void testCalcNearestDateAfter() throws Exception {
        LocalDate hol1 = JavaTimeConverter.toLocalDate("2015-02-01");
        LocalDate hol2 = JavaTimeConverter.toLocalDate("2015-01-30");
        LocalDate hol3 = JavaTimeConverter.toLocalDate("2015-01-29");

        LocalDate target = JavaTimeConverter.toLocalDate("2015-02-01");
        LocalDate res = businessDayUtil.calcNearestDateAfter(ImmutableSet.of(hol1, hol2, hol3), target);
        LocalDate ans = JavaTimeConverter.toLocalDate("2015-02-02");
        assertThat(res, is(ans));

        LocalDate target2 = JavaTimeConverter.toLocalDate("2015-01-31");
        LocalDate res2 = businessDayUtil.calcNearestDateAfter(ImmutableSet.of(hol1, hol2, hol3), target2);
        LocalDate ans2 = JavaTimeConverter.toLocalDate("2015-01-31");
        assertThat(res2, is(ans2));
    }

    @Test
    public void testCalcFirstDateOfMonth() throws Exception {
        LocalDate hol1 = JavaTimeConverter.toLocalDate("2015-01-01");
        LocalDate hol2 = JavaTimeConverter.toLocalDate("2015-01-02");
        LocalDate hol3 = JavaTimeConverter.toLocalDate("2015-08-02");

        List<LocalDate> res = businessDayUtil.calcFirstDateOfMonth(ImmutableSet.of(hol1, hol2, hol3),
                ImmutableSet.of(Year.of(2015)),
                ImmutableSet.of(Month.JANUARY, Month.AUGUST));

        LocalDate ans1 = JavaTimeConverter.toLocalDate("2015-01-03");
        LocalDate ans2 = JavaTimeConverter.toLocalDate("2015-08-01");
        JavaTimeConverter.print(res);
        assertThat(res, contains(ans1, ans2));
    }

    @Test
    public void testCalcLastDateOfMonth() throws Exception {
        LocalDate hol1 = JavaTimeConverter.toLocalDate("2015-01-31");
        LocalDate hol2 = JavaTimeConverter.toLocalDate("2015-01-30");
        LocalDate hol3 = JavaTimeConverter.toLocalDate("2015-09-30");

        List<LocalDate> res = businessDayUtil.calcLastDateOfMonth(ImmutableSet.of(hol1, hol2, hol3),
                ImmutableSet.of(Year.of(2015)),
                ImmutableSet.of(Month.JANUARY, Month.SEPTEMBER));

        LocalDate ans1 = JavaTimeConverter.toLocalDate("2015-01-29");
        LocalDate ans2 = JavaTimeConverter.toLocalDate("2015-09-29");
        JavaTimeConverter.print(res);
        assertThat(res, contains(ans1, ans2));
    }

    @Test
    public void testCalcNearestDatesByCronPatternAfter() throws Exception {
        LocalDate hol1 = JavaTimeConverter.toLocalDate("2015-01-31");
        LocalDate hol2 = JavaTimeConverter.toLocalDate("2015-01-30");
        LocalDate hol3 = JavaTimeConverter.toLocalDate("2015-09-30");
        LocalDate hol4 = JavaTimeConverter.toLocalDate("2015-10-31");

        List<LocalDate> res = businessDayUtil.calcNearestDatesByCronPatternAfter(
                ImmutableSet.of(hol1, hol2, hol3, hol4), "0 0 0 L 1,9 ?");

        LocalDate ans1 = JavaTimeConverter.toLocalDate("2015-02-01");
        LocalDate ans2 = JavaTimeConverter.toLocalDate("2015-10-01");
        JavaTimeConverter.print(res);
        assertThat(res, contains(ans1, ans2));
    }

    @Test
    public void testCalcNearestDatesByCronPatternBefore() throws Exception {
        LocalDate hol1 = JavaTimeConverter.toLocalDate("2015-01-01");
        LocalDate hol2 = JavaTimeConverter.toLocalDate("2015-01-31");
        LocalDate hol3 = JavaTimeConverter.toLocalDate("2015-01-30");
        LocalDate hol4 = JavaTimeConverter.toLocalDate("2015-09-30");

        List<LocalDate> res = businessDayUtil.calcNearestDatesByCronPatternBefore(
                ImmutableSet.of(hol1, hol2, hol3, hol4), "0 0 0 L 1,9 ?");

        LocalDate ans1 = JavaTimeConverter.toLocalDate("2015-01-29");
        LocalDate ans2 = JavaTimeConverter.toLocalDate("2015-09-29");
        JavaTimeConverter.print(res);
        assertThat(res, contains(ans1, ans2));
    }
}