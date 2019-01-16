package com.enlink.j8.STREAM;

import java.util.ArrayList;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName :
 * @Author Aprwu
 * @Description : TODO(并行流)
 * @Date : Created in 17:13 2019/1/16
 */
public class PARALLEL_STRAMS {
    public static void main(String[] args) {
        //首先。创建1000000UUID的list集合
        int max = 1000000;
        ArrayList<Object> list = new ArrayList<>(max);
        for (int i = 0; i < max; i++) {
            UUID uuid = UUID.randomUUID();
            list.add(uuid.toString());
        }

        //顺序流排序
        long l = System.nanoTime();
        long count = list.stream().sorted().count();
        System.out.println(count);
        long l1 = System.nanoTime();
        long l2 = TimeUnit.NANOSECONDS.toMillis((l1 - l));
        System.out.println(String.format("顺序流排序耗时： %d ms",l2));

        //并行流排序
        long l3 = System.nanoTime();
        long count1 = list.parallelStream().sorted().count();
        System.out.println(count1);
        long l4 = System.nanoTime();
        long l5 = TimeUnit.NANOSECONDS.toMillis(l4 - l3);
        System.out.println(String.format("并行流排序耗时： %d ms",l5));
    }
}
