package com.ststudy.client.android.graduationproject;

import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.os.StrictMode;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.ststudy.client.android.utils.multithreaddownload.DownloadConfiguration;
import com.ststudy.client.android.utils.multithreaddownload.DownloadManager;

/**
 * Created by Aaron on 2016/1/17.
 * 用于初始化整个项目的配置
 */
public class App extends Application {

    //获取应用的Context
    public static Context sContext;
    private static RequestQueue sRequestQueue;

    @Override
    public void onCreate() {
        super.onCreate();
        initDownloader();
        setStrictMode();
        sContext = getApplicationContext();
//        CrashHandlerUtils.getInstance(sContext);
    }

    //设置严格模式
    private void setStrictMode() {
        if (BuildConfig.DEBUG && Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
//            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
//            StrictMode.setThreadPolicy(policy);
            StrictMode.enableDefaults();
        }
    }


    //初始化下载
    public void initDownloader() {
        DownloadConfiguration configuration = new DownloadConfiguration();
        configuration.setMaxThreadNum(10);
        configuration.setThreadNum(3);
        DownloadManager.getInstance().init(getApplicationContext(), configuration);
    }

    //获取Volley请求队列
    public static RequestQueue getRequestQueue() {
        if (sRequestQueue == null) {
            sRequestQueue = Volley.newRequestQueue(sContext);
        }
        return sRequestQueue;
    }
}
