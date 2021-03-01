package com.ares.core.model.base;

import lombok.Data;

import java.util.HashMap;

/**
 * @description:
 * @author: yy 2020/01/23
 **/
@Data
public class AjaxResult extends HashMap<String, Object> {
    public AjaxResult() {
    }

    public static AjaxResult error(int code, String message) {
        AjaxResult result = new AjaxResult();
        result.put("code", code);
        result.put("msg", message);
        return result;
    }

    public static AjaxResult success(int code, String message) {
        AjaxResult result = new AjaxResult();
        result.put("code", code);
        result.put("msg", message);
        return result;
    }

    public static AjaxResult error() {
        return error(1, "操作失败");
    }

    public static AjaxResult error(String msg) {
        return error(ResultCode.FAILED.getCode(), msg);
    }

    public static AjaxResult success(String msg) {
        AjaxResult json = new AjaxResult();
        json.put("msg", msg);
        json.put("code", ResultCode.SUCCESS.getCode());
        return json;
    }

    public static AjaxResult success() {
        return AjaxResult.success("操作成功");
    }

    @Override
    public AjaxResult put(String key, Object value) {
        super.put(key, value);
        return this;
    }

    public static AjaxResult successData(int code, Object value) {
        AjaxResult json = new AjaxResult();
        json.put("code", code);
        json.put("data", value);
        return json;
    }

    public static AjaxResult successData(Object value) {
        AjaxResult json = new AjaxResult();
        json.put("code", ResultCode.SUCCESS.getCode());
        json.put("data", value);
        return json;
    }

    public static AjaxResult result(int code, String msg, Object value) {
        AjaxResult ajaxResult = new AjaxResult();
        ajaxResult.put("code", code);
        ajaxResult.put("msg", msg);
        ajaxResult.put("data", value);
        return ajaxResult;
    }

    public static AjaxResult unAuth() {
        AjaxResult ajaxResult = new AjaxResult();
        ajaxResult.put("code", ResultCode.NOAUTH.getCode());
        ajaxResult.put("msg", ResultCode.NOAUTH.getMsg());
        return ajaxResult;
    }

    public static AjaxResult unLogin() {
        AjaxResult ajaxResult = new AjaxResult();
        ajaxResult.put("code", ResultCode.NOLOGIN.getCode());
        ajaxResult.put("msg", ResultCode.NOLOGIN.getMsg());
        return ajaxResult;
    }
}
