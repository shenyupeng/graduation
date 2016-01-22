package com.ststudy.client.android.graduationproject.dialog;

import android.app.Dialog;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RemoteViews;
import android.widget.TextView;
import com.ststudy.client.android.graduationproject.Constants;
import com.ststudy.client.android.graduationproject.R;
import com.ststudy.client.android.graduationproject.model.AppInfo;
import com.ststudy.client.android.graduationproject.model.NewVersion;
import com.ststudy.client.android.utils.AppVersionUtils;
import com.ststudy.client.android.utils.multithreaddownload.CallBack;
import com.ststudy.client.android.utils.multithreaddownload.DownloadException;
import com.ststudy.client.android.utils.multithreaddownload.DownloadManager;
import com.ststudy.client.android.utils.multithreaddownload.DownloadRequest;

import java.io.File;

/**
 * Created by Aaron on 2016/1/19.
 * 更新对话框
 */
public class VersionUpdateDialog extends Dialog implements View.OnClickListener {

    private TextView mTvUpdateContent;
    private TextView mTvForceUpdate;
    private TextView mTvDownloadBack;
    private TextView mTvNoTip;
    private LinearLayout mLayForceUpdate;
    private int lastPositon = 0;
    private NotificationManager mNotiManager;
    private NotificationCompat.Builder mNotiCompat;
    private RemoteViews mNotiRemoteView;
    private final int NOTI_APP_UPDATE = 7;
    private AppInfo mUpdateApp;


    public VersionUpdateDialog(Context context, NewVersion pNewVersion) {
        super(context, R.style.update_Dialog);
        setContentView(R.layout.dialog_update);
        initData();
        initView(pNewVersion);
        setListener();
    }

    /**
     * 初始化数据
     */
    private void initData() {
        mNotiManager = (NotificationManager) getContext().getSystemService(Context.NOTIFICATION_SERVICE);
        mNotiCompat = new NotificationCompat.Builder(getContext()).setTicker("版本升级").setSmallIcon(R.drawable.ic_launcher);
        mNotiRemoteView = new RemoteViews(getContext().getPackageName(), R.layout.notification_update_app);
        mNotiCompat.setContent(mNotiRemoteView);
        mUpdateApp = new AppInfo();
        mUpdateApp.setUrl(Constants.APP_DOWNLOAD_URL);
        mUpdateApp.setName("GraduationProject.apk");
    }

    /**
     * 初始化界面
     *
     * @param pNewVersion 新版本对象
     */
    private void initView(NewVersion pNewVersion) {
        mTvUpdateContent = (TextView) findViewById(R.id.tvUpdateContent);
        mTvForceUpdate = (TextView) findViewById(R.id.tvForceUpdate);
        mTvDownloadBack = (TextView) findViewById(R.id.tvDownloadBack);
        mTvNoTip = (TextView) findViewById(R.id.tvNoTip);
        mLayForceUpdate = (LinearLayout) findViewById(R.id.layForceUpdate);
        if (pNewVersion.is_force()) {
            mLayForceUpdate.setVisibility(View.GONE);
            mTvForceUpdate.setVisibility(View.VISIBLE);
            //强制升级时候不能响应任何事件
            this.setCancelable(false);
        } else {
            mLayForceUpdate.setVisibility(View.VISIBLE);
            mTvForceUpdate.setVisibility(View.GONE);
        }
        mTvUpdateContent.setText(pNewVersion.getDesc());
    }

    private void setListener() {
        mTvDownloadBack.setOnClickListener(this);
        mTvForceUpdate.setOnClickListener(this);
        mTvNoTip.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tvDownloadBack:
                upateApp();
                this.dismiss();
                break;
            case R.id.tvNoTip:
                AppVersionUtils.setNoTipUpdateApp(getContext());
                this.dismiss();
                break;
            case R.id.tvForceUpdate:
                upateApp();
                break;
        }
    }


    /**
     * 更新APP操作
     */
    private void upateApp() {
        mNotiManager.notify(NOTI_APP_UPDATE, mNotiCompat.build());
        File _filePath = new File(Constants.APP_DOWNLOAD_PATH + mUpdateApp.getName());
        if (_filePath.exists()) {
            _filePath.delete();
        }
        DownloadRequest _request = new DownloadRequest.Builder().setTitle(mUpdateApp.getName()).setUri(mUpdateApp.getUrl()).setFolder(new File(Constants.APP_DOWNLOAD_PATH)).build();
        DownloadManager.getInstance().download(_request, mUpdateApp.getUrl(), new CallBack() {
            @Override
            public void onStarted() {
                mNotiRemoteView.setProgressBar(R.id.pbUpdateProgress, 100, 0, false);
                mNotiRemoteView.setTextViewText(R.id.tvUpdateSubText, "正在连接下载");
                mNotiRemoteView.setTextViewText(R.id.tvUpdateText, "版本更新");
                mNotiManager.notify(NOTI_APP_UPDATE, mNotiCompat.build());
            }

            @Override
            public void onConnecting() {

            }

            @Override
            public void onConnected(long total, boolean isRangeSupport) {

            }

            @Override
            public void onProgress(long finished, long total, int progress) {
                mNotiRemoteView.setProgressBar(R.id.pbUpdateProgress, 100, progress, false);
                mNotiRemoteView.setTextViewText(R.id.tvUpdateSubText, "当前进度为：" + progress + "%");
                mNotiRemoteView.setTextViewText(R.id.tvUpdateText, "版本更新");
                if ((progress - lastPositon) > 1) {
                    mNotiManager.notify(NOTI_APP_UPDATE, mNotiCompat.build());
                }
            }

            @Override
            public void onCompleted() {
                if (mNotiCompat != null) {
                    mNotiManager.cancel(NOTI_APP_UPDATE);
                }
                //安装应用
                String fileName = Constants.APP_DOWNLOAD_PATH + mUpdateApp.getName();
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setDataAndType(Uri.fromFile(new File(fileName)), "application/vnd.android.package-archive");
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                getContext().startActivity(intent);
            }

            @Override
            public void onDownloadPaused() {
                mNotiRemoteView.setTextViewText(R.id.tvUpdateSubText, "暂停下载");
                mNotiManager.notify(NOTI_APP_UPDATE, mNotiCompat.build());
            }

            @Override
            public void onDownloadCanceled() {
                if (mNotiCompat != null) {
                    mNotiManager.cancel(NOTI_APP_UPDATE);
                }
            }

            @Override
            public void onFailed(DownloadException e) {
                mNotiRemoteView.setTextViewText(R.id.tvUpdateSubText, "下载失败");
                mNotiManager.notify(NOTI_APP_UPDATE, mNotiCompat.build());
            }
        });
    }
}
