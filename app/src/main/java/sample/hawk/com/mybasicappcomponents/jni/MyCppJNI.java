package sample.hawk.com.mybasicappcomponents.jni;

/**
 * Created by ha271 on 2017/8/2.
 */

public class MyCppJNI {
    // 讀取My Cpp native函式庫
    static {
        System.loadLibrary("mycpplibrary");
    }


    public static native String helloString(String toWhat);
    public static native int multiply(int a, int b);
    public static native double getTimeOfDay();
}
