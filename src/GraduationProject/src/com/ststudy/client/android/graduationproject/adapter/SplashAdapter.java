package com.ststudy.client.android.graduationproject.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

/**
 * Created by Aaron on 2016/1/17.
 * 引导界面的Adapter
 */
public class SplashAdapter extends ViewPagerBaseAdapter {

    private Context mContext;
    private List mImgs;

    public SplashAdapter(Context pContext, List pList) {
        super(pList);
        this.mContext = pContext;
        this.mImgs = pList;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView _ivTemp = new ImageView(mContext);
        _ivTemp.setScaleType(ImageView.ScaleType.FIT_XY);
        _ivTemp.setBackgroundResource((Integer) mImgs.get(position));
        container.addView(_ivTemp);
        return _ivTemp;
    }
}
