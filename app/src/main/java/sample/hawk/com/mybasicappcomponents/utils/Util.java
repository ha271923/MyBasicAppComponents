package sample.hawk.com.mybasicappcomponents.utils;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Build;
import android.os.Looper;
import android.view.View;

import com.google.android.exoplayer2.ExoPlayerLibraryInfo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;

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




 /**
  *  Find the saved thumbnail at:
  *    data/data/sample.hawk.com.mybasicappcomponents/files/thumbs/private.png
  *    data/user/0/sample.hawk.com.mybasicappcomponents/files/thumbs/private.png
  *
  * */
    public void Test_saveThumbnail(Context context){
        // Save a thumbnail to file
        final File thumbsDir = new File(context.getFilesDir(), "thumbs");
        thumbsDir.mkdirs();
        final File file = new File(thumbsDir, "private.png");
    }

    /**
     * Save thumbnail of given {@link View} to {@link File}.
     */
    static public void saveThumbnailFile(View view, File file) {
        final Bitmap bitmap = Bitmap.createBitmap(
                view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
        final Canvas canvas = new Canvas(bitmap);
        view.draw(canvas);

        try {
            final OutputStream os = new FileOutputStream(file);
            try {
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, os);
            } finally {
                os.close();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean isUiThread() {
        return Looper.myLooper() == Looper.getMainLooper();
    }

    /* check if service is running */
    public static boolean isServiceRunning(Context context, Class<?> serviceClass) {
        ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }
}
