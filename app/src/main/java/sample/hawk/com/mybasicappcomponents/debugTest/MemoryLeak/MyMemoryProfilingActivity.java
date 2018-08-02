package sample.hawk.com.mybasicappcomponents.debugTest.MemoryLeak;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.ComponentCallbacks2;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import sample.hawk.com.mybasicappcomponents.R;
import sample.hawk.com.mybasicappcomponents.utils.SMLog;

/**
 * Manage your app's memory
 * refer to https://developer.android.com/topic/performance/memory
 */

public class MyMemoryProfilingActivity extends Activity implements ComponentCallbacks2 {

    TextView mAvailableMemory;
    MemoryEater mMemoryEater;
    int      iWasted_Local_var;
    byte[][]  mWasted_Local_DB;
    int      iWasted_Object_var;
    byte[][]  mWasted_Object_DB;
    int      iWasted_Static_var;
    byte[][]   mWasted_Static_DB;
    int      iObjectLeakage_var;
    ObjectLeakage[]   mObjectLeakage_DB;
    int      iWasted_Thread_var;
    ThreadLeakage[]   mWasted_Thread_DB;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mymemoryprofiling);
        mAvailableMemory= (TextView)findViewById(R.id.availablememory);
        mMemoryEater = new MemoryEater();
        mWasted_Local_DB = new byte[1000][];
        mWasted_Object_DB = new byte[1000][];
        mWasted_Static_DB = new byte[1000][];
        mObjectLeakage_DB = new ObjectLeakage[1000];
        mWasted_Thread_DB = new ThreadLeakage[1000];
        updateMemoryUsage();
    }

    public void onClick_LOCAL_VAR(View v){
        mWasted_Local_DB[iWasted_Local_var] = mMemoryEater.waste_Memory(MemoryEater.TYPE.LOCAL_VAR,10*1024*1024);
        iWasted_Local_var++;
        updateMemoryUsage();
    }

    public void onClick_OBJECT_VAR(View v){
        mWasted_Object_DB[iWasted_Object_var] = mMemoryEater.waste_Memory(MemoryEater.TYPE.OBJECT_VAR,10*1024*1024);
        iWasted_Object_var++;
        updateMemoryUsage();
    }

    public void onClick_STATIC_VAR(View v){
        mWasted_Static_DB[iWasted_Static_var] = mMemoryEater.waste_Memory(MemoryEater.TYPE.STATIC_VAR,10*1024*1024);
        iWasted_Static_var++;
        updateMemoryUsage();
    }

    public void onClick_OBJECT(View v){
        mObjectLeakage_DB[iObjectLeakage_var] = mMemoryEater.waste_Object(1024);
        iObjectLeakage_var++;
        updateMemoryUsage();
    }

    public void onClick_THREAD(View v){
        mWasted_Thread_DB[iWasted_Thread_var] = mMemoryEater.waste_Thread(1000);
        iWasted_Thread_var++;
        updateMemoryUsage();
    }

    public void onClick_GC(View v){
        System.gc();
        updateMemoryUsage();
    }


    private void TryGetMemoryClassMethod(){
        ActivityManager activityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);

        int per_app_mem = activityManager.getMemoryClass(); // really brian dead now
        SMLog.i("per_app_mem="+per_app_mem);
    }

    /**
     * Release memory when the UI becomes hidden or when system resources become low.
     * @param level the memory-related event that was raised.
     */
    public void onTrimMemory(int level) {

        // Determine which lifecycle or system event was raised.
        switch (level) {

            case ComponentCallbacks2.TRIM_MEMORY_UI_HIDDEN:
                /*
                   Release any UI objects that currently hold memory.

                   The user interface has moved to the background.
                */
                SMLog.e("TRIM_MEMORY_UI_HIDDEN");
                break;

            case ComponentCallbacks2.TRIM_MEMORY_RUNNING_MODERATE:
            case ComponentCallbacks2.TRIM_MEMORY_RUNNING_LOW:
            case ComponentCallbacks2.TRIM_MEMORY_RUNNING_CRITICAL:

                /*
                   Release any memory that your app doesn't need to run.

                   The device is running low on memory while the app is running.
                   The event raised indicates the severity of the memory-related event.
                   If the event is TRIM_MEMORY_RUNNING_CRITICAL, then the system will
                   begin killing background processes.
                */
                SMLog.e("TRIM_MEMORY_RUNNING_MODERATE\nTRIM_MEMORY_RUNNING_LOW\nTRIM_MEMORY_RUNNING_CRITICAL");
                break;

            case ComponentCallbacks2.TRIM_MEMORY_BACKGROUND:
            case ComponentCallbacks2.TRIM_MEMORY_MODERATE:
            case ComponentCallbacks2.TRIM_MEMORY_COMPLETE:

                /*
                   Release as much memory as the process can.

                   The app is on the LRU list and the system is running low on memory.
                   The event raised indicates where the app sits within the LRU list.
                   If the event is TRIM_MEMORY_COMPLETE, the process will be one of
                   the first to be terminated.
                */
                SMLog.e("TRIM_MEMORY_BACKGROUND\nTRIM_MEMORY_MODERATE\nTRIM_MEMORY_COMPLETE");
                break;

            default:
                /*
                  Release any non-critical data structures.

                  The app received an unrecognized memory level value
                  from the system. Treat this as a generic low-memory message.
                */
                SMLog.e("Release!!! default="+level);
                break;
        }

        updateMemoryUsage();
    }

    public void updateMemoryUsage() {

        // Before doing something that requires a lot of memory,
        // check to see whether the device is in a low memory state.
        ActivityManager.MemoryInfo memoryInfo = getAvailableMemory();

        if (memoryInfo.lowMemory) {
            mAvailableMemory.setTextColor(Color.RED);
        }
        mAvailableMemory.setText(new String("Available="+memoryInfo.availMem+"\nTotal="+memoryInfo.totalMem));
    }

    // Get a MemoryInfo object for the device's current memory status.
    private ActivityManager.MemoryInfo getAvailableMemory() {
        ActivityManager activityManager = (ActivityManager) this.getSystemService(ACTIVITY_SERVICE);
        ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
        activityManager.getMemoryInfo(memoryInfo);
        return memoryInfo;
    }

}
