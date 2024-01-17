package com.camellia.exam.exception;

/**
 * @Author: 阿庆
 * @Date: 2024/1/16 20:22
 * 账号被锁定异常
 */

public class AccountLockedException extends BaseException {
    public AccountLockedException() {
    }

    public AccountLockedException(String msg) {
        super(msg);
    }
}
