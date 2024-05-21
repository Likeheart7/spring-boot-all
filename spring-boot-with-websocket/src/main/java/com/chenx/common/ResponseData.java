package com.chenx.common;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.util.Objects;
import static com.chenx.common.ResponseCodeEnum.*;

public class ResponseData implements Serializable {
    private static final long serialVersionUID = -3965519617324233170L;
    private Integer code;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Object data;
    private String msg;

    public ResponseData() {
    }

    public ResponseData(Integer code, Object data, String msg) {
        this.code = code;
        this.data = data;
        this.msg = msg;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ResponseData)) return false;
        ResponseData that = (ResponseData) o;
        return Objects.equals(code, that.code) && Objects.equals(data, that.data) && Objects.equals(msg, that.msg);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, data, msg);
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }


    public void setData(Object data) {
        this.data = data;
    }

    public Object getData() {
        return data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    //    静态方法
    public static ResponseData ok(){
        return new ResponseData(SUCCESS.getCode(), null, SUCCESS.getMsg());
    }

    public static ResponseData error(){
        return new ResponseData(ERROR.getCode(), null, ERROR.getMsg());
    }

    //    实例方法
    public ResponseData data(Object data){
        this.data = data;
        return this;
    }


    public ResponseData msg(String msg) {
        this.msg = msg;
        return this;
    }
}
