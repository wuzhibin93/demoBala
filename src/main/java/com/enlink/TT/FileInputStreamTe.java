package com.enlink.TT;

import java.io.FileInputStream;
import java.io.IOException;

/**
 * @ClassName :
 * @Author Aprwu
 * @Description : TODO(FileInputStream读取文件)
 * @Date : Created in 13:55 2019/1/28
 */
public class FileInputStreamTe {
    public static void main(String[] args) throws IOException {
//        创建字节输入流
        FileInputStream fis = null;
        try {
//            创建字节输入流
            fis = new FileInputStream("D:\\settings.txt");
//            创建长度为1024的桶
            byte[] bytes = new byte[1024];
//            用于保存实际的字节数
            int hasRead = 0;
//            使用循环重复读取的过程
            while ((hasRead = fis.read(bytes)) > 0){
//                取出字节，将字节数转换成字符串进行输出
                System.out.println(new String(bytes,0,hasRead));
            }
        }catch (IOException e) {
            e.printStackTrace();
        }finally {
            fis.close();
        }
    }
}
