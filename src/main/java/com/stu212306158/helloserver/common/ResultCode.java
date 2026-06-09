package com.stu212306158.helloserver.common;

/**
 * 自定义业务状态码枚举
 */
public enum ResultCode {
    // 基础状态码
    SUCCESS(200,"操作成功"),
    ERROR(500,"系统繁忙,请稍后再试"),
    // 权限/Token状态码
    TOKEN_INVALID(401,"登录凭证已缺失或过期,请重新登录");
    USER_HAS_EXISTED(4001,"该用户名已被注册"),
    USER_NOT_EXIST(4002,"该用户不存在"),
    PASSWORD_ERROR(4003,"账号或密码错误");

    // 状态码
    private final Integer code;
    // 提示信息
    private final String msg;

    // 构造方法
    ResultCode(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    // Getter 方法（枚举只有getter，不允许修改）
    public Integer getCode() {
        return code;
    }
    public String getMsg() {
        return msg;
    }
}