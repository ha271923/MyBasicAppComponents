package sample.hawk.com.mybasicappcomponents.misc;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.UiThread;
import android.support.annotation.WorkerThread;
import android.widget.TextView;

import sample.hawk.com.mybasicappcomponents.R;
import sample.hawk.com.mybasicappcomponents.utils.SMLog;

/**
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
        TestThreadAnnotations();
    }

    private void TestThreadAnnotations(){
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