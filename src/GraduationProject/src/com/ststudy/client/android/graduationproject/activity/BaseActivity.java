package com.ststudy.client.android.graduationproject.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Window;

import java.util.LinkedList;

/**
 * Created by Aaron on 2016/1/17.
 * 用于Activity的统一管理，避免内存泄漏
 */

public abstract class BaseActivity extends FragmentActivity {

    //Activity管理链表
    private static LinkedList<Activity> mActivityList = new LinkedList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //请求没有头
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        mActivityList.add(this);

    }

    /**
     * 初始化数据
     */
    protected abstract void initData();

    /**
     * 用于初始化整个视图
     */
    protected abstract void initView();

    /**
     * 用于绑定数据
     */
    protected abstract void bindData();

    /**
     * 对相关组件设计监听
     */
    protected abstract void setListener();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mActivityList.remove(this);
    }

    /**
     * 关闭所有打开的Activity
     */
    private static void finishAll() {
        for (Activity _activity : mActivityList) {
            _activity.finish();
        }
    }

    /**
     * 推出应用程序
     */
    public static void exitApp() {
        finishAll();
        System.exit(0);
    }
}
