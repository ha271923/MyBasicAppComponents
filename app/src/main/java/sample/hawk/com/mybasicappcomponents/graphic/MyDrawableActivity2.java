package sample.hawk.com.mybasicappcomponents.graphic;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
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
public class MyDrawableActivity2 extends Activity {
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

    public  class MySeekBar_Listener implements SeekBar.OnSeekBarChangeListener {

        public MySeekBar_Listener(Activity activity) {
        }

        public final void onProgressChanged(SeekBar seekBar, int progress, boolean z) {

        }

        public final void onStartTrackingTouch(SeekBar seekBar) {
        }

        public final void onStopTrackingTouch(SeekBar seekBar) {
        }

    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mydrawableactivity2);
        mContext = this;
        mDisplayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(mDisplayMetrics);
        SMLog.i("StatusBarHeight="+ ImageUtils.getStatusBarHeight(this));
        mImageView_blur = (ImageView) findViewById(R.id.imageViewBlurred);
        mImageView_org = (ImageView) findViewById(R.id.imageViewOrigin);
        SeekBar myseekbar = (SeekBar) findViewById(R.id.drawable_seekbar);
        int width =  mDisplayMetrics.widthPixels;
        myseekbar.setMax(width);
        myseekbar.setProgress(pixelShift);
        myseekbar.setOnSeekBarChangeListener(new MySeekBar_Listener(this ));

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
        int nStatusBarHeight = 100;

        switch(tag) {
            case 0:
                break;

            case 1:
                DrawableUtils2.setContext(mContext);
                Bitmap bitmap1 = DrawableUtils2.getBackgroundBitmap(null);
                Bitmap mStatusBarBackgroundBitmap = Bitmap.createBitmap(bitmap1, 0, 0, bitmap1.getWidth(), nStatusBarHeight);
                drawable_out = new BitmapDrawable(getResources(), mStatusBarBackgroundBitmap);
                break;

            case 2:
                DrawableUtils2.setContext(mContext);
                Bitmap bitmap2 = DrawableUtils2.getBackgroundBitmap(null);
                Bitmap mBackgroundBitmap = Bitmap.createBitmap(bitmap2, 0, nStatusBarHeight, bitmap2.getWidth(), bitmap2.getHeight() - nStatusBarHeight);
                drawable_out = new BitmapDrawable(getResources(), mBackgroundBitmap);
                break;
            case 3:
                DrawableUtils.scaleDrawable(mDrawable_org, mImageView_org.getWidth()/2, mImageView_org.getHeight()/2); // setBounds
                drawable_out = mDrawable_org;
                break;

            case 4:
                drawable_out = DrawableUtils.changeDrawableColor(mDrawable_org, 0x7657321);
                break;

            case 5:
                break;

            case 6:
                break;
        }
        long end_time = SystemClock.uptimeMillis();
        SMLog.i("Effect     TimeCost = " + (end_time - start_time));
        // mImageView_org.setImageDrawable(drawable_out);
        mImageView_org.setBackground(drawable_out);
    }



}
