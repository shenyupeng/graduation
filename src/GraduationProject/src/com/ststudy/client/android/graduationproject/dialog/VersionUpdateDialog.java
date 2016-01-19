package com.ststudy.client.android.graduationproject.dialog;

import android.app.Dialog;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.ststudy.client.android.graduationproject.Constants;
import com.ststudy.client.android.graduationproject.L;
import com.ststudy.client.android.graduationproject.R;
import com.ststudy.client.android.graduationproject.model.NewVersion;
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

    public VersionUpdateDialog(Context context, NewVersion pNewVersion) {
        super(context, R.style.update_Dialog);
        setContentView(R.layout.dialog_update);
        initView(pNewVersion);
        setListener();
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
                String TAG = "updateApp";
                DownloadRequest request = new DownloadRequest.Builder().setTitle("GraduationProject.apk").setUri("https://raw.githubusercontent.com/shenyupeng/graduation/master/Deployment/apk/GraduationProject.apk").setFolder(new File(Constants.APP_DOWNLOAD_PATH)).build();
                final NotificationManager nm = (NotificationManager) getContext().getSystemService(Context.NOTIFICATION_SERVICE);
                final NotificationCompat.Builder nc = new NotificationCompat.Builder(getContext()).setSmallIcon(R.drawable.ic_launcher).setProgress(100, 0, true).setContentTitle("版本升级").setContentText("当前进度为：").setShowWhen(true);
                DownloadManager.getInstance().download(request, TAG, new CallBack() {
                    @Override
                    public void onStarted() {

                    }

                    @Override
                    public void onConnecting() {

                    }

                    @Override
                    public void onConnected(long total, boolean isRangeSupport) {

                    }

                    @Override
                    public void onProgress(long finished, long total, int progress) {
                        if ((progress - lastPositon) > 1) {
                            nc.setProgress(100, progress, false);
                            nc.setColor(Color.BLUE);
                            nc.setSmallIcon(R.drawable.ic_launcher);
                            nc.setContentTitle("版本升级");
                            nc.setContentText("当前进度为：" + progress + " %");
                            nm.notify(7, nc.build());
                            lastPositon = progress;
                        }
                        if (100 == progress) {
                            L.d("我完了");

                            //安装应用
                            String fileName = Constants.APP_DOWNLOAD_PATH + "GraduationProject.apk";
                            Intent intent = new Intent(Intent.ACTION_VIEW);
                            intent.setDataAndType(Uri.fromFile(new File(fileName)), "application/vnd.android.package-archive");
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            getContext().startActivity(intent);
                        }
                    }

                    @Override
                    public void onCompleted() {
                        nm.cancel(7);
                    }

                    @Override
                    public void onDownloadPaused() {

                    }

                    @Override
                    public void onDownloadCanceled() {
                        nm.cancel(7);
                    }

                    @Override
                    public void onFailed(DownloadException e) {

                    }
                });
                nm.notify(7, nc.build());
                L.d("后台更新软件");
                this.dismiss();
                break;
            case R.id.tvNoTip:
                L.d("不在提示更新");
                this.dismiss();
                break;
            case R.id.tvForceUpdate:
                L.d("强制更新");
                break;
        }
    }
}
