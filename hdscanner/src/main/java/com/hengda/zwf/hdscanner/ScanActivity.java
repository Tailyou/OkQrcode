package com.hengda.zwf.hdscanner;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.zxing.Result;
import com.google.zxing.client.result.ParsedResult;
import com.google.zxing.client.result.ParsedResultType;
import com.google.zxing.client.result.TextParsedResult;
import com.google.zxing.client.result.URIParsedResult;
import com.mylhyl.zxing.scanner.OnScannerCompletionListener;
import com.mylhyl.zxing.scanner.ScannerView;
import com.mylhyl.zxing.scanner.common.Intents;

/**
 * 扫描界面
 *
 * @author 祝文飞（Tailyou）
 * @time 2017/4/27 17:22
 */
public class ScanActivity extends AppCompatActivity implements OnScannerCompletionListener {

    RelativeLayout toolbar;
    TextView tvTitle;
    ImageView ivBack;
    ScannerView scannerView;
    public static final int REQUEST_CODE_SCANNER = 188;
    public static final String SCAN_CONFIG = "SCAN_CONFIG";
    public static final String TAG = ScanActivity.class.getSimpleName();
    private Result mLastResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan);

        ivBack = (ImageView) findViewById(R.id.ivBack);
        scannerView = (ScannerView) findViewById(R.id.scanner_view);
        toolbar = (RelativeLayout) findViewById(R.id.toolbar);
        tvTitle = (TextView) findViewById(R.id.tvTitle);

        ScanConfig scanConfig = getIntent().getParcelableExtra(SCAN_CONFIG);
        tvTitle.setText(scanConfig.getTitle());
        toolbar.setBackgroundColor(scanConfig.getToolbarColor());
        StatusBarUtil.setColorNoTranslucent(ScanActivity.this, scanConfig.getToolbarColor());
        scannerView.setLaserFrameBoundColor(scanConfig.getLaserColor());
        scannerView.setLaserColor(scanConfig.getLaserColor());
        scannerView.setMediaResId(scanConfig.getMediaResId());
        scannerView.setLaserFrameTopMargin(scanConfig.getFrameMarginTop());
        scannerView.setLaserFrameSize(scanConfig.getFrameSizeWidth(), scanConfig.getFrameSizeHeight());
        scannerView.setLaserFrameCornerLength(scanConfig.getFrameCornerLength());
        scannerView.setLaserLineHeight(scanConfig.getLaserLineHeight());
        scannerView.setDrawText(getString(scanConfig.getScanTip()), 16, Color.WHITE, true, 28);
        scannerView.setOnScannerCompletionListener(this);
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    protected void onResume() {
        scannerView.onResume();
        resetStatusView();
        super.onResume();
    }

    @Override
    protected void onPause() {
        scannerView.onPause();
        super.onPause();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                if (mLastResult != null) {
                    restartPreviewAfterDelay(0L);
                    return true;
                }
                break;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void restartPreviewAfterDelay(long delayMS) {
        scannerView.restartPreviewAfterDelay(delayMS);
        resetStatusView();
    }

    private void resetStatusView() {
        mLastResult = null;
    }

    @Override
    public void OnScannerCompletion(Result rawResult, ParsedResult parsedResult, Bitmap barcode) {
        if (rawResult == null) {
            Log.d(TAG, "qrcode error");
            finish();
            return;
        }
        ParsedResultType type = parsedResult.getType();
        switch (type) {
            case ADDRESSBOOK:
                break;
            case PRODUCT:
                break;
            case ISBN:
                break;
            case URI:
                URIParsedResult uri = (URIParsedResult) parsedResult;
                Log.i(TAG, "uri: " + uri.getURI());
                scanSuccess(uri.getURI());
                break;
            case TEXT:
                TextParsedResult textParsedResult = (TextParsedResult) parsedResult;
                Log.i(TAG, "text: " + textParsedResult.getText());
                scanSuccess(textParsedResult.getText());
                break;
            case GEO:
                break;
            case TEL:
                break;
            case SMS:
                break;
        }
    }

    private void scanSuccess(String text) {
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putString(Intents.Scan.RESULT, text);
        intent.putExtras(bundle);
        setResult(RESULT_OK, intent);
        finish();
    }

    public static void gotoActivity(Activity activity, ScanConfig scanConfig) {
        Intent intent = new Intent(Intents.Scan.ACTION).putExtra(SCAN_CONFIG, scanConfig);
        activity.startActivityForResult(intent, REQUEST_CODE_SCANNER);
    }

}
