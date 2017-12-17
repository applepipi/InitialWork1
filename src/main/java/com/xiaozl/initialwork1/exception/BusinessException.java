package com.xiaozl.initialwork1.exception;

/**
 * Created by user on 2017/12/13.
 * 业务异常类
 */
public class BusinessException extends Exception{
    public BusinessException() {
    }

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(Throwable cause) {
        super(cause);
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }
}
