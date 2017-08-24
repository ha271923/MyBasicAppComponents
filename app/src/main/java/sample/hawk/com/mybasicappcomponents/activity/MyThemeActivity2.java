package sample.hawk.com.mybasicappcomponents.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.FileNotFoundException;

import sample.hawk.com.mybasicappcomponents.R;

/**
    注意: Android提供的BlurMaskFilter/EmbossMaskFilter 只是边缘效果而已（egde），不是整体的效果

    BlurMaskFilter.Blur
        INNER : Blur inside the border, draw nothing outside.
        NORMAL : Blur inside and outside the original border.
        OUTER : Draw nothing inside the border, blur outside.
        SOLID : Draw solid inside the border, blur outside.
*/

public class MyThemeActivity2 extends Activity {
    private Context mContext;
    private Resources mResources;
    private RelativeLayout mRelativeLayout;
    private TextView mTextView;
    private RadioGroup mRadioGroup;

    ImageView mImageView;

    final int RQS_IMAGE1 = 1;
    Uri source;
    Bitmap bitmapMaster;
    Canvas canvasMaster;
    BlurMaskFilter.Blur mStyle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mytheme_activity2);

        // Get the application context
        mContext = getApplicationContext();

        // Get the Resources
        mResources = getResources();

        // Get the widgets reference from XML layout
        mTextView = (TextView) findViewById(R.id.tv);
        mImageView = (ImageView) findViewById(R.id.result);
        mRadioGroup = (RadioGroup) findViewById(R.id.rg);

        // Set a checked change listener for RadioGroup
        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.rb_none) {
                    // If no blur is checked
                    // Set the TextView layer type
                    mTextView.setLayerType(View.LAYER_TYPE_SOFTWARE,null);
                    // Clear any previous MaskFilter
                    mTextView.getPaint().setMaskFilter(null);
                }
                if(i == R.id.rb_inner){
                    mStyle = BlurMaskFilter.Blur.INNER;
                    getImageFromAlbum();
                }
                if(i == R.id.rb_normal){
                    mStyle = BlurMaskFilter.Blur.NORMAL;
                    getImageFromAlbum();
                }
                if(i == R.id.rb_outer){
                    mStyle = BlurMaskFilter.Blur.OUTER;
                    getImageFromAlbum();
                }
                if(i == R.id.rb_solid){
                    mStyle = BlurMaskFilter.Blur.SOLID;
                    getImageFromAlbum();
                }
                applyBlurMaskFilter(mTextView, mStyle);
            }
        });
    }

    // Custom method to apply BlurMaskFilter to a TextView text
    protected void applyBlurMaskFilter(TextView tv, BlurMaskFilter.Blur style){
        float radius = tv.getTextSize()/10; // Define the blur effect radius
        BlurMaskFilter filter = new BlurMaskFilter(radius,style); // Initialize a new BlurMaskFilter instance
        tv.setLayerType(View.LAYER_TYPE_SOFTWARE, null); // Set the TextView layer type
        tv.getPaint().setMaskFilter(filter); // Finally, apply the blur effect on TextView text
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case RQS_IMAGE1:
                    source = data.getData();
                    try {
                        bitmapMaster = BitmapFactory
                                .decodeStream(getContentResolver().openInputStream(
                                        source));
                        loadGrayBitmap(bitmapMaster, mStyle);

                    } catch (FileNotFoundException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    break;
            }
        }
    }

    private void loadGrayBitmap(Bitmap src, BlurMaskFilter.Blur style) {
        if (src != null) {
            int w = src.getWidth();
            int h = src.getHeight();
            // RectF rectF = new RectF(w/4, h/4, w*3/4, h*3/4);
            final DisplayMetrics metrics=mContext.getResources().getDisplayMetrics();
            final float density=metrics.density;
            float blurRadius = 2000*density;
            //float blurRadius = 3.5f;
            Bitmap bitmapResult = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(bitmapResult);
            Paint paint = new Paint();
            paint.setColor(0xFFffffff);
            // BlurMaskFilter/EmbossMaskFilter提供的只是边缘效果而已（egde），不是整体的效果
            paint.setMaskFilter(new BlurMaskFilter(blurRadius, style));
            canvas.drawBitmap(bitmapMaster, 0, 0, paint);
            mImageView.setImageBitmap(bitmapResult);
        }
    }

    void getImageFromAlbum(){
        Intent intent = new Intent(
        Intent.ACTION_PICK,
        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, RQS_IMAGE1);
    }

}