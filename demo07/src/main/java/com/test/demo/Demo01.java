package com.test.demo;

import org.hamcrest.core.CombinableMatcher;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

/**
 * @ClassName Demo01
 * @Description junit 学习
 * @Author lktbz
 * @Date 2020/7/2
 */
public class Demo01 {
    /**
     *JUnit provides overloaded assertion methods for all primitive types and Objects and arrays
     * (of primitives or Objects).
     *  The parameter order is expected value followed by actual value.
     */
    @Test
    public void demo01(){
        byte[] expected = "trial".getBytes();
        byte[] actual = "trial".getBytes();
        //判断数组是否相等
        assertArrayEquals(expected,actual);
    }

    @Test
    public void demo02(){
        assertEquals("text","text");
        Assert.assertEquals("zs","lis");
    }

    @Test
    public void demo03(){
        Assert.assertFalse("zs",(1<0));
    }
    @Test
    public void demo04(){
        Assert.assertFalse("zs",(1>0));
    }

    @Test
    public void demo05(){
        Assert.assertNotNull("lk",new Object());
    }
    @Test
    public void demo06(){
        Assert.assertNotNull("lk",null);
    }
    @Test
    public void demo07(){
        Assert.assertNotSame("lk",new Object(),new Object());
    }
    @Test
    public void demo08(){
        Assert.assertNull("应该是空",null);
    }
    @Test
    public void demo09(){
        Assert.assertNull("应该是空",new Object());
    }
    @Test
    public void demo10(){
        Integer aNumber = Integer.valueOf(768);
        Assert.assertSame("应该相等",aNumber,aNumber);
    }

    /**
     *  Assert.assertThat 需要一些匹配表达式
     */
    @Test
    public void demo11(){
       Assert.assertThat(Arrays.asList("one","two","three"),hasItems("one","three"));
    }

    @Test
    public void demo12(){
        Assert.assertThat("zabc",both(containsString("a")).and(containsString("b")));
    }
    @Test
    public void demo13(){
        Assert.assertThat(Arrays.asList(new String[] { "fun", "ban", "net" }), everyItem(containsString("n")));
    }
    @Test
    public void demo14(){
        Assert.assertThat("good", allOf(equalTo("good"), startsWith("good")));
        Assert.assertThat("good", not(allOf(equalTo("bad"), equalTo("good"))));
        Assert.assertThat("good", anyOf(equalTo("bad"), equalTo("good")));
        Assert.assertThat(7, not(CombinableMatcher.<Integer> either(equalTo(3)).or(equalTo(4))));
        Assert.assertThat(new Object(), not(sameInstance(new Object())));
    }

    @Test
    public void demo15() {
        Assert.assertTrue("failure - should be true", true);
    }
}
