package sample.hawk.com.mybasicappcomponents.jni;

import android.app.Activity;

import sample.hawk.com.mybasicappcomponents.utils.SMLog;

/**
 * Created by ha271 on 2017/8/2.
 */

public class MyCppJNI {
    // 讀取My Cpp native函式庫
    static {
        System.loadLibrary("mycpplibrary");
    }


    public static native String JAVAtoJNI(String inputString);
    public native String JAVAtoJNIcallbackJAVA(String inputString);
    public static native int multiply(int a, int b);
    public static native double getTimeOfDay();

    private static Activity sActivity;
    public static void setActivity(Activity activity) {
        sActivity = activity;
    }
    // callback from JNI
    // method for callback from JNI
    // non-static method will cause Exception "JNI DETECTED ERROR IN APPLICATION: can't call java.lang.String sample.hawk.com.mybasicappcomponents.jni.MyCppJNI.fromJNIcallbackJAVA(java.lang.String) on instance of java.lang.Class<sample.hawk.com.mybasicappcomponents.jni.MyCppJNI>"
    // static method will cause Exception " JNI DETECTED ERROR IN APPLICATION: JNI CallObjectMethodV called with pending exception java.lang.NoSuchMethodError: no non-static method "Lsample/hawk/com/mybasicappcomponents/jni/MyCppJNI;.fromJNIcallbackJAVA"
    static public String callbackHandleEvent(String text) {
        SMLog.e("MyCppJNIActivity::fromJNIcallbackJAVA input="+text);
        text = "JNI string modified in JAVA";
        SMLog.e("MyCppJNIActivity::fromJNIcallbackJAVA output="+text);
        return text;
    }
}
