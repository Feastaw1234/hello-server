package com.stu212306158.helloserver.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.stu.helloserver.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * MyBatis-Plus Mapper接口
 * 继承 BaseMapper 自动获得单表CRUD方法
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
    // 无需手写SQL，BaseMapper已内置 insert/select/delete/update
}