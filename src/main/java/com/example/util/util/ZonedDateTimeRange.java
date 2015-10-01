package com.example.util.util;

import java.time.ZonedDateTime;

public class ZonedDateTimeRange {
    public final ZonedDateTime start;
    public final ZonedDateTime end;

    public ZonedDateTimeRange(ZonedDateTime start, ZonedDateTime end) {
        this.start = start;
        this.end = end;
    }
}
