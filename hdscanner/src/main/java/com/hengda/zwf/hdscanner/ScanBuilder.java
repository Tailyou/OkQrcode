package com.hengda.zwf.hdscanner;

import android.graphics.Color;

/**
 * 扫描配置构造者
 *
 * @author 祝文飞（Tailyou）
 * @time 2017/4/27 18:21
 */
public class ScanBuilder {

    private int title;
    private int scanTip;
    private int toolbarColor = Color.GRAY;
    private int laserColor = Color.YELLOW;
    private int mediaResId = R.raw.beep;
    private int frameMarginTop = 128;
    private int frameSizeWidth = 256;
    private int frameSizeHeight = 256;
    private int frameCornerLength = 24;
    private int laserLineHeight = 2;

    public ScanBuilder setToolbarColor(int toolbarColor) {
        this.toolbarColor = toolbarColor;
        return this;
    }

    public ScanBuilder setLaserColor(int laserColor) {
        this.laserColor = laserColor;
        return this;
    }

    public ScanBuilder setMediaResId(int mediaResId) {
        this.mediaResId = mediaResId;
        return this;
    }

    public ScanBuilder setFrameMarginTop(int frameMarginTop) {
        this.frameMarginTop = frameMarginTop;
        return this;
    }

    public ScanBuilder setFrameSizeWidth(int frameSizeWidth) {
        this.frameSizeWidth = frameSizeWidth;
        return this;
    }

    public ScanBuilder setFrameSizeHeight(int frameSizeHeight) {
        this.frameSizeHeight = frameSizeHeight;
        return this;
    }

    public ScanBuilder setFrameCornerLenght(int frameCornerLength) {
        this.frameCornerLength = frameCornerLength;
        return this;
    }

    public ScanBuilder setLaserLineHeight(int frameCornerLength) {
        this.frameCornerLength = frameCornerLength;
        return this;
    }

    public ScanBuilder setTitle(int title) {
        this.title = title;
        return this;
    }

    public ScanBuilder setScanTip(int scanTip) {
        this.scanTip = scanTip;
        return this;
    }

    public ScanConfig create() {
        return new ScanConfig(title, scanTip, toolbarColor, laserColor, mediaResId,
                frameMarginTop, frameSizeWidth, frameSizeHeight, frameCornerLength, laserLineHeight);
    }

}
