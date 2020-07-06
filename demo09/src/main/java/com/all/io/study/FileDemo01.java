package com.all.io.study;

import org.junit.Test;

import java.io.File;
import java.io.IOException;

/**
 * @ClassName FileDemo01
 * @Description TODO
 * @Author lktbz
 * @Date 2020/7/6
 */
public class FileDemo01 {
    /**
     * 文件的创建
     */
    @Test
    public void test01(){
        File file=new File("f:\\input-file.txt");
    }

    /**
     * 判断是否存在
     */
    @Test
    public void test02(){
        File file=new File("f:\\input-file.txt");
        System.out.println(file.exists());
    }
    /**
     * 目录不存在则创建
     */
    @Test
    public void test03(){
        File file=new File("f:\\data");
        boolean mkdir = file.mkdir();
        System.out.println(mkdir);
    }
    /**
     * 文件长度
     */
    @Test
    public void test04(){
        File file=new File("f:\\input-file.txt");
        System.out.println(file.length());
    }

    /**
     * 目录与文件删除
     */
    @Test
    public  static  void test05(){
        File dir=new File("f:\\input-file.txt");
        deleteDir(dir);

    }
    public static boolean deleteDir(File dir){
        File[] files = dir.listFiles();
        if(files != null){
            for(File file : files){
                if(file.isDirectory()){
                    deleteDir(file);
                } else {
                    file.delete();
                }
            }
        }
        return dir.delete();
    }


}
