package com.ststudy.client.android.graduationproject.fragment;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.ststudy.client.android.graduationproject.App;
import com.ststudy.client.android.graduationproject.Constants;
import com.ststudy.client.android.graduationproject.R;
import com.ststudy.client.android.graduationproject.activity.CoursePlayInfoActivity;
import com.ststudy.client.android.graduationproject.activity.MaiZiCourseActivity;
import com.ststudy.client.android.graduationproject.activity.VideoPlayActivity;
import com.ststudy.client.android.graduationproject.adapter.VideoInfoAdapter;
import com.ststudy.client.android.graduationproject.model.CareerCourse;
import com.ststudy.client.android.graduationproject.model.LocalVideoInfo;
import com.ststudy.client.android.graduationproject.model.NormalCourse;
import com.ststudy.client.android.graduationproject.model.VideoItem;
import com.ststudy.client.android.graduationproject.utils.DataParseUtils;
import org.json.JSONObject;

import java.io.Serializable;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aaron on 2016/1/23.
 * 每一个视频的通用接口
 */
public class VideoItemFragment extends BaseFragment {

    private String TAG = VideoItemFragment.class.getSimpleName();
    private int mCurrentIndex;
    private ListView mVideoItemList;
    private List<VideoItem> mVideoList;
    private VideoInfoAdapter mInfoAdapter;
    private List<CareerCourse> mCareerCourseList;
    private List<NormalCourse> mNormalCourseList;
    private List<LocalVideoInfo> mLocalVideoList;
    private final static int SEARCHSUCCESS = 1;
    private MyHandler mHandler;

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
        mVideoList = new ArrayList<>();
        mInfoAdapter = new VideoInfoAdapter(getContext(), mVideoList);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        loadData();
        return (inflater.inflate(R.layout.fragment_video_item, container, false));
    }

    /**
     * 加载数据
     */
    private void loadData() {
        if (0 == mCurrentIndex) {
            //初始化本地数据
            mHandler = new MyHandler(VideoItemFragment.this);
            getLocalAllVideo();
        } else {
            String _RequestStr;
            if (1 == mCurrentIndex) {
                _RequestStr = Constants.MAIZIAPI.CAREERCOURSE;
            } else {
                _RequestStr = Constants.APP_BASE_VIDEO_REQUEST_URL + mCurrentIndex;
            }
            JsonObjectRequest _request = new JsonObjectRequest(Request.Method.GET, _RequestStr, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    mVideoList.clear();
                    if (1 == mCurrentIndex) {
                        mCareerCourseList = DataParseUtils.parseCareer(response);
                        for (CareerCourse item : mCareerCourseList) {
                            mVideoList.add(new VideoItem(item.getImg_url(),
                                    item.getName()));
                        }
                    } else {
                        mNormalCourseList = DataParseUtils.parseNormalCareer(response);
                        for (NormalCourse item : mNormalCourseList) {
                            mVideoList.add(new VideoItem(item.getImg_url(), item.getDesc()));
                        }
                    }
                    mInfoAdapter.notifyDataSetChanged();
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            });
            App.getRequestQueue().add(_request).setTag(TAG + mCurrentIndex);
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        findView(view);
        initData();
        bindData();
        setListener();
    }

    @Override
    public void onPause() {
        super.onPause();
        //取消有关网络请求
        App.getRequestQueue().cancelAll(TAG + mCurrentIndex);
        if (null != mHandler) {
            mHandler.removeCallbacksAndMessages(null);
        }
    }

    @Override
    protected void findView(View pView) {
        mVideoItemList = (ListView) pView.findViewById(R.id.swipe_target);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void bindData() {
        mVideoItemList.setAdapter(mInfoAdapter);
    }

    @Override
    protected void setListener() {
        mVideoItemList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent _intent = new Intent();
                Bundle _bundle = new Bundle();
                if (0 == mCurrentIndex) {
                    _intent.setClass(getContext(), VideoPlayActivity.class);
                    _intent.putExtra("type", 0);
                    _intent.putExtra("position", position);
                    _bundle.putSerializable("data", (Serializable) mLocalVideoList);
                } else {
                    if (1 == mCurrentIndex) {
                        _intent.setClass(getContext(), MaiZiCourseActivity.class);
                        _bundle.putSerializable("data", mCareerCourseList.get(position));
                    } else {
                        _intent.setClass(getContext(), CoursePlayInfoActivity.class);
                        _intent.putExtra("type", 2);
                        _bundle.putSerializable("data", mNormalCourseList.get(position));
                    }
                }
                _intent.putExtras(_bundle);
                startActivity(_intent);
            }
        });
    }

    private static class MyHandler extends Handler {
        private final WeakReference<VideoItemFragment> mFragment;

        public MyHandler(VideoItemFragment pActivity) {
            mFragment = new WeakReference<>(pActivity);
        }

        @Override
        public void handleMessage(Message msg) {
            VideoItemFragment _fragment = mFragment.get();
            if (null != _fragment) {
                switch (msg.what) {
                    case SEARCHSUCCESS:
                        _fragment.mInfoAdapter.notifyDataSetChanged();
                        _fragment.mHandler.removeMessages(SEARCHSUCCESS);
                        break;
                }
            }
        }
    }

    /**
     * 获取所有视频数据
     */
    private void getLocalAllVideo() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String[] mediaColumns = new String[]{
                        MediaStore.Video.Media.DATA,
                        MediaStore.Video.Media.TITLE,
                };
                //URI地址
                Uri _uri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                //首先检索内存卡所有的video
                ContentResolver resolver = getContext().getContentResolver();
                Cursor cursor = resolver.query(_uri, mediaColumns, null, null, null);
                mLocalVideoList = new ArrayList<>();
                mVideoList.clear();
                while (cursor.moveToNext()) {
                    LocalVideoInfo _item = new LocalVideoInfo();
                    _item.setFilePath(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DATA)));
                    _item.setTitle(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.TITLE)));
                    mLocalVideoList.add(_item);
                }
                cursor.close();
                for (LocalVideoInfo item : mLocalVideoList) {
                    mVideoList.add(new VideoItem(item.getFilePath(), item.getTitle()));
                }
                mHandler.sendEmptyMessage(SEARCHSUCCESS);
            }
        }).start();
    }
}
