package sample.hawk.com.mybasicappcomponents.graphic.utils;

import android.app.WallpaperManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.view.View;

import java.lang.ref.WeakReference;
import java.util.concurrent.Executor;

import sample.hawk.com.mybasicappcomponents.utils.logger2.Logger;



public class BFeedBlurBuilder {
    private static final String LOG_TAG = BFeedBlurBuilder.class.getSimpleName();

    public static final float BITMAP_SCALE = 0.55f;
    private static final float BLUR_RADIUS = 20.0f;
    private static Drawable m_blurWallpaper = null;
    private static Context mContext;

    public interface BlurCallback {
        public void onBlurCompleted(Bitmap blurredBitmap);
    }

    public static Bitmap realBlur(Context ctx, Bitmap inputBitmap) {
        Bitmap outputBitmap = Bitmap.createBitmap(inputBitmap.getWidth(), inputBitmap.getHeight(), Bitmap.Config.ARGB_8888);

        RenderScript rs = RenderScript.create(ctx);
        ScriptIntrinsicBlur intrinsicBlur = ScriptIntrinsicBlur.create(rs, Element.U8_4(rs));
        Allocation tmpIn = Allocation.createFromBitmap(rs, inputBitmap);
        Allocation tmpOut = Allocation.createFromBitmap(rs, outputBitmap);
        intrinsicBlur.setRadius(BLUR_RADIUS);
        intrinsicBlur.setInput(tmpIn);
        intrinsicBlur.forEach(tmpOut);
        tmpOut.copyTo(outputBitmap);
        rs.destroy();
        return outputBitmap;
    }

    public static Bitmap getScreenshot(View v) {
        Bitmap b = Bitmap.createBitmap(v.getMeasuredWidth(),
                v.getMeasuredHeight(), Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(b);
        v.draw(c);
        return b;
    }

    public static void blurAsync(Context context, Bitmap bitmap, BlurCallback blurCallback) {
        blurAsync(context, bitmap, blurCallback, AsyncTask.THREAD_POOL_EXECUTOR);
    }

    public static void blurAsync(final Context context, Bitmap bitmap, BlurCallback blurCallback, Executor executor) {
        if (context == null) {
            Logger.d(LOG_TAG, "blurAsync: wrong params: %s", context);
            return;
        } else {
            mContext = context;
        }

        final WeakReference<BlurCallback> blurCallbackRef = new WeakReference<>(blurCallback);
        Logger.d(LOG_TAG, ">> blurAsync: %d", Thread.currentThread().getId());
        new AsyncTask<Bitmap, Void, Bitmap>() {
            @Override
            protected Bitmap doInBackground(Bitmap... params) {
                Bitmap bitmap = params[0];
                Logger.d(LOG_TAG, ">> blurAsync: background %d", Thread.currentThread().getId());

                Drawable d = bitmap == null ? null : new BitmapDrawable(bitmap);
                bitmap = getBackgroundBitmap(d);

                if (bitmap == null || bitmap.isRecycled()) {
                    Logger.w(LOG_TAG, "blurAsync: recycled bitmap %s", bitmap);
                    return null;
                }
                Bitmap result = null;
                try {
                    result = BFeedBlurBuilder.realBlur(context, bitmap);
                } catch (Exception e) {
                    Logger.w(LOG_TAG, "blurAsync: blur failed", e);
                }
                Logger.d(LOG_TAG, "<< blurAsync: background %d", Thread.currentThread().getId());
                return result;
            }

            @Override
            protected void onPostExecute(Bitmap result) {
                super.onPostExecute(result);
                Logger.d(LOG_TAG, ">> blurAsync: postExe %d", Thread.currentThread().getId());
                m_blurWallpaper = new BitmapDrawable(result);
                final BlurCallback callback = blurCallbackRef.get();
                if (callback != null) {
                    callback.onBlurCompleted(result);
                } else {
                    if (result != null && !result.isRecycled()) {
                        result.recycle();
                    }
                }
                Logger.d(LOG_TAG, "<< blurAsync: postExe %d", Thread.currentThread().getId());
            }
        }.executeOnExecutor(executor, bitmap);
        Logger.d(LOG_TAG, "<< blur %d", Thread.currentThread().getId());
    }

    private static Bitmap createEmptyFullScreenBitmap() {
        Point ptScreenSize = ImageUtils.getDisplaySize(mContext, true, true);
        return BitmapUtils.createBitmapSafely(ptScreenSize.x, ptScreenSize.y, Bitmap.Config.ARGB_8888);
    }

    public static Bitmap getBackgroundBitmap(Drawable customizedBackground) {
        Bitmap bitmap = createEmptyFullScreenBitmap();
        Canvas canvas = new Canvas(bitmap);

        //draw wallpaper
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
        int nStatusBarHeight = ImageUtils.getStatusBarHeight(mContext);
        if (wallpaperManager.getWallpaperInfo() != null) {
            Logger.d(LOG_TAG, "current use live wallpaper");
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
            height = (int) (height * scale) + nStatusBarHeight;
        } else {
            float scale;
            scale = (float) canvasHeight / (float) height;
            width = (int) (width * scale);
            height = canvasHeight;
        }
        int top = 0 - nStatusBarHeight;
        int left = (int) ((canvasWidth - width) / 2.0f);
        int right = left + width;
        int bottom = top + height;
        drawable.setBounds(left, top, right, bottom);
        drawable.draw(canvas);
        wallpaperManager.forgetLoadedWallpaper();
    }
}
