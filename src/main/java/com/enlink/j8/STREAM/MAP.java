package com.enlink.j8.STREAM;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName :
 * @Author Aprwu
 * @Description : TODO(类的作用)
 * @Date : Created in 16:54 2019/1/16
 */
public class MAP {
    public static void main(String[] args) {
        Map<Integer, String> map = new HashMap<>();
        for (int i = 0; i < 10; i++) {
            map.putIfAbsent(i,"val "+i);
        }
        map.forEach((key,value) -> System.out.println(value));


    }
}
