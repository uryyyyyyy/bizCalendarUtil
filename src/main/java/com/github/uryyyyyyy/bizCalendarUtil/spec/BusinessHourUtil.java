package com.github.uryyyyyyy.bizCalendarUtil.spec;

import java.time.Month;
import java.time.Year;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Set;

import com.github.uryyyyyyy.bizCalendarUtil.util.ZonedDateTimeRange;

/**
 営業時間を考慮したスケジュール計算用API.
 各メソッドの引数に営業時間一覧を入れてもらう形を取る。<br>
 zonedDateTimeRangesは、営業時間（何時start〜何時end）を入れる。
 */
public interface BusinessHourUtil {

    /**
     * 対象日時以降の、最も近い営業時間帯を返す。<br>
     *
     * @param zonedDateTimeRanges: 営業時間一覧（二年度分を想定）
     * @param target: 対象日時
     */
    ZonedDateTimeRange calcNearestTimeAfter(Set<ZonedDateTimeRange> zonedDateTimeRanges, ZonedDateTime target);

    /**
     * 対象日時以前の、最も近い営業時間帯を返す。<br>
     *
     * @param zonedDateTimeRanges: 営業時間一覧（二年度分を想定）
     * @param target: 対象日時
     */
    ZonedDateTimeRange calcNearestTimeBefore(Set<ZonedDateTimeRange> zonedDateTimeRanges, ZonedDateTime target);

    /**
     * 指定された月の、最終の営業時間帯を返す。<br>
     *
     * @param zonedDateTimeRanges: 営業時間一覧（二年度分を想定）
     * @param years: 指定年
     * @param month: 指定月
     * @param zoneId: client側で知りたいzoneId
     * @return 古い順でソートされた最終営業日List
     */
    List<ZonedDateTimeRange> calcLastTimesOfMonth(Set<ZonedDateTimeRange> zonedDateTimeRanges, Set<Year> years, Set<Month> month, ZoneId zoneId);

    /**
     * 指定された月の、最初の営業時間帯を返す。<br>
     *
     * @param zonedDateTimeRanges: 営業時間一覧（二年度分を想定）
     * @param years: 指定年
     * @param month: 指定月
     * @param zoneId: client側で知りたいzoneId
     * @return 古い順でソートされた最終営業日List
     */
    List<ZonedDateTimeRange> calcFirstTimesOfMonth(Set<ZonedDateTimeRange> zonedDateTimeRanges, Set<Year> years, Set<Month> month, ZoneId zoneId);

    /**
     * cronパターンで求めた各日付以降の最も近い営業時間帯を算出する。<br>
     * 範囲はholidaysに入力された日の最も古い日〜最も新しい日までとする。<br>
     * 重複した場合は一つとみなす。
     *
     * cronPatternの例：<br>
     * "0 0 22 1 1,2 ?" : 1月、2月の1日22:00<br>
     *
     * @param zonedDateTimeRanges: 営業時間一覧（二年度分を想定）
     * @param cronPattern: 上記参照
     */
    List<ZonedDateTimeRange> calcNearestDatesByCronPatternBefore(Set<ZonedDateTimeRange> zonedDateTimeRanges, String cronPattern, ZoneId zoneId);

    /**
     * cronパターンで求めた各日付以前の最も近い営業時間帯を算出する。<br>
     * 範囲はholidaysに入力された日の最も古い日〜最も新しい日までとする。<br>
     * 重複した場合は一つとみなす。
     *
     * cronPatternの例：<br>
     * "0 0 22 1 1,2 ?" : 1月、2月の1日22:00<br>
     *
     * @param zonedDateTimeRanges: 営業時間一覧（二年度分を想定）
     * @param cronPattern: 上記参照
     */
    List<ZonedDateTimeRange> calcNearestDatesByCronPatternAfter(Set<ZonedDateTimeRange> zonedDateTimeRanges, String cronPattern, ZoneId zoneId);

}
