package com.ststudy.client.android.graduationproject.model;

import java.io.Serializable;

/**
 * Created by Aaron on 2016/1/29.
 * 提供统一的视频信息
 */
public class VideoItemInfo implements Serializable {

    private String video_name;
    private String video_url;
    private int video_id;
    private boolean is_watch;

    /**
     * 视频信息数据构造
     *
     * @param video_name 视频名称
     * @param video_url  视频链接
     * @param video_id   视频id
     * @param is_watch   是否被看过
     */
    public VideoItemInfo(String video_name, String video_url, int video_id, boolean is_watch) {
        this.video_name = video_name;
        this.video_url = video_url;
        this.video_id = video_id;
        this.is_watch = is_watch;
    }

    public String getVideo_name() {
        return video_name;
    }

    public void setVideo_name(String video_name) {
        this.video_name = video_name;
    }

    public String getVideo_url() {
        return video_url;
    }

    public void setVideo_url(String video_url) {
        this.video_url = video_url;
    }

    public int getVideo_id() {
        return video_id;
    }

    public void setVideo_id(int video_id) {
        this.video_id = video_id;
    }

    public boolean is_watch() {
        return is_watch;
    }

    public void setIs_watch(boolean is_watch) {
        this.is_watch = is_watch;
    }

    @Override
    public String toString() {
        return "VideoItemInfo{" +
                "video_name='" + video_name + '\'' +
                ", video_url='" + video_url + '\'' +
                ", video_id=" + video_id +
                ", is_watch=" + is_watch +
                '}';
    }
}
