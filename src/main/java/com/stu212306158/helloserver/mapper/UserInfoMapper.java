package com.stu212306158.helloserver.mapper;


import com.stu212306158.helloserver.vo.UserDetailVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserInfoMapper {

    /**
     * 两表联查：sys_user 左连接 user_info
     */
    @Select("""
            SELECT u.id AS userId, u.username, i.real_name AS realName, i.phone, i.address
            FROM sys_user u LEFT JOIN user_info i ON u.id = i.user_id
            WHERE u.id = #{userId}
            """)
    UserDetailVO getUserDetail(@Param("userId") Long userId);
}