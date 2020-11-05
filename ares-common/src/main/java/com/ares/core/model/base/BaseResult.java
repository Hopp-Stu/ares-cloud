package com.ares.core.model.base;

import lombok.Data;

import java.util.HashMap;

/**
 * @description:
 * @author: yy 2020/01/23
 **/
@Data
public class BaseResult extends HashMap<String, Object> {
    public BaseResult() {
    }

    public static BaseResult error(int code, String message) {
        BaseResult result = new BaseResult();
        result.put("code", code);
        result.put("msg", message);
        return result;
    }

    public static BaseResult success(int code, String message) {
        BaseResult result = new BaseResult();
        result.put("code", code);
        result.put("msg", message);
        return result;
    }

    public static BaseResult error() {
        return error(1, "操作失败");
    }

    public static BaseResult error(String msg) {
        return error(ResultCode.FAILED.getCode(), msg);
    }

    public static BaseResult success(String msg) {
        BaseResult json = new BaseResult();
        json.put("msg", msg);
        json.put("code", ResultCode.SUCCESS.getCode());
        return json;
    }

    public static BaseResult success() {
        return BaseResult.success("操作成功");
    }

    @Override
    public BaseResult put(String key, Object value) {
        super.put(key, value);
        return this;
    }

    public static BaseResult successData(int code, Object value) {
        BaseResult json = new BaseResult();
        json.put("code", code);
        json.put("data", value);
        return json;
    }

    public static BaseResult successData(Object value) {
        BaseResult json = new BaseResult();
        json.put("code", ResultCode.SUCCESS.getCode());
        json.put("data", value);
        return json;
    }

    public static BaseResult result(int code, String msg, Object value) {
        BaseResult baseResult = new BaseResult();
        baseResult.put("code", code);
        baseResult.put("msg", msg);
        baseResult.put("data", value);
        return baseResult;
    }

    public static BaseResult unAuth() {
        BaseResult baseResult = new BaseResult();
        baseResult.put("code", ResultCode.NOAUTH.getCode());
        baseResult.put("msg", ResultCode.NOAUTH.getMsg());
        return baseResult;
    }

    public static BaseResult unLogin() {
        BaseResult baseResult = new BaseResult();
        baseResult.put("code", ResultCode.NOLOGIN.getCode());
        baseResult.put("msg", ResultCode.NOLOGIN.getMsg());
        return baseResult;
    }
}
