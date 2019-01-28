package com.enlink.TT;

import java.io.FileReader;
import java.io.IOException;

/**
 * @ClassName :
 * @Author Aprwu
 * @Description : TODO(使用FileReader进行文件读取)
 * @Date : Created in 14:13 2019/1/28
 */
public class FileReaderTe {
    public static void main(String[] args) throws IOException {
//        创建字节输入流
        FileReader fe = null;
        try {
//            创建字节输入流
            fe = new FileReader("D:\\settings.txt");
//            创建一个长度为1024的桶
            char[] chars = new char[1024];
//            用于保存实际字节数
            int hasRead = 0;
//            循环取出字节
            while ((hasRead=fe.read(chars))>0){
//                取出字节并进行输出
                System.out.println(new String(chars,0,hasRead));
            }
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            fe.close();
        }
    }
}
