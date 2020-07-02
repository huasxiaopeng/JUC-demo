package com.test.demo;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;


/**
 * @ClassName Demo03
 * @Description 异常
 * @Author lktbz
 * @Date 2020/7/2
 */
public class Demo03 {
    @Test
    public void test01(){
        List<Object> list = new ArrayList<>();
        IndexOutOfBoundsException indexOutOfBoundsException =
                assertThrows(IndexOutOfBoundsException.class, () -> list.add(1, new Object()));
        assertEquals("index: 1,size: 0",indexOutOfBoundsException.getMessage());
        assertTrue(list.isEmpty());
    }
    /**
     * 使用注解方式
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void test02(){
        new ArrayList<>().get(0);
    }
}
