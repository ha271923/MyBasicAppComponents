package sample.hawk.com.mybasicappcomponents.view.SurfaceView;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by ha271 on 2016/11/10.
 */

public class MySurfaceViewActivity extends Activity{
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        MySurfaceView mySurfaceView = new MySurfaceView(this);
        mySurfaceView.setOnTouchListener(mySurfaceView);
        setContentView(mySurfaceView);
    }
}
