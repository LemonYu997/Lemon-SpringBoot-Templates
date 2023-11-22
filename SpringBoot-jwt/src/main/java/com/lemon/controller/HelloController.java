package com.lemon.controller;

import com.lemon.domain.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class HelloController {
    @GetMapping("/hello")
    public String hello(HttpServletRequest request) {
        //从请求中获取当前用户
        User user = (User) request.getAttribute("user");

        return "hello" + user.getUsername();
    }
}
