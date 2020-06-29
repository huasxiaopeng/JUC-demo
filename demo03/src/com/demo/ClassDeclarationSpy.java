package com.demo;

import java.lang.reflect.TypeVariable;

/**
 * @ClassName ClassDeclarationSpy
 * @Description 02 Examining Class Modifiers and Types检查类修饰符与类型
 * @Author lktbz
 * @Date 2020/6/28
 */
public class ClassDeclarationSpy {
    public static void main(String[] args) throws ClassNotFoundException {
        Class<?> aClass = Class.forName("com.demo.Person");
        //返回类路径
        String canonicalName = aClass.getCanonicalName();
        System.out.println(canonicalName);
        //返回java 语言修饰符
        int modifiers = aClass.getModifiers();
        System.out.println(modifiers);

        TypeVariable<? extends Class<?>>[] typeParameters = aClass.getTypeParameters();
        System.out.println(typeParameters);

    }
}
