package sample.hawk.com.mybasicappcomponents.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import sample.hawk.com.mybasicappcomponents.receiver.ReceiverTestActivity;
import sample.hawk.com.mybasicappcomponents.utils.SMLog;

/**
 * Created by Hawk_Wei on 2016/3/16.
 */
public class MyReceiver extends BroadcastReceiver { // Hawk: UI thread, however the receiver class doesn't support UI component except you move it to be inner class of Activity.
    private static final String TAG = "[MyReceiver]";
    static int i;
    public static final int SET_MYALARM_CODE = 12345;

    public MyReceiver() {
        super();
        SMLog.i();
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        // HAWK:CAUTION!! If you register the receiver at AndroidManifest.xml and CODE, you will get twice broadcast here!
        SMLog.i(TAG,"MyReceiver  i="+i);
        i++;
        if("sample.hawk.com.mybasicappcomponents.broadcast1".equals(intent.getAction())){
            SMLog.i(TAG,"Received " + "sample.hawk.com.mybasicappcomponents.broadcast1 !!!!");
            ReceiverTestActivity.mReceiver_status.setText("Got broadcast1 !!");
        }
        if("sample.hawk.com.mybasicappcomponents.broadcast2".equals(intent.getAction())){
            SMLog.i(TAG,"Received " + "sample.hawk.com.mybasicappcomponents.broadcast2 !!!!");
            ReceiverTestActivity.mReceiver_status.setText("Got broadcast2 !!");
        }
        if("sample.hawk.com.mybasicappcomponents.alarmmanager".equals(intent.getAction())){
            SMLog.i(TAG,"Received " + "sample.hawk.com.mybasicappcomponents.alarmmanager !!!!");
            ReceiverTestActivity.mReceiver_status.setText("Got alarmmanager !!");
        }


    }
}
