package sample.hawk.com.mybasicappcomponents.graphic.utils;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Environment;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Random;

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

    public static int getStatusBarHeight(Context context) {
        int result = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
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

    // PATH: adb pull /storage/emulated/0/test_images/
    public static void saveScreenshot(final View v) {
        new Thread(){
            public void run() {
                try {
                    String root = Environment.getExternalStorageDirectory().toString();
                    android.util.Log.i("hawk", "SaveImage  file path = " + root);
                    File myDir = new File(root + "/test_images");
                    myDir.mkdirs();
                    Random generator = new Random();
                    int n = 10000;
                    n = generator.nextInt(n);
                    String fname = "Image-" + n + ".jpg";
                    File file = new File(myDir, fname);
                    if (file.exists()) file.delete();
                    try {
                        FileOutputStream out = new FileOutputStream(file);
                        Bitmap finalBitmap = getScreenshot(v);
                        finalBitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);
                        out.flush();
                        out.close();

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        }.start();
    }

    public static Point getDisplaySize(Context context, boolean bIsReal) {
        Point ptSize = new Point();
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        if (bIsReal) {
            windowManager.getDefaultDisplay().getRealSize(ptSize);
        } else {
            windowManager.getDefaultDisplay().getSize(ptSize);
        }
        return ptSize;
    }

    public static Point getDisplaySize(Context context, boolean bIsReal, boolean bIsPortrait) {
        Point ptScreenSize = getDisplaySize(context, bIsReal);
        if (bIsPortrait) {
            return new Point(Math.min(ptScreenSize.x, ptScreenSize.y),
                    Math.max(ptScreenSize.x, ptScreenSize.y));
        } else {
            return new Point(Math.max(ptScreenSize.x, ptScreenSize.y),
                    Math.min(ptScreenSize.x, ptScreenSize.y));
        }
    }


}
