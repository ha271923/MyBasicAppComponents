package sample.hawk.com.mybasicappcomponents;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import sample.hawk.com.mybasicappcomponents.utils.PermissionUtil;
import sample.hawk.com.mybasicappcomponents.utils.SMLog;
import sample.hawk.com.mybasicappcomponents.view.MyView;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);SMLog.i();
        // View
        setContentView(R.layout.myactivity);
        mMyActivityProgressBar= (ProgressBar)findViewById(R.id.myactivity_progressBar);
        mStatus = (TextView) findViewById(R.id.status_textView);
        mState +="(C1)onCreate->"; mStatus.setText(mState);
        my_update_PB_thread();
        AddAndroidRobotView(this);
        remove_add_view(this);
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


    public static final int WRITE_EXTERNAL_STORAGE_REQUEST_CODE = 234566;
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                           int[] grantResults) {
        if (requestCode == WRITE_EXTERNAL_STORAGE_REQUEST_CODE) {
            SMLog.i(TAG, "Received response for REQUEST_WRITE_EXTERNAL_STORAGE");
            // We have requested multiple permissions for WRITE_EXTERNAL_STORAGE, so all of them need to be checked.
            if (PermissionUtil.verifyPermissions(grantResults)) {
                SMLog.i(TAG, "WRITE_EXTERNAL_STORAGE has been granted.");
                onNewIntent(getIntent()); // Got the permissions from the dialog
                //Toast.makeText(getContext(), "ALLOW: Help APP can access the database on the phone.", Toast.LENGTH_SHORT).show();
            } else {
                SMLog.i(TAG, "WRITE_EXTERNAL_STORAGE were NOT granted.");
                // Toast.makeText(this, "DENY: Help APP can NOT access the database on the phone.", Toast.LENGTH_LONG).show();
                //Utils.showGuideHome(getContext());    //
                finish();
            }
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }
    public void onClickRequestPermissionButton(View view){
        if (!PermissionUtil.hasSelfPermission(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)){
            // The permission is NOT already granted.
            // Check if the user has been asked about this permission already and denied
            // it. If so, we want to give more explanation about why the permission is needed.
            if (shouldShowRequestPermissionRationale(
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                // Show our own UI to explain to the user why we need to read the contacts
                // before actually requesting the permission and showing the default UI
            }

            // Fire off an async request to actually get the permission
            // This will show the standard permission request dialog UI
            requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    WRITE_EXTERNAL_STORAGE_REQUEST_CODE);
        }
    }
    public void onClickWriteLog(View view){
        appendLog("AAAAA");
    }
    public void appendLog(String text)
    {
        File logFile = new File("sdcard/testlog.txt"); // Hawk: you can find log file at /mnt/sdcard/testlog.txt
        if (!logFile.exists())
        {
            try
            {
                logFile.createNewFile();
            }
            catch (IOException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        try
        {
            //BufferedWriter for performance, true to set append to file flag
            BufferedWriter buf = new BufferedWriter(new FileWriter(logFile, true));
            buf.append(text);
            buf.newLine();
            buf.close();
        }
        catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
