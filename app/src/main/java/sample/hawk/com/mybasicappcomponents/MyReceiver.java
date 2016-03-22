package sample.hawk.com.mybasicappcomponents;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import sample.hawk.com.mybasicappcomponents.utils.SMLog;

/**
 * Created by Hawk_Wei on 2016/3/16.
 */
public class MyReceiver extends BroadcastReceiver { // NOT UI thread
    private static final String TAG = "[MyReceiver]";
    static int i;

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
        }
        if("sample.hawk.com.mybasicappcomponents.broadcast2".equals(intent.getAction())){
            SMLog.i(TAG,"Received " + "sample.hawk.com.mybasicappcomponents.broadcast2 !!!!");
        }
    }
}
