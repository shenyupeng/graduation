package com.ststudy.client.android.graduationproject.model;

import java.io.Serializable;

/**
 * Created by Aaron on 2016/1/31.
 * 本地视频信息
 */
public class LocalVideoInfo implements Serializable {

    private String filePath;
    private String title;

    public LocalVideoInfo() {
    }

    public LocalVideoInfo(String filePath, String title) {
        this.filePath = filePath;
        this.title = title;
    }


    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "LocalVideoInfo{" +
                "filePath='" + filePath + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
