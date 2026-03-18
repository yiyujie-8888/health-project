package com.health.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            // 1. 禁用 CSRF（H2 控制台需要）
            .csrf(csrf -> csrf
                .ignoringRequestMatchers("/h2-console/**")
            )
            // 2. 配置访问权限
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/h2-console/**").permitAll()
                .requestMatchers("/api/auth/**").permitAll()
                .requestMatchers("/api/public/**").permitAll()
                .anyRequest().authenticated()
            )
            // 3. 允许 H2 控制台使用 frame
            .headers(headers -> headers
                .frameOptions(frame -> frame.disable())
            )
            // 4. 表单登录配置
            .formLogin(form -> form
                .permitAll()
            )
            // 5. 退出登录配置
            .logout(logout -> logout
                .permitAll()
            );
        
        return http.build();  // ⭐ 重要
    }
    
    // 6. 密码编码器
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}