package com.ststudy.client.android.graduationproject.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.ststudy.client.android.graduationproject.App;
import com.ststudy.client.android.graduationproject.R;
import com.ststudy.client.android.graduationproject.model.ChaoXingCourse;
import com.ststudy.client.android.graduationproject.model.VideoItemInfo;
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
    private final static int SUCCESSGETURL = 1;
    private int mType;
    private List<VideoItemInfo> mVideoInfoList;
    private List<ChaoXingCourse> mChaoXingCourseList;
    private int mPosition;
    private int mLength;
    private String mCurrentVideoUrl;
    private MyHandler mHandler;
    private VideoView mVideoPlayer;

    private Button mBtnPlayPre;
    private Button mBtnPlayPause;
    private Button mBtnPlayNext;
    private boolean isPlaying;

    private RelativeLayout mRlayControl;

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
        mHandler = new MyHandler(this);
        mType = getIntent().getIntExtra("type", 0);
        mPosition = getIntent().getIntExtra("position", 0);
        if (1 == mType) {
            mVideoInfoList = (List<VideoItemInfo>) getIntent().getSerializableExtra("data");
            mLength = mVideoInfoList.size() - 1;
        } else if (2 == mType) {
            mChaoXingCourseList = (List<ChaoXingCourse>) getIntent().getSerializableExtra("data");
            mLength = mChaoXingCourseList.size() - 1;
        }
//        Toast.makeText(this, "我的长度为： " + mLength, Toast.LENGTH_SHORT).show();
        getVideoUrl(mPosition);
    }

    @Override
    protected void initView() {
        mVideoPlayer = (VideoView) findViewById(R.id.vvPlayer);
        mBtnPlayPre = (Button) findViewById(R.id.btnPlayPre);
        mBtnPlayPause = (Button) findViewById(R.id.btnPlayPause);
        mBtnPlayNext = (Button) findViewById(R.id.btnPlayNext);
        mRlayControl = (RelativeLayout) findViewById(R.id.rlayMediaControler);
//        mRlayControl.setVisibility(View.GONE);
    }

    @Override
    protected void bindData() {
    }

    @Override
    protected void setListener() {
        mBtnPlayPre.setOnClickListener(this);
        mBtnPlayPause.setOnClickListener(this);
        mBtnPlayNext.setOnClickListener(this);
    }


    /**
     * 播放吧  (●'◡'●)
     */
    private void player() {
        mVideoPlayer.setVideoPath(mCurrentVideoUrl);
        mVideoPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mVideoPlayer.start();
                isPlaying = true;
            }
        });
    }

    /**
     * 获取视频播放地址
     *
     * @param pPosition 当前视频地址
     */
    private void getVideoUrl(int pPosition) {
        if (1 == mType) {
            mCurrentVideoUrl = mVideoInfoList.get(pPosition).getVideo_url();
            mHandler.sendEmptyMessage(SUCCESSGETURL);
        } else if (2 == mType) {
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
                }
            }
        }
    }
}