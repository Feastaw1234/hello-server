package com.stu212306158.helloserver.service;

import com.stu212306158.helloserver.common.Result;
import com.stu212306158.helloserver.dto.UserDTO;

public interface UserService {
    Result<String> register(UserDTO userDTO);
    Result<String> login(UserDTO userDTO);
    Result<String> getUserById(Long id);
    // 新增：分页查询用户列表
    Result<Object> getUserPage(Integer pageNum, Integer pageSize);
}