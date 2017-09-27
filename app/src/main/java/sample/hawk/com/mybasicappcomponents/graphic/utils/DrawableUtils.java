package sample.hawk.com.mybasicappcomponents.graphic.utils;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.LightingColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;

import java.io.ByteArrayOutputStream;

import sample.hawk.com.mybasicappcomponents.R;
import sample.hawk.com.mybasicappcomponents.utils.SMLog;

import static sample.hawk.com.mybasicappcomponents.graphic.utils.BitmapUtils.createBitmapSafely;

/**
 * Created by ha271 on 2017/8/25..
 *
    JAVA有 reference)這個特性，如果沒有小心使用，像︰ 
      Bitmap使用後沒有recycle()、
      Drawable使用後沒有setCallback(null)⋯
    都會容易出現OutOfMemoryException而導致程式出錯。
 */

public class DrawableUtils {
    private static final String LOG_TAG = "DrawableUtils";

    private static boolean isDrawed(Drawable drawable){
        Rect bounds = drawable.copyBounds();
        if ((bounds.right == 0) && (bounds.left == 0) &&
            (bounds.top   == 0) && (bounds.bottom == 0)) // Drawables have no dimensions unless they've been drawn.
            return false;
        return true;
    }


    // Applied on Background test PASSED! like an animation
    public static TransitionDrawable transitionDrawable(Drawable currentBG, Drawable newBG ) {
        TransitionDrawable transitionDrawable = new TransitionDrawable(new Drawable[]{currentBG, newBG});
        transitionDrawable.setCrossFadeEnabled(true);
        return transitionDrawable;
    }

    // Applied on Background test PASSED!
    public static Drawable shiftDrawable(Drawable drawable, int x) {
        Rect bounds = drawable.copyBounds();
        if (isDrawed(drawable) != true) // Drawables have no dimensions unless they've been drawn.
            return drawable;
        bounds.left = x;
        bounds.right = x + drawable.getIntrinsicWidth();
        drawable.setBounds(bounds);
        return drawable;
    }

    // Applied on Background test PASSED!
    public static Drawable moveDrawable(Drawable drawable, int x, int y) {
        Rect bounds = drawable.copyBounds(); // return four points actually position in View.
        if (isDrawed(drawable) != true) // Drawables have no dimensions unless they've been drawn.
            return drawable;
        bounds.left = x + bounds.left;
        bounds.top = y + bounds.top;
        bounds.right = x + bounds.right;
        bounds.bottom = y + bounds.bottom;
        drawable.setBounds(bounds);
        return drawable;
    }

    // Applied on Background test PASSED!
    public static Drawable resizeDrawable(Drawable drawable, int x, int y) {
        // copyBounds() and getBounds() returns (0,0,0,0) if Drawables don't have dimensions unless they've been drawn.
        Rect bounds = drawable.copyBounds(); // return four points actually position in View.
        // Rect bounds = drawable.getBounds(); // Return (0,0,0,0) if Drawables don't have dimensions unless they've been drawn.
        if (isDrawed(drawable) != true) // Drawables have no dimensions unless they've been drawn.
            return drawable;
        bounds.left = 0;
        bounds.right = x;
        bounds.top = 0;
        bounds.bottom = y;
        drawable.setBounds(bounds);
        return drawable;
    }

    // OK!
    public static Drawable scaleDrawable(Drawable drawable, int targetWidth, int targetHeight ){
        if (isDrawed(drawable) != true) // Drawables have no dimensions unless they've been drawn.
            return drawable;
        drawable.setBounds(0, 0, targetWidth, targetHeight);
        SMLog.i("Intrinsic: getIntrinsicWidth="+drawable.getIntrinsicWidth() +"   getIntrinsicHeight="+ drawable.getIntrinsicHeight());
        SMLog.i("Minimum:   getMinimumWidth="+drawable.getMinimumWidth() +"   getMinimumHeight="+ drawable.getMinimumHeight());
        return drawable;
    }

    public static Bitmap drawableToBitmap(Drawable drawable) {
        Bitmap bitmap = null;

        if (drawable instanceof BitmapDrawable) {
            BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
            if(bitmapDrawable.getBitmap() != null) {
                return bitmapDrawable.getBitmap();
            }
        }

        if(drawable.getIntrinsicWidth() <= 0 || drawable.getIntrinsicHeight() <= 0) {
            bitmap = Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888); // Single color bitmap will be created of 1x1 pixel
        } else {
            bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        }

        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return bitmap;
    }

    public static Bitmap drawableToBitmap2(Drawable drawable) {
        Bitmap bitmap = null;
        if (drawable != null) {
            if (drawable instanceof BitmapDrawable) {
                SMLog.i("drawableToBitmap2 from bitmapDrawable");
                BitmapDrawable bitmapD = (BitmapDrawable) drawable;
                bitmap = bitmapD.getBitmap();
            } else {
                SMLog.i("drawableToBitmap2 draw again");
                try {
                    bitmap = createBitmapSafely(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
                    Canvas canvas = new Canvas(bitmap);
                    drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
                    drawable.draw(canvas);
                } catch (Exception e) {
                    SMLog.i("drawableToBitmap2 draw fail"+ e);
                } catch (Error e) {
                    SMLog.i("drawableToBitmap2 draw fail"+ e);
                }
            }
        }
        return bitmap;
    }

    public static Drawable overlapDrawable(Context context, Drawable drawableBottom, Drawable drawableTop , int LeftShift){
        Bitmap bitmapBottom = drawableToBitmap(drawableBottom);
        Bitmap bitmapTop = drawableToBitmap(drawableTop);
        bitmapTop = BitmapUtils.cropBitmap(bitmapTop, LeftShift);

        Bitmap bitmapOverlap = Bitmap.createBitmap(bitmapBottom.getWidth(), bitmapBottom.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmapOverlap);
        canvas.drawBitmap(bitmapBottom, 0, 0, null);
        canvas.drawBitmap(bitmapTop, LeftShift, 0, null);

        return new BitmapDrawable(context.getResources(), bitmapOverlap);
    }

    public static Drawable changeDrawableColor(Drawable drawable, int newColor) {
        drawable.setColorFilter(new PorterDuffColorFilter(newColor, PorterDuff.Mode.SRC_IN));
        return drawable;
    }
    public static byte[] drawableToByteArray(Drawable drawable) {
        Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] bitmapdata = stream.toByteArray();
        return bitmapdata;
    }

    public static Drawable bytesToDrawable(Resources resources, byte[] imageBytes) {

        if (imageBytes != null)
            return new BitmapDrawable(resources, BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length));
        else
            return null;
    }

    public static Drawable getColoredDrawable(Context context, boolean isworking) {
        Drawable d = context.getResources().getDrawable(R.drawable.android_robot);
        ColorFilter filter = new LightingColorFilter(
                isworking ? Color.GREEN : Color.RED,
                isworking ? Color.GREEN : Color.RED);
        d.setColorFilter(filter);
        return d;
    }


    public static Drawable cropDrawable(Bitmap bitmapOrg, int targetWidth, int targetHeight){
        try{

            Paint paint = new Paint();
            paint.setFilterBitmap(true);

            Bitmap targetBitmap = Bitmap.createBitmap(targetWidth, targetHeight,Bitmap.Config.ARGB_8888);

            RectF rectf = new RectF(0, 0, 100, 100);

            Canvas canvas = new Canvas(targetBitmap);
            Path path = new Path();

            path.addRect(rectf, Path.Direction.CW);
            canvas.clipPath(path);

            canvas.drawBitmap( bitmapOrg, new Rect(0, 0, bitmapOrg.getWidth(), bitmapOrg.getHeight()),
                    new Rect(0, 0, targetWidth, targetHeight), paint);

            Matrix matrix = new Matrix();
            matrix.postScale(1f, 1f);
            Bitmap resizedBitmap = Bitmap.createBitmap(targetBitmap, 0, 0, 100, 100, matrix, true);

            BitmapDrawable bd = new BitmapDrawable(resizedBitmap);
            return bd;
        }
        catch(Exception e){
            System.out.println("Error1 : " + e.getMessage() + e.toString());
        }
        return null;
    }

    public static Drawable cropDrawable(Drawable drawable, int targetWidth, int targetHeight) {
        Bitmap bitmapSource = ((BitmapDrawable)drawable).getBitmap();
        Bitmap bitmapResized = Bitmap.createBitmap(bitmapSource, 0, 0, targetWidth, targetHeight);
        return new BitmapDrawable(bitmapResized);
    }


/*
    private Drawable cropDrawable(Drawable drawable, int pixelToShift){
    {
        int height = drawable.getIntrinsicHeight();
        int width = pixelToShift;
        Bitmap target= Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(target);
        float hScale = width/(float)drawable.getWidth();
        // float vScale = height/(float)source.getHeight();
        float scale = Math.min(hScale, vScale);
        Matrix matrix = new Matrix();
        matrix.setScale(scale, scale);
        matrix.postTranslate(width/2 - drawable.getWidth()/2 * scale, height/2 - source.getHeight()/2 * scale);
        canvas.drawColor(Color.BLACK);
        canvas.drawBitmap(source, matrix, new Paint());
    }
*/

}
