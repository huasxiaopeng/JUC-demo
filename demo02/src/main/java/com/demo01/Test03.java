package com.demo01;

/**
 * @ClassName Test03
 * @Description 访问私有方法
 * @Author lktbz
 * @Date 2020/5/29
 */
public class Test03 {
    public static void main(String[] args) {
        //不在同一个报下访问不到private
        new AccessModifiers03.TestA().methodProtected();
    }
}
