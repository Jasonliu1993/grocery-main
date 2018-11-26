package com.jwebcoder.grocerymain.common.exception;

/**
 * Created by Jason on 10/10/2017.
 */
public enum StatusCode {

    SUCCESSFUL(10000,"成功"),
    UNKNOWN(20000, "未知错误"),
    FAILED(20001, "内部错误"),
    NULL_FILE(20002,"文件不能为空"),
    FAILED_UPLOAD(20003,"文件上传失败"),
    SUCCESSFUL_UPLOAD(20004,"文件上传成功")
    ;

    private int code;
    private String errorMessage;

    StatusCode(int code, String errorMessage) {
        this.code = code;
        this.errorMessage = errorMessage;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
