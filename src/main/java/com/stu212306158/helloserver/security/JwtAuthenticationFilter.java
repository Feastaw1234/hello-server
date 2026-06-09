package com.stu212306158.helloserver.security;

import com.stu212306158.helloserver.entity.User;
import com.stu212306158.helloserver.mapper.UserMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserMapper userMapper;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        // 1. 获取请求头 Token
        String authHeader = request.getHeader("Authorization");
        // 无Token 或 非Bearer格式，直接放行
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        // 截取真实Token
        String jwt = authHeader.substring(7);
        String username;
        try {
            username = jwtUtil.extractUsername(jwt);
        } catch (Exception e) {
            // Token解析失败，直接放行
            filterChain.doFilter(request, response);
            return;
        }

        // 解析成功，存入Security上下文
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            User user = userMapper.selectOne(
                    com.baomidou.mybatisplus.core.conditions.query.LambdaQueryChainWrapper
                            .lambdaQuery(userMapper).eq(User::getUsername, username).getWrapper()
            );
            UsernamePasswordAuthenticationToken authToken =
                    new UsernamePasswordAuthenticationToken(user, null, null);
            SecurityContextHolder.getContext().setAuthentication(authToken);
        }
        filterChain.doFilter(request, response);
    }
}