package com.io.demo;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.InputStream;

/**
 * @ClassName Demo00
 * @Description io流的api
 * @Author lktbz
 * @Date 2020/7/6
 */
public class Demo00 {
    public static void main(String[] args) {
        /**
         * 输入流
         @see  java.io.BufferedInputStream
         @see  java.io.ByteArrayInputStream
         @see  java.io.DataInputStream
         @see java.io.FilterInputStream
         @see  java.io.InputStream#read()
         @see  java.io.PushbackInputStream
         */
        //用法参见demo01
        InputStream inputStream=null;

        ByteArrayInputStream bu=null;
        //用法参见demo04
        DataInputStream data=null;

        BufferedInputStream buf=null;

        /**
         * 输出流
         *   @see     java.io.BufferedOutputStream
         *   @see     java.io.ByteArrayOutputStream
         *   @see     java.io.DataOutputStream
         *   @see     java.io.FilterOutputStream
         *   @see     java.io.OutputStream#write(int)
         */


    }
}
