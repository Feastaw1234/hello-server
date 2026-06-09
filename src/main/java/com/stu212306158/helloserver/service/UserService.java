package com.stu212306158.helloserver.service;

import com.stu212306158.helloserver.common.Result;
import com.stu212306158.helloserver.dto.UserDTO;

public interface UserService {
    Result<String> register(UserDTO userDTO);
    Result<String> login(UserDTO userDTO);
    // 新增：根据ID查询用户
    Result<String> getUserById(Long id);
}