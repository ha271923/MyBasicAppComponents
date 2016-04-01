package sample.hawk.com.mybasicappcomponents;

import android.content.Context;
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

    @Override
    public void run() { // WorkerThread now!
        super.run();
        try
        {
            for(int i=0;i<100;i++)
            {
                Message msg = new Message(); // new it every time for system QUEUE.
                msg.what = MSG_UPDATE_UI;
                msg.obj = i;
                if(mGo == true)
                {
                    SMLog.i(TAG, "i=" + i + " tID=" + Thread.currentThread().getId());
                    sleep(1000);
                    mUpdateUI_Handler.sendMessage(msg);
                }
                else // for stop condition:
                {
                    break;
                }
                if(i==100)
                    i=0;
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
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
