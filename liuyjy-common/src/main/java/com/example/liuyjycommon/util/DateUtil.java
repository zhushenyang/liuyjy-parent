package com.example.liuyjycommon.util;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 *   java 8 LocalDate /LocalDateTime
 * Instant         时间戳
 * Duration        持续时间、时间差
 * LocalDate       只包含日期，比如：2018-09-24
 * LocalTime       只包含时间，比如：10:32:10
 * LocalDateTime   包含日期和时间，比如：2018-09-24 10:32:10
 * Peroid          时间段
 * ZoneOffset      时区偏移量，比如：+8:00
 * ZonedDateTime   带时区的日期时间
 * Clock           时钟，可用于获取当前时间戳
 * java.time.format.DateTimeFormatter      时间格式化类
 * @Author liuyjy
 * @Date 2019/8/15
 * @Description: TODO 日期处理
 **/
public class DateUtil {
    /**
     *  DateTime
     * @param localDateTime
     * @return
     */
    public static final String DATETIME="yyyy-MM-dd HH:mm:ss";
    public static final String DATE="yyyy-MM-dd";
    public static final String TIME="HH:mm:ss";



    public static String getLocalDateFormat (LocalDate localDate) {
        // 格式化日期
        return localDate.format(DateTimeFormatter.ISO_LOCAL_DATE);
    }

    public static String getLocalTimeFormat (LocalTime localTime) {
        // 格式化日期
        return localTime.format(DateTimeFormatter.ISO_LOCAL_TIME);
    }

    /**
     * 1.8 Date格式化
     * @param date
     */
    public static String dateFormat (LocalDateTime date,String format) {
        // 格式化日期
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return date.format(formatter);
    }

    /**
     * 1.8 Date格式化
     * @param date
     */
    public static String dateFormat(Date date,String format){
        //An instantaneous point on the time-line.(时间线上的一个瞬时点。)
        Instant instant = date.toInstant();
        //A time-zone ID, such as {@code Europe/Paris}.(时区)
        ZoneId zoneId = ZoneId.systemDefault();
        LocalDateTime localDateTime = instant.atZone(zoneId).toLocalDateTime();
        ////This class is immutable and thread-safe.@since 1.8
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(format);
        return dateTimeFormatter.format(localDateTime);
    }

    /**
     *  String转时间
     * @param time
     * @param format
     * @return
     */
    public static LocalDateTime parseStringToDateTime(String time, String format) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern(format);
        return LocalDateTime.parse(time, df);
    }

    /**
     * 1.8 Date格式化
     * @param timestamp
     */
    public static String dateFormat(Long timestamp,String format){
        Instant instant = Instant.ofEpochMilli(timestamp);
        ZoneId zone = ZoneId.systemDefault();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zone);
        ////This class is immutable and thread-safe.@since 1.8
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(format);
        return dateTimeFormatter.format(localDateTime);
    }

    /**
     * LocalDateTime转换为Date
     * @param localDateTime
     */
    public static Date localDateTimeDate( LocalDateTime localDateTime){
        ZoneId zoneId = ZoneId.systemDefault();
        //Combines this date-time with a time-zone to create a  ZonedDateTime.
        ZonedDateTime zdt = localDateTime.atZone(zoneId);
        return  Date.from(zdt.toInstant());
    }

    /**
     * 时间转时间戳
     * @param localDateTime
     * @return
     */
    public static long getTimestampOfDateTime(LocalDateTime localDateTime) {
        ZoneId zone = ZoneId.systemDefault();
        Instant instant = localDateTime.atZone(zone).toInstant();
        return instant.toEpochMilli();
    }

    public static void main(String[] args) {
        LocalDateTime localDateTime=LocalDateTime.now();
        System.out.println("long=" + getTimestampOfDateTime(localDateTime));
        System.out.println("long2=" + localDateTime.toEpochSecond(ZoneOffset.of("+8")));
        System.out.println("localDateTime=" + dateFormat(getTimestampOfDateTime(localDateTime),DATETIME));
        LocalDate localDate=LocalDate.now();
        System.out.println("localDate=" + getLocalDateFormat(localDate));
        LocalTime localTime=LocalTime.now();
        System.out.println("localTime=" + getLocalTimeFormat(localTime));
        // 解析日期
        String dateText = "20180924";
        LocalDate date = LocalDate.parse(dateText, DateTimeFormatter.BASIC_ISO_DATE);
        System.out.println("格式化之后的日期=" + date);
        // 格式化日期
        dateText = date.format(DateTimeFormatter.ISO_DATE);
        System.out.println("dateText=" + dateText);
    }

}
