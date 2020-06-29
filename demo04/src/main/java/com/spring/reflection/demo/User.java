package com.spring.reflection.demo;

import org.springframework.stereotype.Service;

/**
 * @ClassName User
 * @Description TODO
 * @Author lktbz
 * @Date 2020/6/28
 */
@Service(value = "zs")
public class User {
    @Lktbz(value = "yan")
    private String name;
    private String address;
    private Integer age;
    private String lktbz="peng";
    public User() {
    }
    @Lktbz(value = "这是构造函数")
    public User(String name, String address, Integer age) {
        this.name = name;
        this.address = address;
        this.age = age;
    }
    public String getName() {
        return name;
    }
    @Lktbz(value = "zxiaojing")
    public void setName(String name) {
        this.name = name;
    }
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", age=" + age +
                '}';
    }
}
