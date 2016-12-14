package sample.hawk.com.mybasicappcomponents.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

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

}
