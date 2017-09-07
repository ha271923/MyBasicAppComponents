package sample.hawk.com.mybasicappcomponents.utils.logger2;

import android.util.Log;


public class LoggerBase {
    private String mLoggableName;

    public static boolean LOCAL_DEBUG_FLAG = true; // changed
    public static boolean SECURITY_DEBUG_FLAG = false; // changed

    //adb shell setprop log.tag.${$mLoggableName} [VERBOSE | DEBUG | INFO | WARN | ERROR | ASSERT].
    //By default, it is INFO which means log level < INFO is not loggable.
    public boolean mDebug_flag_v = false;
    public boolean mDebug_flag_d = false;
    public boolean mDebug_flag_i = false;
    public boolean mDebug_flag_w = false;
    public boolean mDebug_flag_e = false;
    //public static boolean DEBUG_FLAG_A = LOCAL_DEBUG_FLAG || Log.isLoggable(LOGGABLE_TAG_HOME_UTIL, Log.ASSERT);
    public boolean LOG_TRACE = Log.isLoggable(mLoggableName, Log.VERBOSE);

    LoggerBase(String tag) {
        mLoggableName = tag;
        mDebug_flag_v = LOCAL_DEBUG_FLAG || Log.isLoggable(mLoggableName, Log.VERBOSE);
        mDebug_flag_d = LOCAL_DEBUG_FLAG || Log.isLoggable(mLoggableName, Log.DEBUG);
        mDebug_flag_i = LOCAL_DEBUG_FLAG || Log.isLoggable(mLoggableName, Log.INFO);
        mDebug_flag_w = LOCAL_DEBUG_FLAG || Log.isLoggable(mLoggableName, Log.WARN);
        mDebug_flag_e = LOCAL_DEBUG_FLAG || Log.isLoggable(mLoggableName, Log.ERROR);
    }

    private boolean loggable(int level) {
        switch(level) {
            case Log.VERBOSE:
                return mDebug_flag_v;
            case Log.DEBUG:
                return mDebug_flag_d;
            case Log.INFO:
                return mDebug_flag_i;
            case Log.WARN:
                return mDebug_flag_w;
            case Log.ERROR:
                return mDebug_flag_e;
        }
        return false;
    }

    private void printS(int level, String tag, Throwable tr, String format, Object... args) {
        if (SECURITY_DEBUG_FLAG) {
            String stackString = tr == null ? "" : '\n' + android.util.Log.getStackTraceString(tr);
            if (args != null && args.length <=0 ) {
                android.util.Log.println(level, tag, format + stackString);
            } else {
                android.util.Log.println(level, tag, String.format(format, args) + stackString);
            }
        }
    }

    private void print(int level, String tag, Throwable tr, String format, Object... args) {
        if (loggable(level)) {
            String stackString = tr == null ? "" : '\n' + android.util.Log.getStackTraceString(tr);
            if (args != null && args.length <=0 ) {
                android.util.Log.println(level, tag, format + stackString);
            } else {
                android.util.Log.println(level, tag, String.format(format, args) + stackString);
            }
        }
    }

    /////////// secure logger +++
    public void vs(String tag, String format, Object... args) {
        printS(Log.VERBOSE, tag, null, format, args);
    }

    public void vs(String tag, Throwable tr, String format, Object... args) {
        printS(Log.VERBOSE, tag, tr, format, args);
    }

    public void ds(String tag, String format, Object... args) {
        printS(Log.DEBUG, tag, null, format, args);
    }

    public void ds(String tag, Throwable tr, String format, Object... args) {
        printS(Log.DEBUG, tag, tr, format, args);
    }

    public void is(String tag, String format, Object... args) {
        printS(Log.INFO, tag, null, format, args);
    }

    public void is(String tag, Throwable tr, String format, Object... args) {
        printS(Log.INFO, tag, tr, format, args);
    }

    public void ws(String tag, String format, Object... args) {
        printS(Log.WARN, tag, null, format, args);
    }

    public void ws(String tag, Throwable tr, String format, Object... args) {
        printS(Log.WARN, tag, tr, format, args);
    }

    public void es(String tag, String format, Object... args) {
        printS(Log.ERROR, tag, null, format, args);
    }

    public void es(String tag, Throwable tr, String format, Object... args) {
        printS(Log.ERROR, tag, tr, format, args);
    }
    /////////// secure logger ---

    public void v(String tag, String format, Object... args) {
        print(Log.VERBOSE, tag, null, format, args);
    }

    public void v(String tag, Throwable tr, String format, Object... args) {
        print(Log.VERBOSE, tag, tr, format, args);
    }

    public void d(String tag, String message) {
        print(Log.DEBUG, tag, null, message);
    }

    public void d(String tag, String format, Object... args) {
        print(Log.DEBUG, tag, null, format, args);
    }

    public void d(String tag, Throwable tr, String format, Object... args) {
        print(Log.DEBUG, tag, tr, format, args);
    }

    public void i(String tag, String format, Object... args) {
        print(Log.INFO, tag, null, format, args);
    }

    public void i(String tag, Throwable tr, String format, Object... args) {
        print(Log.INFO, tag, tr, format, args);
    }

    public void w(String tag, String format, Object... args) {
        print(Log.WARN, tag, null, format, args);
    }

    public void w(String tag, Throwable tr, String format, Object... args) {
        print(Log.WARN, tag, tr, format, args);
    }

    public void e(String tag, String format, Object... args) {
        print(Log.ERROR, tag, null, format, args);
    }

    public void e(String tag, Throwable tr, String format, Object... args) {
        print(Log.ERROR, tag, tr, format, args);
    }

    public String showStack(String tag, int level) {
        final StackTraceElement[] ste = new Throwable().getStackTrace();
        String result = "";
        if(ste == null) {
            return result;
        }
        int deep = ste.length;
        if(deep < 2) {
            return result;
        }
        if(deep > level) {
            deep = level;
        }
        for(int i = 2; i < deep; i++) {
            print(Log.DEBUG, tag, null, "[%s] %s, %s(), Line:%d", tag, ste[i].getFileName(), ste[i].getMethodName(), ste[i].getLineNumber());
        }
        return result;
    }

    public void traceBegin(String name) {
        if (LOG_TRACE) {
            android.os.Trace.beginSection(name);
        }
    }

    public void traceEnd() {
        if (LOG_TRACE) {
            android.os.Trace.endSection();
        }
    }
}
