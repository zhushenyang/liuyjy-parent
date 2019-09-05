package com.example.liuyjycommon.util;

import com.google.gson.*;

import java.lang.reflect.Type;

/**
 * @Author liuyjy
 * @Date 2019/8/14
 * @Description: TODO 创建谷歌对象
 **/
public class GsonUtil {

    private volatile static Gson gson;
    private GsonUtil() {
    }
    public static Gson newInstance() {
        if (gson == null) {
            synchronized (Gson.class) {
                if (gson == null) {
                    gson = new GsonBuilder().registerTypeAdapter(Double.class, new JsonSerializer<Double>() {
                        @Override
                        public JsonElement serialize(Double src, Type typeOfSrc, JsonSerializationContext context) {
                            if(src == src.longValue()){
                                return new JsonPrimitive(src.longValue());
                            }
                            return new JsonPrimitive(src);
                        }
                    }).create();
                }
            }
        }
        return gson;
    }

}
