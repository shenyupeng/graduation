package com.ststudy.client.android.graduationproject.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;

import java.util.List;

/**
 * Created by Aaron on 2016/1/17.
 */
public class ViewPagerBaseAdapter extends PagerAdapter {

    private List<?> mList;

    public ViewPagerBaseAdapter(List pList) {
        this.mList = pList;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object o) {
        return (view == o);
    }
}
