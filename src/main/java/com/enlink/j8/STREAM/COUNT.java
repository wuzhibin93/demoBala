package com.enlink.j8.STREAM;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName :
 * @Author Aprwu
 * @Description : TODO(Count计数，返回的是long类型)
 * @Date : Created in 17:06 2019/1/16
 */
public class COUNT {
    public static void main(String[] args) {
        List<String> stringCollection = new ArrayList<>();
        stringCollection.add("ddd2");
        stringCollection.add("aaa2");
        stringCollection.add("bbb1");
        stringCollection.add("aaa1");
        stringCollection.add("bbb3");
        stringCollection.add("ccc");
        stringCollection.add("bbb2");
        stringCollection.add("ddd1");

        long b = stringCollection
                .stream()
                .filter(s -> s.startsWith("b"))
                .count();
        System.out.println(b);

    }
}
