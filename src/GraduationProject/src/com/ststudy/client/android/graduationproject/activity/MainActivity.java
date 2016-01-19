package com.ststudy.client.android.graduationproject.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;
import com.ststudy.client.android.graduationproject.App;
import com.ststudy.client.android.graduationproject.R;
import com.ststudy.client.android.graduationproject.fragment.BaseFragment;
import com.ststudy.client.android.graduationproject.fragment.UserFragment;
import com.ststudy.client.android.graduationproject.fragment.VideoFragment;
import com.ststudy.client.android.graduationproject.fragment.VoiceFragment;
import com.ststudy.client.android.graduationproject.listener.OnItemClickListener;
import com.ststudy.client.android.graduationproject.model.CareerCourse;
import com.ststudy.client.android.graduationproject.view.BottomLayout;
import com.ststudy.client.android.utils.AppVersionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Aaron on 2016/1/17.
 * 主界面
 */
public class MainActivity extends BaseActivity {
    private List<CareerCourse> mCareerCourseList;
    private final String TAG = MainActivity.class.getSimpleName();
    private Timer mQuitTimer;
    private boolean isDouble = false;
    private BottomLayout mLayBottom;
    private BaseFragment mMainBaseFragment;
    private int mLastFragment = -1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            showFragment(0);
        }
        initData();
        initView();
        bindData();
        setListener();
    }

    @Override
    protected void initData() {
        //检查版本升级
        AppVersionUtils.checkUpdate(MainActivity.this, TAG);
        mCareerCourseList = new ArrayList<>();
        mQuitTimer = new Timer();
    }

    @Override
    protected void initView() {
        mLayBottom = (BottomLayout) findViewById(R.id.layBottom);
    }

    @Override
    protected void bindData() {

    }

    @Override
    protected void setListener() {
        mLayBottom.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                showFragment(position);
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        App.getRequestQueue().cancelAll(TAG);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();
    }

    @Override
    public boolean onKeyDown(int keyCode, @NonNull KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            if (keyCode == KeyEvent.KEYCODE_BACK) {
                if (!isDouble) {
                    isDouble = true;
                    Toast.makeText(MainActivity.this, "再按一下退出程序", Toast.LENGTH_SHORT).show();
                    mQuitTimer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            isDouble = false;
                        }
                    }, 1500);
                    return true;
                } else {
                    exitApp();
                    return true;
                }
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    /**
     * 替换并显示显示主Fragment
     *
     * @param pPostion 当前选中的位置
     */
    private void showFragment(int pPostion) {
        if (mLastFragment != pPostion) {
            FragmentManager _fm = getSupportFragmentManager();
            FragmentTransaction _ft = _fm.beginTransaction();
            switch (pPostion) {
                case 0:
                    mMainBaseFragment = VoiceFragment.getInstance();
                    break;
                case 1:
                    mMainBaseFragment = VideoFragment.getInstance();
                    break;
                case 2:
                    mMainBaseFragment = UserFragment.getInstance();
                    break;
            }
            _ft.replace(R.id.flayContainer, mMainBaseFragment).commit();
        }
        mLastFragment = pPostion;
    }
}