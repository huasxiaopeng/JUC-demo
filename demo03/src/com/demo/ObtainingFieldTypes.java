package com.demo;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.List;

/**
 * @ClassName ObtainingFieldTypes
 * @Description 获取字段类型 03
 * @Author lktbz
 * @Date 2020/6/28
 */
public class ObtainingFieldTypes<T> {
    public boolean[][] b = {{ false, false }, { true, true } };
    public String name  = "Alice";
    public List<Integer> list;
    public T val;

    public static void main(String[] args) throws ClassNotFoundException, NoSuchFieldException {
        Class<?> aClass = Class.forName("com.demo.ObtainingFieldTypes");
        Field[] fields = aClass.getFields();
        Arrays.stream(fields).forEach(System.out::println);
        Field name = aClass.getField("name");

        System.out.println( Modifier.isPublic(name.getModifiers())); //判断修饰符
        System.out.println( name.getName());
        System.out.println( name.getGenericType());
        System.out.println( name.getType());



    }
}
