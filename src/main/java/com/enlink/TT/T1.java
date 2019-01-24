package com.enlink.TT;

/**
 * @ClassName :
 * @Author Aprwu
 * @Description : TODO(递归，已经忘了)
 * @Date : Created in 16:27 2019/1/24
 */
public class T1 {
    public static void main(String[] args) {
        System.out.println(sum(100));
    }
    private static int sum(int i){
        if (i == 1){
            return 1;
        }else {
            return i+sum(i-1);
        }
    }
}
