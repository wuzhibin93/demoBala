package com.enlink.TT;

import java.io.*;

/**
 * @ClassName :
 * @Author Aprwu
 * @Description : TODO(字节缓冲流的使用。当我们使用缓存流包在字节流上面的时候，只需要关闭上层的处理就来可以了
 *  java会帮我们关闭下层的节点流
 * )
 * @Date : Created in 14:45 2019/1/28
 */
public class BufferStreamTe {
    public static void main(String[] args) throws IOException {
        FileInputStream fis = null;
        FileOutputStream fos = null;
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;

        try {
            fis = new FileInputStream("D:\\settings.txt");
            fos = new FileOutputStream("D:\\settings_Buffer.txt");
//            创建字节缓存流
            bis = new BufferedInputStream(fis);
            bos = new BufferedOutputStream(fos);
            byte[] bytes = new byte[1024];
            int hasRead = 0;
            while ((hasRead=bis.read(bytes))>0){
                bos.write(bytes,0,hasRead);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }finally {
            bis.close();
            bos.close();
        }
    }


}
