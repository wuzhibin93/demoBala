package com.enlink.j8.LAMBDA;

import java.util.Objects;
import java.util.function.Predicate;

/**
 * @ClassName :
 * @Author Aprwu
 * @Description : TODO(类的作用)
 * @Date : Created in 17:27 2019/1/16
 */
public class PREDICATE {
    public static void main(String[] args) {
        //predicate 是一个可以指定入参类型，并返回boolean值的函数式接口。内部提供了一些带有默认实现的方法，
        //可以被用来组合一个复杂的逻辑判断（and，or，negate）
        Predicate<String> predicate = s -> s.length() > 0;
        boolean foo = predicate.test("foo");
        boolean foo1 = predicate.negate().test("foo");
        System.out.println(foo);
        System.out.println(foo1);

        Predicate<Boolean> nonNull = Objects::nonNull;
        Predicate<Boolean> isNull = Objects::isNull;
        Predicate<String> isEmpty = String::isEmpty;
        Predicate<String> isNotEmpty = isEmpty.negate();


    }
}
