package sample.hawk.com.mybasicappcomponents.debugTest.DebugMsgProxy;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by ha271 on 2017/3/27.
 */

public class DebugStaticReceiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        if ("android.intent.action.BOOT_COMPLETED".equals(intent.getAction())) {
            SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
            Boolean bAutoStart = defaultSharedPreferences.getBoolean("prefAutoStart", true);
            Boolean bEnableSwipeService = defaultSharedPreferences.getBoolean("prefEnable", true);
            if (bAutoStart && bEnableSwipeService) {
                context.startService(new Intent(context, DebugMsgService.class));
            }
        }
    }
}
