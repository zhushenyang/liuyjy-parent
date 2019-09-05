package com.example.liuyjycommon.util;

import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.security.Key;
import java.security.SecureRandom;
import java.util.Base64;

/**
 * @Author liuyjy
 * @Date 2019/7/15
 * @Description: TODO  DES 加密解密
 **/
public class DesUtil {
    private static Key key;
    private static String KEY_STR = "aladin-pim";

    static {
        try {
            KeyGenerator generator = KeyGenerator.getInstance("DES");
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
            secureRandom.setSeed(KEY_STR.getBytes());
            generator.init(secureRandom);
            key = generator.generateKey();
            System.out.println("key=====" + key.getAlgorithm());
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

    /**
     *
     * @Method: encrypt
     * @Description: 加密数据
     * @param data
     * @return
     * @throws Exception
     * @date 2016年7月26日
     */
    public static String encrypt(String data,String desKey) {  //对string进行BASE64Encoder转换
        byte[] bt = encryptByKey(data.getBytes(), desKey);
        BASE64Encoder base64en = new BASE64Encoder();
        String strs = new String(base64en.encode(bt));
        return strs;
    }
    /**
     *
     * @Method: encrypt
     * @Description: 解密数据
     * @param data
     * @return
     * @throws Exception
     * @date 2016年7月26日
     */
    public static String decryptor(String data,String desKey) throws Exception {  //对string进行BASE64Encoder转换
        sun.misc.BASE64Decoder base64en = new sun.misc.BASE64Decoder();
        byte[] bt = decrypt(base64en.decodeBuffer(data), desKey);
        String strs = new String(bt);
        return strs;
    }

    /**
     * 加密
     * @param datasource
     * @param key
     * @return
     */
    private static byte[] encryptByKey(byte[] datasource, String key) {
        try{
            SecureRandom random = new SecureRandom();

            DESKeySpec desKey = new DESKeySpec(key.getBytes());
            //创建一个密匙工厂，然后用它把DESKeySpec转换成
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey securekey = keyFactory.generateSecret(desKey);
            //Cipher对象实际完成加密操作
            Cipher cipher = Cipher.getInstance("DES");
            //用密匙初始化Cipher对象
            cipher.init(Cipher.ENCRYPT_MODE, securekey, random);
            //现在，获取数据并加密
            //正式执行加密操作
            return cipher.doFinal(datasource);
        }catch(Throwable e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 解密
     * @param src
     * @param key
     * @return
     * @throws Exception
     */
    private static byte[] decrypt(byte[] src, String key) throws Exception {
        // DES算法要求有一个可信任的随机数源
        SecureRandom random = new SecureRandom();
        // 创建一个DESKeySpec对象
        DESKeySpec desKey = new DESKeySpec(key.getBytes());
        // 创建一个密匙工厂
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        // 将DESKeySpec对象转换成SecretKey对象
        SecretKey securekey = keyFactory.generateSecret(desKey);
        // Cipher对象实际完成解密操作
        Cipher cipher = Cipher.getInstance("DES");
        // 用密匙初始化Cipher对象
        cipher.init(Cipher.DECRYPT_MODE, securekey, random);
        // 真正开始解密操作
        return cipher.doFinal(src);
    }

    public static void main(String[] args) {

        //加密
        String jiami = getEncryptString("jdbc:mysql://219.232.255.138:3548/hanyidb?useUnicode=true&characterEncoding=UTF-8");
        System.out.println("加密=====" + jiami);
        //解密
        String jiemi = getDecryptString("WsLqN9gkFg9Hi0FZB1tvUy6VRtQrEH4JbEro0JiNn+K+Dvii65dhi6ikd1uOvXoDSWyu4j3wEnxFbCCUpmm/B6RG67HNvLnVM0kipk1lirV9xtkS0MvGTg==");
        System.out.println("解密===" + jiemi);

        //*****************************************
        String pwd="aladin-pim";
        //待加密内容
        String str = "task_id=TSK_000000006870&ledger_id=0715-5572";
        String result = encrypt(str,pwd);
        System.out.println("加密后："+result);
        //直接将如上内容解密
        try {
            String s="aKKqmULqcpxZZHfRcTSOfPgMM1ORQIJRySFGk3RuG1thXEKNrgnlAY8aRFv+MC165QP7ZJml06Pk\n" +
                    "l88j/epNkiA8IKdmxB9J1tfEe2vfXtucogqinJt29RXxVe4yCFK6BECu2D3c/RTELOhqTujHiYQU\n" +
                    "5GTd8jAiusdAT7/klvvnuE1umM7BjC3lHN6slyMrt3BOGRQJuis/FPROzPXdxl7oty/kiVQw24SY\n" +
                    "hGjDFOu71yRZ9QFmbRlKoNcyD4MWlqnAJs0vkcEUGRG8LwMQYCTXNKlMQZnaGxA6QkrA0ViRvl4J\n" +
                    "z/hgdxev0jASOgcV3G0cWKccgB3+Frr8U812bQ5H4A8nycvVhZDfL/epreGFkN8v96mt4YWQ3y/3\n" +
                    "qa3hhZDfL/epreGFkN8v96mt4YWQ3y/3qa3hhZDfL/epreEgjixJFT9jGADP03yqognKqSBeQ3Q8\n" +
                    "+j/alT7GHbju0QVWXlgsZLwIk24S4zoD5ZrW8Ar0Ig3iVtFvAIXVhi9kMpAQoSG5c++2bYLo9kbC\n" +
                    "ghfYthYLkVhOnr/ad5qe4A7705PbYgM2dZwyP6eqCGYJHnOmph4mBZ6j1b+M05KBbsz5ic/W1/QI\n" +
                    "1ep95bEkFsHuQNwMcQtpOxUbB8FeQm0keDyZJ8EQppXucbro7UuVIk1GGXFDWQt+OqwjzI9A1gHW\n" +
                    "8Ar0Ig3iVoAkKt2srUYdzte9hReQDrcJjaRdf42cEHHteGQEkUOOynnpmqvLSzzbum1FVGPLjpVA\n" +
                    "BVi03ah22L67ax9b8EyZO04s9M/TpQO2ImIQJ/mJPmjgBGCyv52Z4F4lbNOTlWvzoFnin6H3lsz+\n" +
                    "nSDaJxrxV41Le9hUunm+uTtJbUCeqsIAaM+TLpar/EO54cfVH9ivaXjGHrtpRzNXj8xutmnkoD7W\n" +
                    "LKIF8NrLk6H1zNhZQyuC6AadTA9WabroGSYIrdJm1wjHmQkGz+oWbk22li+WbyPDUr4qpenNi2zt\n" +
                    "DgHPTiXUVCu8xudeLMq4eWBEC9dhTb4tMbWO/jTTMWfui3IqnCjIwrWMByfcFGlFrhjs9Q/7rE21\n" +
                    "KlPwIionz+RpoTpjme5OnbNT1zXn4W6oIbfwC482ucnt36iAD6vZY5nTa9vf7woSby8fWwmVD5pt\n" +
                    "1OeqkXaYfS3MvJbhY6gO0UwEV67dyO3TtN0eKSNXNmFTHzUbAy2qjyFFOZWjd/zcuFbrepAvIxhY\n" +
                    "F4p2PbdzU6QySI3ZhdU3tF+dnjpB9Dr9XuFAT19TsS77IzkSziZNPopr99AkEg6N5Smuxh+MOWw1\n" +
                    "1s7J6DrvhvH9qOKtisuGtrybzSq/CCg7Q9driuLhjxuOH8mZoiYCmjrv188yMotlUh2GL+BtCSze\n" +
                    "+EX1OJ8pXt6qlKn96pRpm2Ierz+J40UXZFqdtC/i78W0a3tXYvUtaMhawe9nijPRH6Dcw3ihnxyT\n" +
                    "r2Ecp+QvYo7h0URiUesHD5NfLjgbBKaMACLTda8tBMjJJi9rSV5O7J8/+bQVx/6tOisbbz8hk75T\n" +
                    "WxeUjMoO3sNV3SOMdTT9qwfdpHaoYHxTQT92Y89h9DC/DywRHzD59xpTc4Cl+T9BBkVUwcJr+o8x\n" +
                    "NWOFfPTTPvnvAt5vQCgB+oUVFdoTSkn5ZxWX2Ij6VXWuCKscjXWghgfw5pTLbp9C243JzDJ/Q6Ua\n" +
                    "jY4E9rQ2iwnziyxJKknKQ9Z4w2iCUawZMd1Fb0VjnNXIWMvgpvEjVxLcG2lQorf25/5j08f+MiPF\n" +
                    "DRugB3BnqbgyYE/DhjGCvRB/cUzuHzonRrbuvTB500cMoV5eoGThK/0jHw/sN51eY3SIXl+Auivl\n" +
                    "3Nl0Q3HEZ/dcUnb78GSC6xCN0c23cG1dnBNnivfs7ZozMp8X7I6rvUCXRVA91kokxhYQToaEqFnj\n" +
                    "n8EU7udrnjhgaNbHFj+BVPTXp7C6SkKqgo5WJaCib0h8HGmTqLC0TNIAEpLJgFC2MvLlxDYuJjII\n" +
                    "NIuj+eJaNpSRx5kGh76vtVvVo6PLQLrI1TWJPobSuvwwmodoPlBLYiE4xEU+lPwwqwRH2rWGBarv\n" +
                    "6ijIJdPQokR4egHLFvfOC0O1D5x5h4IL1ubSzxnELNfcVAv2PosBJZ13oGpCfZeVvT6Ch9GCj8OD\n" +
                    "3520L2dibmJiZ15/6RGMDGzBc3u/NwsXCILic8NXaVXWsYGDeWYKqUUQ4efLgzVdeAlQlePpu9VW\n" +
                    "Bq+2g/xSvKvj4aXmn/3oVm9hwoMBzsjjCcaMa0UmOiP5mn7GJ7JHjsQAlNNvefqRUoG8xaH1w2Eg\n" +
                    "g+beYseb+MzCUB1GqkbkIOc/pl7I6KG26t6bWzLBNF6muSqWFe4kHURCPT65SBfoNt+w29E9KJU5\n" +
                    "Y4OEVIbjRDCUV/dW070onc+0bAYZojQzCwlHCzaBWGMkQASzE3IrGjavPVSSccyQSbFZZofg5F+i\n" +
                    "yRi5Ky6sQ5oQfJf9IAQwgTNT19alyg+Lu76dS0SagLV9P3EqJGREwYEKSDYBaKkS9k06CL6fJ6Zd\n" +
                    "HYSjln++Tph1/T5qnjubCjgkLK/CCjlCClIk2DnaFHkvvoNwjdDV3mCfn17Zdk+Hlmewn0qt9f5p\n" +
                    "rdgFLlJ0HEiz+7lq/PEpfMhBKoLqgFyK/DYTgTUfM4yRJrhP51QDBgqYDayOWFcqYB+iHk6lvSuc\n" +
                    "B3/cWSVTS7xudJ3ReiFs5qXZHWHuEQpZxWCxhA8/b0k7znVWuhzYti2xySxinSk6bVJi1ykPOnW+\n" +
                    "OcvAEIACYq5wKHyv66h2dLvnCxloA3jCUzwikX77z0jUTf4j5M4+31iLNmL1OUhhNHnKl5CFMmiU\n" +
                    "tUsTXSXj4NQjoJ6Z+6qrGucB8iypvF9ccyARP+FaSYzwohOFX9j84Vopl8M/oFTtrIs50r7Lqc2e\n" +
                    "+r71CZPmkAt9G0YP2abvO7AP/WvY1CRW6NNjA8dIzmpJLrFPnC/y0BjBjH5LKDt2PdwPJ+IvRt4G\n" +
                    "dHZ7+LIhnU175BWrw57KRynphhR/w6QhBOnjhrdc9AZ6N8DLpgqfoFbzlCYhNzArNYHL9oj+mZVL\n" +
                    "d4fc+UalynKJWYAcqWWVYgtVim7E5uc0K+oxAe3FXfHOrQXegJG0Bu/C/3m2axpTcLhfnLObXCB8\n" +
                    "8Y7jOPXprB0QFKsX0AwlEFhsUUcEgpoLvBSOVWPSRAT3udEYeHsqfZEPua0pnoq2xhN8NRNqDjHV\n" +
                    "F7/FNmbOe3YTYMfMyQUm8A6Hu3YqVYE6YPqPE2YeHtRLLTyeRTkMYuxhHeG1N9l9XsOmkj8lXYXw\n" +
                    "e+6iPCHZJkrSPARxqeFzgX6a3f7xkn26uljqoUU01I3LWo5De/dO1bpXotMXgn2tA7khLXpK5JXx\n" +
                    "R/xgsKimXhg6QuvOqIRiP55aRPxPcfbsQmWdxAu+3YYnJ2bqX7927d0cBmD4ylTGqTa8JgISkD/0\n" +
                    "Cv93QSynrco/yjP0niP922tF/YdXDDZsaYWeEYMkpcrJxonHHUuujdgpWTb13Hc7ynPVs8daY+eC\n" +
                    "M0hxSEsR3qjQeyXeR99GCXb0oOUZ3MBfdA1jM6Janc1u2a6q7799HfwKTKquINiNO+cTg60gas4K\n" +
                    "/EhjfJ+l59UX9MD09UbpegybIDubR38DS+Th7JyJG2AaeGvFkEeMnAuomiBQ9wl/hIadntyODdJV\n" +
                    "h/kp9nUa1fLIY9F/yGKjxnxVLk7PD2V9sVavG/Vj65RvQ5AhUtQvaKUoEOHJzBVU7nTmNOQfoVha\n" +
                    "ZQRsoRW6LV0UJE5Ah5tujfgkW3X4FZfVilc80VkgbactXihaFVCooaXxMG/y/mKrT4Q0Epej4Vfq\n" +
                    "kYztwB8vF1pR1GfYsg5fGqLPviHOtDwir6WJjJoRHhFH681scPu+Z1nc2+XzSjjhfaidyiUqtuhq\n" +
                    "YiiMqXQaaMRQ4+r2lDUitl2lAr1+SA+EKbESUnflM+tpOz7e8vtY5i5eXbdzVdNH6F0Nv6a5WAcJ\n" +
                    "xxuiQ7AwE7+aUW9rUG4BQLDPCo1tCLz+kFBFzrrcXITRwUIRpQHZARAtoMN+SHV/Q5KX4B/p7Ybf\n" +
                    "Fw89b8+VfQYXjRy+ovdtS8HAQPu3wR4GakjArquXn8o/HU1x+Fiyeoin7YAjQQFRSn4eU4N0IW1p\n" +
                    "BiFa+DhthcuB4EXx8JifRNDaotWVSD5JQn8gI7Brt/9R8JL+XNVAi0cTIPGM+etJOMK0lScs7aJz\n" +
                    "+0ImtJ5ZK8Z9yziPRS/Okr/ffmT6WCD4dC8lPCsdkpK2anmvT02B+Enqum9ij9jdrDtREU6prij4\n" +
                    "HAs7MKIvb51l2ZjyFzDJAWdYugi7SDN8awnXzH2Eb5eakjemhGOqz8KDKT7ynjp/jVubZXFnz5rK\n" +
                    "HL4uTXTFinz+ye9LGJls7I8lTSdO2FM6edv8bZa46kkIiiNS4KL6+RaF2U895WnRRbpnRav/ueeQ\n" +
                    "2zIibhZnW5FSJnGX0aZdcOtgO1YAXm83p9H044BeqpxNL8r8Nu+OOp4FEpt4THALC5VCKdNczc3S\n" +
                    "xPj1ukNqVTs8k4ZMuweGmPG6rZPGi5C5nVXYXzpR0R936juyaqzSYErUmMG/RZ/Hb2ePatWEQTCh\n" +
                    "l2wNLLkRmFaiLYsxObVEWq77ZpyR0XCdk5iTOMi+2slgQZ+u5wo8exnX9MqiBUiDRc2e38ddgOsS\n" +
                    "zoDEabU96XsvpeHzeRoWkQ6ACjDZpO5+AEoVIbyU08pKThHgylMcEB62YlGjosUKpzAiuMvf/L0+\n" +
                    "yD9rvBvwrL4YyoFwrPrVcqLveNKDvlt+sv1F+2NoD3djiITnNw4MZMzOWE6cjjkihGH7DJkBRqNX\n" +
                    "TDkuzHNO0cLrJpotKaSum5EW+xp8AtdKoN2M/GgHBtnUKdjULsoXLwLFZYGQuzC8Uv5Z4zoPqOHk\n" +
                    "audLO6lv2rRtI6mMVBRwaNbunH7jHqZUERJA3xAWGdoXwtmBrjwXIERycctxT1wPTqr81RjgH7HD\n" +
                    "C1yzM3BYo0WYqkxn9bIiB2tZxiNi72HO1/XqV49szneUd7t0LqhxB1uaR0Rs66J7PkhRtdZYUoWX\n" +
                    "a8ntFjfoWZHrgZidOiu8rGegnzV+zJQxtEINdyWeUOipFtslzXrjiAah9nYpfmJb41ZeQLGTXV27\n" +
                    "wCbHCVXmT/6T31E/ThCcJYKETpIoAm5+9K/iXtox5edhSaFtI1dQui+s7khmRk5aWMRCYcjzXbLL\n" +
                    "7aOgU0Cb0fqJyqYtw9KZhbfq8e+5BRSYX+3bCZp7Rnkf2EJhmI566oXhABn2c62RifRe+Ql9HxL1\n" +
                    "QMjs4ZRNAm245SBdv2Cusr05URfaWYETyvoOMaTgsovf49f06DtmCn/kZ2kTHJTM9Lwfo5zih+DW\n" +
                    "Enp0N7o/5AprVD90uW6sLiE7+0iOqCuRz2couhDfhcyuwdqckFHhZ4Ro3yoHMpLnW01qfziNPYzS\n" +
                    "KzKYLQGU/cJphFxAeO+/406eIUrlonZz3nhzn5fduhUdD9vtXlm2oueMm9wV8k7FvhtWNHPH4JPS\n" +
                    "g2YjRZT/YPaoexSH75BtFzgztUghkhcmLPAsN9Bmak5xOxYbCImtTHzTutJLJRbMe4S3FNFHkmo8\n" +
                    "XwWEpiS6UNhW2jGXfhYPe+Wkpn0L/fH26kWzKRPYXPoIvvyCoWWoZiXgfYhavwuOkrowykcLR9jI\n" +
                    "NpN1kJ+D8cvgPc5cwZRZR3ZIHL3jF9BHpC+e3fCJcHp+EFFZ7bSOhgwoFP0olMDRcehPJNVSWa2F\n" +
                    "UTlBxqHUCrfGzNpRlZm/FpAAY/HTz5wBHb594EoUHf5wFr2rrB8tYAU71zDd9EgH+ZgjD1f/KcXO\n" +
                    "85v3G29AwsdmuGKLVvVP3NONuEJr+mN+HsgMZE962LbJNgndg6vy9rQSu9vrWFwr+4hyKID6StWg\n" +
                    "cWbCvPhJ28M9amjsk9BCH4VcN3kfkh6ZJPUH88gWKl/jK8jvFstH95kCSdhxcHpHR2jCSSSQcdow\n" +
                    "NUILarq5of1IxuPS1WZr+CRGV1GTyYUdOQmYOfcPa+vJ+n1tBEXp/38d2ew8vTAvuVPkIi8CyTKx\n" +
                    "Z/mmT724W1xs7hOlPNQv6lSkE/OOoITMV4qIziG7N9WedraRcOyPTo5SVPC92+/Aq1FVq2AUzHCg\n" +
                    "er2tO4tJl13pfEVVNsm2EzHWX/5Xq1LzSnawTZdwyQ/Qt2uU+rF+g/18RmOb/5uItxid7PgTJXTM\n" +
                    "RnOvO6Tle9HwlsDPUUVVC/vTAi8/CGY3uELnJ9U1kirAxHjcoE0sEJUtGAmgF5ygv6XTl8vXnfbg\n" +
                    "w8TRHJRr5URf4+Au9mgz7+dZi1x2wz8tZiYJAOREvfBwaR2irp1n5JG02lcvur1Ont9XC2mzqECy\n" +
                    "D3vH5ByQbDFnx1G/vA9+nWyhrW9wqmSv0zqHKwLe9jA38PWv3xNO0sDlj9SgkzRS1mZlrWyu3Epz\n" +
                    "wdI0+3AbMruiQLl+bMnCbEtH3PI8PdzJqbn6VmSXhqOG39M7HSXru8g1ZFNflQMoiFRnxofbeG3D\n" +
                    "LAEMpntI9xWfsd9cMfZ2olvlYL3jVxgWjeHmQKRUSr5t7mvV3IHEO8RXW6avXL5P6+5GHxQXHDRu\n" +
                    "F0FrTLTxsJLcvb9fQrpb8f7fApt19DWEd/PNE6u/RyleDad0bCsafxmt+/UOPWkwGCEkklI5zYpS\n" +
                    "5qvBDa9Kn9Zott3ltO8lHxg5DdgXEGBVjBZzC302OeAr+wTlhkf2si9N/d6uDYVFgRLsSykNcveS\n" +
                    "a1bS8P2ojPqrPzTdNLV+MYV3ig8bj0p8p/BvtM6ItSYJkx5xVc2CUBl9qoDm+9H1o9YjoD7mQ08/\n" +
                    "NC1XhlaTlxsOJGbVUgQoKK/AGgWSARlu/EJ/40Bw0myBjTwamqcjBYOIM7gSwOUesyDqvYfnl5k3\n" +
                    "sfiZ6oXGRFyOVM6EUQ42Qmuxbl00wKzfgPInCAHc91+tV8SIPKq9h45l+hORiurLVrAI8Hmh+RJ9\n" +
                    "OB6PY+GJb7zj6gAPZ7mHM0jF4BvwgHHknPpKcKeNnSOxK2YB9Uun+1sk4aim2KeK8tgv8qEph/5A\n" +
                    "PvOeqb1MrM5TciaZHyTCUl0TTNXuE4Cx3gOxwavulL7cMNDKv/IS2SsGYq8OGM6O29UuTL0f2ciV\n" +
                    "ZdPIlOdAWyfmvF5/aXD5ecz4betDeYSQbb/F+FzT0NT6GZpHb55lDMbkxOb1ESuyeqsdZNesaUuW\n" +
                    "5ph/vz2futY3Gvb0/AS+uiOwi2tTkAart+q4f8d9D03wvNa8h8AxrD01qYM7YFemdhSJvFlMmbO0\n" +
                    "YDftJUL4qqNOU14a/p2XhBAY+T+l9XhJrHhTyKUxrprVoYW+eDMvq4IBF7h8mH4v/uGyHF463UvR\n" +
                    "6CiCi37xfA+8NJ++G1Ne/UVHTG4nhmni5hW14ioEQ1mfLbiReZY0+huY/xCc5r+24A4doyVsvdpN\n" +
                    "33H6R1VNmz8pntCqinReNiAyV1MWw1JRYcnT5ZRYM54unq0Bbd1eorNBbLDyMECS/3QEdaWsy7MD\n" +
                    "1JdkjLvIf4FOHjlS647ReoR6IiE8GTpb+yfCHYktLf7FSglPSgeFxOB3FlMcZ3ng+mBCbc8p+miU\n" +
                    "qyCIbb0OqYbbsdszEqQl7R0fhbUYmdrSj4x+kzeLCR/+COLO7lEWUBSpiFdiPgh9qirR9y8C6iXt\n" +
                    "NRgUYlaWg3qfQDTK046KaiGu9TYTk3hRGbomVwcpirxQPhDeJodSSQ4aD6VIBf/hCUKBRMpzolZL\n" +
                    "bczUo3vnHavD935krxpCtABxk8hdoCvYneDzCCExFbyipAws4nUlH2/La4zj3FhgvPomOJ8/TWGj\n" +
                    "sRmYNw7UMrNfR/OGPe13FwcU0CoNDztO8DOwcjgNNbCzZyP8mZD8VjxXdxRb+FSGSsQbtSLqde53\n" +
                    "4w9JnnjvaQWkuucTlR8BYctUymAp+TC9OZSpjyo7ZoM+rX3chRM4m0VEXBMYSnaRpiecXjVff6XX\n" +
                    "VOC2EiFD16QguV6/sj5AMorbtb3DPzK2oU2A9eGfa8nHTVUTgThoyvAvCgr/sRCqB//1lzDsm9kJ\n" +
                    "3Ny7MgoB5pYcYvaaNry4kWus+2S+FTRDj04UrmM13TswluJr6/25QbZ/XUjwbAFlhMPOCH8KqNtO\n" +
                    "Ll2pjy3REM5SPI6Qq0zK28HvXbUg6upWvbKhCjbZZcbzGxsVOPq97pFFsZdwnsth8Q0RoeJvDcuW\n" +
                    "ya2+2YSnCIFD0Umv6MYJ1KbH8+yacrIxRoSxx7n9S+nd3/YGCO9Q01RbmAXluqH6PTPQzqjIrjOx\n" +
                    "ZeoqbgXMiZVJLyfglGSL0xJxAK4dmrfb7j3PdCj5qp7yzwHsHRN50sMjoF7PjScouFAy0ytbXNhD\n" +
                    "GhmfRvxE4AaH5KQ+gARjTbx8PiuXhOYeC61o54WpyrxKmDbKZKWgF9TpSvhrm+Y8x6Z+AKdkj3NU\n" +
                    "2gqv1Jf//RfQCNTgChrt7eJxe+/3zgD7mbw7MYNCL2IuX9cLV5P6gcR+dwm4pUtLaE7Xz+xuTENa\n" +
                    "unC7vDpUbOSWFFQ/tMqWvxd/kpJbAU+eG60fdc75ADpIjTkA2yjf6Yl38kBYYXkmKL7ygmec2N+S\n" +
                    "ixQHiMrGykSJ1VHloJXx0CQ/fVZYJ7BtwW70G0N7NTqtwdRJ5TngKKiA4K9Ven9QW2KNNPcwqRcU\n" +
                    "r/v4cRdmX+bLixzIuQVY1kuezL5fWp0Iemy02fTcdxaagF3s3+ZLxJr6VJ67DCYJ40GhRboyRGuC\n" +
                    "Z+zCaT3omP4QYO1d5kQPP22Hg7RGdq98lOtvot809ySqqC1nCx4cAqa7W7mI1F321+MsxeIASF0r\n" +
                    "n5GR8nkgDJR69hqe6ZO1/06nfv0JDJpRlUnuF7t2jx4FyDCI5WO5Nfc/ipCsmwvI1ZxXU/DTdV9O\n" +
                    "pwjzY/ki3kHBSmjUqbA5E4YQdFNGtkXMO10x16kKRC7ljB1MO4QAP5dRYAt4DkmZWUs93Fuj6I9C\n" +
                    "mDYwnVDi4QpI5SiMSBm4jTizqXVhv+o83Bcl5xBy3mf5FnDQfar0Cbc34tOUvTQusz6eb6gWvgx0\n" +
                    "u5oPzG3IKCXRF5qFUUmrRHjYiULduVD2W9oyGlApyy7i4nalZQBC00W9QESF8u/W7x907VC25+ep\n" +
                    "UDuggw76Vl6AUXVo5MaubyPZzWPwC06QJMQ694cfnsOQFeEzsGV3fSqMXDc1M3NiG9HwzTupshbV\n" +
                    "P4d796ARtjwDFNeGRWNFFg75PJ3TutMrIOdN8ABArRhgoqBCa3W3YJCx/1af06/r0r4Hl7wHkJ0m\n" +
                    "vnRY5Nm+8r103L9IHdGkvyPDUtQ0KZ5LVkf/EozPwrRcMWozwrX3uSYA0kPaTLIq2nYs/mQkq2UV\n" +
                    "ov30VZMbUlctSkScq9iiioxcJrdSklYqClwL2fIpBd1WEaM2CNcSt1dQ+hwXAMB+sg6rIcJ6zQ54\n" +
                    "qwxz2tELDSf1E7I1/7AyewY4lcazE2DiaMb6FPFVWbsgdY9h3GfBOE9kD2mPvvzfQH4bzoLiZU1s\n" +
                    "MNrEWI1GT4y+yGmW+o+vfRTMydL0inqunigz2J3hqllXizFpGQy0/xSerCc8GDw5TBEGZuHTRK2u\n" +
                    "0mQ9Aivtc48t/yNiVUF/vbPEbPttQf9Mc1DXThRorhev5LlUi88c3Tv3IiRlut0LEtA3ZiXfOEaJ\n" +
                    "Oq/Snw1lOnxu9HinytLrLvFgVbn1ekpo7xXP1flepjZ01xlm9/up2NUckQ2GveSLr8zk9567T96u\n" +
                    "sjs+Zkg1+eKktjlmKU+RWxk3TRXRFJ4Q5eP8pjRR3XjXDg0kEYQugocLn3uAdqj0a8oTlN5wDLjw\n" +
                    "m63ap7paFnvQrNRswU2GOAIzhvbqxclhd+IeTq/14VIqsnzw6iG78WccYzbCsqAjZPvHYkI6x1BI\n" +
                    "43rR6Qsdms/0kNtNifOkI0dMJWWIGuLZL2aRqhaVgqpaHamg5Emlv23FJROgWM8rqsyPzXUhiqGL\n" +
                    "TlPZpfkYKX7Jh2uYH5RfHRTq2aQcv/oOdkKFJycakFdiD50c87mzo8UkxK0D0f0h/vV1FPpHxiVi\n" +
                    "kZtH8d/KSdpKguaewIqxx+Tc4TzHBJ2RREoOzSHHqh5fbOi947mQDIAf9IwddtrJs8MsCSqWNdRg\n" +
                    "yjTfQmyVgQ6bdc7OZz1AWUDvv8t6GwD+kPVruUTJY8Gmle3+RSsn7fxNL8WSYjNnhyvTTMBcdkUm\n" +
                    "vRQfMh6ao3so6s3X7gWpvXQ/Brc3KGBW/dURX+DaOJ02v7+kknUGDeUN+uC1jkW9BSUfwZAc79i5\n" +
                    "59sM+j+HAgvNRLRkBqZZhPyjVOdUdXlzKLZ7os9okBdDkxHYnLNDE8W5Vpn/c/XbqJTou4vRO5tQ\n" +
                    "i7lRvMkxg2Vk6cxQXsWp4nSslpB/WjtHD/pZqN2d57IZytPtBSSJ+xdnakxaCG+b01C/lu9CqT3H\n" +
                    "mnVMVBx1JKKte7wX3oHp+pH97KJHMp01KyhHnYZNlnnxiZRlGVNAc7G+NQPAGh0m7+dlIoObBn4g\n" +
                    "9IvH7X65WysJblglTAXjDROuTY2fMoiWtnXxWjDtx2F5cjk9T53pLFawPWp1zWLIGmuHqlTHzWMB\n" +
                    "1b2pTRc77hb+hFVYuFgeWiDhPlNf/zK/IW/nWuXz0tDx0nZK0WQpQwipUn6a0iDwsPDlXXuM6GgX\n" +
                    "CIrqqA/hhee2GIYnjn3TgLwKHCWrd2PkCcFM7mgeHBgXmK+1Qiu9kb/1lavdszIzfLxE3GL3Tu5Y\n" +
                    "WESe0f9/HHtNszD80X5jQP2P3nYGKQ5JoHXC0YfoF8/jufw2BDjzXbzVEv+ObacKegAvoZpWdRK2\n" +
                    "pUk/RUPPZiO4OqWY7mRbCmuQ6PLLa0/eX69i6SZPo4AMQayBj+C0wkEpeW/bQOube7QHwIlWE6/r\n" +
                    "E0zC4Zsm/vaaqw/CSZ3OfLJZ/zva3hlBZ160NGeNJlD3brFX0pzrJZDr4dm7tRcY25rnddoY4nqg\n" +
                    "SUbDYm62cK7vz9JsCm9C9BcINwO7AWmvpWvu3CCzgcJUzbFs2pQOA9u5RSML3cCjHkA29xDQTFhI\n" +
                    "9kgOvG5Cdj+VMpLADp/8zYOF40r4fdf4GsLPZpXph0lQwv7Ok9EyXTeiZxdvbjJvuad52phMVyrm\n" +
                    "+k3L3RO1yQRF3P+hxrX5QlVjbxDx9cMCFYm5nerd27PPIn1fdCVQsSepo8k1w6OGI2l8I9OSYbJ1\n" +
                    "VSMrxuR4P7dMgVgW+OGnYjq3SSF4gOXda8Zm5tMDL9dbdNhAAX4DQDqcoqkt6sqZ2t6pkVwRIoZB\n" +
                    "yQSCcrg5RtDs9160Ql/zk92oqfNWtVypgOxtMsXrquRYeQo78/BO7R3Eg48g6Rh8khjiyX2f1gdE\n" +
                    "cofjZmExvZiyrn2VJNRAzkxRplllpOgb6q0i3qZdewGpyToCOMlxaAz3n1kYutmjj4VZ0v3WCeGI\n" +
                    "jbg9YuQReP2wgD0jfg2TuhWFFvnxi8SpPQAFhmKUhua8sT1MGmNajOfIvc4Yb1BH0TGLHyCBfkW1\n" +
                    "cMhkET2Op2TxgnOjThvzGe++L8gNGs0ajP7HDaHDMegwZplKJPs4bO3bE9cMXlVkYgDiAP4Yf0AL\n" +
                    "24NECZM02GqEn0fMXtgUzB5zijwGaXsq9M+qi9Pi9sV+j9LqATdfrGkVf964ztP/ITGdjUgSa59u\n" +
                    "B/Dm9EHj8ri73H5EXtH5f/7lzURBm8J8k6jiCEzbvQPV+f80q0NXkB6gkmQ7OhCha9G9At4PZ+hL\n" +
                    "zpngmfwg3+PljwF/+OCKESK1wT3N8VmXirtg3yE+XVGM9oSyXpapKYyuSXevgW8+bD/6XyKaI/Jn\n" +
                    "s2twGQFtBR+7kHF2i14I9vQmNUs13LFPeI7h1MJJjYxDV8m6uZERGuVbQmOECuo9EkG4R+d0NtKu\n" +
                    "dovz9cs0hNtzyDEoDl4b5hQhfVFubrnIuGll6kIHl1rG2XhtPp+eli4VC7ovtHgLHpRvKfmEJmh2\n" +
                    "ZV8PeOrzi1/w4NBGbTII3iW60TZmVuoHM4PLbHeI9JxHa3RP21PEUw6OcK5A2ei0iC+Hhc76HGPa\n" +
                    "TuhudG8Wz1V0c/bhN0Yqwh11EnX++RgEIPHeHJgyemfjjc1+0hrivPs21UkP49onqdqTSXQaUq9G\n" +
                    "FqXhY4XZ0Cbo/r3IZ46VIOnprGyJzy+D48gst3WHI0kuPrhueD8jKzptUCfiErMsmNdLTpCQ2ts0\n" +
                    "0DzBTgNP/lwGCLkm+Kvq1wvu3GuMUbLOq3Sn3h7kHoxTt8Zg7xuVUkTaulkWw4KM/GrlX/FNbqHi\n" +
                    "hAID3Z9HIS9/G6bsD4PRM7TzBRCwMmzbc7s9Ku8u431/PYvQiini1qvpos9LF9b7mQZ90zofg7oQ\n" +
                    "x0TnAP5WixUQHUuu3/OtxxzBFNuhzbK1P0Scngeg0zOxtJeFA/RkUpbPOMfXZ3YN9Anc1D32UVVB\n" +
                    "Bq41LzjJYxO5kJ47pGcnmyKS9rHGjP/kolVUtSmO6inlVMuWzozkTtRC9gvEPbK43k0phUZQDlQl\n" +
                    "NOX4/VzM9+5yBtN4n3696/68HKuUxIQQBOjuyakPCMTuTvFMUD3cZqL3UG24e+W9thADNGKJ6PjU\n" +
                    "Hbo5pEHL1LUAoxMwuehTH0ud3R73YQ4VhYGLyRNgia9jRq0ci/gDBMGWtN6e4OsX5xBLzoI33kpI\n" +
                    "IDzEGheaPDo3BdZpOOlfYIRoTU/uL1XEpUPSl9EkONQ6fnpGKerpqIQoibJBcWDoDxPC7CMHk6ku\n" +
                    "XrotMt4s0SkJd8qgTihYIXHMGsoSP2KRDQIxG1K3N4WyL5JmsT8D+VfbY93g2gCUroI7AKRJAAWM\n" +
                    "B5+CZPro1raMSQSYU1Qcq03dzLcgMxjz5N5rRcQ85j8SaNsMlQDAQqMPK5y1zGt9DUWvT6cDvNqf\n" +
                    "pV24bGssfrNrLughDmDoKVpBt3OCDwseOKjgd0tseArNNBFYVwBQgUIjwTyY76k95FMAVnyzupBT\n" +
                    "QyhCw4+sLGM1n8ilBYBvC1TTAYfVWULnIfEFIrBh7fbjOeWyuRlA3PoAVe6ybOaF/IymET3QSjwP\n" +
                    "ZG3/tCtxMlAWl0iC52LYs1v7DUyXxad7Zywt3Rer25S/8Rn4T58BXhNLzMxJYybwXYGJP5NrsaJH\n" +
                    "gIAqbI3SN+DY2DioMra3NLvWmAl2lhCYBEYxSmm+DRuv/fe7cBMjGRpnWIsaLXKMq3QF10ZSeFSY\n" +
                    "CczglzGb0isJ8K5gWRpEhMGuZt/mSM5oqlhmRvcx7IpLHI6rJ6c4H0CTuTdVQttdzstdmZrPOL49\n" +
                    "yF9DksAdDAI4zrdHc8QNDKs/7e5zQn4/fkAI5DWX+gU4YRpryJRcnHgV1ZzlJ1nsN6FTbOAWej5t\n" +
                    "NPcQaKGT/Q9/d9ZdnFpiqAEGVHAJ4rb1oGvUd8AQwjWI5LXjmljXU98RsRXErKax/LzPHjUQ+i6p\n" +
                    "M5o+T0/0sJOexQqcI+Ixbb2CiJx/J6JqgCLpaOhGB0QTl1R+0CZ/8lUoLZd0fWNy5g1UANuvqWfF\n" +
                    "S3k4ODeTZFB1iu/I8A0S8lFN3kqfk9CCn77z7poKXFzZibvd2X/FkeAv86JqpXMlgH+RZRGYy68A\n" +
                    "7b2N5D8xMaib6yVSQY8tSABazf3CDPa6v5KuyubpHl14PZIkaY0XfeZSKFRo0GpIcp63d3eIkHF7\n" +
                    "8kLDTZU0Dt6PVg/hlFSDxCe+liCkFPYew72q7aCxzPKeynkMpWdg1XaxIQBFt9ZHnKw2Q1gAt+Cy\n" +
                    "DGtl9Jfn6hIXkCqjYYZyigJWkCyxzoGZVZ4dfXTa/CrowJM4eFu31oHh1RTs93qVhXtwSEaYL91a\n" +
                    "wHANMogdBaSjsq1qYqTIZSauzw+6JTuLXuc1C0UaD8Ao1vc5wdwBg9Ve9rfy3Yf1G1VwxTu8/vLh\n" +
                    "tBsNl7gNDlKRY4Y/V7t1b4cjZ6QkLSq4NWQVf/JxkfgHJzMDb9zBWujiKzGV5UjjgGJl9vVBdqP0\n" +
                    "y/U1VE9Ir5N7cV4SlFskRzCETEubcc6THK6QcEuYw5oDI6Si+BvAFgsLBbooCtK0mptQeqxjJhyO\n" +
                    "Ix00hwTdQ/6BYFscJ+I2mFQ9Xlin++kEmxSXPhJQEzJ2p3IKcdiA1Deio0xSsUEAUqkIvM34g7av\n" +
                    "dNfReHXO21xsFsINIu1olqyujEMPuXv/4lhvNII2iCEru8S/NphHrwK5OqSDulqFpfXUHs+aF801\n" +
                    "AetDahT8nTaxHFQA8zIPj9syBrAgRljeH0g40j2E9mMvPV0dYeZJxydZ4s4pvBJ6qmbSEAcTQhxi\n" +
                    "sXsdbJBHQyJkEaQPqXeyvF/lqxYB5HaVl0967Xa5VccS+sj1J9to1+ABgiTNwTjZBWsqjLnNFcEH\n" +
                    "102GATs0E9KlxJpjjA+nWrADcHo7hBqYX/ajI026ZWNAqiyVgYMclBXV2TDJqxYKUcR3THajYIFn\n" +
                    "Js5Ns3pA9zSRD5hWaxLFC+Aw0BReXvPglshH4B2awSC2iwNJWtmUtKb3pHTP+6o5VuJfZ+y2QXSk\n" +
                    "o8Wx7gNtVOlyCNR3Mv23SBkefsaX2ENPhDLJhGh8xCgOIPUh0YDy0MP0cLjbP/uKXOEXJknIQbx7\n" +
                    "dwdGqgBlbT7KvfBO4TW/GkfGatTUNTbLxzGdzIAmJknPirQ3nHYUWinPbSVWKqbBg6bRPK7CAy31\n" +
                    "F7AoT8gZirNWg/QJO+91kJuAiRlOKOO9NkqCnxsx7cjITXpHaNHTlTry8h9kGffUpEFJa3uzgxY3\n" +
                    "27qeQ43pvTeJAx2yWIZZ0QZs62fn1tnAExrBAFYEvhf1/cGz6Xd1lJUITPBQspmqIIeNz9rSE7y0\n" +
                    "2Vo8WxIegUJYnlgeBkgYNA09c9RQQMlUiImrm9dX4Ojx32OvdqxFlxAHjgByOdL1DBXrdAJESico\n" +
                    "ShsU+C3Abs7GqN8AV7f4y/8Xg5Z//e+rhRiwYiLgPAcLvjvx9+RHCD8vCSGcxlAc5B/bne9+QAHL\n" +
                    "BJGk3JquwmMf8LYwbFDDHKvejhPph5y5VgX3TKqa64y5Bnxnqe4EJ5WG93owy4APXmz4dBWd8XuK\n" +
                    "18tadeE0RiEDbs/1e6ahIIUijLFRNZ3PNRZspdcuEqGqA7juDPtLgL9Tgq9eNcZPKejb3gfdlhOX\n" +
                    "df8eyw981FqDp7d9WsPmFHOrnLGEm4y+B9DLoeXTl9PBN/tGNx/hBv0V+bsPsDUlXBPsWyOTNZzK\n" +
                    "h8nqaNlhH9DYLtvfo6CJMlVoF5/im0CANKmsckEgYkuaP93QHquBSr2vaItb6Gdmin6qRC9ZB6TU\n" +
                    "fL/eE4r+aLJSPNE9lEfwjfo9oFf92DTSWgaFKFL52//kxux+gJZQzNabdjA8M1Kl30iLWCa2ZFyL\n" +
                    "3FJdAmll+taAdqM7I3VrKmttVKFdeiYSgyj0lp34kCzUUfzd74BhhjkBrq3T5Ta3fJMAHb/hwpEa\n" +
                    "tEHXF67JAVd4IeoQKwekRL3c1SbyGBjT1kvmGl4EtjyDiReN4AcHJAlyYsz0JDGb9ZZOWef8YbPu\n" +
                    "T4abywDOnw6NeMPVobfpGhh9zrJycdc086pINK+LqgRCI/zkvvft9w4RBPC5ZO3eKgjUaITIT7xf\n" +
                    "sQdJSOpG/m5ERn6cmp0pca/Z/OexvEYZgoZAq3QmTwCxtpG83gDabXCfejt9B+5EA3qLgEemqqh6\n" +
                    "U8MoJjvpyOdnoHpIBdjTWnOX5Ts7KQlZOZk2Pn0VJ2gZpPNZ8lvlPnd2m4NaVOOfY1APPt1d96kQ\n" +
                    "sDAWkcIjIWh+5FPSE5K45QD+AtPujPO39wwOO+qvcftcAPE7ia66ZBtZ3Cyit5ITPjOmmD3SRWW5\n" +
                    "socdZrKZ1H5p0y31Fmhc/4YFICU1z7F2ZMMhlS9QxM4speQN1etg5yn7fYre36Lwyo1FIhwVlR/b\n" +
                    "oPSAeihJL1Y+iQbFgVKw808bG/TC/AF7H4zfTS2RSxuf1VptoqkxRV2YyNynv7b0ePxg1qZhQqh3\n" +
                    "xy4T8h1y9+nVjfyXXdq9orbL2MOD0yv/VD6ujato9CAKxM2oVoKdrPJg/QlahB2cQGw+9hAyVHqG\n" +
                    "nIy/rPsY6F4uqkCSyy2P/ODFKC/WlyASNVOYQktySYoRoefiDzw8A58laNMx7+M65gyS7mhZLL60\n" +
                    "QOOzDpx5UsTrUtKm8FWWr4cYn/WZ2Pk+m6xbuDSbPssl20WmEE7uMiuGCpi/ex2dtz5tnQMnecRc\n" +
                    "w51nPC6PO/YDCzmsP2ygjnaVNMYX5GroCvpKCpdGQvS/EUEUT+XzFxhYBTJGiX0YxaMhwFsLM+Mc\n" +
                    "9i7IGAf2PgOl0DBvECrxmZod/Tl1+ItMUzd3vzf+JmgN6Td+uSLVoZNyFAzn5GdSO2aX1IemlShu\n" +
                    "Coi3KddxCpXRki0HxHGWwrtzWuJ3j9phH9gFASEb9WWHFrSYbWrKHeDCcgwl8zjz32QyO06kXBal\n" +
                    "/sbqWuBqwJPGP2rY7hGhI+q8XFRFPdePTahw5ZCfytQYeC+JxW638F6MzSfLHQnZLc/ez1FImyxo\n" +
                    "8SZxAQh6fsuQQkw9XMLSvB2Ur0yb37/L6toCevI7nzMNnT5Ab8qUcmkiTZG1F9bEv5vLiFurzwg+\n" +
                    "I6Udwtvu/+nkvn+dXDgP/K5mbbwLaCtqRoDZdAHqazKwf6XDBpzgiGy/7YFwbSC2wZBIV5ckwivY\n" +
                    "7DC9tEJoZpHY8oMQkmaUt7xAxMGnx/RfIKZkNC6qmf7u5NZBeNQzwSkX2Uq10zCT3W47Ncbl26rp\n" +
                    "GnabibT7Fr2fhtk4wkJejBKd4F+rjY+wSiz0C4PCAGehZOrlaGpttlmvY4zHu8QADOOHU37AflOI\n" +
                    "3W2NWWwayDa5wBcvUt7QYY7aveAYTH3DRe+PKtnjpsgMVjzQkF/0CmklXaopMTkMB0D/m2G6fC5h\n" +
                    "mAGIT8mLpCgZB2Iy1r/jYOIGRfpH4U25BFjD2O1oh8UMRg8D215RP39SxMhxH27LpcGgswYqVh8p\n" +
                    "fpkxXG5JnLCzCfh1CG8xMoug9zZj34uPygtS44uLg1zVMiV1TiPJinPGYIDAMyF38jh19JDniZ4r\n" +
                    "BeMaNDPaoCf7Sze+j0Bk2PGOskHk6IyNp+flbjKI9hK/txofq8K5n8pxC6tjtY4TrxraS8OOaeD6\n" +
                    "QywdcD9khprfyI+Y6eWQZxnT8+dIsRc5+GPwPsJOO7mtj8+71EubTDLGS04yJRYRH7RvGOmyHOMD\n" +
                    "jRoLK5EuhmkdjCUgd547a2qG5Wh6lhPsIRe1D1j6Cq2YveyL2o2IQv9i/euaAezxj+L+G2lGdxUx\n" +
                    "Nn7klQFAEbalN+GV1Z+NivFKX6L0LvQ1A3vC7xt7b/4VXqdihtCIYvQC1lV8kM7r0H6HoYvXI5cX\n" +
                    "Hc/oVsvIro2RbaYDpOWp7Yhn3HrxT3fy2o/8/0gRr4iGeQl78MCdWSExGB6PPvbh7ehCTvu0Uq1a\n" +
                    "ZpVqgt+SDMJCKAeYI2FU1VgS1UuTrb7lsq7/IOg9MTIn9Mm8sEn/Z6RdCzxsJMgnJy/C+NQ24w4I\n" +
                    "2S6n+7EO83Sd8wwg/zq5enbQ6UOvfGBOj2CN8plxRbrUXE0nvAcXgzc8t0VpTch2MSjlzVpSVnGC\n" +
                    "NoxHqEGjbtXSyvPw2PbRb7iwCYdY4gezGjmuUIfqyZ9ctDk53DbrjZfq8xYiL4vYQHMtfNdvJTS5\n" +
                    "sQWnDcUCDcSqqC6vVILlxBd+KaELQqVOaRtEFskEepMM/109+6coR3Voy1ysilpAQ8EDtAYH4qF6\n" +
                    "Fyar3lRNanjMnU3WRn1AfcRPRj5POuuCWjBFgWtgVXKa8e9AWUg9L6A51w5JLV0s5EXuD4e7RGJw\n" +
                    "Tn3gpyEA+Y2MNNiCtI9DDL05xW/8Ag4+AeQ9LUr+OMDrSoJTsMa4AcOrvRWBCB81dJVdwdC6em3L\n" +
                    "CUNPS3FEqVldktNCnl62dy3Gma0G/zwy7mPUfAJrad/DdN3Gas+vCLsjuYiXPowEOPHwPzG2+aCt\n" +
                    "PnuG9p3G6OwLziIrR3L1hYZwrtGn+ANBKPNd3ycZ6Hfl3ePH/sken9U2cFX156+l50MzLrA8RqvI\n" +
                    "B7BE3OtHoCKt8t/ZStGEq5qdLYg9UfnvTqMB7FDZE47mq5lp9BM4wlaxTEDtfEZrxPVekiQn19tA\n" +
                    "2rpRUku8nqfLw592wq6KtZyqdu55fZYkKN1ol63eF9zWXJc4uSaVQqfHVsVSHVm9JBywz33Sqou8\n" +
                    "+EQH6G0rAlMjRe/OVQ9XAMc3HffAGZB6+bNlKkeJi+d6d1RCkCeN7qOkzOZ+5u8/DFBOCE/U5gVh\n" +
                    "od2jp2oGEjgR3nW5XGiZCoMTUkGX3E5FxD3CTkN0NyjWBCiOpTEX0vYSap87EXwbcoO0OqpvpStJ\n" +
                    "L19Ib3WQ9+Hp1V4EboSmHxlxvBJyQ11GEGRf/lmBfptjZ80qH1ax+u2DfGGrGFcCqZebmTCSCHQe\n" +
                    "dzHH2bxMDJarDBYLBKLBRE9IUa7BJ77Qcp+fDlR3/IoQQ0FmiIvhN5ChF7/hevsrDJ/y4/QiLO6d\n" +
                    "fkSLVGJC4bWsZ1lygjKkfie72sffIhBWjf8meFvIzhHxpDVWsnwwZHrqg2KecqlyXMtX354BfLfe\n" +
                    "KS5ueHwR25A0WQdXaJHRe7G2skHGLh9lkt/D+DQ/fkibZkdgkJw+Qh6DLKDFjqy46T2X8RnYlNFV\n" +
                    "kV/olMSgvTKv+U529+6jnyKCjW0HCyJzKeabNgTF0TQD9fKLHNy+KeKoQe/ndja93n+8StFh/ZBQ\n" +
                    "9ccQY9aSQwM249VkpWuty9u1sB1VnaQW4GviAxE1o5iOasV301TbsBY/EucKpQok0+KJqrFTbOVT\n" +
                    "9M+EvQD2Fe46msdBqApKyFmhcptupNKuP1Czx37jfkCA3LMV1C680LsoWs4duHe7S91Dav3UQfpv\n" +
                    "LrcenIbChR7hHvBNjOuiXcwlerEHKJkkv4p3AdyC14LzAGLyNz47Qd39L88H7Xh+SWHcrHb0BHqY\n" +
                    "R4XftkmaCUBojIqz9nshJmGF2d2zSrHHEKdIz6IgXjxyRWJlXHq3cY4TfA35bqDPk7SmquZkg86H\n" +
                    "57SeJcWC+tBsEmqjspEf+1mqmLx7G0vAJAYYASAYbQ0DwI92Uc2wXhpscfR+2x3xtWEsOq0lMTVl\n" +
                    "NVYwgf/gMtHL0aSxopWuDVEQqRg++wwzsMJz/Ry1On8YiylBWG7oatnhY/qDsu8oOxhAKmDRAQ7/\n" +
                    "YrwXdojCef5zJOfHxopouR4p2lFCeHHznBPEyEgfKRoWG+ftl8EVwwp4v6bJoEPikDf4+C5l6Gns\n" +
                    "FmbwXFOoSP7WIyjI5OY/UUOGwQBX6di8uruiPXB3RKU+swFaJIN2TRNGFxUoJAeTtwjvhXfX/W1X\n" +
                    "s2r/hu7uGoY0ecf5QutPTrdxSURFm8mcmpAemBAonWUagPQWGc+cmk0mcMDYycvfx/7Wd/l9GQa2\n" +
                    "r0f0Xobq4ReeixM02NThBDxxj0mtNYJXSuJESFEZocK5eCkW7Ogs5JkZK6MOdRewJ9WgBgHZG5tP\n" +
                    "rKwhoJvgpvUtcQCcJ75/2gnji/Pi3Tyw8/2JD+aXL/nzRI7lk4Tmzz79a/UWcaP/cOOyE6WH5X5l\n" +
                    "UBEUdVVw/jbyN/4xMZh4RXmVI8kdHWy/7bOQ2VOKe0lBnPg3fW1iGzDZ4QlI7WIFw+H7Ge1Ohhnt\n" +
                    "71EA5kdFQtZCixtL3aaudF3BS0vsj6tdfvht/eDOu+Tdnkb6sf2Dzdw1YvgzG+y3EO4F5tSNPUne\n" +
                    "rpHLJzgwJfr1KNO8t9nMnOYYviumKsMNSWjXA7BFQSzczgJ3C3JSHIRSM/UL0CQu5wkOHrK08iMf\n" +
                    "Wkaq43aCo+HyCBxWtM1avcQ9tis+ISCG0ME8JDP75nVu8sxWPvX5PByad/SoVoae65rWFpqmcMch\n" +
                    "UacrlZFaG2TJ4SvdnWqiDmhtBK9fAi3tNQn2Eev7ix+uEuzUxlU4FWs8ISA49rfmqNb7EW/ZcPso\n" +
                    "cdQM4iwRzb/4O2WsPRa9k89fXVC+NOGugGO+xU3bprEpXkLz4cG7uXxc2OfTiESmQIMXfFhxKiqF\n" +
                    "Cb3vZzVM0mB5QbPEqc12ynzH6rkPjKTYIrVGoObdPYpEUGdhGuIDilxa4tOrs582XK/KrVgHhiBt\n" +
                    "Ngz/cxbz9oXXZss/iVnubaeWWgAFry1VLj8HPET9btORgDWp0YFxR2mnDTWqPR3bfGNLVDNg/N1S\n" +
                    "kk9/ZlwEvC9nG6QtxdZ6Tuo9jg97TXkWy8Zz2xRbc+1TP75CQfRX//amrDeti3PWBtKaZODUBoiO\n" +
                    "y5Vn3RpMAEE17sVlBm5ZMeqaVYqVYwgvI7UHiQm5Z9Jm7KI9NUb+28LjdlRYS5Y4Zr5lTW/Kta1c\n" +
                    "J2YsLuSmp2a44h8ZKcgxkXSpxqhz9wfbbbf5LfYtMLEd/g5zb5czOoS55h/I0I46PYpAr+DzOr7q\n" +
                    "YRymS4E38w/S+JYF49M7B4z0bJU5R8jwumY+qR3OVe79XOBF3+5qSIy1uRypFzMTCeWIhL1K+MTQ\n" +
                    "IICkQmjsBaGZYIh/N9BjJjmQDgGA7tDdPtshlcAJRAsuW7v7S/N/Aa0wF8FVst5AoSYNR0M1Vxx9\n" +
                    "a2rauxV7HWXIzfooOOSVWLazt1vfb0usE+RR/IR8Gga6SbShCtFLITvXA219mcU3zrpGkblqxJDD\n" +
                    "4B9HelXbQhDeIMtmY8xBwQlG3Qs36WaqSwXHgFLzWXqp9nntedxDYpoDbS2hIh9yLq2cR3ZP7vIQ\n" +
                    "2L6xrTjVXwCaGyeSe56xmruCbMurC6a5gYYg3QqZbvyhPavoGz8Rv54GRVLGQ65pHng2hUcDj7Ec\n" +
                    "KqEJ0utz5RTHzZLRsInbTRmm4F22La18pCyzgZDQcQn+v7fZIfI8BV5C/MAPdq1M0S3q3XJZVIIg\n" +
                    "kEIbc0UeUa/MvLpDBGQ2vA6SZvs/3v5hINMHWVEfjJ4qSAy+cmN/j5J9KQYET5SglyOOg1YmAlt9\n" +
                    "Gf1R3MRNc2vXDmTm98GzwYEVQKIaaN9Dx9ecPkkwm/FZA7FVOF+ZgIPv9Trnw4yMJOXQ0a4WgOxG\n" +
                    "kxiHoThe5Td3HR1xv6m0A3Vfs3MYsd+Fn+4bhtPf5YYfL0p0MSbexXonluHUbuQkHJjFOsztRJrN\n" +
                    "pmOngPqhQGsGs0oPmb5gBhlwx9wx7h5Le62zdP+UDm1HTTjXDReh1qVYyk/yV3bIRnnCJs0TIgV8\n" +
                    "gJzm0vKbq+PV6xwzVzdQ9tkEOdpmq1QbKlnoEjKtmfKhcv8sf4K9eQjptPIvGpd2i7Myub4qGGXf\n" +
                    "VvpI4OzJVvEpzBqDwe/ZM5kYuS2ca4zfn/IzlFHSTrDw9/E0EvcPmKvzSqq5T2w6G1O/1l4Vvrqa\n" +
                    "UAZ2tTyB1cSSUfyFC/PIbinpPhaz0vAtDUSrVfq3fF8GhP0C/nxom6E2pErxZhyELyAlHSWfsY4F\n" +
                    "bwlwOgUqh8H0f5Sh/Yy8maBn4zPdedZjpbFrTKDx6qvTxY3Deaq54pWtZih3Vu4IHTNkEScU5nDT\n" +
                    "KL1d3XYcwshw5H0F9HxfCdMsMK8miuAaZw7KjFjYF/qBa9Zyub8yl2v2IASTCZ1EJHGUxtbazFiu\n" +
                    "GH+9fzHtjlp/AObPK4KLh6bhJgSKrHUvtCj48ombDQtMf6rEXN9Z/VyZnjeNhm7qGcJjB+tKWRJb\n" +
                    "IqTZ9YTDuee2KzHjjl8UOkVrKMThJh/68PLEGm16+P1iEiSC2niGWlzpxi6VTUnQDsKIRi2D4k+H\n" +
                    "4yPVffGCEKnadk0edO+6fpjcRiBURNxOQn5cvExczzIQbDx6PqBpZlqfx7YnlaAHlQIJ7I6am6nR\n" +
                    "xnVjve31T7BP+Xja3SgzLnh0J2kI7qXHaBsv2HqcSmZi2izmQhv4QK61ZEqur4Wif1B9JfnMe7/2\n" +
                    "2ytrTySC03pt/tq5Gq5/IHK3SVK5okZVSWAFD2kIHHankVfb46JK7cwQGRLztm/voPDUZRK302Iu\n" +
                    "sDVQg2FGIHYZiJth+8Mm9sLfbKVptzy08QnhKI3vJ8PHvd1reQE/zHSSj8ZQPDJg51GJfJJly7nl\n" +
                    "B78/8k8FXh6j/mNR/ej/DwJYFFIIcNIMj9Drkys1rOAc5eI7mG7Onr8uH/EAxbFMfv/3czbAn8mz\n" +
                    "0yZzWAUoI5d4Rd7G9GVc66ipBJBlQ6uxLUx+E4Vd4ASsjlq/XODybrFfWcldZWqvkBNtPVxQgqNO\n" +
                    "aA+Dw6PhSshCdLzHvLbSfOCHhq6t4hr+vigiCW5Lrix5/jmryB7b9cFVZVpvJ4G1TWUnW5zzo1Dd\n" +
                    "mqoHHk6VOfpy2y97f7D92jI0y1IleiwdqBEV7fjXeR5U+2zVwTvxCx/pwRIkXqbiOUERVSy+YOD6\n" +
                    "72l83cmZlc5BClBSoox5OZO2HVItR+QnJ4FD8vrscbLIta0iJS9L7Y28n4a5zLMLjqORuYOsHluq\n" +
                    "YRL4B8vtLISncd0yi5h0FSSIw/ryuabjM6EYK/V7UaFlDDhB9Q/IfbMP3jr97k9ErmiyPYPXPvUG\n" +
                    "XmHkLM4kUxZ9VTO5cDRNcYh2S9N+lo+fO9e8PPcniZ1qiFimlW8HJ1wzFxDjch19kNjpitPKypX8\n" +
                    "cpGVWDKs8U4LIku6q7PrHBeBbYTLpGbaltx3fb84ZNYMZw2w3eSPb9qo70AEVV4xa1M7OBY8QRo1\n" +
                    "Ixw0s8cTpuqO2BHwboBGme3D03S5ztft4l54ol2zjHSOIUC4QFyzfmJn7JifMgtuTlIGJzvHcNV7\n" +
                    "cc45s2nfUIMDsOlIIVjFPOn9F4f/1Jtzq/B22izXWwTF+XicFZEw9ZnVt93xSIlipdFuFcf5YveZ\n" +
                    "mrXkyObzMIRpB4sawfKpiVJlkWt+paBGRzTlnANBFn6cIr60E7zItWjeD6QuY4ItkdrXevX5OGyH\n" +
                    "S/6aZY/tdsN/+SkZtvD/pOULQTodB/TTw+LH+vuHk7tYOQYL8fq7WmXHvCoSjmpPVrEVJJR0aTk1\n" +
                    "RaUIyyzv9fD+d/Erf2+xr7YKzsTzQVrDpJWQyPmyBewfmDhyt9cCEHy8ESuF2qo3P+ykw2E72JfS\n" +
                    "QqNN9QKy9RCvMenFxV2sdYmR2j0oawGAXIi2ES5mRoogdQ4jONL4+TC3y9ydcz6o3DxsWnmsTcA8\n" +
                    "VhWs+HVWgUwgq9eaP7/kXNt5cjRuAkT3lG5A1MMk+iYWnJ4+E9bgzIXVEhPYV9ue+9GBMGPf7BaT\n" +
                    "LLQF1Ya+uq9VlxQAePwjzseTLlGAZDIoeXekBupq9OpSp2uDK440jEhkUskD55E339FCc00120tP\n" +
                    "uTKcbvRCt+1n9ARveEH/GN8aVuUoX9djkXiTfbOI1GsfrsR02wQufcXURXy7Ox7qHM0THHbE6jbc\n" +
                    "KDhb4sGm/bYZ/ujboljyTDA5l7hPrQtxRRDM7DwuMQNBsByvt9Uz7xMxOIB4efxxyzPYk7o1j/N2\n" +
                    "pVcwRY8e/nt/8865uCqdnUCg9YCYdR23SWVWPWVqzhvk8OxegySD0iJ2vFsUlDfPrRGuqMfbI8TS\n" +
                    "YSbGQAue4P6k4G1s7EZxl8lRK49cXnLxB448i6uD9Hn4ImW5APuiX24KwddsTtpIO8BgMFn8bTpi\n" +
                    "qxSxkTPT/wMRCLWzn61ztGZGJEkrZBO2oa/BJvq0381858hiqiUilGtO7YCCb+H8SOAidgfcJ6OV\n" +
                    "WduMScpXm3NyNeDjKtIQ8hFTl4Bm0xOsWllCqElwnbJY+8BF5mymDLCrjnM3risspB4tMNJ49mfF\n" +
                    "ZYDDpoWvj6qpuCvYdW7Df7eV2rxupTn/66Un4RGhX4YGDvsprkcsoy5mYVwhssISF48TStI8Dq1L\n" +
                    "FieIdSZj63EcQlJtR4sL8tqZj8BjAotwe8AcUkbo0+DXqu4TNRinzx1ficOXN1qeBiUdwn7XWsgl\n" +
                    "ZJX5O38Qo1pzKaVP47aO3+zm1z5IoJ9vSV875y0b0B6MwAM65ELAYivKUf6xHTa2fGUhZ4Z0oElg\n" +
                    "oHXeFfpXihchNF5X5BLXeT7EF2hAJLL0861QfS7Ntla3ERp+QH1jsJHHxPyRYgLKxLND4ULelKzn\n" +
                    "00zxLph5f3V5B/Qpk9D8NjHn7LzeB/1fDXv8Tx565AfftfPVeM0WtQ8858j1PiZ+7WKFvHOXP8rW\n" +
                    "XNFIA6eGUc+9lSy8WPcaUQOPILLr+0IwUCToBKVYFWTtnYJ8MMOSwxy5HtrRA/lS/hO4n9cDoQgR\n" +
                    "48q52XxwSamYhYJxwH5aDENovaopikkEi1bxTQGX/oGN9Cs+g+rswtAoz0E4NTqfvtPCe3O3Eo/A\n" +
                    "DgLE142Zmv5z9ZToTIpa/qnXhefYCMvZHRDQxY90PZUeSnpeyyb4Fif/sIRzM/x605GcXyMgfISg\n" +
                    "lXzz8ih/GDzCBjeLIJIOPvvn2J6JakEPd/etQ+6AFGRzUrhzNbixuhFC1KJhR1XJUgZiEIlUeZDF\n" +
                    "cfaR1291V6V2zTwz3QR3Nj23LQlG3Opi3GARYf5KhycMgXEcvuYYRxJgAeYA8+SeJAGJ2em0FqhG\n" +
                    "FlkTnESxoYbJ9VFHW7cnt/cSJJtTS9QaRQ2Wns2wh6wJl3l0fTwxIUUKYEIo3dX7jpJx9MciFmD+\n" +
                    "VV2EECjyM9w9RphPVm5/DC23++VtM87ysuTYKdoUKS9n4QPPRM2c+4+cE9Jran/kSKY+1v/WSakn\n" +
                    "3DLEdGOe5JSoO6MLnB10qic1GcDfdIXffGS+F2cdZRRfopOxu75OQMk1D6G+5FAV4QZ5x9KdQKla\n" +
                    "ViMD9tG5DfDGlN4dVDG+hbePorjRZVWo5sS/yFW6s75ajjiqWB0Br82HAvcnSLOZxnB9mAmPq2WW\n" +
                    "nJJw0Wdr9mNDOZ+OSZJ5wHqzanFnA8sY0eftUxziezfAdjvGCt1/kFzC/BUMGFXLtU3DUuM2oV9c\n" +
                    "aWJqy2XfEQCpR9Uz+qv3b/1akoxWhCUENr+kq8/1VcyFlyBhWnJh/5fsF+fsNXZHJDxBQ3BlgHsH\n" +
                    "vea+oRKaP4Gs6vjV3D+SVwWcpiIarkNjBPaVS+7omwS5c3jw8RPosP3EGvpcVjnIxKi0zivBtlJl\n" +
                    "LftoWfZnqsJqZi2xHXFgYOqpog16OkZYfpGqhd2lHVkA3YPhwCqDqSPMmCvhhm6XhR+I0+bj9dyF\n" +
                    "uJU5Tc7nkYS0lJeqKK0YqofRLOcu0EDdpv9KCzaJg7t/fhMREsZACtjpDCq7r6qjLOmjRGMhAjJb\n" +
                    "2SVCZt3o6U8b3CXw2DP3q3XjhWWljiPUABUhoSk194iAlQnlP1H9OWD7rGjL18uYzdxNGoBlqicY\n" +
                    "4GXVS8LgMdvaJNt0c8NKkq7uT3lEQojRwD0dAEPGAIo4S9ldD7QYvjBHklE8R0uSAD1VNgwAcn9T\n" +
                    "giXak+eNlBzH1bLV3F5NSyURLtWw1XBaeXE5HvVnAyk+1eUkS2w6UpfiDlg5HqNNdBVEVlj9ZC6b\n" +
                    "VVYtZ/TzmpTF4McrF5wziO4SL4mzdyYdCGgMT7PDyOwd8p3MC90oMLoCAQztbpJBFqmLHnANx+xd\n" +
                    "Oi2PrdMaNFO5IW/eWjL2W5KIbr8qe+RLn1Jukr88cr3Vja2JP8qrAJY/wLAyIpKajFU+IUYzPxs7\n" +
                    "tk3PD946W39I/rilcQMpu1BtzlXmResvqmx0SiO+Rj3enwiXmymVVWqWbOWv05pOkBk0pChIcG/f\n" +
                    "buzIZdCJK1xS+USE8Upc0U/B/noq4cs7JMgO1MIwe52ieAFdU2dqIkENw2D0fzkO2j8lwtMrdBNP\n" +
                    "IhPM4KAbzBV2WDrtXG0Fjb8zAhU2S7OlNaxMJP7KlC+bLYHFHcu1wKO0kHGkScyElWaGwo7OHbIX\n" +
                    "05VNGyckHK5bUlHQ6l+5Suwc1gQJALvHwcPVjKSPtjLG7YH7T4EUQsqAQrOV8ZCGNR0Hy/590wgX\n" +
                    "m/+Xrk1dsuq1+ixY9NtLgcQ5DUUAYybMvf+WMMWmBwrL4YtIicDyGu6BnRxbLY88+jXpva0Wyk59\n" +
                    "/xFecDqqXWhPMZrtR+gbRoZrRufbL3lu6g4uRbLJLZayn8ja2VeaVc2bvtYEEnX7pKYbNN9QiSY7\n" +
                    "QVnsInL5bIrwaaTGLUKu/sddrOBQ0+onVKVFQYQKBfe5hrtqErpSrPBgSyUgT7jLdiTkyVJ7D3Tk\n" +
                    "h0lQieCKtDiCsY0KqYlXSCZvzgKvNYNOQTEtgk4Z8eeBAf9N+B1qIZHOt/HGLk9Fd36SbVF0fp+y\n" +
                    "9tDWIR/fYBu5euLtBvHMpjS67MqchDmNejBSiYJPUKUT8FbKGsQmVlTT/xSl049WWdRWXMt6vTTg\n" +
                    "JfkPL+5KQaA6xz1mrskRzjvZROeFdCC4JC1emkr3FveEfK+WcvYRGDAPJIPc/dZsdGiR+C+seRt1\n" +
                    "oZfjCVmML9IX1McEn0Ht52OKhMn+mjG5iQHyp+EiyodXGsjZJMHCoPDaV8xEifCwusUbboL0dFdA\n" +
                    "mItzV4tRMotFpN29LMRp0FAbqJAApBgOY+Uid3NWpjsavQNpLohBdM/9qO2/xjjipeztDWUl36Kk\n" +
                    "rT5wuufgjZWhgexgVmhL2fKjmiMsBkeS8hm8cOp5lBR1P1WNQAxiJ0UEGXlgtBHXpkBoVtGVvPPl\n" +
                    "z7DFTL1mButwhX7h24zfzWyAHKiFEBTRwNXGTbLwFIl5s4CAEK/XtTdmYDS3OUYfSrV4rSncKjHG\n" +
                    "fK/6FlIFXg7AX4yfYsMECm1wjJ7j/QhuW+gHV05Nqd32CJjl0uW/2JK10i9p0pXTUmz47sXTHGRi\n" +
                    "4K8gCtD+WCit4c92dyWV8bTcEMQ5yfRSFKoQPYVmTqzgJPGJpPsdPcNZMYj+Ag2ESeFdHcHg65zp\n" +
                    "mSX5ctPrwXUBbXCHUn0FVs/SI8nlyQOR+ddlt80ooHU8+gnzG3ZUshCOQS2pdrLzhYfKILDXYJMB\n" +
                    "h4mGw60XH8ouTxthnTZVvCQ+ELNwJMkHPni5rQ1MxSUQn5vj/5bIAP6nK1FdBHdvidl786F613V6\n" +
                    "vSjijV1TFWb1MlAtkW7gsDuBgx2l6hy80mTKlpRtVWYtsXv9AXz8ZKOT7wWkNdzCTwDfmbPwIbqY\n" +
                    "jRPLM3DWm92BakTWufpqgPVRRia1VTgFkbQaa5ObTAqyXxJ249GFXki81JZZhFnk3jFDbJ5jPFVL\n" +
                    "oNsSDBBHbIZ+1gU7yTPVm8bbz1KHEWOmHf1gI5S/96ZgbK2IrQT/d/J5IcPWpoyL1iIXO195TiZT\n" +
                    "61MCKlJBI784C24tpDaoaGeXekkgdsu1CAxUKQaFYSUYRajPf18qx1bYLxZ1qi3eYuk+IapyAJDy\n" +
                    "BeLpEEs1TmynTu/0Umf6JpuQMxA1XsMozpP4OYZ/dHVP5aG0QRYJOmWxtuuQHjj4K4oBqS5oH5y6\n" +
                    "QIhW+QwgeqeuDuowPVsS+LU1ud7eGNgSOdk/uIL9a/l59MYxBriAN6rV/xE3Wfc/IQRqrdAjFKfQ\n" +
                    "H3FGKewxaYmFULp+J0qLPos6d8M8Bu2H836gdDoFmgRqKppN/Q7jaM+OsyLItGKSm1g5nw7gZWjN\n" +
                    "LEiV/ZEkRwnZYugjY4knbImrRA7qmYUO0lWo9r6jyCVQpkpwKrF8octIbSN4hnm4Ylc3+CUTtWUL\n" +
                    "9nVNxOj+LLKb1XmPxKdYi6aGRHlepjdfJB4kU6sd53Y9IqX5c/prHUnQt6lOh18LNbXZ7yCmqaQJ\n" +
                    "j5Ed6owRP7KtpCQqgxAS68EY9azt7ZkRr53q6+Yjfa1EAN9UbFT3NOGORRTEe/96yREi2rXBuzxQ\n" +
                    "zt9g0ZNBib4sST41txgZ3UUn0DGFNLTD52RuF6Q+ZKTdpeuvM8MEOsLB9yE9Mnb5Vw5Fr+K10pLR\n" +
                    "gPMyz4tpvoIJcajJjrJeaZuDTfxfCFaqG3XCz7DYpgzBSl+28ihvTAuLAga+jO5E6RPhr4e8+jma\n" +
                    "7MSXTgsmGLkWrxgFzCWwES42TKIwxjLi6FFOg7eYdc3OFgKZKigFU/2+KcCy8gYvCm5LYYL74lFi\n" +
                    "usU+p87L/JH5sOtcKcIQZHOeSOzPOWW9QSSfTcz2uzkFflqgF9QE8+hTx1QzT9HygF1Di0qkxl16\n" +
                    "Nyo5GEonTpJzji8BM3Dg34THFyckXbIb8QnliXv9TCu777VIBBxw9V1MLapMscxAz1wsyh07y2l5\n" +
                    "xEAn8gEpF0/W+EuQO+rM+/w7/pzrF6gBNGNyfzuOdJGQtuGAr+bmH2JijxrF2XafdHK6+DEUZT2H\n" +
                    "MmxO0tg2xFy+ZOAPvvvWswkmCkTncD1yskvDu/g25aJzWe19KcsL82+d8aLmgxSexMUNL0bMLlwI\n" +
                    "1RdBSYrQIfQFrIczBiaVGha8IYYwPP2odOEUcy5ZCg4keEFYmShk+mtsU5u8RLoWfRqCfpE6n+dS\n" +
                    "SaSlmF/q9IYc2oaV4P6yzW68N96QKj+CkTZdt3+gWYJxFtnyQfDahgzm/qS6gPBOxh1D95MKKGdI\n" +
                    "nSNyBPOwgaSvXneLeYnYmOz51JAm24S72KOq6PZ9x8VWZ7+vzz2MUz2D7BuL+fZFpmj5SpNnJgD7\n" +
                    "bauNpbA=";
            String decryResult = decryptor(s,pwd);
            System.out.println("解密后："+new String(decryResult));
        } catch (Exception e1) {
            e1.printStackTrace();
        }

    }

}
