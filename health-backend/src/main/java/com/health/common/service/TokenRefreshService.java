package com.health.common.service;

import com.health.common.config.JwtUtil;
import com.health.common.util.Result;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Token刷新服务
 * 提供Token刷新机制的基础框架
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class TokenRefreshService {

    private final JwtUtil jwtUtil;

    /**
     * 检查Token是否需要刷新
     * @param token 当前Token
     * @return true-需要刷新，false-不需要刷新
     */
    public boolean shouldRefreshToken(String token) {
        try {
            return jwtUtil.isTokenNearExpiry(token, 86400000); // 1天阈值
        } catch (Exception e) {
            log.error("检查Token刷新状态失败", e);
            return false;
        }
    }

    /**
     * 刷新Token
     * @param oldToken 旧的Token
     * @param username 用户名
     * @param userId 用户ID
     * @return 新的Token
     */
    public String refreshToken(String oldToken, String username, Integer userId) {
        try {
            // 验证旧Token是否有效
            if (!jwtUtil.isTokenValid(oldToken)) {
                throw new RuntimeException("旧Token已失效");
            }

            // 生成新Token
            String newToken = jwtUtil.generateToken(username, userId);
            log.info("Token刷新成功，用户：{}", username);
            return newToken;
        } catch (Exception e) {
            log.error("Token刷新失败，用户：{}", username, e);
            throw new RuntimeException("Token刷新失败: " + e.getMessage());
        }
    }

    /**
     * 刷新Token（不含用户ID）
     * @param oldToken 旧的Token
     * @param username 用户名
     * @return 新的Token
     */
    public String refreshToken(String oldToken, String username) {
        try {
            // 验证旧Token是否有效
            if (!jwtUtil.isTokenValid(oldToken)) {
                throw new RuntimeException("旧Token已失效");
            }

            // 生成新Token
            String newToken = jwtUtil.generateToken(username);
            log.info("Token刷新成功，用户：{}", username);
            return newToken;
        } catch (Exception e) {
            log.error("Token刷新失败，用户：{}", username, e);
            throw new RuntimeException("Token刷新失败: " + e.getMessage());
        }
    }

    /**
     * 验证Token并返回验证结果
     * @param token Token
     * @return 验证结果
     */
    public Result<String> validateAndRefreshIfNeeded(String token, String username, Integer userId) {
        try {
            // 验证当前Token是否有效
            if (!jwtUtil.isTokenValid(token, username)) {
                return Result.unauthorized("Token无效或已过期");
            }

            // 检查是否需要刷新
            if (shouldRefreshToken(token)) {
                // 需要刷新，生成新Token
                String newToken = refreshToken(token, username, userId);
                return Result.success("Token已刷新", newToken);
            }

            // 不需要刷新，返回原Token
            return Result.success("Token有效", token);
        } catch (Exception e) {
            log.error("Token验证失败", e);
            return Result.error("Token验证失败: " + e.getMessage());
        }
    }
}