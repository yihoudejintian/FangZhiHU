package qj.ccmtjf.com.lib.api;

/**
 * Common class used by API responses.
 *
 * @param <T> 范型
 *            Created by liyanxi on 10/26/17.
 *            Copyright (c) 2017 www.zhengshijr.com. All rights reserved.
 */

public class ApiResponse<T> {


    private int code;    // 返回码，0为成功
    private String message;      // 返回信息
    private T data;           // 单个对象/数组对象
    public int errorCode;//: 0,
    public String errorMsg;//: ""

    public ApiResponse(int status, String message) {
        this.code = status;
        this.message = message;
    }

    public ApiResponse(T data, String message, int status) {
        this.data = data;
        this.message = message;
        this.code = status;
    }

    public ApiResponse(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return code == 0;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ApiResponse{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                ", errorCode=" + errorCode +
                ", errorMsg='" + errorMsg + '\'' +
                '}';
    }
}
