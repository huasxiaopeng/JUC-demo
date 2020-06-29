package com.demo;

import java.lang.reflect.Field;
import java.util.Arrays;

/**
 * @ClassName GettingandSettingFieldValues
 * @Description 获取字段的值与设置值  04
 * @Author lktbz
 * @Date 2020/6/28
 */
enum Tweedle { DEE, DUM }
public class GettingandSettingFieldValues {
    public long chapters = 2;
    public String[] characters = { "Alice", "White Rabbit" };
    public Tweedle twin = Tweedle.DEE;

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        GettingandSettingFieldValues get=new GettingandSettingFieldValues();
        Class<? extends GettingandSettingFieldValues> aClass = get.getClass();
        Field chapters = aClass.getDeclaredField("chapters");
        //字段值的获取
        long aLong = chapters.getLong(get);
        System.out.println(aLong);

        String[] newChars = { "Queen", "King" };
        Field characters = aClass.getDeclaredField("characters");
        //设置值
        characters.set(get,newChars);
        System.out.println(Arrays.asList(get.characters));

        Field t = aClass.getDeclaredField("twin");
        //设置并获取
        t.set(get,Tweedle.DUM);
        System.out.println(t.get(get));

    }
}
