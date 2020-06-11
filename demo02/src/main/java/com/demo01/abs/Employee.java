package com.demo01.abs;

/**
 * @ClassName Employee
 * @Description TODO
 * @Author lktbz
 * @Date 2020/5/29
 */
public class Employee  extends Person{
    public Employee(String nm, String gen) {
        super(nm, gen);
    }

    @Override
    public void work() {
        System.out.println("之类实行");
    }
}
