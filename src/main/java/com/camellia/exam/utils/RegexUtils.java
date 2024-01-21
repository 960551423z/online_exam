package com.camellia.exam.utils;

import java.util.regex.Pattern;

/**
 * @Author: 阿庆
 * @Date: 2024/1/19 12:14
 *  正则工具类
 */

public class RegexUtils {

    // 检测邮箱
    public static Boolean checkEmail(String userAccount) {
        String check = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
        Pattern regex = Pattern.compile(check);
        return regex.matcher(userAccount).matches();
    }


}
