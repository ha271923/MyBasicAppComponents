package sample.hawk.com.mybasicappcomponents.background;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

import sample.hawk.com.mybasicappcomponents.utils.SMLog;

/**
 * Created by ha271 on 2016/7/13.
 */

public class MyJobSchedulerService extends JobService { // If APP killed for unknown reason, it will auto start at next APP launch.
    static int mJobRound;
    private UpdateAppsAsyncTask mMyAsyncTask = new UpdateAppsAsyncTask();
    private UpdateAppsThread mMyThreadTask;
    private final int MSG_ASYNC_JOB=0;
    private final int MSG_THREAD_JOB=1;
    private final int MSG_OTHER_JOB=2;
    private final int MSG_UPDATE_STATUS_COUNT=100;

    // Hawk: I implemented two conditions:
    // 1. AsyncTask
    // 2. Thread
    // private final int  JobType = MSG_ASYNC_JOB;
    // private String mStrJobType = new String("MSG_ASYNC_JOB");
    private final int  JobType = MSG_THREAD_JOB;
    private String mStrJobType = new String("MSG_THREAD_JOB");

    private boolean bForceStopJob = true;

    @Override
    public void onCreate() {
        super.onCreate();
        SMLog.i("MyJobSchedulerService::onCreate");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        SMLog.i("MyJobSchedulerService::onDestroy");
    }

    @Override
    public boolean onStartJob(JobParameters params ) {
        bForceStopJob = false;
        SMLog.i("onStartJob +++ id=" + params.getJobId());
        // if(MyJobSchedulerActivity.mJob_status !=null)
            MyJobSchedulerActivity.mJob_status.setText("JobService onStartJob +++ id=" + params.getJobId()); // Can UpdateUI in JobService directly
        Toast.makeText( getApplicationContext(), "MyJobSchedulerService onStartJob mJobRound="+(mJobRound++), Toast.LENGTH_SHORT ).show();
        if(JobType == MSG_ASYNC_JOB){
            mJobHandler.sendMessage( Message.obtain( mJobHandler, MSG_ASYNC_JOB, params ) );
            mMyAsyncTask.execute(params);
        }
        if(JobType == MSG_THREAD_JOB){
            mJobHandler.sendMessage( Message.obtain( mJobHandler, MSG_THREAD_JOB, params ) );
            mMyThreadTask = new UpdateAppsThread(params);
            mMyThreadTask.start();
        }
        Toast.makeText( getApplicationContext(), "onStartJob..... ", Toast.LENGTH_SHORT ).show();
        return true;
    }

    // 之前建立Job builder時, 若有設定過條件限定, 例如:setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY)或setRequiresDeviceIdle(true)
    // 在跑Job過程中, 一旦發生條件不符合情況或是發生cancelJob, 則會立即呼叫此callback, 注意!! 正常完整做完JOB時, 並不會呼叫此callback
    @Override
    public boolean onStopJob( JobParameters params ) {  // Not call here if Job normally finish.
        bForceStopJob = true;
        SMLog.i("onStopJob --- id=" + params.getJobId());
        // if(MyJobSchedulerActivity.mJob_status !=null)
            MyJobSchedulerActivity.mJob_status.setText("JobService onStopJob --- id="+params.getJobId()); // Can UpdateUI in JobService
        mJobRound =0;
        Toast.makeText( getApplicationContext(), "onStopJob!! ", Toast.LENGTH_SHORT ).show();
        boolean shouldReschedule = false;
        if(JobType == MSG_ASYNC_JOB){
            shouldReschedule = mMyAsyncTask.MyForceStopJob(params);
        }
        if(JobType == MSG_THREAD_JOB){
            mMyThreadTask.MyForceStopJob();
        }
        return shouldReschedule; // 被突然STOP的Job是否要參考之前Job builder的設定, 重新安排一次Job
    }

    // MainLooperHandler for update UI
    private Handler mJobHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage( Message msg ) {

            switch (msg.what) {
                case MSG_ASYNC_JOB:
                    break;
                case MSG_THREAD_JOB:
                    break;
                case MSG_OTHER_JOB:
                    break;

                case MSG_UPDATE_STATUS_COUNT: // MSG_UPDATE_STATUS_COUNT:
                    MyJobSchedulerActivity.mJob_status.setText("JobStatus = "+ msg.arg1);
                    break;

                default:
                    SMLog.e("Unrecognised message received.");
                    break;
            }
            SMLog.i("JobHandler handleMessage  msg="+msg.what+"   strJobType="+mStrJobType); // UI thread

            // if(MyJobSchedulerActivity.mJob_type !=null) // Exception occur if activity was killed by OS.
                MyJobSchedulerActivity.mJob_type.setText("JobType = "+mStrJobType);

            mJobHandler.removeMessages( msg.what );
            return true;
        }
    } );

    // A dummy job for testing
    private void DummyJob(int limit){
        for(int i=0;i<=limit;i++){
            SMLog.i("i= "+ i);
            if(bForceStopJob == true) {
                SMLog.i("Job Break!!");
                break;
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            mJobHandler.sendMessage( Message.obtain( mJobHandler, MSG_UPDATE_STATUS_COUNT, i, 0 ) );
        }
        bForceStopJob = false;
    }

    // AsyncTask for JobScheduler
    private class UpdateAppsAsyncTask extends AsyncTask<JobParameters, Void, JobParameters[]> {
        int mCounter;

        @Override
        protected JobParameters[] doInBackground(JobParameters... params) {
            // Do updating and stopping logical here.
            SMLog.i("UpdateAppsAsyncTask::doInBackground   TID="+Thread.currentThread().getId()); // Background thread
            // TODO: put your background job at here!!! WorkerThread
            DummyJob(10);
            return params;
        }

        @Override
        protected void onPostExecute(JobParameters[] result) {
            for (JobParameters params : result) {
                if (!hasJobBeenStopped(params)) {
                    SMLog.i("Finishing job XXX id=" + params.getJobId());
                    jobFinished(params, false);
                }
            }
        }

        public boolean hasJobBeenStopped(JobParameters params) {
            // Logic for checking stop.
            return bForceStopJob;
        }

        public boolean MyForceStopJob(JobParameters params) {
            SMLog.i("MyForceStopJob id=" + params.getJobId());
            // Logic for stopping a job. return true if job should be rescheduled.
            bForceStopJob = true;
            return false;
        }
    }

    public class UpdateAppsThread extends Thread {
        JobParameters mParams;

        public UpdateAppsThread(JobParameters params) {
            super();
            mParams = params;
        }

        @Override
        public void run() {
            super.run();
            DummyJob(10);
            SMLog.i("Finishing job XXX id=" + mParams.getJobId());
            jobFinished(mParams, false);
        }

        public boolean hasJobBeenStopped(JobParameters params) {
            // Logic for checking stop.
            return bForceStopJob;
        }
        public void MyForceStopJob(){
            bForceStopJob = true;
        }
    }

}
