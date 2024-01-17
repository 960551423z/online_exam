package com.camellia.exam.utils;

import org.springframework.util.DigestUtils;

/**
 * @Author: 阿庆
 * @Date: 2024/1/17 16:15
 * 加密工具包，防止每次都要重写
 */

public class DigestUtilsMD5 {

    public static String encryption(String password) {
        return  DigestUtils.md5DigestAsHex(password.getBytes());
    }
}
