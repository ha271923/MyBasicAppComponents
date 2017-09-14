package sample.hawk.com.mybasicappcomponents.graphic.utils;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;

import sample.hawk.com.mybasicappcomponents.utils.logger2.Logger;

/**
 * Created by ha271 on 2017/9/12.
 */

public class BitmapUtils2 {
    final private static String LOG_TAG = "BitmapUtils2";
    public enum WallpaperCenterScaleOption {
        TYPE_LEFT_CENTER_RATIO(1),
        TYPE_CENTER_RATIO(2),
        TYPE_RIGHT_CENTER_RATIO(3);

        public final int key;

        private WallpaperCenterScaleOption(int key) {
            this.key = key;
        }
    }
    /***
     * create a bitmap which follow rule of ImageView.ScaleType.CENTER_CROP based on the source bitmap
     *
     * suggest to run in bg thread
     *
     * @param  src the bitamp source which we want to create a bitmap from;
     *         dstWidth the width of returned bitmap;
     *         dstHeight the height of returned bitmap;
     *
     *         WallpaperCenterScaleOption the scale option to be center crop, right|center_vertical or left|center_vertical
     */

    public static Bitmap createCenterCroppedBitmap(Bitmap src, Point dstSize) {
        if (dstSize == null) {
            Logger.w(LOG_TAG, "createCenterCroppedBitmap no effect %s", dstSize);
            return src;
        }
        return createCenterCroppedBitmap(src, dstSize.x, dstSize.y, WallpaperCenterScaleOption.TYPE_CENTER_RATIO);
    }

    public static Bitmap createCenterCroppedBitmap(Bitmap src, Point dstSize, WallpaperCenterScaleOption option) {
        if (dstSize == null) {
            Logger.w(LOG_TAG, "createCenterCroppedBitmap no effect %s", dstSize);
            return src;
        }
        return createCenterCroppedBitmap(src, dstSize.x, dstSize.y, option);
    }

    public static Bitmap createCenterCroppedBitmap(Bitmap src, int dstWidth, int dstHeight) {
        return createCenterCroppedBitmap(src, dstWidth, dstHeight, WallpaperCenterScaleOption.TYPE_CENTER_RATIO);
    }

    public static Bitmap createCenterCroppedBitmap(Bitmap src, int dstWidth, int dstHeight, WallpaperCenterScaleOption option) {
        if (src == null)
            return null;

        if (dstWidth <= 0 || dstHeight <= 0) {
            Logger.w(LOG_TAG, "createCenterCroppedBitmap no effect %s/%s", dstWidth, dstHeight);
            return src;
        }

        int srcWidth = src.getWidth();
        int srcHeight = src.getHeight();

        if (dstWidth == srcWidth && dstHeight == srcHeight) {
            Logger.w(LOG_TAG, "createCenterCroppedBitmap no effect, dst size == src size %s/%s", dstWidth, dstHeight);
            return src;
        }

        Bitmap dst = Bitmap.createBitmap(dstWidth, dstHeight, Bitmap.Config.ARGB_8888);

        Canvas c = new Canvas(dst);
        c.drawColor(Color.WHITE);

        float aspectSrc = (float) src.getWidth() / src.getHeight();
        float aspectDst = (float) dstWidth / dstHeight;

        Logger.e(LOG_TAG, "src %s, %s, %s", aspectSrc, srcWidth, srcHeight);
        Logger.e(LOG_TAG, "dst %s, %s, %s", aspectDst, dstWidth, dstHeight);

        int scaledWidth = src.getWidth();
        int scaledHeight = src.getHeight();
        if (aspectSrc > aspectDst) {
            scaledWidth = (int) ((float) scaledHeight * aspectDst);
        } else {
            scaledHeight = (int) ((float) scaledWidth / aspectDst);
        }

        //by defaul WallpaperCenterScaleOption.TYPE_CENTER_RATIO
        int srcLeft = (src.getWidth() - scaledWidth) / 2;
        int srcTop = (src.getHeight() - scaledHeight) / 2;

        if (WallpaperCenterScaleOption.TYPE_LEFT_CENTER_RATIO.equals(option))
            srcLeft = 0;
        else if (WallpaperCenterScaleOption.TYPE_RIGHT_CENTER_RATIO.equals(option))
            srcLeft = src.getWidth() - scaledWidth;

        int srcRight = srcLeft + scaledWidth;
        int srcBottom = srcTop + scaledHeight;

        Rect rectSrc = new Rect(srcLeft, srcTop, srcRight, srcBottom);
        Rect rectDst = new Rect(0, 0, dstWidth, dstHeight);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setDither(true);
        c.drawBitmap(src, rectSrc, rectDst, paint);
        return dst;
    }

}
