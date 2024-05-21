package com.chenx.common;

public enum ResponseCodeEnum {
    SUCCESS(200, "请求成功"),
    NOT_FOUND(404, "请求地址不存在"),
    PARAM_MISMATCH(600, "请求参数错误"),
    ALREADY_EXISTS(601, "信息已存在"),
    ERROR(500, "服务器返回错误，联系管理员"),
    OVERTIME(901, "登录超时，请重新登录！");

    private Integer code;
    private String msg;

    ResponseCodeEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
