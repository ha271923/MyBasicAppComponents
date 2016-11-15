package sample.hawk.com.mybasicappcomponents.animation;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import sample.hawk.com.mybasicappcomponents.R;

/**
 * Created by ha271 on 2016/11/15.
 */

public class ScaleAnimationActivity extends Activity {
    protected ScaleAnimationHelper mScaleAnimationHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myscaleanimation);

        final View bar1 = findViewById(R.id.bar1);
        final View bar2 = findViewById(R.id.bar2);
        final View bar3 = findViewById(R.id.bar3);

        final int max = 14;
        final int min = 6;
        final float fScale = (float) min / (float) max;
        mScaleAnimationHelper = new ScaleAnimationHelper(600, fScale, bar1, bar2, bar3);
        mScaleAnimationHelper.setStart();
    }



}
