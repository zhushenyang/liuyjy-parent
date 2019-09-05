package com.example.liuyjycommon.util;

import java.math.BigDecimal;

/**
 * @Author liuyjy
 * @Date 2019/8/15
 * @Description: TODO 格式化浮点类型数据
 **/
public class BigDecimalUtil {

    /**
     *  格式化 BigDecimal
     * @param num  值
     * @param places 小数位
     * @return
     */
    public static BigDecimal getBigDecimal(Double num,int places) {
        return new BigDecimal(num).setScale(places,BigDecimal.ROUND_HALF_UP);
    }

    /**
     *  格式化 BigDecimal 保留2位小数
     * @param num 值
     * @return
     */
    public static BigDecimal getBigDecimal(Double num) {
        return getBigDecimal( num, 2);
    }
}
