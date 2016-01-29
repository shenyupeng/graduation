package com.ststudy.client.android.graduationproject.model;

import java.io.Serializable;

/**
 * Created by Aaron on 2016/1/29.
 * 提供麦子学院某类课程细节
 */
public class CareerCourseDetail implements Serializable {

    private String img_url;
    private int updating;
    private String name;
    private String course_id;

    /**
     * 数据构造
     *
     * @param img_url   图片链接
     * @param updating  正在更新
     * @param name      课程名称
     * @param course_id 课程id
     */
    public CareerCourseDetail(String img_url, int updating, String name, String course_id) {
        this.img_url = img_url;
        this.updating = updating;
        this.name = name;
        this.course_id = course_id;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public int getUpdating() {
        return updating;
    }

    public void setUpdating(int updating) {
        this.updating = updating;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCourse_id() {
        return course_id;
    }

    public void setCourse_id(String course_id) {
        this.course_id = course_id;
    }

    @Override
    public String toString() {
        return "CareerCourseDetail{" +
                "img_url='" + img_url + '\'' +
                ", updating=" + updating +
                ", name='" + name + '\'' +
                ", course_id='" + course_id + '\'' +
                '}';
    }
}
