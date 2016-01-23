package com.ststudy.client.android.graduationproject.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.ststudy.client.android.graduationproject.R;

/**
 * Created by Aaron on 2016/1/23.
 * 每一个视频的通用接口
 */
public class VideoItemFragment extends BaseFragment {

    private int mCurrentIndex;
    private TextView mTvTarget;

    /**
     * 提供私有的构造
     */
    private VideoItemFragment() {
    }

    public static VideoItemFragment newInstance(int pIndex) {
        VideoItemFragment _fragment = new VideoItemFragment();
        Bundle _bundle = new Bundle();
        _bundle.putInt("NORMAL_VIDEO", pIndex);
        _fragment.setArguments(_bundle);
        return _fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCurrentIndex = getArguments().getInt("NORMAL_VIDEO", 0);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return (inflater.inflate(R.layout.fragment_video_item, container, false));
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        findView(view);
        initData();
    }

    @Override
    protected void findView(View pView) {
        mTvTarget = (TextView) pView.findViewById(R.id.swipe_target);
    }

    @Override
    protected void initData() {
        mTvTarget.setText("当前是第" + mCurrentIndex + "个视频管理界面");
    }

    @Override
    protected void bindData() {

    }

    @Override
    protected void setListener() {

    }
}
