package sample.hawk.com.mybasicappcomponents.graphic.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.LightingColorFilter;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ScaleDrawable;
import android.graphics.drawable.TransitionDrawable;

import sample.hawk.com.mybasicappcomponents.R;
import sample.hawk.com.mybasicappcomponents.utils.SMLog;

import static sample.hawk.com.mybasicappcomponents.graphic.utils.BitmapUtils.createBitmapSafely;

/**
 * Created by ha271 on 2017/9/4.
 */

public class DrawableUtils {
    private static final String LOG_TAG = "DrawableUtils";

    public static TransitionDrawable transitionDrawable(Drawable currentBG, Drawable newBG ) {
        TransitionDrawable transitionDrawable = new TransitionDrawable(new Drawable[]{currentBG, newBG});
        transitionDrawable.setCrossFadeEnabled(true);
        return transitionDrawable;
    }

    // Applied on Background test PASSED!
    public static Drawable moveDrawable1(Drawable drawable, int x, int y) {
        Rect bounds = drawable.copyBounds(); // return four points actually position in View.
        bounds.left += x;
        bounds.top += y;
        bounds.right += x;
        bounds.bottom += y;
        drawable.setBounds(bounds);
        return drawable;
    }

    public static Drawable shiftDrawable(Drawable drawable, int x) {
        Rect bounds = drawable.copyBounds();
        if ((bounds.right == 0) && (bounds.left == 0)) // Drawables have no dimensions unless they've been drawn.
            return drawable;
        bounds.left = x;
        bounds.right = x + drawable.getIntrinsicWidth();
        drawable.setBounds(bounds);
        return drawable;
    }

    public static Drawable moveDrawable(Drawable drawable, int x, int y) {
        Rect bounds = drawable.copyBounds(); // return four points actually position in View.
        if ((bounds.right == 0) && (bounds.left == 0)) // Drawables don't have dimensions unless they've been drawn.
            return drawable;
        bounds.left = x;
        bounds.top = y;
        bounds.right = x + drawable.getIntrinsicWidth();
        bounds.bottom = y + drawable.getIntrinsicHeight();
        drawable.setBounds(bounds);
        return drawable;
    }

    // Applied on Background test PASSED!
    public static Drawable resizeDrawable(Drawable drawable, int x, int y) {
        // copyBounds() and getBounds() returns (0,0,0,0) if Drawables don't have dimensions unless they've been drawn.
        Rect bounds = drawable.copyBounds(); // return four points actually position in View.
        // Rect bounds = drawable.getBounds(); // Return (0,0,0,0) if Drawables don't have dimensions unless they've been drawn.
        bounds.left += x;
        bounds.right -= x;
        bounds.top += y;
        bounds.bottom += y;
        drawable.setBounds(bounds);
        return drawable;
    }


    public static Drawable scaleDrawable(Drawable drawable, int gravity, float scaleWidth, float scaleHeight ){
        ScaleDrawable scaleDrawable = new ScaleDrawable(drawable, gravity, scaleWidth, scaleHeight);
        return scaleDrawable;
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

    public static Drawable getColoredDrawable(Context context, boolean isworking) {
        Drawable d = context.getResources().getDrawable(R.drawable.android_robot);
        ColorFilter filter = new LightingColorFilter(
                isworking ? Color.GREEN : Color.RED,
                isworking ? Color.GREEN : Color.RED);
        d.setColorFilter(filter);
        return d;
    }

    /*
    private static Drawable cropDrawable(){
        try{

            Paint paint = new Paint();
            paint.setFilterBitmap(true);
            Bitmap bitmapOrg = BitmapFactory.decodeResource(getResources(),R.drawable.image);

            int targetWidth  = 300;
            int targetHeight = 300;


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

            part1.setBackgroundDrawable(bd);

        }
        catch(Exception e){
            System.out.println("Error1 : " + e.getMessage() + e.toString());
        }
    }
*/
/*
    private Drawable cropDrawable(Drawable drawable, int pixelToShift){
        Bitmap bitmapSource = ((BitmapDrawable)drawable).getBitmap();
        Bitmap bitmapResized = Bitmap.createBitmap(bitmapSource, 0, 0, pixelToShift, drawable.getIntrinsicHeight());
        return new BitmapDrawable(getApplicationContext().getResources(), bitmapResized);
    }
*/

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
