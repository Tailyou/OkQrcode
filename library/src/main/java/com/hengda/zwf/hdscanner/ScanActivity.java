package com.hengda.zwf.hdscanner;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mylhyl.zxing.scanner.OnScannerCompletionListener;
import com.mylhyl.zxing.scanner.ScannerView;
import com.mylhyl.zxing.scanner.common.Intents;

/**
 * 扫描界面
 *
 * @author 祝文飞（Tailyou）
 * @time 2017/4/27 17:22
 */
public class ScanActivity extends AppCompatActivity {

    RelativeLayout toolbar;
    TextView tvTitle;
    ImageView ivBack;
    ScannerView scannerView;
    private static final String SCAN_CONFIG = "SCAN_CONFIG";
    private static OnScannerCompletionListener scannerCompletionListener;
    public static ScanActivity sInstance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan);
        sInstance = ScanActivity.this;
        ScanConfig scanConfig = getIntent().getParcelableExtra(SCAN_CONFIG);

        toolbar = (RelativeLayout) findViewById(R.id.toolbar);
        toolbar.setBackgroundColor(scanConfig.getToolbarColor());
        StatusBarUtil.setColorNoTranslucent(ScanActivity.this, scanConfig.getToolbarColor());

        ivBack = (ImageView) findViewById(R.id.ivBack);
        ivBack.setColorFilter(scanConfig.getTitleColor());
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        tvTitle = (TextView) findViewById(R.id.tvTitle);
        tvTitle.setText(scanConfig.getTitle());
        tvTitle.setTextColor(scanConfig.getTitleColor());

        scannerView = (ScannerView) findViewById(R.id.scanner_view);
        scannerView.setLaserFrameBoundColor(scanConfig.getLaserColor());
        scannerView.setLaserColor(scanConfig.getLaserColor());
        scannerView.setMediaResId(scanConfig.getMediaResId());
        scannerView.setLaserFrameTopMargin(scanConfig.getFrameMarginTop());
        scannerView.setLaserFrameSize(scanConfig.getFrameSizeWidth(), scanConfig.getFrameSizeHeight());
        scannerView.setLaserFrameCornerLength(scanConfig.getFrameCornerLength());
        scannerView.setLaserLineHeight(scanConfig.getLaserLineHeight());
        scannerView.setDrawText(getString(scanConfig.getScanTip()), 16, Color.WHITE, true, 28);
        scannerView.setOnScannerCompletionListener(scannerCompletionListener);

    }

    @Override
    protected void onResume() {
        scannerView.onResume();
        super.onResume();
    }

    @Override
    protected void onPause() {
        scannerView.onPause();
        super.onPause();
    }

    public static void gotoActivity(Activity activity, ScanConfig scanConfig, OnScannerCompletionListener listener) {
        Intent intent = new Intent(Intents.Scan.ACTION).putExtra(SCAN_CONFIG, scanConfig);
        activity.startActivity(intent);
        scannerCompletionListener = listener;
    }

}
