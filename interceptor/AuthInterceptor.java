package com.stu212306158.helloserver.interceptor;

import com.stu212306158.helloserver.common.ResultCode;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.IOException;

/**
 * 自定义Token鉴权拦截器
 * 拦截所有请求，校验请求头中的Authorization
 */
public class AuthInterceptor implements HandlerInterceptor {

    /**
     * 前置拦截：请求进入Controller之前执行
     * return true：放行  return false：拦截
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        // 1. 从请求头获取Token
        String token = request.getHeader("Authorization");

        // 2. Token为空，拦截并返回JSON错误
        if (token == null || token.isEmpty()) {
            // 设置响应格式为JSON、编码UTF-8
            response.setContentType("application/json;charset=UTF-8");
            // 拼接JSON字符串返回前端
            String json = "{\"code\":" + ResultCode.TOKEN_INVALID.getCode()
                    + ",\"msg\":\"" + ResultCode.TOKEN_INVALID.getMsg() + "\"}";
            response.getWriter().write(json);
            return false;
        }
        // 3. Token存在，放行
        return true;
    }
}