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

    @Override
    public void onCreate() {
        super.onCreate();
        userAgent = Util.getUserAgent(this, "MyBasicAppComponents");
        SMLog.i("userAgent: "+userAgent);
        ReportHandler.install(this, "ha271923@yahoo.com.tw");
    }
}
