package com.ststudy.client.android.graduationproject.listener;

import android.support.v4.view.ViewPager;

/**
 * Created by Aaron on 2016/1/17.
 * 获取当前ViewPager选中的位置
 */
public abstract class OnViewPagerSelectedLastListener implements ViewPager.OnPageChangeListener {
    @Override
    public void onPageScrolled(int i, float v, int i1) {

    }

    @Override
    public abstract void onPageSelected(int i);

    @Override
    public void onPageScrollStateChanged(int i) {

    }
}
