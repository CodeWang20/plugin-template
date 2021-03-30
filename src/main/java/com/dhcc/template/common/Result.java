package com.dhcc.template.common;

import lombok.Data;

import java.io.Serializable;

@Data
public class Result implements Serializable {

    private int code; //200是正常，非200表示异常
    private String msg; //提示信息
    private Object data; //返回的数据


    /*
     * 请求成功
     * */

    public static Result succ(Object data){
        return succ(200, "", data);
    }

    public static Result succ(String msg, Object data){
        return succ(200, msg, data);
    }

    public static Result succ(int code, String msg, Object data){
        Result r = new Result();
        r.setCode(code);
        r.setMsg(msg);
        r.setData(data);
        return r;
    }

    /*
     * 请求失败
     * */

    public static Result fail(String msg){
        return fail(400, msg, null);
    }

    public static Result fail(int code, String msg){
        return fail(code, msg, null);
    }

    public static Result fail(String msg, Object data){
        return fail(400, msg, data);
    }

    public static Result fail(int code, String msg, Object data){
        Result r = new Result();
        r.setCode(code);
        r.setMsg(msg);
        r.setData(data);
        return r;
    }
}
