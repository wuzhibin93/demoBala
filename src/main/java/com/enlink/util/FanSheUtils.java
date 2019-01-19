package com.enlink.util;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * @ClassName :
 * @Author Aprwu
 * @Description : TODO(类的作用)
 * @Date : Created in 16:52 2019/1/19
 */
public class FanSheUtils {
    //打印该类的所有方法
    public static void printMethods(Class cl){
        System.out.println();
        //获得包含该类所有其他方法的数组
        Method[] methods = cl.getDeclaredMethods();
        //遍历数组
        for (Method method : methods) {
            System.out.print("    ");
            //获取该方法的修饰符并打印
            String mordifiers = Modifier.toString(method.getModifiers());
            if (mordifiers.length() > 0){
                System.out.print(mordifiers + "");
            }
            //打印方法名
            System.out.print(method.getName() + "(");

            //获得该方法包含所有参数类型的Class对象的数组
            Class<?>[] parameterTypes = method.getParameterTypes();
            //遍历数组
            for (int i = 0; i < parameterTypes.length; i++) {
                if (i > 0){
                    System.out.print(",");
                }
                System.out.print(parameterTypes[i].getName());

            }
            System.out.println(");");
        }
    }
}
