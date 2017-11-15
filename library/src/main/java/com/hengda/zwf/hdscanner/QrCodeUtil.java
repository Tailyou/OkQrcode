package com.hengda.zwf.hdscanner;

import android.content.Context;
import android.graphics.Bitmap;
import android.text.TextUtils;

import com.google.zxing.client.result.ParsedResultType;
import com.mylhyl.zxing.scanner.encode.QREncode;

/**
 * 作者：祝文飞（Tailyou）
 * 邮箱：tailyou@163.com
 * 时间：2017/11/15 15:08
 * 描述：二维码生成工具
 */
public class QrCodeUtil {

    public static Bitmap genQrCode(Context context, String qrContent, int margin, int size, int color) {
        Bitmap bitmap = new QREncode.Builder(context)
                .setColor(context.getResources().getColor(color))
                .setMargin(margin)
                .setParsedResultType(TextUtils.isEmpty(qrContent) ? ParsedResultType.URI : ParsedResultType.TEXT)
                .setContents(TextUtils.isEmpty(qrContent) ? "https://github.com/Tailyou" : qrContent)
                .setSize(size)
                .build()
                .encodeAsBitmap();
        return bitmap;
    }

}
