package com.example.liuyjycommon.util;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.util.EntityUtils;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.net.*;


/**
 * @Author liuyjy
 * @Date 2019/8/15
 * @Description: TODO http 请求方法
 */
@Slf4j
public class HttpUtil {
    /**
     * 请求超时时间
     */
    private static int out_time = 4 * 1000;


    public static void main(String[] args) {
        String url="http://192.168.1.30:18081/AladdinPIM/redisSet/setRedisNoticeManage";
        String s=sendPost(url,new JSONObject());
        System.out.println("===========dddd========"+s);
    }

    public static String doPost(String httpUrl, String param) {

        HttpURLConnection connection = null;
        InputStream is = null;
        OutputStream os = null;
        BufferedReader br = null;
        String result = null;
        try {
            URL url = new URL(httpUrl);
            // 通过远程url连接对象打开连接
            connection = (HttpURLConnection) url.openConnection();
            // 设置连接请求方式
            connection.setRequestMethod("POST");
            // 设置连接主机服务器超时时间：15000毫秒
            connection.setConnectTimeout(out_time);
            // 设置读取主机服务器返回数据超时时间：60000毫秒
            connection.setReadTimeout(out_time);

            // 默认值为：false，当向远程服务器传送数据/写数据时，需要设置为true
            connection.setDoOutput(true);
            // 默认值为：true，当前向远程服务读取数据时，设置为true，该参数可有可无
            connection.setDoInput(true);
            // 设置传入参数的格式:请求参数应该是 name1=value1&name2=value2 的形式。
            connection.setRequestProperty("Content-Length", param.length()+"");
            connection.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
            // 设置鉴权信息：Authorization: Bearer da3efcbf-0845-4fe3-8aba-ee040be542c0
           // connection.setRequestProperty("Authorization", "Bearer da3efcbf-0845-4fe3-8aba-ee040be542c0");
            // 通过连接对象获取一个输出流
            os = connection.getOutputStream();
            // 通过输出流对象将参数写出去/传输出去,它是通过字节数组写出的
            os.write(param.getBytes());
            // 通过连接对象获取一个输入流，向远程读取
            if (connection.getResponseCode() == 200) {

                is = connection.getInputStream();
                // 对输入流对象进行包装:charset根据工作项目组的要求来设置
                br = new BufferedReader(new InputStreamReader(is, "UTF-8"));

                StringBuffer sbf = new StringBuffer();
                String temp = null;
                // 循环遍历一行一行读取数据
                while ((temp = br.readLine()) != null) {
                    sbf.append(temp);
                    sbf.append("\r\n");
                }
                result = sbf.toString();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭资源
            if (null != br) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != os) {
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != is) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            // 断开与远程地址url的连接
            connection.disconnect();
        }
        return result;
    }
    /**
     * POST请求
     *
     * @param urls
     * @param obj
     * @return
     */
    public static String sendPost(String urls, JSONObject obj) {
        String result=null;
        try {
            //主机证书明不匹配问题
            //SSLSocketFactory.getSocketFactory().setHostnameVerifier(new AllowAllHostnameVerifier());

            //获取请求地址
            HttpPost post = new HttpPost(urls);
            //获取当前客户端对象
            DefaultHttpClient httpClient = new DefaultHttpClient();

            // 设置超时时间
            //httpClient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, out_time);
            //httpClient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, out_time);
            //设置参数
            StringEntity s = new StringEntity(obj.toJSONString());
            s.setContentEncoding("UTF-8");
            //s.setContentType("text/html;charset=UTF-8");
            s.setContentType("application/json;charset=UTF-8");
            s.getContentLength();
            post.setEntity(s);
          //通过请求获得相应的对象　　　
            CloseableHttpResponse httpResponse = httpClient.execute(post);
            //判断是否成功
            if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                HttpEntity entity = httpResponse.getEntity();
                 result = EntityUtils.toString(entity);// 返回json格式：
            }else {
                log.info(urls + "访问结果状态值：" + httpResponse.getStatusLine().getStatusCode());
            }
        }catch (ClientProtocolException e) {
            log.error(urls, e);
        }catch (Exception e) {
            log.error("发送 POST 请求出现异常！", e);
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally {

        }
        return result;
    }

    /**
     * 发起一个get的http请求
     *
     * @param url
     * @return
     */
    public static String sendGet(String url) {
        String body = "";
        try {
            DefaultHttpClient httpClient = new DefaultHttpClient();
            // 设置超时
            HttpConnectionParams.setConnectionTimeout(httpClient.getParams(), out_time);
            HttpConnectionParams.setSoTimeout(httpClient.getParams(), out_time);
            CloseableHttpResponse httpResponse = null;
            HttpGet get = new HttpGet(url);
            httpResponse = httpClient.execute(get);
            if (httpResponse != null) {
                if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                    HttpEntity entity = httpResponse.getEntity();// 获取返回数据
                    body = EntityUtils.toString(entity);
                    if (body != null && !"".equals(body)) {
                        log.info(body);
                    } else {
                        body = "";
                    }
                } else {
                    log.info(url + "访问结果状态值：" + httpResponse.getStatusLine().getStatusCode());
                }
            }
        } catch (ClientProtocolException e) {
            log.error(url, e);
        } catch (Exception e) {
            log.error(url, e);
        }
        return body;
    }

    /**
     * 向指定 URL 发送POST方法的请求
     *
     * @param url   发送请求的 URL
     * @param param 请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return 所代表远程资源的响应结果
     */
    public static String sendPost(String url, String param) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 设置传入参数的格式:请求参数应该是 name1=value1&name2=value2 的形式。
            conn.setRequestProperty("Content-Length", param.length()+"");
            conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
            //conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            //System.out.println("发送 POST 请求出现异常！"+e);
            log.error("发送 POST 请求出现异常！", e);
            e.printStackTrace();
        } finally {//使用finally块来关闭输出流、输入流
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 获取request 的body 部分
     *
     * @param request
     * @return
     */
    public static byte[] binaryReader(HttpServletRequest request) {
        int len = request.getContentLength();//获取数据长度
        byte[] buffer = new byte[len];
        try {
            ServletInputStream iii = request.getInputStream();//获取数据流
            iii.read(buffer, 0, len);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return buffer;
    }

    /**
     * 转换数据
     *
     * @param text
     * @return
     */
    public static String encode(String text) {
        return java.net.URLEncoder.encode(text).replaceAll("\\+", "%20");
    }
}
