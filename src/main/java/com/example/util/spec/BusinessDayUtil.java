package com.example.util.spec;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.Year;
import java.util.Set;

/**
 営業日を考慮したスケジュール計算用API.
 純粋な関数にしたいため、各メソッドの引数に休日一覧を入れてもらう形を取る。
 */
public interface BusinessDayUtil {

    /**
     期首と休日一覧から、四半期末営業日を取得する。
     範囲は今年度分と来年度分の二年度とし、過去分は算出しない。

     holidays: 休業日一覧（二年度分）
     beginingOfPeriod: 期首の月（普通は4月。日までは不要？）
     */
    Set<LocalDate> calcBusinessDaysOfEndOfQuarter(Set<LocalDate> holidays, Month beginningOfPeriod);

    /**
     休日一覧から、各月末営業日を算出する。
     範囲は今年度分と来年度分の二年度とし、過去分は算出しない。
     いつが年度末かはholidaysの最後の日を参考にする。

     holidays: 休業日一覧（二年度分）
     */
    Set<LocalDate> calcBusinessDaysOfEndOfMonth(Set<LocalDate> holidays);

    /**
     休日一覧から、対象日のX営業日前を算出する。

     holidays: 休業日一覧
     targetDay: 今回であれば決算予定日
     x: 今回であれば決算予定日の一日前なので１
     */
    LocalDate calcBusinessDayBeforeXDay(Set<LocalDate> holidays, LocalDate targetDay, int x);

    LocalDate calcDateAfter(Set<LocalDate> holidays, LocalDate targetDate, int after);

    LocalDate calcDateBefore(Set<LocalDate> holidays, LocalDate targetDate, int before);

    LocalDate calcNearestDateBefore(Set<LocalDate> holidays, LocalDate targetDate);

    LocalDate calcNearestDateAfter(Set<LocalDate> holidays, LocalDate targetDate);

    LocalDate calcFirstDateOfMonth(Set<LocalDate> holidays, Year year, Month month);

    LocalDate calcLastDateOfMonth(Set<LocalDate> holidays, Year year, Month month);

    Set<LocalDate> calcNearestDatesByCronPatternAfter(Set<LocalDate> holidays, LocalDate from,
                                                      LocalDate to, Set<Year> years, Set<Month> month, Set<Short> day, Set<DayOfWeek> weeks);

    Set<LocalDate> calcNearestDatesByCronPatternBefore(Set<LocalDate> holidays, LocalDate from,
                                                       LocalDate to, Set<Year> years, Set<Month> month, Set<Short> day, Set<DayOfWeek> weeks);

    Set<LocalDateTime> createAllDatesByCronPattern(Set<LocalDate> holidays, LocalDate from, LocalDate to, String cronPattern);

    Set<LocalDate> calcLastDatesOfMonth(Set<LocalDate> holidays, LocalDate from, LocalDate to, Set<Year> years, Set<Month> month);

    Set<LocalDate> calcFirstDatesOfMonth(Set<LocalDate> holidays, LocalDate from, LocalDate to, Set<Year> years, Set<Month> month);

}
