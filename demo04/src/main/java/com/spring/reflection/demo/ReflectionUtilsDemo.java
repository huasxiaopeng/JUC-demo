package com.spring.reflection.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.util.ReflectionUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedType;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.springframework.util.ReflectionUtils.doWithFields;

/**
 * @ClassName ReflectionUtilsDemo
 * @Description spring  反射支持
 * @Author lktbz
 * @Date 2020/6/28
 */
public class ReflectionUtilsDemo {

    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchFieldException {
        /**
         * 强制反射，创建构造器
         */
        Class<User> userClass = User.class;
        Constructor<User> userConstructor = ReflectionUtils.accessibleConstructor(userClass);

        User user = userConstructor.newInstance();
        user.setName("zs");
        user.setAddress("北京");
        user.setAge(18);
        System.out.println(user);

        /**
         * 获取注解
         */
        Constructor<?>[] constructors = userClass.getConstructors();

        for (Constructor c:constructors
             ) {

            Lktbz declaredAnnotation = c.getDeclaredAnnotation(Lktbz.class);
            if(declaredAnnotation!=null){
                String value = declaredAnnotation.value();
                System.out.println("获取指定的注解的值"+value);
            }
            AnnotatedType annotatedReceiverType = c.getAnnotatedReceiverType();
            System.out.println("注解接收的类型"+annotatedReceiverType.getType());
            AnnotatedType annotatedReturnType = c.getAnnotatedReturnType();
            System.out.println("注解返回的类型为"+annotatedReturnType.getType());
            Class declaringClass = c.getDeclaringClass();
            System.out.println(declaringClass.getDeclaredField("name"));
        }



        Method[] declaredMethods = ReflectionUtils.getDeclaredMethods(User.class);
        for (Method method:declaredMethods) {

            String name = method.getName();
            System.out.println("方法名字为"+name);
            Class<?> returnType = method.getReturnType();
            System.out.println("返回值类型为"+returnType);
        }
        //ReflectionUtils.clearCache();
//        ReflectionUtils.doWithFields();

       doWithFields(User.class,(s)-> System.out.println("获取的字段名字为"+s.getName()));
       doWithFields(User.class,(s)->{
           if(s.getGenericType().getTypeName().equals("java.lang.Integer")){
               System.out.println("查询int类型的成员属性："+s.getName());
           }
       } );

    }
}
