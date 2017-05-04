package sample.hawk.com.mybasicappcomponents.debugTest.autoTest;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ToggleButton;

import sample.hawk.com.mybasicappcomponents.R;

/**
 * Created by ha271 on 2017/5/4.
 */

public class MyAutoClickActivity extends Activity{
    private int mClickCounter;
    private final int MAX_CLICK_COUNT = 1000000;
    private final int CLICK_DURATION = 1*1000;

    TextView tvClickCounter;
    Button SingleClickBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myautoclickactivity);
        tvClickCounter = (TextView) findViewById(R.id.tvClickCounter);
        SingleClickBtn = (Button) findViewById(R.id.SingleClickBtn);

    }

    public void onClick_SingleClick(View view){
        UpdateCount();
    }


    public void onClick_AutoClick(View view){
        if(((ToggleButton)view).isChecked()){
            mThread.start();
        } else {
            mThread.interrupt();
        }
    }

    void UpdateCount(){
        tvClickCounter.setText("mClickCounter= " + (mClickCounter++));
    }


    private static final int MSG_UPDATE_UI = 44444;
    private Thread mThread = new Thread(){
        @Override
        public void run() {
            try {
                for (; mClickCounter < MAX_CLICK_COUNT; ) {
                    Thread.sleep(CLICK_DURATION);
                    mMain_Handler.sendEmptyMessage(MSG_UPDATE_UI);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    };
    private Handler mMain_Handler = new Handler(){
        public void handleMessage(Message msg){
            super.handleMessage(msg);
            switch(msg.what){
                case MSG_UPDATE_UI:
                    SingleClickBtn.performClick();
                    break;
                default:
                    break;
            }
        }
    };

}
