package com.ststudy.client.android.graduationproject.model;

/**
 * Created by Aaron on 2016/1/29.
 * 提供视频的显示文字
 */
public class VideoInfo {

    private String videoInfo;

    /**
     * 视频数据构造
     *
     * @param videoInfo 视频描述
     */
    public VideoInfo(String videoInfo) {
        this.videoInfo = videoInfo;
    }

    public String getVideoInfo() {
        return videoInfo;
    }

    public void setVideoInfo(String videoInfo) {
        this.videoInfo = videoInfo;
    }

    @Override
    public String toString() {
        return "VideoInfo{" +
                "videoInfo='" + videoInfo + '\'' +
                '}';
    }
}
