package com.shinsoft.common;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class RestResult implements Serializable {
    private static final long serialVersionUID = 1L;
    private Object data;
    private int resultCode;
    private String message;
    private Long count;

    public RestResult(int resultCode, String message) {
        this.resultCode = resultCode;
        this.message = message;
    }

    public RestResult(int resultCode, Object data) {
        this.data = data;
        this.resultCode = resultCode;
    }

    public RestResult(int resultCode, Object data, Long count) {
        this.data = data;
        this.resultCode = resultCode;
        this.count = count;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public int getResultCode() {
        return resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }
}
