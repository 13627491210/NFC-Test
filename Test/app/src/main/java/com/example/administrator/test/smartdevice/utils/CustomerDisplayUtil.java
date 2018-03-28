package com.example.administrator.test.smartdevice.utils;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by hantao on 2017/9/9.
 */

public class CustomerDisplayUtil {
    public static Bitmap getBitmap(String ss) {
        Bitmap bitmap = Bitmap.createBitmap(480, 272, Bitmap.Config.RGB_565);
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setTextSize(30);
        canvas.drawColor(Color.WHITE);
        canvas.drawText(ss, 10, 30, paint);
        canvas.drawText(ss,10,60,paint);

        return bitmap;
    }
    public static Bitmap getBitmap(String[] ss, int texSize, int x, int y) {
        Bitmap bitmap = Bitmap.createBitmap(480, 272, Bitmap.Config.RGB_565);

        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setTextSize(texSize);
        canvas.drawColor(Color.WHITE);
        for (int i=0;i <ss.length;i++)
        {
            canvas.drawText(ss[i], x, (i*texSize)+30, paint);
        }
//        canvas.drawText(ss, 10, 10, paint);
//        canvas.drawText(ss,10,40,paint);

        return bitmap;
    }

}
