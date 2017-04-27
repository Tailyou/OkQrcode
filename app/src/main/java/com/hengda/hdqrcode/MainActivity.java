package com.hengda.hdqrcode;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.hengda.zwf.hdscanner.ScanBuilder;
import com.hengda.zwf.hdscanner.ScanConfig;
import com.hengda.zwf.hdscanner.ScannerActivity;
import com.mylhyl.zxing.scanner.common.Intents;

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
                openQrcodeScanner();
            }
        });
    }

    private void openQrcodeScanner() {
        if (ContextCompat.checkSelfPermission(MainActivity.this,
                Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            //权限还没有授予，需要在这里写申请权限的代码
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.CAMERA}, 60);
        } else {
            //权限已经被授予，在这里直接写要执行的相应方法即可
            ScanConfig scanConfig = new ScanBuilder()
                    .setTitle(R.string.title_scan)
                    .setScanTip(R.string.qrcode_scan_tip)
                    .create();
            ScannerActivity.gotoActivity(MainActivity.this, scanConfig);
        }
    }

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

}
