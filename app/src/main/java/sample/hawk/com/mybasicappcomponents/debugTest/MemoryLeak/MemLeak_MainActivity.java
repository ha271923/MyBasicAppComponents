package sample.hawk.com.mybasicappcomponents.debugTest.MemoryLeak;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import sample.hawk.com.mybasicappcomponents.BuildConfig;
import sample.hawk.com.mybasicappcomponents.R;
import sample.hawk.com.mybasicappcomponents.utils.SMLog;

/**

 http://www.codexpedia.com/android/memory-leak-examples-and-solutions-in-android/

 */

public class MemLeak_MainActivity extends Activity {

    static public StrictMode.ThreadPolicy.Builder m_threadPolicyBuilder;
    static public StrictMode.VmPolicy.Builder m_vmPolicyBuilder;
    static public RelativeLayout m_OutputLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (BuildConfig.DEBUG) {
            setContentView(R.layout.memleaktests_activity);
            m_OutputLayout = (RelativeLayout) findViewById(R.id.OutputLayout);
            enableStrictMode();
        }
        else{
            Toast.makeText(this, "Only for DEBUG build" + this,Toast.LENGTH_LONG).show();
        }
    }
    /*

      https://developer.android.com/reference/android/os/StrictMode.html

      The possible alerts that you can choose are:
        A. Write to LogCat
        B. Display a dialog
        C. Flash the screen
        D. write to the DropBox log file
        E. Crash the application
    */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static void enableStrictMode() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
            m_threadPolicyBuilder =
                    new StrictMode.ThreadPolicy.Builder()
                            .detectAll()
                            .penaltyDialog()
                            .penaltyLog();

            m_vmPolicyBuilder =
                    new StrictMode.VmPolicy.Builder()
                            .detectAll()
                            .penaltyLog();

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
                m_threadPolicyBuilder.penaltyFlashScreen();

            }

            StrictMode.setThreadPolicy(m_threadPolicyBuilder.build());
            StrictMode.setVmPolicy(m_vmPolicyBuilder.build());
        }
    }


    public void onClick_NewActivity(View view) {
        // For startActivity to another Activity, you MUST concat the class_path string with Tag.
        final String class_path = "sample.hawk.com.mybasicappcomponents.debugTest";
        String Tag = view.getTag().toString();
        String ActivityPath = class_path + Tag;
        SMLog.i("ActivityPath= " + ActivityPath);
        startNewActivity(ActivityPath);
    }

    private void startNewActivity(String activitypath){
        Intent intent = new Intent();
        Class<?> cls;
        try {
            cls = Class.forName(activitypath);
            intent.setClass( MemLeak_MainActivity.this, cls );
            Bundle bundle=new Bundle();
            bundle.putString("FromActivity", activitypath);
            intent.putExtras(bundle);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
        startActivity(intent);
    }

}
