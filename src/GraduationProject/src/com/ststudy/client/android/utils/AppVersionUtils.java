package com.ststudy.client.android.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.ststudy.client.android.graduationproject.App;
import com.ststudy.client.android.graduationproject.Constants;
import com.ststudy.client.android.graduationproject.dialog.VersionUpdateDialog;
import com.ststudy.client.android.graduationproject.model.NewVersion;
import com.ststudy.client.android.graduationproject.utils.DataParseUtils;
import org.json.JSONObject;

/**
 * Created by Aaron on 2016/1/19.
 * 应用管理程序
 */
public class AppVersionUtils {

    private static final String PREF_VERSION_CODE = "pref_version_code";

    /**
     * 获取版本号
     *
     * @param pContext 上下文
     * @return 版本号
     */
    public static int getVersionCode(Context pContext) {
        int _versionCode = -1;
        try {
            PackageInfo _info = pContext.getPackageManager().getPackageInfo(pContext.getPackageName(), 0);
            _versionCode = _info.versionCode;
            return _versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return _versionCode;
    }

    /**
     * 版本核对
     *
     * @param pContext 上下文
     * @return 版本是否一致
     */
    public static boolean versionCheck(Context pContext) {
        return (SharedPrefsUtils.getPrefInt(pContext, PREF_VERSION_CODE, 0) == getVersionCode(pContext));
    }

    /**
     * 设置本地版本码
     *
     * @param pContext 上下文
     */
    public static void setVersionCode(Context pContext) {
        SharedPrefsUtils.setPrefInt(pContext, PREF_VERSION_CODE, getVersionCode(pContext));
    }

    /**
     * 检查应用版本更新信息
     *
     * @param pContext 弹对话框所需要的上下文
     * @param pTag     tag标签，方便管理请求队列
     */
    public static void checkUpdate(final Context pContext, String pTag) {
        JsonObjectRequest _request = new JsonObjectRequest(Request.Method.GET, Constants.UPDATE_VERSION_API, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                NewVersion _newVersion = DataParseUtils.parseNewVersion(response);
                //大于当前版本则弹出对话框
                if (_newVersion.getVc() > getVersionCode(pContext)) {
                    VersionUpdateDialog dialog = new VersionUpdateDialog(pContext, _newVersion);
                    dialog.show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        App.getRequestQueue().add(_request).setTag(pTag);
    }
}
