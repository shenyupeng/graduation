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
import com.ststudy.client.android.graduationproject.adapter.VideoInfoAdapter;
import com.ststudy.client.android.graduationproject.model.CareerCourse;
import com.ststudy.client.android.graduationproject.model.CareerCourseDetail;
import com.ststudy.client.android.graduationproject.model.VideoItem;
import com.ststudy.client.android.graduationproject.utils.DataParseUtils;
import com.ststudy.client.android.ui.swipeback.SwipeBackActivity;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aaron on 2016/1/28.
 * 麦子的系列课程
 */
public class MaiZiCourseActivity extends SwipeBackActivity {

    private String TAG = MaiZiCourseActivity.class.getSimpleName();
    private CareerCourse mCareerCourse;
    private List<CareerCourseDetail> mCareerCourseDetailList;
    private List<VideoItem> mVideoItemList = new ArrayList<>();
    private VideoInfoAdapter mVideoInfoAdapter;
    private ListView mLvCourseDetail;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maizi_course);
        initData();
        initView();
        bindData();
        setListener();
    }

    @Override
    protected void initData() {
        mVideoInfoAdapter = new VideoInfoAdapter(MaiZiCourseActivity.this, mVideoItemList);
        mCareerCourse = (CareerCourse) getIntent().getSerializableExtra("data");
        JsonObjectRequest _request = new JsonObjectRequest(Request.Method.GET, Constants.MAIZIAPI.CAREERLISTAPI + mCareerCourse.getCareer_id(), null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                mCareerCourseDetailList = DataParseUtils.parseCareerCourseDetail(response);
                for (CareerCourseDetail item : mCareerCourseDetailList) {
                    mVideoItemList.add(new VideoItem(item.getImg_url(), item.getName()));
                }
                mVideoInfoAdapter.notifyDataSetChanged();
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
        mLvCourseDetail = (ListView) findViewById(R.id.lvCourseDetail);
    }

    @Override
    protected void onPause() {
        super.onPause();
        App.getRequestQueue().cancelAll(TAG);
    }

    @Override
    protected void bindData() {
        mLvCourseDetail.setAdapter(mVideoInfoAdapter);
    }

    @Override
    protected void setListener() {
        mLvCourseDetail.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent _intent = new Intent(MaiZiCourseActivity.this, CoursePlayInfoActivity.class);
                _intent.putExtra("type", 1);
                Bundle _bundle = new Bundle();
                _bundle.putSerializable("data", mCareerCourseDetailList.get(position));
                _intent.putExtras(_bundle);
                startActivity(_intent);
            }
        });
    }
}