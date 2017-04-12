package sample.hawk.com.mybasicappcomponents.background;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.concurrent.CopyOnWriteArrayList;

import sample.hawk.com.mybasicappcomponents.R;
import sample.hawk.com.mybasicappcomponents.utils.SMLog;

/**
 * Created by ha271 on 2017/4/11.
 */

public class MyExecutorServiceActivity extends Activity {
    //List<String> mJobArrayList = synchronizedList(new ArrayList<String>());
    CopyOnWriteArrayList<String> mJobArrayList = new CopyOnWriteArrayList<String>();
    MyExecutorService mMyExecutorService;

    private int mJobDoneCount = 0;
    private final int mJobCount = 100;
    JobCallBack mJobCallBack;
    interface JobCallBack{
        void onJobStart();
        void onJobDone();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myexecutorserviceactivity);
        mMyExecutorService = new MyExecutorService();
        mJobCallBack = new JobCallBack() {
            ProgressBar myexecutorserviceactivity_progressBar;
            TextView myexecutorserviceactivity_progressBarText;

            @Override
            public void onJobStart() {
                myexecutorserviceactivity_progressBar = (ProgressBar)findViewById(R.id.myexecutorserviceactivity_progressBar);
                myexecutorserviceactivity_progressBarText = (TextView)findViewById(R.id.myexecutorserviceactivity_progressBarText);
                myexecutorserviceactivity_progressBarText.setText("( "+mJobDoneCount+" / "+mJobCount+" )");
            }

            @Override
            public void onJobDone() {
                synchronized (this) { // You MUST synchronized it, otherwise mJobDoneCount value will not right.
                    ++mJobDoneCount;
                    if(mJobDoneCount >= mJobCount ){
                        SMLog.i("MultiTasking: All Jobs List ===============");
                        for(String str : mJobArrayList){
                            SMLog.i(str);
                        }
                        mJobDoneCount = 0;
                    }
                    else
                    {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                myexecutorserviceactivity_progressBarText.setText("( "+mJobDoneCount+" / "+mJobCount+" )");
                            }
                        });
                    }
                }
            }
        };
    }

    public void onClickExecuteServiceButton(View v){
        SMLog.i("ExecuteServiceButton");
        mMyExecutorService.CreateManyJobs_ByExecutorService(mJobArrayList,mJobCount, mJobCallBack);
    }

    public void onClickThreadButton(View v){
        SMLog.i("Thread");
        mMyExecutorService.CreateManyJobs_ByThread(mJobArrayList,mJobCount, mJobCallBack);
    }


}
