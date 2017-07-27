package sample.hawk.com.mybasicappcomponents.activity;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.widget.FrameLayout;
import android.widget.TextView;

import sample.hawk.com.mybasicappcomponents.R;

/**
 * Created by ha271 on 2017/7/27.
 */

public class MyResolutionActivity extends Activity {
    FrameLayout mBacground_framelayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myresolution_activity);

        mBacground_framelayout = ((FrameLayout)findViewById(R.id.bacground_framelayout));

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int device_height = displayMetrics.heightPixels;
        int device_width = displayMetrics.widthPixels;
        ((TextView)findViewById(R.id.tvScreenData)).setText("Screen: W="+device_width+"  H="+device_height);

        Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.drawable.android_robot);
        ((TextView)findViewById(R.id.tvImageData)).setText("Image : W="+bmp.getWidth()+"  H="+bmp.getHeight());
    }



}

