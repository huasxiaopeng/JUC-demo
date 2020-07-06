package com.io.demo;

import java.io.*;

/**
 * @ClassName Demo01
 * @Description 字节流
 * @Author lktbz
 * @Date 2020/7/5
 */
public class Demo01 {
    public static void main(String[] args) {
        FileInputStream in=null;
        FileOutputStream out=null;
        File file=new File("F:"+File.separator+"a.txt");
        File file1=new File("F:"+File.separator+"b.txt");

        try {
            in=new FileInputStream(file);
            out=new FileOutputStream(file1);
            int c=0;
            while ((c=in.read())!=-1){
                out.write(c);
            }
            System.out.println("读取完毕");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
