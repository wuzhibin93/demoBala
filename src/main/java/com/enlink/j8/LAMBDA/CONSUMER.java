package com.enlink.j8.LAMBDA;

import java.util.function.Consumer;

/**
 * @ClassName :
 * @Author Aprwu
 * @Description : TODO(类的作用)
 * @Date : Created in 17:42 2019/1/16
 */
public class CONSUMER {
    public static void main(String[] args) {
        Consumer<Person> greeter = (p) -> System.out.println("Hello"+p.firstName);
        greeter.accept(new Person("Luck","Sky walker"));
        System.out.println(greeter);
    }
}
