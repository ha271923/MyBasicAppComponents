package sample.hawk.com.mybasicappcomponents;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.widget.ProgressBar;

import sample.hawk.com.mybasicappcomponents.utils.SMLog;

/**
 * Created by ha271 on 2016/3/31.
 */
public class MyThread extends Thread {
    private static final String TAG = "[MyThread]";
    private boolean mGo = true;
    private final int MSG_UPDATE_UI=123454321;
    public android.os.Handler mFromUI_handler;

    public MyThread() {
        super();
        // Hawk: UI thread.
    }

    @Override
    public void run() { // TODO: In MyThread class, this is the only routine run on background.
        super.run();
        Looper.prepare(); // For receive msg from UI thread.
        mFromUI_handler = new android.os.Handler(){ // Hawk: In order to run on background, new it here.
            @Override
            public void handleMessage(Message msg) {
                switch(msg.what){
                    case 11111111:  // JOB1
                        SMLog.i();
                        for(int i=0;i<100;i++) // For send msg to UI thread.
                        {
                            Message FromWorkerT_msg = new Message(); // new it every time for system QUEUE.
                            FromWorkerT_msg.what = MSG_UPDATE_UI;
                            FromWorkerT_msg.obj = i;
                            if(mGo == true)
                            {
                                SMLog.i(TAG, "JOB1: i=" + i + " tID=" + Thread.currentThread().getId());
                                try
                                {
                                    sleep(1000);
                                }
                                catch (Exception e)
                                {
                                    e.printStackTrace();
                                }
                                mUpdateUI_Handler.sendMessage(FromWorkerT_msg);
                            }
                            else // for stop condition:
                            {
                                break;
                            }
                            if(i==100)
                                i=0;
                        }
                        break;
                    case 22222222: // JOB2
                        SMLog.i(TAG, "JOB2:  tID=" + Thread.currentThread().getId());
                        break;
                }
            }
        };
        SMLog.i(TAG,"Looper.loop() ++++++++++++++++++++++++++");
        Looper.loop(); // Enter this API will never run the following code.
        SMLog.i(TAG,"Looper.loop() --------------------------");
        mFromUI_handler = null;
    }

    public void stopThread()
    {
        mGo = false;
    }

    private android.os.Handler mUpdateUI_Handler = new android.os.Handler(){
        @Override
        public void handleMessage(Message msg) {
            // This function is already running on UI thread now.
            Object obj = msg.obj;
            switch (msg.what){
                case MSG_UPDATE_UI:
                    MainActivity.mMainActivityProgressBar.setProgress((int)obj);
                    break;
                default:
                    break;
            }
        }
    };
}
