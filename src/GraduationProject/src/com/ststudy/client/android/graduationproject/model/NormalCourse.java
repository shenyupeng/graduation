package com.ststudy.client.android.graduationproject.model;

import java.io.Serializable;

/**
 * Created by Aaron on 2016/1/28.
 * 提供超星网站上的通用数据
 */
public class NormalCourse implements Serializable {

    private String img_url;
    private String serieId;
    private String desc;

    /**
     * 数据构造
     *
     * @param img_url 显示的图片链接
     * @param serieId 视频的系列Id
     * @param desc    视频的描述
     */
    public NormalCourse(String img_url, String serieId, String desc) {
        this.img_url = img_url;
        this.serieId = serieId;
        this.desc = desc;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public String getSerieId() {
        return serieId;
    }

    public void setSerieId(String serieId) {
        this.serieId = serieId;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "NormalCourse{" +
                "img_url='" + img_url + '\'' +
                ", serieId='" + serieId + '\'' +
                ", desc='" + desc + '\'' +
                '}';
    }
}
