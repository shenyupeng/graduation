package com.ststudy.client.android.graduationproject.utils;

import com.google.gson.Gson;
import com.ststudy.client.android.graduationproject.model.CareerCourse;
import com.ststudy.client.android.graduationproject.model.NewVersion;
import com.ststudy.client.android.graduationproject.model.NormalCourse;
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
            if (pJsonObject.has("success") && pJsonObject.get("success").toString().equals("true")) {
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
