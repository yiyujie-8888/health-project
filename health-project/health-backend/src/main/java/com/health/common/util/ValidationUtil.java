package com.health.common.util;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 手动参数校验工具类
 */
public class ValidationUtil {

    private static final Validator VALIDATOR;

    static {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        VALIDATOR = factory.getValidator();
    }

    /**
     * 校验对象，返回错误信息列表
     */
    public static <T> String validate(T obj) {
        Set<ConstraintViolation<T>> violations = VALIDATOR.validate(obj);
        if (!violations.isEmpty()) {
            return violations.stream()
                    .map(v -> v.getPropertyPath() + ": " + v.getMessage())
                    .collect(Collectors.joining("; "));
        }
        return null;
    }

    /**
     * 校验对象，抛出异常
     */
    public static <T> void validateAndThrow(T obj) {
        String errorMsg = validate(obj);
        if (errorMsg != null) {
            throw new IllegalArgumentException(errorMsg);
        }
    }

    /**
     * 校验单个值
     */
    public static <T> String validateValue(Class<T> beanType, String propertyName, Object value) {
        Set<ConstraintViolation<T>> violations = VALIDATOR.validateValue(beanType, propertyName, value);
        if (!violations.isEmpty()) {
            return violations.stream()
                    .map(v -> v.getMessage())
                    .collect(Collectors.joining("; "));
        }
        return null;
    }
}