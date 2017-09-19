package sample.hawk.com.mybasicappcomponents.graphic.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;

import sample.hawk.com.mybasicappcomponents.R;

/**
 * http://www.cnblogs.com/liangstudyhome/p/4143498.html
 */

public class CanvasUtils {

    public static void drawCropRect(Canvas canvas, RectF bounds) {
        Paint p = new Paint();
        p.setStyle(Paint.Style.STROKE);
        p.setColor(Color.RED);
        p.setStrokeWidth(3);
        canvas.drawRect(bounds, p);
    }

    public static void drawArraw(Canvas canvas) {
        Paint paintBkg = new Paint();
        paintBkg.setColor(Color.GRAY);

        Paint paintLine = new Paint();
        paintLine.setStrokeWidth(4);
        paintLine.setColor(Color.RED);

        int px = 500;
        int py = 500;

        canvas.drawRect(0, 0, px, py, paintBkg);
        canvas.save();
        canvas.rotate(90, px / 2, py / 2); // 畫布轉90度, 用來畫一個向上的箭頭
        canvas.drawLine(px / 2, 0, 0, py / 2, paintLine); // 左邊的斜線
        canvas.drawLine(px / 2, 0, px, py / 2, paintLine);// 右邊的斜線
        canvas.drawLine(px / 2, 0, px / 2, py, paintLine);// 垂直的線
        canvas.restore(); //
        canvas.drawCircle(px - 100, py - 100, 50, paintLine);
    }


    public static void drawLayer(Canvas canvas) {
        final int LAYER_FLAGS = Canvas.MATRIX_SAVE_FLAG | Canvas.CLIP_SAVE_FLAG
                | Canvas.HAS_ALPHA_LAYER_SAVE_FLAG | Canvas.FULL_COLOR_LAYER_SAVE_FLAG
                | Canvas.CLIP_TO_LAYER_SAVE_FLAG;
        Paint paint;
        paint = new Paint();
        paint.setAntiAlias(true);
        canvas.drawColor(Color.WHITE);
        canvas.translate(10, 10);
        paint.setColor(Color.RED);
        canvas.drawCircle(75, 75, 75, paint);
        canvas.saveLayerAlpha(0, 0, 200, 200, 0x88, LAYER_FLAGS);
        paint.setColor(Color.BLUE);
        canvas.drawCircle(125, 125, 75, paint);
        canvas.restore();
    }


    public static void drawImage(Context context, Canvas canvas){
        Drawable drawablePic;
        Bitmap bitmapPic;
        drawablePic = context.getResources().getDrawable(R.drawable.android_robot);
        bitmapPic = BitmapFactory.decodeResource(context.getResources(), R.drawable.android_robot);

        canvas.drawColor(Color.WHITE);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(120, 40, 30, paint);
        canvas.drawRect(90,170,150,200, paint);
        drawablePic.setBounds(10, 10, 100, 100);
        drawablePic.draw(canvas);

        drawablePic.setBounds(120, 120, 200, 200);
        drawablePic.draw(canvas);

        canvas.drawBitmap(bitmapPic, 0, 200, null);
    }
}
