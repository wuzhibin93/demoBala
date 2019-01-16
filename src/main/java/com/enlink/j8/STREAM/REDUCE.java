package com.enlink.j8.STREAM;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @ClassName :
 * @Author Aprwu
 * @Description : TODO(将一个集合拼接)
 * @Date : Created in 17:08 2019/1/16
 */
public class REDUCE {
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

        Optional<String> reduce = stringCollection
                .stream()
                .sorted()
                .reduce(((s, s2) -> s + "====" + s2));
        reduce.ifPresent(System.out::println);

    }
}
