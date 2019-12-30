package sample.hawk.com.mybasicappcomponents;

import android.app.Application;
import android.util.Log;

// import com.google.android.gms.analytics.GoogleAnalytics;
// import com.google.android.gms.analytics.Tracker;

import sample.hawk.com.mybasicappcomponents.debugTest.crashreport.ReportHandler;
import sample.hawk.com.mybasicappcomponents.utils.SMLog;
import sample.hawk.com.mybasicappcomponents.utils.Util;

/**
 * Created by ha271 on 2016/12/16.
 */

public class MyApplication extends Application {

    protected String userAgent;
    public static Application mApplication;
    // private static GoogleAnalytics sAnalytics;
    // private static Tracker sTracker;

    @Override
    public void onCreate() {
        super.onCreate();
        registerActivityLifecycleCallbacks(new MyAppLifecycleHandler());
        mApplication = this;
        userAgent = Util.getUserAgent(this, "MyBasicAppComponents");
        SMLog.i("userAgent: "+userAgent);
        CheckLogLevel();
        ReportHandler.install(this, "ha271923@yahoo.com.tw");
        // sAnalytics = GoogleAnalytics.getInstance(this);
    }

    void CheckLogLevel(){
        SMLog.v("1. SMLog.v() == Verbose -- OK! ");
        SMLog.d("2. SMLog.d() == Debug   -- OK! ");
        SMLog.i("3. SMLog.i() == info    -- OK! ");
        SMLog.w("4. SMLog.w() == Warn    -- OK! ");
        SMLog.e("5. SMLog.e() == Error   -- OK! ");
        Log.wtf("[Hawk]","?. Log.wtf() == WTF   -- OK! ");
        // Log.a("?. Log.a() == Silent  -- OK! "); // APP no this.
        // Log.f("?. Log.f() == Fatal   -- OK! "); // APP no this.
        // Log.s("?. Log.s() == Silent  -- OK! "); // APP no this.

    }
/*
    //
    // Gets the default {@link Tracker} for this {@link Application}.
    // @return tracker
    //
    synchronized public Tracker getDefaultTracker() {
        // To enable debug logging use: adb shell setprop log.tag.GAv4 DEBUG
        if (sTracker == null) {
            sTracker = sAnalytics.newTracker(R.xml.global_tracker);
        }

        return sTracker;
    }
 */
}
