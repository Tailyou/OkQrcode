package com.hengda.zwf.hdscanner;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 扫描参数配置类
 *
 * @author 祝文飞（Tailyou）
 * @time 2017/4/27 17:22
 */
public class ScanConfig implements Parcelable {

    private int title;//标题
    private int scanTip;//扫描提示
    private int toolbarColor;//toolbar颜色
    private int titleColor;//文字，返回按钮颜色
    private int laserColor;//扫描线颜色
    private int mediaResId;//扫描成功声音
    private int frameMarginTop;//扫描框上边距
    private int frameSizeWidth;//扫描框宽度
    private int frameSizeHeight;//扫描框高度
    private int frameCornerLength;//角上短线长度
    private int laserLineHeight;//扫描线高度

    protected ScanConfig(Parcel in) {
        title = in.readInt();
        scanTip = in.readInt();
        toolbarColor = in.readInt();
        titleColor = in.readInt();
        laserColor = in.readInt();
        mediaResId = in.readInt();
        frameMarginTop = in.readInt();
        frameSizeWidth = in.readInt();
        frameSizeHeight = in.readInt();
        frameCornerLength = in.readInt();
        laserLineHeight = in.readInt();
    }

    public static final Creator<ScanConfig> CREATOR = new Creator<ScanConfig>() {
        @Override
        public ScanConfig createFromParcel(Parcel in) {
            return new ScanConfig(in);
        }

        @Override
        public ScanConfig[] newArray(int size) {
            return new ScanConfig[size];
        }
    };

    public int getTitle() {
        return title;
    }

    public void setTitle(int title) {
        this.title = title;
    }

    public int getScanTip() {
        return scanTip;
    }

    public void setScanTip(int scanTip) {
        this.scanTip = scanTip;
    }

    public int getToolbarColor() {
        return toolbarColor;
    }

    public void setToolbarColor(int toolbarColor) {
        this.toolbarColor = toolbarColor;
    }

    public int getTitleColor() {
        return titleColor;
    }

    public void setTitleColor(int titleColor) {
        this.titleColor = titleColor;
    }

    public int getLaserColor() {
        return laserColor;
    }

    public void setLaserColor(int laserColor) {
        this.laserColor = laserColor;
    }

    public int getMediaResId() {
        return mediaResId;
    }

    public void setMediaResId(int mediaResId) {
        this.mediaResId = mediaResId;
    }

    public int getFrameMarginTop() {
        return frameMarginTop;
    }

    public void setFrameMarginTop(int frameMarginTop) {
        this.frameMarginTop = frameMarginTop;
    }

    public int getFrameSizeWidth() {
        return frameSizeWidth;
    }

    public void setFrameSizeWidth(int frameSizeWidth) {
        this.frameSizeWidth = frameSizeWidth;
    }

    public int getFrameSizeHeight() {
        return frameSizeHeight;
    }

    public void setFrameSizeHeight(int frameSizeHeight) {
        this.frameSizeHeight = frameSizeHeight;
    }

    public int getFrameCornerLength() {
        return frameCornerLength;
    }

    public void setFrameCornerLength(int frameCornerLength) {
        this.frameCornerLength = frameCornerLength;
    }

    public int getLaserLineHeight() {
        return laserLineHeight;
    }

    public void setLaserLineHeight(int laserLineHeight) {
        this.laserLineHeight = laserLineHeight;
    }

    public ScanConfig(int title, int scanTip, int toolbarColor,
                      int laserColor, int titleColor, int mediaResId,
                      int frameMarginTop, int frameSizeWidth, int frameSizeHeight,
                      int frameCornerLength, int laserLineHeight) {
        this.title = title;
        this.scanTip = scanTip;
        this.toolbarColor = toolbarColor;
        this.titleColor = titleColor;
        this.laserColor = laserColor;
        this.mediaResId = mediaResId;
        this.frameMarginTop = frameMarginTop;
        this.frameSizeWidth = frameSizeWidth;
        this.frameSizeHeight = frameSizeHeight;
        this.frameCornerLength = frameCornerLength;
        this.laserLineHeight = laserLineHeight;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(title);
        dest.writeInt(scanTip);
        dest.writeInt(toolbarColor);
        dest.writeInt(titleColor);
        dest.writeInt(laserColor);
        dest.writeInt(mediaResId);
        dest.writeInt(frameMarginTop);
        dest.writeInt(frameSizeWidth);
        dest.writeInt(frameSizeHeight);
        dest.writeInt(frameCornerLength);
        dest.writeInt(laserLineHeight);
    }
}
