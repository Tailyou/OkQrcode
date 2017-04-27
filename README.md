# HdQrcode-二维码扫描功能库

## 一、概述
封装此库的目的是简化开发二维码导览功能的工作量，设计原理类似ImagePicker，将二维码扫描功能单独封装，调用时可配置相关参数修改UI上的元素，扫描成功或失败时进行回调。

## 二、版本
已上传jCenter，最新版本0.0.1，直接在gradle中添加即可。
compile 'com.hengda.zwf:HdQrcode:0.0.1'

## 三、效果
![](http://oksdjdocc.bkt.clouddn.com/17-4-27/21584263-file_1493291552849_1da8.png)

## 四、使用
#### 1）启动二维码扫描

```
            ScanConfig scanConfig = new ScanBuilder()
                    .setTitle(R.string.title_scan)
                    .setScanTip(R.string.qrcode_scan_tip)
                    .create();
            ScannerActivity.gotoActivity(MainActivity.this, scanConfig);
```

#### 2）UI上可配置元素

```
    private int title;//标题
    private int scanTip;//扫描提示
    private int toolbarColor;//toolbar颜色
    private int laserColor;//扫描线颜色
    private int mediaResId;//扫描成功声音
    private int frameMarginTop;//扫描框上边距
    private int frameSizeWidth;//扫描框宽度
    private int frameSizeHeight;//扫描框高度
    private int frameCornerLength;//角上短线长度
    private int laserLineHeight;//扫描线高度
```

#### 3）扫描成功

```
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != Activity.RESULT_CANCELED && resultCode == Activity.RESULT_OK) {
            if (requestCode == ScannerActivity.REQUEST_CODE_SCANNER) {
                if (data != null) {
                    String stringExtra = data.getStringExtra(Intents.Scan.RESULT);
                    tvHello.setText(stringExtra);
                }
            }
        }
    }
```

详细用法参见demo，项目地址：https://git.oschina.net/tailyou/HD_Frame_QrcodeScan

