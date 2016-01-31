package com.ststudy.client.android.utils;

/**
 * Created by Aaron on 2016/1/17.
 * 通用的工具类
 */
public class Utils {


    /**
     * 格式化视频时间
     *
     * @param pDuration 长度
     * @return 时间数据
     */
    public static String formatVideoTime(long pDuration) {
        return (getType(getMin(pDuration)) + ":" + getType(getSec(pDuration)));
    }

    /**
     * 获取视频数据的分钟
     *
     * @param pDuration 当前时间
     * @return 分钟
     */
    public static int getMin(long pDuration) {
        return ((int) ((pDuration / 1000) / 60));
    }

    /**
     * 获取视频数据的秒
     *
     * @param pDuration 当前时间
     * @return 秒
     */
    public static int getSec(long pDuration) {
        return ((int) ((pDuration / 1000) % 60));
    }

    private static String getType(int pTime) {
        return (pTime < 10 ? "0" + pTime : String.valueOf(pTime));
    }
}
