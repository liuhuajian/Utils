package com.lhj.mylibrary.http.bean;


import android.support.annotation.Keep;

import java.io.Serializable;

/**
 * Json返回结果集
 *
 * @param <T>
 * @author july.zeng
 * @date 2018/6/10 下午4:11:04
 */
@Keep
public class JsonResult<T> implements Serializable{

    String message;
    String msg;
    String code;
    T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "JsonResult{" +
                "message='" + message + '\'' +
                ", code='" + code + '\'' +
                ", data=" + data +
                '}';
    }
}
