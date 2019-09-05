package com.example.liuyjycommon.util;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import java.security.Key;
import java.security.SecureRandom;
import java.util.Base64;

public class Des {
    private static Key key;
    private static String KEY_STR = "hart-edu";

    static {
        try {
            KeyGenerator generator = KeyGenerator.getInstance("DES");
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
            secureRandom.setSeed(KEY_STR.getBytes());
            generator.init(secureRandom);
            key = generator.generateKey();
            generator = null;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 对字符串进行加密，返回BASE64的加密字符串
     * <功能详细描述>
     *
     * @param str
     * @return
     * @see [类、类#方法、类#成员]
     */

    public static String getEncryptString(String str) {
        Base64.Encoder encoder = Base64.getEncoder();
        try {
            byte[] strBytes = str.getBytes("UTF-8");
            Cipher cipher = Cipher.getInstance("DES");
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] encryptStrBytes = cipher.doFinal(strBytes);

            String encode = encoder.encodeToString(encryptStrBytes);
            return encode;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * 对BASE64加密字符串进行解密
     * <功能详细描述>
     *
     * @param str
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static String getDecryptString(String str) {
        Base64.Decoder decoder = Base64.getDecoder();
        try {

            byte[] buffer = decoder.decode(str);
            Cipher cipher = Cipher.getInstance("DES");
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] encryptStrBytes = cipher.doFinal(buffer);
            return new String(encryptStrBytes, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }

    public static void main(String[] args) {

        //加密
        String jiami = getEncryptString("jdbc:mysql://219.232.255.138:3548/hanyidb?useUnicode=true&characterEncoding=UTF-8");
        System.out.println("加密=====" + jiami);
        //解密
        String jiemi = getDecryptString("w+MBoDYOvBI=");
        System.out.println("解密===" + jiemi);


    }
}
