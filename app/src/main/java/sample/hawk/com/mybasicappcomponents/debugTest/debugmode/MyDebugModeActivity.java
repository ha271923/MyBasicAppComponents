package sample.hawk.com.mybasicappcomponents.debugTest.debugmode;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.widget.LinearLayout;
import android.widget.TextView;

import sample.hawk.com.mybasicappcomponents.utils.SMLog;

/**
 * you can pass some parameters for changing APP behavior.
 */

public class MyDebugModeActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LinearLayout ll = new LinearLayout(this);
        TextView tv = new TextView(this);
        tv.setTextSize(20);
        tv.setText("ADB CMD> adb shell input text 'A'");
        ll.addView(tv);
        setContentView(ll);

    }

    // 透過 ADB cmd來切換debug模式
    // EX: adb shell input text 'A' // ASCII for key 'A'
    // EX: adb shell input text "A" // ASCII for key 'A'
    // EX: adb shell input keyevent 29 // ASCII for key 'A'
    // EX: adb shell input text "Hello" // 不適用, 欲用連續技時, 採用 Linux Script即可
    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_A:
                SMLog.i("onKeyUp, A");
                return true;
            case KeyEvent.KEYCODE_B:
                SMLog.i("onKeyUp, B");
                return true;
            case KeyEvent.KEYCODE_C:
                SMLog.i("onKeyUp, C");
                return true;
            case KeyEvent.KEYCODE_F:
                SMLog.i("onKeyUp, F");
                return true;
            case KeyEvent.KEYCODE_N:
                SMLog.i("onKeyUp, N");
                return true;
            case KeyEvent.KEYCODE_R:
                SMLog.i("onKeyUp, R");
                return true;
            case KeyEvent.KEYCODE_T:
                SMLog.i("onKeyUp, T");
                return true;
            default:
                return super.onKeyUp(keyCode, event);
        }
    }
}
