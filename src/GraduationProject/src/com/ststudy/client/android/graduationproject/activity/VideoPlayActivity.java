package com.ststudy.client.android.graduationproject.activity;

import android.content.Context;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.*;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.ststudy.client.android.graduationproject.App;
import com.ststudy.client.android.graduationproject.R;
import com.ststudy.client.android.graduationproject.model.ChaoXingCourse;
import com.ststudy.client.android.graduationproject.model.LocalVideoInfo;
import com.ststudy.client.android.graduationproject.model.VideoItemInfo;
import com.ststudy.client.android.utils.Utils;
import io.vov.vitamio.MediaPlayer;
import io.vov.vitamio.Vitamio;
import io.vov.vitamio.widget.VideoView;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.WeakReference;
import java.util.List;

/**
 * Created by Aaron on 2016/1/29.
 * 视频播放界面
 */
public class VideoPlayActivity extends BaseActivity implements View.OnClickListener {

    private String TAG = VideoPlayActivity.class.getSimpleName();
    //发送消息的值
    private final static int SUCCESSGETURL = 1;
    private final static int SHOWDELAY = 2;
    private final static int PROGRESS = 3;
    private final static int DELAYTIME = 6000;
    private final static int UPDATETIME = 1000;

    private int mType;
    private List<VideoItemInfo> mVideoInfoList;
    private List<ChaoXingCourse> mChaoXingCourseList;
    private List<LocalVideoInfo> mLocalVideoList;
    private int mPosition;
    private int mLength;
    private String mCurrentVideoUrl;
    private MyHandler mHandler;
    private VideoView mVideoPlayer;
    private TextView mTvVideoTitle;
    private SeekBar mSeekVideo;
    private Button mBtnPlayPre;
    private Button mBtnPlayPause;
    private Button mBtnPlayNext;
    private TextView mTvCurrentTime;
    private TextView mTvTotalTime;
    private boolean isPlaying;
    private boolean isControlShow;
    private boolean isDestory;
    private RelativeLayout mRlayControl;
    //加入手势
    private GestureDetector mControlGesture;
    //有关的管理
    private AudioManager mAudioManager;
    //最大声音
    private int mMaxVolume;
    //当前声音
    private int mVolume = -1;
    // 当前亮度
    private float mBrightness = -1f;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Vitamio.isInitialized(this);
        setContentView(R.layout.activity_videoplayer);
        initData();
        initView();
        bindData();
        setListener();
    }

    @Override
    protected void initData() {
        isControlShow = false;
        isDestory = false;
        mHandler = new MyHandler(this);
        mType = getIntent().getIntExtra("type", -1);
        mPosition = getIntent().getIntExtra("position", 0);
        mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        mMaxVolume = mAudioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        if (0 == mType) {
            mLocalVideoList = (List<LocalVideoInfo>) getIntent().getSerializableExtra("data");
            mLength = mLocalVideoList.size() - 1;
        } else if (1 == mType) {
            mVideoInfoList = (List<VideoItemInfo>) getIntent().getSerializableExtra("data");
            mLength = mVideoInfoList.size() - 1;
        } else if (2 == mType) {
            mChaoXingCourseList = (List<ChaoXingCourse>) getIntent().getSerializableExtra("data");
            mLength = mChaoXingCourseList.size() - 1;
        }

        mControlGesture = new GestureDetector(VideoPlayActivity.this, new GestureDetector.SimpleOnGestureListener() {
            //单击
            @Override
            public boolean onSingleTapConfirmed(MotionEvent e) {
                if (isControlShow) {
                    mRlayControl.setVisibility(View.GONE);
                    mHandler.removeMessages(SHOWDELAY);
                } else {
                    mRlayControl.setVisibility(View.VISIBLE);
                    mHandler.sendEmptyMessageDelayed(SHOWDELAY, DELAYTIME);
                }
                isControlShow = !isControlShow;
                return super.onSingleTapConfirmed(e);
            }


            @Override
            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
                float mOldX = e1.getX(), mOldY = e1.getY();
                int y = (int) e2.getRawY();
                Display disp = getWindowManager().getDefaultDisplay();
                int windowWidth = disp.getWidth();
                int windowHeight = disp.getHeight();
                float _percent = (mOldY - y) / windowHeight;
                if (mOldX > windowWidth * 4.0 / 5) {
                    //右侧滑动
                    onVolumeSlide(_percent);
                } else if (mOldX < windowWidth / 5.0) {
                    //左侧滑动
                    onBrightnessSlide(_percent);
                }

                return super.onScroll(e1, e2, distanceX, distanceY);
            }
        });
        getVideoUrl(mPosition);
    }

    //核对控制按钮状态
    private void checkControlBtnState() {
        if (0 == mPosition) {
            mBtnPlayPre.setVisibility(View.GONE);
        } else {
            mBtnPlayPre.setVisibility(View.VISIBLE);
        }
        if (mLength == mPosition) {
            mBtnPlayNext.setVisibility(View.GONE);
        } else {
            mBtnPlayNext.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 滑动改变声音
     *
     * @param pPercent 百分比
     */
    private void onVolumeSlide(float pPercent) {
        if (mVolume == -1) {
            mVolume = mAudioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
            if (mVolume < 0)
                mVolume = 0;
        }
        int index = (int) (pPercent * mMaxVolume) + mVolume;
        if (index > mMaxVolume) {
            index = mMaxVolume;
        } else if (index < 0) {
            index = 0;
        }
        // 变更声音
        mAudioManager.setStreamVolume(AudioManager.STREAM_MUSIC, index, 0);
    }


    /**
     * 滑动改变亮度
     *
     * @param pPercent 百分比
     */
    private void onBrightnessSlide(float pPercent) {
        if (mBrightness < 0) {
            mBrightness = getWindow().getAttributes().screenBrightness;
            if (mBrightness <= 0.00f) {
                mBrightness = 0.50f;
            }
            if (mBrightness < 0.01f) {
                mBrightness = 0.01f;
            }
        }
        WindowManager.LayoutParams lpa = getWindow().getAttributes();
        lpa.screenBrightness = mBrightness + pPercent;
        if (lpa.screenBrightness > 1.0f) {
            lpa.screenBrightness = 1.0f;
        } else if (lpa.screenBrightness < 0.01f) {
            lpa.screenBrightness = 0.01f;
        }
        getWindow().setAttributes(lpa);
    }


    @Override
    protected void initView() {
        mVideoPlayer = (VideoView) findViewById(R.id.vvPlayer);
        mBtnPlayPre = (Button) findViewById(R.id.btnPlayPre);
        mBtnPlayPause = (Button) findViewById(R.id.btnPlayPause);
        mBtnPlayNext = (Button) findViewById(R.id.btnPlayNext);
        mRlayControl = (RelativeLayout) findViewById(R.id.rlayMediaControler);
        mTvVideoTitle = (TextView) findViewById(R.id.tvVideoTitle);
        mSeekVideo = (SeekBar) findViewById(R.id.seekVideo);
        mTvCurrentTime = (TextView) findViewById(R.id.tvCurrentTime);
        mTvTotalTime = (TextView) findViewById(R.id.tvTotalTime);
        mRlayControl.setVisibility(View.GONE);
    }

    @Override
    protected void bindData() {
    }

    @Override
    protected void setListener() {
        mBtnPlayPre.setOnClickListener(this);
        mBtnPlayPause.setOnClickListener(this);
        mBtnPlayNext.setOnClickListener(this);
        mSeekVideo.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                mHandler.removeMessages(SHOWDELAY);
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mHandler.sendEmptyMessageDelayed(SHOWDELAY, DELAYTIME);
                mVideoPlayer.seekTo(mSeekVideo.getProgress());
            }
        });

        mVideoPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                if (mPosition < mLength) {
                    getVideoUrl(++mPosition);
                } else {
                    finish();
                }
            }
        });

    }


    /**
     * 播放吧  (●'◡'●)
     */
    private void player() {
        mVideoPlayer.setVideoPath(mCurrentVideoUrl);
        mVideoPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setPlaybackSpeed(1.0f);
                long _time = mVideoPlayer.getDuration();
                mTvTotalTime.setText(Utils.formatVideoTime(_time));
                mTvCurrentTime.setText(Utils.formatVideoTime(mVideoPlayer.getCurrentPosition()));
                mSeekVideo.setMax((int) _time);
                mHandler.sendEmptyMessage(PROGRESS);
            }
        });
        //修改视频头的显示
        if (0 == mType) {
            mTvVideoTitle.setText(mLocalVideoList.get(mPosition).getTitle());
        } else if (1 == mType) {
            mTvVideoTitle.setText(mVideoInfoList.get(mPosition).getVideo_name());
        } else if (2 == mType) {
            mTvVideoTitle.setText(mChaoXingCourseList.get(mPosition).getTitle());
        }
        //修改控制按钮的状态
        checkControlBtnState();
    }

    /**
     * 获取视频播放地址
     *
     * @param pPosition 当前视频地址
     */
    private void getVideoUrl(int pPosition) {
        if (2 == mType) {
            JsonObjectRequest _request = new JsonObjectRequest(Request.Method.GET, mChaoXingCourseList.get(pPosition).getGetVideoUrl(), null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        if (response.has("videoUrls")) {
                            mCurrentVideoUrl = response.getJSONArray("videoUrls").get(0).toString();
                            mHandler.sendEmptyMessage(SUCCESSGETURL);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            });
            App.getRequestQueue().add(_request).setTag(TAG);
        } else {
            if (0 == mType) {
                mCurrentVideoUrl = mLocalVideoList.get(pPosition).getFilePath();
            } else if (1 == mType) {
                mCurrentVideoUrl = mVideoInfoList.get(pPosition).getVideo_url();
            }
            mHandler.sendEmptyMessage(SUCCESSGETURL);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (isPlaying) {
            mVideoPlayer.pause();
        }
        App.getRequestQueue().cancelAll(TAG);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (null != mHandler) {
            mHandler.removeCallbacksAndMessages(null);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnPlayPre:
                if (0 < mPosition) {
                    getVideoUrl(--mPosition);
                }
                break;
            case R.id.btnPlayPause:
                if (isPlaying) {
                    mVideoPlayer.pause();
                    mBtnPlayPause.setText("播放");
                } else {
                    mVideoPlayer.start();
                    mBtnPlayPause.setText("暂停");
                }
                isPlaying = !isPlaying;
                break;
            case R.id.btnPlayNext:
                if (mPosition < mLength) {
                    getVideoUrl(++mPosition);
                }
                break;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (mControlGesture.onTouchEvent(event)) {
            return true;
        }
        // 处理手势结束
        switch (event.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_UP:
                mVolume = -1;
                mBrightness = -1f;
                break;
        }

        return super.onTouchEvent(event);
    }

    /**
     * 自己的Handler类，防止内存泄漏
     */
    private static class MyHandler extends Handler {
        private final WeakReference<VideoPlayActivity> mActivity;

        public MyHandler(VideoPlayActivity pActivity) {
            mActivity = new WeakReference<>(pActivity);
        }

        @Override
        public void handleMessage(Message msg) {
            VideoPlayActivity activity = mActivity.get();
            if (null != activity) {
                switch (msg.what) {
                    case SUCCESSGETURL:
                        activity.player();
                        break;
                    case SHOWDELAY:
                        activity.isControlShow = false;
                        activity.mRlayControl.setVisibility(View.GONE);
                        break;
                    case PROGRESS:
                        activity.mTvCurrentTime.setText(Utils.formatVideoTime(activity.mVideoPlayer.getCurrentPosition()));
                        activity.mSeekVideo.setProgress((int) activity.mVideoPlayer.getCurrentPosition());
                        if (!activity.isDestory) {
                            activity.mHandler.removeMessages(PROGRESS);
                            activity.mHandler.sendEmptyMessageDelayed(PROGRESS, UPDATETIME);
                        }
                        break;
                }
            }
        }
    }
}