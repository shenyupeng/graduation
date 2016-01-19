package com.ststudy.client.android.graduationproject.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import com.ststudy.client.android.graduationproject.R;
import com.ststudy.client.android.graduationproject.adapter.SplashAdapter;
import com.ststudy.client.android.graduationproject.listener.OnViewPagerSelectedLastListener;
import com.ststudy.client.android.ui.viewpagerindicator.CirclePageIndicator;
import com.ststudy.client.android.utils.AppVersionUtils;

import java.util.ArrayList;

/**
 * Created by Aaron on 2016/1/17.
 * 欢迎界面
 */
public class SplashActivity extends BaseActivity {

    private ViewPager mGuideViewPager;
    private ArrayList<Integer> mGuideImgs = new ArrayList<>();
    private SplashAdapter mGuideAdapter;
    private Button mBtnEnterMain;
    private CirclePageIndicator mCpiSplash;
    private Handler mHandler;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        //初始化数据
        initData();
        //初始化组件
        initView();
        //组件与数据进行绑定
        bindData();
        //设置监听
        setListener();
    }

    /**
     * 初始化数据
     */
    @Override
    protected void initData() {

        mHandler = new Handler();
        //获取欢迎界面引导)
        if (!AppVersionUtils.versionCheck(SplashActivity.this)) {
            //初始化引导页数据
            for (int i = 0; i < 6; i++) {
                mGuideImgs.add(R.drawable.ic_launcher);
            }
        } else {
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    enterMain();
                }
            }, 3000);
            mGuideImgs.add(R.drawable.ic_launcher);
        }
        mGuideAdapter = new SplashAdapter(SplashActivity.this, mGuideImgs);
    }

    /**
     * 用于初始化整个视图
     */
    @Override
    protected void initView() {
        mGuideViewPager = (ViewPager) findViewById(R.id.vp4Splash);
        mBtnEnterMain = (Button) findViewById(R.id.btnEnterMain);
        mCpiSplash = (CirclePageIndicator) findViewById(R.id.cpiSplash);
    }

    /**
     * 用于绑定数据
     */
    @Override
    protected void bindData() {
        mGuideViewPager.setAdapter(mGuideAdapter);
        mCpiSplash.setViewPager(mGuideViewPager);
    }

    /**
     * 对相关组件设计监听
     */
    @Override
    protected void setListener() {
        mBtnEnterMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppVersionUtils.setVersionCode(SplashActivity.this);
                enterMain();
            }
        });
        mGuideViewPager.addOnPageChangeListener(new OnViewPagerSelectedLastListener() {
            @Override
            public void onPageSelected(int i) {
                if ((i + 1) == mGuideImgs.size()) {
                    mBtnEnterMain.setVisibility(View.VISIBLE);
                    mCpiSplash.setVisibility(View.GONE);
                } else {
                    mBtnEnterMain.setVisibility(View.GONE);
                    mCpiSplash.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, @NonNull KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            if (keyCode == KeyEvent.KEYCODE_BACK) {
                mHandler.removeCallbacksAndMessages(null);
                exitApp();
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    /**
     * 进入主界面
     */
    private void enterMain() {
        Intent _intentMain = new Intent(SplashActivity.this, MainActivity.class);
        startActivity(_intentMain);
        finish();
    }
}