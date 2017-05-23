package sample.hawk.com.mybasicappcomponents.activity;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.res.Configuration;
import android.os.Bundle;

import sample.hawk.com.mybasicappcomponents.R;
import sample.hawk.com.mybasicappcomponents.utils.SMLog;
import sample.hawk.com.mybasicappcomponents.utils.Util;

/**
 * Created by ha271 on 2016/11/11.
 */

public class MyFragmentActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myfragment_activity); // this xml's viewgroup included fragment1
        addFragment(); // dynamic add fragment2
    }

    void addFragment() {
        Fragment newFragment = new MyFragment2();
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.add(R.id.MyFragment_Activity, newFragment, "Loaded MyFragment2.xml");
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ft.commit();
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
