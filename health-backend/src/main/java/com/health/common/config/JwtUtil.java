package com.health.common.config;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtUtil {

    // JWT 签名密钥（从配置文件读取）
    @Value("${jwt.secret}")
    private String secretKey;

    // Token 过期时间（毫秒）
    @Value("${jwt.expiration}")
    private long expiration;

    // HTTP 请求头名称
    @Value("${jwt.header:Authorization}")
    private String headerName;

    // Token 前缀
    @Value("${jwt.prefix:Bearer }")
    private String tokenPrefix;

    /**
     * 获取加密密钥
     */
    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    /**
     * 从 Token 中提取用户名
     */
    @Nullable
    public String extractUsername(@NonNull String token) {
        return extractClaim(token, Claims::getSubject);
    }

    /**
     * 从 Token 中提取用户ID
     */
    @Nullable
    public Integer extractUserId(@NonNull String token) {
        return extractClaim(token, claims -> claims.get("userId", Integer.class));
    }

    /**
     * 从 Token 中提取指定声明
     */
    @Nullable
    public <T> T extractClaim(@NonNull String token, @NonNull Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    /**
     * 生成 Token（不含额外声明）
     */
    @NonNull
    public String generateToken(@NonNull String username) {
        return generateToken(new HashMap<>(), username);
    }

    /**
     * 生成 Token（含额外声明）
     */
    @NonNull
    public String generateToken(@NonNull Map<String, Object> extraClaims, @NonNull String username) {
        return Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    /**
     * 生成 Token（含用户ID）
     */
    @NonNull
    public String generateToken(@NonNull String username, @NonNull Integer userId) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", userId);
        return generateToken(claims, username);
    }

    /**
     * 验证 Token 是否有效
     */
    public boolean isTokenValid(@NonNull String token, @NonNull String username) {
        final String extractedUsername = extractUsername(token);
        return (extractedUsername != null && extractedUsername.equals(username)) && !isTokenExpired(token);
    }

    /**
     * 验证 Token 是否有效（仅检查过期）
     */
    public boolean isTokenValid(@NonNull String token) {
        return !isTokenExpired(token);
    }

    /**
     * 检查 Token 是否过期
     */
    private boolean isTokenExpired(@NonNull String token) {
        return extractExpiration(token).before(new Date());
    }

    /**
     * 提取 Token 过期时间
     */
    @NonNull
    private Date extractExpiration(@NonNull String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    /**
     * 解析 Token 获取所有声明
     */
    @NonNull
    private Claims extractAllClaims(@NonNull String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * 从请求头中提取 Token（去掉 Bearer 前缀）
     */
    @Nullable
    public String extractTokenFromHeader(@Nullable String authHeader) {
        if (authHeader != null && authHeader.startsWith(tokenPrefix)) {
            return authHeader.substring(tokenPrefix.length());
        }
        return null;
    }

    /**
     * 获取 HTTP 请求头名称
     */
    @NonNull
    public String getHeaderName() {
        return headerName;
    }

    /**
     * 获取 Token 前缀
     */
    @NonNull
    public String getTokenPrefix() {
        return tokenPrefix;
    }

    /**
     * 检查 Token 是否即将过期（剩余时间小于指定阈值）
     * @param token Token
     * @param thresholdMillis 阈值（毫秒）
     * @return true-即将过期，false-未即将过期
     */
    public boolean isTokenNearExpiry(@NonNull String token, long thresholdMillis) {
        Date expiration = extractExpiration(token);
        long remainingTime = expiration.getTime() - System.currentTimeMillis();
        return remainingTime < thresholdMillis;
    }

    // ========== 新增方法：兼容 JwtAuthenticationFilter ==========
    
    /**
     * 验证 Token 是否有效（简化版，用于过滤器）
     */
    public boolean validateToken(@NonNull String token) {
        return !isTokenExpired(token);
    }

    /**
     * 从 Token 中提取用户名（兼容过滤器调用）
     */
    @Nullable
    public String getUsernameFromToken(@NonNull String token) {
        return extractUsername(token);
    }
}