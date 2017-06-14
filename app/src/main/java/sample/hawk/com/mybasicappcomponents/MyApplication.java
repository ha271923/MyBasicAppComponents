package sample.hawk.com.mybasicappcomponents;

import android.app.Application;

import sample.hawk.com.mybasicappcomponents.debugTest.crashreport.ReportHandler;
import sample.hawk.com.mybasicappcomponents.utils.SMLog;
import sample.hawk.com.mybasicappcomponents.utils.Util;

/**
 * Created by ha271 on 2016/12/16.
 */

public class MyApplication extends Application {

    protected String userAgent;
    public static Application mApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        mApplication = this;
        userAgent = Util.getUserAgent(this, "MyBasicAppComponents");
        SMLog.i("userAgent: "+userAgent);
        CheckLogLevel();
        ReportHandler.install(this, "ha271923@yahoo.com.tw");
    }

    void CheckLogLevel(){
        SMLog.v("1. SMLog.v() == Verbose -- OK! ");
        SMLog.d("2. SMLog.d() == Debug   -- OK! ");
        SMLog.i("3. SMLog.i() == info    -- OK! ");
        SMLog.w("4. SMLog.w() == Warn    -- OK! ");
        SMLog.e("5. SMLog.e() == Error   -- OK! ");
    }
}
