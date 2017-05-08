package sample.hawk.com.mybasicappcomponents.background;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.PowerManager;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

import sample.hawk.com.mybasicappcomponents.R;

import static android.os.PowerManager.PARTIAL_WAKE_LOCK;

/**
 * Keep Screen On or CPU alive
 * 1. WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON == setKeepScreenOn API == Keep the Screen On
 * 2. WakeLock == Keep the CPU On
 */

public class MyWakeLockActivity extends Activity {

    private int mCounter;
    private final int MAX_COUNT = 1000000;
    private final int COUNT_DURATION = 1*1000;

    PowerManager.WakeLock mWakeLock;
    LinearLayout mWakelocklayout;
    TextView tvShowCounter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mywakelockactivity);
        mWakelocklayout = (LinearLayout)findViewById(R.id.wakelocklayout);
        tvShowCounter = (TextView) findViewById(R.id.showCounter);
        PowerManager powerManager = (PowerManager) getSystemService(POWER_SERVICE);
/**
 * PARTIAL_WAKE_LOCK
 *      讓CPU持續運行, 但不讓螢幕或鍵盤背光亮起
 * SCREEN_DIM_WAKE_LOCK(deprecated) instead of FLAG_KEEP_SCREEN_ON
 *      點亮螢幕, 但只有灰暗程度
 * SCREEN_BRIGHT_WAKE_LOCK(deprecated) instead of FLAG_KEEP_SCREEN_ON
 *      點亮螢幕, 亮度全開
 * FULL_WAKE_LOCK(deprecated) instead of FLAG_KEEP_SCREEN_ON
 *      點亮螢幕和鍵盤背光, 亮度全開
 */
        mWakeLock = powerManager.newWakeLock(PARTIAL_WAKE_LOCK, "MyWakeLockTag:PARTIAL_WAKE_LOCK_");
    }
    public void onClick_FLAG_KEEP_SCREEN_ON_btn(View v){
        if(((ToggleButton)v).isChecked()==true){
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        }
        else{
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        }
    }
    public void onClick_LayoutParam_keepScreenOn_btn(View v){
        if(((ToggleButton)v).isChecked()==true){
            v.setKeepScreenOn(true);
        }
        else{
            v.setKeepScreenOn(false);
        }
    }
    public void onClick_WakeLock0(View v){
        if(((ToggleButton)v).isChecked()==true){
            mWakeLock.acquire();
        }
        else{
            mWakeLock.release();
        }
    }
    public void onClick_WakeLock1(View v){
        if(((ToggleButton)v).isChecked()==true){
            mWakeLock.acquire();
        }
        else{
            mWakeLock.release();
        }
    }
    public void onClick_WakeLock2(View v){
        if(((ToggleButton)v).isChecked()==true){
            mWakeLock.acquire();
        }
        else{
            mWakeLock.release();
        }
    }
    public void onClick_WakeLock3(View v){
        if(((ToggleButton)v).isChecked()==true){
            mWakeLock.acquire();
        }
        else{
            mWakeLock.release();
        }
    }

    public void onClick_AutoCount(View view){
        if(((ToggleButton)view).isChecked()){
            mThread.start();
        } else {
            mThread.interrupt();
        }
    }

    void UpdateCount(){
        tvShowCounter.setText("Counter= " + (mCounter++));
    }


    private static final int MSG_UPDATE_UI = 8888;
    private Thread mThread = new Thread(){
        @Override
        public void run() {
            try {
                for (; mCounter < MAX_COUNT; ) {
                    Thread.sleep(COUNT_DURATION);
                    mMain_Handler.sendEmptyMessage(MSG_UPDATE_UI);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    };
    private Handler mMain_Handler = new Handler(){
        public void handleMessage(Message msg){
            super.handleMessage(msg);
            switch(msg.what){
                case MSG_UPDATE_UI:
                    UpdateCount();
                    break;
                default:
                    break;
            }
        }
    };

}
