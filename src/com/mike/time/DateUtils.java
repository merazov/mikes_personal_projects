package com.mike.time;

import java.sql.Timestamp;
import java.time.Clock;
import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.DateTimeConstants;
import org.joda.time.DateTimeZone;
import org.joda.time.Days;
import org.joda.time.IllegalFieldValueException;
import org.joda.time.Interval;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.DateTimeFormatterBuilder;
import org.joda.time.format.DateTimeParser;
import org.joda.time.format.ISODateTimeFormat;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.ContiguousSet;
import com.google.common.collect.DiscreteDomain;
import com.google.common.collect.Range;

public class DateUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(DateUtils.class);
    // expose clock for unit test mocking
    private static Clock CLOCK = Clock.systemUTC();
    public static void setClock(Clock clock) {
      CLOCK = clock;
    }

    private static final DateTimeFormatter ROBUST_MONTH;

    private static final Map<String, Date> ISO_WEEK_DATE_CACHE;
    private static final Map<String, DateTime> ISO_WEEK_DATE_TIME_CACHE;

    private static final DateTimeFormatter ROBUST_DATETIME_FORMATTER;
    static {
      ROBUST_MONTH = new DateTimeFormatterBuilder().append(null, new DateTimeParser[]{
        DateTimeFormat.forPattern("MMM").getParser(),
        DateTimeFormat.forPattern("MM").getParser(),
        DateTimeFormat.forPattern("M").getParser()
      }).toFormatter();
      ROBUST_DATETIME_FORMATTER = new DateTimeFormatterBuilder().append(null, new DateTimeParser[] {
          DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss:SSSZ").getParser(),
          DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss.SSSZ").getParser(),
          DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ssZ").getParser(),
          DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss").getParser(),
          DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mmZ").getParser(),
          DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSSS").getParser(),
          DateTimeFormat.forPattern("yyyy-MM-dd").getParser(),
          DateTimeFormat.forPattern("yyyy-M-d").getParser(),
          DateTimeFormat.forPattern("MM/dd/yyyy").getParser(),
          DateTimeFormat.forPattern("M/d/yyyy").getParser(),
          DateTimeFormat.forPattern("MM/dd/yy").getParser(),
          DateTimeFormat.forPattern("M/d/yy").getParser()
      }).toFormatter();
      ISO_WEEK_DATE_TIME_CACHE = Collections.synchronizedMap(new HashMap<String, DateTime>());
      ISO_WEEK_DATE_CACHE = Collections.synchronizedMap(new HashMap<String, Date>());
    }

    public static final ZoneId PST_TIME_ZONE = ZoneId.of("America/Los_Angeles");
    
    public static Date dateFromString(String dateValue) {
      if (StringUtils.isNotBlank(dateValue) && !StringUtils.equalsIgnoreCase("null", dateValue) && !StringUtils.equalsIgnoreCase("N/A", dateValue) && !StringUtils.equalsIgnoreCase("UNKNOWN", dateValue)) {
        return ROBUST_DATETIME_FORMATTER.withZone(DateTimeZone.UTC).parseDateTime(dateValue).toDate();
      }
      return null;
    }
    public static Date dateFromField(JSONObject json, String fieldName) throws JSONException {
      if (json.has(fieldName)) {
        return dateFromString(json.getString(fieldName));
      }
      return null;
    }

    public static String toISODate(java.util.Date date) {
      return date == null ? null : ISODateTimeFormat.date().withZoneUTC().print(date.getTime());
    }

    public static Integer toMonth(String monthValue) {
      try {
        return ROBUST_MONTH.parseDateTime(monthValue).getMonthOfYear();
      } catch (Exception e) {
        return null;
      }
    }

    public static long toEpochDay(LocalDate date) {
      return toEpochDay(DateUtils.toDate(date));
    }
    public static long toEpochDay(Date date) {
      return date == null ? 0 : date.toInstant().atZone(ZoneId.of("UTC")).toLocalDate().toEpochDay();
    }

    public static java.sql.Timestamp toTimestamp(java.util.Date date) {
      return date == null ? null : new java.sql.Timestamp(date.getTime());
    }
    public static java.sql.Timestamp toTimestamp(LocalDate date) {
      return date == null ? null : toTimestamp(date.atStartOfDay());
    }
    public static java.sql.Timestamp toTimestamp(LocalDateTime time) {
      return time == null ? null : Timestamp.valueOf(time);
    }
    public static java.sql.Timestamp toTimestamp(ZonedDateTime date) {
      return date == null ? null : toTimestamp(date.toLocalDateTime());
    }

    public static java.sql.Date toSQLDate(Date date) {
      return date == null ? null : new java.sql.Date(date.getTime());
    }
    public static java.sql.Date toSQLDate(LocalDate date) {
      return date == null ? null  :toSQLDate(toDate(date));
    }
    // FIXME: migrate to java8 time
    @Deprecated
    public static java.sql.Date toSQLDate(DateTime date) {
      return date == null ? null : toSQLDate(date.toDate());
    }

    public static Date toDate(java.sql.Timestamp timestamp) {
      return timestamp == null ? null : new Date(timestamp.getTime());
    }
    public static Date toDate(Date date) {
      return date == null ? null : new Date(date.getTime());
    }
    public static Date toDate(java.sql.Date date) {
      return date == null ? null : new Date(date.getTime());
    }
    public static Date toDate(ZonedDateTime date) {
      return date == null ? null : Date.from(date.withZoneSameInstant(ZoneId.of("UTC")).toInstant());
    }
    public static Date toDate(LocalDateTime date) {
      return date == null ? null : Date.from(date.atZone(ZoneId.of("UTC")).toInstant());
    }
    public static Date toDate(LocalDate date) {
      return date == null ? null : Date.from(date.atStartOfDay(ZoneId.of("UTC")).toInstant());
    }

    public static LocalDateTime toLocalDateTime(Timestamp timestamp) {
      return timestamp == null ? null : LocalDateTime.ofInstant(java.time.Instant.ofEpochMilli(timestamp.getTime()), ZoneId.of("UTC"));
    }
    public static LocalDateTime toLocalDateTime(Date date) {
      return date == null ? null : LocalDateTime.ofInstant(java.time.Instant.ofEpochMilli(date.getTime()), ZoneId.of("UTC"));
    }

    public static ZonedDateTime toZonedDateTime(Instant instant) {
      return instant == null ? null : ZonedDateTime.ofInstant(instant, ZoneId.of("UTC"));
    }
    public static ZonedDateTime toZonedDateTime(Date date) {
      return date == null ? null : toZonedDateTime(Instant.ofEpochMilli(date.getTime()));
    }
    public static ZonedDateTime toZonedDateTime(Timestamp timestamp) {
      return timestamp == null ? null : toZonedDateTime(Instant.ofEpochMilli(timestamp.getTime()));
    }

    public static java.time.LocalDate toJLocalDate(YearWeek week) {
      return week == null ? null : toJLocalDate(new Date(week.getMillis()));
    }
    public static java.time.LocalDate toJLocalDate(java.sql.Date date) {
      return date == null ? null : toJLocalDate(new Date(date.getTime()));
    }
    public static LocalDate toJLocalDate(LocalDateTime time) {
      return time == null ? null : time.toLocalDate();
    }
    public static LocalDate toJLocalDate(Timestamp timestamp) {
      return timestamp == null ? null : toJLocalDate(new Date(timestamp.getTime()));
    }
    // FIXME: migrate to java8 time
    @Deprecated
    public static LocalDate toJLocalDate(DateTime datetime) {
      return datetime == null ? null : toJLocalDate(datetime.toDate());
    }
    public static LocalDate toJLocalDate(Date date) {
      // convert to java.util.Date, in-case object really java.sql.Date
      return date == null ? null : new java.util.Date(date.getTime()).toInstant().atZone(ZoneId.of("UTC")).toLocalDate();
    }
    public static LocalDate toJLocalDate(String value) {
      try {
        return StringUtils.isBlank(value) ? null : toJLocalDate(DateUtils.dateFromString(value));
      }
      catch (DateTimeParseException | IllegalFieldValueException e) {
        return null;
      }
    }

    public static Date parseDate(String date) {
      try {
        return DateTime.parse(date).toDate();
      }
      catch (Exception e) {
        return null;
      }
    }
    // FIXME: migrate to java8 time
    @Deprecated
    public static DateTime min(DateTime date1, DateTime date2) {
      return date1 == null || date1.isBefore(date2) ? date1 : date2;
    }
    public static LocalDate min(LocalDate date1, LocalDate date2) {
      if (date1 == null) {
        return date2;
      }
      if (date2 == null) {
        return date1;
      }
      return date1.isBefore(date2) ? date1 : date2;
    }
    // FIXME: migrate to java8 time
    @Deprecated
    public static DateTime max(DateTime date1, DateTime date2) {
      return date1 == null || date1.isAfter(date2) ? date1 : date2;
    }
    public static YearWeek max(YearWeek lhs, YearWeek rhs) {
      if (lhs == null) {
        return rhs;
      }
      if (rhs == null) {
        return lhs;
      }
      return lhs.isAfter(rhs) ? lhs : rhs;
    }
    public static LocalDate max(LocalDate date1, LocalDate date2) {
      if (date1 == null) {
        return date2;
      }
      if (date2 == null) {
        return date1;
      }
      return date1.isAfter(date2) ? date1 : date2;
    }

    public static YearWeek toIsoYearWeek(String isoWeek) {
      if (StringUtils.isNotBlank(isoWeek)) {
        try {
          return YearWeek.parse(isoWeek);
        }
        catch (IllegalArgumentException e) {
          LOGGER.debug("Unable to parse ISO YearWeek {}", isoWeek, e);
        }
      }
      return null;
    }

    public static Date max(Date date1, Date date2) {
      if (date1 == null) {
        return date2;
      }
      if (date2 == null) {
        return date1;
      }
      return date1.after(date2) ? date1 : date2;
    }

    public static Date min(Date date1, Date date2) {
      if (date1 == null) {
        return date2;
      }
      if (date2 == null) {
        return date1;
      }
      return date1.before(date2) ? date1 : date2;
    }

    public static DateTime isoWeekToDateTime(String isoWeek, int dayOfTheWeek) {
      if (!ISO_WEEK_DATE_TIME_CACHE.containsKey(isoWeek)) {
        ISO_WEEK_DATE_TIME_CACHE.put(isoWeek, DateTime.parse(isoWeek).withDayOfWeek(dayOfTheWeek).withTimeAtStartOfDay());
      }
      return ISO_WEEK_DATE_TIME_CACHE.get(isoWeek);
    }

    public static Date isoWeekToMonday(String isoWeek) {
      if (!ISO_WEEK_DATE_CACHE.containsKey(isoWeek)) {
        ISO_WEEK_DATE_CACHE.put(isoWeek, isoWeekToDateTime(isoWeek, DateTimeConstants.MONDAY).toDate());
      }
      return ISO_WEEK_DATE_CACHE.get(isoWeek);
    }

    public static String toIsoWeek(Date time) {
      return time == null ? null : toIsoWeek(new DateTime(time));
    }
    public static String toIsoWeek(YearWeek week) {
      return week == null ? null : week.toString().intern();
    }
    public static String toIsoWeek(LocalDate time) {
      return time == null ? null : toIsoWeek(toDate(time));
    }
    // FIXME: migrate to java8 time
    @Deprecated
    public static String toIsoWeek(DateTime time) {
      return time == null ? null : time.toString("xxxx'-W'ww").intern();
    }
    public static Integer getIsoWeek(Date time) {
      return time == null ? null : getIsoWeek(new DateTime(time));
    }
    // FIXME: migrate to java8 time
    @Deprecated
    public static Integer getIsoWeek(DateTime time) {
      return time == null ? null : Integer.parseInt(time.toString("ww"));
    }
    public static Integer getIsoYear(Date time) {
      return time == null ? null : getIsoYear(new DateTime(time));
    }
    // FIXME: migrate to java8 time
    @Deprecated
    public static Integer getIsoYear(DateTime time) {
      return time == null ? null : Integer.parseInt(time.toString("xxxx"));
    }

    public static Interval dateToQuarter(Date date) {
      final DateTime time = new DateTime(date);
      final int year = time.getYear();

      final List<Interval> intervals = new ArrayList<>();
      intervals.add(new Interval(new DateTime(year, 1, 1, 0, 0), new DateTime(year, 4, 1, 0, 0)));
      intervals.add(new Interval(new DateTime(year, 4, 1, 0, 0), new DateTime(year, 7, 1, 0, 0)));
      intervals.add(new Interval(new DateTime(year, 7, 1, 0, 0), new DateTime(year, 10, 1, 0, 0)));
      intervals.add(new Interval(new DateTime(year, 10, 1, 0, 0), new DateTime(year + 1, 1, 1, 0, 0)));

      for (Interval interval : intervals) {
        if (interval.contains(time)) {
          return interval;
        }
      }

      throw new IllegalArgumentException("Unable to determine quarter for time " + time);
    }

    public static Interval toInterval(Date fromDate, Date toDate) {
      return new Interval(new DateTime(fromDate), new DateTime(toDate));
    }

    public static Date monthEndForDate(Date date) {
      Calendar cal = Calendar.getInstance();
      cal.setTime(date);
      cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
      return cal.getTime();
    }

    public static boolean isBetween(YearWeek fromInclusive, YearWeek toExclusive, Date date) {
      if (date == null) {
        return false;
      }
      final YearWeek week = YearWeek.fromDate(date);
      if (fromInclusive != null && fromInclusive.isAfter(week)) {
        return false;
      }
      if (toExclusive != null && !toExclusive.isAfter(week)) {
        return false;
      }
      return true;
    }

    public static int yearsBetweenClosedOpen(Temporal start, Temporal end) {
      return (int) Math.abs(ChronoUnit.YEARS.between(start, end));
    }

    public static int monthsBetweenClosedOpen(Temporal start, Temporal end) {
      return (int) Math.abs(ChronoUnit.MONTHS.between(start, end));
    }
    

    public static int weeksBetweenClosedOpen(YearWeek start, YearWeek end) {
      return weeksBetweenClosedOpen(start == null ? null : start.monday(), end == null ? null : end.monday());
    }
    public static int weeksBetweenClosedOpen(LocalDate startDate, LocalDate otherDate) {
      if (otherDate == null) {
        return -1;
      }
      return (int) Math.abs(ChronoUnit.WEEKS.between(startDate, otherDate));
    }

    public static Integer daysDiff(Date startDate, Date otherDate) {
      if (startDate == null || otherDate == null) {
        return null;
      }
      return Days.daysBetween(new DateTime(startDate), new DateTime(otherDate)).getDays();
    }

    public static int daysBetween(Date startDate, Date otherDate) {
      if (startDate == null || otherDate == null) {
        return -1;
      }
      return daysBetween(toJLocalDate(startDate), toJLocalDate(otherDate));
    }
    public static int daysBetween(Range<LocalDate> range) {
      if (range == null || !range.hasLowerBound() || !range.hasUpperBound()) {
        return 0;
      }
      return daysBetween(range.lowerEndpoint(), range.upperEndpoint());
    }
    public static int daysBetween(LocalDate lhs, LocalDate rhs) {
      return daysBetween(lhs, rhs, false);
    }
    public static int daysBetween(LocalDate lhs, LocalDate rhs, boolean relative) {
      if (lhs == null || rhs == null) {
        return 0;
      }
      return relative ? (int) ChronoUnit.DAYS.between(lhs, rhs) : (int) Math.abs(ChronoUnit.DAYS.between(lhs, rhs));
    }
    
    public static String formatWeekEndDate(LocalDate date) {
      return date.with(ChronoField.DAY_OF_WEEK, DayOfWeek.SUNDAY.getValue()).toString();
    }
    public static String formatWeekEndDate(Date date) {
      return formatWeekEndDate(DateUtils.toJLocalDate(date));
    }
    public static String formatWeekEndDate(YearWeek week) {
      return formatWeekEndDate(DateUtils.toJLocalDate(week));
    }

    public static Date toStartOfDayUTC(Date date) {
      return date != null ? new DateTime(date.getTime()).withZone(DateTimeZone.UTC).withTimeAtStartOfDay().toDate() : null;
    }

    // FIXME: migrate to java8 time
    @Deprecated
    public static DateTime today() {
      return DateTime.now().withZone(DateTimeZone.UTC).withTimeAtStartOfDay();
    }
    // FIXME: migrate to java8 time
    @Deprecated
    public static DateTime thisMonday() {
      return DateTime.now().withDayOfWeek(DateTimeConstants.MONDAY).withTimeAtStartOfDay();
    }

    public static ZonedDateTime nowDateTime() {
      return ZonedDateTime.now(CLOCK);
    }
    public static LocalDate now() {
      return LocalDate.now(CLOCK);
    }
    public static LocalDate monday() {
      return mondayOfWeek(LocalDate.now(CLOCK));
    }

    public static Interval thisWeek() {
      final DateTime thisMonday = DateUtils.thisMonday();
      return new Interval(thisMonday, thisMonday.plusWeeks(1));
    }
    public static YearWeek thisIsoWeek() {
      return YearWeek.now();
    }
    public static Interval lastWeek() {
      final DateTime today = DateUtils.today();
      return DateUtils.toInterval(today.minusWeeks(1).toDate(), today.toDate());
    }

    public static LocalDate mondayOfWeek(YearWeek week) {
      return mondayOfWeek(toJLocalDate(week));
    }
    public static LocalDate mondayOfWeek(LocalDate date) {
      return date == null ? null : date.with(ChronoField.DAY_OF_WEEK, DayOfWeek.MONDAY.getValue());
    }
    public static LocalDate sundayOfWeek(YearWeek week) {
      return sundayOfWeek(toJLocalDate(week));
    }
    public static LocalDate sundayOfWeek(LocalDate date) {
      return date == null ? null : date.with(ChronoField.DAY_OF_WEEK, DayOfWeek.SUNDAY.getValue());
    }
    
    
    public static boolean isBeforeThisWeek(Date date) {
      return date == null ? true : DateUtils.thisMonday().isAfter(date.getTime());
    }
    public static boolean isBeforeThisWeek(YearWeek week) {
      return week == null ? true : week.isBefore(thisIsoWeek());
    }
    public static boolean isBeforeWeek(YearWeek week, LocalDate localDate) {
      final YearWeek targetWeek = YearWeek.fromDate(localDate);
      return targetWeek == null ? false : targetWeek.isBefore(week);
    }
    public static boolean isAfterWeek(YearWeek week, LocalDate localDate) {
      final YearWeek targetWeek = YearWeek.fromDate(localDate);
      return targetWeek == null ? false : targetWeek.isAfter(week);
    }

    public static boolean isAfterToday(Date date) {
      return date == null ? true : date.after(DateTime.now().toDate());
    }

    /**
     * Gives the Sunday closest to and before or on the input date
     * @param d a LocalDate
     * @return the Sunday closest to and before d, or d itself is d is a Sunday
     */
    public static LocalDate mostRecentSundayJLocalDate(LocalDate d) {
      if (d == null) return null;
      return d.getDayOfWeek() == DayOfWeek.SUNDAY ? d : d.minusDays(d.getDayOfWeek().getValue());
    }

    /**
     * Gives the list of Sundays between the two given dates. If startDate or endDate is a Sunday, they will also be included in the list.
     * @param startDate a LocalDate
     * @param endDate a LocalDate
     * @return list of Sundays between the two given dates including the given dates if they are Sundays
     */
    public static List<LocalDate> allSundaysBetweenInclusiveJLocalDate(LocalDate startDate, LocalDate endDate) {
      List<LocalDate> sundays = new ArrayList<>();
      while (!startDate.isAfter(endDate)) {
        sundays.add(startDate);
        startDate = startDate.plusWeeks(1);
      }
      return sundays;
    }

    public static Date subtractWeekDays(Date date, int numberOfWeekDays) {
      if(date == null) {
        return date;
      }

      LocalDate localDate = toJLocalDate(date);
      while(numberOfWeekDays > 0) {
        localDate = localDate.minusDays(1);
        if (localDate.getDayOfWeek() != DayOfWeek.SATURDAY && localDate.getDayOfWeek() != DayOfWeek.SUNDAY) {
          numberOfWeekDays--;
        }
      }
      return toDate(localDate);
    }

    public static LocalDate roundToNextWholeMonthWeek(LocalDate endDate) {
      final LocalDate nextMonth = endDate.plusMonths(1).withDayOfMonth(1);
      return mondayOfWeek(nextMonth);
    }

    public static LocalDate toSundayWithinHorizon(LocalDate date, LocalDate beginOfHorizon, LocalDate endOfHorizon) {
      LocalDate sanitizedDate = DateUtils.sundayOfWeek(date);
      // TODO: verify again if it is correct
      if (sanitizedDate.isBefore(beginOfHorizon)) {
        sanitizedDate = beginOfHorizon;
      } else if (sanitizedDate.isAfter(endOfHorizon)) {
        sanitizedDate = endOfHorizon;
      }
      return sanitizedDate;
    }
  }

