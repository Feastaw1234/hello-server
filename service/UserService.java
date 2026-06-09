package com.stu212306158.helloserver.service;

import com.stu212306158.helloserver.common.Result;
import com.stu212306158.helloserver.dto.UserDTO;

public interface UserService {
    // 注册
    Result<String> register(UserDTO userDTO);
    // 登录
    Result<String> login(UserDTO userDTO);
}