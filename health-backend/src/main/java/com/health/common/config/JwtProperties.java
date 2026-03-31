package com.health.common.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * JWT配置类
 * 从application.yml中读取JWT相关配置
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "jwt")
public class JwtProperties {

    /**
     * JWT签名密钥
     */
    private String secret;

    /**
     * Token过期时间（毫秒）
     */
    private long expiration;

    /**
     * HTTP请求头名称
     */
    private String header;

    /**
     * Token前缀
     */
    private String prefix;

    /**
     * 刷新Token过期时间（毫秒）
     */
    private long refreshExpiration = 604800000 * 2; // 默认14天

    /**
     * Token即将过期的阈值（毫秒）
     * 当Token剩余时间小于此值时，可以触发刷新
     */
    private long refreshThreshold = 86400000; // 默认1天
}