package com.hengda.hdqrcode;

import android.Manifest;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.google.zxing.Result;
import com.google.zxing.client.result.ParsedResult;
import com.google.zxing.client.result.ParsedResultType;
import com.google.zxing.client.result.TextParsedResult;
import com.google.zxing.client.result.URIParsedResult;
import com.hengda.zwf.hdscanner.ScanActivity;
import com.hengda.zwf.hdscanner.ScanConfig;
import com.hengda.zwf.hdscanner.ScanConfigBuilder;
import com.mylhyl.zxing.scanner.OnScannerCompletionListener;

import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.RuntimePermissions;

@RuntimePermissions
public class MainActivity extends AppCompatActivity {

    private android.widget.TextView tvHello;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.tvHello = (TextView) findViewById(R.id.tvHello);
        tvHello.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivityPermissionsDispatcher.startScannerWithCheck(MainActivity.this);
            }
        });
    }

    @NeedsPermission(Manifest.permission.CAMERA)
    void startScanner() {
        ScanConfig scanConfig = new ScanConfigBuilder()
                .setTitle(R.string.title_scan)
                .setScanTip(R.string.qrcode_scan_tip)
                .create();
        OnScannerCompletionListener listener = new OnScannerCompletionListener() {
            @Override
            public void OnScannerCompletion(Result rawResult, ParsedResult parsedResult, Bitmap barcode) {
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
                        tvHello.setText(uri.getURI());
                        ScanActivity.sInstance.finish();
                        break;
                    case TEXT:
                        TextParsedResult textParsedResult = (TextParsedResult) parsedResult;
                        tvHello.setText(textParsedResult.getText());
                        ScanActivity.sInstance.finish();
                        break;
                    case GEO:
                        break;
                    case TEL:
                        break;
                    case SMS:
                        break;
                }
            }
        };
        ScanActivity.gotoActivity(MainActivity.this, scanConfig, listener);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        MainActivityPermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
    }

}
