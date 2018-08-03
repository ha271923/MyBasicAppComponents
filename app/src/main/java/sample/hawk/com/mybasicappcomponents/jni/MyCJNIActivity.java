package sample.hawk.com.mybasicappcomponents.jni;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import sample.hawk.com.mybasicappcomponents.R;
import sample.hawk.com.mybasicappcomponents.utils.SMLog;

/**
 * Created by ha271 on 2017/7/28.
 */

public class MyCJNIActivity extends Activity {
    // 讀取My C native函式庫
    static {
        System.loadLibrary("myc");
    }
    // 宣告由C/C++實作的方法
    private native String JAVAtoJNI(String inputString);
    private native String JAVAtoJNIcallbackJAVA(String inputString);
    private native int multiply(int a, int b);
    private native double getTimeOfDay();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.strings_ouput);
        ((TextView)findViewById(R.id.string1)).setText(JAVAtoJNI("paramFromJava"));
        ((TextView)findViewById(R.id.string2)).setText(""+multiply(3,4));
        ((TextView)findViewById(R.id.string3)).setText(""+getTimeOfDay());
        ((TextView)findViewById(R.id.string4)).setText(JAVAtoJNIcallbackJAVA("paramFromJava"));
    }

    // method for callback from JNI
    public String fromJNIcallbackJAVA(String text) {
        SMLog.e("MyCJNIActivity::fromJNIcallbackJAVA input="+text);
        text = "JNI string modified in JAVA";
        SMLog.e("MyCJNIActivity::fromJNIcallbackJAVA output="+text);
        ((TextView)findViewById(R.id.string5)).setText(text);
        return text;
    }
}
