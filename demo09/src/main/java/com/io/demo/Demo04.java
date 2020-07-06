package com.io.demo;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import java.io.*;

/**
 * @ClassName Demo04
 * @Description Data Streams  官方例子
 * @Author lktbz
 * @Date 2020/7/6
 */
public class Demo04 {

    static final String dataFile = "invoicedata";
    static final double[] prices = { 19.99, 9.99, 15.99, 3.99, 4.99 };
    static final int[] units = { 12, 8, 13, 29, 50 };
    static final String[] descs = {
            "Java T-shirt",
            "Java Mug",
            "Duke Juggling Dolls",
            "Java Pin",
            "Java Key Chain"
    };
    public static void main(String[] args) throws IOException {
        DataOutputStream outputStream=null;
        try {
           outputStream=new DataOutputStream(new BufferedOutputStream(new FileOutputStream(dataFile)));
            for (int i = 0; i <prices.length ; i++) {

                outputStream.writeDouble(prices[i]);
                outputStream.writeInt(units[i]);
                outputStream.writeUTF(descs[i]);
            }
            outputStream.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        DataInputStream in=new DataInputStream(new BufferedInputStream(new FileInputStream(dataFile)));
        double price=0;
        int unit=0;
        String desc;
        double total = 0.0;
        while(true){
                unit = in.readInt();
            desc = in.readUTF();
            System.out.format("You ordered %d" + " units of %s at $%.2f%n",
                    unit, desc, price);
            total += unit * price;
        }
    }
}
