package com.stu212306158.helloserver;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// 项目启动入口
@SpringBootApplication
// 扫描mapper包下所有接口，必须配置，否则Mapper无法注入
@MapperScan("com.stu.helloserver.mapper")
public class HelloServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(HelloServerApplication.class, args);
    }
}