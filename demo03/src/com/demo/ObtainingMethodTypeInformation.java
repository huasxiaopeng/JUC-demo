package com.demo;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.lang.reflect.TypeVariable;

/**
 * @ClassName MethodsDemo
 * @Description 06 获取方法类型信息
 * @Author lktbz
 * @Date 2020/6/28
 */
public class ObtainingMethodTypeInformation {
    /**
     * 定义方法
     * @param <E>
     * @throws E
     */
    <E extends RuntimeException> void genericThrow() throws E {}

    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException {
        Class<?> aClass = Class.forName("com.demo.ObtainingMethodTypeInformation");
        Method[] declaredMethods = aClass.getDeclaredMethods();
//        for (Method method:declaredMethods) {
//            System.out.println("lala-->"+method.toGenericString());
//            System.out.println("++++++++++++++++++++++++++");
//            System.out.println("bb-->"+method.getReturnType());
//            System.out.println("++++++++++++++++++++++++++");
//            System.out.println("vv-->"+method.getGenericReturnType());
//            String name = method.getName();
//            System.out.println(name);
//        }
        Method genericThrow = aClass.getMethod("genericThrow", ObtainingMethodTypeInformation.class);
        TypeVariable<Method>[] typeParameters = genericThrow.getTypeParameters();
        Parameter[] parameters = genericThrow.getParameters();

    }
}
