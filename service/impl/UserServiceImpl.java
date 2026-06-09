package com.stu212306158.helloserver.service.impl;

import com.stu212306158.helloserver.common.Result;
import com.stu212306158.helloserver.common.ResultCode;
import com.stu212306158.helloserver.dto.UserDTO;
import com.stu212306158.helloserver.service.UserService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

// @Service：将当前类交给Spring容器管理
@Service
public class UserServiceImpl implements UserService {
    // 内存Map 模拟数据库（key=用户名，value=密码）
    private static final Map<String, String> userDb = new HashMap<>();

    @Override
    public Result<String> register(UserDTO userDTO) {
        // 判断用户名是否已存在
        if (userDb.containsKey(userDTO.getUsername())) {
            return Result.error(ResultCode.USER_HAS_EXISTED);
        }
        // 存入模拟数据库
        userDb.put(userDTO.getUsername(), userDTO.getPassword());
        return Result.success("注册成功");
    }

    @Override
    public Result<String> login(UserDTO userDTO) {
        // 判断用户是否存在
        if (!userDb.containsKey(userDTO.getUsername())) {
            return Result.error(ResultCode.USER_NOT_EXIST);
        }
        // 判断密码是否正确
        String dbPwd = userDb.get(userDTO.getUsername());
        if (!dbPwd.equals(userDTO.getPassword())) {
            return Result.error(ResultCode.PASSWORD_ERROR);
        }
        return Result.success("登录成功");
    }
}