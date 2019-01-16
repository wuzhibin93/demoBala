package com.enlink.j8.LAMBDA;

import java.util.function.Function;

/**
 * @ClassName :
 * @Author Aprwu
 * @Description : TODO(类的作用)
 * @Date : Created in 17:35 2019/1/16
 */
public class FUNCTION {
    public static void main(String[] args) {
        Function<String,Integer> toInteger = Integer::valueOf;
        Function<String, String> stringStringFunction = toInteger.andThen(String::valueOf);
        String apply = stringStringFunction.apply("123");
        System.out.println(apply);
    }
}
