package com.enlink.j8.LAMBDA;

/**
 * @ClassName :
 * @Author Aprwu
 * @Description : TODO(类的作用)
 * @Date : Created in 16:11 2019/1/16
 */
public interface PersonFactory<P extends Test2.Person> {
    P creat(String firstName,String lastName);
}
