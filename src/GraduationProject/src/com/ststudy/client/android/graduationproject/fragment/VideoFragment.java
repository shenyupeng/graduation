package com.ststudy.client.android.graduationproject.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.ststudy.client.android.graduationproject.R;

/**
 * Created by Aaron on 2016/1/18.
 * 视频主界面
 */
public class VideoFragment extends BaseFragment implements View.OnClickListener {

    private VideoFragment() {
    }

    public static VideoFragment getInstance() {
        return VideoFragmentHolder.INSTANCE;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnStart:

                break;
            case R.id.btnPause:
                break;
            case R.id.btnCancle:
                break;
        }
    }

    private static class VideoFragmentHolder {
        public static final VideoFragment INSTANCE = new VideoFragment();
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_video, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        view.findViewById(R.id.btnStart).setOnClickListener(this);
        view.findViewById(R.id.btnPause).setOnClickListener(this);
        view.findViewById(R.id.btnCancle).setOnClickListener(this);
    }

    /**
     * 初始化界面
     *
     * @param pView 当前显示的View
     */
    @Override
    protected void initView(View pView) {

    }

    /**
     * 初始化数据
     */
    @Override
    protected void initData() {

    }

    /**
     * 绑定数据
     */
    @Override
    protected void bindData() {

    }

    /**
     * 设置组件监听
     */
    @Override
    protected void setListener() {

    }

}
