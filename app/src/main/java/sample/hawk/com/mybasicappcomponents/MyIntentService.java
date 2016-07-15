package sample.hawk.com.mybasicappcomponents;

import android.app.IntentService;
import android.content.Intent;

import java.text.SimpleDateFormat;
import java.util.Date;

import sample.hawk.com.mybasicappcomponents.utils.SMLog;

/**
 * Created by ha271 on 2016/7/13.
 */

public class MyIntentService extends IntentService { //
    private static final String TAG = "MyIntentService";
    private static final SimpleDateFormat SDF_DATE_FORMAT = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss.SSS");

    public MyIntentService() { // in UI thread
        super(TAG);
        SMLog.i(TAG," ----> constructor TID="+ Thread.currentThread().getId());
    }

    @Override
    public void onCreate() { // in UI thread
        super.onCreate();
        SMLog.i(TAG," ----> onCreate() TID="+ Thread.currentThread().getId());
    }

    @Override
    public void onDestroy() { // in UI thread
        super.onDestroy();
        SMLog.i(TAG," ----> onDestroy() TID=" + Thread.currentThread().getId());
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {  // in UI thread
        SMLog.i(TAG," ----> onStartCommand() TID=" + Thread.currentThread().getId());
        intent.putExtra("time", System.currentTimeMillis());
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void setIntentRedelivery(boolean enabled) {
        SMLog.i(TAG," ----> setIntentRedelivery() TID=" + Thread.currentThread().getId());
        super.setIntentRedelivery(enabled);
    }

    @Override
    protected void onHandleIntent(Intent intent) { // in Worker thread now!!!
        SMLog.i(TAG," ----> onHandleIntent() TID=" + Thread.currentThread().getId());
        // WorkerThread
        // TODO: put your background job at here!!

        long time = intent.getLongExtra("time", 0);
        Date date = new Date(time);
        try {
            SMLog.i(TAG," ----> onHandleIntent(): Date" + SDF_DATE_FORMAT.format(date));
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
