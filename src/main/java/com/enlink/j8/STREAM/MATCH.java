package com.enlink.j8.STREAM;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName :
 * @Author Aprwu
 * @Description : TODO(类的作用)
 * @Date : Created in 16:56 2019/1/16
 */
public class MATCH {
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

        //验证list中是否有以a开头的
        boolean a = stringCollection
                .stream()
                .anyMatch((s) -> s.startsWith("a"));
        System.out.println(a);

        //验证list中是否全是a开头
        boolean a1 = stringCollection
                .stream()
                .allMatch(s -> s.startsWith("a"))
                ;
        System.out.println(a1);

        //验证list中是否全不是以z开头
        boolean z = stringCollection
                .stream()
                .noneMatch(s -> s.startsWith("z"));
        System.out.println(z);

    }
}
