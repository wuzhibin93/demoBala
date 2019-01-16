package com.enlink.j8.LAMBDA;

/**
 * @ClassName :
 * @Author Aprwu
 * @Description : TODO(类的作用)
 * @Date : Created in 16:37 2019/1/16
 */
public class Lambda4 {
    static int outerStaticNum;
    int outerNum;
    void testScopes(){
        Converter<Integer,String> stringConverter = (from) -> {
            outerNum = 23;
            return String.valueOf(from);
        };
        System.out.println(stringConverter);
        Converter<Integer,String> stringConverter1 = from -> {
            outerStaticNum = 72;
            return String.valueOf(outerStaticNum);
        };
        System.out.println(stringConverter1);
    }
}
