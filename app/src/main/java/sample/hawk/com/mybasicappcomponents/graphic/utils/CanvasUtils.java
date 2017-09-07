package sample.hawk.com.mybasicappcomponents.graphic.utils;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

/**
 * Created by ha271 on 2017/9/4.
 */

public class CanvasUtils {

    public static void drawCropRect(Canvas canvas, RectF bounds) {
        Paint p = new Paint();
        p.setStyle(Paint.Style.STROKE);
        p.setColor(Color.RED);
        p.setStrokeWidth(3);
        canvas.drawRect(bounds, p);
    }
}
