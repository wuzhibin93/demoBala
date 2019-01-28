package com.enlink.TT;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @ClassName :
 * @Author Aprwu
 * @Description : TODO(转换流(InputStreamReader和OutputStreamWriter)的使用)
 * @Date : Created in 15:05 2019/1/28
 */
public class InputStreamReaferTe {
    public static void main(String[] args) {
        try {
//              将System.in对象转换成Reader对象
            InputStreamReader inputStreamReader = new InputStreamReader(System.in);
//            将普通的Reader包装成BufferReader
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String buffer = null;
            while ((buffer=bufferedReader.readLine())!= null){
//                如果读到exit，则程序退出
                if (buffer.equals("exit")){
                    System.exit(1);
                }
//                打印输入的内容
                System.out.println("输入内容："+buffer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
