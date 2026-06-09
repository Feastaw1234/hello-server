package com.stu212306158.helloserver.controller;

import com.stu212306158.helloserver.common.Result;
import com.stu212306158.helloserver.dto.UserDTO;
import com.stu212306158.helloserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {
    // 自动注入业务层
    @Autowired
    private UserService userService;

    // 注册接口
    @PostMapping
    public Result<String> register(@RequestBody UserDTO userDTO) {
        return userService.register(userDTO);
    }

    // 登录接口
    @PostMapping("/login")
    public Result<String> login(@RequestBody UserDTO userDTO) {
        return userService.login(userDTO);
    }

    // 查询接口（受拦截器保护）
    @GetMapping("/{id}")
    public Result<String> getUser(@PathVariable Long id) {
        return Result.success("查询成功,ID:" + id);
    }
}