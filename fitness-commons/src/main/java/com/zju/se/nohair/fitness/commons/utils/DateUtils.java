package com.zju.se.nohair.fitness.commons.utils;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
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
}
