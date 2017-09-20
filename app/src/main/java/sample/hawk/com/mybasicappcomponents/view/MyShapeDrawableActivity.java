package sample.hawk.com.mybasicappcomponents.view;

import android.app.Activity;
import android.os.Bundle;

import sample.hawk.com.mybasicappcomponents.view.CustomView.MyShapeDrawableView;

/**
 * Created by ha271 on 2017/9/20.
 */

public class MyShapeDrawableActivity extends Activity {
    MyShapeDrawableView mCustomDrawableView;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCustomDrawableView = new MyShapeDrawableView(this);

        setContentView(mCustomDrawableView);
    }
}