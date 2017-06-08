package sample.hawk.com.mybasicappcomponents.utils;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.AsyncTask;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;


public class BlurBuilder {
    private static final float BITMAP_SCALE = 0.55f;
    private static final float BLUR_RADIUS = 20.0f;

    public interface AsyncResponse {
        void processFinish(Bitmap output);
    }

    public static Bitmap scriptBlur(Context ctx, Bitmap image) {
        int width = Math.round(image.getWidth() * BITMAP_SCALE);
        int height = Math.round(image.getHeight() * BITMAP_SCALE);

        Bitmap inputBitmap = Bitmap.createScaledBitmap(image, width, height, false);
        Bitmap outputBitmap = Bitmap.createBitmap(inputBitmap);

        RenderScript rs = RenderScript.create(ctx);
        ScriptIntrinsicBlur intrinsicBlur = ScriptIntrinsicBlur.create(rs, Element.U8_4(rs));
        Allocation tmpIn = Allocation.createFromBitmap(rs, inputBitmap);
        Allocation tmpOut = Allocation.createFromBitmap(rs, outputBitmap);
        intrinsicBlur.setRadius(BLUR_RADIUS);
        intrinsicBlur.setInput(tmpIn);
        intrinsicBlur.forEach(tmpOut);
        tmpOut.copyTo(outputBitmap);

        return outputBitmap;
    }


    public static void asyncBlur(final Context context, final Bitmap bitmap,
                                 final ImageView view, final AnimatorSet afterAnimeSet) {
        if (bitmap == null) {
            SMLog.d("blur without bitmap");
            return;
        }

        final int bitmapHeight = bitmap.getHeight();
        final int bitmapWidth = bitmap.getWidth();
        final int viewHeight =
                (view.getMeasuredHeight() < bitmapHeight ? bitmapHeight : view.getMeasuredHeight());
        final int viewWidth =
                (view.getMeasuredWidth() < bitmapWidth ? bitmapWidth : view.getMeasuredWidth());
        new AsyncTask<Void, Void, Bitmap>() {
            @Override
            protected Bitmap doInBackground(Void... params) {
                if (bitmap.isRecycled()) {
                    SMLog.w("recycled bitmap !!!!");
                    return null;
                }
                Bitmap cropImage = Bitmap.createBitmap(bitmap, 0, viewHeight - bitmapHeight,
                        bitmapWidth, viewHeight);

                return BlurBuilder.scriptBlur(context, cropImage);
            }

            @Override
            protected void onPostExecute(Bitmap bitmap) {
                super.onPostExecute(bitmap);
                if (bitmap != null) {
                    ColorDrawable dim = new ColorDrawable(0xcc141414);
                    ColorDrawable darkGray =
                            new ColorDrawable(context.getResources().getColor(android.R.color.darker_gray));
                    LayerDrawable layerDrawable =
                            new LayerDrawable(new Drawable[]{darkGray,
                                    new BitmapDrawable(context.getResources(), bitmap), dim});

                    view.setImageDrawable(layerDrawable);

                    if (afterAnimeSet != null) {
                        afterAnimeSet.start();
                        ArrayList<Animator> animators = afterAnimeSet.getChildAnimations();
                        for (Animator animator : animators) {
                            if (!(animator instanceof ObjectAnimator)) {
                                continue;
                            }
                            Object view = ((ObjectAnimator)animator).getTarget();
                            if (view instanceof LinearLayout) {
                                LinearLayout layout = (LinearLayout) view;
                                layout.setVisibility(View.VISIBLE);
                            }
                        }

                    }
                }
            }
        }.execute();
    }

    public static void asyncBlur(final Context context, final Bitmap bitmap, final AsyncResponse callback) {
        if (bitmap == null) {
            SMLog.d("blur without bitmap");
        }

        final int bitmapHeight = bitmap.getHeight();
        final int bitmapWidth = bitmap.getWidth();
        new AsyncTask<Void, Void, Bitmap>() {
            @Override
            protected Bitmap doInBackground(Void... params) {
                if (bitmap.isRecycled()) {
                    SMLog.w("recycled bitmap !!!!");
                    return null;
                }
                Bitmap cropImage = Bitmap.createBitmap(bitmap, 0, 0, bitmapWidth, bitmapHeight);

                return BlurBuilder.scriptBlur(context, cropImage);
            }

            @Override
            protected void onPostExecute(Bitmap bluredBitmap) {
                super.onPostExecute(bluredBitmap);
                callback.processFinish(bluredBitmap);
            }
        }.execute();
    }

}
