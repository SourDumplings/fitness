package com.zju.se.nohair.fitness.commons.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.Date;

/**
 * 时间 Date 相关的工具类.
 *
 * @author CHANG Zheng
 * @version 1.0.0
 * @projectName fitness
 * @date 2019/12/30 21:04
 */
public class DateUtils {

  /**
   * 比较两个 Date 的日期部分是否相等.
   *
   * @param d1
   * @param d2
   * @return
   */
  public static boolean sameDate(Date d1, Date d2) {
    LocalDate localDate1 = ZonedDateTime.ofInstant(d1.toInstant(), ZoneId.systemDefault())
        .toLocalDate();
    LocalDate localDate2 = ZonedDateTime.ofInstant(d2.toInstant(), ZoneId.systemDefault())
        .toLocalDate();
    return localDate1.isEqual(localDate2);
  }

  /**
   * 计算两个 Date 之间的差.
   *
   * @param oldDate
   * @param newDate
   * @return
   */
  public static Period dateDiff(Date oldDate, Date newDate) {
    LocalDate localDate1 = ZonedDateTime.ofInstant(oldDate.toInstant(), ZoneId.systemDefault())
        .toLocalDate();
    LocalDate localDate2 = ZonedDateTime.ofInstant(newDate.toInstant(), ZoneId.systemDefault())
        .toLocalDate();
    return Period.between(localDate1, localDate2);
  }

  /**
   * 通过生日得到年龄.
   *
   * @param birthday
   * @return
   */
  public static Integer getAgeFromBirthday(Date birthday) {
    return dateDiff(birthday, new Date()).getYears();
  }

  /**
   * 将 Date 转换为 yyyy/MM/dd HH:mm:ss 格式的字符串.
   *
   * @param date
   * @return
   */
  public static String date2String(Date date)
  {
    return new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(date);
  }

  /**
   * 将 yyyy/MM/dd HH:mm:ss 格式的字符串转换为 Date.
   *
   * @param dateStr
   * @return
   */
  public static Date strToDate(String dateStr) throws ParseException
  {
    return new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").parse(dateStr);
  }

  /**
   * 计算时间点之前的一个时间点.
   *
   * @param date
   * @param year
   * @param month
   * @param day
   * @return
   */
  public static Date dateBack(Date date, int year, int month, int day) {
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(date);
    calendar.add(Calendar.YEAR, -year);
    calendar.add(Calendar.MONTH, -month);
    calendar.add(Calendar.DAY_OF_MONTH, -day);
    date = calendar.getTime();

    return date;
  }

  /**
   * 计算时间点之后的一个时间点.
   *
   * @param date
   * @param year
   * @param month
   * @param day
   * @return
   */
  public static Date dateFuture(Date date, int year, int month, int day) {
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(date);
    calendar.add(Calendar.YEAR, year);
    calendar.add(Calendar.MONTH, month);
    calendar.add(Calendar.DAY_OF_MONTH, day);
    date = calendar.getTime();

    return date;
  }


  /**
   * 只取日期部分.
   *
   * @param date
   * @return
   */
  public static String datePart(Date date) {
    return new SimpleDateFormat("yyyy/MM/dd").format(date);
  }

  /**
   * 只取时分秒部分.
   *
   * @param date
   * @return
   */
  public static String timePart(Date date) {
    return new SimpleDateFormat("HH:mm:ss").format(date);
  }


  /**
   * 返回上课时间段：yyyy/MM/dd HH:mm:ss~HH:mm:ss.
   *
   * @param courseDate
   * @param beginTime
   * @param endTime
   * @return
   */
  public static String courseTime(Date courseDate, Date beginTime, Date endTime) {
    return new StringBuilder().append(datePart(courseDate)).append(" ").append(timePart(beginTime))
        .append("~")
        .append(timePart(endTime)).toString();
  }
}
