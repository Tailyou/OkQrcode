package com.hengda.zwf.hdscanner;

import android.graphics.Color;

/**
 * 扫描配置构造者
 *
 * @author 祝文飞（Tailyou）
 * @time 2017/4/27 18:21
 */
public class ScanConfigBuilder {

    private int title;
    private int scanTip;
    private int toolbarColor = Color.GRAY;
    private int titleColor = Color.WHITE;
    private int laserColor = Color.YELLOW;
    private int mediaResId = R.raw.beep;
    private int frameMarginTop = 128;
    private int frameSizeWidth = 256;
    private int frameSizeHeight = 256;
    private int frameCornerLength = 24;
    private int laserLineHeight = 2;

    public ScanConfigBuilder setToolbarColor(int toolbarColor) {
        this.toolbarColor = toolbarColor;
        return this;
    }

    public ScanConfigBuilder setTitleColor(int titleColor) {
        this.titleColor = titleColor;
        return this;
    }

    public ScanConfigBuilder setLaserColor(int laserColor) {
        this.laserColor = laserColor;
        return this;
    }

    public ScanConfigBuilder setMediaResId(int mediaResId) {
        this.mediaResId = mediaResId;
        return this;
    }

    public ScanConfigBuilder setFrameMarginTop(int frameMarginTop) {
        this.frameMarginTop = frameMarginTop;
        return this;
    }

    public ScanConfigBuilder setFrameSizeWidth(int frameSizeWidth) {
        this.frameSizeWidth = frameSizeWidth;
        return this;
    }

    public ScanConfigBuilder setFrameSizeHeight(int frameSizeHeight) {
        this.frameSizeHeight = frameSizeHeight;
        return this;
    }

    public ScanConfigBuilder setFrameCornerLenght(int frameCornerLength) {
        this.frameCornerLength = frameCornerLength;
        return this;
    }

    public ScanConfigBuilder setLaserLineHeight(int frameCornerLength) {
        this.frameCornerLength = frameCornerLength;
        return this;
    }

    public ScanConfigBuilder setTitle(int title) {
        this.title = title;
        return this;
    }

    public ScanConfigBuilder setScanTip(int scanTip) {
        this.scanTip = scanTip;
        return this;
    }

    public ScanConfig create() {
        return new ScanConfig(title, scanTip, toolbarColor, titleColor, laserColor, mediaResId,
                frameMarginTop, frameSizeWidth, frameSizeHeight, frameCornerLength, laserLineHeight);
    }

}
