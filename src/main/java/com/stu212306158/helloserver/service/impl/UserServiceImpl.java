package com.stu212306158.helloserver.service.impl;

import cn.hutool.core.util.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.stu212306158.helloserver.common.Result;
import com.stu212306158.helloserver.common.ResultCode;
import com.stu212306158.helloserver.dto.UserDTO;
import com.stu212306158.helloserver.entity.User;
import com.stu212306158.helloserver.entity.UserInfo;
import com.stu212306158.helloserver.mapper.UserInfoMapper;
import com.stu212306158.helloserver.mapper.UserMapper;
import com.stu212306158.helloserver.service.UserService;
import com.stu212306158.helloserver.vo.UserDetailVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.TimeUnit;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserInfoMapper userInfoMapper;
    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    private static final String CACHE_KEY_PREFIX = "user:detail:";

    @Override
    public Result<String> login(UserDTO userDTO) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUsername, userDTO.getUsername());
        User dbUser = userMapper.selectOne(queryWrapper);

        if (dbUser == null) {
            return Result.error(ResultCode.USER_NOT_EXIST);
        }
        if (!dbUser.getPassword().equals(userDTO.getPassword())) {
            return Result.error(ResultCode.PASSWORD_ERROR);
        }

        // 登录成功，生成JWT返回
        String token = jwtUtil.generateToken(userDTO.getUsername());
        return Result.success(token);
    }
    @Override
    @Transactional
    public Result<String> updateUserInfo(Long userId, UserInfo userInfo) {
        userInfo.setUserId(userId);
        int rows = userMapper.updateById(userInfo);
        stringRedisTemplate.delete(CACHE_KEY_PREFIX + userId);
        return rows > 0 ? Result.success("更新成功") : Result.error(ResultCode.ERROR);
    }

    @Override
    @Transactional
    public Result<String> deleteUser(Long userId) {
        userMapper.deleteById(userId);
        stringRedisTemplate.delete(CACHE_KEY_PREFIX + userId);
        return Result.success("删除成功");
    }
}