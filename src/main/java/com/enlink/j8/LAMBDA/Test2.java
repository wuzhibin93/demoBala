package com.enlink.j8.LAMBDA;

import com.enlink.j8.INTERFACE.Formula;

/**
 * @ClassName :
 * @Author Aprwu
 * @Description : TODO(函数式接口)
 * @Date : Created in 15:56 2019/1/16
 */
public class Test2 {
    public static void main(String[] args) {

        //Converter实现
        Converter<String, Integer> converter = (from)-> Integer.valueOf(from);
        Integer converted = converter.convert("123");
        System.out.println(converted);

        //简化方法。使用双冒号关键字来引用类的方法后构造器
        Converter<String,Integer> converter1 = Integer::valueOf;
        Integer converted1 = converter1.convert("123");
        System.out.println(converted1);

        Converter<String, Integer> formula = Integer::valueOf;
        Integer integer = Integer.valueOf("123",35);
        System.out.println(integer);

    }
    class Person{
        String firstName;
        String lastName;
        Person(){}
        Person(String firstName ,String lastName){
         this.firstName = firstName;
         this.lastName = lastName;
        }
    }
    interface PersonFactory<P extends Test2.Person> {
        P create(String firstName,String lastName);
    }
}
