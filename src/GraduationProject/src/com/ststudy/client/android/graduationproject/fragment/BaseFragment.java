package com.ststudy.client.android.graduationproject.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Aaron on 2016/1/18.
 * 提供基础的fragment
 */
public abstract class BaseFragment extends Fragment {

    @Nullable
    @Override
    public abstract View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState);

    @Override
    public abstract void onViewCreated(View view, @Nullable Bundle savedInstanceState);

    /**
     * 初始化界面
     *
     * @param pView 当前显示的View
     */
    protected abstract void initView(View pView);


    /**
     * 初始化数据
     */
    protected abstract void initData();


    /**
     * 绑定数据
     */
    protected abstract void bindData();

    /**
     * 设置组件监听
     */
    protected abstract void setListener();
}
