package com.example.util.spec;

import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.util.List;
import java.util.Set;

/**
 営業日を考慮したスケジュール計算用API.
 純粋な関数にしたいため、各メソッドの引数に休日一覧を入れてもらう形を取る。
 */
public interface BusinessDayUtil {

    /**
     期首と休日一覧から、四半期末営業日を取得する。<br>
     範囲はholidaysに入力された日の最も古い日〜最も新しい日までとする。

     @param holidays: 休業日一覧（二年度分）
     @param beginningOfPeriod: 期首の月（普通は4月。日までは不要？）
     @return 四半期末営業日（日付のみ。期首が４月なら、3,6,9,12月末の営業日）
     */
    List<LocalDate> calcBusinessDaysOfEndOfQuarter(Set<LocalDate> holidays, Month beginningOfPeriod);

    /**
     休日一覧から、各月末営業日を算出する。<br>
     範囲はholidaysに入力された日の最も古い日〜最も新しい日までとする。

     @param holidays: 休業日一覧（二年度分）
     @return 各月末営業日（日付のみ）
     */
    List<LocalDate> calcBusinessDaysOfEndOfMonth(Set<LocalDate> holidays);

    /**
     休日一覧から、対象日のX営業日前を算出する。

     @param holidays: 休業日一覧
     @param targetDay: 対象日
     @param x
     */
    LocalDate calcBusinessDayBeforeXDay(Set<LocalDate> holidays, LocalDate targetDay, int x);

    /**
     休日一覧から、対象日のX営業日後を算出する。

     @param holidays: 休業日一覧
     @param targetDate: 対象日
     @param x
     */
    LocalDate calcBusinessDayAfterXDay(Set<LocalDate> holidays, LocalDate targetDate, int x);

    /**
     対象日に最も近い営業日を算出する。<bt>
     例えば、対象日が25日で24,25日が休みであれば、２３日を返す。

     @param holidays: 休業日一覧
     @param targetDate: 対象日
     */
    LocalDate calcNearestDateBefore(Set<LocalDate> holidays, LocalDate targetDate);

    /**
     対象日に最も近い営業日を算出する。<bt>
     例えば、対象日が25日で25,26日が休みであれば、２７日を返す。

     @param holidays: 休業日一覧
     @param targetDate: 対象日
     */
    LocalDate calcNearestDateAfter(Set<LocalDate> holidays, LocalDate targetDate);

    /**
     対象月の月末の営業日を算出する。<br>
     複数ある場合は全ての月を対象とする。

     @param holidays: 休業日一覧
     @param years: 対象年
     @param month: 対象月
     */
    LocalDate calcFirstDateOfMonth(Set<LocalDate> holidays, Set<Year> years, Set<Month> month);

    /**
     対象月の月末の営業日を算出する。<br>
     複数ある場合は全ての月を対象とする。

     @param holidays: 休業日一覧
     @param years: 対象年
     @param month: 対象月
     */
    LocalDate calcLastDateOfMonth(Set<LocalDate> holidays, Set<Year> years, Set<Month> month);

    /**
     cronパターンで求めた各日付に最も近い営業日を算出する。<br>
     範囲はholidaysに入力された日の最も古い日〜最も新しい日までとする。<br>
     重複した場合は一つとみなす。

     cronPatternの例：<br>
     "0 0 0 1 * ?" : 毎月1日→毎月の月初営業日<br>
     "0 0 0 1 1-6 ?" : 1~6月の1日→1~6月の月初営業日<br>
     "0 0 0 1-7 * MON" : 毎月1~7日の月曜日→毎月の第一月曜日（その日が休みなら翌日）<br>
     "0 0 0 1 * ? 2015" : 2015年の毎月14-18日<br>

     @param holidays: 休業日一覧
     @param cronPattern: cronパターン。時間・分は無視される
     */
    List<LocalDate> calcNearestDatesByCronPatternAfter(Set<LocalDate> holidays, String cronPattern);

    /**
     cronパターンで求めた各日付に最も近い営業日を算出する。<br>
     範囲はholidaysに入力された日の最も古い日〜最も新しい日までとする。<br>
     重複した場合は一つとみなす。

     @param holidays: 休業日一覧
     @param cronPattern: cronパターン。時間・分は無視される
     */
    List<LocalDate> calcNearestDatesByCronPatternBefore(Set<LocalDate> holidays, String cronPattern);

}
