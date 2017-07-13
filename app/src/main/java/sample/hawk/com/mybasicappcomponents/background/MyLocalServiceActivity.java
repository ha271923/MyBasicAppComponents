package sample.hawk.com.mybasicappcomponents.background;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;


import sample.hawk.com.mybasicappcomponents.R;
import sample.hawk.com.mybasicappcomponents.utils.SMLog;

/**
 * Created by ha271 on 2016/12/14.
 */

public class MyLocalServiceActivity extends Activity {
    Context mContext;
    public static ProgressBar mMyLocalServiceProgressBar;
    public TextView mMyOutputTextView;
    public ToggleButton mMyLocalServiceToggleBtn;
    public ToggleButton mMyLocalForegroundServiceToggleBtn;
    public ToggleButton mMyBindServiceToggleBtn;
    public Button mMygetBindServiceResultBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mContext = this;
        setContentView(R.layout.mylocalservice);
        mMyLocalServiceProgressBar = (ProgressBar) findViewById(R.id.mylocalservice_progressBar);
        mMyOutputTextView        = (TextView) findViewById(R.id.OutputTextView);
        mMyLocalServiceToggleBtn  = (ToggleButton) findViewById(R.id.LocalServiceToggleBtn);
        mMyLocalForegroundServiceToggleBtn  = (ToggleButton) findViewById(R.id.LocalForegroundServiceToggleBtn);
        mMyBindServiceToggleBtn  = (ToggleButton) findViewById(R.id.BindServiceToggleBtn);
        mMygetBindServiceResultBtn = (Button) findViewById(R.id.getBindServiceResultBtn);
        mMyLocalServiceToggleBtn.setOnClickListener(mMyLocalServiceToggleBtnListener);
        mMyBindServiceToggleBtn.setOnClickListener(mMyBindServiceToggleBtnListener);
        mMygetBindServiceResultBtn.setOnClickListener(mMygetBindServiceResultBtnListener);
        mMyLocalForegroundServiceToggleBtn.setOnClickListener(mMyLocalForegroundServiceToggleBtnListener);

    }


    // LocalForegroundService start/stop
    private View.OnClickListener mMyLocalForegroundServiceToggleBtnListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            SMLog.i();
            if(mMyLocalForegroundServiceToggleBtn.isChecked()){
                Intent intent = new Intent(MyLocalService.ACTION_FOREGROUND);
                intent.setClass(mContext, MyLocalService.class);
                startService(intent);
                Toast.makeText(mContext, "START Foreground service", Toast.LENGTH_SHORT).show();
                mMyOutputTextView.setText("START Foreground Service");
            }
            else{
                stopService(new Intent(mContext, MyLocalService.class));
                Toast.makeText(mContext, "STOP Foreground service", Toast.LENGTH_SHORT).show();
                mMyOutputTextView.setText("STOP Foreground Service");
            }
        }
    };

    // LocalService start/stop
    private View.OnClickListener mMyLocalServiceToggleBtnListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            SMLog.i();
            Intent intent = new Intent(MyLocalService.ACTION_BACKGROUND);
            intent.setClass( mContext, MyLocalService.class);
            if(mMyLocalServiceToggleBtn.isChecked()){
                startService(intent);
                Toast.makeText(mContext, "START service", Toast.LENGTH_SHORT).show();
                mMyOutputTextView.setText("START Service");
            }
            else{
                stopService(intent);
                Toast.makeText(mContext, "STOP service", Toast.LENGTH_SHORT).show();
                mMyOutputTextView.setText("STOP Service");
            }
        }
    };

    // LocalService bind/unbind
    MyLocalService mMyLocalService;
    boolean mBound = false;
    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName className, IBinder service) {
            SMLog.i();
            MyLocalService.LocalBinder binder = (MyLocalService.LocalBinder) service;
            mMyLocalService = binder.getService();
            mBound = true;
        }
        @Override
        public void onServiceDisconnected(ComponentName arg0) {
            SMLog.i();
            mBound = false;
        }
    };
    private View.OnClickListener mMyBindServiceToggleBtnListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            boolean ret = false;
            SMLog.i();
            if(mMyBindServiceToggleBtn.isChecked()){
                Intent intent = new Intent(mContext, MyLocalService.class);
                ret = bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
                Toast.makeText(mContext, "BIND service", Toast.LENGTH_SHORT).show();
                mMyOutputTextView.setText("bindService = "+ret);
            }
            else{
                unbindService(mConnection);
                mBound = false;
                Toast.makeText(mContext, "UNBIND service", Toast.LENGTH_SHORT).show();
                mMyOutputTextView.setText("unbindService");
            }
        }
    };
    private View.OnClickListener mMygetBindServiceResultBtnListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (mBound) {
                // Call API at background
                int num = mMyLocalService.getRandomNumber();
                Toast.makeText(mContext, "number: " + num, Toast.LENGTH_SHORT).show();
                mMyOutputTextView.setText("number: "+ num);
            }
        }
    };

}
