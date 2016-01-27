package com.ststudy.client.android.graduationproject.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import java.util.List;

/**
 * Created by Aaron on 2016/1/21.
 * 音频Fragment
 */
public class ViewPagerFragmentAdapter extends BaseFragmentAdapter {

    private List<Fragment> mFragmentList;

    public ViewPagerFragmentAdapter(FragmentManager fm, List pFragmentList) {
        super(fm, pFragmentList);
        this.mFragmentList = pFragmentList;
    }

    @Override
    public void addFragment(Fragment pFragment) {
        mFragmentList.add(pFragment);
    }
}
