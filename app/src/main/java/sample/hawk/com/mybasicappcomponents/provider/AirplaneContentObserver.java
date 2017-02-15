package sample.hawk.com.mybasicappcomponents.provider;

import android.content.Context;
import android.database.ContentObserver;
import android.os.Handler;
import android.provider.Settings;
import android.provider.Settings.SettingNotFoundException;
import android.util.Log;

import static sample.hawk.com.mybasicappcomponents.provider.ContentObserverActivity.MSG_AIRPLANE;

/**
 * Created by ha271 on 2017/2/14.
 */

public class AirplaneContentObserver extends ContentObserver{
    //用來觀察system表裡飛行模式所在行是否發生變化 ， “行”內容觀察者
    private static  String TAG =  "AirplaneContentObserver"  ;

    private  Context mContext;
    private  Handler mHandler ;   //此Handler用來更新UI線程

    public  AirplaneContentObserver(Context context, Handler handler) {
        super (handler);
        mContext = context;
        mHandler = handler ;
    }

    /**
     * 當所監聽的Uri發生改變時，就會回調此方法
     *
     * @param selfChange 此值意義不大 一般情況下該回調值false
     */
    @Override
    public void  onChange( boolean  selfChange) {
        Log.i(TAG,  "-------------the AirPlane mode has changed-------------" );
        // 系統是否處於飛行模式下
        try  {
            int  isAirplaneOpen = Settings.System.getInt(mContext.getContentResolver(), android.provider.Settings.Global.AIRPLANE_MODE_ON);
            Log.i(TAG,  " isAirplaneOpen -----> "  +isAirplaneOpen) ;
            mHandler.obtainMessage(MSG_AIRPLANE,isAirplaneOpen).sendToTarget() ;
        }
        catch  (SettingNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}