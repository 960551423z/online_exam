package com.camellia.exam.exception;

/**
 * @Author: 阿庆
 * @Date: 2024/1/16 20:15
 * 账号不存在异常
 */

public class AccountNotFoundException extends BaseException {
    public AccountNotFoundException() {
    }

    public AccountNotFoundException(String msg) {
        super(msg);
    }
}
