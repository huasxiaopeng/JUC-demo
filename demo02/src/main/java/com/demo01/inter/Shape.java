package com.demo01.inter;

/**
 * @ClassName Shape
 * @Description TODO
 * @Author lktbz
 * @Date 2020/5/29
 */
public interface Shape {
    //implicitly public, static and final
    public String LABLE="Shape";

    //interface methods are implicitly abstract and public
    void draw();

    double getArea();
}
