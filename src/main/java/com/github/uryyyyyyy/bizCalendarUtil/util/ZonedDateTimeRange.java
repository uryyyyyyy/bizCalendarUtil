package com.github.uryyyyyyy.bizCalendarUtil.util;

import java.time.ZonedDateTime;

public class ZonedDateTimeRange {
    public final ZonedDateTime start;
    public final ZonedDateTime end;

    public ZonedDateTimeRange(ZonedDateTime start, ZonedDateTime end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ZonedDateTimeRange that = (ZonedDateTimeRange) o;

        if (start != null ? !start.equals(that.start) : that.start != null) return false;
        return !(end != null ? !end.equals(that.end) : that.end != null);

    }

    @Override
    public int hashCode() {
        int result = start != null ? start.hashCode() : 0;
        result = 31 * result + (end != null ? end.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ZonedDateTimeRange{" +
                "start=" + start +
                ", end=" + end +
                '}';
    }
}
