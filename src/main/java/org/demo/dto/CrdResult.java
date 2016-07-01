package org.demo.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.io.Serializable;

/**
 * Created by hanhu on 16/6/20.
 */

//DTO:完成WEB层到Service层的数据传递
//所有的ajax请求的返回类型封装JSON结果

@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class CrdResult<T> implements Serializable {


    private static final long serialVersionUID = -3936895148526393338L;

    private boolean success;

    private T data;

    private String error;

    public CrdResult(boolean success, String error) {
        this.success = success;
        this.error = error;
    }

    public CrdResult(boolean success, T data) {
        this.success = success;
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    @Override
    public String toString() {
        return "CrdResult{" +
                "success=" + success +
                ", data=" + data +
                ", error='" + error + '\'' +
                '}';
    }
}
