package com.apache.commons.io;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.junit.Test;

import java.io.*;
import java.nio.charset.Charset;
import java.util.List;

/**
 * @ClassName Demo01
 * @Description TODO
 * @Author lktbz
 * @Date 2020/7/7
 */
public class Demo01 {
    /**
     * 创建流的方式  IOUtils.buffer(ins);
     * @throws IOException
     */
    @Test
    public void test01() throws IOException {
        InputStream ins=new FileInputStream("E:"+ File.separator+"a.txt");
        BufferedInputStream in = IOUtils.buffer(ins);
        BufferedOutputStream out = IOUtils.buffer(new FileOutputStream("E:" + File.separator + "b.txt"));
        int length=0;
        byte b[]=new byte[4096];
       while((length=in.read())!=-1){
            out.write(b,0,length);
       }
       out.flush();
    }

    /**
     *   读取的抽象
     * @throws IOException
     */
    @Test
    public void test02() throws IOException {
        InputStream ins=new FileInputStream("F:"+ File.separator+"a.txt");
        OutputStream out=new FileOutputStream("F:"+ File.separator+"b.txt");
         IOUtils.copy(ins, out);
    }

    /**
     *  字节流变成字符流
     * @throws IOException
     */
     @Test
    public void test03() throws IOException {
        InputStream ins=new FileInputStream("F:"+ File.separator+"a.txt");
        FileWriter fileWriter=new FileWriter("F:"+ File.separator+"b.txt");
        IOUtils.copy(ins, fileWriter, Charset.forName("utf-8"));
        fileWriter.flush();

    }

    /**
     * 读取内容到字节数组中
     * @throws IOException
     */
    @Test
    public void test04() throws IOException {
        InputStream inputStream=new FileInputStream("F:"+ File.separator+"a.txt");
        byte[]b=new byte[1024];
        PrintWriter writer=new PrintWriter(new FileOutputStream("F:"+ File.separator+"b.txt"));
        int length=0;
        IOUtils.read(inputStream,b);
        System.out.println(new String(b));
        //读取指定长度
        b=new byte[1111];
        InputStream inputStream1 = IOUtils.toInputStream("hello world");
        IOUtils.read(inputStream1,b,2,5);
        System.out.println(new String(b));
    }

    /**
     * 读取一行
     * @throws IOException
     */
    @Test
    public void test05() throws IOException {
        InputStream inputStream=new FileInputStream("F:"+ File.separator+"a.txt");
        List<String> strings = IOUtils.readLines(inputStream, Charset.forName("utf-8"));
        strings.forEach(System.out::println);
    }

    /**
     * 直接通过字节数组
     * @throws IOException
     */
    @Test
    public void test06() throws IOException {
        byte[] bytes = IOUtils.toByteArray(new FileInputStream("F:" + File.separator + "a.txt"));
       if(bytes.length!=0){
           OutputStream outputStream=new FileOutputStream("F:" + File.separator + "b.txt");
           IOUtils.write(bytes,outputStream);
           outputStream.flush();
       }

    }

}
