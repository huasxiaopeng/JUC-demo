package com.demo01;

/**
 * @ClassName Test04
 * @Description TODO
 * @Author lktbz
 * @Date 2020/5/29
 */
public class Test04  extends AccessModifiers03.TestB {
    public static void main(String[] args) {
        new AccessModifiers03.TestB().methodProtected();
        new Test04().methodProtected();
    }
}
