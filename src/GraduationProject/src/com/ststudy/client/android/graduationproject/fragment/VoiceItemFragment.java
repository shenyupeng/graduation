package com.ststudy.client.android.graduationproject.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.ststudy.client.android.graduationproject.R;

/**
 * Created by Aaron on 2016/1/21.
 * 提供音频的统一接口
 */
public class VoiceItemFragment extends BaseFragment {

    private TextView mTvTarget;
    private int mCurrentIndex;

    /**
     * 提供私有的构造函数
     */
    private VoiceItemFragment() {
    }

    public static BaseFragment newInstance(int pIndex) {
        VoiceItemFragment _fragment = new VoiceItemFragment();
        Bundle _bundle = new Bundle();
        _bundle.putInt("NORMAL_VOICE", pIndex);
        _fragment.setArguments(_bundle);
        return _fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCurrentIndex = getArguments().getInt("NORMAL_VOICE", 0);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return (inflater.inflate(R.layout.fragment_voice_item, container, false));
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
        mTvTarget.setText("当前是第" + mCurrentIndex + "个音频管理界面");
    }

    @Override
    protected void bindData() {
    }

    @Override
    protected void setListener() {

    }
}
