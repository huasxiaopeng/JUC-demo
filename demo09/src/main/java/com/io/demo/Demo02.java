package com.io.demo;

import org.junit.Test;

import java.io.*;
import java.util.Locale;
import java.util.Scanner;

/**
 * @ClassName Demo02
 * @Description 字符流
 * @Author lktbz
 * @Date 2020/7/5
 */
public class Demo02 {
    /**
     * 一个一个字符的读取
     */
    @Test
    public void demo01(){
        FileReader reader=null;
        FileWriter writer=null;
        File file=new File("F:"+File.separator+"a.txt");
        File file1=new File("F:"+File.separator+"b.txt");
        try {
            reader=new FileReader(file);
            writer=new FileWriter(file1);
            int c;
            while ((c=reader.read())!=-1){
                writer.write(c);

            }
            System.out.println("读取完毕");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    /**
     * 读取一行
     * 使用打印流
     */
    @Test
    public void test02() throws IOException {
        BufferedReader reader=null;
        PrintWriter writer=null;
        File file=new File("F:"+File.separator+"a.txt");
        File file1=new File("F:"+File.separator+"b.txt");
        //存储信息
        reader=new BufferedReader(new FileReader(file));
        writer=new PrintWriter(new FileWriter(file1));
        String l;
        while((l=reader.readLine())!=null){
            writer.println(l);
            //需要刷新，不然没效果
            writer.flush();
        }
    }

    /**
     * scanner formatting
     * @throws IOException
     */
    @Test
    public void test03() throws IOException {
        Scanner scanner=null;
        scanner=new Scanner(new BufferedReader(
                new FileReader(
                      "F:"+File.separator+"c.txt")));
        while(scanner.hasNext()){
            System.out.println(scanner.next());
        }
    }
    /**
     * scanner formatting
     * @throws IOException
     */
    @Test
    public void test04() throws IOException {
        Scanner scanner=null;
        scanner=new Scanner(new BufferedReader(
                new FileReader(
                        "F:"+File.separator+"d.txt")))
                //添加截取
                .useDelimiter(",");
        while(scanner.hasNext()){
            System.out.println(scanner.next());
        }
    }

    /**
     *
     * @throws IOException
     */
    @Test
    public void test05() throws IOException {
        Scanner s = null;
        double sum = 0;
        s=new Scanner(new BufferedReader(new FileReader("F:"+File.separator+"e.txt")));
       // s.useLocale(Locale.CHINA);1032778.74159
        s.useLocale(Locale.US);
        while (s.hasNext()){
            if(s.hasNextDouble()){
                sum+=s.nextDouble();
            }else {
                s.next();
            }
        }
        System.out.println(sum);
    }
    /**
     * 打印流的格式化
     */
    @Test
    public void test06() throws IOException {
        int i = 2;
        double r = Math.sqrt(i);

        System.out.print("The square root of ");
        System.out.print(i);
        System.out.print(" is ");
        System.out.print(r);
        System.out.println(".");

        i = 5;
        r = Math.sqrt(i);
        System.out.println("The square root of " + i + " is " + r + ".");
        System.out.println("使用格式化。。。。");
        System.out.printf("printf..The square root of %d is %f\n",i,r);
        System.out.format("format ..The square root of %d is %f.%n", i, r);


    }
}
