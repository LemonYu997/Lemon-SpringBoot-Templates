package com.lemon.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.lemon.domain.User;
import com.lemon.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl {
    private final UserMapper userMapper;

    public User getByUserName(String username) {
        LambdaQueryWrapper<User> qw = new LambdaQueryWrapper<>();
        qw.eq(User::getUsername, username);
        //根据用户名获取用户信息
        return userMapper.selectOne(qw);
    }

    public boolean validatePassword(User user, String password) {
        if (user == null) {
            return false;
        }
        return user.getPassword().equals(password);
    }
}
