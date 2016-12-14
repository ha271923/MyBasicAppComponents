package sample.hawk.com.mybasicappcomponents.background;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

import sample.hawk.com.mybasicappcomponents.utils.SMLog;

/**
 * Created by ha271 on 2016/7/13.
 */

public class MyJobSchedulerService extends JobService { // If APP killed for unknown reason, it will auto start at next APP launch.
    static int mCount;
    private Handler mJobHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage( Message msg ) {
            SMLog.i();
            switch (msg.what) {
                case 0: // MSG_EXECUTE_JOB:
                    break;
                case 1: // MSG_STOP_JOB:
                    break;
                case 2: // MSG_JOB_FINISHED:
                    break;
                default:
                    SMLog.e("MyJobSchedulerService", "Unrecognised message received.");
                    break;
            }
            if(JobSchedulerActivity.mJob_status !=null) // Exception occur if activity was killed by OS.
                JobSchedulerActivity.mJob_status.setText("JobService handleMessage");
            jobFinished( (JobParameters) msg.obj, false );
            return true;
        }
    } );

    @Override
    public boolean onStartJob(JobParameters params ) {
        SMLog.i();
        if(JobSchedulerActivity.mJob_status !=null)
            JobSchedulerActivity.mJob_status.setText("JobService onStartJob"); // UpdateUI in JobService directly
        Toast.makeText( getApplicationContext(), "MyJobSchedulerService onStartJob mCount="+(mCount++), Toast.LENGTH_SHORT ).show();
        mJobHandler.sendMessage( Message.obtain( mJobHandler, 1, params ) );
        return true;
    }

    @Override
    public boolean onStopJob( JobParameters params ) {
        SMLog.i();
        if(JobSchedulerActivity.mJob_status !=null)
            JobSchedulerActivity.mJob_status.setText("JobService onStopJob!!"); // UpdateUI in JobService
        mCount =0;
        Toast.makeText( getApplicationContext(), "MyJobSchedulerService onStopJob!! ", Toast.LENGTH_SHORT ).show();
        mJobHandler.removeMessages( 1 );
        return false;
    }
}
