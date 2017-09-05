package sample.hawk.com.mybasicappcomponents.graphic.utils;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import sample.hawk.com.mybasicappcomponents.utils.SMLog;

/**
 * Created by ha271 on 2017/6/6.
 */

public class ImageUtils {



    // test failed!
    public static int getStatusBarHeight2(Activity activity) {
        Rect rectangle = new Rect();
        Window window = activity.getWindow();
        window.getDecorView().getWindowVisibleDisplayFrame(rectangle);
        int statusBarHeight = rectangle.top;
        int contentViewTop =
                window.findViewById(Window.ID_ANDROID_CONTENT).getTop();
        int titleBarHeight= contentViewTop - statusBarHeight;
        return statusBarHeight;
    }

    public static int getStatusBarHeight(Activity activity) {
        int result = 0;
        int resourceId = activity.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = activity.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    public static boolean isHardwareAccelerated(Application app) {
        // Has HW acceleration been enabled in the manifest?
        ApplicationInfo info = app.getApplicationInfo();
        if ((info.flags & ApplicationInfo.FLAG_HARDWARE_ACCELERATED) != 0) {
            SMLog.i("1. App Accelerator = ENABLED");
            return true;
        }
        else{
            SMLog.i("1. App Accelerator = disable");
        }
        return false;
    }

    // Has HW acceleration been enabled in the manifest?
    public static boolean isHardwareAccelerated(Activity activity) {
        try {
            ActivityInfo info = activity.getPackageManager().getActivityInfo(
                    activity.getComponentName(), 0);
            if ((info.flags & ActivityInfo.FLAG_HARDWARE_ACCELERATED) != 0) {
                SMLog.i("2. Activity Accelerator = ENABLED");
                return true;
            }
            else{
                SMLog.i("2. Activity Accelerator = disable");
            }
        } catch (PackageManager.NameNotFoundException e) {
            SMLog.e("Hawk", "getActivityInfo(self) should not fail");
        }
        return false;
    }

    // Has HW acceleration been enabled in the manifest?
    public static boolean isHardwareAccelerated(Window window) {
        if (window != null) {
            if ((window.getAttributes().flags
                    & WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED) != 0) {
                SMLog.i("3. Window Accelerator = ENABLED");
                return true;
            }
            else{
                SMLog.i("3. Window Accelerator = disable");
            }
        }
        return false;
    }

    public static boolean isHardwareAccelerated(View view) {
        if(view.isHardwareAccelerated()==true){
            SMLog.i("4. View Accelerator = ENABLED");
            return true;
        }
        else{
            SMLog.i("4. View Accelerator = disable");
        }
        return false;
    }

    public static boolean isHardwareAccelerated(Canvas canvas) {
        if(canvas.isHardwareAccelerated()==true){
            SMLog.i("5. Canvas Accelerator = ENABLED");
            return true;
        }
        else{
            SMLog.i("5. Canvas Accelerator = disable");
        }
        return false;
    }

    public static void getImageFromAlbum(Activity activity, int reqCode){
        Intent intent = new Intent(
                Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        activity.startActivityForResult(intent, reqCode);
    }

    public static Bitmap getScreenshot(View v) {
        Bitmap b = Bitmap.createBitmap(v.getMeasuredWidth(),
                v.getMeasuredHeight(), Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(b);
        v.draw(c);
        return b;
    }

}
