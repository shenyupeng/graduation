package com.ststudy.client.android.graduationproject.adapter;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.ststudy.client.android.graduationproject.R;
import com.ststudy.client.android.graduationproject.model.VideoItem;
import com.ststudy.client.android.utils.glide.Glide;

import java.util.List;

/**
 * Created by Aaron on 2016/1/26.
 * 提供视频基本的Adapter
 */
public class VideoInfoAdapter extends ViewBaseAdapter {

    private List<VideoItem> mList;
    private LayoutInflater mInflater;

    public VideoInfoAdapter(Context pContext, List<VideoItem> pList) {
        super(pList);
        this.mList = pList;
        mInflater = LayoutInflater.from(pContext);
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View getView(int position, View convertView, final ViewGroup parent) {
        ViewHolder holder = new ViewHolder();
        if (null == convertView) {
            convertView = mInflater.inflate(R.layout.item_video_course, null, false);
            holder.ivCourse = (ImageView) convertView.findViewById(R.id.ivCourse);
            holder.tvCourseDesc = (TextView) convertView.findViewById(R.id.tvCourseDesc);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        if (null != mList.get(position).getImg_url()) {
            Glide.with(parent.getContext()).load(mList.get(position).getImg_url()).into(holder.ivCourse);
        } else {
            holder.ivCourse.setImageResource(R.drawable.video_test);
        }
        holder.tvCourseDesc.setText(mList.get(position).getTv_desc());
        return convertView;
    }

    private static class ViewHolder {
        ImageView ivCourse;
        TextView tvCourseDesc;
    }
}
