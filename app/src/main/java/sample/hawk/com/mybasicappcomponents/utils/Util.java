package sample.hawk.com.mybasicappcomponents.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Looper;

import com.google.android.exoplayer2.ExoPlayerLibraryInfo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

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

    static public void appendLog(String text)
    {
        File logFile = new File("sdcard/testlog.txt"); // Hawk: you can find log file at /mnt/sdcard/testlog.txt
        if (!logFile.exists())
        {
            try
            {
                logFile.createNewFile();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        try
        {
            //BufferedWriter for performance, true to set append to file flag
            BufferedWriter buf = new BufferedWriter(new FileWriter(logFile, true));
            buf.append(text);
            buf.newLine();
            buf.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Returns a user agent string based on the given application name and the library version.
     *
     * @param context A valid context of the calling application.
     * @param applicationName String that will be prefix'ed to the generated user agent.
     * @return A user agent string generated using the applicationName and the library version.
     */
    public static String getUserAgent(Context context, String applicationName) {
        String versionName;
        try {
            String packageName = context.getPackageName();
            PackageInfo info = context.getPackageManager().getPackageInfo(packageName, 0);
            versionName = info.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            versionName = "?";
        }
        return applicationName + "/" + versionName + " (Linux;Android " + Build.VERSION.RELEASE
                + ") " + "ExoPlayerLib/" + ExoPlayerLibraryInfo.VERSION;
    }

    public static boolean isUiThread() {
        return Looper.myLooper() == Looper.getMainLooper();
    }
}
