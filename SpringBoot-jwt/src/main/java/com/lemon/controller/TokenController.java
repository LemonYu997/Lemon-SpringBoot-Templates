package com.lemon.controller;

import com.lemon.domain.User;
import com.lemon.service.UserServiceImpl;
import com.lemon.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
public class TokenController {
    private final JwtUtil jwtUtil;
    private final RedisTemplate<String, String> redisTemplate;
    private final UserServiceImpl userService;

    @PostMapping("/login")
    public String login(@RequestBody User loginUser) {
        if (loginUser.getUsername() == null || loginUser.getPassword() == null) {
            return "用户名或密码不正确";
        }
        User user = userService.getByUserName(loginUser.getUsername());
        if (userService.validatePassword(user, loginUser.getPassword())) {
            //生成token
            String token = jwtUtil.generateToken(user);
            //存储到redis中，key为用户id
            redisTemplate.opsForValue().set(String.valueOf(user.getUserId()), token);

            //返回token
            return token;
        } else {
            return "密码验证失败";
        }
    }

    //token校验
    @PostMapping("/validate")
    public String validate(String token) {
        if (token == null || token.trim().equals("")) {
            return "token为空";
        }
        if (jwtUtil.validateToken(token)) {
            return "token校验成功";
        } else {
            return "token校验失败";
        }
    }
}
