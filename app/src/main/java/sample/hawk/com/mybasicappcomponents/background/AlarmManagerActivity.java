package sample.hawk.com.mybasicappcomponents.background;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ToggleButton;


import sample.hawk.com.mybasicappcomponents.R;
import sample.hawk.com.mybasicappcomponents.utils.SMLog;

/**
 * Created by ha271 on 2016/12/8.
 */

public class AlarmManagerActivity extends Activity {

    public  static final int MY_REQUEST_CODE = 123456789;// Hawk: To Prvent two pendingIntent overwrite, you can define the diff REQUEST_CODE everytime.

    public ToggleButton mMyAlarmManagerToggleBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alarmmanageractivity);
        mMyAlarmManagerToggleBtn= (ToggleButton) findViewById(R.id.AlarmManagerToggleBtn);
        mMyAlarmManagerToggleBtn.setOnClickListener(mMyAlarmManagerToggleBtnListener);
    }

    private View.OnClickListener mMyAlarmManagerToggleBtnListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            SMLog.i();
            if(mMyAlarmManagerToggleBtn.isChecked()){
                scheduleAlarm(true);
            }
            else{
                scheduleAlarm(false);
            }
        }
    };

    public void scheduleAlarm(boolean enable) {
        AlarmManager mAlarmMgr = (AlarmManager) this.getSystemService(Context.ALARM_SERVICE);
/*
    // WAY1: BroadcastReceiver : The specific receiver can get an intent if Alarm arrived!
        Intent intent = new Intent(AlarmManagerActivity.this, MyReceiver.class);
        intent.setAction("sample.hawk.com.mybasicappcomponents.alarmmanager");
        final PendingIntent alarmIntent = PendingIntent.getBroadcast(AlarmManagerActivity.this, 1, intent, PendingIntent.FLAG_UPDATE_CURRENT);
*/

        // WAY2: Service : The specific service can get an intent if Alarm arrived!
        Intent intent = new Intent(AlarmManagerActivity.this, MyNotificationService.class);
        final PendingIntent alarmIntent = PendingIntent.getService(AlarmManagerActivity.this, MY_REQUEST_CODE, intent, PendingIntent.FLAG_UPDATE_CURRENT );
/*
    // WAY3: Activity : The specific Activity can get an intent if Alarm arrived!
        Intent intent = new Intent(AlarmManagerActivity.this, LifeCycleActivity.class);
        final PendingIntent alarmIntent = PendingIntent.getActivity(AlarmManagerActivity.this, MY_REQUEST_CODE, intent, PendingIntent.FLAG_UPDATE_CURRENT );
*/

        if (enable == true) {
            // Hawk: Min duration forced up to 60000 as of Android 5.1
            mAlarmMgr.setInexactRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + 10*1000, 60*1000, alarmIntent);
            // mAlarmMgr.set(AlarmManager.RTC_WAKEUP, firstMillis + 10*1000, alarmIntent); // No repeat cast.
        } else {
            if (mAlarmMgr != null) {
                mAlarmMgr.cancel(alarmIntent);
            }
        }
    }


}
