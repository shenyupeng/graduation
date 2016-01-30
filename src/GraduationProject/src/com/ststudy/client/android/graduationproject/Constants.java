package com.ststudy.client.android.graduationproject;

import android.os.Environment;

/**
 * Created by Aaron on 2016/1/17.
 * 应用中所需要常量
 */
public class Constants {

        public static final String UPDATE_VERSION_API = "https://raw.githubusercontent.com/shenyupeng/graduation/master/Deployment/version/version";
    public static final String APP_DOWNLOAD_URL = "https://raw.githubusercontent.com/shenyupeng/graduation/master/Deployment/apk/GraduationProject.apk";
    public static final String APP_BASE_VIDEO_REQUEST_URL = "https://raw.githubusercontent.com/shenyupeng/graduation/master/Deployment/api/";
//    public static final String APP_BASE_VIDEO_REQUEST_URL = "http://192.168.1.4/api/";
//    public static final String APP_DOWNLOAD_URL = "http://192.168.1.4/GraduationProject.apk";
//    public static final String UPDATE_VERSION_API = "http://192.168.1.4/version";
    public static final String APP_DOWNLOAD_PATH = Environment.getExternalStorageDirectory().toString() + "/Download/";

    //Log信息管理
    interface MyLog {
        boolean DEBUG = true;
        String TAG = "Aaron";
    }

    //麦子学院的API
    public interface MAIZIAPI {
        String CAREERCOURSE = "http://api.maiziedu.com/v3/getCareerCourse/?count=0";
        String CAREERLISTAPI = "http://api.maiziedu.com/v2/getCareerDetail/?careerId=";
        String COURSEPLAYINFOAPI = "http://api.maiziedu.com/v2/getCoursePlayInfo/?courseId=";
    }

    //超星的API
    public interface CHAOXINGAPI {
        String CAREERLIST = "http://video.chaoxing.com/ajax/getvideolistinfo_";
    }


}
