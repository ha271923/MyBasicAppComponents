package sample.hawk.com.mybasicappcomponents.controls.Move;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.RelativeLayout;

/**
 * Created by ha271 on 2017/9/21.
 */

public class MyMoveViewActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MoveableBallView moveableBallView = new MoveableBallView(this);

        RelativeLayout mRoot = new RelativeLayout(this);
        mRoot.addView(moveableBallView);

        setContentView(mRoot);
    }
}
