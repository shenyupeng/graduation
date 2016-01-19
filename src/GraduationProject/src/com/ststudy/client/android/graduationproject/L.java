package com.ststudy.client.android.graduationproject;

import android.util.Log;

/**
 * Created by Aaron on 2016/1/17.
 * 此工具用于对日志的统一管理
 */
public class L {
    private static final boolean DEBUG = Constants.MyLog.DEBUG;
    private static final String TAG = Constants.MyLog.TAG;

    public static void d(String pMsg) {
        if (DEBUG) {
            Log.d(TAG, pMsg);
        }
    }

    public static void d(String pTag, String pMsg) {
        if (DEBUG) {
            Log.d(pTag, pMsg);
        }
    }

    public static void w(String pMsg) {
        if (DEBUG) {
            Log.d(TAG, pMsg);
        }
    }

    public static void w(String pTag, String pMsg) {
        if (DEBUG) {
            Log.d(pTag, pMsg);
        }
    }

    public static void e(String pMsg) {
        if (DEBUG) {
            Log.d(TAG, pMsg);
        }
    }

    public static void e(String pTag, String pMsg) {
        if (DEBUG) {
            Log.d(pTag, pMsg);
        }
    }
}
