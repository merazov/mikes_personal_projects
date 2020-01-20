package com.mike.time;

import java.io.Serializable;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.temporal.IsoFields;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalField;
import java.time.temporal.TemporalUnit;
import java.time.temporal.UnsupportedTemporalTypeException;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
//import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.joda.time.DateTime;
import org.joda.time.DateTimeConstants;
import org.joda.time.format.ISODateTimeFormat;

public class YearWeek implements Comparable<YearWeek>, Temporal, Serializable {

    public static YearWeek now() {
      return fromDateTime(DateTime.now());
    }
    public static YearWeek from(Temporal temporal) {
      if (temporal instanceof YearWeek) {
        return new YearWeek(((YearWeek)temporal).getYear(), ((YearWeek)temporal).getWeekOfYear());
      }
      if (temporal instanceof LocalDate) {
        return fromDate((LocalDate)temporal);
      }
      return new YearWeek(temporal.get(IsoFields.WEEK_BASED_YEAR), temporal.get(IsoFields.WEEK_OF_WEEK_BASED_YEAR));
    }
    public static YearWeek of(int year, int week) {
      return new YearWeek(year, week);
    }

    public static YearWeek parse(String str) {
      if (StringUtils.isBlank(str)) {
        return null;
      }
      final DateTime date = ISODateTimeFormat.dateParser().parseDateTime(str);
      return new YearWeek(date.getWeekyear(), date.getWeekOfWeekyear());
    }
    public static YearWeek fromDate(LocalDate date) {
      return date == null ? null : fromDate(DateUtils.toDate(date));
    }
    public static YearWeek fromDate(Date date) {
      return date == null ? null : fromDateTime(new DateTime(date));
    }
    public static YearWeek fromDateTime(DateTime time) {
      return time == null ? null : new YearWeek(time.getWeekyear(), time.getWeekOfWeekyear());
    }

    private final int year;
    private final int weekOfYear;

    public YearWeek(int year, int weekOfYear) {
      this.year = year;
      this.weekOfYear = weekOfYear;
    }

    public int getYear() {
      return year;
    }

    public int getWeekOfYear() {
      return weekOfYear;
    }

    public LocalDate monday() {
      return DateUtils.toJLocalDate(getDateTime());
    }

    private DateTime getDateTime() {
      return new DateTime().withWeekyear(getYear()).withWeekOfWeekyear(getWeekOfYear())
          .withDayOfWeek(DateTimeConstants.MONDAY).withTimeAtStartOfDay();
    }

    public long getMillis() {
      return getDateTime().getMillis();
    }

    public YearWeek plusYears(int years) {
      final DateTime futureTime = getDateTime().plusYears(years);
      return new YearWeek(futureTime.getWeekyear(), futureTime.getWeekOfWeekyear());
    }
    public YearWeek plusWeeks(int weeks) {
      final DateTime futureTime = getDateTime().plusWeeks(weeks);
      return new YearWeek(futureTime.getWeekyear(), futureTime.getWeekOfWeekyear());
    }

    @Override
    public String toString() {
      return String.format("%04d-W%02d", getYear(), getWeekOfYear());
    }

    @Override
    public long getLong(TemporalField field) {
      if (field == IsoFields.WEEK_BASED_YEAR) {
        return getYear();
      }
      else if (field == IsoFields.WEEK_OF_WEEK_BASED_YEAR) {
        return getWeekOfYear();
      }
      else {
        throw new UnsupportedTemporalTypeException("TemporalField unit is not supported: " + field);
      }
    }

    @Override
    public Temporal with(TemporalField field, long newValue) {
      if (field == IsoFields.WEEK_BASED_YEAR) {
        return new YearWeek((int)newValue, getWeekOfYear());
      }
      else if (field == IsoFields.WEEK_OF_WEEK_BASED_YEAR) {
        return new YearWeek(getYear(), (int)newValue);
      }
      else {
        throw new UnsupportedTemporalTypeException("TemporalField unit is not supported: " + field);
      }
    }

    @Override
    public boolean isSupported(TemporalField field) {
      return field == IsoFields.WEEK_BASED_YEAR || field == IsoFields.WEEK_OF_WEEK_BASED_YEAR;
    }

    @Override
    public boolean isSupported(TemporalUnit unit) {
      return unit == ChronoUnit.YEARS || unit == ChronoUnit.WEEKS;
    }

    @Override
    public Temporal plus(long amountToAdd, TemporalUnit unit) {
      if (unit == ChronoUnit.YEARS) {
        return this.plusYears((int)amountToAdd);
      }
      else if (unit == ChronoUnit.WEEKS) {
        return this.plusWeeks((int)amountToAdd);
      }
      else {
        throw new DateTimeException("Cannot add unit " + unit + " to YearWeek");
      }
    }

    @Override
    public long until(Temporal endExclusive, TemporalUnit unit) {
      if (unit instanceof ChronoUnit) {
        final YearWeek other = from(endExclusive);
        if (unit == ChronoUnit.YEARS) {
          return DateUtils.yearsBetweenClosedOpen(this, other);
        }
        else if (unit == ChronoUnit.WEEKS) {
          return DateUtils.weeksBetweenClosedOpen(this, other);
        }
        else {
          throw new UnsupportedTemporalTypeException("TemporalUnit unit is not supported: " + unit);
        }
      }
      return unit.between(this, endExclusive);
    }

    @Override
    public boolean equals(Object obj) {
      if (obj == null || !(obj instanceof YearWeek)) {
        return false;
      }
      final YearWeek other = (YearWeek) obj;
      return year == other.year && weekOfYear == other.weekOfYear;
    }
    @Override
    public int hashCode() {
      return new HashCodeBuilder().append(year).append(weekOfYear).toHashCode();
    }

    @Override
    public int compareTo(YearWeek other) {
      final int yearDiff = year - other.year;
      return yearDiff != 0 ? yearDiff : weekOfYear - other.weekOfYear;
    }
    public boolean isAfter(YearWeek other) {
      return compareTo(other) > 0;
    }
    public boolean isBefore(YearWeek other) {
      return compareTo(other) < 0;
    }
  }