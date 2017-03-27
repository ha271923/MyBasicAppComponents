package sample.hawk.com.mybasicappcomponents.debugTest.DebugMsgProxy;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import sample.hawk.com.mybasicappcomponents.utils.SMLog;

/**
 * Created by ha271 on 2017/3/22.
 */

public class DebugMsgReceiver extends BroadcastReceiver {
    DebugMsgService.DebugMsgService_Listener mListener;
    public DebugMsgReceiver(DebugMsgService.DebugMsgService_Listener listener){
        mListener = listener;
    }

    public void onReceive(Context context, Intent intent) {
        if("sample.hawk.com.mybasicappcomponents.debugmsg".equals(intent.getAction())){
            String className = intent.getStringExtra("ClassName");
            SMLog.d("ClassName: " + className);
            mListener.onDebugMsgUpdate(className);
        }
    }
}
