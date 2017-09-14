package sample.hawk.com.mybasicappcomponents.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import sample.hawk.com.mybasicappcomponents.R;

/**
 * Created by ha271 on 2017/9/7.
 */

public class MyTransparentActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mytheme_activity);

        // transparent by style in AndroidManifest.xml
    }
}
