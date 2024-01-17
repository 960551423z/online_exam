package com.camellia.exam.exception;

/**
 * @Author: 阿庆
 * @Date: 2024/1/16 20:19
 * 密码错误异常
 */

public class PasswordErrorException extends BaseException{
    public PasswordErrorException() {
    }

    public PasswordErrorException(String msg) {
        super(msg);
    }
}
