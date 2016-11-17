package sample.hawk.com.mybasicappcomponents.misc;

import android.Manifest;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.CheckResult;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresPermission;
import android.support.annotation.UiThread;
import android.support.annotation.WorkerThread;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;

import sample.hawk.com.mybasicappcomponents.R;
import sample.hawk.com.mybasicappcomponents.utils.SMLog;

/**
 @see <a href="https://developer.android.com/reference/android/support/annotation/package-summary.html"> annotations </a>

 NULLNESS ANNOTATIONS
     @NonNull
     @Nullable
 RESOURCE TYPE ANNOTATIONS
     @AnimatorRes
     @AnimRes
     @AnyRes
     @ArrayRes
     @AttrRes
     @BoolRes
     @ColorRes
     @DimenRes
     @DrawableRes
     @FractionRes
     @IdRes
     @IntegerRes
     @InterpolatorRes
     @LayoutRes
     @MenuRes
     @PluralsRes
     @RawRes
     @StringRes
     @StyleableRes
     @StyleRes
     @XmlRes
 THREADING ANNOTATIONS
     @UiThread
     @MainThread
     @WorkerThread
     @BinderThread
 VALUE CONSTRAINTS
     @Size
     @IntRange
     @FloatRange
 INTDEF/STRINGDEF: TYPEDEF ANNOTATIONS
     @IntDef
     @StringDef
 RGB COLOR INTEGERS
    @ColorInt
 OVERRIDING METHODS
    @CallSuper
 RETURN VALUES
    @CheckResult
 */

public class AnnotationActivity extends Activity implements AnnotationInterface {
    Thread WorkerThread;

    private Runnable r;
    private static final String TAG = "AnnotationActivity";
    private TextView mTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.annotation);
        Annotation_Thread();
        Annotation_ValueRange();
        Annotation_Permission();
        int ret = Annotation_ReturnVal();
        Annotation_Compatible();
    }

    private void Annotation_ValueRange() {
        SMLog.i("boolean range: "  + false + " ~ " + true);  // 16Bits Unicode char = or  ~ -65535
        SMLog.i("char    range: "  + Character.MAX_VALUE + " ~ " + Character.MIN_VALUE);  // 16Bits Unicode char = or  ~ -65535
        SMLog.i("byte    range: "  + Byte.MAX_VALUE + " ~ " + Byte.MIN_VALUE);       // 8Bits  = 127 ~ -128
        SMLog.i("short   range: "  + Short.MAX_VALUE + " ~ " + Short.MIN_VALUE);     // 16Bits = 32767 ~ -32768
        SMLog.i("int     range: "  + Integer.MAX_VALUE + " ~ " + Integer.MIN_VALUE); // 32Bits = 2147483647 ~ -2147483648
        Int_Range(100);
        SMLog.i("long    range: "  + Long.MAX_VALUE + " ~ " + Long.MIN_VALUE);       // 64Bits = 9223372036854775807 ~ -9223372036854775808
        SMLog.i("float   range: "  + Float.MAX_VALUE + " ~ " + Float.MIN_VALUE);     // 32Bits = 3.402823e+38 ~ 1.401298e-45
        SMLog.i("double  range: "  + Double.MAX_VALUE + " ~ " + Double.MIN_VALUE);   // 64Bits = 1.797693e+308 ~ 4.900000e-324

    }
    private void Int_Range(@IntRange(from=(-2147483648-1),to=2147483647 ) int value) {
        SMLog.i(TAG,"IntRange( value="+value+" )");
    }
    private void Annotation_Permission() {
        setMyWallpaper(null); // just Highlight this param, Not RED line.
        copyFile("AAA",null);
        startMyActivity(null,null);
    }


    @RequiresPermission(Manifest.permission.SET_WALLPAPER)
    private void setMyWallpaper(@NonNull Bitmap bitmap){
        SMLog.i(TAG,"setMyWallpaper( bitmap )");
    }

    @NonNull
    @SuppressWarnings("all")
    @CheckResult
    private int Annotation_ReturnVal(){

        return 99; // CheckResult annotaion will check the return value is for the return format or Not.
    }

    private void Annotation_Compatible() {
        aboveAPI9();
        aboveAPI24();
        deprecatedAPI();
    }
    /* ＠TargetApi 意思是說︰
    「嘿！Android兄，我知道我現在正用一些比我AndroidManifest.xml裡android:minSdkVersion還要新的的API。
    我想這是沒問題的。因為我很確定我的編譯環境是在新的SDK和新的機器上，我minSdkVersion設那麼低其實只是假的啦(為了在Google Play上被更多人看到我的App)！
    */
    @TargetApi(Build.VERSION_CODES.GINGERBREAD)
    private boolean aboveAPI9(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD){
            SMLog.i(TAG,"aboveAPI9()");
            File f = new File("test");
            f.getTotalSpace();
        }
        return true;
    }
    /* ＠SuppressLint 則是指︰
    「嘿！Android兄，我知道我現在正用一些比我AndroidManifest.xml裡android:minSdkVersion還要新的的API。
    不要再跟我碎唸了！因此，如果要在@TargetApi或@SuppressLint裡做選擇，
    盡量選@TargetApi就是了。
    */
    @TargetApi(24)
    @SuppressLint("NewApi")
    private boolean aboveAPI24(){
        if(Build.VERSION.SDK_INT >= 24){
            SMLog.i(TAG,"aboveAPI24()");
            SMLog.i(TAG,"OOOOOOOOOO new API OOOOOOOOOO");
        }
        return true;
    }

    @TargetApi(5)
    @SuppressWarnings("deprecation")
    private boolean deprecatedAPI(){
        SMLog.i(TAG,"XXXXXXXX API has been deprecated XXXXXXXXX");
        return true;
    }

    @RequiresPermission(allOf = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE})
    public static final void copyFile(@NonNull String dest, @Nullable String source) {
        SMLog.i(TAG,"copyFile( bitmap )");
    }
    @RequiresPermission(android.Manifest.permission.BLUETOOTH)
    public void startMyActivity( Intent intent, @Nullable Bundle bundle) {
        SMLog.i(TAG,"startMyActivity( intent, bundle )");
    }

    private void Annotation_Thread(){
        SMLog.i(TAG,"UiThread TID="+Thread.currentThread().getId());
        mTv =(TextView) findViewById(R.id.annotationText);
        uiAPI();
        workerAPI();

        r = new Runnable() {
            @Override
            public void run() {
                SMLog.i(TAG,"TID="+Thread.currentThread().getId());
                uiAPI();
                workerAPI();
                while(true){
                    doInUiThread();
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    doInWorkerThread();
                    doInWorkerThread_2();
                }
            }
        };
        WorkerThread = new Thread(r);
        WorkerThread.start();
    }

    @Override
    public void doInUiThread() {
        mTv.setText("doInUiThread.........");
    }

    @Override
    public void doInWorkerThread() {
        mTv.setText("doInWorkerThread........."); // Hawk: @WorkerThread annotation can ONLY check the method inside, Not the caller. BUILD pass.
    }

    public void doInWorkerThread_2() { // Hawk: can't detect two level call.
        doInWorkerThread();
    }

    @UiThread
    private void uiAPI(){
        SMLog.i(TAG,"calling uiAPI()");
    }
    @WorkerThread
    private void workerAPI(){
        SMLog.i(TAG,"calling workerAPI()"); // No warning, because no any UI element call in API.
    }
}