package com.health.common.util;

import lombok.Data;
import java.io.Serializable;
import java.util.Map;

/**
 * 统一返回结果类
 * 用于标准化 API 响应格式
 */
@Data
public class Result<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    // 状态码
    private Integer code;

    // 消息
    private String msg;

    // 数据
    private T data;

    // ==================== 静态工厂方法 ====================

    /**
     * 成功返回（无数据）
     */
    public static <T> Result<T> success() {
        return new Result<>(200, "success", null);
    }

    /**
     * 成功返回（带消息）
     */
    public static <T> Result<T> success(String message) {
        return new Result<>(200, message, null);
    }

    /**
     * 成功返回（带数据）
     */
    public static <T> Result<T> success(T data) {
        return new Result<>(200, "success", data);
    }

    /**
     * 成功返回（带消息和数据）
     */
    public static <T> Result<T> success(String message, T data) {
        return new Result<>(200, message, data);
    }

    /**
     * 失败返回（带消息）
     */
    public static <T> Result<T> error(String message) {
        return new Result<>(400, message, null);
    }

    /**
     * 失败返回（带消息和错误详情）
     */
    public static <T> Result<Map<String, String>> error(String message, Map<String, String> errors) {
        Result<Map<String, String>> result = new Result<>(400, message, errors);
        return result;
    }

    /**
     * 未授权返回（401）
     */
    public static <T> Result<T> unauthorized(String message) {
        return new Result<>(401, message, null);
    }

    /**
     * 权限不足返回（403）
     */
    public static <T> Result<T> forbidden(String message) {
        return new Result<>(403, message, null);
    }

    /**
     * 内部服务器错误（500）
     */
    public static <T> Result<T> internalServerError(String message) {
        return new Result<>(500, message, null);
    }

    // ==================== 构造函数 ====================

    public Result() {}

    public Result(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Result(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
}