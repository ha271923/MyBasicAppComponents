package sample.hawk.com.mybasicappcomponents.controls.Move;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.RelativeLayout;

import sample.hawk.com.mybasicappcomponents.graphic.utils.ImageUtils;

/**
 * Created by ha271 on 2017/9/21.
 */

public class MyMoveViewActivity extends Activity {

    MoveableBallView mMoveableBallView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mMoveableBallView = new MoveableBallView(this);

        RelativeLayout mRoot = new RelativeLayout(this);
        mRoot.addView(mMoveableBallView);

        setContentView(mRoot);
    }

    @Override
    protected void onResume() {
        super.onResume();
        ImageUtils.saveScreenshot(mMoveableBallView);
    }
}
