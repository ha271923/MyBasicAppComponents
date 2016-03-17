package sample.hawk.com.mybasicappcomponents.utils;

/**
 * Created by ha271 on 2016/3/14.
 */
public class SMLog {

    public static final boolean DEBUG_LOG_ENABLED = false;

    private static final String TAG = "[Hawk]";

    public static void i() {
        android.util.Log.i(TAG, getClassFunctionName());
    }

    public static void i(String tag, String msg) {
        if (null == msg)
        {
            msg = "null log string";
        }
        android.util.Log.i(TAG + tag, getClassFunctionName()+msg);
    }

    public static void i(String tag, String msg, Throwable e) {
        if (null == msg)
        {
            msg = "null log string";
        }
        android.util.Log.i(TAG + tag, getClassFunctionName()+msg, e);
    }

    public static void d() {
        android.util.Log.d(TAG, getClassFunctionName());
    }

    public static void d(String tag, String msg) {
        if (DEBUG_LOG_ENABLED) {
            if (null == msg)
            {
                msg = "null log string";
            }
            android.util.Log.d(TAG + tag, getClassFunctionName()+msg);
        }
    }

    public static void d(String tag, String msg, Throwable e) {
        if (DEBUG_LOG_ENABLED) {
            if (null == msg)
            {
                msg = "null log string";
            }
            android.util.Log.d(TAG + tag, getClassFunctionName()+msg, e);
        }
    }

    public static void w() {
        android.util.Log.i(TAG, getClassFunctionName());
    }

    public static void w(String tag, String msg) {
        if (null == msg)
        {
            msg = "null log string";
        }
        android.util.Log.w(TAG + tag, getClassFunctionName()+msg);
    }

    public static void w(String tag, String msg, Throwable e) {
        if (null == msg)
        {
            msg = "null log string";
        }
        android.util.Log.w(TAG + tag, getClassFunctionName()+msg, e);
    }

    public static void e() {
        android.util.Log.i(TAG, getClassFunctionName());
    }

    public static void e(String tag, String msg) {
        if (null == msg)
        {
            msg = "null log string";
        }
        android.util.Log.e(TAG + tag, getClassFunctionName()+msg);
    }

    public static void e(String tag, String msg, Throwable e) {
        if (null == msg)
        {
            msg = "null log string";
        }
        android.util.Log.e(TAG + tag, getClassFunctionName()+msg, e);
    }

    public static String getClassFunctionName(  )
    {
        String Class_Function_Name = "class ";
        Class_Function_Name += Thread.currentThread().getStackTrace()[4].getClassName().toString();
        Class_Function_Name += " :: ";
        Class_Function_Name += Thread.currentThread().getStackTrace()[4].getMethodName().toString();
        Class_Function_Name += "() ";
        return Class_Function_Name;

    }
}
