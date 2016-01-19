package com.ststudy.client.android.graduationproject.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.ststudy.client.android.graduationproject.R;
import com.ststudy.client.android.graduationproject.listener.OnItemClickListener;

/**
 * Created by Aaron on 2016/1/18.
 * 底部导航栏
 */
public class BottomLayout extends LinearLayout implements View.OnClickListener {

    private OnItemClickListener mItemClickListener;
    private int mCurrentPosition = 0;

    private RelativeLayout mRlayVoice;
    private RelativeLayout mRlayVideo;
    private RelativeLayout mRlayUser;

    private Button mBtnVoice;
    private Button mBtnVideo;
    private Button mBtnUser;

    public BottomLayout(Context context) {
        this(context, null);
    }

    public BottomLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BottomLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context pContext) {
        LayoutInflater.from(pContext).inflate(R.layout.bottom_layout, this, true);
        initView();
        setListener();
        changeButtonStatus(mCurrentPosition);
    }


    private void initView() {
        mRlayVoice = (RelativeLayout) findViewById(R.id.rlayVoice);
        mRlayVideo = (RelativeLayout) findViewById(R.id.rlayVideo);
        mRlayUser = (RelativeLayout) findViewById(R.id.rlayUser);

        mBtnVoice = (Button) findViewById(R.id.btnVoice);
        mBtnVideo = (Button) findViewById(R.id.btnVideo);
        mBtnUser = (Button) findViewById(R.id.btnUser);
    }

    private void setListener() {
        mRlayVoice.setOnClickListener(this);
        mRlayVideo.setOnClickListener(this);
        mRlayUser.setOnClickListener(this);
    }


    public void setOnItemClickListener(OnItemClickListener pListener) {
        this.mItemClickListener = pListener;
    }

    @Override
    public void onClick(View v) {
        mCurrentPosition = 0;
        switch (v.getId()) {
            case R.id.rlayVoice:
                mCurrentPosition = 0;
                break;
            case R.id.rlayVideo:
                mCurrentPosition = 1;
                break;
            case R.id.rlayUser:
                mCurrentPosition = 2;
                break;
        }
        if (null == mItemClickListener) {
            return;
        }
        mItemClickListener.onItemClick(v, mCurrentPosition);
        changeButtonStatus(mCurrentPosition);
    }

    private void changeButtonStatus(int mCurrentPosition) {
        mBtnVoice.setSelected(0 == mCurrentPosition);
        mBtnVideo.setSelected(1 == mCurrentPosition);
        mBtnUser.setSelected(2 == mCurrentPosition);
    }
}
