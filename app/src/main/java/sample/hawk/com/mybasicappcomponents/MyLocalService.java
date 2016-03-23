package sample.hawk.com.mybasicappcomponents;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import sample.hawk.com.mybasicappcomponents.utils.SMLog;


/**
 * Created by Hawk_Wei on 2016/3/16.
 */
// 不管是何種Service，default 都是在應用程序的UI thread中運行的, 即使如此service內也無法直接修改UI
public class MyLocalService extends Service { // Hawk: UI thread, however the receiver class doesn't support UI component.
    private static final String TAG = "[MyLocalService]";

    // Binder given to clients
    private final IBinder mBinder = new LocalBinder();
    // Random number generator
    private final Random mGenerator = new Random();



    public MyLocalService() {
        super();
        SMLog.i();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        SMLog.i();
        my_update_time_thread();
    }

    @Override
    public IBinder onBind(Intent intent) {
        SMLog.i();
        return mBinder;
    }

    @Override
    public void onRebind(Intent intent) {
        super.onRebind(intent);
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        SMLog.i();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        SMLog.i();
        return super.onStartCommand(intent, flags, startId);
    }

    public class LocalBinder extends Binder {
        MyLocalService getService() {
            return MyLocalService.this;
        }
    }
    // Hawk: Call the high loading API at background SERVICE.
    public int getRandomNumber() {
        return mGenerator.nextInt(100);
    }

    private Timer timer = null;
    private SimpleDateFormat sdf = null;
    private Intent timeIntent = null;
    private Bundle bundle = null;
    public void my_update_time_thread(){
        SMLog.i(TAG,"my_update_time_thread  ThreadId="+Thread.currentThread().getId()); // UI thread, only Run first time.

        timer = new Timer();
        sdf = new SimpleDateFormat("yyyy年MM月dd日 "+"hh:mm:ss");
        timeIntent = new Intent();
        bundle = new Bundle();
        timer.schedule(new TimerTask() { // schedule(TimerTask task, long delay, long period)
            @Override
            public void run() {// Worker thread, Run every 1sec
                SMLog.i(TAG,"my_update_time_thread  run1() ThreadId="+Thread.currentThread().getId());
                bundle.putString("To_mMyOutputView", sdf.format(new Date()));
                timeIntent.putExtras(bundle);
                timeIntent.setAction(MainActivity.UPDATE_MAINACTIVITY_ACTION); // Hawk: you don't need to define MainActivity's constant again at here!
                // UPDATE_UI WAY1A: send a broadcast to MainActivity
                sendBroadcast(timeIntent);
            }
        }, 1000,1000); // time param at here!
    }

}
