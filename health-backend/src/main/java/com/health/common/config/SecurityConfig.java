package com.health.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            // 1. 完全禁用 CSRF（JWT 不需要）
            .csrf(csrf -> csrf.disable())
            
            // 2. 配置访问权限
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/h2-console/**").permitAll()
                .requestMatchers("/api/auth/**").permitAll()  // 登录/注册
                .requestMatchers("/api/public/**").permitAll()
                .anyRequest().authenticated()
            )
            
            // 3. 设置无状态会话 ⭐ 核心配置
            .sessionManagement(session -> 
                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            )
            
            // 4. 禁用表单登录（使用 JWT）
            .formLogin(form -> form.disable())
            
            // 5. 禁用 HTTP Basic（使用 JWT）
            .httpBasic(basic -> basic.disable())
            
            // 6. 允许 H2 控制台使用 frame
            .headers(headers -> headers
                .frameOptions(frame -> frame.disable())
            )
            
            // 7. 异常处理（返回统一的Result格式）
            .exceptionHandling(ex -> ex
                .authenticationEntryPoint((request, response, authException) -> {
                    response.setStatus(401);
                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");
                    response.getWriter().write("{\"code\":401,\"msg\":\"未登录或Token无效\",\"data\":null}");
                })
                .accessDeniedHandler((request, response, accessDeniedException) -> {
                    response.setStatus(403);
                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");
                    response.getWriter().write("{\"code\":403,\"msg\":\"权限不足\",\"data\":null}");
                })
            );
        
        return http.build();
    }
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}