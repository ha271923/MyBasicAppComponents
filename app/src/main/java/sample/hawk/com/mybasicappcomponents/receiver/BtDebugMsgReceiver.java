package sample.hawk.com.mybasicappcomponents.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import sample.hawk.com.mybasicappcomponents.utils.SMLog;

/**
 *
 * Prints Bluetooth intents to logcat. For example:
 * BTDEBUG : a.b.device.a.FOUND
 * BTDEBUG :       a.b.device.e.DEVICE = 00:18:13:F2:CC:33
 * BTDEBUG :       a.b.device.e.RSSI = -35
 * BTDEBUG :       a.b.device.e.CLASS = 200404
 * BTDEBUG : a.b.adapter.a.DISCOVERY_FINISHED
 * BTDEBUG : a.b.device.a.BOND_STATE_CHANGED
 * BTDEBUG :       a.b.device.e.DEVICE = 00:18:13:F2:CC:33
 * BTDEBUG :       a.b.device.e.BOND_STATE = 11
 * BTDEBUG :       a.b.device.e.PREVIOUS_BOND_STATE = 10
 */
public class BtDebugMsgReceiver extends BroadcastReceiver {
    private static final String TAG = "BTDEBUG";

    public void onReceive(Context context, Intent intent) {
        SMLog.i(TAG, shorten(intent.getAction()));

        Bundle bundle = intent.getExtras();
        if (bundle == null) return;
        for (String extra : bundle.keySet()) {
            SMLog.i(TAG, "\t" + shorten(extra) + " = " + bundle.get(extra));
        }
    }

    // shorten string to shorthand
    // android.bluetooth.device.extra.DEVICE -> a.b.device.e.DEVICE
    private static String shorten(String action) {
        return action.replace("android", "a")
                .replace("bluetooth", "b")
                .replace("extra", "e")
                .replace("action", "a");
    }

}