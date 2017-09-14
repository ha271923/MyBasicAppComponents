package sample.hawk.com.mybasicappcomponents.graphic.utils;

import android.app.WallpaperManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.drawable.Drawable;

import sample.hawk.com.mybasicappcomponents.utils.SMLog;

/**
 * Created by ha271 on 2017/8/25..
 *
    JAVA有 reference)這個特性，如果沒有小心使用，像︰ 
      Bitmap使用後沒有recycle()、
      Drawable使用後沒有setCallback(null)⋯
    都會容易出現OutOfMemoryException而導致程式出錯。
 */

public class DrawableUtils2 {

    private static Context mContext;

    public static void setContext(Context context){ // mContext may memory leakage if Utils keep this reference always.
        mContext = context;
    }

    public static Drawable copyDrawable(Drawable sourceDrawable){
        return sourceDrawable.getConstantState().newDrawable().mutate();
    }

    private static Bitmap createEmptyFullScreenBitmap() {
        Point ptScreenSize = ImageUtils.getDisplaySize(mContext, true, true);
        return BitmapUtils.createBitmapSafely(ptScreenSize.x, ptScreenSize.y, Bitmap.Config.ARGB_8888);
    }

    public static Bitmap getBackgroundBitmap(Drawable customizedBackground) {
        Bitmap bitmap = createEmptyFullScreenBitmap();
        Canvas canvas = new Canvas(bitmap);

        //draw Full wallpaper
        drawWallpaperBitmap(canvas, customizedBackground);

        //scale down bitmap;
        int newWidth = (int) (bitmap.getWidth() * BFeedBlurBuilder.BITMAP_SCALE);
        int newHeight = (int) (bitmap.getHeight() * BFeedBlurBuilder.BITMAP_SCALE);
        Bitmap scaledBitmap = Bitmap.createScaledBitmap(bitmap, newWidth, newHeight, false);
        bitmap.recycle();
        return scaledBitmap;
    }

    private static void drawWallpaperBitmap(Canvas canvas, Drawable customizedBackground) {
        WallpaperManager wallpaperManager = WallpaperManager.getInstance(mContext);
        if (wallpaperManager.getWallpaperInfo() != null) {
            SMLog.d("current use live wallpaper");
            return;
        }
        Drawable drawable = customizedBackground == null ? wallpaperManager.getDrawable() : customizedBackground;
        int canvasHeight = canvas.getHeight();
        int canvasWidth = canvas.getWidth();
        int height = drawable.getIntrinsicHeight();
        int width = drawable.getIntrinsicWidth();
        if (height > width) {
            float scale;
            scale = (float) canvasWidth / (float) width;
            width = canvasWidth;
            height = (int) (height * scale);
        } else {
            float scale;
            scale = (float) canvasHeight / (float) height;
            width = (int) (width * scale);
            height = canvasHeight;
        }
        int top = 0;
        int left = (int) ((canvasWidth - width) / 2.0f);
        int right = left + width;
        int bottom = top + height;
        drawable.setBounds(left, top, right, bottom);
        drawable.draw(canvas);
        wallpaperManager.forgetLoadedWallpaper();
    }

}
