package com.all.io.study;

import java.io.*;

/**
 * @ClassName DataOutputStreamDemo4
 * @Description TODO
 * @Author lktbz
 * @Date 2020/7/6
 */
public class DataOutputStreamDemo4 {

        public static void main(String[] args) throws IOException {
            DataOutputStream dataOutputStream =
                    new DataOutputStream(
                            new FileOutputStream("F:"+File.separator+"a.txt"));

            dataOutputStream.writeInt(123);
            dataOutputStream.writeFloat(123.45F);
            dataOutputStream.writeLong(789);

            dataOutputStream.close();

            DataInputStream dataInputStream =
                    new DataInputStream(
                            new FileInputStream("F:"+File.separator+"a.txt"));

            int   int123     = dataInputStream.readInt();
            float float12345 = dataInputStream.readFloat();
            long  long789    = dataInputStream.readLong();

            dataInputStream.close();

            System.out.println("int123     = " + int123);
            System.out.println("float12345 = " + float12345);
            System.out.println("long789    = " + long789);
        }

}
