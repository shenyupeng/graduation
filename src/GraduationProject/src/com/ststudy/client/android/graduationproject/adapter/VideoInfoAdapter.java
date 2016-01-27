package com.ststudy.client.android.graduationproject.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.ststudy.client.android.graduationproject.R;
import com.ststudy.client.android.utils.glide.Glide;

import java.util.List;

/**
 * Created by Aaron on 2016/1/26.
 * 提供视频基本的Adapter
 */
public class VideoInfoAdapter extends ViewBaseAdapter {

    private Context mContext;
    private List<?> mList;
    private LayoutInflater mInflater;

    public VideoInfoAdapter(Context pContext, List<?> pList) {
        super(pList);
        this.mContext = pContext;
        this.mList = pList;
        mInflater = LayoutInflater.from(pContext);
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = new ViewHolder();
        if (null == convertView) {
            convertView = mInflater.inflate(R.layout.item_video_course, null, false);
            holder.ivCourse = (ImageView) convertView.findViewById(R.id.ivCourse);
            holder.tvCourseDesc = (TextView) convertView.findViewById(R.id.tvCourseDesc);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        Glide.with(parent.getContext()).load("http://www.maiziedu.com/uploads/course/2015/09/img-ios2x.png").into(holder.ivCourse);
        holder.tvCourseDesc.setText("我在测试。。。。");
        return convertView;
    }

    private static class ViewHolder {
        ImageView ivCourse;
        TextView tvCourseDesc;
    }
}
