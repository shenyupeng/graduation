package com.ststudy.client.android.graduationproject.model;

/**
 * Created by Aaron on 2016/1/19.
 */
public class NewVersion {
    private String down_url;
    private int vc;
    private boolean is_force;
    private String desc;
    private String size;

    /**
     * 检查新版本信息
     *
     * @param down_url 新版本的下载地址
     * @param vc       新版本的VersionCode
     * @param is_force 是否强制升级
     * @param desc     新版本的描述
     * @param size     新版本的大小
     */
    public NewVersion(String down_url, int vc, boolean is_force, String desc, String size) {
        this.down_url = down_url;
        this.vc = vc;
        this.is_force = is_force;
        this.desc = desc;
        this.size = size;
    }

    public String getDown_url() {
        return down_url;
    }

    public void setDown_url(String down_url) {
        this.down_url = down_url;
    }

    public int getVc() {
        return vc;
    }

    public void setVc(int vc) {
        this.vc = vc;
    }

    public boolean is_force() {
        return is_force;
    }

    public void setIs_force(boolean is_force) {
        this.is_force = is_force;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "NewVersion{" +
                "down_url='" + down_url + '\'' +
                ", vc=" + vc +
                ", is_force=" + is_force +
                ", desc='" + desc + '\'' +
                ", size='" + size + '\'' +
                '}';
    }
}
