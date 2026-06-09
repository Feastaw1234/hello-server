package com.stu212306158.helloserver.controller;

import com.stu212306158.helloserver.entity.User;
import org.springframework.web.bind.annotation.*;

// 接口控制器，返回JSON数据
@RestController
// 统一接口前缀：所有接口路径都以 /api/users 开头
@RequestMapping("/api/users")
public class UserController {

    /**
     * 1. GET 查询接口：根据ID查询用户
     * 路径：GET /api/users/{id}
     * @PathVariable("id") 接收URL中的路径参数
     */
    @GetMapping("/{id}")
    public String getUser(@PathVariable("id") Long id) {
        return "查询成功,正在返回ID为" + id + "的用户信息";
    }

    /**
     * 2. POST 新增接口：新增用户
     * @RequestBody 接收前端传递的JSON格式参数，自动封装为User对象
     */
    @PostMapping
    public String createUser(@RequestBody User user) {
        return "新增成功,接收到用户:" + user.getName() + ",年龄:" + user.getAge();
    }

    /**
     * 3. PUT 更新接口：根据ID更新用户
     */
    @PutMapping("/{id}")
    public String updateUser(@PathVariable("id") Long id, @RequestBody User user) {
        return "更新成功,ID" + id + "的用户已修改为:" + user.getName();
    }

    /**
     * 4. DELETE 删除接口：根据ID删除用户
     */
    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        return "删除成功,已移除ID为" + id + "的用户";
    }
}