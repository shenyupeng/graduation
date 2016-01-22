package com.ststudy.client.android.graduationproject.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.ststudy.client.android.graduationproject.R;
import org.jetbrains.annotations.Contract;

/**
 * Created by Aaron on 2016/1/18.
 * 用户主界面
 */
public class UserFragment extends BaseFragment {

    private UserFragment() {
    }

    @Contract(pure = true)
    public static UserFragment getInstance() {
        return UserFragmentHolder.INSTANCE;
    }

    private static class UserFragmentHolder {
        public static final UserFragment INSTANCE = new UserFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_user,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    }

    /**
     * 初始化界面
     *
     * @param pView 当前显示的View
     */
    @Override
    protected void findView(View pView) {

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
