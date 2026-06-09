package com.stu212306158.helloserver.entity;

/*
 * 基础用户实体类
 */
public class User {
    // 用户ID
    private Long id;
    // 用户名
    private String name;
    // 年龄
    private Integer age;

    // 无参构造方法（Spring 反射实例化必须）
    public User() {}

    // 全参构造方法（方便手动创建对象）
    public User(Long id, String name, Integer age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    // Getter & Setter：属性的读写方法，@RequestBody 依赖此方法
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Integer getAge() {
        return age;
    }
    public void setAge(Integer age) {
        this.age = age;
    }
}