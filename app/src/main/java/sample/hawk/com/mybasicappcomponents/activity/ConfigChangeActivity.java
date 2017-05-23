package sample.hawk.com.mybasicappcomponents.activity;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;

import sample.hawk.com.mybasicappcomponents.utils.SMLog;
import sample.hawk.com.mybasicappcomponents.utils.Util;

/**
 * https://developer.android.com/guide/topics/manifest/activity-element.html
 */

public class ConfigChangeActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        SMLog.i("onConfigurationChanged");
        Configuration config = this.getResources().getConfiguration();
        if(config.equals(newConfig ))
            SMLog.i("newConfig == getConfiguration()");
        Util.ListActivityConfigurations(newConfig);

    }
}
