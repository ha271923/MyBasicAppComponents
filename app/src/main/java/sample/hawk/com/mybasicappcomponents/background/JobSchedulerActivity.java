package sample.hawk.com.mybasicappcomponents.background;

import android.app.Activity;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.ToggleButton;

import sample.hawk.com.mybasicappcomponents.R;
import sample.hawk.com.mybasicappcomponents.utils.SMLog;

/**
 * Created by ha271 on 2016/12/8.
 */

public class JobSchedulerActivity extends Activity {

    public static TextView mJob_status;
    public ToggleButton mMyJobSchedulerToggleBtn;
    private JobScheduler mMyJobScheduler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jobscheduler);
        mMyJobSchedulerToggleBtn= (ToggleButton) findViewById(R.id.JobSchedulerToggleBtn);
        mMyJobSchedulerToggleBtn.setOnClickListener(mMyJobSchedulerToggleBtnListener);
        mJob_status = (TextView) findViewById(R.id.job_status);
    }

    private View.OnClickListener mMyJobSchedulerToggleBtnListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            SMLog.i();
            if(mMyJobSchedulerToggleBtn.isChecked())
            { // BUG: No response
                final int JOB_ID = 1;
                int REFRESH_INTERVAL = 3000; // Run Job every 3sec.
                mMyJobScheduler = (JobScheduler) getSystemService( Context.JOB_SCHEDULER_SERVICE );
                ComponentName serviceName = new ComponentName( getPackageName(), MyJobSchedulerService.class.getName() );
                JobInfo.Builder builder = new JobInfo.Builder(JOB_ID, serviceName);;

                if (Build.VERSION.SDK_INT >= 24) { // BUG: JobScheduler is not work. pls follow Google Latest Sample since it's always improving by Google new SDK.
                    // TODO: follow Google Sample: https://github.com/googlesamples/android-JobScheduler
                    REFRESH_INTERVAL = 60*1000;
                    builder.setMinimumLatency(REFRESH_INTERVAL);
                } else {
                    builder.setPeriodic(REFRESH_INTERVAL);
                }
                builder.build();
                builder.setRequiresCharging(true);
                builder.setRequiresDeviceIdle(true);
                if( mMyJobScheduler.schedule( builder.build() ) <= 0 ) {
                    SMLog.e("[Hawk]","mMyJobScheduler is failed!!");
                }
            }
            else
            {
                mMyJobScheduler.cancelAll();
            }
        }
    };

}
