package com.ststudy.client.android.graduationproject.model;

/**
 * Created by Aaron on 2016/1/17.
 * 课程开始界面
 * 有利的数据为careerId
 */
public class CareerCourse {

    private String img_url;
    private String class_count;
    private String student_count;
    private String course_count;
    private String career_id;
    private String name;

    public CareerCourse() {

    }

    public CareerCourse(String img_url, String class_count, String student_count, String course_count, String career_id, String name) {
        this.img_url = img_url;
        this.class_count = class_count;
        this.student_count = student_count;
        this.course_count = course_count;
        this.career_id = career_id;
        this.name = name;
    }

    public String getImg_url() {
        return img_url;
    }


    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public String getClass_count() {
        return class_count;
    }

    public void setClass_count(String class_count) {
        this.class_count = class_count;
    }

    public String getStudent_count() {
        return student_count;
    }

    public void setStudent_count(String student_count) {
        this.student_count = student_count;
    }

    public String getCourse_count() {
        return course_count;
    }

    public void setCourse_count(String course_count) {
        this.course_count = course_count;
    }

    public String getCareer_id() {
        return career_id;
    }

    public void setCareer_id(String career_id) {
        this.career_id = career_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public String toString() {
        return "CareerCourse{" +
                "img_url='" + img_url + '\'' +
                ", class_count='" + class_count + '\'' +
                ", student_count='" + student_count + '\'' +
                ", course_count='" + course_count + '\'' +
                ", career_id='" + career_id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
