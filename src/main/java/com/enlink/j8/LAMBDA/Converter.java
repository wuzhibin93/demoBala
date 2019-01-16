package com.enlink.j8.LAMBDA;

/**
 * @ClassName :
 * @Author Aprwu
 * @Description : TODO(函数式接口)
 * @Date : Created in 15:52 2019/1/16
 */
@FunctionalInterface
public interface Converter<F,T> {
    T convert(F from);
}
