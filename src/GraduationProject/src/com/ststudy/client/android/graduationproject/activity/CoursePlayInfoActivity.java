package com.ststudy.client.android.graduationproject.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.ststudy.client.android.graduationproject.App;
import com.ststudy.client.android.graduationproject.Constants;
import com.ststudy.client.android.graduationproject.R;
import com.ststudy.client.android.graduationproject.adapter.VideoListAdapter;
import com.ststudy.client.android.graduationproject.model.*;
import com.ststudy.client.android.graduationproject.utils.DataParseUtils;
import com.ststudy.client.android.ui.swipeback.SwipeBackActivity;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aaron on 2016/1/29.
 * 用于存放视频的播放信息
 */
public class CoursePlayInfoActivity extends SwipeBackActivity {

    private String TAG = CoursePlayInfoActivity.class.getSimpleName();
    private CareerCourseDetail mCareerCourseDetail;
    private NormalCourse mNormalCourse;
    private List<VideoInfo> mVideoList;
    private List<VideoItemInfo> mVideoInfoList;
    private List<ChaoXingCourse> mChaoXingList;
    private VideoListAdapter mVideoListAdapter;
    private ListView mLvCoursePlayInfo;
    private int mType;
    private String mRequestStr;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_playinfo);
        initData();
        initView();
        bindData();
        setListener();
    }

    @Override
    protected void initData() {
        mVideoList = new ArrayList<>();
        mVideoListAdapter = new VideoListAdapter(CoursePlayInfoActivity.this, mVideoList);
        mType = getIntent().getIntExtra("type", -1);

        if (1 == mType) {
            mCareerCourseDetail = (CareerCourseDetail) getIntent().getSerializableExtra("data");
            mRequestStr = Constants.MAIZIAPI.COURSEPLAYINFOAPI + mCareerCourseDetail.getCourse_id();
        } else {
            mNormalCourse = (NormalCourse) getIntent().getSerializableExtra("data");
            mRequestStr = Constants.CHAOXINGAPI.CAREERLIST + mNormalCourse.getSerieId() + "_1.shtml";
        }

        JsonObjectRequest _request = new JsonObjectRequest(Request.Method.GET, mRequestStr, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                if (1 == mType) {
                    mVideoInfoList = DataParseUtils.parseVideoItemInfo(response);
                    for (VideoItemInfo item : mVideoInfoList) {
                        mVideoList.add(new VideoInfo(item.getVideo_name()));
                    }
                } else if (2 == mType) {
                    mChaoXingList = DataParseUtils.parseChaoXingList(response);
                    for (ChaoXingCourse item : mChaoXingList) {
                        mVideoList.add(new VideoInfo(item.getTitle()));
                    }
                }
                mVideoListAdapter.notifyDataSetChanged();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        App.getRequestQueue().add(_request).setTag(TAG);
    }

    @Override
    protected void initView() {
        mLvCoursePlayInfo = (ListView) findViewById(R.id.lvCoursePlayInfo);
    }

    @Override
    protected void bindData() {
        mLvCoursePlayInfo.setAdapter(mVideoListAdapter);
    }

    @Override
    protected void setListener() {
        mLvCoursePlayInfo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent _intent = new Intent(CoursePlayInfoActivity.this, VideoPlayActivity.class);
                Bundle _bundle = new Bundle();
                if (1 == mType) {
                    _intent.putExtra("type", 1);
                    _bundle.putSerializable("data", (Serializable) mVideoInfoList);
                } else if (2 == mType) {
                    _intent.putExtra("type", 2);
                    _bundle.putSerializable("data", (Serializable) mChaoXingList);
                }
                _intent.putExtra("position", position);
                _intent.putExtras(_bundle);
                startActivity(_intent);
            }
        });

    }

    @Override
    protected void onPause() {
        super.onPause();
        App.getRequestQueue().cancelAll(TAG);
    }
}