package sample.hawk.com.mybasicappcomponents.activity;

import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import sample.hawk.com.mybasicappcomponents.R;
import sample.hawk.com.mybasicappcomponents.utils.SMLog;

public class RotateActivity extends AppCompatActivity {
    private TextView mTextViewCount;
    private int mCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SMLog.i();
        setContentView(R.layout.rotate_activity);

        mTextViewCount = (TextView)findViewById(R.id.text_view_count);

        Button buttonDecrement = (Button)findViewById(R.id.button_decrement);
        Button buttonIncrement = (Button)findViewById(R.id.button_increment);

        buttonDecrement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                decrement();
            }
        });

        buttonIncrement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                increment();
            }
        });

    }

    private void decrement() {
        mCount--;
        mTextViewCount.setText(String.valueOf(mCount));
    }

    private void increment() {
        mCount++;
        mTextViewCount.setText(String.valueOf(mCount));
    }


    private void checkOrientation(Configuration newConfig){
        int orientation = newConfig.orientation;
        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            SMLog.d("checkOrientation", "onConfigurationChanged  Portrait");

        }
        else if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            SMLog.d("checkOrientation", "onConfigurationChanged  Landscape");

        }
        else {
            SMLog.w("checkOrientation", "onConfigurationChanged  other: " + orientation);

        }

    }

    // Add android:configChanges="orientation" will not call this CB.
    // Add android:configChanges="orientation|screenSize" will call this CB, all images will fit to window.
    @Override
    public void onConfigurationChanged(Configuration newConfig)
    {
        SMLog.i();
        super.onConfigurationChanged(newConfig);
        checkOrientation(newConfig);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        SMLog.i();
        if (savedInstanceState != null) {
            mCount = savedInstanceState.getInt("count");
            mTextViewCount.setText(String.valueOf(mCount));
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        SMLog.i();
        if (outState != null) {
            outState.putInt("count", mCount);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        SMLog.i();
    }

    @Override
    protected void onResume() {
        super.onResume();
        SMLog.i();
    }

    @Override
    protected void onPause() { // If user back to UI, state will goto onResume.
        super.onPause();
        SMLog.i();
    }

    @Override
    protected void onStop() {  // If user back to UI, state will goto onRestart. If process has been killed, the state will goto onCreate.
        super.onStop();
        SMLog.i();
    }

    @Override
    protected void onRestart() { // the next state is onStart.
        super.onRestart();
        SMLog.i();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SMLog.i();
    }
}