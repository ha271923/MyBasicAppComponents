package sample.hawk.com.mybasicappcomponents.background;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;

import java.text.SimpleDateFormat;
import java.util.Date;

import sample.hawk.com.mybasicappcomponents.utils.SMLog;

/**
 * Created by ha271 on 2016/7/13.
 */

public class MyIntentService extends IntentService { //
    private static final String TAG = "MyIntentService";
    private static final SimpleDateFormat SDF_DATE_FORMAT = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss.SSS");
    private static boolean bForceStop=false;
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
        bForceStop = false;
        SMLog.i(TAG," ----> onDestroy() TID=" + Thread.currentThread().getId());
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {  // in UI thread
        // TODO: process and receive the command by setAction() or param by putExtra() at here!!
        String action = intent.getAction();
        SMLog.i(TAG," ----> onStartCommand() TID=" + Thread.currentThread().getId() +"   action="+action);
        if (action.equals("START_IntentService")) {
            intent.putExtra("time", System.currentTimeMillis());
        } else if (action.equals("STOP_IntentService")) {
            bForceStop = true;
        }

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void setIntentRedelivery(boolean enabled) {
        SMLog.i(TAG," ----> setIntentRedelivery() TID=" + Thread.currentThread().getId());
        super.setIntentRedelivery(enabled);
    }

    // IntentService automatically stops itself when onHandleIntent() ends, if no more commands had been sent to it
    // while onHandleIntent() was running. Hence, you do not manually stop an IntentService yourself.
    @Override
    protected void onHandleIntent(Intent intent) { // in Worker thread now!!!
        // TODO: put your job in workerThread at here!!
        String action = intent.getAction();
        SMLog.i(TAG," ----> onHandleIntent() TID=" + Thread.currentThread().getId() +"   action="+action);
        long time = intent.getLongExtra("time", 0);
        Date date = new Date(time);
        try {
            for(int i=0; i<10; i++){
                if( bForceStop == true )
                    break;
                SMLog.i(TAG,"["+i+"]  ----> onHandleIntent(): Date" + SDF_DATE_FORMAT.format(date));
                Thread.sleep(1000);
                updateUiProgressBar(intent , i);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    // send data between Activity and IntentService
    private void updateUiProgressBar(Intent intent, final int i){
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            Messenger messenger = (Messenger) bundle.get("messenger");
            Message msg = Message.obtain();
            bundle.putInt("progress",i);
            msg.setData(bundle); //put the data here
            try {
                messenger.send(msg);
            } catch (RemoteException e) {
                SMLog.e("error");
            }
        }
    }
}
