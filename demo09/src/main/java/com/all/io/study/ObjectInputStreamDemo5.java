package com.all.io.study;

import java.io.*;

/**
 * @ClassName ObjectInputStreamDemo5
 * @Description TODO
 * @Author lktbz
 * @Date 2020/7/6
 */
public class ObjectInputStreamDemo5 {

    public static class Person implements Serializable {
        public String name = null;
        public int    age  =   0;
    }

    /**
     * 序列化对象
     * @param args
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ObjectOutputStream out=new ObjectOutputStream(new FileOutputStream("F:"+ File.separator+"a.txt"));
        Person person = new Person();
        person.name = "Jakob Jenkov";
        person.age  = 40;
        out.writeObject(person);
        out.close();
        ObjectInputStream in=new ObjectInputStream(new FileInputStream("F:"+ File.separator+"a.txt"));
        Person p= (Person)in.readObject();
         in.close();
        System.out.println(p.name);
        System.out.println(p.age);


    }
}
