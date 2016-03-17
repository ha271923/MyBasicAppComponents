package sample.hawk.com.mybasicappcomponents;

import android.app.Activity;
import android.os.Bundle;

import sample.hawk.com.mybasicappcomponents.utils.SMLog;

/**
 * Created by Hawk_Wei on 2016/3/16.
 */
public class MyActivity extends Activity {
    final String TAG = "MyActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SMLog.i();

    }
}
