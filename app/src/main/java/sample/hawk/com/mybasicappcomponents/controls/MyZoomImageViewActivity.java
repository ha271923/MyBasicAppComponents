package sample.hawk.com.mybasicappcomponents.controls;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import sample.hawk.com.mybasicappcomponents.R;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;

/**
 * Created by ha271 on 2017/9/27.
 */

public class MyZoomImageViewActivity extends Activity {
    MyZoomImageView myZoomImageView;

    FrameLayout mFrameLayout;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFrameLayout = new FrameLayout(this);
        mFrameLayout.setLayoutParams(new FrameLayout.LayoutParams(MATCH_PARENT, MATCH_PARENT));
        mFrameLayout.setBackgroundColor(Color.BLUE);

        myZoomImageView = new MyZoomImageView(this);
        // myZoomImageView.setLayoutParams(new FrameLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT));
        myZoomImageView.setLayoutParams(new FrameLayout.LayoutParams(1200, 1200));
        myZoomImageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        Drawable drawable = getDrawable(R.drawable.vertical_photo_480x720);
        myZoomImageView.setBackgroundColor(Color.YELLOW);
        myZoomImageView.setImageDrawable(drawable);

        mFrameLayout.addView(myZoomImageView);
        setContentView(mFrameLayout);
        Toast.makeText(this, "Zoom, Move by finger at image edge",Toast.LENGTH_LONG).show();
    }

}

