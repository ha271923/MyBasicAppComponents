package sample.hawk.com.mybasicappcomponents.background;

import android.app.Activity;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.TextView;

import sample.hawk.com.mybasicappcomponents.R;

import static sample.hawk.com.mybasicappcomponents.utils.Util.isUiThread;

/**
 * Test Count Down Timer
 */

public class CountDownTimerActivity extends Activity {
    private final static int TIMEOUT  = 1*60*1000;
    private final static int INTERVAL =    1*1000;
    private TextView mCountdown_text;
    private String mCountdown_str = "";
    private int      mCount = 0;
    CountDownTimer mCountDownTimer;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.coundowntimer_activity);
        mCountdown_text = (TextView)findViewById(R.id.countdown_text);
        mCountdown_text.setText(mCountdown_str);

        mCountDownTimer = new CountDownTimer(TIMEOUT, INTERVAL) {
            @Override
            public void onTick(long millisUntilFinished) {
                Log.i("CountDownTimer", "COUNT="+mCount +"      isUiThread = "+isUiThread());
                mCountdown_str = mCountdown_str + "\nCOUNT="+mCount;
                mCount++;
                mCountdown_text.setText(mCountdown_str);
            }

            @Override
            public void onFinish() {
                Log.i("CountDownTimer", "COUNT="+mCount +"      isUiThread = "+isUiThread() + "Finish!!");
                mCountdown_str = mCountdown_str + "\nFinish!";
                mCountdown_text.setText(mCountdown_str);
            }
        }.start();  // Start it!!!!

    }

}
