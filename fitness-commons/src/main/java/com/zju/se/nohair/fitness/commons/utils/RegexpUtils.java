package com.zju.se.nohair.fitness.commons.utils;

/**
 * 正则表达式工具类.
 *
 * @author CHANG Zheng
 * @version 1.0.0
 * @projectName my-shop
 * @date 2019/11/12 10:45
 */
public class RegexpUtils {

  /**
   * 验证身份证号.
   */
  public static final String ID_NUMBER = "^\\d{15}|\\d{18}$";


  /**
   * 验证手机号.
   */
  public static final String PHONE = "^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$";

  /**
   * 验证邮箱地址.
   */
  public static final String EMAIL = "\\w+(\\.\\w)*@\\w+(\\.\\w{2,3}){1,3}";

  /**
   * 验证身份证号.
   *
   * @param idNumber
   * @return
   */
  public static boolean checkIdNumber(String idNumber) {
    return idNumber.matches(ID_NUMBER);
  }

  /**
   * 验证手机号.
   *
   * @param phone
   * @return
   */
  public static boolean checkPhone(String phone) {
    return phone.matches(PHONE);
  }

  /**
   * 验证邮箱.
   *
   * @param email
   * @return
   */
  public static boolean checkEmail(String email) {
    return email.matches(EMAIL);
  }
}
