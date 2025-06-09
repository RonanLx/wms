/**
 * @author Ronan_JoJo
 */
package com.itheima.wms.utils;

import lombok.extern.slf4j.Slf4j;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

@Slf4j
public class DateUtils {

    public static String format(LocalDateTime date, String pattern) {
        if (date == null) {
            date = LocalDateTime.now();
        }

        if (pattern == null) {
            pattern = "yyyy-MM";
        }

        return date.format(DateTimeFormatter.ofPattern(pattern));
    }

    public static String format(Date d, String f) {
        Date date = d;
        String format = f;
        if (d == null) {
            date = new Date();
        }

        if (f == null) {
            format = "yyyy-MM-dd HH:mm:ss";
        }

        SimpleDateFormat df = new SimpleDateFormat(format);
        return df.format(date);
    }

    public static String formatAsDateTime(Date date) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return df.format(date);
    }


    public static Date parseAsDateTime(String dateTime) {
        SimpleDateFormat simpledateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        try {
            return simpledateformat.parse(dateTime);
        } catch (ParseException var3) {
            return null;
        }
    }


    public static Date getDate2359(LocalDate value) {
        LocalDateTime dateEnd = LocalDateTime.of(value, LocalTime.MAX);
        return localDateTime2Date(dateEnd);
    }

    public static Date localDateTime2Date(LocalDateTime localDateTime) {
        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime zdt = localDateTime.atZone(zoneId);
        return Date.from(zdt.toInstant());
    }

    public static LocalDateTime date2LocalDateTime(Date date) {
        if (date == null) {
            return LocalDateTime.now();
        } else {
            Instant instant = date.toInstant();
            ZoneId zoneId = ZoneId.systemDefault();
            return instant.atZone(zoneId).toLocalDateTime();
        }
    }

    public static LocalDate date2LocalDate(Date date) {
        if (date == null) {
            return LocalDate.now();
        } else {
            Instant instant = date.toInstant();
            ZoneId zoneId = ZoneId.systemDefault();
            return instant.atZone(zoneId).toLocalDate();
        }
    }

    public static LocalTime date2LocalTime(Date date) {
        if (date == null) {
            return LocalTime.now();
        } else {
            Instant instant = date.toInstant();
            ZoneId zoneId = ZoneId.systemDefault();
            return instant.atZone(zoneId).toLocalTime();
        }
    }

    public static long until(Date endDate) {
        return LocalDateTime.now().until(date2LocalDateTime(endDate), ChronoUnit.DAYS);
    }

    public static long until(Date startDate, Date endDate) {
        return date2LocalDateTime(startDate).until(date2LocalDateTime(endDate), ChronoUnit.DAYS);
    }

    public static long until(LocalDateTime startDate, LocalDateTime endDate) {
        return startDate.until(endDate, ChronoUnit.DAYS);
    }

    public static long until(LocalDate startDate, LocalDate endDate) {
        return startDate.until(endDate, ChronoUnit.DAYS);
    }

    public static List<String> getBetweenDay(Date start, Date end) {
        return getBetweenDay(date2LocalDate(start), date2LocalDate(end));
    }

    public static List<String> getBetweenDay(String start, String end) {
        return getBetweenDay(LocalDate.parse(start), LocalDate.parse(end));
    }

    public static List<String> getBetweenDay(LocalDate startDate, LocalDate endDate) {
        return getBetweenDay(startDate, endDate, "yyyy-MM-dd");
    }

    public static List<String> getBetweenDayEn(LocalDate startDate, LocalDate endDate) {
        return getBetweenDay(startDate, endDate, "yyyy年MM月dd日");
    }

    public static List<String> getBetweenDay(LocalDate startDate, LocalDate endDate, String pattern) {
        if (pattern == null) {
            pattern = "yyyy-MM-dd";
        }

        List<String> list = new ArrayList();
        long distance = ChronoUnit.DAYS.between(startDate, endDate);
        if (distance < 1L) {
            return list;
        } else {
            String finalPattern = pattern;
            Stream.iterate(startDate, (d) -> {
                return d.plusDays(1L);
            }).limit(distance + 1L).forEach((f) -> {
                list.add(f.format(DateTimeFormatter.ofPattern(finalPattern)));
            });
            return list;
        }
    }

    public static List<String> getBetweenWeek(Date start, Date end) {
        return getBetweenWeek(date2LocalDate(start), date2LocalDate(end));
    }

    public static List<String> getBetweenWeek(String start, String end) {
        return getBetweenWeek(LocalDate.parse(start), LocalDate.parse(end));
    }

    public static List<String> getBetweenWeek(LocalDate startDate, LocalDate endDate) {
        return getBetweenWeek(startDate, endDate, "yyyy-ww");
    }

    public static List<String> getBetweenWeek(LocalDate startDate, LocalDate endDate, String pattern) {
        List<String> list = new ArrayList();
        long distance = ChronoUnit.WEEKS.between(startDate, endDate);
        if (distance < 1L) {
            return list;
        } else {
            Stream.iterate(startDate, (d) -> {
                return d.plusWeeks(1L);
            }).limit(distance + 1L).forEach((f) -> {
                list.add(f.format(DateTimeFormatter.ofPattern(pattern)));
            });
            return list;
        }
    }

    public static List<String> getBetweenMonth(Date start, Date end) {
        return getBetweenMonth(date2LocalDate(start), date2LocalDate(end));
    }

    public static List<String> getBetweenMonth(String start, String end) {
        return getBetweenMonth(LocalDate.parse(start), LocalDate.parse(end));
    }

    public static List<String> getBetweenMonth(LocalDate startDate, LocalDate endDate) {
        return getBetweenMonth(startDate, endDate, "yyyy-MM");
    }

    public static List<String> getBetweenMonth(LocalDate startDate, LocalDate endDate, String pattern) {
        List<String> list = new ArrayList();
        long distance = ChronoUnit.MONTHS.between(startDate, endDate);
        if (distance < 1L) {
            return list;
        } else {
            Stream.iterate(startDate, (d) -> {
                return d.plusMonths(1L);
            }).limit(distance + 1L).forEach((f) -> {
                list.add(f.format(DateTimeFormatter.ofPattern(pattern)));
            });
            return list;
        }
    }

    public static String calculationEn(LocalDateTime startTime, LocalDateTime endTime, List<String> dateList) {
        if (startTime == null) {
            startTime = LocalDateTime.now();
        }

        if (endTime == null) {
            endTime = LocalDateTime.now().plusDays(30L);
        }

        return calculationEn(startTime.toLocalDate(), endTime.toLocalDate(), dateList);
    }

    public static String calculation(LocalDate startDate, LocalDate endDate, List<String> dateList) {
        if (startDate == null) {
            startDate = LocalDate.now();
        }

        if (endDate == null) {
            endDate = LocalDate.now().plusDays(30L);
        }

        if (dateList == null) {
            dateList = new ArrayList();
        }

        long day = until(startDate, endDate);
        String dateType = "MONTH";
        if (day >= 0L && day <= 30L) {
            dateType = "DAY";
            ((List) dateList).addAll(getBetweenDay(startDate, endDate, "yyyy-MM-dd"));
        } else if (day > 30L && day <= 90L) {
            dateType = "WEEK";
            ((List) dateList).addAll(getBetweenWeek(startDate, endDate, "yyyy-ww"));
        } else {
            if (day <= 90L || day > 365L) {
                throw new RuntimeException("日期参数只能介于0-365天之间");
            }

            dateType = "MONTH";
            ((List) dateList).addAll(getBetweenMonth(startDate, endDate, "yyyy-MM"));
        }

        return dateType;
    }

    public static String calculationEn(LocalDate startDate, LocalDate endDate, List<String> dateList) {
        if (startDate == null) {
            startDate = LocalDate.now();
        }

        if (endDate == null) {
            endDate = LocalDate.now().plusDays(30L);
        }

        if (dateList == null) {
            dateList = new ArrayList();
        }

        long day = until(startDate, endDate);
        String dateType = "MONTH";
        if (day >= 0L && day <= 30L) {
            dateType = "DAY";
            ((List) dateList).addAll(getBetweenDay(startDate, endDate, "yyyy年MM月dd日"));
        } else if (day > 30L && day <= 90L) {
            dateType = "WEEK";
            ((List) dateList).addAll(getBetweenWeek(startDate, endDate, "yyyy年ww周"));
        } else {
            if (day <= 90L || day > 365L) {
                throw new RuntimeException("日期参数只能介于0-365天之间");
            }

            dateType = "MONTH";
            ((List) dateList).addAll(getBetweenMonth(startDate, endDate, "yyyy年MM月"));
        }

        return dateType;
    }
}
