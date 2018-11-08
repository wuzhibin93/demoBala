package OpenSSL;

import org.junit.Test;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Base64;

/**
 * @ClassName :
 * @Author Aprwu
 * @Description : TODO(类的作用)
 * @Date : Created in 16:14 2018/9/4
 */
public class SecurityTest {
    @Test
    public void test1(){
        //KeyPaorGenerator类用与生成公钥和私钥对，基于RSA算法
        KeyPairGenerator keyPairGenerator = null;
        try {
            keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        }catch (NoSuchAlgorithmException e){
            e.printStackTrace();
        }
        //初始化密钥对生成器，密钥大小为96-1024位
        keyPairGenerator.initialize(1024,new SecureRandom());
        //生成一个密钥对，保存再keyPair中
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        //得到私钥
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
        //得到公钥
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();

        //得到公钥字符串
        //Base64 base64 = new Base64();
        for (byte b : publicKey.getEncoded()) {
            System.out.printf(b+"");
        }
        ;
        //System.out.printf(publicKey.getEncoded().toString());
    }
}
