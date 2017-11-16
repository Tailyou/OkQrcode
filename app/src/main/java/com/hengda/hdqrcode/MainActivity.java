package com.hengda.hdqrcode;

import android.Manifest;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.Result;
import com.google.zxing.client.result.ParsedResult;
import com.google.zxing.client.result.ParsedResultType;
import com.google.zxing.client.result.TextParsedResult;
import com.hengda.zwf.hdscanner.QrCodeUtil;
import com.hengda.zwf.hdscanner.ScanActivity;
import com.hengda.zwf.hdscanner.ScanConfig;
import com.hengda.zwf.hdscanner.ScanConfigBuilder;
import com.mylhyl.zxing.scanner.OnScannerCompletionListener;

import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.RuntimePermissions;

@RuntimePermissions
public class MainActivity extends AppCompatActivity {

    private TextView tvHello;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvHello = (TextView) findViewById(R.id.tvHello);
        imageView = (ImageView) findViewById(R.id.imageView);
        tvHello.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivityPermissionsDispatcher.startScannerWithCheck(MainActivity.this);
            }
        });
        imageView.setImageBitmap(QrCodeUtil.genQrCode(this, "222", 2, 112, R.color.colorAccent));
    }

    @NeedsPermission(Manifest.permission.CAMERA)
    void startScanner() {
        ScanConfig scanConfig = new ScanConfigBuilder()
                .setTitle(R.string.title_scan)
                .setScanTip(R.string.qrcode_scan_tip)
                .setToolbarColor(android.R.color.transparent)
                .create();
        OnScannerCompletionListener scanListener = new OnScannerCompletionListener() {
            @Override
            public void OnScannerCompletion(Result rawResult, ParsedResult parsedResult, Bitmap barcode) {
                ParsedResultType type = parsedResult.getType();
                switch (type) {
                    /*case ADDRESSBOOK:
                        break;
                    case PRODUCT:
                        break;
                    case ISBN:
                        break;
                    case URI:
                        ScanActivity.sInstance.finish();
                        URIParsedResult uri = (URIParsedResult) parsedResult;
                        tvHello.setText(uri.getURI());
                        break;*/
                    case TEXT:
                        //ScanActivity.sInstance.finish();
                        TextParsedResult textParsedResult = (TextParsedResult) parsedResult;
                        tvHello.setText(textParsedResult.getText());
                        Toast.makeText(MainActivity.this, textParsedResult.getText(), Toast.LENGTH_SHORT).show();
                        ScanActivity.scannerView.onResume();
                        break;
                    /*case GEO:
                        break;
                    case TEL:
                        break;
                    case SMS:
                        break;*/
                }
            }
        };
        //启动扫描界面，传入UI配置和扫描结束回调
        ScanActivity.gotoActivity(MainActivity.this, scanConfig, scanListener);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        MainActivityPermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
    }

}
