package sample.hawk.com.mybasicappcomponents;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import sample.hawk.com.mybasicappcomponents.utils.SMLog;

/**
 * Created by Hawk_Wei on 2016/3/16.
 */
public class MyActivity extends Activity {
    private static final String TAG = "[MyActivity]";

    String   mState;
    TextView mStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);SMLog.i();
        // View
        setContentView(R.layout.myactivity);

        mStatus = (TextView) findViewById(R.id.status_textView);
        mState +="(C1)onCreate->"; mStatus.setText(mState);
    }

    @Override
    protected void onStart() {
        super.onStart();SMLog.i();
        mState +="(C2)onStart->"; mStatus.setText(mState);
    }

    @Override
    protected void onResume() {
        super.onResume();SMLog.i();
        mState +="(C3)onResume->"; mStatus.setText(mState);
    }

    @Override
    protected void onPause() {
        super.onPause();SMLog.i();
        mState +="(D1)onPause->"; mStatus.setText(mState);
    }

    @Override
    protected void onStop() {
        super.onStop();SMLog.i();
        mState +="(D2)onStop->"; mStatus.setText(mState);
    }

    @Override
    protected void onRestart() {
        super.onRestart();SMLog.i();
        mState +="(D3-1)onRestart->"; mStatus.setText(mState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();SMLog.i();
        mState +="(D3)onDestroy->"; mStatus.setText(mState);
    }

}
