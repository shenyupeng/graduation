package com.ststudy.client.android.graduationproject.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.ststudy.client.android.graduationproject.App;
import com.ststudy.client.android.graduationproject.L;
import com.ststudy.client.android.graduationproject.R;
import org.json.JSONObject;

/**
 * Created by Aaron on 2016/1/23.
 * 每一个视频的通用接口
 */
public class VideoItemFragment extends BaseFragment {

    private String TAG = VideoItemFragment.class.getSimpleName();
    private int mCurrentIndex;
    private TextView mTvTarget;

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
        String _RequestStr = "http://192.168.1.3/2";
        switch (mCurrentIndex) {
            case 1:
                L.d("我是1...........");
                break;
            case 2:
                L.d("我是2...........");
                break;
            case 3:
                L.d("我是3...........");
                break;
            case 4:
                L.d("我是4...........");
                break;
            case 5:
                L.d("我是5...........");
                break;
            case 6:
                L.d("我是6...........");
                break;
            case 7:
                L.d("我是7...........");
                break;
            case 8:
                L.d("我是8...........");
                break;
            case 9:
                L.d("我是9...........");
                break;
            case 10:
                L.d("我是10...........");
                break;
            case 11:
                L.d("我是11...........");
                break;
            case 12:
                L.d("我是12...........");
                break;
            default:
                L.d("我是默认。。。");
                break;
        }
        JsonObjectRequest _request = new JsonObjectRequest(Request.Method.GET, _RequestStr, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                L.d(response.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        App.getRequestQueue().add(_request).setTag(TAG + mCurrentIndex);
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
        mTvTarget = (TextView) pView.findViewById(R.id.swipe_target);
    }

    @Override
    protected void initData() {
        mTvTarget.setText("当前是第" + mCurrentIndex + "个视频管理界面");
    }

    @Override
    protected void bindData() {

    }

    @Override
    protected void setListener() {

    }
}
