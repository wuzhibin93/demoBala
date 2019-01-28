package com.enlink.TT;

import com.enlink.entity.Key;
import com.enlink.entity.User;

import java.io.*;

/**
 * @ClassName :
 * @Author Aprwu
 * @Description : TODO(类的作用)
 * @Date : Created in 15:13 2019/1/28
 */
public class ObjectInputStreamTe {
    public static void main(String[] args) {
        writeObject();
        readObject();
    }

    //写入对象
    public static void writeObject(){
        OutputStream outputStream = null;
        BufferedOutputStream buf = null;
        ObjectOutputStream obf = null;
        try {
            //序列化文件输出流
            outputStream = new FileOutputStream("D:\\settings.txt");
            //构建缓冲流
            buf = new BufferedOutputStream(outputStream);
            //构建字符输出的对象流
            obf = new ObjectOutputStream(buf);
            //序列化写入
            obf.writeObject(new Key("Key"));
            //关闭流
            obf.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    读取对象
    public static void readObject(){
        try {
            InputStream inputStream = new FileInputStream("D:\\settings.txt");
            //构建缓冲流
            BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
            //构建字符输入的对象流
            ObjectInputStream objectInputStream = new ObjectInputStream(bufferedInputStream);
            Key key = (Key) objectInputStream.readObject();
            System.out.println("Key对象为："+key);
            //关闭流
            objectInputStream.close();
            bufferedInputStream.close();
            inputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
