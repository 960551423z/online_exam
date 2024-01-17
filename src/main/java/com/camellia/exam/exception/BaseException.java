package com.camellia.exam.exception;

/**
 * @Author: 阿庆
 * @Date: 2024/1/16 20:16
 * 业务异常
 */

public class BaseException extends RuntimeException {
    public BaseException() {
    }

    public BaseException(String msg) {
        super(msg);
    }
}
