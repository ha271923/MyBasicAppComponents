package sample.hawk.com.mybasicappcomponents.background;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Message;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import sample.hawk.com.mybasicappcomponents.R;
import sample.hawk.com.mybasicappcomponents.activity.MyActivity1;
import sample.hawk.com.mybasicappcomponents.receiver.ReceiverTestActivity;
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
        my_update_time_thread(); // update UI via BroadcastReceiver.
        UpdateUI_in_LocalService(); // update UI via Handler.
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

    static final String ACTION_FOREGROUND = "sample.hawk.com.mybasicappcomponents.background.FOREGROUND";
    static final String ACTION_BACKGROUND = "sample.hawk.com.mybasicappcomponents.background.BACKGROUND";
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        SMLog.i();
        if(ACTION_FOREGROUND.equals(intent.getAction())) {
            setAsForeground();
            // return START_STICKY; // START_STICKY是service被kill掉後自動重寫建立
        }
        return super.onStartCommand(intent, flags, startId);
    }
    
    Notification mNotification;
    void setAsForeground() { // ForegroundService優先權較高
        // The contentIntent to launch our activity if the user selects this notification
        Intent notificationIntent = new Intent(this, MyActivity1.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0);

        mNotification = new Notification.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)  // the status icon
                .setTicker("ForegroundService: setTicker")  // the status text
                .setWhen(System.currentTimeMillis())  // the time stamp
                .setContentTitle("ForegroundService: setContentTitle")  // the label of the entry
                .setContentText( "ForegroundService: setContentText")  // the contents of the entry
                .setContentIntent(contentIntent)  // The intent to send when the entry is clicked
                .setLights(0xff00ff00,1000,3000) // LED
                .build();

        // 一個已啟動的service可以調用startForeground(int, Notification)將service置為foreground狀態，
        // 調用stopForeground(boolean)將service置為 background狀態。
        startForeground(1,mNotification);
    }

    public class LocalBinder extends Binder {
        public MyLocalService getService() {
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
                // TODO: put your background job at here
                SMLog.i(TAG,"my_update_time_thread  run1() ThreadId="+Thread.currentThread().getId());
                bundle.putString("To_mMyOutputView", sdf.format(new Date()));
                timeIntent.putExtras(bundle);
                timeIntent.setAction(ReceiverTestActivity.UPDATE_ACTIVITY_ACTION); // Hawk: you don't need to define MainActivity's constant again at here!
                // UPDATE_UI WAY1A: send a broadcast to MainActivity
                sendBroadcast(timeIntent);
            }
        }, 1000,1000); // time param at here!
    }
// UPDATE_UI WAY2: update UI in thread.
//region = 寫法3: HandlerThread*1 + Handler*2(在handleMessage內部直接處理msg)+ Runnable*1 , 更新UI與DATA
    private TextView txtView;
    private static int count = 0;
    private static int ps = 0;
    private HandlerThread mhThread;
    private Handler mHandler;
    private static final int MSG_UPDATE_UI = 12345;

    protected void UpdateUI_in_LocalService() {
        mhThread = new HandlerThread("myHT");
        mhThread.start();
        mHandler = new Handler(mhThread.getLooper()); // KEY POINT: The default looper
        SMLog.i("TEST", "onCreate() tID=" + Thread.currentThread().getId());
        mHandler.post(updateData);
    }
    private Runnable updateData = new Runnable() { // I can't update UI.
        @Override
        public void run() {
            // TODO: put your background job at here
            try {
                for (; count < 1000; ) {
                    Thread.sleep(1000);
                    count++;
                    SMLog.i("TEST","updateData Runnable() tID="+Thread.currentThread().getId());
                    mMain_Handler.sendEmptyMessage(MSG_UPDATE_UI); // KEY POINT:
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    };
    private Handler mMain_Handler = new Handler(){ // I can update UI, because new it under Activity class(UI thread).
        public void handleMessage(Message msg){
            super.handleMessage(msg);
            SMLog.i("TEST", "handleMessage() tID=" + Thread.currentThread().getId());
            switch(msg.what){
                case MSG_UPDATE_UI:
                    ps = count%100;
                    MyLocalServiceActivity.mMyLocalServiceProgressBar.setProgress(ps);
                    break;
                default:
                    break;
            }
        }
    };
//endregion
}
