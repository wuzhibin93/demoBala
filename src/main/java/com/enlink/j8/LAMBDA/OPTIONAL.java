package com.enlink.j8.LAMBDA;

import com.enlink.entity.Operation;
import org.apache.poi.ss.formula.functions.T;

import java.util.Optional;
import java.util.function.Consumer;

/**
 * @ClassName :
 * @Author Aprwu
 * @Description : TODO(类的作用)
 * @Date : Created in 17:50 2019/1/16
 */
public class OPTIONAL {
    public static void main(String[] args) {
        Optional<String> optional = Optional.of("bam");

        boolean present = optional.isPresent();//判断是否为空
        System.out.println(present);
        System.out.println(optional.get());//不为空返回传入的值
        optional.get();
        optional.orElse("fallback");
        optional.ifPresent(s -> System.out.println(s.charAt(0)));

        Person person = new Person();

    }
}
