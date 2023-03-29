package com.java.myspringbootdemo02.Api.result;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class Result {

    private int code;
    private String message;
    private Object data;

    public Result(int code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data =  data;
    }

    public Result(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public Result(int code, Object data) {
        this.code = code;
        this.data = data;
    }
    public static Result success() {
        return new Result(200, "success");
    }

    public static Result success( Object data) {
        return new Result(200, "success",data);
    }

    public static Result result( Object data) {
        Map<String,Object> result =new HashMap<>();
        result.put("success",data);
        return new Result(200,result);
    }

    public static Result fail() {
        return new Result(500, "fail");
    }

    public static Result fail( Object data) {
        return new Result(-1, "fail",data);
    }
}
