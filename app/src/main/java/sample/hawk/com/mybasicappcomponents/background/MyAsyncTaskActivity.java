package sample.hawk.com.mybasicappcomponents.background;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import sample.hawk.com.mybasicappcomponents.R;
import sample.hawk.com.mybasicappcomponents.utils.SMLog;

/**
 * Created by ha271 on 2016/12/14.
 */

public class MyAsyncTaskActivity extends Activity implements MyAsyncTaskApi.MyAsyncTaskApiCallback {

    Context m_context;
    public static ProgressBar mMainActivityProgressBar;
    public ToggleButton mMyAsyncTaskToggleBtn;
    public Button mAsyncTaskApiBtn;
    public TextView mMyOutputTextView;
    public static int pa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        m_context = this;
        setContentView(R.layout.myasynctask);
        mMainActivityProgressBar = (ProgressBar) findViewById(R.id.myainactivity_progressBar);
        mMyOutputTextView        = (TextView) findViewById(R.id.OutputTextView);
        mMyAsyncTaskToggleBtn    = (ToggleButton) findViewById(R.id.AsyncTaskToggleBtn);
        mMyAsyncTaskToggleBtn.setOnClickListener(mMyAsyncTaskToggleBtnListener);
        mAsyncTaskApiBtn    = (Button) findViewById(R.id.AsyncTaskApiBtn);
        mAsyncTaskApiBtn.setOnClickListener(mMyAsyncTaskApiBtnListener);
        mMyTask= new  MyAsyncTask();
    }


    private View.OnClickListener mMyAsyncTaskToggleBtnListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            Intent intent = new Intent(m_context, MyIntentService.class);
            SMLog.i();
            if(mMyAsyncTaskToggleBtn.isChecked()){
                // Hawk: Update UI in AsyncTask's override APIs.
                mMyTask= new  MyAsyncTask(); // AsyncTask can be executed only once, so new it everytime.
                mMyTask.execute( "Start MyAsyncTask job!" , "InputParam2"/*param1*/);
            }
            else{
                // Hawk: Update UI in AsyncTask's override APIs.
                if(mMyTask!=null)
                    mMyTask.cancel( true );
            }
        }
    };


    // use an independent executor for MyAsyncTaskApi;
    static private final AtomicInteger mCount = new AtomicInteger(1);
    private static class WorkerThreadFactory implements ThreadFactory {
        private static final String PREFIX = "My WorkerThread # ";

        @Override
        public Thread newThread(Runnable runnable) {
            return new Thread(runnable, PREFIX + mCount.getAndIncrement());
        }
    }
    static public Executor ASYNCTASKAPI_EXECUTOR = new ThreadPoolExecutor(0, 1,
            0L, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<Runnable>(), new WorkerThreadFactory());
    private View.OnClickListener mMyAsyncTaskApiBtnListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            MyAsyncTaskApi.MyAsyncTaskApi(m_context, null, (MyAsyncTaskActivity)m_context, ASYNCTASKAPI_EXECUTOR); // TODO: follow asyncBlur to write this code
        }
    };

    // AsyncTask can only start by UI thread.

    // CLASS: AsyncTask Class ------------------------------------------------------
    // UPDATE_UI WAY3: AsyncTask can update UI easily.
    //param1：向後台任務的執行方法傳遞參數的類型；
    //param2：在後台任務執行過程中，要求主UI thread處理中間狀態，通常是一些UI處理中傳遞的參數類型；
    //param3：return value
    private  MyAsyncTask mMyTask;
    public class MyAsyncTask extends AsyncTask<String/*param1*/,String/*param2*/,String/*param3*/>
    {
        // Update UI before job start.
        protected void onPreExecute() {
            SMLog.i();
            Toast.makeText(m_context, "AsyncTask start..." + this,Toast.LENGTH_SHORT).show();
        }
        @Override
        protected String/*param3*/ doInBackground(String... params/*param1*/) { // WorkerThread at here!
            SMLog.i();
            // param1 == params == {params[0], params[1], ....}
            String strInput1 = params[0];  // params[0] == "Start MyAsyncTask job!"
            String strInput2 = params[1]; // params[1] == "InputParam2"
            // TODO: WORKTHREAD at here for implement your job!
            for (int i = 0; i < 1000; i++) {
                publishProgress(strInput1+i/*param2*/); // Hawk: related with onProgressUpdate()
                SystemClock.sleep(10);
                SMLog.i("["+i+"]  AsyncTask::doInBackground" + "   strInput1="+strInput1+ "   strInput2="+strInput2); // Hawk: Activity BACK or TaskSwitch does NOT stop this Job.
                // Hawk: Activity BACK or TaskSwitch does NOT stop this Job.
            }
            return "AsyncTask finish!";
        }
        // Update UI during Job is working.
        protected void onProgressUpdate(String... values/*param2*/ ) {
            SMLog.i();
            mMyOutputTextView.setText(values[0]);
            if(pa>100)
                pa=0;
            mMainActivityProgressBar.setProgress(pa++);
        }

        // Update UI after job done.
        @Override
        protected void onPostExecute(String result/*param3*/) {
            super.onPostExecute(result);
            SMLog.i();
            Toast.makeText(m_context, result, Toast.LENGTH_SHORT).show();
            mMyOutputTextView.setText(result);
        }
        // Update UI after job cancelled.
        @Override
        protected void onCancelled() {
            SMLog.i();
            mMyOutputTextView.setText("AsyncTask cancelled");
        }
    }

    // API: AsyncTask built-in API ------------------------------------------------------
    @Override
    public void onAsyncTaskApiCompleted(Bitmap[] blurredBitmaps) {
        SMLog.i("onAsyncTaskApiCompleted");
    }
}
