
# Biz Calendar Util

for business scheduler.

## dependencies

- quartz scheduler.

## usage

### initialize

```java

BusinessDayUtil businessDayUtil = BizScheduleClient.getBusinessDayUtil();

businessDayUtil.~~~

BusinessHourUtil businessHourUtil = BizScheduleClient.getBusinessHourUtil();

businessHourUtil.~~~

```

### BusinessDayUtil

```java

    //休日リストを用意する。
    LocalDate hol1 = JavaTimeConverter.toLocalDate("2015-01-01");
    LocalDate hol2 = JavaTimeConverter.toLocalDate("2015-06-30");
    LocalDate hol3 = JavaTimeConverter.toLocalDate("2016-01-01");
    
    //四半期末の営業日を算出する。
    List<LocalDate> res = businessDayUtil.calcBusinessDaysOfEndOfQuarter(ImmutableSet.of(hol1, hol2, hol3), Month.APRIL);

    //ここでは、四半期末は３,6,9,12月末で、６月は６/30が休みなので6/29になる。
    LocalDate ans1 = JavaTimeConverter.toLocalDate("2015-03-31");
    LocalDate ans2 = JavaTimeConverter.toLocalDate("2015-06-29");
    LocalDate ans3 = JavaTimeConverter.toLocalDate("2015-09-30");
    LocalDate ans4 = JavaTimeConverter.toLocalDate("2015-12-31");
    JavaTimeConverter.print(res);
    assertThat(res, contains(ans1, ans2, ans3, ans4));
```

```java

    //休日リストを用意する。
    LocalDate hol1 = JavaTimeConverter.toLocalDate("2015-01-31");
    LocalDate hol2 = JavaTimeConverter.toLocalDate("2015-01-30");
    LocalDate hol3 = JavaTimeConverter.toLocalDate("2015-01-27");

    //ここでは2/1の三営業日前を算出する。
    LocalDate target = JavaTimeConverter.toLocalDate("2015-02-01");
    LocalDate res = businessDayUtil.calcBusinessDayBeforeXDay(ImmutableSet.of(hol1, hol2, hol3), target, 3);
    
    //営業日は1/29, 1/28, 1/26となり、３営業日前は1/26になる。
    LocalDate ans = JavaTimeConverter.toLocalDate("2015-01-26");
    JavaTimeConverter.print(res);
    assertThat(res, is(ans));
```

### BusinessHourUtil

```java

    //営業時間（start, end）を用意する。
    //ここでは日本時間で、1/1 18:00~20:00, 1/2 18:00~20:00, 1/3 18:00~20:00
    ZonedDateTimeRange b1 = new ZonedDateTimeRange(
            JavaTimeConverter.toZonedDateTime("2015-01-01T18:00:00", "Japan"),
            JavaTimeConverter.toZonedDateTime("2015-01-01T20:00:00", "Japan"));
    ZonedDateTimeRange b2 = new ZonedDateTimeRange(
            JavaTimeConverter.toZonedDateTime("2015-01-02T18:00:00", "Japan"),
            JavaTimeConverter.toZonedDateTime("2015-01-02T20:00:00", "Japan"));
    ZonedDateTimeRange b3 = new ZonedDateTimeRange(
            JavaTimeConverter.toZonedDateTime("2015-01-03T18:00:00", "Japan"),
            JavaTimeConverter.toZonedDateTime("2015-01-03T20:00:00", "Japan"));

    // 1/1 17:00〜の営業時間で最も近いものを算出する。
    ZonedDateTime target = JavaTimeConverter.toZonedDateTime("2015-01-01T17:00:00", "Japan");
    ZonedDateTime res = businessHourUtil.calcNearestTimeAfter(ImmutableSet.of(b1, b2, b3), target);
    //ここでは1/1 18:00~20:00が最も近い。
    ZonedDateTimeRange ans = new ZonedDateTimeRange(
            JavaTimeConverter.toZonedDateTime("2015-01-01T18:00:00", "Japan"),
            JavaTimeConverter.toZonedDateTime("2015-01-01T20:00:00", "Japan")
    );
    assertThat(res, is(ans));

    // ロンドン時間の1/2 22:00〜の営業時間で最も近いものを算出する。
    ZonedDateTime target4 = JavaTimeConverter.toZonedDateTime("2015-01-02T22:00:00", "Europe/London");
    ZonedDateTime res4 = businessHourUtil.calcNearestTimeAfter(ImmutableSet.of(b1, b2, b3), target4);
    //ここでは、日本時間の1/3 18:00が最も近い。
    ZonedDateTimeRange ans4 = new ZonedDateTimeRange(
            JavaTimeConverter.toZonedDateTime("2015-01-03T18:00:00", "Japan"),
            JavaTimeConverter.toZonedDateTime("2015-01-03T20:00:00", "Japan")
    );
    assertThat(res4, is(ans4));
```

```java

    //cronパターンにも対応している。

    //営業時間（start, end）を用意する。
    ZonedDateTimeRange b1 = new ZonedDateTimeRange(
            JavaTimeConverter.toZonedDateTime("2015-01-01T18:00:00", "Japan"),
            JavaTimeConverter.toZonedDateTime("2015-01-01T20:00:00", "Japan"));
    ZonedDateTimeRange b2 = new ZonedDateTimeRange(
            JavaTimeConverter.toZonedDateTime("2015-01-02T18:00:00", "Japan"),
            JavaTimeConverter.toZonedDateTime("2015-01-02T20:00:00", "Japan"));
    ZonedDateTimeRange b3 = new ZonedDateTimeRange(
            JavaTimeConverter.toZonedDateTime("2015-01-03T18:00:00", "Japan"),
            JavaTimeConverter.toZonedDateTime("2015-01-03T20:00:00", "Japan"));

    ZonedDateTimeRange b4 = new ZonedDateTimeRange(
            JavaTimeConverter.toZonedDateTime("2015-02-01T18:00:00", "Japan"),
            JavaTimeConverter.toZonedDateTime("2015-02-01T20:00:00", "Japan"));
    ZonedDateTimeRange b5 = new ZonedDateTimeRange(
            JavaTimeConverter.toZonedDateTime("2015-02-22T19:00:00", "Japan"),
            JavaTimeConverter.toZonedDateTime("2015-02-22T21:00:00", "Japan"));

    //cronパターン(日本時間)に合う時間の直前の営業時間を算出する。
    //ここでは、Jan, Febの1日の22:00のパターン。
    List<ZonedDateTimeRange> res = businessHourUtil.calcNearestDatesByCronPatternBefore(ImmutableSet.of(b1, b2, b3, b4, b5),
            "0 0 22 1 1,2 ?", ZoneId.of("Japan"));

    //営業時間リストが2015年しかないので、2015/1/1 22:00:00, 2015/2/1 22:00:00が対象。
    //直前はそれぞれ1日の営業時間があるのでそれが出力される。
    ZonedDateTimeRange ans1 = new ZonedDateTimeRange(
            JavaTimeConverter.toZonedDateTime("2015-01-01T18:00:00", "Japan"),
            JavaTimeConverter.toZonedDateTime("2015-01-01T20:00:00", "Japan")
    );
    ZonedDateTimeRange ans2 = new ZonedDateTimeRange(
            JavaTimeConverter.toZonedDateTime("2015-02-01T18:00:00", "Japan"),
            JavaTimeConverter.toZonedDateTime("2015-02-01T20:00:00", "Japan")
    );
    assertThat(res, contains(ans1, ans2));
```