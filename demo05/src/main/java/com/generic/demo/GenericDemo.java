package com.generic.demo;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.function.Consumer;

/**
 * @ClassName GenericDemo
 * @Description 泛型参数 01
 * @Author lktbz
 * @Date 2020/6/29
 */
public class GenericDemo {
    public static void main(String[] args) {
//            demo1();
              demo2();

    }
    private static void demo2(){
        ContainerExtend<String> aString=new ContainerExtend<String>("zs");
        System.out.println(aString.element);
        //inter 不是 CharSequence的子类，所以报错，
//        ContainerExtend<Integer> aInter=new ContainerExtend<Integer>(20);
//        System.out.println(aInter.element);
        ContainerExtend<StringBuffer>sn=new ContainerExtend<StringBuffer>(new StringBuffer());
        //传递string 一般以为会报错，其实不会报错 *******
        //是写法上的瑕疵，跟语法没关系
        ContainerExtend<StringBuffer>s2n=new ContainerExtend("Helloworld");
        System.out.println(s2n.getElement());
    }
    /**
     * 基本泛型约束
     */
    private  static void demo1(){
        Container<String> aString=new Container<String>("zs");
        System.out.println(aString.element);
        Container<Integer> aInter=new Container<Integer>(20);
        System.out.println(aInter.element);


    }

    //定义了一个泛型
    public  static  class Container<E>{
        private E element;
        public Container(E element) {
            this.element = element;
        }
    }
    //单界限  定义了E 元素的上界线，不能超过 CharSequence
    public  static  class ContainerExtend<E extends  CharSequence>{
        private E element;
        public ContainerExtend(E element) {
            this.element = element;
        }

        public E getElement() {
            return element;
        }

        public void setElement(E element) {
            this.element = element;
        }
    }

    /**
     * 多继承
     */
    public  static  class A{}
    public interface I{}
    public interface I2{}
    public static  class demo03 <T extends A & I &I2> {

    }
    //调换顺序
//    public static class Demo04<T extends  I & A>{}
    public static class Demo05<T extends  I & I2>{}
    //从上面的例子可以发现，泛型的多继承第一个继承的可以是类或者接口，第二个参数必须是接口

   //方法泛型设计
    public static void add(Collection<String> target,String elemet){
        target.add(elemet);
    }
    //通过上面的可以看到，方法不够通用进行泛型抽取
    public static <E> void add1(Collection<E> target,E elemet){
        target.add(elemet);
    }
    //看到上面的这个，只是进行了泛型的抽取，约束性不是太强
    public static <E extends String> void add2(Collection<E> target,E  elemet){
        target.add(elemet);
     }
     //在传递的参数那进行约束，发现，并不能进行约束，
    // 只能提前到方法处去写，同时发现这样去写，发现，参数，不简写，并且，我想对target 进行泛型限定
    //所以继续变换
     public static <C extends  Collection<E> ,E extends String> void add3(C target,E  elemet){
         target.add(elemet);
     }

    //上面就是最完美的通用泛型


    /**
     * ？ 类型匹配
     */
    private static void upperBoundedWildcards(List<Number> numbers) {
        // 泛型上界通配配型
        // Number -> Byte, Short , Integer, Long
        numbers.add(Byte.valueOf((byte) 1));
        numbers.add(Short.valueOf((short) 2));
        numbers.add(Integer.valueOf(3));
        numbers.add(Long.valueOf(4L));

        List<Byte> bytes = Arrays.asList((byte) 5);
        List<Short> shorts = Arrays.asList((short) 6);
        List<Integer> integers = Arrays.asList(7);

        numbers.addAll(bytes); // ? extends Number; List<Byte>
        numbers.addAll(shorts); // ? extends Number; List<Short>
        numbers.addAll(integers); // ? extends Number; List<Integer>

        // 被操作（处理）的对象，需要更为抽象类型，Number
        // 待整合（输入）的对象，可以是具体类型

        upperBoundedWildcardsDemo(numbers, System.out::println);
    }
    public static void upperBoundedWildcardsDemo(Iterable<? extends Number> iterable, Consumer<Object> consumer) {
        for (Object e : iterable) {
            consumer.accept(e);
        }

    }

    private static void lowerBoundedWildcards(List<Number> numbers) {
        lowerBoundedWildcardsDemo(numbers, numbers);
    }

    private static void lowerBoundedWildcardsDemo(List<? extends Number> producer, List<? super Number> consumer) {
        //List<? extends Number> producer 与List<? super Number> consumer 使用技巧 effect in java
        // PECS stands for producer-extends, consumer-super.
        // 读取数据（生产者）使用 extends
        for (Number number : producer) {

        }
        // 操作输出（消费者）使用 super
        consumer.add(1);
        consumer.add((short) 2);
    }
}
