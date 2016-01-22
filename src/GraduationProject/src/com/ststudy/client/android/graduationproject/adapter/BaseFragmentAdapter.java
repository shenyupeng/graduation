package com.ststudy.client.android.graduationproject.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import com.ststudy.client.android.graduationproject.fragment.BaseFragment;

import java.util.List;

/**
 * Created by Aaron on 2016/1/21.
 * 提供基础的Fragment数据
 */
public abstract class BaseFragmentAdapter extends FragmentPagerAdapter {

    private List<BaseFragment> mFragmentList;

    public BaseFragmentAdapter(FragmentManager fm, List pFragmentList) {
        super(fm);
        this.mFragmentList = pFragmentList;
    }

    @Override
    public Fragment getItem(int i) {
        return mFragmentList.get(i);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }

    public abstract void addFragment(Fragment pFragment);

}
