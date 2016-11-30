package sample.hawk.com.mybasicappcomponents.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

/**
 * Created by ha271 on 2016/11/23.
 */

public class Util {
    private static final String TAG = "[Hawk]";

    public static String getAppVersion(Context context, String AppPackage) {
        PackageInfo pi=null;
        PackageManager pm = context.getPackageManager();
        try {
            pi = pm.getPackageInfo(AppPackage, 0);
            SMLog.d(TAG, "version name: " + pi.versionName);
            SMLog.d(TAG, "version code: " + pi.versionCode);
        } catch (PackageManager.NameNotFoundException e) {
            SMLog.e(TAG, "Android System WebView is not found");
        }
        return pi.versionName;
    }
}
