package sample.hawk.com.mybasicappcomponents;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import sample.hawk.com.mybasicappcomponents.utils.SMLog;

public class MainActivity extends Activity{
    public Button mMyActivityBtn;
    public Button mMyServiceBtn;
    public Button mMyReceiverBtn;public Button mMyBroadcastBtn;public TextView mMyReceivedOutputTextView;
    public Button mMyProviderBtn;
    private BroadcastReceiver mMyReceiver = new MyReceiver();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); SMLog.i();

        // View
        setContentView(R.layout.activity_main);
        // Presenter
        mMyActivityBtn = (Button) findViewById(R.id.ActivityBtn);
        mMyServiceBtn  = (Button) findViewById(R.id.ServiceBtn);
        mMyReceiverBtn = (Button) findViewById(R.id.ReceiverBtn);
        mMyBroadcastBtn = (Button) findViewById(R.id.BroadcastBtn);
        mMyReceivedOutputTextView = (TextView) findViewById(R.id.ReceivedOutputTextView);
        mMyProviderBtn = (Button) findViewById(R.id.ProviderBtn);
        mMyActivityBtn.setOnClickListener(mMyActivityBtnListener);
        mMyServiceBtn.setOnClickListener(mMyServiceBtnListener);
        mMyReceiverBtn.setOnClickListener(mMyReceiverBtnListener);
        mMyBroadcastBtn.setOnClickListener(mMyBroadcastBtnListener);
        mMyProviderBtn.setOnClickListener(mMyProviderBtnListener);
    }


    // Model
    private OnClickListener mMyActivityBtnListener =new OnClickListener() {
        @Override
        public void onClick(View v) {
            SMLog.i();
            Intent  intent = new Intent();
            intent.setClass( MainActivity.this, MyActivity.class);
            startActivity(intent);
        }
    };
    private OnClickListener mMyServiceBtnListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            SMLog.i();
            Intent intent = new Intent();
            intent.setClass( MainActivity.this, MyService.class);
            startService(intent);
        }
    };

    private OnClickListener mMyReceiverBtnListener = new OnClickListener(){
        @Override
        public void onClick(View v) {
            SMLog.i();
            // init Receive side.
            IntentFilter intentFilterForReceiver = new IntentFilter("sample.hawk.com.mybasicappcomponents.broadcast1");
            // <!-- Dynamically register MyReceiver  -->
            registerReceiver(mMyReceiver, intentFilterForReceiver);
        }
    };
    private OnClickListener mMyBroadcastBtnListener = new OnClickListener(){
        @Override
        public void onClick(View v) {
            SMLog.i();
            // send a broadcast only for this APP.
            Intent intent = new Intent();
            intent.setAction("sample.hawk.com.mybasicappcomponents.broadcast1");
            sendBroadcast(intent);
        }
    };

    private OnClickListener mMyProviderBtnListener = new OnClickListener(){
        @Override
        public void onClick(View v) {
            SMLog.i();
            // Hawk: During launching APP, my APP's ContentProvider will be launched by OS.
        }
    };



}
