package com.lemon.config;

import com.lemon.domain.User;
import com.lemon.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 校验拦截器
 */
@Component
public class JwtInterceptor implements HandlerInterceptor {
    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //从请求头中获取token
        String token = request.getHeader("Authorization");

        if (token != null && jwtUtil.validateToken(token)) {
            //校验通过，将token解析出的用户信息存放到request中，以便后续使用
            User user = jwtUtil.getUserFromTokne(token);
            request.setAttribute("user", user);
            return true;
        } else {
            //校验失败，返回错误信息
            response.getWriter().write("token is error");
            return false;
        }
    }
}
