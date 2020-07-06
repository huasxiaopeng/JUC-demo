package com.io.demo;

import java.io.*;

/**
 * @ClassName Demo05
 * @Description Datainputstream
 * @Author lktbz
 * @Date 2020/7/6
 */
public class Demo05 {
    public static void main(String[] args) throws IOException {
        DataOutputStream outputStream=new DataOutputStream
                (new FileOutputStream("F:"+ File.separator+"e.txt"));
        outputStream.writeDouble(20.00);
        outputStream.writeInt(1);
        outputStream.writeUTF("肖朋");
        outputStream.flush();
        System.out.println("写入成功。。。");
        outputStream.close();
        //循环的话，通过while 进行遍历
    }
}
