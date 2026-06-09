package com.stu212306158.helloserver.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(cors -> cors.disable())
                .csrf(csrf -> csrf.disable()) // 关闭CSRF（前后端分离必备）
                // 设置无状态会话（不使用Session）
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                // 接口权限规则
                .authorizeHttpRequests(auth -> auth
                        // 放行：注册、登录接口
                        .requestMatchers(org.springframework.http.HttpMethod.POST, "/api/users").permitAll()
                        .requestMatchers(org.springframework.http.HttpMethod.POST, "/api/users/login").permitAll()
                        // 其余所有接口必须认证
                        .anyRequest().authenticated()
                )
                // 关闭默认登录页、httpBasic认证
                .formLogin(form -> form.disable())
                .httpBasic(basic -> basic.disable());

        return http.build();
    }
}