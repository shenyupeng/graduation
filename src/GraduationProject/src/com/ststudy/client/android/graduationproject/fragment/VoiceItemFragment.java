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
    private CharSequence mTitle;

    /**
     * 提供私有的构造函数
     */
    private VoiceItemFragment() {
    }

    public static BaseFragment newInstance(String pTilte) {
        VoiceItemFragment _fragment = new VoiceItemFragment();
        Bundle _bundle = new Bundle();
        _bundle.putString("NORMAL_VOICE", pTilte);
        _fragment.setArguments(_bundle);
        return _fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTitle = getArguments().getString("NORMAL_VOICE");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return (inflater.inflate(R.layout.fragment_voice_item, container, false));
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        mTvTarget = (TextView) view.findViewById(R.id.swipe_target);
        mTvTarget.setText(mTitle);
    }

    @Override
    protected void findView(View pView) {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void bindData() {
    }

    @Override
    protected void setListener() {

    }
}
