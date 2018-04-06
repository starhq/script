package com.star.common;

/**
 * 业务逻辑异常
 *
 * @author starhq(50849806 @ qq.com)
 * @version 1.0
 * @description
 * @date 2018-04-06 18:40:36
 */
public class BussinessException extends RuntimeException {

    private static final long serialVersionUID = -647040740925304353L;

    public BussinessException() {
    }

    public BussinessException(String message) {
        super(message);
    }

    public BussinessException(String message, Throwable cause) {
        super(message, cause);
    }

    public BussinessException(Throwable cause) {
        super(cause);
    }

    public BussinessException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
