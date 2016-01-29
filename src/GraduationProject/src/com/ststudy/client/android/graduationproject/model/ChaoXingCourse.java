package com.ststudy.client.android.graduationproject.model;

import java.io.Serializable;

/**
 * Created by Aaron on 2016/1/29.
 * 超星视频数据列表
 */
public class ChaoXingCourse implements Serializable {
    private String title;
    private String pointListUrl;
    private int seriNum;
    private long serieId;
    private long videoId;
    private String getVideoUrl;
    private String subtitleUrl;
    private String previewUrl;


    public ChaoXingCourse(String title, String pointListUrl, int seriNum, long serieId, long videoId, String videoUrl, String subtitleUrl, String previewUrl) {
        this.title = title;
        this.pointListUrl = pointListUrl;
        this.seriNum = seriNum;
        this.serieId = serieId;
        this.videoId = videoId;
        this.getVideoUrl = videoUrl;
        this.subtitleUrl = subtitleUrl;
        this.previewUrl = previewUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPointListUrl() {
        return pointListUrl;
    }

    public void setPointListUrl(String pointListUrl) {
        this.pointListUrl = pointListUrl;
    }

    public int getSeriNum() {
        return seriNum;
    }

    public void setSeriNum(int seriNum) {
        this.seriNum = seriNum;
    }

    public long getSerieId() {
        return serieId;
    }

    public void setSerieId(long serieId) {
        this.serieId = serieId;
    }

    public long getVideoId() {
        return videoId;
    }

    public void setVideoId(long videoId) {
        this.videoId = videoId;
    }

    public String getGetVideoUrl() {
        return getVideoUrl;
    }

    public void setGetVideoUrl(String getVideoUrl) {
        this.getVideoUrl = getVideoUrl;
    }

    public String getSubtitleUrl() {
        return subtitleUrl;
    }

    public void setSubtitleUrl(String subtitleUrl) {
        this.subtitleUrl = subtitleUrl;
    }

    public String getPreviewUrl() {
        return previewUrl;
    }

    public void setPreviewUrl(String previewUrl) {
        this.previewUrl = previewUrl;
    }

    @Override
    public String toString() {
        return "ChaoXingCourse{" +
                "title='" + title + '\'' +
                ", pointListUrl='" + pointListUrl + '\'' +
                ", seriNum=" + seriNum +
                ", serieId=" + serieId +
                ", videoId=" + videoId +
                ", getVideoUrl='" + getVideoUrl + '\'' +
                ", subtitleUrl='" + subtitleUrl + '\'' +
                ", previewUrl='" + previewUrl + '\'' +
                '}';
    }
}
