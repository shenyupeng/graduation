package com.ststudy.client.android.graduationproject.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.ststudy.client.android.graduationproject.R;
import com.ststudy.client.android.graduationproject.model.VideoInfo;

import java.util.List;

/**
 * Created by Aaron on 2016/1/29.
 * 提供视频列表数据的显示
 */
public class VideoListAdapter extends ViewBaseAdapter {

    private LayoutInflater mInflate;
    private List<VideoInfo> mVideoInfoList;

    public VideoListAdapter(Context pContext, List<VideoInfo> pList) {
        super(pList);
        mVideoInfoList = pList;
        mInflate = LayoutInflater.from(pContext);
    }

    @Override
    public Object getItem(int position) {
        return mVideoInfoList.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = new ViewHolder();
        if (null == convertView) {
            convertView = mInflate.inflate(R.layout.item_video, null, false);
            holder.tvVideoDesc = (TextView) convertView.findViewById(R.id.tvVideoDesc);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tvVideoDesc.setText(mVideoInfoList.get(position).getVideoInfo());
        return convertView;
    }

    private static class ViewHolder {
        TextView tvVideoDesc;
    }
}
