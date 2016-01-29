package com.ststudy.client.android.graduationproject.utils;

import com.google.gson.Gson;
import com.ststudy.client.android.graduationproject.model.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aaron on 2016/1/17.
 * 此类专门用于解析网络数据
 */
public class DataParseUtils {

    private static Gson _gson = new Gson();

    /**
     * 解析麦子学院课程类型数据
     *
     * @param pJsonObject 从网络中请求的json数组
     * @return 课程类型数据的列表
     */
    public static List<CareerCourse> parseCareer(JSONObject pJsonObject) {
        ArrayList<CareerCourse> _tempArray = new ArrayList<>();

        try {
            if (pJsonObject.has("success") && pJsonObject.getBoolean("success")) {
                JSONArray _jsonArray = pJsonObject.getJSONObject("data").getJSONArray("list");
                for (int i = 0; i < _jsonArray.length(); i++) {
                    CareerCourse _itemCourse = _gson.fromJson(_jsonArray.get(i).toString(), CareerCourse.class);
                    _tempArray.add(_itemCourse);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return _tempArray;
    }


    /**
     * 解析超星课程类型数据
     *
     * @param pJsonObject 从网络中请求的json数组
     * @return 课程类型数据的列表
     */
    public static List<NormalCourse> parseNormalCareer(JSONObject pJsonObject) {
        ArrayList<NormalCourse> _tempArray = new ArrayList<>();
        try {
            if (pJsonObject.has("success") && pJsonObject.getBoolean("success")) {
                JSONArray _jsonArray = pJsonObject.getJSONArray("data");
                for (int i = 0; i < _jsonArray.length(); i++) {
                    NormalCourse _course = _gson.fromJson(_jsonArray.get(i).toString(), NormalCourse.class);
                    _tempArray.add(_course);
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return _tempArray;
    }

    /**
     * 解析课程细节
     *
     * @param pJsonObject 从网络中获取的json对象
     * @return 解析信息对象
     */
    public static List<CareerCourseDetail> parseCareerCourseDetail(JSONObject pJsonObject) {
        ArrayList<CareerCourseDetail> _tempArray = new ArrayList<>();
        try {
            if (pJsonObject.has("success") && pJsonObject.getBoolean("success")) {
                if (pJsonObject.has("data")) {
                    if (pJsonObject.getJSONObject("data").has("stage")) {
                        JSONArray _jsonArray = pJsonObject.getJSONObject("data").getJSONArray("stage");
                        for (int i = 0; i < _jsonArray.length(); i++) {
                            if (_jsonArray.getJSONObject(i).has("list")) {
                                JSONArray _tempJsonArray = _jsonArray.getJSONObject(i).getJSONArray("list");
                                for (int j = 0; j < _tempJsonArray.length(); j++) {
                                    CareerCourseDetail item = _gson.fromJson(_tempJsonArray.get(j).toString(), CareerCourseDetail.class);
                                    _tempArray.add(item);
                                }
                            }
                        }
                    }
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return _tempArray;
    }

    /**
     * 解析视频列表信息
     *
     * @param pJsonObject 从网络中获取的json对象
     * @return 数据列表
     */
    public static List<VideoItemInfo> parseVideoItemInfo(JSONObject pJsonObject) {
        List<VideoItemInfo> _tempArray = new ArrayList<>();
        try {
            if (pJsonObject.has("success") && pJsonObject.getBoolean("success")) {
                if (pJsonObject.has("data")) {
                    JSONObject _jsonObj = pJsonObject.getJSONObject("data");
                    if (_jsonObj.has("video_list")) {
                        JSONArray _jsonArray = _jsonObj.getJSONArray("video_list");
                        for (int i = 0; i < _jsonArray.length(); i++) {
                            VideoItemInfo _item = _gson.fromJson(_jsonArray.get(i).toString(), VideoItemInfo.class);
                            _tempArray.add(_item);
                        }
                    }
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return _tempArray;
    }

    public static List<ChaoXingCourse> parseChaoXingList(JSONObject pJsonObject) {
        List<ChaoXingCourse> _tempArray = new ArrayList<>();
        if (pJsonObject.has("datas")) {
            try {
                JSONArray _jsonArray = pJsonObject.getJSONArray("datas");
                for (int i = 0; i < _jsonArray.length(); i++) {
                    _tempArray.add(_gson.fromJson(_jsonArray.getString(i), ChaoXingCourse.class));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return _tempArray;
    }

    /**
     * 解析新版本
     *
     * @param pJsonObject 从网络中获取到的数据
     * @return 新版本信息对象
     */
    public static NewVersion parseNewVersion(JSONObject pJsonObject) {
        NewVersion _newVersion = null;
        try {
            if (pJsonObject.has("new_version") && (pJsonObject.get("new_version") != null)) {
                _newVersion = _gson.fromJson(pJsonObject.get("new_version").toString(), NewVersion.class);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return _newVersion;
    }
}
