package com.enlink.j8.LAMBDA;

import java.util.function.Supplier;

/**
 * @ClassName :
 * @Author Aprwu
 * @Description : TODO(类的作用)
 * @Date : Created in 17:38 2019/1/16
 */
public class SUPPLIER {
    public static void main(String[] args) {
        Supplier<Person> personSupplier = Person::new;
        Person person = personSupplier.get();//new Person
    }
}
class Person{
    String firstName;
    String lastName;
    Person(){}
    Person(String firstName,String lastName){
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
