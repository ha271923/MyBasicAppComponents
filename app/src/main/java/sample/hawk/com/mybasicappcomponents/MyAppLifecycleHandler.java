package sample.hawk.com.mybasicappcomponents;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import sample.hawk.com.mybasicappcomponents.utils.SMLog;

public class MyAppLifecycleHandler implements Application.ActivityLifecycleCallbacks {
    // If you want a static function you can use to check if your application is
    // foreground/background, you can use the following:
    // Replace the four variables above with these four
    private static int resumed;
    private static int paused;
    private static int started;
    private static int stopped;

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
    }

    @Override
    public void onActivityDestroyed(Activity activity) {
    }

    @Override
    public void onActivityResumed(Activity activity) {
        ++resumed;
    }

    @Override
    public void onActivityPaused(Activity activity) {
        ++paused;
        SMLog.i("Application is in foreground: " + (resumed > paused));
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
    }

    @Override
    public void onActivityStarted(Activity activity) {
        ++started;
    }

    @Override
    public void onActivityStopped(Activity activity) {
        ++stopped;
        SMLog.i("application is visible: " + (started > stopped));
    }

    // And these two public static functions
    public static boolean isApplicationVisible() {
        return started > stopped;
    }

    public static boolean isApplicationInForeground() {
        return resumed > paused;
    }
}