package com.example.liuyjycommon.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 *  压缩算法
 */
public class Zip {
    /**
     * 字符串的压缩
     *
     * @param str
     *            待压缩的字符串
     * @return    返回压缩后的字符串
     * @throws IOException
     */
    public static String compress(String str) throws IOException {
        if (null == str || str.length() <= 0) {
            return str;
        }
        // 创建一个新的 byte 数组输出流
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        // 使用默认缓冲区大小创建新的输出流
        GZIPOutputStream gzip = new GZIPOutputStream(out);
        // 将 b.length 个字节写入此输出流
        gzip.write(str.getBytes());
        gzip.close();
        // 使用指定的 charsetName，通过解码字节将缓冲区内容转换为字符串
        return out.toString("ISO-8859-1");
    }
    /**
     * 字符串的解压
     *
     * @param str
     *            对字符串解压
     * @return    返回解压缩后的字符串
     * @throws IOException
     */
    public static String unCompress(String str) throws IOException {
        if (null == str || str.length() <= 0) {
            return str;
        }
        // 创建一个新的 byte 数组输出流
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        // 创建一个 ByteArrayInputStream，使用 buf 作为其缓冲区数组
        ByteArrayInputStream in = new ByteArrayInputStream(str
                .getBytes("ISO-8859-1"));
        // 使用默认缓冲区大小创建新的输入流
        GZIPInputStream gzip = new GZIPInputStream(in);
        byte[] buffer = new byte[256];
        int n = 0;
        while ((n = gzip.read(buffer)) >= 0) {// 将未压缩数据读入字节数组
            // 将指定 byte 数组中从偏移量 off 开始的 len 个字节写入此 byte数组输出流
            out.write(buffer, 0, n);
        }
        // 使用指定的 charsetName，通过解码字节将缓冲区内容转换为字符串
        return out.toString("UTF-8");
    }

    /**
     * 压缩
     * @param str
     * @return
     */
    public static String compressZip(String str){
        if (null == str || str.length() <= 0) {
            return str;
        }
        long time=System.nanoTime();
        String ys = null;
        try {
            ys = compress(str);
        } catch (IOException e) {
            e.printStackTrace();
        }
        long time1=System.nanoTime();
        System.out.println(time1-time);
        String strs= null;
        try {
            strs = HexUtil.byteArrToHex(ys.getBytes("ISO-8859-1"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        // 使用指定的 charsetName，通过解码字节将缓冲区内容转换为字符串
        return strs;
    }
    /**
     * 解压
     * @param str
     * @return
     */
    public static String decompressionZip(String str){
        if (null == str || str.length() <= 0) {
            return str;
        }
        String isoString = null;
        try {
            isoString = new String(HexUtil.hexToByteArr(str),"ISO-8859-1");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String jy = null;
        try {
            jy = unCompress(isoString);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jy;
    }
        // 测试方法
        public static void main(String[] args) throws IOException {

            StringBuffer kiss=new StringBuffer();
            kiss.append("/9j/4AAQSkZJRgABAQAAAQABAAD/2wBDAAYEBQYFBAYGBQYHBwYIChAKCgkJChQODwwQFxQYGBcU\n" +
                    "FhYaHSUfGhsjHBYWICwgIyYnKSopGR8tMC0oMCUoKSj/2wBDAQcHBwoIChMKChMoGhYaKCgoKCgo\n" +
                    "KCgoKCgoKCgoKCgoKCgoKCgoKCgoKCgoKCgoKCgoKCgoKCgoKCgoKCgoKCj/wAARCAHgAoADASIA\n" +
                    "AhEBAxEB/8QAHAAAAQUBAQEAAAAAAAAAAAAABAACAwUGBwEI/8QAZRAAAQMDAQQECAoFBwcIBwUJ\n" +
                    "AgABAwQFERIGEyEiMTJBUQcUQlJhYnGBFSNygpGSoaKxwTNDssLRJFNjc6PS8AgWNIOT4eIlJkRk\n" +
                    "pLO08TVFVHR1hMM2N0Zl8hcnVXaUlcTT1P/EABkBAAMBAQEAAAAAAAAAAAAAAAABAgMEBf/EACYR\n" +
                    "AQACAgMAAgIDAQADAAAAAAABAhESAyExIkEEMhNRYXFCYoH/2gAMAwEAAhEDEQA/AO1pCyQr1JkS\n" +
                    "djKQpwoBYSwkK9wgFhLCcmpAsJYS7U5CzcLzCekgGJL0uC8UA3GU3HFSJKwZoXmFIm6VBm4SwpPJ\n" +
                    "UaA8xhNwnrwkBT7XPo2TvL/9VMfrcP3lyqlqPFasZPJ6pt3iuqbZvp2Ru/8AUgP1pQFclJlrRE+t\n" +
                    "WLs/V5hfodPF1V2ao3lNuyfnj/BWIug4TiaBuoZEZPmonUo523kRBlCkNmtVRcBlkpziYY30Oxvh\n" +
                    "+r7FpCpZRItUZe7mWMinnpz1wTzR4fU7RyOGflYfitvFUPNEMkMruJtqZ2dKwqiKGRm6hfQm6HUp\n" +
                    "TXBi+Kkice834/h+acM9xbrBCaAg0klodKpuVfD/AOrBnH0Hpf7eVRR3uVyxLZJo/S88b/g7o1GT\n" +
                    "9CWMJ0d0iMvjaCph9L6Hb8UiulvYufeh7Q/4kgiJk3Cl+EbW/wD0jT8w/wCCkGagPqVkXHvfT+KX\n" +
                    "ZhsJIrRAfUqYX9hsSd4o5dQxf2JgFjKbpRpUUjdiiKnkbyCQA+lNwpyhNvIL6EzQ/cTe5AR4SwnF\n" +
                    "hu1LSoBhNxSwn6EhbSgIpXaGMjLqsqYuLk5dZH3ObJDEPZzOq4n9KYe4RFHUPSTjKHHyXHvZQC6W\n" +
                    "U5DZQSR1tMJhzC6obvRbsyMG5e1kPbK56KfmfMB9du71lpJ42mDWPHKkMYXBRlxRlzpXpZuXqP0e\n" +
                    "hRUNJJVVAgDcrcxu/YKAMgojqho4x5RaHUZ92oydX4hHSwjHFwBkhYIIsD1W5VV11W7kQC6A8uFX\n" +
                    "1gDrfgqvRlS6ckvRZBPBBPFk+MMqUQQEIhzL3GFJ2paVYRkyWE7CWEA3C8wvUkwZpSJlIXQm4QSP\n" +
                    "GUzCmUckgAGTfApA0mQVVVhHyBpc+9DV1e7gTA+gO/tdVBT5ctHvN+lLAFTzZLmfJICU9RZylrd3\n" +
                    "wOpyfoZu1GBQBBFv7nLuQ7Ix4m6pACCnlqpdFOBGXa/YyPkCmtsUXjDjNLzYZm4IasukkgFBSB4t\n" +
                    "T9wdL+9V882uGCPGNyxce/UWUwVyrZauYjlPIt0N0CyAIsqUulRkzpk+oBSSFJSZwpw8V4vUAl7l\n" +
                    "eJCgPU5NTklkmpJyAbnCWUiXiA9JeJJJQCSSSTM3Ul0Jy8UAkxPTEAl4vV4g1Ft0+jY27P6IR+tU\n" +
                    "RMuUk+RXUfCHw2LuHpkpf/Ewv+S5cT4Fa0RKWhn8XqBk8nqv8laHUsqL+cru1VG8g0E/PHy+0U7C\n" +
                    "FiLpZUWV7nKSlfVhoqC9PMiLRUHHNuxMmA+xn7UyubLCfcoIHxJnuQGl38vkyEnx1U4/rPpQQycq\n" +
                    "eJpGNGtnbzXUo10rdg/Sq8TXutAWY1x+Zy+h16NxPulb2Oq0ZGU4YPqvlAFlWxm3xrE/y2yvC8QP\n" +
                    "rQU/vjZDbvglpSCXcW5/1ULezlS8XpH7B+u6i0pm79CAI8UgbqOTew0ipXdsDWVIeyRC7tLGO0vp\n" +
                    "QE/itSHUuMr/AC2/3pYuDD+nif2v/uQ+Tb9Yf0pb6Vv1hID3eXhvIpzH2snb+4t16SJ/Y7fxTRqJ\n" +
                    "W8v7EvGpfKcUAvGKnVz28dPezJxTM4E+4Ji8zLim+NSeUwoSsr3DVGGnX39yAiqa2OSEw8WEJX5c\n" +
                    "684+dhBZUad0IBybq5ktSGldxQE5OtBs1X6w8TlfmD9G79o+asvHJlvWU0BnHKMkT4MH1M6A1V3p\n" +
                    "98GMZynW+COiptA9Z+Y37yXsFdFVwaxfBdoP2ISsqmYSAeJKAVwq+sAPzKrFsuS9FslzcVKAoDwQ\n" +
                    "Uoxp8YJxmw9XiSAbhmTSdeE+pJWC1JZwm5TcoI5LKallAJLKWV4gPcrxJRSnhsD1kwjq52hbvPuV\n" +
                    "RUzSSFkzyX4IqdtfW+lRDC8j4FtReayCU1W8jPysPvT7fbaipEjLTDB1nkPoZXJQU9Pxm0zS+Z2M\n" +
                    "hKyaWo4G/I3QDdDIqER1dPQcltj3kvVec/3VTynJNIUkrkZv2u6PkhZkzc+qmhXEC8IFZbh3LoU8\n" +
                    "VBnib8vcgKLdu/V4o+mt2iIjNsn1lchTxxjyALexSFHkC9iWQ7UKWFX1VbUR3WjoqeiObfsRnM8j\n" +
                    "MEYD0ljrdooGeqen2pnf4R0UbQjv4PF3PD6eA5Zix5ROXL2IDQak5AVkdZUVsEdJU+LRUszFUPu8\n" +
                    "771PRw/FSlXwNWy02Tc4w1SOEZmweqWGSVApeih5pzBo3gpKiqE2yxQHCzf2kgfZlUN32kqKQhCn\n" +
                    "t8Du9UFK5yVZizEXnM0L/Y5Jm0wpLyJ5HAd8EQS9rRyObN8knZs/QvUgcm5TlW3i801qKnjmjq5p\n" +
                    "6h9EMcEBm5+/qoCwyvF7/jioqmeOlpznmfREHM7/ADtP7yAkSSF2Mcg4mL9Ds+WdRU08VTHvKeUJ\n" +
                    "AyQ5B9XMoNKkklnI58lALpXi86EtSA9TE9JWZiSemKAy/hIfGyEoefVQD/a5/dXL5V03wllp2epQ\n" +
                    "/nLjG39jUP8AkuZTtpda0RJkbupaafxWcDzyu+k/kqAesvKl+CtLTC/FPF1VWqq31Juy68XL83sR\n" +
                    "4moWmlbWBN3oCBufCK3ihxiYnFBrCI+RSiaFifDqXKAn1pEai1Jkh8EGfJJy9K0tGcR0sX6J+Ru7\n" +
                    "gsrEbuZYfsQslbVtW1kYVBBFBCBszMz5IiPvb1Ug3nI/mpu7j8xlh5K+4REfxsRiDcdcbKOLaKr6\n" +
                    "f5MY+ozj+anAbvcR932qPxUC6pkslHtNUh1qYX/17/wU8e1LsXPTy/MNi/JGA03ijef9ihq4AigO\n" +
                    "TXysqYNqYnLBRVYZ/o2f81LPdmrqc4h1BxYnY43F0YB+8Yk3UqiputHSviWoHU3YHM6rp9qYmL+T\n" +
                    "08p+k+RMNORqKeqigbMsggPrusTU32rn1fGbse6N1VyVGssnqcu83QG+lvNFGBOVXDq7mNU092pu\n" +
                    "Z9/rJ36QWV1h5g6fYnR4N+WMm9yD1Xc99jYS3QERdjuoB2i8+nF/kSKuwzlzAmbmJ36MpdFqu4L5\n" +
                    "TSdssZdxtwR8VVHI2RMX9LPqWYk3QD8UxafSlFIzFkHeMu9kw05N2gioHYxVJTVsmnEul/SyNpZ2\n" +
                    "GYfNdAWgNxRUYJkQZFERgoBRgpxZgZNyzdVeE6sPSPPoFeJak3OUAkk3K8J0B7leZSwlhAepZXiS\n" +
                    "AWUkk0nTIjLhyqAm1KXGUyV3AC3Taz7MpEi3DPxN9A/ikb6WIIuAv5Xa6Dl8YZ8y6tOelTxnLj9G\n" +
                    "XtTNFuH1JFTuj8YHmUEkzN1OKRApINPWTNyxIgsmXMnhHlMB442ZSiCIGPlTNYM+B1OXoTQaEDmS\n" +
                    "KClZusoomPOegVOTv5ToDefAh1F+guFXUDojYPiQbVxEjxzO3Vw/Z0qp+C7jWkchxTANXDMT6nAQ\n" +
                    "CWYcai59XACIejsWywnYS2DE3K1VdRtDfznoZaiOpmE6aeWnM44gIGyTSa9IExauXS79Ct6zamzU\n" +
                    "tXOR3qzSC+XanhnY53k8lsAT/azK/Fk8XNjI+VjfpNm4v85CwVtgna20cQh8eFPGDs7asFoHKzV/\n" +
                    "iqJtpNnhOOanqTrHiac4zjwLQy50O7f44LWywQTjioiimHukBj/FCQWK0U9WNXT2i3R1TPqaYKcG\n" +
                    "NvegJayqit9GVRVSG0EbDrkdtWG84kRj3p45biL49iWHdIPBHJ4VN49LVhaZaFovj9+bBMbsDaRx\n" +
                    "qLDf4yi7zawutJ4vLWXGjHtein3bn6pZZ8iqum2Ot9PCMcNw2kjw2lnjvM0WPkiGB+6lAW1wqo6K\n" +
                    "21FTVSFGEUZGZx8xdXyct9HKs1AbSbG7qWcq6qrJ/FzOsZiB5euRaePIDMXZ2LR0lr3NCNLW1lZd\n" +
                    "BFz0yVrs8mlx06SdmbPlc3pXlXa4KhqePmjp4ITiCONmw2rTzcWcegdPV8p0zUFNeKShsVMA7uGl\n" +
                    "kemip/FYDZ3CRjduTJPq5C+llaUkcgW06Wz274Pljn3LwTszbnUOvW7A75fBDy57V5X7OU1Xa4qM\n" +
                    "ZTjKGeCoCY4wkdihHA6g4M445cesmz22op6E6ekoLfdAnk1zBW1HinHSIsQiEZjwwPm4wl0CvUle\n" +
                    "FquUFK4PWNDHu5ADRoaUiBzLLv0MJOqa/VfilnuVnt8tME8EEMNFTM769HJz63wLloLq+hXsVDcZ\n" +
                    "KC5NcDomqquEacApTMwhiETFuY8E5fGk+r0JXmwR1414Q1BURVRhNvIw1G0sXU4u/VZxHlZvemFl\n" +
                    "olYNEskXjGMGbBkNfby56M+sqm5zXCjphnkqoHi16ZpIKVo922ksPiSSTW+rSOG70TZWvYx1DbQN\n" +
                    "bJDB23M1E55kHmyRs7cC6vR6VFcqoqaUJYWuJl1sfB1ZMGezSMcen7yVfQF2MmqauyBV1dZNOUrm\n" +
                    "bMbwu2HInYhwDE3DySdXyp9ibc9t2StME1MdNWNSgNQEkeg9Y8OZXeESZij8lSkOUzSpDJeEgc2S\n" +
                    "gb/8xAv+z1H8VzasbEhLqG34a7fQAWr/AEojb/ZG35rmtdG+ta0RIAV5O2F7jBLyd+Aq0m0M/i9S\n" +
                    "J+Q/KfyVof8AGRWWJ+bmV1aqjfU2jyo+X5vYpVCxzwThbUoxdSi6S0sejOSU+YO829yiHinCyQPz\n" +
                    "G5csqaTA/VlB/evMcqaQauxAOAMPnIl7EBJCb1le+MjJHGLP8kpf7wovDM6nGfH6sUAKVLJIcvDl\n" +
                    "fSSlK2vMRb18E76st0qfxph60Ze50vHA8yVIBSswMWQkM/QeENJShGRfFCxO44fHQrTxuJv536FD\n" +
                    "WXGCOEtLkZP0M7JeBXTzBDEb5Zhc9WPkqmrq55AlYJCATf2ZTK45ZCI5dLdzKpkdnMuOS9CZmk+O\n" +
                    "3KYRv3ZTi4+Qozz5T4TDwpH6Ccvcy9iBn45JRDhyxrRIuAdpOpyE2I27Cf2pkhuzYDgPoQ8tUDN0\n" +
                    "8yFkqJDHl4elMC5HfHXFveoS63LOGpDDBI5ZJ8el04m3fTqT1AgZjZ+aQH96d40zFzfSyrjqgbyF\n" +
                    "Fv4zbymL2pkvoqjPblHRVbgOBfIrJBUPGaNgq9PHWlqHTrPXR1NMGp8GzaXZHgfpXPrHcdxOL5yD\n" +
                    "8r4W3pZNY8v0pAVlLK8FJAe5XmUkkAkkkkAl7jK8Xuc9qZEK8LgkT+lIePFIFjKWMJ4umoBnF+sl\n" +
                    "pUW/d7kdPjkaAT148rUQ6f2UQgBa5vifevBnYYx0tqLHao6vBzZF8+SvBDKYNkNz6yboyp8M3WTt\n" +
                    "DuPI3vQSIQYOJPgfSpQ5x5GJ/ThPig5ud8kiBbDIAfcZ65l7GTxjYG5Wx8lSrwnZkBHhMLgkUmS5\n" +
                    "VETSGXThCdnXRnjft+xe72Pz1m4L3JN1ILXMX9DdmL/6aPiqqw+i1E/9TVb38QZGpLYZI37U7eB5\n" +
                    "yq/GagBzLaLmw94NC4/bIyjrLjT0VGVXVBUBE2nLbtiNtXqs7oWuRMH7RTuHeqKG70c4CYR3PS/Q\n" +
                    "/wAFVj6vojdSPdKIP0slREP9NQ1Mf7cbKcBd4SwqL4btQde8W+P+sqwj/F2UsV4t8nCnvNuPPYFd\n" +
                    "CX5owFxhLCFjklk/Qybz5GH/AAUu7rWbJQS6fTC6MBLhLCGKacOuAD8xx/NIat+4XSwYnCWFB40f\n" +
                    "kxi/z9P5Jw1T+VH9/wD3JA/GUtKZ4w3mF7khqmf9WbfR/FAPxypYXm/DuJLfh3Eg3uF5heb+Pzi+\n" +
                    "h0t9H54t7UB7hQi2GUu8jfqmL+9e4QGY2zj109AHryl9UQ/isDWU7uZPjmXTb/A850elupvfvaP4\n" +
                    "LK1lC/NwWlSYeenw/Kq6p4OthWUPKSzN1g3cw8OxUSsU9vn3FSJ+Q/K/yVCTYSFs6kBphdSiq22T\n" +
                    "byDQT80fL81WUaRiouKnEFBA3MrGKPLJWMNoyKWlHDA+lNKB0gAIEwgR5QP3KI4XTAEgTCZGFHhD\n" +
                    "VUjUsW8lYuL6WbvSAeV8AXnKrqZNGoy4l6XUp1TzERk/K3Yz8FW1c7vq0oMHO0k7k0plp7sqLdxw\n" +
                    "t0Yz2J5cjZ8p0OZ4Lr8z96AlqXY+FOAgPfI+c+5Dy0uhtc0nK/e+lQSzGx5F+hBzm8x5J8l5zowB\n" +
                    "kc9OzFziw+hCyzsY/Fan9KdBQa+J8R+xFRwwRlzdX0Ml0AtNRSyRFPyhFnTqftL1VKUwQjgdLn3v\n" +
                    "2KO51+8lJom0RNyszdiipoXBt4f0dyoJDOQ2yZkwoGpf+kJ06pklkMmHVpboZQDSyG+cEmX7Byd/\n" +
                    "KTMo0qGd+rGSYVBO36skRaDxYMR5UkcjsmnBID8zEmizoJaW+o0HzdV+xdG2Zneag5uqD6crlURY\n" +
                    "dbzYerZ4ipnkBi6zM78XSsG0z6U4eKjCOTHLHn2KTRK36gvoSBJJvFusBJa/QaAcveCYUgBxPU3t\n" +
                    "ZNGZj6mp/TjgmEmUuLpo6PJMXL2p2UAss3VUYvwTuxNFIjtSbngl5SSAFJzGp8rSpeJtxfl7mUmj\n" +
                    "jle4QA5Nz9Be5SjG/sFP4t1U7W7pwRoxsykwoyfj0CvC49iAmEsJZbvQ49boTtH+NSaHsmstWl0z\n" +
                    "d567k6QxvnlMk2Sl3nXn6e/m+6g8PCmijbl447lXVN1jbUwHrLuj/vI07NBN+mnlkHuzhvqpDZqZ\n" +
                    "urGz+9Poaq2p2ZxNmGm1hnokePP2Go6nZqcJoIZbXTxtUZKIpZI44zx04M3Znfi3BuK0QyO5c0UL\n" +
                    "/wCpZDnBE+vTT0za+n+T9P2rKtpb6wq6F4IDOKWOnglhfQ+hmPmHl6QLS/uW4vUn/MGlkE9etoC1\n" +
                    "9+risaNtBiJ4tQejQ+G+1bK+No8H1rAuGfFR/sTL91OJRZm6O93CCLdhcKhh7tasItprvGOIrp9L\n" +
                    "QF+IKhjbBfpJW+RJp/JTjrxzGb/PAv3USK+NGG1u0ABkbhrL+rj/ACZkwtqbjMP8ojpp/wCug1fv\n" +
                    "qk3jMA6tb+4CTRaMx5eH+p/3qRrCxluUZlmawWOTL9J0XH9tOirrcHH/ADetjF50ce7/ACVWTaC5\n" +
                    "dOnu0Oy9zG4+Sxe008jVdx3unj/RWypjH+hus0f4MiA2ijxzfDYfIvE5/i7LN9Tt5fRI6QzeuX+2\n" +
                    "ZPI1av8AzggYeap2gb2SRyftmnR7Rxu3C4XYPl0tKX5OsqUj6RbMv0s6cLm/879Rv4pbFq1wX8H/\n" +
                    "APXFQ3y6CMv2AUnw+zdW705f1ltmf8MLG8dXQXvjTdfN5P0OkerbjfX/AP4nZ3+XQzh+MylG+Pzf\n" +
                    "8oWH3uYfjI6wuv5Le82XoyY8v+0QK1buO8yG+Al2ek9l1cP/AKbouKrrZOpR2o/6u8GX/wDjLnsk\n" +
                    "2RHiT8PPZRSRxyDzRi/tYCT2GrqdMdSZF41TRQFkcaJ3kZ/uArIW4LG+DuEI6O46AEB38XQDM3VL\n" +
                    "uW2Fkh+oaWMDEtSpqylbSStK492Q+lkBK+WWkeFLPVdKziSx+0FLifo5cf3l0Gdll75T65i9iZOf\n" +
                    "zx4MkwGVlcaV21OqseHBMLSyg3jh9vxJftCtFFGDrNWMv5ZL/Ul+0K0ERpGPigZ1t9h7RRXAqrxw\n" +
                    "HPdiOkGPHfno9jfSsHGbirO2XOpoJ97SVDxH1ctzftIN1k9mbWQ4aAw9kj/mhj2Ptx9B1DfPb+Cy\n" +
                    "MG2F1B8lUtJ6DiDH2YRQ7cXNuyif2wn/AH0DZeS7FUhZ3VRKPtZiQsmwYF1a5m/1H/Ego9u6wf0t\n" +
                    "PTH7Mj+bouj25OacRloIgj7Tao4/V0fmgAK7YgaSmmqam4AEEQOb4i48Peua3WaOecgBtQs/Bls9\n" +
                    "utrfhGM6SnMoaVn5mYuJ/K9HoXNKuvfe4i0iH0k6XRvKx9AFHEwiboEYDaHJmOr8UiYym1nq1u/Q\n" +
                    "pJDeabdBqfHS7JADPA76tXFASgzI651YUvxeNUvd3Kuo6WWrm3kz4Dtd0owrtFuDnfl6qeEIQ8ek\n" +
                    "vSjpX1lu6cORu3vUEkLt6SS2OtUE9W58B1IUtb9uSRg03MjKaidy6FG8Qv8AimVXTUXNkkX4q58g\n" +
                    "N0q9gtzu4tjGVaQUIR6dKieZVeFn6PZ/I5l+hW9NZow4CHN7FcwU+EdBCzLG3LNmtaRVTRWePHQi\n" +
                    "Bskcn6sfoWhghbCs4IAZStiKnZKOaIm3Te3Cy932EqY2I4WyPoXbI42RG4AwxgVpW0wznFvXy5U0\n" +
                    "UlK5BLGQF6WTrbVHRVgTjzED9C7xf9laavA+Tmf0Lj20+z89krNEup4n6hropy7esL018bqhqQq6\n" +
                    "WKcOqbakULu3nMuWUsDT8CllDHmHhFDC0fALvWgTd1U60wxdNGY28sm969GeT+cP3rnET1g/ob3c\n" +
                    "H/17l+a0OydfV1tNVR1chST0sgg5v26h1J6llqN/J56eM2es2fahwB36ynFlBnZ9A/QvNeewUsJ2\n" +
                    "FQNLq9ArzT6E/CWEAzCWE/CWEEiwzJY+UylwlhMICB/JcktBeeSnxlNwgkQh65Jwh6SUuhOGPLow\n" +
                    "EQx8ele6Mdv2IoYXdSbh0wB0P533V5ofv+xH7h36rZXvipv6EIVZRm58p49y8ID8k/oZXI0oN1uK\n" +
                    "9GPA8rcqA11T4Nond3pbiQD2NNBrf7CFUsmxLxVLxzXOZnbp0WWd2+uxuKDtnhOvkBi1XBRVodvT\n" +
                    "Eb/ObLfYtlReEewzxN41NPRT+UEsJkzfPFnHCztTP3htsytRsgIfornAWP54JIfydR7ZxtS7J0EG\n" +
                    "sD3VVBFqB9Qvpp5h4fQuqRT0d3o9dDVwzxFw31NNq+0H/Nc28KI+K2kIjPIx1oGchl1W3UvEs/LZ\n" +
                    "RSl6z3ORyXiWHoKWqq2F6SAJhMi06aymB20vjqHIz/YrUtmNoN25PZK5w88JKY/wk1KlpK2CKIIa\n" +
                    "hqs449WBCGhmAtROX6ync+3+cSqK61hI27sdtqfTVbM0R/hUxov/ACZ6hVK0x6s5bDXgAtLbbiBf\n" +
                    "/D5j/YYkHLSvTyHHK4xyh0xyQyRm3udkvG6B3Bxo7bRN3R2I4/q7uu4KKse3Sizwz2+GUicidgr+\n" +
                    "f2i4TD9BKIm+e4OYrHkmy5gMWlDRnoyzsmhNTSSaBraID7pKsI/xJkwaqopp4pIK4zOH9FJEcjCH\n" +
                    "s1szt9DK/pdqbmUzkFyuVOPr3mD8J6bH2q7zMR0mmPtVxUlTNJppjo53/o7rSv8A/UVjDs5fakya\n" +
                    "K0TnjtGrpTZ/ok1KWvvYSn/Lrrd6gn76rZ6QPvaCVZM8FRUPuIKepF+ya2WapZ/9nUiSx35P6baU\n" +
                    "xnKapsN5ifB2a55bzKVzH7EN8F3Jj0PZ7vq/+GTY+nCs6a2xyuwNY7MDef8A5jnI7e+CpT621ywV\n" +
                    "LnRxU7M3kxbMXml+/HI7fYieTk/pERX+1LPTz0x4qaWpp37pqWSN/tUElVTxniWoijLuM3Z/tV5H\n" +
                    "cKyORmnF3D177fqP7CjJE1Fz0yOQ3S1Uht5B7e1Yv9WWBKOW8fSopEs0NfTv1ayF/ZUKUagD6k4m\n" +
                    "PomZ1dR7U3ASKIL9TyA/ZDtTb6h/ompkVHU1FdPztf6ov6CXZ+YX+lmdVPLj6EUiZwoCM+XiT8PU\n" +
                    "S49xfUZaKsstyKbeRWC6TBjpK02eYPsmYl4FPUiW7PZ2OH1y2PcvthqURz/4LUiPtd+DkH+Da18Y\n" +
                    "zVMPRp6oD/FbIW4Kk2UpdxbixEELHK76Rt01F2N+rkMy+cyvsLas7RljZX11PJNJ8UxPhkHJQVDD\n" +
                    "koy0rQwXCKjYwlbJvzMpyvMDjy93arqlipaWTVzATKnudE7mWplvJ6+CQs4VVcNxMZPhUHNLnQPz\n" +
                    "cFk66keGQnxyrrVwpQcC0ssfd6QHbGOllWSZeyuAVEup8Zj4fWV5GYP5ay1wB6GoEx7H4I2kuMcw\n" +
                    "k4uXm9CR1aWM271MB+lUcVUD9pI2KojfrOgZWwO7qXj6VrPB/arLdaCbxthmrGk6m+dn0aW7GL2r\n" +
                    "WnsfZXjwNIQelpD/ADdBuRk7spZJggpSPHP+K6YWwlqP9ZVt7DD+6s3tHsXVy10NHaZA3Ukbu803\n" +
                    "6rHfhu3s9/clYVcmvNawOWs9ZvzYboZVdJJviIx4k3b3Kyqtnqit2olsdqlGvqGmeIpGbQzuPX9g\n" +
                    "i6K8IWy9ZsjSWumqHp38baUtcBuQto0dbLN06/sSWztdctyZBTnmc+XLcxMi4n+CbbrPU05976nc\n" +
                    "kBYYIwqtZ6SNubJq2gpzu1ZkeItys6m3xFa7Ki1WuSuqNc2ohzqfPatQVtYId3jlV5R2oKdsA2cd\n" +
                    "qnnp+C5bXmZdVaREMmVDHG3ICEkpMn0LTS0/oToKDPHCnddaqKmtvHJI+Ki0dVldjS4FShSqNj1V\n" +
                    "0FLjjhEhBxRu4wnDHhBhggRMUOFKAKUWwmHsTYR0HWQoCiAbggrD4UQD4QkRcEQD8FVUnG2pZ3ay\n" +
                    "zRXW2yxm3NjUxLRE/BDTlkSRsl85zwHQVU8UrYOPUK6LTZaEAyWlgYVQ+EGi0XQ5PPbSrehm3lHA\n" +
                    "feDLqrbMOW8ayrtqMDFR+c8j8fmpmwgM/wANuXH+VB+wm7UPnxL2yfsipNgP0N5f/r2n7grWPGf2\n" +
                    "1KkFk0U4eBJG9Fl7hepIBYTscF4noCPCWMJ+lLSgjMJYUogloQSLSnYUoxp4x5T1CAQySMpKdzT6\n" +
                    "anCSQQOcIA7ZHAzw3qszPkvqroFlg2cij1RVIyFnpqX0F9V2b8E9QydJa5JByMZP7G1Ir4KcOvGT\n" +
                    "e1dEgraImYYammcexgNkUJM7cHz7EDDmBUGOxNKh9C6gUYF1hF/ayjKlp360Mf1EDDmBUT6fKUZU\n" +
                    "T+sumlbKM+tTh7lGdnoS/U6fYToLD5yjBnfox70ty7nkXFPiZ8/707PFZ5VhY2O+XWwGUlDUTNAP\n" +
                    "PJAWHjP3P0dHWFddrbxb7fdrfU3CoCmGpgli1nwDXmLp7u3pXEZTPxaXTx5CW38KT6JbbH/T1JfV\n" +
                    "3TfvJxaRLZjsRsnXTDXxWihkE31tuOEMnpIBfQfvZUe0XgvopsybObq2yt1qfHxB/JZv0fubHoXL\n" +
                    "IGjhk3kPxMvnw5B/rMtJZdqrzbC+JuU04P0x1hnUM/znfUP1lWYMPfNlrraQfxum3jB0nTxySA3v\n" +
                    "0CyoSp3YueMgLuOPSuzW3wh28oQa7MVJN2kDPIH2c32K3raKxbW26VhKmqxdtLVEBAUkb+gvJdL0\n" +
                    "OA7jrcBTd3h+hdKvng3kpI3ktNTXVZO/6PcwmbfWkiFZiq2Uv9IBHLZq54m8sGjN3+ZGZl+0lqKs\n" +
                    "1jHnfSvN2B+c/vR8lLKBEBwSgTPpcDBxdi9ZnURQmxdQvoUgHLRQSDg4gce5wYlANtpGLI00LE3Q\n" +
                    "7QgLq2x1dQF707EfmffcfyS2OtZCxSVEP6KpqA+RIbfmiHu11bTm9XdsdjV87fvpsmGItLiw/L1f\n" +
                    "kyHkbL5TPwWN5u+MPebkbd0lXIbfQ7phV1XNwllhm/rKWGV/vg6HF8J8WXItLJawItMeHVRaiZpK\n" +
                    "S0ScOiS1Uj/jGmwyxxPkbZZfdbIA/YBk6qdwlxjH3VEJ+xExE/RxeY+3VvBzLv8AZ8j3MMP8qkHT\n" +
                    "CzsPQHpda7TyrK+DQP8AmlEeMa6iYvvC37q1mEIn5MrtDPu7to8nxcC+8aAjrX71FtjNo2jlDugi\n" +
                    "/eL95Vccz6lrUl5416U6So5ulU5TYZI5/jSTIZUz8CWaub8wn5KuSdjilc3xgHx7VVVzOcAtjOW1\n" +
                    "Jhjtqo23AH6/7pKmtlVTQRm004Rlr4a1p9oIHktxf0fMsDXR6OPe6LFDVxV9A/Ea2m/2zIqKro/J\n" +
                    "rKR/9eH8VgIwd1OMZ9ylbo0clNMOgjp5h7ncHR9NJuW+JbQLdG75fwXLd2b/AKvPuThh/oPuJh2S\n" +
                    "jvV0pZdTV1xIPMKokJvtdEHtXdn3ohW1EIyM48Hy/wArLrim7x5BN7G0pwz1EfUnqA9khsgOmWSr\n" +
                    "LZ+qOotDvBMUO4aTSxOwZFy62e1hQW320V1uNtAK6ulkAzBjBmYA5cu3BvS/3RWC8erG6tbW/wC3\n" +
                    "P+KfJUVFbT6JZ5ZND+WeVJnW55JqkgDgD8rrrWzFraCiE8YI26Mdi5/s5bcGDk2SN29wrsNvbNOP\n" +
                    "m4XJy2+nTxVQFBjsQs8CuZA5ULJHlYt1MVNl+ZSDDoVluWTdwzkgBBhUowooY3xysvd3yoMIUKjI\n" +
                    "MdiO0JbpAAg2lSiykKHDp4hhBIhUsb4TtC9FkBLG6JidQRs6nFlVSek6hl4inn1lBI6ZOfbe0u8L\n" +
                    "I9ZnVHbbjBHEFNKZBKzaOLcHWt20jwwH0i65vIGboId0i6eHxzcnqzvx65qVu5pf3VHsmZwhctJk\n" +
                    "2arsf1RSuIfH0/Hsl6fmqHZyeJ6mvpBMhnaYjxjpblZbfTBqBqpW/WkpRrZW8v7ECMb95KQQPvWZ\n" +
                    "j466T1H9ylGuPuBVmJNXSnYk71YWg13qD7nTxrWf9X9qqheRvNXuZGHyUEuPHI+4k8aqP1lTapO5\n" +
                    "O3kncgLkaiP1mTxqI37VSDMbdicMz9xJhdjNH54p8cgP5Y/SqQZn5uQlIM/oJMl9HIzdv2ouKox2\n" +
                    "rMjP8pSjVfKQGqCrfvUo1efNf2ssqNVjzk8av5X0J5ThrI64w6hk3s4Kb4XqW/6VUf7Y/wCKyPjv\n" +
                    "ykhrW7yQGyC+Vgf9Jm+cbkpB2kr2/wCkl7wD+CxQ1vrl9Kd4765fWQFLQUr1FRuzr6emfTw8ai0x\n" +
                    "P7ZmN3b50ePSrGbZm87rxiCjaupfJqKCUJ43+Th9b/VQkceH8pMCN4ZylpzOCf8AnITeM/rNzLL/\n" +
                    "AK1jCCWN45Cp5glhmf8AUzxvGbe5+ZbDwrvmrtvpkqi+9Cq+iv1xY6enqZI7hT7xhaOvh3+nU49r\n" +
                    "8ftRfhR41ttDl0sFQeG6OYw/uoqVmHHp6VMD48sfpwjmtdEVJvodpLOBswuUFwc6LT6NZ6mP3CgI\n" +
                    "35i0mL4fTkJwMH+S7crj6yLHUfXTahFsi4+ibV9iEo55KesaamklgmbokhfQf0qeud308S6fPYkA\n" +
                    "DPvfK+hFbCXRrH4Q6+kYY7nEddF/OAwBI33tJfYtBPe9itot094p6GSQB4fCVI3xfzzbT9DrkYg/\n" +
                    "d9xOw7eb9R0bjDpVTa/B9NSH4jXWKnd3/SDXcG+pIP4rEXS3Qwkc1BXWeppnbVporg0mnHyz1P8A\n" +
                    "JbKrMu/l83tNk3D+d/aOjaFfqcT4Lp++4pklXHGQgc8TE/Qzz4UpM7noEx1P3zsLfarCmhvUVMcV\n" +
                    "JUG0EvXjguNMbHn1WNQeyr3juPX++yhl4+d9isis11b/ANV3Nx72pHJvpZlEVtuMJiZ2q4aWfViS\n" +
                    "hkw/2ICrJPpmyZdvDu1ImqPecPEKalNn47tphf6pyOP3VBAOTLq9Hazv+CaDazhUn2fdUQupK7/S\n" +
                    "pdPf8lRiios7J4N2/wCZlv8ATJOX9sbfktRpWZ8HLY2Otbf1z/20q1GEE5ttVok2srG8po4R+4JI\n" +
                    "GOMMpu1szBtjdOPQUI/9nD+8oaadn7VrHhD5IQftUo0kZyk5P2oI5mYuupojYizr7UEMKkwJaXym\n" +
                    "S23MQPr6Y9LKXLbvroqrgeOGn1HzPBGf1hTNl7la/wCSS6mHSub3y2vCFO3N8ZPuvukutXKAzocD\n" +
                    "ILFr71i9oaJ2O16jFxer/dJIOcSQnHKTczKWBzZ8lqcVobla3YDkH5SpIo+fHe+lGxrOKklLrRmx\n" +
                    "ellZWfZy43eSeO30dRUFFpeTdt0MXV/D7FaFCbmXHPFG2WuvVsuIBYZ5Y6ircIdAMD7zm4cDbHaX\n" +
                    "N6XUBVz7FXyPgVquefRSmf4Mq2u2dvFIGua2XEBd9LOdJIP4suwNW+Eqn1SHRFII9hhTH9gGzoeX\n" +
                    "bHb2DlKx83e9qnl/YNVgOHVj+KCXjbjBj+e5PxVlszHHPv5C0nETCQOHMz/45V1SXwi7X0QlJXWa\n" +
                    "OKJ24FNbamBs+0nXPqq81lwvNTW3KUDq6k+IgOABsYZmbuZmZKRVc2iONjF26q2toPMfvXPqGZ2b\n" +
                    "mfBehbXZ+R5Ih9C5OR1cS+LiKi0ZU+OCQhlZOgOMeST44UbFB5yJFgjSwFduMJhw4VlJJGw8zihZ\n" +
                    "JA709S2BFHke5R6VORs6ZlGo2R4yvN26flmJLWKYNEMKSNmUEs4B1nVNcr9FSCXPzehOtNitZpRc\n" +
                    "B6zqUaqBm5nXN59rgdiAdftdk2DaN5uQZfcy1rRlN3QzqI5CwKgkbmWco683Dpz6VY01x3kmiXgX\n" +
                    "YlaorZXbZtihA/TpXIrnvfH5d02g9b8Qzldn2qDXZJz7tJLi1wuklPUygEUTlnjnK04k3iEtDJUS\n" +
                    "VP8AKJJTEAfiarTqp6G7VktJKUZ6yDOGLyvT8lPK/wA7iTeLwsXY+XTKGllmeWWZh531c/auly/Z\n" +
                    "57T3gD5auJx9MAfwVxBtBcXoCnI4TNmIuMP8FmrhDu6kWwLdHQry3wMdrBsZF2L9pL6Bw7W3NutH\n" +
                    "Qv8A6s2/fTx2wr/KpqF/cf8AfQpW6LV0Lz4Oib/zdT0FgG2VS3XoKZ/kSG38VL/noflWsPdVf8Cp\n" +
                    "ioYu4vpXniMXpR0F8O2geVai1eir/wCBSx7aUz/pbdVN8iQC/gs54hE/lmkVuDyZD96roNQO2Nuf\n" +
                    "rUlwb2NH/fUse2Fq8qC4t/qQ/vrIeIN/Ol9CXiA/zv2I6DZf53Wbyjqw9sH8HdTwbT2ad/iqmX30\n" +
                    "8g/kufVlLuwF94L5fowpbLHrMPTImTokl9tUene1mgn74ZP4J8d+tD/+sYW+Wzt+LLGXOlOQwcHE\n" +
                    "cB2/KQY0kveH0o1Dow3i1v1bnRe+ZhTxuVuf/wBYUX+3D+K5yNLL6v0qQaWXzG+lNDo4VtGb8lbS\n" +
                    "P7Jw/ipRkicuWWJ/YbLmZU8mn9Gm+Knp/QZ9yMB1IWyPLpf2L3cyO3LGWn0MuWDSv/MfYkNPoLlg\n" +
                    "x7AQHWtz1uT7EOUfOXD7FZbvh0Y+Y6g3fMX8HWDVFbYdd0oA8l6iEej1xVj4SXzd6Bu6l1f2sqZa\n" +
                    "Ic3e3ceiqhLHzxXvhC43ulb/AKiH/ezKqlLKA7s/KePn6VOAO5j1n94ElBDIZ8pxf7RvzR8dBUvx\n" +
                    "GDWLd27f95GFVlBXRvy6ml+dGH5IKOPUXR9itKyDBC+7IfS8DN+BKClpZZ5mGnp5Zj8yOEzf6GU1\n" +
                    "CIYcdgt7QdPGNu8W+urGO21zOWbXc2/+RqW/JML4v9Kxxl/SOYfiyNQD0cev/aOKeMbef/bsp/Gq\n" +
                    "dnx43TsXc9cH5qeLRNp0GJ+kJozRqNgskOZCbmf54Ooyp8jzAT/MAlZ7l3PlaV/mA6jkgx+rl99O\n" +
                    "38UxsppLdTu5OVJE5d707KLxCCPqU8Q+yDH4K4OBv5sv9h/vQxxg3Y2n+rMUgrzj4+t6WdKmDBlx\n" +
                    "H7/5IogbvH3a0oA5+uLfPNkKAVjfymXj296jFmb/AM0VU8amXj2+emC3N/xoS7FsHHo2Utbd8Tl9\n" +
                    "aUyWjwqbY4MbL2T00UZfW5ldk2GJBOH7fzY20vel+pNEP/Z4lW0NQ/RlS7YO022O0btxxXOP1YgV\n" +
                    "bSPhbV8JYT1b74WyiYKs2lLjy5VOT5qRUsDvrQza+k/lWinM8BJq1v3AIk7/AGMSvNsJ3+GaoNLC\n" +
                    "UYxg7B0atGr95ZSLjQ1r504oan7YTBvtMVpdrA1bRXGPukiH+yBCvpQ3eR46KLiXHUX7Kxt3qHO4\n" +
                    "WvU/Kzyl91a3aH9BE3yhWEub6a6i9G9/ZFFlVFVcjvTF6eVZmQMTe/sWlxmAVXRU5nXxMAGZPI3B\n" +
                    "my7qTW8dMbnkKgm9OXRQ0lTDpmCsNjjfWBhIeWLsIV4YHSzaJoqiM+swSA4P9qeNVHo5mqPczLP9\n" +
                    "QLDa/aWGQ46faCuY8cchHJ+2DpTbebVxdbaOYcdLHS03/wDrVZvnAycGLS79rJTnHNHzRxa28t2V\n" +
                    "7ATctutqKuCalqLzJuZAIJWCCFsiXK7Z0Zb3LBXOuAKwnJifjqyzq2uPxMRGbjrfylkK6bXLnmRE\n" +
                    "m3NvqtTCuk7IBrpyPOcLjthnc6YHLhjlXathY82/I9VcvK6eLtohDKljZow1v1V7KzRgTl1WVLPV\n" +
                    "ySOTi5MPYywr26JnUXV3RoxLT2LN3PaRqcS1GQl3NzOhrlJJpN+kn8lZKrpaiuk53IBytq4hl3Ze\n" +
                    "jta56nI8C3ab4TYtsojlxkgHvftWf+Bow62p1LFbYPYnmBiW3o7tHUfrBfKsRmWFpKV6VxeLgr+C\n" +
                    "qfRgusonC4rK5KfPVdMOfDFxVdvsdXgoZ5ncS0pADfLo+ko4eJ9/WWS8VqKgyOVy1P2utFOzM5cE\n" +
                    "MQ5V1vhOFQNtd26URTWsIyyHEu8lYjU0lP8ApTDV654b+KNpriDj8VSEY+pBJx+dhG0jWDqHIcCL\n" +
                    "3q0GHeaXHrN0OgPHpH0/8iXFjfo0PDh/pNlPTVtWD/8Aoa4v6GODP/eI7LpeVkPjdlqIy4k8a4nU\n" +
                    "nTwV9a9XIACcJCDm7M2dY9/o1Lr5bRRUUJNXW+5wF3PAx/sO6x2yu1tBs/tJW3WkekkqJIXp4wqT\n" +
                    "eNgAjEn7PUD6FpT1nfxkojCQcxSCY94PqZSjGT9hOuuS+ECpq6ArhV7F2iros6HqSq9TO/8AsSWf\n" +
                    "qNtbFIekvBrsxIz9JSSh/wD8y6cOZyW7tiu4+qtPb6d2sNPJj9WxfeJDbZXe33S8RFb7DbrNBHGw\n" +
                    "bii6HfmLPKIj5XmrW2KuorbZaWprbPTXWnekiHxWok0A5FpLV1S6Obl0+UjBMkTH3JmhdB/zxsLN\n" +
                    "gPB/Zwj7gq9DfQ0KVFtBsUxGdbsfUxtJ0jTVpSMPsYiDHzUajZz7dpFDnsJdM+GfBybuxbL3sG87\n" +
                    "xj+E6niq/BcY89qvEXyp6kv2ZHSwHLN2+lN3buutTh4KpGyM1zpX7gjrD/dJD+I+DIwwN8vYF3+L\n" +
                    "zZ+2FPAct0OvN266gNp8G5Ran2kvXtemw/0eLqArJ4PJWfc7V3eM8cCnpcAL+t8QP4sl2HJ7kHIp\n" +
                    "tmYddVSh3yfvEt1tps/svQWinltG0w3KtM2E4hcCbGOL8nEPfqWe2MpwO8UHm6zL7pEq+ky8vUe5\n" +
                    "qQDo+JYvvEgRbiunPspark4VNZtbaLdPo0lSzgO8DBFjPxzdPW6qng8HFDUSD4rtrZpgfoEKfJP7\n" +
                    "2n/JB4cxCM37FLu8ec5ehdPLwU1DHiPaC2n6HiJn/bXkngjuJdW627X3Zf8AggsS5pHGzNzJ28jD\n" +
                    "Vqf6F0KbwR3YY9T3K1g7dInIePp0oV/BLtJKIHDNZZIS4sXjcjZb/YoLVghqwM9ARm/pdk8pD09Q\n" +
                    "W9y3R+CPawZGaF7G8XaRV0zP9G4/NNk8FO1QMXC3H3aKoi/EGTiM+CeoXgtyeT9qiJm1Fx+1TZ5O\n" +
                    "Vx+u6ZxfV+RrBonsrZu9F/Xh2+soNvXztBE3khRRD9+V/wA0bYQ1Xel/rNXT5ok6A26fO0krd0EI\n" +
                    "/d1fmrgrKujjkMsAEx8OgGhP7rujBt2ttctvJ/TJaoTdvezqCCk3/WpN+OOh6QJvsyiY7dGDZKip\n" +
                    "Iyb/API5Gf6WNSAtXAwS/ohb/wCXcP8AzRezY/8AKQkGvXuzxoCpd/m7nihakWY+gG/1cgfiirLJ\n" +
                    "HBVk5yQg27f9JcZ6Nn/1kbO6dTajmjEnMi1f9ZvtxpPsMXUfwm0J4Kvs0LevtfO7/Q8aZHdYY+m4\n" +
                    "WSPHftlUv+1Gjqe+uXJBeKT2U17gnf8AtI1dQaF6lNt3FfaR2fsprxTTF/aQqrqYirL5FEXj1Szh\n" +
                    "5L0LyF1+q44b6VofHrhK3xNXtBJ/UyWt2+1VB0s820UbS0dxlPd8wTwURm/KXmvoRIPPZyQWLdWe\n" +
                    "7n/7zDb5h+rrZRQUldRyY+C3Bm8g7DGbfTHMrSWywu2TsNMP9faIJv8AuzVcdqpBfT8H2uMf6PZO\n" +
                    "pb7wGl4QaqoynJ3O10ETvxdx2bqwP60ZqsuNGEFMchUwBjofxG4x/ePl+lWxUEQP8VEw/wBXY7jD\n" +
                    "+BoO5GUVtmEjlBtOnSVRcY+3zJOX6VOchRRhF4vk2hcu85pw/AHFLMccwNEdO2X8ivkd/qvGyPgq\n" +
                    "NzR/6SHBiLQ17qYX+rocUMdxeeUP0ocegrk834gyk6qiV2eqNzkHS5ll98w/edTxx0hvgDEyfzLj\n" +
                    "Af5L0JNFXvN4TcdWQMM/ajvH2cuee5uPyKUm/BMOp7MAwWC0xj1QooRbPyBVpJ1C9iA2fZvgqgxq\n" +
                    "0tSw8PmCrGTqEkHzxd5Gm2k2gfynulSL+49H7qGifBqCoNz2iv3/AMYrx/7TK35KHeYqCAutlbEs\n" +
                    "YmzNKfmMpYsZVTJO8c5ea6Igm4p6s282NoI7vdvEpS+KKn3pv8iohLT7+ZvejaurGvrpa1sj43qm\n" +
                    "ZnHS7MxnGH2RsqvYQaqovkkFG3NNQzxFIRad2z4w/wBcQb3krTaMmgv1RGz8gQRuwt0NnU76fe7p\n" +
                    "LjxRXx9cIPnl5liLk2a6l/1n7q1txkd6OD0hqWOrONyib+jcvvIt4dVnBHqAPwV34Ozjg25tEhkI\n" +
                    "C8hjl+94jZvtdkDZmB6ul3vU3w6/k6kPaoTkulHGPWeRh4KTd+2vskV/s0lIfCVn1wn5ht/jHvXE\n" +
                    "JaI4yMCOYDByBwfrMQ9Irfx3K/BGwR3SbDNhsxxu/wBZ2WfrqConmlnmkKSeQ9Zm7M2S9ymbRJsv\n" +
                    "JAbfrT+hlEQSaC+NL5wMruWgkbrIM6WRlAYDaiXE266SZulZQWkmm0C2Sz2MtXtfSTwV5SaCPeMo\n" +
                    "NnLdLHdoPGoCYZG6ccFcW6VWvaGhjkpA52Jsrv3g3bXstTy/zjqktOw1NebfJUVNUcBC3xYRs2X+\n" +
                    "VlXeyT0+zmzBU11q4aYKWSQ3mnNmbRq4EuO993XSmkr24PmMvNWdqpNGrznVNc/CVaqqrjt+zlvu\n" +
                    "N7rJnwD/AOiw/SbOf3FLPS1s7ZudSMZ9tNQZAG9UpX53+U2n2KNZr6q1ot4U7xsBHUSDGHeb4QEV\n" +
                    "22fcyjG8W7W3SDVQGf1Gdy+6oawrNaPj6qjp56pv0LSfHH9J5JAXOuvdytM8oyywQMzk0ED6GVVr\n" +
                    "sJvrAquu1nZ8RT1chd8dBUu37CrPhKmY/wBHWsPr0Uw/iywE76+JGT59OpbW1U9TQ2+nkM5dRt1H\n" +
                    "Wn8PTKvNK4pK6mmMQCcdXc/K/wBV1dU0LGKCpgCrjHWwyC/YbZRUpnZigMozkopJAB2Z+IaiWerW\n" +
                    "txXipqCpidmJauekF6fRTxnEef0kku84ezDKsuNEbBrGSJyz2xvj8UajZiK6do5d2LEcp9QGQtTS\n" +
                    "PoI62Uzz0QxvoD+86PtFOdTV3GrqH1m85RBpbSzAPmqapoJJqwZN4IRR9GebKvqETMsbv5IZpQiA\n" +
                    "KbHLiNsY+cr3ZwDuhyxynKxQhq161a11noq+XWeqM+04305R9ooaS3Q7qlbp5nd+Z3V7w57UvlRl\n" +
                    "UVNtrfF6rVNB2H2stVanCQNevI96LpmCRiA2DS/Tw6VPTUoG/I2iJuxu1Q1r0zO3sEgUlPX09QdN\n" +
                    "u30ZZ+kfWWJ2jt0d1tdHcaRhOoc91M8fNn1vurrO0tXPbrUVRRPCE4Gws8kASY+ln+xZi7V9xu2y\n" +
                    "M1bV1EbHBNw3UAR57OxlrxM7ufQVFRDboqDxibxON9TQu/Iz+dpXmpekDrwgwt2KhrnzXE/pW3nf\n" +
                    "RsvRN/QU44+YsbWN/Ky+ctrcgxZKWPzGhH6oJkpM5S1kloXmEgeMi93jqPSSWkkBLvH85IZH71Fh\n" +
                    "LigkozOlvn1KLCXYmAtyndtPFWmwpf8AKdB6I5C/sjVJc+Gn2K82CD+X0791IZfd0/mmhZ7Qzu14\n" +
                    "n4lwCL9gVWFIxdZhL3IraN/+Xapu7QP3BVcp8NITROOgoonHucGwmbik0k3ilPp/qWTehLiq2BDB\n" +
                    "TMPLSU7D6IWTCpqNtX8jpWz0/EtxUijLLvhut2I2IQNXa4JQ00VOwduiPmZFFeaN9TQhpxys+8cX\n" +
                    "WcusNXTlmfiOOlmwmWylqa4ZdzIDDFp4P26k8HmXdBPh1x/2i819bj9q9E8hyvn3smZd9XW+xYNv\n" +
                    "B+z+Xu1LxLpP9glU7aPnais9AQt/ZArnZxs3WD2H+wSpdsXztPX/AOpHoz+pBXCLBoKWOQi108J+\n" +
                    "mS3PN+BIwI4w4aIg9DW6qi/PShaRgYi5B91PN+TowZAYeWQfY9VUxfdfIqQFqSbWOk/vyN+KLsk7\n" +
                    "w1BP41T0/Jxea5HRt0j5bA/1UFPJmXOsOjsqzP8AEVY7PVhUs0xx1e4LRjLVkcWffIzsg13BepI3\n" +
                    "+Lv9J7Ib/BN/3kKLC6VU4O3whd52fyYai1mz/SzJ0Nzq5Ax8IXaV/NhqbZJ+LMnBTVlSD4o75KPr\n" +
                    "x2s2+x0bEiegmqxy9pu0/pmgtZt9jqqG2M14KM7RExMPGGa1Qm3V8yM8P9KspLKzv8fZRP8Ar7NT\n" +
                    "SfsGquGhia8PD4lTRx/zfwGejq/zYGnsFk1sgBuWgtUPyNlKjP0gahOkAXHdAX+rsdxh/ZNSeIwB\n" +
                    "1IoAf+j2brg/A17g4BJhlqGb+rudM37Tqc7BGe/CPJhWkzedV3Wmb8HVddKlnoZmE4W1af8A8R1s\n" +
                    "x9cf1cgYdWZ1DRtxNvad/uEX4g6qrjcGqLfUANRAJC4i4Pf55nfnHyJI2yqgQFG4s1veL4QiZtD/\n" +
                    "ABXw7gv9i8f3cqtgcCl/SC5Z/ngJ1blcpHtJQfCeR3endNdYS927cM/aq2A3ch+MPpIsawfyVQqB\n" +
                    "MOt1tOO8F4QcxamLTnuBSkzv57/7NRzt8VK5AXDPkAs1O12gd3R0sb9ZoIx+4KscZcfNd0NAzNIY\n" +
                    "D1Q5G+aivKb2sgnyvvGPaa9uHVO8V5N//VzJ9S+KovS6qrRNvLicv8/Vyyv/AK2Unf8AFWld8XVF\n" +
                    "9ZbpezhrIXSgfDqIpMt3ryOTnQhvdgrjJQ3nWzMQSDDEbu3adXTxt+2X1UOdVKcNF41JrqnttHvj\n" +
                    "znJvFrcvvpmyEjx00rxGO/nraaGNnDWwFryBk3dvvF+b1kGO+jr/ABar0tNTuFIen+hHd/uIafR9\n" +
                    "wPNPA3dHpWXnbN1if+gL9tXs82acff8AtEqKc2e6A/dT6f7UlNhVorG2urgDynfSptkY95tNbW/p\n" +
                    "tX3SQ9hnaGvgMmyIczj81G7Bc+1FtbPRrLj6sRrI3UApmx0KGelbuVkPQmStwWZs5U0jc3BVc9It\n" +
                    "NPGgJYeZUGUuFuCYMEAuSFitbMGjC1pUhmWBDJdzdKjKgnjHJwSsPe4JhUT+M0kMVRRSGEoPpcG6\n" +
                    "HFW9vtNFW2kHraOGpN86942XfUXEULJBU+P0D04CcG/EZ2d8citLHI7007f0zrlvXWXbxX3hkrHs\n" +
                    "7brN4RZY7ZG8cElAUsYG7noPWIuIk/HzVrKy1Sz9XgPejZ6IAqIqzQOsOXX28yt48GCibbL1YKfZ\n" +
                    "Sj68rDMfbrdSxQRUQbsYzYe5uZlqa6Pgs9WRvzcUVtNRZkqq12eOsKpCjiCfOrOj8uqnYCoPJsR/\n" +
                    "LRtZEzHzKKAMngVe82FaRhbWqlDSLAAgDc2GU20cDHZ5WLrZAm+UJKehfQCjvhxSUYRU9Sc8xsOo\n" +
                    "NDg0fHo49KKxszn4rqSod4cCqyqnc4SYusp7eBzwkZamx5LqC4QuwcnWfykNGctgbuSsDvk1/WUt\n" +
                    "YHJkVLSUkkN0KQmGaB4dLg76eb3JS8hkD6fcnaP6RE9qWUWclNTNo7VLU0uriHWTIoHZ+ZLZosqN\n" +
                    "zkPGS0rR0zMwCqGjZgVpBO7OTYwPeitmdqq3b2Tc7N1Enc7ftLG7R1p0mw1pgJ8HVm8zt6q1+2MJ\n" +
                    "19pGjifnqJwib81znwxTbi5W6nif4qGDSDN5o8q6eH1hywoRrX8/KmCrB+ssoNWermdSjVcMi679\n" +
                    "ay4u1zPDvKgpA0uOH4dq2txBjowYerrbH1VzQa12/wCJGQXupgHEUmQ8w31CotxxPiqzLU7leblR\n" +
                    "Wi7RVwYNt3L3djoquq6aijM5pOjyW5idYWrNV1tlDuU0oURTVdPUBrikHT3PyuyRSQB1p4h9pspA\n" +
                    "fdJbnKKGanfq1EP+0ZSgDPxAxf0s6eQA3SRQ8FZDDkej6E0o0gy96DQJP3BqWj2DDRWd5NQl+4qD\n" +
                    "aUdA1Hoh/dWr2Jjxcahh6rUjj98FpUkF8DN4rX/pNP3RQG75Vd3WPN0rX/pj/aQe5SKQAxrwo1Yb\n" +
                    "ll5ucJZMBu1LTNFHWQSTU51UQSMZwBJu3kbV1c9mehEbtMHEcouXVZ9XTpVIWtZX2W4nu6jwemwB\n" +
                    "zZbaSaP9hQRVFho8jD4P5hB3yWnaqqd1lLxe3eSWOiqDeKTBZzxb1RJl5s/Vb6vzNJUHnujc28rr\n" +
                    "YTjJ5dqKaSbnlMzJ+lz5lF2FwXgyNo6UhkDR1x6Vi2/1b7Nf+lB4dAGX3VnNpnztPdPROI8fVAVo\n" +
                    "dl5Ge5GwuP6Ay+8P95ZnaM9W013f/rcg/VLSrhFktsMPGSY9Ol27aiaFvrBzK0nkjCPklpmLq4a+\n" +
                    "Tm/1HjVHbp3hrAMTxh//AGg4fvsz4V/VXFpIdHwhTPl+pHe9+/1XjZSFNPM7y/pM8Oyo1/krbZyW\n" +
                    "SOeV4pagOH6k6bV/bcFU1MjvMXxhnw62sHVns8Ekhz6I6mTgPCGOA/rbx0G0j09RUN/o91nF++K1\n" +
                    "n+8o3srl+nsTn6Z7RSSfsGk1qcmzLaJTz2zWilP9gkmtlOzc1qt4F5x7MGT/AHDUkRWuDoG0WyH0\n" +
                    "/wCbEhfsSKspoIwvE8e6iYW1dS11IN1R/Vs+WVm1BEHVhpBf1NnK0PwNVNM4R3Wo1PpJnfiwVMLf\n" +
                    "ZzsnULQWeHo3zexrlTN+a8KrFtWp9L95324R/iC8esz01ETfKvNdH+SaVyAOHjtIxf8A8yT/AJxo\n" +
                    "B/wiIdWvoA9D7Tz5+/GqA65zoaiPx8H1nq3bVoHnmJ+1tT/KV58MPnAXWFvkX1j/ABjWcOv/AJGU\n" +
                    "A1FQ+txLDTRkD831kVMZLXmdq3Xj9QYsDCwNUUbt9HXVbBn+lceP82Tf3kXPWn4mNOU9c+WHDfyV\n" +
                    "w/vICLL8dBaebsBMVRYd9Wpi94AoqmPMMvJ2F+rZOJm09Tt/m2Tow1mIY6X0/o0G7jT8ZZv6x/xJ\n" +
                    "T9o+0VBB+mn/AKwkUzZNvakUPi/ZWd3orXLI/wAYYQmT/KEXWovPJUi/oWKsEzx2W3v2tSwl9wVr\n" +
                    "toZGaYOPSxftLapBymwRJsR8VVlUZL2IiKZNm6B4PInmvbzk+QDxYOPf49SP+Shvs5ttRfDPDF49\n" +
                    "U8G7GEyFvsES96vPAXTvVTXmeQMwjPSxA7tltY6zJv8Aun+qubwVf/J9LKRk4yQgWs34vyoWuZZP\n" +
                    "iRVPIea4nz5Aj94lLLUNufV9CAKb4/PezJWOrS2z4ybAvggAi4eqKuvBzzbVUfmtFM/9iazNlmzV\n" +
                    "S8cDuZfraeH2rReCs3n2oDQxGTU8hMzN6uP3ljY3XxPlTZH1Cha6pgtsO+udRT0MXn1cwQt992VR\n" +
                    "Ptds5HTnIO0Fnmx5EFdHI7/Q6z1Wt5lBEDHUxB3yCP3lXx7R2KfS0V/szk/kPcYWf6NaNjqIqcRu\n" +
                    "ByC9HB8dJMDsYaR5ullSXyxWPaJ6+6VF1tZV2aupmmmy+QDWX4LZbVbEVWwmyd02gt9rO3BTxDoq\n" +
                    "4aqMwfWYg3BjInZ3IVz6KrNoR1R4I9Rvnp1FzOtXb9orntB4P79sVK5yRVIUz005C2in0TgZZ9XQ\n" +
                    "z4+SuitZ8Kceu/6PF/L/AEbNxd1JbgjhkPdOLhI+tsOuebQ7RVF1qZ91qgo3N9EYdrestDsVcY6u\n" +
                    "i0ay8ap+Q288Owljzfj3rTZp+PzRmYbsm3kJB3qAZnAci/M3K6bHNkOVQyNqlLR29i4Yd02ezztI\n" +
                    "CqKls6tKIld2IvwQ5A5I1JUT0rySkiKWlaEUbuWbUoZ5NAl6E9VVSxSMz8roqpnjqpYPiueNtLn2\n" +
                    "u6rrcGuCesl1eLxvjXjg7+b8pXdqon5amqbRnmYO5ETNUzGywpaXc0frPzOgKmHWxKzqa2MIcC+S\n" +
                    "VSVWGrpTg1XVTVIw+L709wD6t3ngqgz8YEpYuuHLIHctDUz08h8z4JZ68RnSVcVXSHg86Xfsf5Sq\n" +
                    "tk6lA7G3MiN3lQDcI5uJ0EIH2nDM4Z9zs6JjnB+pTl86b/ciYgZmCGNx6qljd24dKWt3H9HEBek3\n" +
                    "P+6vcyeTIQf1fBGITNpHSUrUoDJVOHjj6tEOcvG3nF3EuB+FKu8e2vnjB+SnBol2aqqI6GjnqD4B\n" +
                    "GDm6+cLhVvV19RWSuWueRzXTw9yw5bYhGMDP5ZKQYYmHmnJi9KG3jmXLwHvRUQA7culz9dddXGi4\n" +
                    "eQYn7FIL8ibO8sfXbp9CsaUHejJyDGVdalsgpp3jflVpr8api1yC5Y+92Kj7SU0EjxlkUaxPUl/s\n" +
                    "LSCGVqaWo5mAH0SN3JkEbuYRwtK5yPpBow1O/uZRDM0gytvJQ3zaTw/B/lMhykOMcBIbHE7HGbcH\n" +
                    "b5KxtRpFsiri0ninxplwk06Dbj84UXs/BI9GLgYsOSVRJNU12ueoq9ZzHrM5ncnMvOIlaW6SCnph\n" +
                    "jOoi1NqzwdRbpVRt5aSNg+MJiZusD6UfU1FTDbqc6QCkPAi/JrfqqjrqinmcdFRCxY455cq3kC4H\n" +
                    "TAFBqYwfjpNhz9Kzqamus8s9JUPUBoleMhdsafsXQ9hwzXXL1I4hz8oi/guc3V6l96Fbq3/KL5di\n" +
                    "/BdL8Hja57y/lfyQfrFN/dVwRlwbNfWP/Tn+0Sg0Iydszyv3mRfeUWFIDbtLRkUToSwggujKmtlO\n" +
                    "E1wgAwExzqdn+SSWFPb4wesHfSzQQMxGckJuBswjq6yewAbX7K115rxkt/iIDG2l/GZDjzyj1dAG\n" +
                    "rTZqyS2m1QU1QcJzs5kbwu7hnV5JOzEnbP19quURSU9xu29M31gdxkdwEdLai9V8irSS1g9Ru6e+\n" +
                    "XaMwYsgFRGbt/tI3Tymxa8AowPh0/aspR7VRT04a9LE79Pjcbt9ZPLaemhPQfxme0KiMsfaya6uj\n" +
                    "bGFru1R6KI/+9iWcvx52hu7l/wC3Tj/akrjwaV9Pcbrcmp5NZQ0ga+LPjXKOOj5Cy14uVO+0V3De\n" +
                    "YPx+p4f640oEiaaZwlzr6H6delauur5JoBDx+WTj1GroJPu4ElggvFMzkxTl08XZ1fQXGoqwCMpa\n" +
                    "meDGpmbduzfS6k9SqTffH1/e8f5KWjn3ZFqpqecX8ienjlb7VXTuzSysO91d2gNSZb5AkrQaUMEw\n" +
                    "EWiSn1Zb5LOydQ2Fv8WnnKOW12mEtGpj+Anld/qGmXiuoLdogGW3QSnzPizVNObfSay1wuMdPNEd\n" +
                    "ParcZaHFt5Sztj5rTKirq462sKWYKeE25NELSMDfXdyRql0e0V7TU0soXEzHqhoasD8HTKafFXPI\n" +
                    "MuOJcXqp4n+s2SVNsrcqiC1buGpKMdZFyVE0f4OrGmqzjkOQqiJi7XOukD7cKVLULi4DkqyFvS99\n" +
                    "n/MF5JeHZif4RHh2R3ljf6HjQY3bD/8ApCJvSF1cvxBCldqiacovhCV4nbyKqF3++yCwd/nFWuRf\n" +
                    "8oSyRdra48/sIA52enJwklcsiL5ePH95S3OoZ2xUHUSH2O80D/WdmVPJM2enPs0JmPOc53DU5nob\n" +
                    "TzNH+S8i6x6o+XBYfQxIWsq4KKjKeq1BEGkctGDuslXbZUlPUGFNRy1Q44ObBD/FOvyOemwxw6P7\n" +
                    "NT26PeXOijJsCdRGPU84xXNJdta09TQ0FvjH12My/Fv2UGW1t7cheKsCDD6m3MEbO3yXdnJvrKtZ\n" +
                    "J9lUwvrlc2Jnc9XFk+pnGnhOYurGBG/uFcV/yb7pcLme0jXG41tbuXptHjM5yaNTSatOp+X/AHLq\n" +
                    "O3lU9FsTtBUtweC3VMrfNhN1M11kPjSyn/yNRZ4O1LGP3BWtv0mQpTzyuCydGDQ04R+SAMLLQVz7\n" +
                    "y30T8ziwDx+atfSBF8WJau1tTJsUyZPNkBbu5VBG+FSHTtlbr4jsjDFC4vPTyz3WRgd2Ji1wwg+e\n" +
                    "/Qcv0Mqnwj0cdmvdZbYMsVJHDrbGBYnBn0j38Mc3yld+DC0R1Oy12rXHWVQFVTH8iOIDHHz3ZZzw\n" +
                    "j173HbLaaYXdx8eOFsv0br4n8QJFfTnpQeNfFDqcuhKSdmlH2MqueT6uEp5JDnijiAjM9IMANl3f\n" +
                    "SosqrQwXGCkttfJNJg2jHci3SZ6tOlAWi+NDUiZz1O9mdotEFQcEbMXkk4OxOPq9VOqtna+npvGb\n" +
                    "xSVFFEHMzTRm2fkkzadXt0rPzy4AYjgEybVz1LyP5XDSLSMLfQlFQtKWgOpmulwt01goc1BQAFTX\n" +
                    "QUryaWbqAZtnrdZ/vJtsvk4RE0sdJVFnrybzLfJ3ZsvLbTvUU5R2y1UVXPnVpONjmfl8188ufTq4\n" +
                    "rYfA9RuBeh8HV2Yt4RG4BILadXDozxx8lH/xpXXHrP2qp2gvlwG32XdTmbanh8aaFnHVjpORu/qi\n" +
                    "+pQ1wVNjvN0t94onhqInAJAOTUceoRLlftF8itDfNmo5OWn2JuYM7881xp6oI9OA6z69Dc2vt7lm\n" +
                    "Sskfj/iFPZ2pt4/EaaokCGFvOHLHnzuJorWPpNp/0LR2uW7V+4oeA/rDfojHziW9paSktNGNJQsT\n" +
                    "i3M5v1pH84k6jpaa10I0lK2Im5nd+sZecSrrhcM8gPgV2UphyXvt1VLV1e7EtT5LuboRWwl13G1t\n" +
                    "O0snJUAUOOzPWb8Fj6qtdyQdNcTpKyCpDrQyMbfNJHLbauFcVdZy+oozwybl89PvQlDUR1VNFPFx\n" +
                    "CUGNnZEC2e1eC9mp85tI+ZdTF5zdqizTgPOVX8yIPzNSYQtSza9eOZuVVsWplTPCw/E09XJ/WzhH\n" +
                    "+yBqvnquOAoqbV/Tmc/9xvpZSyu7qKCF5DS2OtUsdfKUINVylJu+oGGAAb0A2GZUe0e3clFVDBFR\n" +
                    "VdUT9Lw4w30rRy2sKiImyQF3ss/Ps3UBMWqUXHvFuKS64eUu0wVdNkWljN/IkbS6bLe3jHmNPlsT\n" +
                    "BCTi5akFFat4JMbFq70y+KprLxcZqoXp3hgp26XfmN/4Kz8fOopxj16+OrKmg2cp2PMuo/Q6swtw\n" +
                    "B1WFh9DJjaA0HEBRMeWHuRQUuhRm2EkpIjw3TklLryKGHoUmcMnsTG+FS6+I7NlAL4lqn0fN7VxI\n" +
                    "X3hc3VW38LNe9bfoqQD1RU4ccecSxWOC6/x69ZcvNaM4Si/LgU4XfKFzgkRFJjrLprZzTUbTSGw9\n" +
                    "KMCbI4VcLqeJ1vVnZFVM8ZEygA/Sj6lt5D6zKqJ3Z0rdCqyi3c/IXAu9M4wmTZFx9ZCwSaTyiKyR\n" +
                    "t6Lor3ASjC5xYh06uxmdBSO4EQG5MTdjoqObQWUfvmqhzM2fJyynSLeCt5U8Z5MQIy0rfUp1kMha\n" +
                    "KfIv3O3FZ4aCnMcg8r+jLN+S0lqujTa2q44od23S8mc/dZZXpP8ATWloZzaCQ5LnLr4G8wC7fVXS\n" +
                    "/Bo2Quh981MP1d7/ABXL7rIFVepZYXE4nqGJjboxqXSvB5W0lLQV/jFZSwk9QBMJzALuIh1vvEo+\n" +
                    "j6ym85+9LGVFFPFIRAEgPjpw+pFCDuORYtKzCHSmkyI0Oy8JkEBqZmgOBiAn30m6bHZykX7pKSOO\n" +
                    "efXHRSTRzuD6Djm3T/SlUwtJJA5amKEyNsd+gh/AiSgmnoqgJYpyjxgXcsdHaSA5zc3udkr6ijqD\n" +
                    "OCdwbebuZiyJc3Wb5q3Gxz3We609XUU9bS0E8OqM/GN4EmrS/Q7vjPL0aVLd9nbHtHc6y41e0Axy\n" +
                    "uYgDQVEDawERbVpdnWysdFHBTW2joX8ZCnpNyGh2N3Ed0PZ7FpnpOHKpJoI6PImMeH6QqoD/AOFA\n" +
                    "eNhOWsqaWQu96KlP82XT4KKnjrMhT0zDo4MEYcPm4Re4BtLjHE2O6Nlg6MovAdHm4XkwpZqYSCjD\n" +
                    "VLShDrfXN0aHfKxtdSVZ3W41IMLDJVTSg+tvKlIvzXYth2zWz/1lN+0a51bwadoDNy0ycz4Zs83t\n" +
                    "WlfGc9yysdpuckxGDQuOfKkjVtbNn5Tq5Tmkh07vSzBAx/my2XiAR/ooKlxb+ehgNRSws2pigFv/\n" +
                    "AJQBf7FHUry5hdaSKCunbGMHpy0ehH7PSMcpwC1MA9Z2MHd5PlcVc7S2mmp9MmuFykfiD22eN/ra\n" +
                    "9L+5kBbwjhLXT08Ln8ib83U9iK5H3iTM0XCECYOyORvwVQM4MZOUkXzpnH8VaXGopgYJKqmodb8r\n" +
                    "NJUTh+BLLy3Kspakmp5xhHPF4dBY+c7aldbdI0bC0XikpqUQOSLyi5Z/4MrO23uKQyaI5WJ+bLTf\n" +
                    "7ll7C1TX1Mp1VWdQH6J3Nwx+DLYWqkipZjOF92OPInYcfYlsvVP8K8C/lFS/zwf8kBLfY9ZRjJWm\n" +
                    "WNWht3h/eryM4zgN+uLeW9VCL/ayz98vNup6Y44WqZJz5MNPGbN8rDJ7I1ZGr2mq5pcwSFAHWw4A\n" +
                    "f5L2WvuL0+8CctXWbEbcWVRVQxPMZgBBl9WFc7PyVdbUSx5I4G5jPxc5dHzWSvZvx1j7D3e41FXZ\n" +
                    "IPGNDG87Z0NjySWOrJMTFxW52vkd6CCIQpwiacSZoaGSm8k/Pd8rAVxfGktOLxjf1HvOXpSE0PlO\n" +
                    "F+K0S79/ktvmXav20f7My6r4U/8A7sdsP/g1b/3BrlP+SvxLat/Xpf2ZV1bwo/8A3YbZf/Ba3/uT\n" +
                    "Wc+m+Qc4MlcQSbyzxN5nKqaR9cpv0cf3kfZn10dVGT8zPqWiQ0j4AnUQyYPpUkvCJAi/MqQ65srt\n" +
                    "lDZNiIrXSx5uT09QbHp1BmWZn4/Jjb8Fg7hUbyaqkl55ZXOU37zIidy+kiUFtN9Zn5W70qKsdzMk\n" +
                    "fqrISd+PuVhZa9rXtPR1gmAbl34mzk3MBD2e1Vs4G5dCluFruLVJaaCp9wKbKq6bd9rdFkqKiGOK\n" +
                    "qiaE/jKqNjjMy5WEgNnyuOVd1qSqqiZ4LXHvT1vHFQQhGHKI8AZsN1Ud41dDkO1GE2k2E3g0cz9y\n" +
                    "BltNzqKkoKW2Vs8+NW7jgM3x8lIzqmSpAdctPbj+RThj7GZPC+1kIj/JLW+G0s50QE7Imm2d2hYd\n" +
                    "NbY7/TRfzj2yZwx7dCio9na+5XiWji1BFFjfTyNhox/veqivyH6irfXVt5rjjitlFNVO2rMbHCwe\n" +
                    "sWHblXQY5IKKjp4gordTVEceiaSijMN91etrN89HW7UDSU9HY6LxahDAv1zPryP5xKmuV0diIIj5\n" +
                    "u111U4or3LC15t1CzrK7kLiqSrquUnygiqHN+Y9Sprncnc9AdVulO1ypQbPUayLih99y9KqyqDft\n" +
                    "wvQNtXO6y2a4fQngfvYXHZUaQzzPQnun9nSy3wHwXzV4OdogsG0gOZ4par4mbP2EvoumqGMc5Xmf\n" +
                    "kU1vl3cM5gWUmELJPlOI2JNxlYtqoSjd0RSRsCdhmFRFNu0GsMsykyxjhVI1WXwLqWqulNbqbe1E\n" +
                    "g6uwO10FX5DKuBt0qemp3fXq5cOqiTamWQykIBcOwOqoqzapwp/iYB3r978GQvRozjwyHkkYOssb\n" +
                    "BtXUwVGZtMgO/Fu5XMt4pqqm3sUot3tlNFq6rkahk0nYxWepriExcr5VlBNntQQgnw6EutbHQ0E9\n" +
                    "RK+AjAidTFIuZ+Fi+4hG1U8nOfPNjsHzU612lNraw59ca466vqKk+tMZGhhPKHzxThNelX4uG1tk\n" +
                    "xPqXgvgkvJTcs4qsJERyY+SiY59Krhdw+SngfBOtk2qsykx2oGfrZSGbLKI36y1TqQvglLIeUKTq\n" +
                    "SN8ioAqORF0s+htBdVV48FMD9q0rMkuI5nYMi6Xjb+Vq1elAk7nATDq1dZCCbt1leyMDznd3LioC\n" +
                    "Nj63H2qIXymlwUqT011qbXUjJQy7s27MMbP8oX5XWr2a2x39SIX2O2NxH4wLXCGebynZmwsRpyau\n" +
                    "bRSbiM6g4qKeJ2LXGdVGRuHyNer7Fz8mGlbYdFg8QsVrlqbhX/CIzSaIGbmd9OrlHj6esqa2bWhV\n" +
                    "1MsEVBuRZpTZ5qoBzp46Ry3EvVWJnukVPXSvSUUJ0+8I445HPk9Xg4p0t5oKhyOqs563/wDZq7dM\n" +
                    "3ueM/wAVnWpy6rVU90mtNRV0tHM8UbMLmzhlnLq8M5f3MSy/wtHQDPHcpeZtIvBNPoPT28HWQoYL\n" +
                    "PVVQRUr3GkqJzaIMwQz8xFpbm1x/sqzpNlI6e4A1bvqmlZ9JhG0cJv8ARI6f6l6uorjsVUfprNdA\n" +
                    "Bm06wnYxb3NIyVhDYySo3k1HSU07O4t46G+jfuLTIzj0Im77M7NNZp6/Z+K6NUUsxRTR1R6gfSWD\n" +
                    "HVnrNnV7llaekrbjMVHaaKrrnAN68dFAcz6ejUTA3pFRlcxq7Jv5N2Ljq1P26GTwmncOds57wQvR\n" +
                    "ELDw92lSA2R5pCUnXLYeD6beS1B4wO/hF8err/vLhIXOvkCj0VErZcBYAXc9h9ENNVOOnS03Z8hf\n" +
                    "P1DJFA9FJUHgI9BcQ19X1csq+hX10CKO6HBmUJYyd/LtM8v3gNkfZrbOUNRIcZuZuIs4UU8Jae3g\n" +
                    "buSy8W01Ezk4+KafRQzx/hO6uqTaugChEItGpunJz/m6wireZyuL1SxPIGuCXU3YbTj+LqClooHD\n" +
                    "G6h6O2onD8nVHXbV0ctTkoopoMaXB62YPtw5IGpvFsMNcUYU5ee18qjNvkq2Zm0MEDVguVZbIxgb\n" +
                    "gz1UmvPqjoVMVdJvKh9/Ke//AEjhMGD+uCbJtGAMTU8FSZO+pzkq9WfsQ8u0E7hiKLQeOvJMx/d0\n" +
                    "fvI1k4xC3st0qKABjpQwLO55d43f6yVz2nuLb8PGJdeeLOwcPoZZurulZN+vFh9RmH8EGEmKaVyP\n" +
                    "JO/Tx9X+8rrQrWW5Xw3hNteCfpzHqUVDMdVIEEMcXnGZt/gkFaIIK6vGCorRoonYieZ4XlxpHuyP\n" +
                    "4o+40tvtkYSUN/C4ym+ndhSHDhvOJ3N1M1hVJlKVDWvOWvSEDP8ApAZ8fR1laUly+DqfdRBTyA3N\n" +
                    "k4TE3L5WVnyvDvDzADF3MfSooK6KQi8YMg9+plGsz60m1Ppe3yrCqpYnDS469T4Y25tJcvF3WOuD\n" +
                    "5mJaOsnp3oACGUTJj4gzvnqlzYWZrn+OJbUr05rehxdexuos8e9SA60D6C/yVejat/XpR+7KujeG\n" +
                    "ud6fwUbVuHWkoDi+vgP3lzX/ACU3/wDtX528pf2ZVvP8oA3DwSX7zj8Wi4etUxN+azn0ny1nmL2o\n" +
                    "m2Tbus5n5ZG0f3UARcxe1R6z1ZB+YOb0rYlnVvhi81ADxJSz1cElQDTPNDE7FrPd6nAuwSbgm7uO\n" +
                    "OUWKvojA2IgkaR8fhqb6qMlhoNmo2qpaqPugIvvD/FVdc7hMTP1mfSr7Y6gqWmqJw8XqYjg0t4rU\n" +
                    "RzP1hLmFn1N1e1ln9pmeG4n2a+bShR0U3PnrLcXCqffSvnPF+K5frdu0kTeJqmkuk8HjdS2jlxvn\n" +
                    "UWAu6hHW7Vnvm1g1IJYXg0UAGW6aWP0hIbfg6z8tRL40Um9l140688U0bjWN1amZ+PBso1OraUdB\n" +
                    "JW1AxBU1zF1nfxubgP0rRzz09toxpKdz0B57u7u/nE7qrpppLBZ4oqiTeXKdtcjv5Hq+5UNdWm4k\n" +
                    "5ORm/Z6V1cVNYzLnvbecH3e8mchAD4VN4w7vzOTk/YnDBGzlJcJCbuhDp+d3Jk908XAgoIxgz2t0\n" +
                    "/WRa3aq1TShUMOgzGmF+lzfj9VCeLUY9ZzmL0vhkCLySGRk5OT96Jigz1nwo9MVEEDGLBEDZ9CbV\n" +
                    "tGJYCMG9LMm6wj6r83ehZZtZcqi9/pUQYTOb4XZvBttTLV0Z0VU5nLSA3P3t2alxvS7Muv8AgKoo\n" +
                    "32e2lr5YikOSZ4m49kNOR/iaxvTaO2tLzWenR4KvWXSj45mXPthaqpr9irddzPfDI8kUnezgZN+G\n" +
                    "laimrWcelebb4u9czzMzKjudxeMS3TZJSz1eRwLoMoN+XPxFLZVVBBf72ExtDbDPuN34KpuAbS3K\n" +
                    "pKQ6fJv0M59C6DuAaLRjCDk1w9Q1VZhpTDDhado4f0u6cfMykVFdZuGgQ961ssxv2IWWdg6zj9Kv\n" +
                    "MNemci2SqJD11Fx0D1nw+p0+fZiJi3YVEzj2vnSrYajW/SPuVhSAGO/0qt+umV8Kq30L0QCA6tLd\n" +
                    "rq8pJnZNlwzIKpqApYJZ5TxFGBGb+gVkyT7QXiO22+WoJ8k3Kw+suG3qSeqq5ayo4nI+pyXSKGvb\n" +
                    "amw3QOgm542x0CueSM0gGxc3BdvDTEOPlv8ASo1JwmojbQRN6U3LrpYDYjzwTpA7UEJuxIqKdn4G\n" +
                    "rqzeg+CwSdIOniPVTijyPKm5dhwnr/ZxY2L4zVpPBN2JFwLBKI2wWR4EiYDCYN3gnPubtWf6n+yP\n" +
                    "K8F3ZNnxGeBf3EmiadZSKjPIqeJ2QUb54I2BlrBWGC+GUUseeI8EjLDKIJMGWpUzeRryQsOpcgoi\n" +
                    "4kixw9j6qmtp/wAvibm+MzFw9YSH95DSuzPgftbiopag6WMqmJueBt82ehybj+S52hksjyFrJycn\n" +
                    "71Fld42itPgist3rbbcR3FVCZg4BNdJXDjy6nByHK5VfrZYAu1Z/m9tBTyWtpG8X8dhqQm06Wzq+\n" +
                    "J7Hcm9yUSrCmtR7u5Up78oCaZiaZgzoLztK6buI44SlqrpmB33u8ek05L6UPstQeCumpLfU3Paa5\n" +
                    "ldRhiOoYgmCIZtPOwj4tjSJascXW2ornsCLs9H4Qaik8wRrI4dD+c2qmbBJEwFJf2h2T2halk1nV\n" +
                    "1U2HCY+AyzFgsZ09Hq6lixr62Av5PcK6HHK25qpI8fQ67JDsZsJeLvIdLthWXOuqZimkGK60U0h5\n" +
                    "fUZadzq71xasj3NXURDqfdzGHH1SJkVr30ubdYdfrr7BAA70BYX6NFOZfmqis2pbANSxnpd+JvHI\n" +
                    "3L7mWevFUzRABOAH/XGH2qu34OHLIGoH6XrZ3/JYUrH3LSf8h3HwZVrT7GX6rAy+LnqR4uZdFIB9\n" +
                    "vHylxC5nuaeBsZXY/B2Yw+CvaGcHY8vXm7tI5ty0cQ9Z/kri96y+4ANT9ZaInqUEbs/6yJvbrz9j\n" +
                    "I2mj0wymNQD5bobWq2DlDm0/arGkNhpp9XciRUMTG3Vkx6FLEYNwl1uPfrUQTO+piMtPt0qWd+Qd\n" +
                    "XH56DqZLJHr+KbAemTV+SYR5Hyfp1JvDV3fPTxbPVdMPCDWPk9HekTM1HjJdcs8fkKUnCGEpJpBA\n" +
                    "G8s3YWVnZdnL5tDCH+b9kuNwCTqTRw6IS/1x4D7UEzucF0/fT9bv2l/tFpLtsBtLaIt5XUdOPNpM\n" +
                    "Ia+OQ4+GeYWdZmOThnm+lAIX5v8AjUhPwz+aaT5JNN/rexAT0hZEuxBVz85K1JtEIt3Mqas65IrI\n" +
                    "shy69F1EKljTD6B/yU+ttb52aP8AZmW98P7Z8Et5x/P0X/jIVz7/ACUuM21/to/2Zl27a6yUF92f\n" +
                    "qqC8wnVUJvHJLAB6Neg2NuLccZFvoS+w+Pdktlr1tfVnBYKXfRAWmermfRTQfLPzuPQOp/Qu4bO7\n" +
                    "G7MeDiie83OqhrLhB1rpVhpjifzaePjx+kvpW+iiZqenobfTxU1HGOiGmpwEIwH0My+fvDlbrpBt\n" +
                    "oMlxqpZ7dPHqoWLqQacNJEI9Gc8c9rO3cqynwzb+5W3wgw3C8WYKuO52zSdUFUwA9VSjqbfDh3xo\n" +
                    "LTn1X9C53FajkLXyt5TFGYH+aubLXy2a6UtxpGF5ac9Wg+iQeqYF6rsRC/tT73s7bqC4RS26N5bL\n" +
                    "XR+NUBSDlwDm1Qu/fGXB/m96IMPSGdCP8rtFNVDvNbTPC9NI3yTb/hVjtHJFPaRllq6FjDqQyHHN\n" +
                    "M/yThMsfJdZ6eipwYtEQh7OVB1TQNCDRRix82t+9UMjSntc9tBhp6ukuMfTJ4wE0M/N5mlij+kk7\n" +
                    "a2QJNpKyQHyEj62dvWIkH4hI1BRVhBrgqJni6OhxIet7cl9Ck2hbN9qmHgLcrN3KDVcmTmJhbUWO\n" +
                    "DK/2DtzT3I62ob4ijfUzP2ydn0dZVdDGZ3UYwbWThwZltrfTfBNkGDy+Msnyy/xpW3FTMs+S+sKS\n" +
                    "8zSVFfLOb549qCuuu20sT5xWT839WH95XNsoDrZiqZmJqOJ9Tv55easxtPVvV3SUy7OVb38yyqrS\n" +
                    "kci6S9qiJ+KaTrzXhc2zdMM+hSeNO49JMg+LpuXZLaSwMkn5elNCRzLAMlTUckxZPgPpVzFShGHQ\n" +
                    "nWgmyqkOQB8ld58D8G48FE8vQc8dfM79+RKP8BFcRqw4E+F9A+CJwm2AtNPkWE6WcH+qf5io5BVX\n" +
                    "eBZv/wB3RgL8oXKpFvQOkP8AiVjXRnSzE8XAe5UPgdm8Sh2gscvXpa0zbPaJcv7orW3INepeVy9X\n" +
                    "l6fHOYA0kmsevzI6M3bzlRE5wy6xV3QzBUx8vW7lm0ERzZFBVjaxLirKOnbynUhU4N1kVkZY2ppZ\n" +
                    "TctLlpQw0B6sYW0lCKMPJQWY9XLpVDZQwUmh+hWEbGAo/AOPMmE4PwQMgyy/W6qq9rjal2UuJl1p\n" +
                    "I90zfKV6DMZeqyxPhKrmemCmB/WdVXtFlL4KapwuRQEZaHjcdDdVUl6pWoL9WwF1QmLDI3waA3wx\n" +
                    "Lq1amZ8Y7eVFbdwMF+rDzza+31h1L06PPv6xtyhcD3gtyuglbTjvKfQqvGlyY1pCDcpCnlHw6RdM\n" +
                    "0OqCeKfHBPKTghRhP1VKMfpTjKCI8pvSpNAN2KYYwMehHYCEpYmzwFP3eC6FPGzMjU9nsceBRMWA\n" +
                    "FQC6nB+C0qzl6RZUJtxUpcCXueVMIhdaLYvZ99oLqNOW98Xb9McM0YOHraT5n9wrOjxMWyPF9POb\n" +
                    "C30vysuv7E7N7VWqiGe21GzUBu+phnnppKhvknG/R/rFlyWOtU8ngnsDylGNZcwLkF3CoDDai0t0\n" +
                    "xv3qUvArZHpKiSa9XQYIW1TEc0OGDTx/Udyu4m8IxSk8VkstU5Gxu8Z9L463+m+hVG31729tOyd0\n" +
                    "C/bP26ht1fEVCc+dZvvBceXFUZZxnsWGZaeuIVNXUXGoOsrZJZKqofeyHI+p3MvOJDyM6fg36oZF\n" +
                    "OHLcCjJaJIW4JpKUo+Tl1KHD6vKZAbzwLUm/2uqrjL+gtlEZ636GklLdt9zffQsXeJKcb3dAKrp2\n" +
                    "Lx2bDGel3Helgl1TwObFVG0Gxl3rYrpXUkdTX+LHBDT08oThCAuzkM3SLHJI2Ftb1shtdS0JSW7a\n" +
                    "CpuTh0UXwTDBgePQ4C4P2cvBRNsKhyi4NrAmDS2G67SHw+xV0TRMPPLEZevPJ/BE1MmSH4wdX9Ya\n" +
                    "jGfDYKpFvRvzWFatsuqbLuzeBu+GOnScFx6Hf+a09vsXF7hhzHVxXZLQe78CNaevOYKvjnp1VBAu\n" +
                    "NXKaKHnqJAjDvN8LVn9vYIIHbmb6DMUdFBF4nUaHJi9Mystndl9pL0IvaLHdKgCbWMhRvTxk3eJy\n" +
                    "YB/c7rd0fgpuEEMrbSX632jIsTQwGdZMTfJwGH9mtTiZNx8o3A+U8/PU9Oz11SNHRxy1da7avF6V\n" +
                    "nmkf5gNld0oNi9ibWTl8G3LaCVjyMl1qNELt3boGYHH5Uav4LvU0NENFaIKG00QZxBQU7RgOf8eT\n" +
                    "pTwcWiHHLZ4K9sa+Ep5bZT2il069/daoIQ+qGs2L2sy1Ft8GNhoW13vaSquUrYxBZ6UIw9LbyTXl\n" +
                    "vSxAtVO5VE28qZJZ5R8uY3Nx+lMVFayK10Vhs0wyWDZi2UsoFqCqq3OsnF/OY5HyH0urSquFdcRL\n" +
                    "x6smmB+kM4D6G4IEWyiY20ijIc+8L1zkttstNvpPiyqzmlNwLTgIxBvt3xfQuR9A4wtz4Wapqrbj\n" +
                    "cZyFDSQwuz95apX+yUPoVPRzWcId3V7PUdWbO3xh1dXG7j2tyTMP+PKQIjLPE5/70ZSwbwhczBvQ\n" +
                    "6KgtdRdrj4taaAjlnkI46aPmEAIuA6jLqt1dTktNd9iqLZq1BcL9c2kPW0XiVEYGeshMuaXDiw8h\n" +
                    "Je+K/VnK6M2hI8Fozpys/V8TJXNVI8wbwwICfoDp0D5qpp+LknWMJmcyHHpU4Mmg3FFQQ5JMO8f5\n" +
                    "JoZq9sNXZ4n/APWX0TjLL55/yUhcLptkBeSFE/8A4hfQ6Aw16oZKSu+LLdiDtNSnpzuyH/zJn9Ul\n" +
                    "m9tLH/njYa+hlMAqjPxikmJsNDMPV9z9D+q5LpV6p2qqYm8sOYHXDduq3aGybQfCtLWlJS0bhKFu\n" +
                    "CPRHNT6RabV3nnV7B0oqlxPRPHKcVVEcNRGZRTQn0gYlpcfpWo2TdrjS1GzVUYANW7zW6Y34Q1en\n" +
                    "q/Jkbh8rC03hfoo5KKg2zsWie01zANV6hFwAy/Yf1sLAVddRTUwhDTFDL1nk33Fn9XDMg4VdZ8XF\n" +
                    "LvXGOWMyikhfgcZDyuJCqLO/mwD5F+ZbnbeeS7bOltNRk+ueaKkvsQP0TCLjDUY7Gkbp9dvSsbaI\n" +
                    "HNpZfJZtDJgfBXVDQxUhyl4rHMEu77Otq1Ie51G+uVRKAE4SPqytRfGtx0tLRhY6ahr4NBHVwSGz\n" +
                    "zBofImGcOTu4Fqx2Kut8G4qxki1OTdHFEYk7xicZyrrAAT7Q0/WbHM7dXlHm/dFa+sZ6uUacHwLv\n" +
                    "qkNuxlBVW4IAG8SmTV8j7l4+xotPW+VlOgq46GgCSbjPUc7N6vYun8fGJc3L6MuMgUtqIAbQANpZ\n" +
                    "mXK6uR5JjMu11ubzWvUUhsPVZtSxEULyPyp83/qOOEHF2TCfL9yu4KXA8zICup9ExaeCwmuIbVsi\n" +
                    "ihyj6anBuPSXe6igbgKPiV0rkrWFRB6VLI2GJNgwlWPoblW9fGatrHwGF2DwH3L/AJthF0lR1ZA4\n" +
                    "d4EWr94lx2r4stV4J7x8HX6Wmlf4iqBs57DFcvK1o2e0wf5reESluYcIK9ip529YeqtYU4ThkH5X\n" +
                    "Q3hBsn+clgHch/KA5wMG7RH/AIRWP2RvMk1EIT6mnj5JAfsIV5v5FP8Aydv49+sNXUx51aVXjJJT\n" +
                    "yawfBI8JwmFRTx5XM6E0F/3Y/wAo4elEjfYJBLQeVQyxoCWlByJ8Kq4JfV12A+AugY7i0ep9apig\n" +
                    "Zkwo+boVdDteSXkG1aXyXcp4J6io4mxRh6elVFCABIL4V0MzaUrYgCZ6oKemNyfoZcr2jq3q6meQ\n" +
                    "nzl+C0+01x5NwB+1YirPXq9K24qovbC12Fbc3qiDo1u+XR+2zhJeK3TqcX0DnuIRFSbCU4SXankJ\n" +
                    "yYY8jrf5KAv0/jV0rXz0yPh2XZVw39Zw8MRcUBcoPihlH5Lqwlj0y945TsAcJxG+RVpZ7eL0ZF5P\n" +
                    "G8UxB3dqbhVAERyZUuULHwdS5dXVCTPFexu7Jq9ymEuvJL0XUIlleiqhIkHfKIjdCgiI+hVUik6y\n" +
                    "iKT0p0kmOCZBG80uPJ9KVpKIWWz1PFW1w+NTnDA36xsFj5TOrypO+UOz0VzhvZSUrMG+pmMwODUW\n" +
                    "lu3S/FxVLEbUtfT6uISNunytvYLbR3iKttdVTFDTuERuYTanMiMn1ejjF1Vhy1mIbUtE+scV9rKg\n" +
                    "MfCEp+oYavxZAXCR3LBOL8dXAAH8GRNwpWs1+u1qOTWNJXS07nji+6Mm1fYg6s2klyGrTjtUVXbw\n" +
                    "HiQzwDEZdzJEFQHAopm9Gh14T8U3XguRyb2JsjtUucc+pImqGHJAYh3uy9GY2fO9lYu9ndOnqDkD\n" +
                    "42olmxy4M3LH0oDdbHbbX3Z+yQUVvrTjpQczYAN+VyMnLhnT0urn/wDbPtJSzaKip1x6csbgBv8A\n" +
                    "aDrEW1s0A+1Uu0HJVxN6mr7xKY9afTZVJ40tvC9xpvjTxj/pcweyqYPxWooNk5rtpkgnqq6M+oVF\n" +
                    "FuYD81/GZhYXb+pjmVtXQbO7Hl/y9fobfUNx8St+uSpf5Uj5k+cEcCmKyecCwmhh8EMNuqKyngra\n" +
                    "wDaCKplwcuqs3nBusfJ2Czpng82fvNgu0t6pqOHxsKc4BmuYlBDAxkLlKMeN87iIYwYw8HfisdX+\n" +
                    "Fintz1P+ZOz8NFLO2mW43F95UzfLwTkfz5C9iy+0FZdr1VQx3+71Fy10pVTQHywxnp4aYx5Bf04y\n" +
                    "r6gVpM5mH0THfq28xHL8PxXOnciDXb2aGDh1hHQZ595umRxhG2IgEB9DLDeBhv8AmHFwwLVtTj66\n" +
                    "3gskTxRlwFSEoybUgnm76urt5ksYUuOVNwkZsbKcWdyFu9RAyHvla9tsVyrQ4nS08soM/aektA/T\n" +
                    "pQHMLnD8K3C/VZzwwwSTSkxn2iJYD7BFZQYcDktI+5WUFdUQAMYMTRMzcmOD6ejtVrZpbNbKX4Vr\n" +
                    "qY7reDmcaakmFmpYOj46VumQtTvhuj9pY2vh0RSJTbD0E8FcNZLHognhcY3Pg58wvq09bT6yF8KV\n" +
                    "xbXbrYPUZ/G58dnWYB/bL3K0st0r7xtDVV91qTqKiSPi/QzdwiPZ1VlNv5BHxuUn/lB3nRjtCGKk\n" +
                    "wHzScyda8XyhlyYybtDcaeusljjhpqGGejjkCaeBsSVGtxcd7wbqaSHpfr+SsqTapSUke8OLe6JX\n" +
                    "B+XeOz4+svaMN5OQCriNUzOZSRQ6yVzbaTWY8E2mpFobZS6OKNg3ngCqfgraPa5yb4p2toF7HapX\n" +
                    "0SLsYahfIvxZ2XzV4OHaG+bYEXAWitrv9WpX0JZxKnttLDNlpWjbVnzul2SJPVGsVtjbXrqM3p2D\n" +
                    "xgMnCxvgHPT1S9V+qthVvwJVFS+oSYkE55sxs+9k2JrLXddFWFcchz0efiYxl0i8IeVpb9pfPe09\n" +
                    "ml2cvlRa5ZDkCPnp5j6ZoS6C+V5L+szr6aq46x7/ALo96dHIwnCeOWMxDjx/ist4VtiT2h2XOe00\n" +
                    "8kl4trnUwBGDmU8ZfpIcN34F29ZvStK4Dh2zN5htFzNrlGU9mrYnpLlA36yB+sQ+uHXb0shL/RVe\n" +
                    "zN5qLTUSBNEINLBURjpCqhMdUcwe0fty3YiC2S2nP/8AC+0X/wDZ6n+4thYdjK7aizUlq2mte0Nr\n" +
                    "lsx66eu+CptctE5PvKbJszZZyFwfjjUfBEhko8zgMhSFMTtxN3zlWllAwrg+K1+jLK48LdFs5szU\n" +
                    "0FFYTeGvZvjqaM9YRx6RwRvnpfzVhqSvrZKoIqVz38nRh0FMf03W0EO/mCM5MB1nDKyV/kd7qb9A\n" +
                    "AwgDehX1yrQpYh3rk5O2hnbt0jzEs3d5GmAJw1ce9dVa4jDDOZLXmJAhuoHLkToJMioKniXKiVCo\n" +
                    "6hpNXBVtY+spX6eJYU8baIifoJBzuztgVjyS0pAimDEAedhExPnrdZCx5cVNhm49CuniLD4OKVwf\n" +
                    "AesvKM2N/WUVzmYpcD1WV/RV9V078CU9hm3N1pz6M6h4d6FlfkUUUzwmJj1gfWsLtKvo/ZC8NUW+\n" +
                    "IwkLU3M7P9q55tLRHs5tZPLExtQVR8Hfsckds1XPS0oSRNyPJr4drPp/urYXKCk2gtwwVceoGDSx\n" +
                    "s/8AjoXNauzSttZZ6lqHYci6Piq2NsEs7HT1Foqjt1a+vRzQzY4SB5ynORwLpXBamsvQrO0ZXM7t\n" +
                    "5KAM1AFXngS8lPLcqnVRSSYURTBqQs8jsSGKbinUltFMzEny12iIuKpvGMMmHIbgq1GytutQ8kxO\n" +
                    "TquiB5pMCp659Rq12ct2+MGLUxyuwrelWF7NRs5SQW7ZgqyoDmM3PHo6G/ZWHrnOGuNukc6vauhb\n" +
                    "W1UdJszBTU7jpebQ3yQHSsBPiqET/Ws+l/YuqrknsHXAw9Xqv0IQujPNqZXMkGumJibmb8FTzxvG\n" +
                    "Zam9VOCV9ZHvIik6cdKr1cRszSnGfADbg/pVQPAsOqgPRUuUzCdhWze5XvFeeUnph4PFPHgvBbK9\n" +
                    "FkEni4ogTYB5kLG+hLWbq9sEdIet+VWNLG0YZ7+lVxM4cSb6UfEfAWRT5T2VvDbr+jgfukVtV3m4\n" +
                    "2ncVlqqygPOk+QDZ+7Uzs/nF9Kq67npw9Egl90lY0wBPDu5Q1g7acOtLd9Jr8VFXV1RdLlWV9WYP\n" +
                    "VVcx1Exs2GcyLU5Yb0uvMu3WcPcoI493bfGTctWdGh/nfwRlZbaukjKSUA0N5YH0LidIUuOrnUZC\n" +
                    "/evPevNXpVIOEM9osnFBkeWQfodMznqupqaCWcviozP04QF7Y3d6UwPsfgj66CkprOdwrXpHFjeI\n" +
                    "IZH+MkfSJco4fvQdqjem3sZNzchftKk2lPNyxnlCNlj7LT6Xe0XhM2rvpytNdTooS6YbdmAS+UeX\n" +
                    "kL5xrGxMEbYiARF+5MzhJbEm1qzoYa26Vg09KxPOYFhs4yI+lU+eCsyn3dWJ07EJPHoYAd9Xys+0\n" +
                    "VNlVw7j4Fv8A7AQdn8rqf+9W6wsH4FmxsBS8OXxip/70lvB4qSsRJhdKf5K8SBJJZTelQDwZmbmf\n" +
                    "A95LD7bbT2qu2florZWDPLNNEMmIZAZgEtfWdhzxYVabe3T4O2fOCI8VFZ8TH36PLL6OHvXKMY7O\n" +
                    "VWKnkcfqpkBvTyyyQmLFMDxPqBi4Fp7+jq9ZRSGDdZxH3qLeRl1ZAL2OpikNJvlc2W6xWyolOWOW\n" +
                    "TWzDgPnf3lW7WVdPea+nqYqTcHGzjI5vq3nd+8o93IfVAz9jOSZJRVJt/olSX+pNXX4ont5da6lq\n" +
                    "rNBRwW6WOsCdpTq5K55MgIm2gYdDMzczPnLvyoO1Q7uYpC4k/eiPEalutTyt7WwpYoZI+kMe9UQ8\n" +
                    "KhwblAVLHdqgOAbpvmKuJ8do/Sot8DFzOP0pBqrDe6y21lZU0pxNPVtEMznGxs+61aOV+XyyWwp/\n" +
                    "CXtFHWQ1Ffc95SxyCc0Pi8Is4auPNo1dC5UFxjj81/epBvLaSbdjp+WgPsQ5yIOdsE3K7dz/AENw\n" +
                    "VfOeVkPBrdTrtjrZXGLCBxlHI/Di8chx6+Df0fF3fLvlaad+t5yQRScXVjWSSUllqIbFV2/4UMMB\n" +
                    "JUT6QEu/gL9HdhUxyYdO3msdfldqAzVdsftbNSjBbtpCM2y/jEl9qdZ59UAwytrTSXXY/Zioa63i\n" +
                    "quV4rTwG8qznCBh4agc/bno6XbuVvTSYQG0L66y1h5OZfvECcz0Hy14RLTTUtw+GLTre13OeYXy2\n" +
                    "rc1QFiaIi9L87eqXqqXY6lkhoDqZWLVI7jHnpYfK+1FWO5R1Q7R2Oog39PWTPUdLM8E4S8kolh+b\n" +
                    "iTP3i6sqo9DCwdVuVmW/FXKOW2OlXtK+bOUuOaN9So4339r5fcrm8SPPQSxlwHCzlmmdqY4n6zOt\n" +
                    "59YV8D00mjhlPM+smVbbuZNkbAEZOoaIjndyxnoTIH1y57lEb+9SxA7AsP2lp5AjeMHyk6LXUSCw\n" +
                    "6lEEEkxiwsrmKOOgi5nF5XW1YQ9kAKKHAvzuq0n1vzJtTVPIeXfKH3nMqmYKsHytpFAG+CR45kQV\n" +
                    "SOgllep1dI2CqnraGKnIy1v8Uzv2d37IrXUNVLCAyA2ODawfsNcx8HtacdyOkH9c2sMecK6kbxSV\n" +
                    "ZSA4gcvPjq+0fpWGrVfUJ0W0VN8HXCP49n108gvh2fzRVDdbFWUGpyCWSDskx+0oKwDpKkTBybqm\n" +
                    "BrTW/aJ7pGMVUYhWM/HjhpPm+cs703VTlmnjDlTuJZFPDuJbystNFVaXi0w1Dt1Ox1Uz7M1rDLIF\n" +
                    "PKYR8zmzcFx3pNXZTli0MtPT5bKq6lmAi0rUVMG7HBNglRVlO7lnCVWiqEsp5foiTTbdupaYN8eg\n" +
                    "mJx9CsttQEFK09Tk+oz9C0VNcaaxB47UfHEwEMMLeXL2Jox09Bb5ZTAXNn4t2sqi0aLptZBJWxEd\n" +
                    "LTsMu7bm/wAcdK6qV1cV77S9Kqkno/F62TJnk8N0MZFl0JagY64YzPAvy5Re1FQE97qp6eCKOKR9\n" +
                    "QAHKzLzdgbBPFHgn5mx2LWrJLOwQzCBAWp+XHegayDfanBulE1MhtOB5zx1cELPIcchtjOHQGdrA\n" +
                    "dhJvKZVsocxfWWju8L703xyvzKhLrYVV9KUQPgsEpxbKjIMknRv2OtEHYXotwTuhkhVEQtlk4nw3\n" +
                    "Klpd/kr0Qy6epFHDrfJKUQZuGU7LNwT4HyetKTqdWR6AFx0+5tKaJ8wowx3kOEAfIeFpriE5ES8Y\n" +
                    "x9qKpj0MKA15D3oqJ+YU6pAX5gj0xB1Xcjx8r/zJaCeeju9IcUVSDbzSWM8W+aqm8gznEfe2lVRR\n" +
                    "g65OWvct6W6ai32mKAJXNhmMDAmNw6NWr+6izpwkfWXWbofOlZKKeWF8hIbE2kunzVbU20Bs+Kqm\n" +
                    "Ex7430v9V1Gp9Dam3RTMTE8ol/WOiaZjYMGecdqGkvlBoz/KNT9mj/emDfaJuyp/2bfxQY+OP+US\n" +
                    "v/Rl+0s1eo2OuPzsCK0FsuMFdWbuFpWLQXXZh/NZm5VTS1kpgxaeq2VFfVW8UWfSnZTUlslJnDZV\n" +
                    "/b5IrbcwlEA+LpS4m7E2sh/4lnM41K+O1ThPRfCGmGCel8bZwz1NPlfdFSbtfgc4bBUWl+V56nj/\n" +
                    "AK41uh6FiPA0zv4PrdpYuJzl/wBolW70Y674UhGS8XpGDdXj7VEUnKkR2cdZ1V3yvkpQp91U2+lG\n" +
                    "STSc1VIwYH1RfpJNvV4htYRMYSz1U/LBTQtqkk/gPrLnu19PdbzX0uo7BSVsIOUFGdy/lPMXpBg1\n" +
                    "cO/sSgz/AAkbTxUm0g0hW6huG5pwJjn52bXxcRHOlZItsDZvirJYYy7H+Do3dvsVHfqKsoLrPTXM\n" +
                    "CCtDBSM8jG/MOpuZn7iVcT8FpEQTTf563Ni+JCih/qaWMPwZMk2yvh/9Pmb5D4WaykgLg9pLrM5a\n" +
                    "62Z/ehpLtWnq1VMv0qvSQBR19SfWnlf56jKqlfrSF9KhymoCUpz88k3fG/aotS9VGkE3ZSgeeGUK\n" +
                    "L6lKDqQ+svBJQifgk2fwDFI0c0oavXnkd+wsdPWxnp71oIJ3NjjPOuPpZ2w/zh6ze9C+CmNo/Bds\n" +
                    "uw9V6AD+sRF+8pLxG1FP4wGkAbOAxwz26Wzpb3CROpsCnfmUcU27PvF+ll4UjSAJjq0vzYdsOhiP\n" +
                    "mQS0A92eOkewu9UPhCvtNs7YorvUajninAIIc6WkLmL90VaUh7wN35YczfJ7Vz/bDbS3BX0TQ2+n\n" +
                    "r5LfUNU0805G4RzMztqZhcWfp7cqq1m3hbauV7OUB2627yb/AEyq+ON34uwlzN+Or3qeSofeYLip\n" +
                    "rldZK2WWU355D1m/eqjfvvPVXbWsVjDC3ykfU/HRaC4rLyR+KXInxgJOVaXXhkBXU4Th3okV+Kiu\n" +
                    "XKefJUQs8kYt3czomugkDTr4j3r0W0QqLVPYAMeuXCs4KQ5C5WwPrJ9vjYzyXVTrhXM2qOLq96Va\n" +
                    "RCpsfJPFSATBpcu9U1TVHMZPkksSTOiILdI/Emx7VXo6gEAGZIyCld0fHBBB13yS9KqijbkSrVOf\n" +
                    "6MGFo25lSVz65CcVaT1GtiVbKHKSL/4qp1qqTpayCoByYo31cF0yjcJId5vCcn+Njx0ZXLoGxxWo\n" +
                    "2eurwh4tLJo0PrhPu9VYWrlezqMFbFcrTzBoMD0yN8pVE8Bhq0sfB9LHjSgrfVHGZyg5OFR0u3Y4\n" +
                    "l/j6Vo42OopKhonzya2bHm/7tSzUyu3FLdJoKCrq/GGGDodj0u4cvpWliOnOwU9ZadoLjSnM3AJq\n" +
                    "8zwXaOg3fHuT5bz8K2engOgE5Y42h3j9g+cS59PRBDcq2m0CxBJpbV0sp7sezRxbb7QQzSxy1hVW\n" +
                    "NQaKnnB/cp6G9xXScoKqmipp35gMH5H9XSsmbyQylHLwNn08exOld3fncm8rLImsT6dbzWcw0Fzp\n" +
                    "cEWlkNQ0kmjW+dOehnUUFyqIxGOo+ODsLtV9FHiMd05dQi+6s6cUxLe/NFqKi5x5pTMWyLmIY+1C\n" +
                    "xQSwXWWeLhobizKxrmzQRP5Wss55fJUs4NJWSsXEZOX7q3czP1zBPGHAm3b/ALSsaEAehlA3Jyh5\n" +
                    "gZujHlKAg1VGjGBfkfPYiKNn1jrcmF+R2x5KQV9XNxF/wQ1Y7as4LizFlMqZH5m8pn0ppG7wA5Pj\n" +
                    "vymDKwNcQuLZ4KknDEquxfNPkTzxIVUVP6V1VfS+gpdKUbM6RNzL0FuzSaMJRtkuVSxcRTyf0J1q\n" +
                    "SImdk6N8anS6SUcj4QHmdR9OEXE6HgZ2CU+bo0+hKmPis6e9qnqFtE2oEDXNh8o6B+RB3Dqit7eM\n" +
                    "/tFE/KiqZ8kgouDC3SjYOCmtgIro99T47n4KrKkx5f2K2I0zr+asub1dFOULsejOU7duLcze9Gzh\n" +
                    "j5S8KNzjLT2rFqBGMHS3Ge1FQUUmeVh+lTy0csIbw2wPegH7NNubxA/fyKrro93WVAd0xD95WNGE\n" +
                    "71AnExGUZibsHW6ybcaKonraiVoJdBmR8W0pfZ/TKC6clhJWRFxAlpBar2iuVPT29xMoKXTmQ9DR\n" +
                    "gJfxIfpWbyruz3WSwXCSopRiNzp3hy/HpJn1fSDImDq7n4HdcewFubPlz9D/APWZVsydYrwSG7eD\n" +
                    "20uXEn35f9pmWrkkys7A+SRVUlyqKiUo7TRDU4fTv6mbcw/N4OT/AFdPpRVWznTm3egKu5NRNFog\n" +
                    "lPPUw3J9KdIz9JlndmfhG7DVbRxXCihKuM4YWkoTqNEMRkDaS3gYF3YiR3+ax7STyjNZbXfHjbE1\n" +
                    "RHA9Mcfo3jm+C9GtA+B21SXiz2a2lWFDSxvLDMIPpIGAzI+PZ/xLe1fhF2SfVatn63xmio+G4tdL\n" +
                    "JOwj36hHTx5uOpVWeTPgmIfO+39LHQ7X19HCdWYQBCDeNY3jfEg+kuL9GrHSs4S1HhMuEd129vVb\n" +
                    "FFNDFKcOgJg0GwjTxBzN2dCzPSnPvggxLi7r3CdpQZnSlhS4TdKAiLgm6lLheE3BSaFIU7SnIBoq\n" +
                    "WPg6ZhPj6yBV9heCmtjqvBnss8TEwQ0AUvHhkoiKN3+kFoatuXPMuJ+ASn2uqJgt4RXWh2cON5hr\n" +
                    "ZKUN2xFxbRvg5suXkLoXhQluWxuyVZdS2omOfIw0sD2+l+OmLoHqfKJ/QzqTSV0zU9TgnwB9vcSg\n" +
                    "KTmXz7cfCLthVCTTXeExfyfEKZvwBGUPhR2mCMIjgs05M2Hlmp5Nb+ksSM32J6yTeeEraaW1ywW8\n" +
                    "NYRTw6zMOk+bTpXMJ64J9Wk0PfLzU3y6T19c4b0+UABtLAI+SKpinkAi5y0rppXWGVvkt6k+TpUQ\n" +
                    "nkMiq3xozHBvy+ciIJs8FpWUYWM9RpEPSn7xnZVcsjvwJSRSciohpYmDB6XQ/ijTaWGUAH0qApsa\n" +
                    "kLPNl1ORqtip4wDd+MYH0N0qAoKMNJm5GTd79KqCndlGU7v2qdjwuyngj6rCwt3KCeuYur1VUbw3\n" +
                    "6ybl0biIH7wHInJeEbGXSgxd9KeKDTkzYURBkS4pcVLGDuJJgGDOiQd2L1u9Ri2NT+lSCp1PZq9n\n" +
                    "LicmunJyfXzMw9hrdbOVTNWRUZRnqlcg1ufnLkVNOdPMEsXAgfUy3Nvr8yjW078zuMoMx8WWNq6r\n" +
                    "rZfSUklPXE9Ox8QIHbGls6lk54ZDuU5ygTSu+t371vrhUU8jHUU4FvZNMwM3SwlzICSgjCsLTGfO\n" +
                    "etndvJLyVmplbm3jcISG2JY2YJDby+4l5Shv6Yoy0tLE2pvSKt6uikpT3crYxyuygOA6KQuGMP0t\n" +
                    "2igAqWnZ5QiJi16x0P6VpqqfBnGL5Jm/dVb4qENYDxajB8GDv5vWZHgBzTVWkOYG6ernm0pwmQFw\n" +
                    "j126l1MTc59D/JT6szCctL5wwFn5oqxnjxay18dzOxPjzSEvzFRVckc9ODhFy40v83/cgeqm5xu8\n" +
                    "5yBpbec4Ybo1cU6ucwiCcm0DIwyv3MRdKll0SRxPzaW5Mfgg79Mz2fQGr4l+Ge4kKZnfbzW48cmS\n" +
                    "nGNypugnID+xQUbZY+PmkjINeqXS48cF7f8AGpUEQs7UkvHRzsqSp/TFlaEo9cMvWLyuT5Szk765\n" +
                    "jfvdFPSnxEYpCWEiLC8Fsrdmmgfm6VOSiiHgpE6kaL4JNkfUSeTKLTze9KxQJjBt1jIOT92coXG7\n" +
                    "mJkXl+Vi1+9Q1YYITSiOlWWVGTOCZcwzCLj2Oo6E+RT1fGnJa18Z/YCNTxmhu1SiPZ3rOqxkD7yU\n" +
                    "Vd/BXytXoVFQtolKQtTiz9DLpd3t0dFWYpagaqjmbe087NjeB5yz5ToxU9j3j53hiTeanBajjYW8\n" +
                    "llqNC9GNYtWcC14LoSqbdvqcoz1aX9K0u5S3HKgMvb7cFIZuGtyftdEGx5JX24ZlCVOGUg4skklh\n" +
                    "aB4PQrG0HFBW72oAJgCMy0P2vpw37WfcgsZZW1HDLe7nFT5igzq58dHL5X3UB2zwVk4eD2zZ7qn/\n" +
                    "AMTMtNPVx04ZlMW/NYzwc0N0qtidlq21NNJS08501dGza23c9cYbzQPFyDp4dmV32g2RoLWwyT1l\n" +
                    "TPJ1jHlFj9GMZZveoubk088lRGTlIMEHnu6zFz2w2esbvFSV4SVTdLUtXu8e02fgu7VQ2SwlNeHt\n" +
                    "FRUTjoaOKljOeQjd+GgM4z63Kua7X+E27XTaayWmltVNZ3irRmFrxUubzy6XGMJYoNWOcxMW1dcA\n" +
                    "6Eq59glZ4J9pf87rhtLBcL7dSOCOOWip4Li36Eidj4s2XcX0Zy79duCn2ro9nRqd3tHtRtJXmHMF\n" +
                    "vkr9+er1YgAS+1NvdFtVe4a/aDx613G52zXT0u6pJ4Xmz+kAHafTp4cMiWp26EOVktO0+ycVVRbR\n" +
                    "XKliJtbn4lSC0BD1gMI44zy3Fv0n0q97RO0TKdY8lxfaCSCa918lLFNBTvN8XHP1wHTp5vWQCmrA\n" +
                    "jjrKiOGcqmIJHEJnj0bxtXW05fGflEohT/6p5hei3BLCQlhAOwkTcEsp3vQEWFHj6ynLoTMKTRJY\n" +
                    "XuMJ6Ajwni2BL0MvV4XCM39CA+ovBRtRWUmylBa6uI6IaZwp4Kmqi3wGxAMgCTg4aOWQMETY49K5\n" +
                    "Z4ZtrKjabaWWCOsCe12iU4ICwwNJN0SGzebqbQ3W4MXnKz262iksFnltdIHx9yt1AMZ+Y4RFHMRf\n" +
                    "MjiXKSnxODg4sMXPwduspr6cmm8mfJRVNC7Bztg35n9A9iiinOYyeY8759R8Ad9P5Kcqh31OT8z8\n" +
                    "zraiJKVm1IWUAbqpSSZfpSJmceVa1nZnjAbpLANklLExxvl15ExyHgeUWUsjO3+9OpIymZ3Usb47\n" +
                    "VCULnxJxZRFrBUBUh5QZ5d0hmyna+ZT6Eeh3S3KnE2dPF2RqMhxhThjRQ6F7kFWsAPueKeMKnE2T\n" +
                    "8t3p1qlDHDh17PyRlpUuvDIeV944gmEQtwTdGPkqfGEibgp1UgF1Z2ar3Mm7PqZ4ehVnQ/MkXQot\n" +
                    "XJ1s6zYaveUY70y1R8jNn6Fcyg50oSyv27rp1f46y5zsvcZGi5JOdunPaujWOd6+hnCXS5O4Ss2v\n" +
                    "o06hf9oVzWq1FVNPv6YJZdPP09vq/wB1VddShHTi4R6xZ905v0ehaOCkbxeeMnA942sOPHUPK4/4\n" +
                    "7kDLC8gbggMhkDgHHkMeLfvCkFMTu9FT6WF9x8U7Z7Os37yOt9nrLqde9PpAI9QtntfrICWSUGyQ\n" +
                    "YB24s75V9s9fI7OdZTzsbhJJvQMObycfuihCpGnqKU6qgqm5poNQafOH/BKpo4d5RS6W1k2Jmbq4\n" +
                    "8kv2lop7k1x2hiqZm0D+iDPrcv5qriDxW7nHnRFreI/QJahQsHADGErEYtw1NjzhWe2gPFKTC4uL\n" +
                    "uOfrK+1tAfK46wchPh9Kyu0OY6o6cn1gx8jsmhW0bZMtPW0ErCjj/StgdWjtQVtdgqBf5vBWNC+J\n" +
                    "uXU+eXiiyzRfG94i3IQ/vfurLk7v1loa4zCEzF8eSs4XStKJs86U8WTRZSCtUJY+C9LgSYD8U8m1\n" +
                    "Jk8I8Mo85LpUhNhkos5yOr3Opv4dUEp7up5dOj1Mo0vjIS+sh5fjCyWpybvToHeNxbyXRQWS0L6d\n" +
                    "SLqS/k5ISLklJlNUv8UqqgLHx/gp4HyZIWSTQPrOiqNuGUjwsYm0DyPgu/Gpbyhq7ptHu/F7VQwU\n" +
                    "9JHumjt1P4tCHNl+BFjVxzwx7FgY3bWOrVp7cLoWxt6o9n6e+UtdIUlHWU8dTSGHSMjOTe4nCR8/\n" +
                    "Iwi8dCn7CfgS6s3NbKv2gGv8Evgq4t1rdXe6nMvyWrpZ6iliE4tTxPzaHbgrijrop+H6M+515X88\n" +
                    "5dv8LnRUVYDc9FWt7ac/4KKQDDrxmHtBxXVcG3VN/cnZnbqySsq/mkv4nIimiYiYpYmLuc2yopDj\n" +
                    "fqyN9K7EUk7jzSm/tdDyw7weZgL2gj+b/E6PjoQwrzZzZi438KqSiCJoqcNZnI+Gfrco9/VJV8cP\n" +
                    "FbzZmvioNj7vTyzhBLJT64HZ+c5NRiwj9X7V03mYjpFIiZc8ANYZF856Fd0lLJNWDBb97PLIDjoj\n" +
                    "D5PlKCOn0N7FqLO8dirxl65SUOk2z0GRgX5K/osu3f5OM5UmwfJwInIfZ/Kav+C6SczmROTk5P2u\n" +
                    "uZ+AXDbAj2/GF/4mrXRCPlWd/VMp4V7m1r2Jqqoz0MNRTBnGS4zB1RXzfeblJUbaldyaogzVQ1TO\n" +
                    "0ODYQ08wi7s2rh5wr6Y262iotm7INwub1DwPM0McdM2qSSQhMmEfcBL542vvdbdtpAuFRbztm7jC\n" +
                    "nCGpbW7MJE7EbP5XP+CKM3bNir3br3snLNY6esgpqeq8UYalgY3IIgPVyGbdBt2rLS2mkrrzX1dP\n" +
                    "IFLZKg/5flnMKghEskAMbC3EhyWpebIyyz7PFZqe4nWz19fNW1tY0ejcU+6hDTjzjcNLe8uxR+FQ\n" +
                    "2othygpW3IPUQ07MHYHMWn7qr9JP1xe4tT/Cdf4k2KPxqbcN/Ray0fZpWq2F8H9z2qhKt34W+0M+\n" +
                    "nxkw1nIQ9OgMt9Zy+lQeDvZhtqtpwpJgP4Op231a4O48nQwCXnO/3WfuX1TFa6ansQQU8EVLBGEU\n" +
                    "MFNG3AItQt+Cq1lOW1Hgn2YorcFLNDXSzSas1s9TiQD0+phtPq4XDtpbJW7MV50l6iKDBuMc7/o5\n" +
                    "284X6q+0LrHT1lNWlIxMAZlcu5kHTSRBCEdJWVLAGnDExh+PK/1lGyXxKFRBIWiKohMn6BA2d1LI\n" +
                    "zh1+HtX2nd2oLhFBbrvQ0lyimyTR1sDVLNp46sHysqKLZfZ0amQqfZKwxA3BpfgqBm9xaE9lPkfC\n" +
                    "8X0Ptj4MtnKugOKyW+ntVezucc0GcOReSYu78vs6PJXAqykqKGsqKOtj3NVAeiSN+x1RZDLzCfhJ\n" +
                    "SZuE4gyBN5LsnaUhbSgLS9XWe7XKWvqtXFmijBmfRGAjpYVVxRyTy7iJikJ+aTD+T9CjJ2eTGOht\n" +
                    "WCb/AHalPR3EKSGogOPR402k5A67COl2Sa4iYTjC4Sm8pwsT8rcc4+xkbTUFFNp3tzij4aXZ436f\n" +
                    "bl1fbPbK3G60EtbZIhngj655jF29zvqfq9mpWXi9fYqYpb3RAdPnRokh0Sai+gktseI1Yi4Waeki\n" +
                    "8YCWKpp+04/I5uGpCZZg5ustXXXWjoprlR09HT/GRhLoy5s3VLmHsxn7Fmigd4RkKMt0fQeOD/OW\n" +
                    "tLotULHM0OrSzuSkjneRskmy0r+S6ZraNsFq1LatmeuDZZubAskTG4crfSmDPgsjHkvSpt/I4E5s\n" +
                    "LD7FZBDBg1aj5u7Cjy+e3Sp95rPoJ1LuzMejHtS1yXgXW3zksSPxHUp/FfOP7E0QMC6eVGoyi0Se\n" +
                    "U5JwtI3aiBNn0sfD1kQLB3p1gwAtJ3qWOOR/LJHaQXogyeE7BxjNu1OibMpehSk+NSUYYFGAbICZ\n" +
                    "nrKUlEbYdUEZsou1ElxFREpkFS1D0lUEg9Vl1PYy6QNLuDjJylYgZ2fS+kuZvtXJ5QyPcrrZ65HG\n" +
                    "AuJk0sD6crn5IaVs7NQ10fjcTBET4k0GbydH2Kx8bqIaktUAicUnB8LMFTyPKVZTym4z6JeHZqHi\n" +
                    "KOuskoTQTnKbDIAngz4OXb+8sl5B7WwmD1WqUtUekgAXzkOli+hVxUUjDSy7zmONi+ry/uqxuDxV\n" +
                    "tuCQtLy6HifQ+rmDoHPs0qtpDeSyB2nTyFE/Ho7v2SRVKwuUcevxgNLFI7S4/H7UJtNuzqaepibV\n" +
                    "FVx63buPtUsUjSWwXmjEzjch4P5yGuUzzbPcof6JMxOzeaQoAerDeFvc8k4b3h39v26lldppG8bp\n" +
                    "35nJ4+OfV4f3Vp4nzbAkHU+iRx09wlx/vLF359deTdAt0MgH0LMFSGvzx6VcRwtHMPODYNUwxu7a\n" +
                    "+9hJWk55Pg5FlhPThFlgb+27hMNY8ZNLMyz2FfbTvrqAfm4tr6POEf4qjFtK1ozsQtpUpNllGKkj\n" +
                    "PsWmEo+h1LqTsZdIm4Jg0uIpgN1k6ToXo8IfK9rOs7ydUQ8CU4BvBx9Cg6XRMD8yuiZNHjLzcCbp\n" +
                    "Sqj5cZTpHxUE/egauTXUEw9VuVFuoVUgzJJ6qs4OAoGmbAoyN+VKsJkZE/MrmkBq2hlg1/GwsRMH\n" +
                    "nxl0/R/eVFE6OpJnhmCQH5wfUyonUaTacKoKOcpMzyU4eNMbcHmHg5e/Tq960FJNTVXGB9zK/kPz\n" +
                    "M653srXUlDcc1VIFXa6zkkAevH8n1uKNrJ6mzuZ8xwZ/Rvxbm9bsXm/k8UVnLt4bzaMOg+PyUhCB\n" +
                    "Bge4+j5pI+kr4qgeR8H2g6xdm2mgrodBPvOGl45Ou38VbeKu4FJQyRGHaBO/D+C4+6tumm1+qlr9\n" +
                    "VZuK6VFPLu5WL5Ej8fml2q3pauOobMRlqbpZ+lldbJmr5cGNER63AWJ8iDaWbu/xqTBZTD0L1XGc\n" +
                    "IK5t9O01YD3Jy3G4eVtPbzCzD95VAvwVhvKivqMQ6XKODhx0swCplUO2+Ax8bCC3lb4//E1f8VaX\n" +
                    "ray4R1lTSbNbMVO0ElKeiolavhpI4z81nNn1P6MKm8BB52Mi9M5/+Jqf7yL8HZ5oNo+0vh+oF3/+\n" +
                    "Xplnf1TI+EHam1bS2WC17SR3nZOupqoKt46m3nWbzTFKGmMo3EX/AEnWfT0LHR2mW42S7S7KVlTV\n" +
                    "262hrqvHYwhmdi80Gzytjtdbnw7tKdu2ejieFgOomObeGzZARAdI56f0vYuWwPU2dqqmI7jSjVRi\n" +
                    "bgDSA7hzC4mD6Mj7fQqqh0HwfbR2q07L3E77XhTV7VWqQ534yRsAiAh5R8d7ysq3wg3avvGz0BjZ\n" +
                    "qmktsldCFKdTwmqZdEuNMfSwvzdKrtj6Krqq6lu9Lb6+50Vsl+MxUQRmDddxCJ5HLVx7l2O0fB+0\n" +
                    "EtuutO2+oqeZq2l1n0y6DBiLPlNrL2FhKepOvgHZPZio2W2WpbVSsHwpVP4xcZ2NsMfmj3iGrH09\n" +
                    "63NI709CMUtQUw9ZnfPkiq2laSesp6mqlhAXhIHzMws5ay5R+6n3Kd8VTD+ppzANPfpTS1t1jJtm\n" +
                    "aqKLS80tMfS/DUQ8FnLHY5/HBq62eEAjYieEG1OfLw1H2f7laU1X41Z54g1SVEMGlmbpfu/ZTZXk\n" +
                    "pKCnpyk0TyNqN8O+j6ElhSqIGrCD4pwBtbkB5H6yErroD8RPI9VmZDSRvXHLojFotZcXbTnT5Pyu\n" +
                    "shambeVIADiFPBy4BuLpoQzzbw5ZDPoXFPDPQhBtHRXEOA19O4Hw/WRFjV9Qw+hdfnPEhmL4B34Z\n" +
                    "WG8MFN4zsfBUj1qStjN39UxKP8TD6EBxpeiy9XgshZYTsO3V6yQpwhJI+7ibJycjN3kSNQFG1Xd+\n" +
                    "JRDJr5nxNGb/ADuKgloauep3A04RzxtpdnmYdbkRd/leqjy2D2kHps7+16iD++p4LBuKCttlTAMe\n" +
                    "0u/glooNbO8glrYwHGRcuqg89CLRe9q9mYCoqeouNoJ8HuZoN2f32QG0e0V/u0pBeLxXVY9Zmkmf\n" +
                    "H0dVWkcYW2y1tsroKapuU00JsZw6jt+giJwAuwjyLOLcvBVFdSvPGOjrt3paxkbSg2eq56WqnkpZ\n" +
                    "ShNg1a2wT9YRVjPUSSVG9q6iXRkjNg4ZJTbJ7M194p62O3U5T18cwA0DGAZER1Pxd2Hyu/sVreNh\n" +
                    "tpbZHQjc7UdPFXVI0cR+M08olITO+nEcjv0MXMmFLTO0zA4ngX5sujipYnhFiMDPL5Fm4t85Wsmx\n" +
                    "d5OsqILTbhrooT0MFLX0c8mn1o45Hf7FFPs3tBaqM6y67P3ShpB0C89TBoBicsN7OJKqftA+pUEl\n" +
                    "K0Zlp+1QSu2OdsiysqluKr5eArrmMOap8G7050Y9qdLVRgOEMM7OOOgu506KFzLJIyD4zCTsL5yh\n" +
                    "KADPoyjyBmDAukEYRjlVjJfqF3DM2MCyiJnBuR8KwE2NMOPSOVOD2VovI78+pkUHtTZGZvR+CZpZ\n" +
                    "u3QX2JeAQJZTt5glHG7gPxvb2spN2zjkVQITZ/lL0mQ5xuL5TRmdusmEhMoi4EphNjFRSN5qLB4/\n" +
                    "lKIDeCfWD4F+V0iyyjPnAmWVq7HXp0rZ6tiqqAd7JKxx8vIfSxf71qSmje1ie7NypzEMO+eUv/0r\n" +
                    "lOxlx3FeUEri4m3ltqXSbVXPNLPTmYtrhcQZu8eP5Lms0HjiSgrAijyLHvm49HY/7qz1skeN7zTk\n" +
                    "xaX0VAN87+BErKeZnMYopYmGQCB8HqPmFZ/Zyq3e1Y056XCeDQ+eln1Y/B0qhcUzvVNOGjAvDrb2\n" +
                    "iQ/8SjpIzOOvpsk2+p3xjvEhf90k6A5I5omLgIGURszdhFpdD0NVuLlTnK5Nok0ScegdWH/eQVUV\n" +
                    "CbvS1URNyvHrZ8dDjzfhqWGrjc6k3J+laoap6GolpzPWQSEH7qx07ucpv5WUKqtKZ3eGJtZMrPRo\n" +
                    "hgMnLjH29mkiFVlvZipxfrEx/uq4w3iQsQZw7i2H+S/5pEptoZGM6XT/ADb/ALWFTI+6u51OObSD\n" +
                    "acOgcYW9I6TYsKQeDqMeKetCOF1KL5ZQ4Uo8EA2TimXD4jTGbYLtAwwTKaNmc8lp0s/a+GQV1meS\n" +
                    "pxk9DdDPNvMfJWV/VVqfFxHKnj4EhYHyCKjWlUSVS+Cygg4mprgeNLZ6ULG+FF/VVHRqYMIUHU4u\n" +
                    "rqQyJ8IgHQoOpQfgmmyyt9bUUh66WcoyfufpW2rqsK+0U79Iz87t8kf4rn8b4V9Y6jMJwF2czLn/\n" +
                    "ACeLamf6bfjzrJksJ08uQcvObHYry27RywOLTGbk36wOlvld6EnBjdC3Ck3ZE8XVwvO/663RKO60\n" +
                    "dxphaq0OD/rA6PnD2Ig6WWlcZKVykDrNx4/NdY+0W6mr6Clls9Z4jeWjAZqapf4mpMR46X7C9V0b\n" +
                    "bb5LQV3iVzY7dOz6Zo525PlKb8P2Iv8ATj48CTxdNFsJL1nGcT8hedhaG+XGIzpWpHFgjpd1gA0s\n" +
                    "2ohL8lnCdHHB4qcWuTJnDvfYRF1foUS0pnEu3eAcxDZKl19XxqTPs8ZmS8EUjzbJVdY7u/jl2nqG\n" +
                    "z/Vwxv8AejJZfY+9fAvghrascnUb+eCnAekzOaXGn6V0HZe1fAWztttBExS0kGmUg6HmdyORx+e7\n" +
                    "qLyn7UnhLtUd5nscUNzrKK7QPNLRR0sbkcnNC5vqZ2IHDQBMSzNno4qPaF4NqAu9yuNeZU8LzkEj\n" +
                    "yRNp/STSTs455W7dOlaXwkzbNjb4g2hucNurwF5aGbPxgP2k2l9Wnh5roeytU7dWGinq7lR26e2z\n" +
                    "aqK5BQSHJUcvX3flC/Du6G4JbGtb/s3Tx3Kgn2Vjo9mrlVOFOwQRhuJCIuAmA5F/lMryw2Wos1l3\n" +
                    "NQ9DLcZJ5J6s6EHaF5SLGWyzdjB87UuRXi23eu2xo6C67R2K4z1VeFLu6aaQ5Iw3uDfd6NMbswu+\n" +
                    "M9i7iJRhAMFFGEMUIaI425cAPQmDwoo6oIt7GLSx8vJ6xIC7s4VNYBsTZz72VpYchUg8vVkfTzP0\n" +
                    "KS9U/jdXyARi76fmoQGtgHSVxnMYsExiDuHYw+SrK46KGUHqJgji0ZYz4Z9g9qGlqPEmKWo3TE3M\n" +
                    "bvzYfzVmh3l0rJa2ucgp+zL8X7hFAOnuLzU3xJnBRR8rO7apqh/Vbs+8oII5HApaiCaGLyNYOwN6\n" +
                    "xP2pk9bLrOWnfcCDaYwhjBzcfVd21MioLTvoRkuBytLJzOOebHmkSYAFPTvHgoicG6D3mHf7FUbY\n" +
                    "0PwtsfdqCl4yzQ5jb143aRh+kGWsOlgAMBBM4N2cPzdCyUsTcYoKiMmfpc2JvwS2D5aF9Y5705br\n" +
                    "wrbMfA14G40QYt1c/HH6ubtH39b6Vh8YTWaLsirYDzXKjjDVqOcBbHykIrPZfjtNaf8A3uP9oUBv\n" +
                    "JaWTUW9lmP2yOstcrPFVbUDLLUCG7p9bx4ciceZtQ9/WXRqmn32rsJY7ayzXmhq4rvQU8xhHHoM4\n" +
                    "WdyYdWrs5tKVfTA1Oy19qqCgrKG11lYEkAA+iI2kAtJu2tnZuXA6dXoWaxLBVnFVQSwyxvpcJGwT\n" +
                    "EiLxtLFeIoGrXo2li4bzWDOXof8A3qG2U013nGC1009fOz/o6KMp397BnCueyWGy92vFnvFedlgl\n" +
                    "mOfBfF8HDlxqzh/YuueD7Z+41t5HaDbWtmuNbDkaKimqnqI6fVqFy4u46seSyG8G+xUkFTLTXirp\n" +
                    "KS5Vekgt7yfH4YSd9X3uVdPt+xr0xZp59JLObG1NunkaMQDUwNyszHhm9ypfC7bqm8+DG/08AZki\n" +
                    "iCrAB4uZQyDNpb26Me9XNstVTA3O5utHTRvHHh35lFf2D4Qq3AzyPEHYSZ27WVfOHpytXtxYg2d2\n" +
                    "svdmpv8AR6Cq0RN3RmAyg3uA2b3LKynofm6rr0v2rlzfqAnjyKhjqpKd8ZLT5vYi5QzxZCTx5UWO\n" +
                    "oqKuikf43kLv7ERJlx5eI97LPyAYPkX5VLR1ZgWBcm9HelsqYhooh0BzJu8Z+CDjrQkEWlbQXe3Q\n" +
                    "iYwy2sXFx72WlbbI1Iud8JslKztyuQ+xSiej5ScJu6vogJBLD5ZSD3OmRzPGXJ1e0FYm2WURw6x6\n" +
                    "Bf2pYGz2OQJA5fodMkjyoDjcD1h1u5SwVAScmcG3SzoyMB5AeMuVPjkZ2RRCzoU4XYkG8JtSiJsK\n" +
                    "UXfyk0mykIClI9PME4dYHXRrRVx76lrA440TY7+9c8kDIkyvdkKt2A6Q2IyifUzZxkCXPerStm/l\n" +
                    "fxWaWKKINLHqDj5HYXFZ+rh3N4Cp5oyeTTnuYuhaCpCOop6OpHUxbvdfGP5v+7Squug8YojYtJHj\n" +
                    "Sz+kehYmtr88jV9Q4aQGb432O/N+OpUN3mBqicycn18/1kUcjzW2jnLVrcCA2BvN0l+arrhugYDI\n" +
                    "+Z+XOflIAW7zA16qpM9Jif1uZZoJMzS+3Ura9TtNXQOPJrhAX+byqrpHzUE3ylSoX1qj1U0vW4OB\n" +
                    "Z+srImALbKY8CCRuXt5hL+CCsx/FVWrVpZmL7yMkk/k07bzsE24f470pNmK495Vyn6UPhSmbmZmX\n" +
                    "Wd9S8XRTxjPqMm4p3lL3SmimEg9KcT4ZNjTSfnwi04gJQ5Az+DqvrA+NJ+nKKOT5XDvZRSvvIs+U\n" +
                    "yziFfqbSPyoqN8oOl5dSJB+KupIri3KD+UhQfii65/iw9qEjbtUT6I8FA+lTA+pCxs6Jj4dqqAIF\n" +
                    "0QLoQH4qYXVEKjdH2yo3NXE/k50uqvXhSC76fWVdJ/VtS51POOIRRUGz90mttHX09P4zT1EEdQDw\n" +
                    "mzlgxF25et0EoJWzSZXkfkRq7eGwOmw4EHpVsNe09KNHdoPhCjbqazxJD8g+z5PQqSN8Skpxf0rT\n" +
                    "hnaiL+ueJJJE/BdVWTxWltp/hGvAKozjieMpdfewqpLoJWVyrvGjiCFsBHC0TMyU5VV0fwU2p7pZ\n" +
                    "bHUzYKkt1XWVTxv0nPvRaH3Nzv8AKFl1QA5ubiue+BaTGxhN5T1Epf2pra1tx8WkpaSicJLrWnph\n" +
                    "Bw1tBE3XqDbgzs2RFgzxIhbo1LO/ptbY7dE4eNy0UJz63GOc4Gc2DSPKLu2rp1IOWEI7xK8Lk4xP\n" +
                    "qk48ALzflLPhWSWzbultJ3OrOC62Y5ghqqxjqZaiGYeI5dgBzjM+AaR5OHVW2pbG9XSNHT1DU9OP\n" +
                    "kbsX+8xuowbm9NsPQUu2cu1cM9WBvNNM1NkCj3komzl0Z/WEWnK1QOBgPWaXvVpebL8H0ccpVMks\n" +
                    "hyMDBhgBmw79Hfw71QnrjfIHj2K4TqNnkkcRMG+NbmfoFuXo4o7MUzDJSuJ56JHf9lU8lxen0fFb\n" +
                    "yXPDhw1I6hqJ5wJ6rdb3t3baWTMHLSHUVYvUatAN0OnzwRnEIY5W6GRUpOx68k/ksy8lOOCMpJjF\n" +
                    "sJBBBQxQ/GGAsTfYq6uqAnOoaVqkBxqA84+rh1grv4UbVW3Segpd8dLGejxk+pI4+b6ufVW3t0wV\n" +
                    "tGE+vkcBLKCwp5aRnkIxYX7n7UTA08fDmbudnUQVoSVksQML6JHB8dmklbwRxyebq9qZqTbO1PtB\n" +
                    "sbdqIG1VW58Yp8ds0XOw+/Tj3r5zKaLAmUsTC/NxNl9Z01K7zgwuTFnhhXVtslroCJ6K2UMBPzOY\n" +
                    "U4C7/YltgsPj632qvuhYtluuNcX/AFWkkkb6WHSuhbEeC7aE6qnudZRVND4vNrCmnpz3kmni3bwX\n" +
                    "ctvb9PszsdcbrTxtPPTvCMccj8mZJgj1ffVlsZcDveytjusoAE9fQw1MjA2GYzHU7D85O19YzJM9\n" +
                    "YdiKgdMlw3QH5mtuC3NJZqWC3BFu6dzHi7mGvt9qNCHDKI8MXlOXsWE8sHlXNY6AjOWYIt52bqLQ\n" +
                    "4/a6fHRyxVGmOeZ6QYegjfr6u72fiju3GPepQjyptyyTHbXbORVctBeqcH+FbRKFRG7Ezb2MTycW\n" +
                    "ezUOrHtWjtV7s9XHmmrqZjHrxySMMkfoMX5mdGVNL4xST05PgZYyiz3ZHCGe20d2jiqq6hpJ5XAX\n" +
                    "YjhZ3DvHPSq4rbL3zGJPqNprFTSDHU3q2RSP0AdUDO/uyo5NpqAXwMdykbHAorZUyAXsIQw6PtlL\n" +
                    "4pEQDyh2AzMzN9CPWxvj3wtTjUeEjaSpAJgaSeB9E0Txn/okLdD8exYSrhyxOPV7l1j/ACgqJqfw\n" +
                    "mVBgxfyugpqh37NeqWN/uxguVSvoJd3F+jlv6ppAkhLkfI9yHOR/KYlbysx9XgSClh85kSMgicDU\n" +
                    "BRtqyKIlgxxUMjOyicrq9EsqWKeWB9cR49HY6FzpLKlF9YpVEriC4wT6WqA3Zd7dDo8o+TIPkX6H\n" +
                    "ZZgmwpYKuelInp5Cb0diqLzBaxK90H5XBPHg6DpL3TzNoqg3J+e3Q6JKRtOYnEx72WtbQzmsnm2V\n" +
                    "V10bOWQ5TbtZG77imSgEg+smVegdJV55JesjCfIqprKc4y1h2eUpKOt1Ng+sorOPV4yLkBeD1eZP\n" +
                    "1axTCbgqJEbY4qOjqPEblBU+RnQfyHU6FnDLE3kusrV2VDp1oq2noqqCV8kGmVstq9D/AGfgihmD\n" +
                    "xaVs5J+YHbsIdSxmyN0YHiaZyco33R9+lasw3csrRORlG/Q2Fz64lobb5N/aDaVx1BPq0N2ah/8A\n" +
                    "0rVBbqSn2QqDlnikp5Kd5mA27dOdKxFMbUlRWUwyDpkAhDj0uJZYvuqpkuNfNQS0hVJNA3M0b9nn\n" +
                    "KZg4wArmZrVRydJs7xG/3v3kJbGZqodTcueK9qZs0BB3SCSZQyaJQMX6ObGUKaG3lGxSgRkwvC5e\n" +
                    "jlURSRs5NrFheNx4fJLH7qJocBVg3ku+h2JVdVwhJtANw0vw4pAB/jKcm+UvehddWJ2PSlhOS6BV\n" +
                    "JRk+BJRZwBP+LJ0ru5YTZcaRYfp71nf5dLgzpEkyA+cg8l17Hw1KEuBoAiNsak4XUe8yHrLwXRtg\n" +
                    "H1b5h96FF8Kaf9D71CD4+Spn0R4njNEASgiwpxVQEsalF1AKdl3LlZPJCNSlB+bpQgs7+hejnv5k\n" +
                    "9kvo7Yl9Gx+zhi/UoKfHzQH+6sVXUrUtZW0Y6tNPPJEHsEiFvs0rZbEs7bF2HU/TQxE3sIdTfZpW\n" +
                    "d2qjeHaGqfGBnYJW+qIl9rEvO/IrmJdXCobPQfCVzCk3u5KRiwbtqbIjn90kVctnbnQsRnT7+Jv1\n" +
                    "lNzt9XrJ1oPxS80U/dOAv7C5X/FdNxgiYVn+PPp8tXy2TphOnKMl3sXhOjZWjpHBgct68Ooyfzi/\n" +
                    "3KvUwtIZZPjw1dKlVfHX/BNUwUexBy1UjQwRzTGZv0MO9PK6NsFSRFQ1O0V1IoJKyNqqQ5uVqWlE\n" +
                    "SIBcuzDOTv6zuuVeDu0y3rZW121zbxavuzUMkejLyRuZzTc3Z8XDK3vXTPDhTwUvgzv9QMTtVidN\n" +
                    "xceAMVTC2B7uDuyU0z3KJmYlyzarwn3S4+ECC4WWWpht9HJptsEmdHNEce+cO92kPp5myvoHYC/3\n" +
                    "GXYikqruMtfdNU283AcXxMYi31dK+OrbXO14pZz1TbuYDx36S6q+tPBI5VuxtBW3aglgqIZpt3mR\n" +
                    "9JOcx4wGe4mbin1hUzOOml2sMzpaJyDQWvUY5zjlfgskTc/Nq0o7aK6+M1+nWzU0b6BfsM36S/Jk\n" +
                    "DxbtWOygEAPPcZZxM91HpBm7H7yVzTGzMWltKBKTA6A4KKeqClpjklk0ADajN+xkzE3O60lpt89x\n" +
                    "uU4QUsDanM/O/MlwLbbbyr2qqZaOllKhsz6s/wA5M3rdw+qgfCHtd8P30wPVJbadtFPCz40F2n6S\n" +
                    "WZKHRTQS72I995A9LKqksdlbP8MXgIhYmo4OeZ+jk7B967xR3GCz7OS1dW46JNEUEbdr9Zh+jUS5\n" +
                    "zsFa/FNnguEzEA1ZnLl26Yh5G/ZNaS+VTzWm1xyh19c27fs1FyfYmB1lqAq6vOstRu5H6SLylt6G\n" +
                    "GBgHSw6u924rm9oj0T/E6tLP2P0Lf283PramFTYoae1Qs2qUu3lZWnYqmhqGYcE3K3RhWMmXi5d6\n" +
                    "xP0ODZdRVVmJ8L4HP4Pb428LdB4tLoZm8mphLp+ar7wHl414MLDJnO7CSn/2U0ofkg/CLS6vBvtO\n" +
                    "5nLy26UsGDD1MP3ehT/5P8gv4MKGNutFV1gl76mU/wACZHJ4mroZhkXbo9iglYRDnLIt3cXTpnIx\n" +
                    "IYylA2bsbh9Lss1XWC4VUzSeM7snfURbzp92ngsNTwuLrKVJaZqilGOQxH4sT6r8URHURMEHxgfH\n" +
                    "/omby+3h7lnrlQ+IWUqcZNdRJo1kzOTtx46e3pZNoZKKSOmpq+q8XqaYcA+dGRbo4v0dOETQsNay\n" +
                    "HtsTRvVxa3dmmd8Z6NXP+8pwyzMzvl27e9RjyXDV2SR4+q//ABfYq45xJQNSSSXS0fPP+U5DGN/2\n" +
                    "bqB4HLSVgH6dBw6f2zXDZ35l1v8AyhrgNZt89KBahoKOKJxfyTNzN/seL6FyKfrEy7OH9XNy+hJW\n" +
                    "UBH2EiJEPK2VZIpHyhZW5VLI6HN8LNSLGFGLuDqQnUZkpOqXOWXnlYUAHh8Enk6Mmhn5DRdNIcIc\n" +
                    "hkyFlbWPrImLqKQLCv5sTB7wR0cgSDmKQXVNKyGy4FkXJi71e8wWsS0WHN8eSgK6hwW8i+hQQXSW\n" +
                    "PgemQfT0o+CtiqB6dBdzq9osjWYB0tU7chvzI4XZxUVVShI+eg+9lBFIcJaJfpbtR3AFGyiMcspx\n" +
                    "fIqMxVgPRzvS1oH5D8ps63IzvIAHExMTsOt3fg+kdKwlSGWJ1qNnqmOqt5Rm+g431ZZnL29vyVzc\n" +
                    "tWlRVdM4VVHOL5PgPzhVXXPJ44cYd5D7UfVhHJTYHhh9eegvoVfVThpE+/pdZmrJz/k0reTwTKb9\n" +
                    "Fn5qbP1T9Cko3yBsL4LrIWut/wAuvXzcpZdOvL4lPS/LJz/W5kPTH8SLF1uqzqW7nrCjfyt3+zyp\n" +
                    "R6Uq7K9FNXmV0sk4pxvy8ybGmyvngKq1ukosvzP5XtURGpCfD4/JN3eSyXDKxr8lyYLpoxu6Ijjb\n" +
                    "SpRZleqchxhwyfEDOPrKcWyoCbQ+RRqaKrbEfvQkfWRtc+Yg9LoAVnf06io35kQLPpQgcERG7unU\n" +
                    "WTx9ymj4Chw4CnFJy4V1JMR4TIjzKoiPClpm45R6l9AeDK6fCmyNPF+vtzDSO3qCPIX0cPcotvIX\n" +
                    "Cpop8E2Yzif5pZ/Ncp2crpaSs0RTywjLyvoNx9i1Z1U8wi0080wt0NIblj6Vzc1PW/FZLKz6S09b\n" +
                    "sXTqadp4QlHqyMJ/WXMRLIZUsVyrYB0QVlQANy4CRxZlw/jz82/J5lyXCWEspZXoOY3SpzdnLl4C\n" +
                    "zaUOLO6t9ntnKy/3DxaFyhgB9U8/ZG3971ULdN8B0dLPVWOKr1PiK7FEDccyOdNH0dr6JJfrOtZ4\n" +
                    "ZbhY4vBvdtmrDLTPcXngF6anYB5wqIyPVju0EsjeaW6bLU1gPY+kkarl8atlGzRuZtvIxc5R9dtz\n" +
                    "0+1Ya77D7b2qzz3C8UktJQQYKQpK+MjbUWnqAbl0uKLdowpdmqc7dtDbqi5BuYI5wM8uz8o83Yvo\n" +
                    "/YvaeO5bMBFSyCHi8kgll9OHMyPt7cEuO7CeDiLam2fCFbtCdLiYg3EFJvD0jp5tbnpbp7nWyqPB\n" +
                    "vspQW96aSiq7hUGXLLVVcrP9EZAH2LOZhVYbWjpHut0EZGMbbSc8sjNw/wAdit548gUhcNfMwLEW\n" +
                    "3wW2iiCOa03G/wBmrRbVrt9dpFvcbP8AtKeez+EC3uT2rbG330f5m9W7dOw/1seSf6WUREKaPHaq\n" +
                    "m8U1JcaSWjr2GSnmbTJG74yqMrxtxSTYuex1LPF5c1uuMLN8pgOR3+nCUl2kqi+NtlbSE76Wap3e\n" +
                    "X+obp4wSmj8ENgcyOKpucgeYc4cPoD80fQ+C3ZiMdFRQVEg9pzV02t/cBs32KxGOTVkDMC7wPH4I\n" +
                    "qKmuE3WrK3R3NOf8VWwFV0Nnpt1BVaIwgAQgowzgAHoHSqKsp6OrrjqSnqTM+gHjDDer06vsV9S7\n" +
                    "PxkWT7eZ+HSrmjtUUH6GIWLvxxRsWHM9qJLvQUlLHaacAnq5mp4TPschIuUfYJfQuhWOgqIaClim\n" +
                    "kKacI2GSR+1+0lDXW56zbex0TsTtBBPVyeo5YijL6njf0LoVNbmjDo5VM2U5jt5tFedlKm21dFb6\n" +
                    "SqsuNNaRge816i6r5wzY09j8VtNmdoLdf7aFfbZzkg6rg+NcZeabecrS4UUR0csc1PFPFLyHHI+G\n" +
                    "dvoJcXvezV88H13K/bLagtxPzw6ikBmfyDbA5ZXWqNnYNtIKeXYPaRhi5ZLZVCZ46PiXWa/ybp95\n" +
                    "sbdo/wCausg+zMMJ/voqz7aW3brZe5UETtTXSSimCShM8lzA7ag4c7cf4qq8AQ1Nh2bu9PfaOoop\n" +
                    "Z69powONyI28XhB+A57YySnw6uwaGc3Ljn2r1mdn6eHcq/4TY3fc01VK3e0eG+1CVF2qAZxKKGnL\n" +
                    "vmkz9nBcqhgUDHKU1cQVEr8G0g4izfJy6ie1xjcAqqcmiIR0EAiz5btb8EGNZXVX6Goxjp8WiZ2+\n" +
                    "3KbNQVdQ5b0aiRj6wnJyfVd8fdT7kYW9XW0tK7NU1MEOf5yRg/FV1ZeKMWjkaU3EDYtbRm4Y6H5s\n" +
                    "Y6HTaSynHlgCCEC6dDv+GGUk1gCop5YKqbMUg4Jog0O/4orx2GIX6SaLMw4boZVu0le9p2fuVwZm\n" +
                    "IqanlmFn7XEXdmXUb4727vHwx4T9pahpNYObiD9jsB6Q+5pWfl4kq62yA97ncDJwNpAB37WHo/ZF\n" +
                    "HT8CXXxW6w5r+hpFCRZUxOoZFfpBjbOpCk2PkoyTgoJGZ1ACSB5qgLgipAwmEGUrVXUGbp4Hy8yl\n" +
                    "KNmFCnly5VBpUTA+Qx3IET96Jpn5/aioFE3BCm2ERJw+SoDVyiEOE3oT8YJLChY6jq36kr59Losm\n" +
                    "Yx5m5VS9CPpJ+XBq6WK1RHGHvcVLnLZymZZ1GWYyz5C1QdI3kpWqo8SuI6zdopOR3bsTs5HlQtXH\n" +
                    "vALzlF42g62w1MraA5TN8uQu7rPSSO4ExeQZDxRFJcJ56bQcg6mbjw4oI5OJfWXM0NkfmLj0qe38\n" +
                    "ZR9PL91CkiINAHnyVIWdNjQR9zptZOxiEfSMer7ylgcG8YDl4tqb6yAnf4wmVU9P6IXThdRi6cL8\n" +
                    "V0MxAcqiI8mSkJ8AT+hDZx/B0r+YKE4t2p5BkF5GpRfgnXBoo30ak/OU020H6qYTYdCUovhInymC\n" +
                    "7O3rKPKakNc+BBkGLqWufVJ7lAKyvbtVfBkRY6yKy2OVAR8VOLu/BFZRKYpMdVIeCYLYTunrKsm9\n" +
                    "HiSJg6yF1sxIqN9Qp1SLA3Ysh1mfVwW3ppmnpwkF+U21LBxyM3WWs2XqN5bijLrRnpWfLXo6eryD\n" +
                    "iBN5SUnAkymfBk2U+VeXHxu75+VHLcOnYypRDmVjZbTUXatGmpWwLc0kztwjDzv+Fei5C2fs9Rea\n" +
                    "0ael5AbmmmfohD+96q7TYbTHQW4Ke3xaKePtN+aR/KIu8lJsZsdNPSjR2qLcUQP8bVyNwcvKd/OJ\n" +
                    "W2215o7bWjHb6cTpIgGJjeZgHUPtQmbKHaa4VsZWOrqaiUxobtRkDOfABIihfHukJXF4roLlSS0F\n" +
                    "bTeM0craDjN3HI+5Yna+8eO7PVDxUVX8W8U2vkdm3RifY/qrS0LPWzBUE2gOUww/zlFv9OvyWVjp\n" +
                    "rXbaQKelt1RQwRtpbds5/Wy+pXNttfjd0CpCping6oY4O3yhVeRvMQxjxJ1ajC1DEEsPIYfpHbtZ\n" +
                    "ZNB1TSVbV8rA8sdOz8HDpf5yBqYJ49WiWbR3O608VdvqYTLjlAVTPOReQKR7MvUmbCX5qhkBpKgn\n" +
                    "0YLPYthV0QO2A4k/eg4LTz8rZJ+1VUAKGljdx4E5Or2Ck4ipZQorJRjUV0htrfRGEYPJNMfkhEDc\n" +
                    "xkn01su91LeXWoks1C/Vt9DLipIf6aduIP6sOMeeSQNuNVa7GwNfrpbrW0nU8eqY4Hf2M75dC0+3\n" +
                    "WxUbHvdpbaDCLmWo3HLejLNlFUFTs9s8U8ey1rjq6920SvbY2InJv/aKp+Gr5Zu/oJZek2tuN42r\n" +
                    "u9NtDbTqbfaSGJoLbMUkMc2ry9YNvjEm6XwwuGWDPMqwG72eudhr6mqulogpqxiYI3uFPKNRrEW4\n" +
                    "Dkc46z8PS60MVdTVB6APQXcfKs/R7NbNX2livFDbGttXIxC1bRj4pUC7E4uzmGHfi3Q+WUjzX6yS\n" +
                    "6LgDbQ2j+fhjZqyFvXiblmb0hpL1CRgNCUE5vyYAfSlPR7yA45tyYG2CEwfD/avLbLb62jjqaBqe\n" +
                    "SCRuUgFv8Z9Cs8K04fOPhE8HNXs/VFdbJIb0jHrbcZA4PkvqVp4Hqh9o6yto7zdNojrIfjY5Gu07\n" +
                    "RmHBnHGvrMX7XoXdJII5QIJRYxJsOz9qzlo2KtVpvclyoQKOQ3zu85Fn04Tz/YrWYWUVitjdeIqn\n" +
                    "/wB6mOf9snR1JTU9MGmlgjgj82MGFvsRGGbsS0skos4TdfodOwkgEL5TIyEjkYX4iWH/AB/NRnJI\n" +
                    "LYCAz+S7M34plEJtGTzCIykZOTC+W9H2YQBa5t4f7m1s8G1bx0nUSBED+sPxn4Ru3vXSV88/5WVz\n" +
                    "0W+020D4G7ySB7SbQX0Ryt70G+cbQ+7uNP1uGofukrmV2cln6STRX075/WD+0ryfhq85dPEwuhkZ\n" +
                    "tSgPgpyNn4EoSW7MOfFQkWERICgIPSotUzCfKaTZFIm0qIn09qlRGyEIcESII8qCRTa0Kqhzgsos\n" +
                    "TblcUAbqSCTlwTqK2NZSO2jlUBZ05SiPIYSHiBN3dy0TqiLpSUmG1JYUamjSEnbipMZTegkAZSzM\n" +
                    "/AnRRYcehVIPpRsE2eGVpSxTBZeAu8FKWDbIrwm18FBzwHguIqtiNH4uXPkpHIzmXsUkraxyKFF+\n" +
                    "dYXVVNqy/uRMcmIh4dKKlpYgs0E/Lvctl+19SAgflJuhZGsKaQHPJPhQ1L4mJs58lNA8CmSya5Cc\n" +
                    "uCunoPjdPB+dQi/FPifmW1UJ5D5O7KGNn9X3KWV+XPN7l5Hzwi6Vu5L6TRcBUueBdZRR9RSZ4Kje\n" +
                    "51il0jhQieCTyNANJnAl4T57U/XkcKIuGrigAqp8zF9VRC6Up5lJ/SkPFYfavpNE7MXM/Kid4DN0\n" +
                    "oPHDpThBVsQgp2bqput3TBBulPFMJAbKIF8Coo+CljDJKoJLG/atHslJiaoj8lwEvq/+azwt71db\n" +
                    "MHory814ySt4VWrHromRn1IcXyKNibWwryuausu3it0//9k=");
            //压缩
            String str=compressZip(kiss.toString());
            System.out.println("\n压缩后的字符串为----->" + str);

           //解压
            str=decompressionZip(str);
            System.out.println("\n解压缩后的字符串为--->" + str);
            /*String str=kiss.toString();
            System.out.println("\n原始的字符串为------->" + str);
            float len0=str.length();
            System.out.println("原始的字符串长度为------->"+len0);
            long time=System.nanoTime();
            String ys = compress(str);
            long time1=System.nanoTime();
            System.out.println(time1-time);
            String strs=HexUtil.byteArrToHex(ys.getBytes("ISO-8859-1"));
            System.out.println("\n压缩后的字符串为----->" + strs);

            float len1=ys.length();

            System.out.println("\n压缩后的字符串长度为----->" + len1);

            String isoString = new String(HexUtil.hexToByteArr(strs),"ISO-8859-1");
            System.out.println("\n=============================isoString--->"+isoString);
            long time2=System.nanoTime();
            String jy = unCompress(isoString);
            long time3=System.nanoTime();
            System.out.println(time3-time2);
            System.out.println("\n解压缩后的字符串为--->" + jy);
            System.out.println("解压缩后的字符串长度为--->"+jy.length());

            System.out.println("\n压缩比例为"+len1/len0);

            //判断
            if(str.equals(jy)){
                System.out.println("先压缩再解压以后字符串和原来的是一模一样的");
            }*/
        }
}
