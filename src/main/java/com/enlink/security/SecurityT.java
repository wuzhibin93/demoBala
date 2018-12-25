package com.enlink.security;

import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.crypto.Cipher;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * @ClassName :
 * @Author Aprwu
 * @Description : TODO(类的作用)
 * @Date : Created in 15:57 2018/9/4
 */
@Controller
@RequestMapping("security")
public class SecurityT {
    /**
     * 字节数据转换字符串专用集合
     */
    private static final char[] HEX_CHAR = {'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'};
    /**
     * String to hold name of the encryption algorithm
     */
    public static final String ALGORITHM = "RSA";
    /**
     * String to hold name of the private key file
     */
    public static final String PRIVATE_KEY_FILE = "E:/OpenSSL-Win64/bin/pkcs8_priv.pem";
    /**
     * String to hold name of the public key file
     */
    public static final String PUBLIC_KEY_FILE = "E:/OpenSSL-Win64/bin/public.key";

    /**
     * Encrypt the plain text using public key
     * @param text
     * @param key
     * @return
     */
    public static byte[] encrypt(String text , PublicKey key){
        byte[] cipherText = null;
        try{
            //get an RSA cipher object and print the provider
            final Cipher cipher = Cipher.getInstance(ALGORITHM);
            //encrypt the plan text using the public key
            cipher.init(Cipher.ENCRYPT_MODE,key);
            cipherText = cipher.doFinal(text.getBytes());
        }catch (Exception e){
            e.printStackTrace();
        }
        return cipherText;

    }

    public static String decrypt(byte[] text , PrivateKey key){
        byte[] dectyptedText = null;
        try {
            //get an RSA cipher object and print the provider
            final Cipher cipher = Cipher.getInstance(ALGORITHM);
            //decrypt the text using the private key
            cipher.init(Cipher.DECRYPT_MODE,key);
            dectyptedText = cipher.doFinal(text);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new String(dectyptedText);
    }
    @RequestMapping(value = "test",method = RequestMethod.GET)
    public static void test(){
        String s = "Hello World";
        try {
            BufferedReader privateKey = new BufferedReader(new FileReader(PRIVATE_KEY_FILE));
            BufferedReader publicKey = new BufferedReader(new FileReader(PUBLIC_KEY_FILE));
            String strPrivateKey = "";
            String strPublicKey = "";
            String line = "";
            while ((line = privateKey.readLine()) != null){
                strPrivateKey += line;
            }
            while ((line = publicKey.readLine()) != null){
                strPublicKey += line;
            }
            privateKey.close();
            publicKey.close();
            //私钥需要使用pkcs8格式的，公钥使用x509格式
            String strPrivKey = strPrivateKey.replace("-----BEGIN PRIVATE KEY-----","");
            String strPubKey = strPublicKey.replace("-----BEGIN PUBLIC KEY-----", "");
            byte[] privKeyByte = Base64.decodeBase64(strPrivKey);
            byte[] pubKeyByte = Base64.decodeBase64(strPubKey);
            PKCS8EncodedKeySpec privKeySpec = new PKCS8EncodedKeySpec(privKeyByte);
            //PKCS8EncodedKeySpec pubKeySpec = new PKCS8EncodedKeySpec(pubKeyByte);
            //X509EncodedKeySpec pubKeySpec = new X509EncodedKeySpec(privKeyByte);
            X509EncodedKeySpec pubKeySpec = new X509EncodedKeySpec(pubKeyByte);
            KeyFactory kf = KeyFactory.getInstance("RSA");
            PrivateKey privKey = kf.generatePrivate(privKeySpec);
            PublicKey pubKey = kf.generatePublic(pubKeySpec);
            byte[] encryptByte = encrypt(s, pubKey);
            System.out.println(decrypt(encryptByte,privKey));
        }catch (IOException e){
            e.printStackTrace();
        }catch (NoSuchAlgorithmException e){
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }


    }


}
