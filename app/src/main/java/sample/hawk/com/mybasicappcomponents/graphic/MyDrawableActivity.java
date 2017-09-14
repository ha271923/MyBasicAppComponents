package sample.hawk.com.mybasicappcomponents.graphic;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ClipDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.TransitionDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;

import java.io.FileNotFoundException;

import sample.hawk.com.mybasicappcomponents.R;
import sample.hawk.com.mybasicappcomponents.graphic.utils.BitmapUtils;
import sample.hawk.com.mybasicappcomponents.graphic.utils.BlurBuilder;
import sample.hawk.com.mybasicappcomponents.graphic.utils.DrawableUtils;
import sample.hawk.com.mybasicappcomponents.graphic.utils.DrawableUtils2;
import sample.hawk.com.mybasicappcomponents.graphic.utils.ImageUtils;
import sample.hawk.com.mybasicappcomponents.utils.SMLog;


/**
 * Created by ha271 on 2017/8/28.
 */

// public class MyDrawableActivity extends Activity implements View.OnTouchListener, View.OnDragListener {
public class MyDrawableActivity extends Activity {
    Context mContext;
    static DisplayMetrics mDisplayMetrics;
    ImageView mImageView_org;
    ImageView mImageView_blur;
    Drawable mDrawable_org;
    Drawable mDrawable_blur;
    Uri source;
    Bitmap mBitmap_org;
    Bitmap mBitmap_blur;
    Drawable drawable_out = null;
    final int GET_IMAGE = 1;
    int pixelShift = 300;

    public  class BlurRangeSeekBar_Listener implements SeekBar.OnSeekBarChangeListener {

        public BlurRangeSeekBar_Listener(Activity activity) {
        }

        public final void onProgressChanged(SeekBar seekBar, int progress, boolean z) {
            pixelShift = progress;
            ColorDrawable colorDrawable = new ColorDrawable(Color.BLACK);
            Rect rect = new Rect();
            rect.top = 0;
            rect.left = 0;
            rect.right = mImageView_org.getWidth();
            rect.bottom = mImageView_org.getHeight();
            colorDrawable.setBounds(rect);
            drawable_out = overlapBlurDrawable(mDrawable_blur,colorDrawable, pixelShift);
            mImageView_org.setBackground(drawable_out);
        }

        public final void onStartTrackingTouch(SeekBar seekBar) {
        }

        public final void onStopTrackingTouch(SeekBar seekBar) {
        }

    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mydrawableactivity);
        mContext = this;
        mDisplayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(mDisplayMetrics);
        SMLog.i("StatusBarHeight="+ ImageUtils.getStatusBarHeight(this));
        mImageView_blur = (ImageView) findViewById(R.id.imageViewBlurred);
        mImageView_org = (ImageView) findViewById(R.id.imageViewOrigin);
        SeekBar blur_pixelrange_seekbar = (SeekBar) findViewById(R.id.drawable_seekbar);
        int width =  mDisplayMetrics.widthPixels;
        blur_pixelrange_seekbar.setMax(width);
        blur_pixelrange_seekbar.setProgress(pixelShift);
        blur_pixelrange_seekbar.setOnSeekBarChangeListener(new BlurRangeSeekBar_Listener(this ));

        ImageUtils.getImageFromAlbum(this, GET_IMAGE);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case GET_IMAGE:
                    source = data.getData();
                    try {
                        mBitmap_org = BitmapFactory
                                .decodeStream(getContentResolver().openInputStream( source));
                        mDrawable_org= new BitmapDrawable(mContext.getResources(), mBitmap_org);
                        BlurBuilder.asyncBlur(mContext, mBitmap_org, new BlurBuilder.AsyncResponse() {
                            @Override
                            public void processFinish(Bitmap processedbmp) { // AsyncBlur will scale down the bitmap for processing quickly, so the width and height is small.
                                mBitmap_blur = Bitmap.createBitmap(mDisplayMetrics.widthPixels, mDisplayMetrics.heightPixels, Bitmap.Config.ARGB_8888);
                                mBitmap_blur = BitmapUtils.scaleBitmap( processedbmp, mDisplayMetrics.widthPixels, mDisplayMetrics.heightPixels);
                                mDrawable_blur = new BitmapDrawable(getResources(), mBitmap_blur);
                                mImageView_org.setBackground(mDrawable_blur);
                            }
                        });

                    } catch (FileNotFoundException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    break;
            }
        }
    }


    public void onClickMyDrawableActivityButtons(View view){
        String Tag = view.getTag().toString();
        int tag = Integer.parseInt(Tag);
        long start_time = SystemClock.uptimeMillis();

        switch(tag) {
            case 0:
                mImageView_org.setImageDrawable(mDrawable_org);
                mImageView_blur.setImageDrawable(mDrawable_blur);
                break;

            case 1:
                // drawable_out = DrawableUtils.copyDrawable(mDrawable_org);
                DrawableUtils2.setContext(mContext);
                Bitmap bitmap = DrawableUtils2.getBackgroundBitmap(null);
                // Bitmap bitmap = DrawableUtils2.getStatusBarBitmap(null); // mBuffer has no data.
                drawable_out = BitmapUtils.BitmapToDrawable(mContext, bitmap); // test OK
                break;

            case 2: // overlapDrawable
                drawable_out = DrawableUtils.overlapDrawable(mContext, mDrawable_org, mDrawable_blur, pixelShift);

                break;

            case 3: // moveDrawable
                // drawable_out = DrawableUtils.shiftDrawable(mDrawable_blur,pixelShift);
                // drawable_out = DrawableUtils.moveDrawable(mDrawable_blur,pixelShift,0);
                drawable_out = DrawableUtils.resizeDrawable(mDrawable_blur,pixelShift,pixelShift);
                break;

            case 4: // TransitionDrawable
                Drawable drawable1 = DrawableUtils.moveDrawable(mDrawable_blur,pixelShift,0);
                TransitionDrawable transitionDrawable = DrawableUtils.transitionDrawable(mDrawable_org, drawable1);
                drawable_out = transitionDrawable;
                transitionDrawable.startTransition(400);
                break;

            case 5: // ClipDrawable
                ClipDrawable clipDrawable = new ClipDrawable(mDrawable_blur, Gravity.RIGHT, ClipDrawable.HORIZONTAL);
                int drawableWidth = clipDrawable.getMinimumWidth();
                int clippedPixel = pixelShift;
                float ratio = (float)clippedPixel/(float)drawableWidth;
                int level = (int)(10000*ratio);
                clipDrawable.setLevel(level);
                SMLog.i("clipedPixel="+clippedPixel+"  ratio="+ratio+"  drawableWidth="+drawableWidth +"  level="+level);
                // LayerDrawable
                LayerDrawable layerDrawable = new LayerDrawable(new Drawable[] {mDrawable_org, clipDrawable});
                layerDrawable.setLayerGravity(1,Gravity.RIGHT);
                drawable_out = layerDrawable;

                break;

            case 6: // ColorDrawable
                ColorDrawable colorDrawable = new ColorDrawable(Color.BLACK);
                Rect rect = new Rect();
                rect.top = 0;
                rect.left = 0;
                rect.right = mImageView_org.getWidth();
                rect.bottom = mImageView_org.getHeight();
                colorDrawable.setBounds(rect);
                drawable_out = overlapBlurDrawable(mDrawable_blur,colorDrawable, pixelShift);
                // mBitmap_org = Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888); // Single color bitmap will be created of 1x1 pixel
                // mBitmap_blur = Bitmap.createBitmap(mImageView_org.getWidth(), mImageView_org.getHeight(), Bitmap.Config.ARGB_8888);
                break;
        }
        long end_time = SystemClock.uptimeMillis();
        SMLog.i("Effect     TimeCost = " + (end_time - start_time));
        // mImageView_org.setImageDrawable(drawable_out);
        mImageView_org.setBackground(drawable_out);
    }


    private static Drawable overlapBlurDrawable(Drawable drawableTop, Drawable drawableBottom, int clippedPixel){
        ClipDrawable clipDrawable = new ClipDrawable(drawableTop, Gravity.RIGHT, ClipDrawable.HORIZONTAL);
        int drawableWidth = clipDrawable.getIntrinsicWidth();
        float ratio = (float)clippedPixel/(float)drawableWidth;
        int level = (int)(10000*ratio);
        clipDrawable.setLevel(level);
        SMLog.i("clippedPixel="+clippedPixel+"  ratio="+ratio+"  drawableWidth="+drawableWidth +"  level="+level);
        // LayerDrawable
        LayerDrawable layerDrawable = new LayerDrawable(new Drawable[] {drawableBottom, clipDrawable});
        layerDrawable.setLayerGravity(1,Gravity.RIGHT);
        return layerDrawable;
    }

}
