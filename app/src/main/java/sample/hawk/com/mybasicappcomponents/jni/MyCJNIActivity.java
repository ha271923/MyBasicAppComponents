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
    private native String JNIcallbackJAVA(String inputString);
    private native int multiply(int a, int b);
    private native double getTimeOfDay();
    static TextView mViewStr5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.strings_ouput);
        ((TextView)findViewById(R.id.string1)).setText(
                JAVAtoJNI("paramFromJava"));
        ((TextView)findViewById(R.id.string2)).setText(""+
                multiply(3,4));
        ((TextView)findViewById(R.id.string3)).setText(""+
                getTimeOfDay());
        ((TextView)findViewById(R.id.string4)).setText(
                JAVAtoJNIcallbackJAVA("paramFromJava"));

        ((TextView)findViewById(R.id.string4)).setText(
                JNIcallbackJAVA("paramFromJava"));

        mViewStr5 = ((TextView)findViewById(R.id.string5));
    }

    // method for callback from JNI
    public String fromJNIcallbackJAVA(String text) {
        SMLog.e("MyCJNIActivity::fromJNIcallbackJAVA input="+text);
        text = "JNI string modified in JAVA";
        SMLog.e("MyCJNIActivity::fromJNIcallbackJAVA output="+text);
        mViewStr5.setText(text);
        return text;
    }

    static public String onHandleEvent(int type, int deviceType, int inputId) {
        String text = Integer.toString(type+deviceType+inputId);
        SMLog.e("MyCJNIActivity::onHandleEvent static input="+text);
        text = "JNI string modified in JAVA";
        SMLog.e("MyCJNIActivity::onHandleEvent static output="+text);
        mViewStr5.setText(text);
        return text;
    }
}
