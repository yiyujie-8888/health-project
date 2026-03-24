package com.health.common.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.health.common.util.Result;
import com.health.common.config.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

/**
 * JWT 认证过滤器
 * 拦截所有请求，验证 JWT Token 并设置认证信息
 */
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final UserDetailsService userDetailsService;
    private final ObjectMapper objectMapper;

    public JwtAuthenticationFilter(JwtUtil jwtUtil, 
                                 UserDetailsService userDetailsService,
                                 ObjectMapper objectMapper) {
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
        this.objectMapper = objectMapper;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, 
                                  HttpServletResponse response, 
                                  FilterChain filterChain) throws ServletException, IOException {
        
        try {
            // 1. 获取 Token
            String token = getTokenFromRequest(request);
            
            if (token != null && jwtUtil.validateToken(token)) {
                // 2. 从 Token 中提取用户名
                String username = jwtUtil.getUsernameFromToken(token);
                
                // 3. 验证用户是否已认证
                if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                    // 4. 加载用户详情
                    UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                    
                    // 5. 创建认证令牌
                    UsernamePasswordAuthenticationToken authentication = 
                        new UsernamePasswordAuthenticationToken(
                            userDetails, 
                            null, 
                            userDetails.getAuthorities()
                        );
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    
                    // 6. 设置认证信息到 SecurityContext
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
            
            // 7. 继续执行过滤器链
            filterChain.doFilter(request, response);
            
        } catch (Exception e) {
            // 8. Token 验证失败处理
            handleAuthenticationError(response, e.getMessage());
        }
    }

    /**
     * 从请求头中提取 Token
     */
    private String getTokenFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7); // 去掉 "Bearer " 前缀
        }
        return null;
    }

    /**
     * 处理认证错误，返回 401 JSON 响应
     */
    private void handleAuthenticationError(HttpServletResponse response, String message) throws IOException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");
        
        Result<?> errorResult = Result.error("认证失败: " + message);
        String jsonResponse = objectMapper.writeValueAsString(errorResult);
        response.getWriter().write(jsonResponse);
    }

    /**
     * 配置需要跳过 JWT 验证的路径
     */
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        String path = request.getRequestURI();
        // 这些路径已经在 SecurityConfig 中放行，但双重保险
        return path.startsWith("/api/auth/") || 
               path.startsWith("/h2-console/") ||
               path.startsWith("/api/public/");
    }
}