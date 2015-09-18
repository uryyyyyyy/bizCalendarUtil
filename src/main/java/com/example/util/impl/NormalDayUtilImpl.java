package com.example.util.impl;


import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.util.Set;

import com.example.util.spec.NormalDayUtil;

public class NormalDayUtilImpl implements NormalDayUtil {

    @Override
    public LocalDate calcLastDateOfMonth(Year year, Month month) {
        return null;
    }

    @Override
    public Set<LocalDate> calcDatesByCronPattern(LocalDate from, LocalDate to, Set<Year> years, Set<Month> month, Set<Short> day) {
        return null;
    }

    @Override
    public Set<LocalDate> calcLastDatesOfMonth(LocalDate from, LocalDate to, Set<Year> years, Set<Month> month) {
        return null;
    }
}
