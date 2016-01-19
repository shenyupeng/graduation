package com.ststudy.client.android.graduationproject.adapter;

/**
 * Created by Aaron on 2016/1/17.
 */

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by Aaron on 2016/1/17.
 * 普通界面的BaseAdapter
 */
public abstract class ViewBaseAdapter extends BaseAdapter {
    private List<?> mList;

    public ViewBaseAdapter(List<?> pList) {
        this.mList = pList;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public abstract Object getItem(int position);

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public abstract View getView(int position, View convertView, ViewGroup parent);
}