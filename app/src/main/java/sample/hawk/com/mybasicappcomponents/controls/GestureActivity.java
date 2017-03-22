package sample.hawk.com.mybasicappcomponents.controls;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by ha271 on 2017/3/22.
 */

public class GestureActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new GestureZoom(this));
    }
}