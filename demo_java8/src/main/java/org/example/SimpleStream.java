package org.example;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 流操作 串行流 stream 、并行流 parallelstream 、Collector 类
 *
 * @author MGG
 * @version 1.0
 * @date 2020/12/2 21:52
 */
public class SimpleStream {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("a", "c", "f", "", "d", "e", "b","d");

        //1. forEach 迭代流中的数据
        list.forEach(System.out::print);
        System.out.println();

        //2. map 映射元素的结果
        List<String> list1 = list.stream().map(e->e+"#").collect(Collectors.toList());
        list1.forEach(System.out::print);
        System.out.println();

        //3. filter 过滤元素
        List<String> list2 = list.stream().filter(e -> !e.isEmpty()).collect(Collectors.toList());
        list2.forEach(System.out::print);
        System.out.println();

        //4. limit 获取指定数量的流
        list2.stream().limit(2).collect(Collectors.toList()).forEach(System.out::print);
        System.out.println();

        //5. sort 排序
        list2.stream().sorted((a,b)->a.compareTo(b)).forEach(System.out::print);
        System.out.println();
        List<String> list3 = list2.stream().sorted().collect(Collectors.toList());
        list3.forEach(System.out::print);
        System.out.println();

        //6. distict 去重复
        list3.stream().distinct().forEach(System.out::print);
        System.out.println();

        //7. max min 不再举例，基本已经够用了。

        //并行流处理方案 测试并行流打印
        list3.stream().distinct().forEach(System.out::print);
        System.out.println();
        //list3 我们已经排好序了 使用并行流发现遍历出来的顺序乱掉了，是多线程输出的特征。
        list3.parallelStream().distinct().forEach(System.out::print);
        System.out.println();

        // Collector 类对流数据进行归约操作 比如组装为集合，合并为串 ，组装集合在上面已经举例。
        String listStr = list3.stream().distinct().collect(Collectors.joining(","));
        System.out.println(listStr);

    }

    /**
     * 切割集合
     * @param collection
     * @param maxSize
     * @param splitSize
     * @param <T>
     * @return
     */
    public static <T> List<Collection> splitList(Collection<T> collection, int maxSize, int splitSize) {
        if (collection.isEmpty()) {
            return Collections.emptyList();
        }
        return Stream.iterate(0, f -> f + 1)
                .limit(maxSize)
                .parallel()
                .map(a -> collection.parallelStream().skip(a * splitSize).limit(splitSize).collect(Collectors.toList()))
                .filter(b -> !b.isEmpty())
                .collect(Collectors.toList());
    }
}
