package com.enlink.TT;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @ClassName :
 * @Author Aprwu
 * @Description : TODO(类的作用)
 * @Date : Created in 14:38 2019/1/28
 */
public class FileWriterTe {
    public static void main(String[] args) throws IOException {
        FileReader fr = null;
        FileWriter fw = null;
        try {
            fr = new FileReader("D:\\settings.txt");
            fw = new FileWriter("D:\\settings_write.txt");
            char[] chars = new char[1024];
            int hasRead = 0;
            while ((hasRead=fr.read(chars))>0){
                fw.write(chars,0,hasRead);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            fr.close();
            fw.close();
        }
    }


}
