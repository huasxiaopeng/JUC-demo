package com.all.io.study;

import java.io.*;

/**
 * @ClassName StreamsDemo
 * @Description TODO
 * @Author lktbz
 * @Date 2020/7/6
 */
public class StreamsDemo2 {
    public static void main(String[] args) throws IOException {
        //输入流
        InputStream input = new FileInputStream("c:\\data\\input-file.txt");
        int data = input.read();
        while(data != -1){
            data = input.read();
        }
        //输出流
        OutputStream output = new FileOutputStream("c:\\data\\output-file.txt");
        output.write("Hello World".getBytes());
        output.close();


        //字符流
        InputStream inputs = new BufferedInputStream(
                new FileInputStream("c:\\data\\input-file.txt"));


    }
}
