package com.health.common.util;

import java.util.UUID;
import java.util.regex.Pattern;

/**
 * 字符串工具类
 */
public class StringUtils {

    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$");
    private static final Pattern PHONE_PATTERN = Pattern.compile("^1[3-9]\\d{9}$");

    /**
     * 判断字符串是否为空
     */
    public static boolean isEmpty(String str) {
        return str == null || str.trim().length() == 0;
    }

    /**
     * 判断字符串是否不为空
     */
    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

    /**
     * 生成UUID（无横线）
     */
    public static String generateUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    /**
     * 验证邮箱格式
     */
    public static boolean isEmail(String email) {
        if (isEmpty(email)) {
            return false;
        }
        return EMAIL_PATTERN.matcher(email).matches();
    }

    /**
     * 验证手机号格式（中国大陆）
     */
    public static boolean isPhone(String phone) {
        if (isEmpty(phone)) {
            return false;
        }
        return PHONE_PATTERN.matcher(phone).matches();
    }

    /**
     * 隐藏手机号中间四位
     */
    public static String hidePhone(String phone) {
        if (isEmpty(phone) || phone.length() < 11) {
            return phone;
        }
        return phone.substring(0, 3) + "****" + phone.substring(7);
    }

    /**
     * 隐藏邮箱部分字符
     */
    public static String hideEmail(String email) {
        if (isEmpty(email) || !email.contains("@")) {
            return email;
        }
        String[] parts = email.split("@");
        if (parts[0].length() <= 2) {
            return email;
        }
        String username = parts[0];
        String domain = parts[1];
        return username.substring(0, 2) + "***@" + domain;
    }
}