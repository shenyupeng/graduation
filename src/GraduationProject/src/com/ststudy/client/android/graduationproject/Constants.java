package com.ststudy.client.android.graduationproject;

import android.os.Environment;

/**
 * Created by Aaron on 2016/1/17.
 * 应用中所需要常量
 */
public class Constants {

    public static final String UPDATE_VERSION_API = "https://raw.githubusercontent.com/shenyupeng/graduation/master/Deployment/version/version";
    public static final String APP_DOWNLOAD_PATH = Environment.getExternalStorageDirectory().toString() + "/Download/";


    //Log信息管理
    interface MyLog {
        boolean DEBUG = true;
        String TAG = "Aaron";
    }


}
