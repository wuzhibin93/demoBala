package OpenSSL;

import org.junit.Test;

import java.io.File;
import java.io.IOException;

/**
 * @ClassName :
 * @Author Aprwu
 * @Description : TODO(类的作用)
 * @Date : Created in 17:33 2018/9/5
 */
public class ExecTest {
    //使用java改变证书格式
    @Test
    public void test1(){
        String path = "/usr/local/enlink/upload/download/logs/";
        File file = new File("/usr/local/enlink/upload/download/logs/");
        Runtime runtime = Runtime.getRuntime();
        String name = "fullchain.pem";
        try {
            runtime.exec("openssl x509 -inform der -in "+name+" -out fullchain22211.pem",
                    null,
                    file
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
        File oldFile = new File("/usr/local/enlink/upload/download/logs/"+name);
        if (oldFile.exists() && oldFile.isFile()) {
            if (file.delete()) {
                System.out.println("删除单个文件" + name + "成功！");
            } else {
                System.out.println("删除单个文件" + name + "失败！");
            }
        }
    }
}
