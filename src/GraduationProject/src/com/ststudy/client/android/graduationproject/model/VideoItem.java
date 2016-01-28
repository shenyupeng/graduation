package com.ststudy.client.android.graduationproject.model;

import java.io.Serializable;

/**
 * Created by Aaron on 2016/1/27.
 * 为每一个视频显示数据提供统一的数据
 */
public class VideoItem implements Serializable {

    private String img_url;
    private String tv_desc;

    /**
     * 数据构造
     *
     * @param img_url 图片地址
     * @param tv_desc 视频描述
     */
    public VideoItem(String img_url, String tv_desc) {
        this.img_url = img_url;
        this.tv_desc = tv_desc;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public String getTv_desc() {
        return tv_desc;
    }

    public void setTv_desc(String tv_desc) {
        this.tv_desc = tv_desc;
    }

    @Override
    public String toString() {
        return "VideoItem{" +
                "img_url='" + img_url + '\'' +
                ", tv_desc='" + tv_desc + '\'' +
                '}';
    }
}
