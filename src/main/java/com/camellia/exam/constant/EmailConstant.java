package com.camellia.exam.constant;

/**
 * @Author: 阿庆
 * @Date: 2024/1/18 20:02
 * 邮箱常量进行封装，方便后面修改
 */

public class EmailConstant {

    // 发送邮件标题
    public static final String EMAIL_OBJECT = "注册验证码";

    // 发送邮件模板
    public static final String EMAIL_TEMPLATE = "mail.html";


    // 验证码过期时间 (分钟)
    public static final Integer EMAIL_EXPIRATION = 3;

}
