package sample.hawk.com.mybasicappcomponents.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

import sample.hawk.com.mybasicappcomponents.R;
import sample.hawk.com.mybasicappcomponents.utils.SMLog;


/**
 * Created by Hawk_Wei on 2016/3/16.
 */
public class LifeCycleActivity extends Activity {
    private static final String TAG = "[LifeCycleActivity]";
    public static String UPDATE_MYACTIVITY_ACTION = "sample.hawk.com.mybasicappcomponents.activity.LifeCycleActivity.UPDATE_UI_ACTION";
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
        mState +="(C1/D1-1/D2-2)onCreate->"; mStatus.setText(mState);
        my_update_PB_thread();
        // AddAndroidRobotView(this);
        // remove_add_view(this);

    }

    @Override
    protected void onStart() {
        super.onStart();SMLog.i();
        mState +="(C2)onStart->"; mStatus.setText(mState);
    }

    @Override
    protected void onResume() {
        super.onResume();SMLog.i();
        mState +="(C3/D1-1)onResume->"; mStatus.setText(mState);
    }

    @Override
    protected void onPause() { // If user back to UI, state will goto onResume.
        super.onPause();SMLog.i();
        mState +="(D1-0)onPause->"; mStatus.setText(mState);
    }

    @Override
    protected void onStop() {  // If user back to UI, state will goto onRestart. If process has been killed, the state will goto onCreate.
        super.onStop();SMLog.i();
        mState +="(D2-0)onStop->"; mStatus.setText(mState);
    }

    @Override
    protected void onRestart() { // the next state is onStart.
        super.onRestart();SMLog.i();
        mState +="(D2-1)onRestart->"; mStatus.setText(mState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();SMLog.i();
        mState +="(D2-0)onDestroy->"; mStatus.setText(mState);
        timer.cancel();
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


/*
    private void AddAndroidRobotView(Context context){
        final Window window = ((Activity)context).getWindow();
        MyView mv = new MyView(context);
        window.addContentView(mv, new WindowManager.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
    }

    private void remove_add_view(Context context){ // http://orzreynold.pixnet.net/blog/post/32590798-%E6%9B%B4%E6%94%B9view%E5%85%83%E4%BB%B6%E7%9A%84%E4%BD%8D%E7%BD%AE
        SMLog.i("remove_add_view++");
        RelativeLayout layout = (RelativeLayout)findViewById(R.id.my_relativelayout); //xml裡的layout
        TextView tv = (TextView)findViewById(R.id.my_textView);  //layout裡的my_textView
        layout.removeView(tv); // Hawk: to prevent the java.lang.IllegalStateException: The specified child already has a parent. You must call removeView() on the child's parent first.
        layout.bringToFront();
        layout.setGravity(Gravity.BOTTOM);
        layout.addView(tv, new RelativeLayout.LayoutParams(200, 200));
        SMLog.i("remove_add_view--");
    }
*/

}
