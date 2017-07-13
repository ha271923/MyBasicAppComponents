package sample.hawk.com.mybasicappcomponents.background;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Messenger;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import sample.hawk.com.mybasicappcomponents.R;
import sample.hawk.com.mybasicappcomponents.utils.SMLog;

/**
 * Created by ha271 on 2016/12/14.
 */

public class MyIntentServiceActivity extends Activity {
    Context mContext;
    public static ProgressBar myintentservice_progressBar;
    public ToggleButton mMyIntentServiceToggleBtn;
    public TextView mMyOutputTextView;
    Handler mMainUiHandler;

    interface passDataToIntentService {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setContentView(R.layout.myintentservice_activity);
        mMyOutputTextView        = (TextView) findViewById(R.id.OutputTextView);
        mMyIntentServiceToggleBtn    = (ToggleButton) findViewById(R.id.IntentServiceToggleBtn);
        mMyIntentServiceToggleBtn.setOnClickListener(mMyIntentServiceToggleBtnListener);
        myintentservice_progressBar = (ProgressBar) findViewById(R.id.myintentservice_progressBar);
        mMainUiHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                Bundle reply = msg.getData();
                myintentservice_progressBar.setProgress(reply.getInt("progress"));
            }
        };
    }


    private View.OnClickListener mMyIntentServiceToggleBtnListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            Intent intent = new Intent(mContext, MyIntentService.class);
            intent.putExtra("messenger", new Messenger(mMainUiHandler));
            SMLog.i();
            if(mMyIntentServiceToggleBtn.isChecked()){
                intent.setAction("START_IntentService");
                startService(intent);
                Toast.makeText(mContext, "new IntentService", Toast.LENGTH_SHORT).show();
                mMyOutputTextView.setText("START IntentService");
            }
            else{
                intent.setAction("STOP_IntentService");
                startService(intent); // Hawk: call startService to pass intent with parameter to the running IntentService again.
                Toast.makeText(mContext, "Can't STOP IntentService", Toast.LENGTH_SHORT).show();
                mMyOutputTextView.setText("Can't STOP IntentService");
            }
        }
    };

}
