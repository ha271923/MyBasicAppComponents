package sample.hawk.com.mybasicappcomponents;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import sample.hawk.com.mybasicappcomponents.utils.SMLog;

/**
 * Created by Hawk_Wei on 2016/3/16.
 */
public class MyActivity extends Activity {
    private static final String TAG = "[MyActivity]";
    public static String UPDATE_MYACTIVITY_ACTION = "sample.hawk.com.mybasicappcomponents.MyActivity.UPDATE_UI_ACTION";
    String   mState;
    TextView mStatus;
    ProgressBar mMyActivityProgressBar;
    private static int p;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);SMLog.i();
        // View
        setContentView(R.layout.myactivity);
        mMyActivityProgressBar= (ProgressBar)findViewById(R.id.myactivity_progressBar);
        mStatus = (TextView) findViewById(R.id.status_textView);
        mState +="(C1)onCreate->"; mStatus.setText(mState);
        my_update_PB_thread();
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

    private Timer timer = null;
    public void my_update_PB_thread(){
        SMLog.i(TAG,"my_update_PB_thread  ThreadId="+Thread.currentThread().getId()); // UI thread, only Run first time.
        timer = new Timer();
        timer.schedule(new TimerTask() { // schedule(TimerTask task, long delay, long period)
            @Override
            public void run() { // Worker thread, Run every 1sec
                SMLog.i(TAG,"my_update_PB_thread  run1() ThreadId="+Thread.currentThread().getId());

                // TODO: implement your job here, it will run every second.

                // UPDATE_UI WAY2: You call runOnUiThread in a thread under Activity class.
                runOnUiThread(new Runnable() {
                    public void run() // UI thread, Run every 1sec
                    {
                        SMLog.i(TAG,"my_update_PB_thread  run2() ThreadId="+Thread.currentThread().getId());
                        if(p>100)
                            p=0;
                        mMyActivityProgressBar.setProgress(p++);
                    }
                });
            }
        }, 1000,1000); // time param at here!
    }

}
