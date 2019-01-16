package com.enlink.j8.LAMBDA;

import java.awt.event.ActionEvent;
import java.beans.EventHandler;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static sun.audio.AudioPlayer.player;

/**
 * @ClassName :
 * @Author Aprwu
 * @Description : TODO(类的作用)
 * @Date : Created in 9:39 2019/1/16
 */
public class Test1 {
    public static void main(String[] args) {
        String[] atp = {"Rafael Nadal","Novak Djokovic",
                            "Stanislas Wawrinka",
                            "David Ferrer","Roger Federer",
                            "Andy Murrary","Tomas Berdych",
                "Juan Matrin Del Potro"};
        List<String> players = Arrays.asList(atp);
        //老式循环
//        for (String player : players) {
//            System.out.println(player + ";");
//        }

        //使用lambda 表达式即函数操作
//        players.forEach((player) -> System.out.println(player + ";"));

        //在java8中使用双冒号操作符
        players.forEach(System.out::println);

        //使用lambda实现Runnable接口
        //使用匿名内部类
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("hello world1");
            }
        }).start();

        //使用lambda表达式
        new Thread(() -> System.out.println("hello world2")).start();
        //使用匿名内部类
        Runnable race1 = new Runnable() {
            @Override
            public void run() {
                System.out.println("hello world3");
            }
        };
        race1.run();

        //使用lambda表达式
        Runnable race2 = () -> System.out.println("hello world4");
        race2.run();

        //Runnable 的lambda表达式，使用块格式，将五行代码转换成单行语句，接下来，使用lambda对集合进行排序
        //在Ｊａｖａ中，使用Ｃｏｍｐａｒａｔｏｒ类来排序
        //旧的方式
        String[] player = {"Rafael Nadal","Novak Djokovic",
                "Stanislas Wawrinka",
                "David Ferrer","Roger Federer",
                "Andy Murrary","Tomas Berdych",
                "Juan Matrin Del Potro"};

        for (String s : player) {
            System.out.println(s+"1");
        }
        //使用匿名内部类
        Arrays.sort(player, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return (o1.compareTo(o2));
            }
        });
        for (String s : player) {
            System.out.println(s+"2");
        }
        //lambda 表达式
        Comparator<String> sortByName = (String s1 ,String s2) -> (s1.compareTo(s2));
        Arrays.sort(player,sortByName);
        for (String s : player) {
            System.out.println(s+"3");
        }

        Arrays.sort(player,(String s1, String s2) -> (s1.compareTo(s2)));
        for (String s : player) {
            System.out.println(s+"4");
        }

        Arrays.sort(player,(String::compareTo));
        for (String s : player) {
            System.out.println(s+"5");
        }

    }
}

