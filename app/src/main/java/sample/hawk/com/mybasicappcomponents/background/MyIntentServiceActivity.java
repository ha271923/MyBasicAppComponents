package sample.hawk.com.mybasicappcomponents.background;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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

public class MyIntentServiceActivity extends Activity {
    Context mContext;

    public ToggleButton mMyIntentServiceToggleBtn;
    public TextView mMyOutputTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setContentView(R.layout.myasynctask);
        mMyOutputTextView        = (TextView) findViewById(R.id.OutputTextView);
        mMyIntentServiceToggleBtn    = (ToggleButton) findViewById(R.id.IntentServiceToggleBtn);
        mMyIntentServiceToggleBtn.setOnClickListener(mMyIntentServiceToggleBtnListener);
    }


    private View.OnClickListener mMyIntentServiceToggleBtnListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            Intent intent = new Intent(mContext, MyIntentService.class);
            SMLog.i();
            if(mMyIntentServiceToggleBtn.isChecked()){
                startService(intent);
                Toast.makeText(mContext, "new IntentService", Toast.LENGTH_SHORT).show();
                mMyOutputTextView.setText("START IntentService");
            }
            else{
                // stopService(intent); // Hawk: IntentService can't be stop, href="http://stackoverflow.com/questions/8709989/how-to-stop-intentservice-in-android"
                Toast.makeText(mContext, "Can't STOP IntentService", Toast.LENGTH_SHORT).show();
                mMyOutputTextView.setText("Can't STOP IntentService");
            }
        }
    };

}
