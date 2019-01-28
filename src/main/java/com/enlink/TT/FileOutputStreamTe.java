package com.enlink.TT;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @ClassName :
 * @Author Aprwu
 * @Description : TODO(FileOutputStream使用)
 * @Date : Created in 14:26 2019/1/28
 */
public class FileOutputStreamTe {
    public static void main(String[] args) throws IOException {

        FileInputStream fi = null;
        FileOutputStream fo = null;
        try {
//            创建字节输入流
            fi = new FileInputStream("D:\\settings.txt");
//            创建字节输出流
            fo = new FileOutputStream("D:\\settings_copy.txt");
//            新建一个桶存放字节
            byte[] aByte = new byte[1024];
//
            int hasRead = 0;
//            循环取出字节并进行写，读多少取多少
            while ((hasRead=fi.read(aByte))>0){
                fo.write(aByte,0,hasRead);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }finally {
            fi.close();
            fo.close();
        }
    }
}
