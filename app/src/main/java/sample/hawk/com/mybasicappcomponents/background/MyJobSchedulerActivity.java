package sample.hawk.com.mybasicappcomponents.background;

import android.app.Activity;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.ToggleButton;

import sample.hawk.com.mybasicappcomponents.R;
import sample.hawk.com.mybasicappcomponents.utils.NetworkUtils;
import sample.hawk.com.mybasicappcomponents.utils.SMLog;
import sample.hawk.com.mybasicappcomponents.utils.Util;

/**
 * Created by ha271 on 2016/12/8.
 */

public class MyJobSchedulerActivity extends Activity {

    public static TextView mJob_status;
    public static TextView mJob_type;
    public ToggleButton mMyJobSchedulerToggleBtn;
    private JobScheduler mMyJobScheduler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jobscheduler_activity);
        mMyJobSchedulerToggleBtn= (ToggleButton) findViewById(R.id.JobSchedulerToggleBtn);
        mMyJobSchedulerToggleBtn.setOnClickListener(mMyJobSchedulerToggleBtnListener);
        mJob_status = (TextView) findViewById(R.id.job_status);
        mJob_type = (TextView) findViewById(R.id.job_type);
        SMLog.i("API level="+Build.VERSION.SDK_INT);
        SMLog.i("isCharging="+Util.isPhonePluggedIn(this));
        SMLog.i("NetworkType="+ NetworkUtils.isConnected(this));
    }

    @Override
    protected void onStop() {
        // A service can be "started" and/or "bound". In this case, it's "started" by this Activity
        // and "bound" to the JobScheduler (also called "Scheduled" by the JobScheduler). This call
        // to stopService() won't prevent scheduled jobs to be processed. However, failing
        // to call stopService() would keep it alive indefinitely.
        stopService(new Intent(this, MyJobSchedulerService.class));
        super.onStop();
    }

    private View.OnClickListener mMyJobSchedulerToggleBtnListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            SMLog.i();
            if(mMyJobSchedulerToggleBtn.isChecked())
            { // BUG: No response
                SMLog.i("ENABLE JobScheduler");
                final int JOB_ID = 1;
                int REFRESH_INTERVAL;
                mMyJobScheduler = (JobScheduler) getSystemService( Context.JOB_SCHEDULER_SERVICE );
                ComponentName serviceName = new ComponentName( getPackageName(), MyJobSchedulerService.class.getName() );
                JobInfo.Builder builder = new JobInfo.Builder(JOB_ID, serviceName);
                // Android framework defined EXECUTING_TIMESLICE_MILLIS for duration control.
                // http://stackoverflow.com/questions/27719218/android-jobscheduler-always-works-for-1-minute
                // EXECUTING_TIMESLICE_MILLIS =  ?         // >=API21(5.0),   ?
                // EXECUTING_TIMESLICE_MILLIS =  1*60*1000 // >=API22(5.1.1), 1minute
                // EXECUTING_TIMESLICE_MILLIS = 10*60*1000 // >=API23(6.0),   10minutes
                if (Build.VERSION.SDK_INT >= 23) {
                    // Google Sample: https://github.com/googlesamples/android-JobScheduler
                    REFRESH_INTERVAL = 10*60*1000;
                } else {
                    REFRESH_INTERVAL = 1*60*1000; // Run Job period MUST large than this value.
                }
                SMLog.i("REFRESH_INTERVAL="+REFRESH_INTERVAL);
                // builder.setMinimumLatency(REFRESH_INTERVAL); // 條件: 當條件均滿足後, 仍要等待此時間條件參數滿足
                // builder.setRequiresCharging(true); // 條件:充電狀態下
                // builder.setRequiresDeviceIdle(true); // 條件:裝置處於閒置狀態一段時間
                builder.setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY); // 只有透過某種介面連接網絡時
                //builder.setPersisted(boolean isPersisted); // 設備重新啟動後，Job是否應該繼續存在
                // builder.setPeriodic(REFRESH_INTERVAL); // 條件:週期性執行Job, 與setOverrideDeadline() 須擇一
                builder.setOverrideDeadline(0); // 條件: 無論條件參數是否滿足, 到達此時間後, 必須強制執行Job, 執行時仍會依照setMinimumLatency參數等待,  當需要立即執行時使用, Can't call setOverrideDeadline() on a periodic job.
                builder.build();

                if( mMyJobScheduler.schedule( builder.build() ) <= 0 ) {
                    SMLog.e("[Hawk]","mMyJobScheduler is failed!!");
                }
            }
            else
            {
                SMLog.i("CANCEL JobScheduler");
                mMyJobScheduler.cancelAll();
            }
        }
    };

}
