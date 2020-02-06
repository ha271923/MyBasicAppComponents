package sample.hawk.com.mybasicappcomponents.ipc.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import sample.hawk.com.mybasicappcomponents.utils.SMLog;


public class MyBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        String strAction = intent.getAction();
        SMLog.d("MyBroadcastReceiver", "action:" + strAction);

        Bundle bundle = intent.getExtras();
        if(bundle!= null){
            String extra_KEY_HELLO = bundle.getString("HELLO");
            SMLog.d("MyBroadcastReceiver","extra_KEY_HELLO= "+extra_KEY_HELLO);
        }
    }
}
