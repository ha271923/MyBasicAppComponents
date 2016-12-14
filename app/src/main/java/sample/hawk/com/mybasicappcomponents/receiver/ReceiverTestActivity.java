package sample.hawk.com.mybasicappcomponents.receiver;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import sample.hawk.com.mybasicappcomponents.R;
import sample.hawk.com.mybasicappcomponents.utils.SMLog;

/**
 * Created by ha271 on 2016/12/8.
 */

public class ReceiverTestActivity extends Activity {
    // This doesn't defined in AndroidManifest.xml
    public static final String UPDATE_ACTIVITY_ACTION = "sample.hawk.com.mybasicappcomponents.MainActivity.UPDATE_UI_ACTION";
    public static final String UPDATE_ACTIVITY_PB_ACTION = "sample.hawk.com.mybasicappcomponents.MainActivity.UPDATE_PB_ACTION";

    Context mContext;
    public Button mMyReceiverBtn;
    public Button mMyBroadcastBtn;
    public static TextView mReceiver_status;
    public ProgressBar mMyReceiverProgressBar;
    private static int ps;

    BroadcastReceiver mMyReceiver = new MyReceiver();
    InnerReceiver mInnerReceiver;    // UPDATE_UI by receiver: an inner class for receive ui update event.

    public class InnerReceiver extends BroadcastReceiver { // inner class can't be registered by AndroidManifest.xml.
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (UPDATE_ACTIVITY_ACTION.equals(action)) {
                Bundle bundle = intent.getExtras();
                String To_mMyOutputView = bundle.getString("To_mMyOutputView");
                mReceiver_status.setText(To_mMyOutputView);
                if (ps > 100)
                    ps = 0;
                mMyReceiverProgressBar.setProgress(ps++);
            }
            if (UPDATE_ACTIVITY_PB_ACTION.equals(action)) {
                if (ps > 100)
                    ps = 0;
                mMyReceiverProgressBar.setProgress(ps++);
            }
        }
    }

    private void register_InnerReceiver() {
        mInnerReceiver = new InnerReceiver();
        IntentFilter filter = new IntentFilter(UPDATE_ACTIVITY_ACTION);
        registerReceiver(mInnerReceiver, filter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setContentView(R.layout.activityinnerreceiver);
        mMyReceiverBtn = (Button) findViewById(R.id.ReceiverBtn);
        mMyBroadcastBtn = (Button) findViewById(R.id.BroadcastBtn);
        mReceiver_status= (TextView) findViewById(R.id.receiver_status);
        mMyReceiverProgressBar = (ProgressBar) findViewById(R.id.myreceiver_progressBar);

        mMyReceiverBtn.setOnClickListener(mMyReceiverBtnListener);
        mMyBroadcastBtn.setOnClickListener(mMyBroadcastBtnListener);

        register_InnerReceiver();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mInnerReceiver);
    }


    //Only For BroadcastReceiver -------------------------------------------------------------------------
    private View.OnClickListener mMyReceiverBtnListener = new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            SMLog.i();
            // init Receive side.
            IntentFilter intentFilterForReceiver = new IntentFilter("sample.hawk.com.mybasicappcomponents.broadcast1");
            // <!-- Dynamically register MyReceiver  -->
            registerReceiver(mMyReceiver, intentFilterForReceiver);
        }
    };
    private View.OnClickListener mMyBroadcastBtnListener = new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            SMLog.i();
            // send a broadcast only for this APP.
            Intent intent = new Intent();
            intent.setAction("sample.hawk.com.mybasicappcomponents.broadcast1");
            sendBroadcast(intent);
        }
    };

}
