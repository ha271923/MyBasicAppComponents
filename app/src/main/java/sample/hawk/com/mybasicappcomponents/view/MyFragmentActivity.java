package sample.hawk.com.mybasicappcomponents.view;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;

import sample.hawk.com.mybasicappcomponents.R;

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
}
