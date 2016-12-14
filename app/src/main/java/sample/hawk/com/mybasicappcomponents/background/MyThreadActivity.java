package sample.hawk.com.mybasicappcomponents.background;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.ToggleButton;

import sample.hawk.com.mybasicappcomponents.R;
import sample.hawk.com.mybasicappcomponents.utils.SMLog;

/**
 * Created by ha271 on 2016/12/14.
 */

public class MyThreadActivity extends Activity {
    Context mContext;
    public static ProgressBar mMainActivityProgressBar;
    public ToggleButton mMyThreadToggleBtn;
    public Button mSendMsg_fromUI_to_Thread_Btn;
    private MyThread mMyThread1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;

        setContentView(R.layout.mythreadactivity);
        mMainActivityProgressBar = (ProgressBar) findViewById(R.id.myainactivity_progressBar);
        mMyThreadToggleBtn= (ToggleButton) findViewById(R.id.ThreadToggleBtn);
        mMyThreadToggleBtn.setOnClickListener(mMyThreadToggleBtnListener);
        mSendMsg_fromUI_to_Thread_Btn = (Button) findViewById(R.id.SendMsg_fromUI_to_Thread_Btn);
        mSendMsg_fromUI_to_Thread_Btn.setOnClickListener(mSendMsg_fromUI_to_Thread_BtnListener);

    }

    private View.OnClickListener mMyThreadToggleBtnListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            SMLog.i();
            if(mMyThreadToggleBtn.isChecked())
            {
                mMyThread1 = new MyThread(mContext);
                mMyThread1.start();
                // CAUTION! mMyThread1.mFromUI_handler may not ready immediately, don't call it right now!
            }
            else
            {
                if(mMyThread1!=null)
                    mMyThread1.stopThread();
            }
        }
    };

    private View.OnClickListener mSendMsg_fromUI_to_Thread_BtnListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Message FromUI_msg;
            if(mMyThread1.mFromUI_handler!=null)
            {
                // run JOB1 first
                FromUI_msg=mMyThread1.mFromUI_handler.obtainMessage();
                FromUI_msg.what=11111111;
                mMyThread1.mFromUI_handler.sendMessage(FromUI_msg);
                // if JOB1 not finish yet, msg will be QUEUE.
                FromUI_msg=mMyThread1.mFromUI_handler.obtainMessage();
                FromUI_msg.what=22222222;
                mMyThread1.mFromUI_handler.sendMessage(FromUI_msg);
            }
            else
            {
                SMLog.e("Worker thread's handler is not ready!");
            }
        }
    };
}
