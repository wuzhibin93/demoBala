package com.enlink.j8.LAMBDA;

import java.util.Comparator;

/**
 * @ClassName :
 * @Author Aprwu
 * @Description : TODO(类的作用)
 * @Date : Created in 17:46 2019/1/16
 */
public class COMPARATOR {
    public static void main(String[] args) {
        Comparator<Person> comparator = (p1,p2) -> p1.firstName.compareTo(p2.firstName);
        Person p1 = new Person("John", "Doe");
        Person p2 = new Person("Alice", "Wonderland");

        int compare = comparator.compare(p1, p2);// > 0
        int compare1 = comparator.reversed().compare(p1, p2);// < 0
        System.out.println(compare);
        System.out.println(compare1);
    }
}
