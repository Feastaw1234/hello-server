package com.stu212306158.helloserver.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.stu212306158.helloserver.common.Result;
import com.stu212306158.helloserver.common.ResultCode;
import com.stu212306158.helloserver.dto.UserDTO;
import com.stu212306158.helloserver.entity.User;
import com.stu212306158.helloserver.mapper.UserMapper;
import com.stu212306158.helloserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    // 自动注入Mapper（操作数据库）
    @Autowired
    private UserMapper userMapper;

    /**
     * 用户注册
     */
    @Override
    public Result<String> register(UserDTO userDTO) {
        // 1. 构建查询条件：根据用户名查询
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUsername, userDTO.getUsername());

        // 2. 查询数据库，判断用户名是否已存在
        User dbUser = userMapper.selectOne(queryWrapper);
        if (dbUser != null) {
            return Result.error(ResultCode.USER_HAS_EXISTED);
        }

        // 3. DTO转Entity（封装数据库实体）
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());

        // 4. 插入数据到数据库
        userMapper.insert(user);
        return Result.success("注册成功!");
    }

    /**
     * 用户登录
     */
    @Override
    public Result<String> login(UserDTO userDTO) {
        // 1. 根据用户名查询数据库
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUsername, userDTO.getUsername());
        User dbUser = userMapper.selectOne(queryWrapper);

        // 2. 判断用户是否存在
        if (dbUser == null) {
            return Result.error(ResultCode.USER_NOT_EXIST);
        }

        // 3. 判断密码是否匹配
        if (!dbUser.getPassword().equals(userDTO.getPassword())) {
            return Result.error(ResultCode.PASSWORD_ERROR);
        }

        return Result.success("登录成功");
    }

    /**
     * 根据ID查询用户（新增接口）
     */
    @Override
    public Result<String> getUserById(Long id) {
        // 根据主键ID查询
        User user = userMapper.selectById(id);
        if (user == null) {
            return Result.error(ResultCode.ERROR);
        }
        String data = "查询成功,正在返回ID为" + id + "的用户信息";
        return Result.success(data);
    }
    @Override
    public Result<Object> getUserPage(Integer pageNum, Integer pageSize) {
        // 1. 创建分页对象：当前页、每页条数
        Page<User> pageParam = new Page<>(pageNum, pageSize);
        // 2. 执行分页查询（null = 无条件查询全部）
        Page<User> resultPage = userMapper.selectPage(pageParam, null);
        // 3. 封装分页结果返回
        return Result.success(resultPage);
    }
}