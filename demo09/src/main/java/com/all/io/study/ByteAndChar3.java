package com.all.io.study;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @ClassName ByteAndChar3
 * @Description 字节与字符
 * @Author lktbz
 * @Date 2020/7/6
 */
public class ByteAndChar3 {
    public static void main(String[] args) throws IOException {
        //字节处理方式
        byte[] bytes = new byte[1024];
        //write data into byte array...
        InputStream input = new ByteArrayInputStream(bytes);
        //read first byte
        int data = input.read();
        while(data != -1) {
            //do something with data
            //read next byte
            data = input.read();
        }

        //Writing to Arrays via OutputStream or Writer
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        //utf-8
        output.write("This text is converted to bytes".getBytes("UTF-8"));
        byte[] bytess = output.toByteArray();

    }
}
