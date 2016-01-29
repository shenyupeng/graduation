package com.ststudy.client.android.graduationproject.fragment;

import android.content.Intent;
import android.os.Bundle;
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
import com.ststudy.client.android.graduationproject.adapter.VideoInfoAdapter;
import com.ststudy.client.android.graduationproject.model.CareerCourse;
import com.ststudy.client.android.graduationproject.model.NormalCourse;
import com.ststudy.client.android.graduationproject.model.VideoItem;
import com.ststudy.client.android.graduationproject.utils.DataParseUtils;
import org.json.JSONObject;

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
        if (mCurrentIndex > 0) {
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
    }

    @Override
    public void onPause() {
        super.onPause();
        //取消有关网络请求
        App.getRequestQueue().cancelAll(TAG + mCurrentIndex);
    }

    @Override
    protected void findView(View pView) {
        mVideoItemList = (ListView) pView.findViewById(R.id.swipe_target);
        mVideoItemList.setAdapter(mInfoAdapter);

        mVideoItemList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent _intent = new Intent();
                Bundle _bundle = new Bundle();
                if (0 < mCurrentIndex) {
                    if (1 == mCurrentIndex) {
                        _intent.setClass(getContext(), MaiZiCourseActivity.class);
                        _bundle.putSerializable("data", mCareerCourseList.get(position));
                    } else {
                        _intent.setClass(getContext(), CoursePlayInfoActivity.class);
                        _intent.putExtra("type", 2);
                        _bundle.putSerializable("data", mNormalCourseList.get(position));
                    }
                    _intent.putExtras(_bundle);
                    startActivity(_intent);
                }
            }
        });
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
