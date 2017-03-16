package sample.hawk.com.mybasicappcomponents.background;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import sample.hawk.com.mybasicappcomponents.utils.SMLog;

/**
 SMLog() shows log as following.
 .background.MyThread :: run() Looper.loop() --------------------------
 .background.MyThreadActivity$1 :: onClick()
 .background.MyThread :: run() Looper.prepare() ++++++++++++++++++++++++++ tID=20072
 .background.MyThread :: run() Looper.prepare() --------------------------
 .background.MyThread :: run() Handler E +++ tID=20072
 .background.MyThread :: run() Handler E ---
 .background.MyThread :: run() Looper.loop() ++++++++++++++++++++++++++
 .background.MyThread$2 :: run() Handler B +++ tID=20072
 .background.MyThread$2 :: run() Handler B ---
 .background.MyThread$3 :: run() Handler C +++ tID=20072
 .background.MyThread$3 :: run() Handler C ---
 .background.MyThread$4 :: run() Handler D +++ tID=1 ( UI thread )
 .background.MyThread$4 :: run() Handler D ---

 */


//region = 寫法0: Thread*1 + Handler*2(UI-->Worker,UI<--Worker),  更新UI與DATA
public class MyThread extends Thread {
    private static final String TAG = "[MyThread]";
    private boolean mGo = true;
    private final int MSG_UPDATE_UI=123454321;
    public android.os.Handler mFromUI_handler;
    private Context mContext;
    Looper mLooper;

    public MyThread(Context context) {
        super();
        // Hawk: UI thread.
        mContext = context;
    }

    @Override
    public void run() { // TODO: In MyThread class, this is the only routine run on background.
        super.run();
        // Hawk: 不想控制looper可以改用HandlerThread
        SMLog.i(TAG,"Looper.prepare() ++++++++++++++++++++++++++ tID=" + Thread.currentThread().getId());
        Looper.prepare(); // // Hawk: UI thread --> MyThread , this prepare is for receiving msg from UI.
        mLooper = Looper.myLooper();
        SMLog.i(TAG,"Looper.prepare() --------------------------");
        mFromUI_handler = new android.os.Handler(){ // Hawk: In order to run on background, new it here.
            @Override
            public void handleMessage(Message msg) {
                SMLog.i(TAG,"Handler A +++ tID=" + Thread.currentThread().getId());
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
                                mUpdateUI_Handler.sendMessage(FromWorkerT_msg); // UI thread <-- MyThread
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
                SMLog.i(TAG,"Handler A ---");
            }
        };

        new Handler().post(new Runnable(){
            @Override
            public void run() {
                SMLog.i(TAG,"Handler B +++ tID=" + Thread.currentThread().getId());
                SMLog.i(TAG,"Handler B ---");
            }
        });
        new Handler().post(new Runnable(){
            @Override
            public void run() {
                SMLog.i(TAG,"Handler C +++ tID=" + Thread.currentThread().getId());
                SMLog.i(TAG,"Handler C ---");
            }
        });
        ((Activity) mContext).runOnUiThread(new Runnable() {
            public void run() {
                SMLog.i(TAG,"Handler D +++ tID=" + Thread.currentThread().getId());
                SMLog.i(TAG,"Handler D ---");
            }
        });
        SMLog.i(TAG,"Handler E +++ tID=" + Thread.currentThread().getId());
        SMLog.i(TAG,"Handler E ---");

        SMLog.i(TAG,"Looper.loop() ++++++++++++++++++++++++++");
        Looper.loop(); // Enter this API will never run the following code.
        SMLog.i(TAG,"Looper.loop() --------------------------");
        mFromUI_handler = null;
    }

    public void stopThread()
    {
        mGo = false; // Hawk: If you want to stop the thread immediately, thread can support exception too.
        mLooper.quit();  // Hawk: call it will exit Looper.loop().
    }

    private android.os.Handler mUpdateUI_Handler = new android.os.Handler(){ // Hawk: UI thread <-- MyThread
        @Override
        public void handleMessage(Message msg) {
            // This function is already running on UI thread now.
            Object obj = msg.obj;
            switch (msg.what){
                case MSG_UPDATE_UI:
                    MyThreadActivity.mMainActivityProgressBar.setProgress((int)obj);
                    break;
                default:
                    break;
            }
        }
    };
}
//endregion