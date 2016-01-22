package com.ststudy.client.android.graduationproject.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.ststudy.client.android.graduationproject.R;
import com.ststudy.client.android.graduationproject.adapter.VoiceFragmentAdapter;
import com.ststudy.client.android.ui.pagerslidingtabstrip.PagerSlidingTabStrip;
import org.jetbrains.annotations.Contract;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aaron on 2016/1/18.
 * 视频主界面
 */
public class VideoFragment extends BaseFragment {

    private PagerSlidingTabStrip mSlidingTabVideo;
    private ViewPager mVp4Video;

    private VideoFragment() {
    }

    @Contract(pure = true)
    public static VideoFragment getInstance() {
        return VideoFragmentHolder.INSTANCE;
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
        findView(view);
        initData();
        bindData();
    }

    /**
     * 初始化界面
     *
     * @param pView 当前显示的View
     */
    @Override
    protected void findView(View pView) {
        mSlidingTabVideo = (PagerSlidingTabStrip) pView.findViewById(R.id.slidingTabVideo);
        mVp4Video = (ViewPager) pView.findViewById(R.id.vp4Video);
    }

    /**
     * 初始化数据
     */
    @Override
    protected void initData() {
        List<BaseFragment> list = new ArrayList<>();
        TextView _tv;
        for (int i = 0; i < mSlidingTabVideo.getTabCount(); i++) {
            _tv = (TextView) mSlidingTabVideo.getTab(i);
            list.add(VoiceItemFragment.newInstance(_tv.getText().toString()));
        }
        VoiceFragmentAdapter voiceFragmentAdapter = new VoiceFragmentAdapter(getChildFragmentManager(), list);
        mVp4Video.setAdapter(voiceFragmentAdapter);
    }

    /**
     * 绑定数据
     */
    @Override
    protected void bindData() {
        mSlidingTabVideo.setViewPager(mVp4Video);
    }

    /**
     * 设置组件监听
     */
    @Override
    protected void setListener() {

    }

}
