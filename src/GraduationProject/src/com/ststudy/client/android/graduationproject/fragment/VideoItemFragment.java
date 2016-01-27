package com.ststudy.client.android.graduationproject.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.ststudy.client.android.graduationproject.App;
import com.ststudy.client.android.graduationproject.Constants;
import com.ststudy.client.android.graduationproject.L;
import com.ststudy.client.android.graduationproject.R;
import com.ststudy.client.android.graduationproject.adapter.VideoInfoAdapter;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Aaron on 2016/1/23.
 * 每一个视频的通用接口
 */
public class VideoItemFragment extends BaseFragment {

    private String TAG = VideoItemFragment.class.getSimpleName();
    private int mCurrentIndex;
    private ListView mVideoItemList;

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
                    if (1 == mCurrentIndex) {
                        L.d("我是麦子学院的数据：" + response.toString());
                    } else {
                        L.d(response.toString());
                    }
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

        ArrayList<String> list = new ArrayList();
        for (int i = 0; i < 30; i++) {
            list.add("111");
        }
        VideoInfoAdapter adapter = new VideoInfoAdapter(getContext(), list);
        mVideoItemList.setAdapter(adapter);
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
