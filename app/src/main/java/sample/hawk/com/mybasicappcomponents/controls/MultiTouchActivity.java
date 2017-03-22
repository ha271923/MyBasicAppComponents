package sample.hawk.com.mybasicappcomponents.controls;

import android.app.Activity;
import android.os.Bundle;

/**
 * Handling single and multi touch on Android - Tutorial
 * http://www.vogella.com/tutorials/AndroidTouch/article.html#singletouch
 */

public class MultiTouchActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new MultiTouchEventView(this, null));
    }
}
