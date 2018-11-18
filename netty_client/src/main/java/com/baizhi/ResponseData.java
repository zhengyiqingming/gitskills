package com.baizhi;

import java.io.Serializable;

/**
 * 响应数据对象
 */
public class ResponseData implements Serializable {

    /**
     * 正常调用: 本地调用结果
     */
    private Object result;

    /**
     * 非正常调用: 异常
     */
    private Exception exception;

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public Exception getException() {
        return exception;
    }

    public void setException(Exception exception) {
        this.exception = exception;
    }

    public ResponseData(Object result) {
        this.result = result;
    }

    public ResponseData(Exception exception) {
        this.exception = exception;
    }
}
