package sample.hawk.com.mybasicappcomponents.utils;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.BatteryManager;
import android.os.Build;
import android.os.Looper;
import android.view.ContextThemeWrapper;
import android.view.View;

import com.google.android.exoplayer2.ExoPlayerLibraryInfo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

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

    public static boolean isPhonePluggedIn(Context context){
        boolean charging = false;

        final Intent batteryIntent = context.registerReceiver(null, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
        int status = batteryIntent.getIntExtra(BatteryManager.EXTRA_STATUS, -1);
        boolean batteryCharge = status==BatteryManager.BATTERY_STATUS_CHARGING;

        int chargePlug = batteryIntent.getIntExtra(BatteryManager.EXTRA_PLUGGED, -1);
        boolean usbCharge = chargePlug == BatteryManager.BATTERY_PLUGGED_USB;
        boolean acCharge = chargePlug == BatteryManager.BATTERY_PLUGGED_AC;

        if (batteryCharge)
            charging=true;
        if (usbCharge)
            charging=true;
        if (acCharge)
            charging=true;

        return charging;
    }

    public static void ListActivityConfigurations(Configuration config){
        SMLog.i("fontScale="+config.fontScale);
        SMLog.i("mcc="+config.mcc);
        SMLog.i("mnc="+config.mnc);
        SMLog.i("locale="+config.locale);
        SMLog.i("screenLayout="+config.screenLayout);
        SMLog.i("keyboard="+config.keyboard);
        SMLog.i("keyboardHidden="+config.keyboardHidden);
        SMLog.i("hardKeyboardHidden="+config.hardKeyboardHidden);
        SMLog.i("navigation="+config.navigation);
        SMLog.i("navigationHidden="+config.navigationHidden);
        SMLog.i("orientation="+config.orientation);
        SMLog.i("uiMode="+config.uiMode);
        SMLog.i("screenWidthDp="+config.screenWidthDp);
        SMLog.i("screenHeightDp="+config.screenHeightDp);
        SMLog.i("smallestScreenWidthDp="+config.smallestScreenWidthDp);
        SMLog.i("densityDpi="+config.densityDpi);
    }

    // access the API with @hide annotation.
    public static Object CallHideAPI(Context context, String ApiName){
        Object result = null;
        try {
            Class<?> clazz = ContextThemeWrapper.class;
            Method method = clazz.getMethod(ApiName);
            method.setAccessible(true);
            result = method.invoke(context);
        } catch (NoSuchMethodException e) {
            SMLog.e("NoSuchMethodException! Err="+ e);
        } catch (IllegalAccessException e) {
            SMLog.e("IllegalAccessException! Err="+ e);
        } catch (IllegalArgumentException e) {
            SMLog.e("IllegalArgumentException! Err="+ e);
        } catch (InvocationTargetException e) {
            SMLog.e("InvocationTargetException! Err="+ e);
        }
        return result;
    }

    public static void hideSystemUI(boolean bHide, Activity activity) {
        if(bHide == true){
        activity.getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide nav bar
                        | View.SYSTEM_UI_FLAG_FULLSCREEN // hide status bar
                        | View.SYSTEM_UI_FLAG_IMMERSIVE);
        }
        else{
            activity.getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN); // extend activity to status bar
        }
    }

    // extracted from Facebook SDK source code
    private static final String HASH_ALGORITHM_MD5 = "MD5";
    private static final String HASH_ALGORITHM_SHA1 = "SHA-1";

    public static String md5hash(String key) {
        return hashWithAlgorithm(HASH_ALGORITHM_MD5, key);
    }
    private static String hashWithAlgorithm(String algorithm, String key) {
        return hashWithAlgorithm(algorithm, key.getBytes());
    }
    private static String hashWithAlgorithm(String algorithm, byte[] bytes) {
        MessageDigest hash;
        try {
            hash = MessageDigest.getInstance(algorithm);
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
        return hashBytes(hash, bytes);
    }
    private static String hashBytes(MessageDigest hash, byte[] bytes) {
        hash.update(bytes);
        byte[] digest = hash.digest();
        StringBuilder builder = new StringBuilder();
        for (int b : digest) {
            builder.append(Integer.toHexString((b >> 4) & 0xf));
            builder.append(Integer.toHexString((b >> 0) & 0xf));
        }
        return builder.toString();
    }

    public static <T> boolean isNullOrEmpty(Collection<T> c) {
        return (c == null) || (c.size() == 0);
    }

    public static boolean isNullOrEmpty(String s) {
        return (s == null) || (s.length() == 0);
    }


    public static ArrayList GetAllInstalledAppsList(Activity activity) {
        ArrayList arrayList = new ArrayList();
        Intent intent = new Intent("android.intent.action.MAIN", null);
        intent.addCategory("android.intent.category.LAUNCHER");
        PackageManager packageManager = activity.getPackageManager();
        List queryIntentActivities = packageManager.queryIntentActivities(intent, 0);
        for (int i = 0; i < queryIntentActivities.size(); i++) {
            AppItem appItem = new AppItem();
            appItem.appLabel = ((ResolveInfo) queryIntentActivities.get(i)).loadLabel(packageManager).toString();
            appItem.packageName = ((ResolveInfo) queryIntentActivities.get(i)).activityInfo.packageName;
            appItem.iconImage = ((ResolveInfo) queryIntentActivities.get(i)).loadIcon(packageManager);
            arrayList.add(appItem);
        }
        return arrayList;
    }

    // Usage: String[] mlistItems = Util.enumToString(ViewType.class);
    public static String[] enumToString(Class<? extends Enum<?>> e) {
        return Arrays.toString(e.getEnumConstants()).replaceAll("^.|.$", "").split(", ");
    }


    public static String getVersionName(Activity activity) {
        String versionName = "";
        PackageInfo packageInfo;
        try {
            packageInfo = activity.getPackageManager().getPackageInfo(activity.getPackageName(), 0);
            versionName = packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionName;
    }
}
