package sample.hawk.com.mybasicappcomponents.provider;


import android.content.Context;
import android.database.ContentObserver;
import android.os.Handler;
import android.provider.Settings;

import sample.hawk.com.mybasicappcomponents.utils.SMLog;

import static sample.hawk.com.mybasicappcomponents.provider.ContentObserverActivity.MSG_WIFI_CHANGED;

/**
 * Hawk: Settings.Global.WIFI_ON can't monitor WiFi status. We will need another ways, just like BroadcastReceiver or
 * or getSystemService(Context.CONNECTIVITY_SERVICE) at
 * https://developer.android.com/training/monitoring-device-state/connectivity-monitoring.html
 *
 */

public class WiFiContentObserver extends ContentObserver {
    private static  String TAG =  "WiFiContentObserver";
    private  Context mContext;
    private  Handler mHandler;   //此Handler用來更新UI線程

    public WiFiContentObserver(Context context, Handler handler) {
        super (handler);
        mContext = context;
        mHandler = handler;
    }

    /**
     * 當所監聽的Uri發生改變時，就會回調此方法
     *
     * @param selfChange 此值意義不大 一般情況下該回調值false
     */
    @Override
    public void  onChange( boolean  selfChange) {
        SMLog.i("-------------the WiFi has changed-------------" );
        // 系統是否處於飛行模式下
        try  {
            int  isWiFi_ON = Settings.System.getInt(mContext.getContentResolver(), Settings.Global.WIFI_ON);
            SMLog.i(TAG,  " isWiFi_ON -----> "  +isWiFi_ON) ;
            mHandler.obtainMessage(MSG_WIFI_CHANGED,isWiFi_ON).sendToTarget() ;
        }
        catch  (Settings.SettingNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}