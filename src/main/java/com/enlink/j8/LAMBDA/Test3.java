package com.enlink.j8.LAMBDA;

import javafx.util.StringConverter;

/**
 * @ClassName :
 * @Author Aprwu
 * @Description : TODO(访问局部变量)
 * @Date : Created in 15:58 2019/1/16
 */
public class Test3 {
    public static void main(String[] args) {
        final int num = 1;
        Converter<Integer,String> stringConverter = (from) -> String.valueOf(from+num);
        String convert = stringConverter.convert(2);
        System.out.println(convert);

    }
}
