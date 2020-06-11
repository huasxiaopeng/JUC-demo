package com.demo01;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @ClassName Demo01
 * @Description stream 01
 * @Author lktbz
 * @Date 2020/5/27
 */
public class Demo01 {
    /**
     * 这是求和的普通写法
     * @param lsit
     * @return
     */
 private  static  int sumIterator(List<Integer> lsit){
     Iterator<Integer> iterator = lsit.iterator();
     int sum=0;
     while(iterator.hasNext()){
         int num=iterator.next();
         if(num>10){
             sum+=num;
         }
     }
     return sum;
 }
    /**
     * strem api 一行搞定
     * @param lsit
     * @return
     */
 private static  int lambdaSumIterator(List<Integer> lsit){
        //过滤大于10，将i转换成int，最后求和
     return  lsit.stream().filter(i->i>10).mapToInt(i->i).sum();
 }

    public static void main(String[] args) {
        //创建stream
       // createStream();
//        stream的类型转换
        //convertingStreamToArrayAndList();
        //stream 流的中间操作
        //javaStreamIntermediateOperations();
        //stream 流中断操作
        javaStreamTerminalOperations();
    }

    private static void javaStreamTerminalOperations() {
//        Stream reduce() example: We can use reduce() to perform a reduction on the elements of the stream,
//        using an associative accumulation function, and return an Optional.
        Stream<Integer> numbers = Stream.of(1,2,3,4,5);
        Optional<Integer> reduce = numbers.reduce((i, j) -> {
            return i * j;
        });
        //点进去发现，加钱乘除，最大最小猪，等都可以使用此方法
        if(reduce.isPresent()) System.out.println(reduce.get());
        //Stream count() example
        Stream<Integer> numbers1 = Stream.of(1,2,3,4,5);
        System.out.println("Number of elements in stream="+numbers1.count()); //5
        //Stream forEach()
        Stream<Integer> numbers2 = Stream.of(1,2,3,4,5);
        numbers2.forEach(i->{
            System.out.println(i+" ");
        });
        //Stream match() examples:匹配
        Stream<Integer> numbers3 = Stream.of(1,2,3,4,5);
        //Stream contains 4? true
        System.out.println(" 是否匹配到值："+numbers3.anyMatch(i->i==4));

        //Stream contains all elements less than 10? true
        Stream<Integer> numbers4 = Stream.of(1,2,3,4,5);
        boolean b = numbers4.allMatch(i -> i < 10);
        System.out.println(" numbers4匹配的值是否都小于10："+b);
        Stream<Integer> numbers5 = Stream.of(1,2,3,4,5,10);
        boolean b2 = numbers5.allMatch(i -> i < 10);
        //Stream contains all elements less than 10? true
        System.out.println(" numbers5 匹配的值是否都小于10："+b2);
        Stream<Integer> numbers6 = Stream.of(1,2,3,4,5);
        //Stream doesn't contain 10? true
        System.out.println(" numbers5 匹配的值是否都等于10："+numbers6.noneMatch(i->i==10));

        Stream<String> names4 = Stream.of("Pankaj","Amit","David", "Lisa");
        Optional<String> firstNameWithD =   names4.filter(i->i.startsWith("D")).findFirst();
        if(firstNameWithD.isPresent()){
            System.out.println("First Name starting with D="+firstNameWithD.get()); //David
        }




    }

    private static void javaStreamIntermediateOperations() {
//        Stream filter测试并筛选过滤后的元素内容
        List<Integer>myList=new ArrayList<>();
        for (int i = 0; i <100 ; i++) myList.add(i);
        Stream<Integer> stream = myList.stream();
        Stream<Integer> highNum = stream.filter((p) -> p > 90);
        System.out.println("超过90的数为"+highNum);
        highNum.forEach((p)->System.out.println(p+" "));
        System.out.println("======分割线========================");

        //Stream map() 转换成新的类型的集合
        Stream<String> aBc = Stream.of("aBc", "de", "FGG");
        List<String> collect =
                aBc.map(s -> s.toUpperCase())
                .collect(Collectors.toList());
        System.out.println(collect.toString());
        System.out.println("======分割线========================");
        //Stream sorted() 排序

        Stream<String> names2 = Stream.of("aBc", "d", "ef", "123456");
        //反转
        List<String> list = names2.sorted(Comparator.reverseOrder()).collect(Collectors.toList());
        Stream<String> names3 = Stream.of("aBc", "d", "ef", "123456");
        //正常排序
        List<String> list1 = names3.sorted().collect(Collectors.toList());
        System.out.println("反转后的值为:"+list);
        System.out.println("正常排序的值为:"+list1);
        System.out.println("======分割线========================");
//       Stream flatMap()  We can use flatMap() to create a stream from the stream of list.
        //通过列表流创建流？？？？
        Stream<List<String>> asList = Stream.of(Arrays.asList("Panki"),
                Arrays.asList("DVI", "LISA"),
                Arrays.asList("AMT")
        );
        //flat the stream from List<String> to String stream
        Stream<String> stringStream = asList.flatMap(s -> s.stream());
        stringStream.forEach(System.out::println);//Panki DVI LISA AMT 将list转换成string 流

    }

    private static void convertingStreamToArrayAndList() {
//        1：We can use java Stream collect() method to get List, Map or Set from stream.
        Stream<Integer> integerStream = Stream.of(1, 2, 3, 4);
        List<Integer> collect = integerStream.collect(Collectors.toList());
        System.out.println(collect);//prints [1, 2, 3, 4]
        //上一个流关闭了，所以我们需要在创建一个流
        Stream<Integer> integerStream1 = Stream.of(1, 2, 3, 4);
        Map<Integer, Integer> collect1 = integerStream1.collect(Collectors.toMap(i -> i, i -> i + 10));
        System.out.println(collect1);

//        2:We can use stream toArray() method to create an array from the stream
        Stream<Integer> integerStream2 = Stream.of(1, 2, 3, 4);
        Integer[] integers = integerStream2.toArray(Integer[]::new);
        System.out.println(Arrays.toString(integers));



    }

    private static void createStream() {
        //Creating Java Streams
//        1:of 创建
        Stream<Integer> stream = Stream.of(1, 2, 3, 4);
//        2:of 对象数组
        Stream<Integer> integerStream = Stream.of(new Integer[]{1, 2, 3, 4});
        Stream<int[]> stream1 = Stream.of(new int[]{1, 2, 3, 4});
//        3: stream方式
        List<Integer>arrInteger=new ArrayList<>();
        for (int i = 0; i <100 ; i++) arrInteger.add(i);
        //sequential stream
        Stream<Integer> stream2 = arrInteger.stream();
        //parallel stream
        Stream<Integer> integerStream1 = arrInteger.parallelStream();
        //4；Stream.generate() or Stream.iterate() 创建
        Stream<String> generate = Stream.generate(() -> {
            return "abc";
        });
        Stream<String> abc = Stream.iterate("abc", (i) -> i);
        //5:Using Arrays.stream() and String.chars() methods.
        Stream<Integer> stream3 = Arrays.stream(new Integer[]{1, 2, 3, 4});
        IntStream chars = "zbc".chars();
    }
}
