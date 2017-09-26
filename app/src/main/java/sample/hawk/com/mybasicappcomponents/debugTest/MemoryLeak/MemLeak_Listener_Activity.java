package sample.hawk.com.mybasicappcomponents.debugTest.MemoryLeak;

import android.app.Activity;
import android.os.Bundle;

import sample.hawk.com.mybasicappcomponents.utils.SMLog;

/*

http://www.codexpedia.com/android/memory-leak-examples-and-solutions-in-android/

* Tere is a Utility.java class, it’s a Singleon class with a method to do long running background
* task, the task will call the onUpdate method on the listener.
* Here is a sample activity class that implements the listener and not clearing the listener onDestroy(),
* which will cause memory leak when the acitivty is destroyed by going to other screen or rotating the screen.
*
* */
public class MemLeak_Listener_Activity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Setting the listener
        Utility.getInstance().setListener(new Utility.UpdateListener() {
            @Override
            public void onUpdate() {
                SMLog.d("Something is updated!");
            }
        });

        //Starting a background thread
        Utility.getInstance().startNewTread();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Fix: The solution to fix the memory leak is as simple as setting the listener to null by adding this line in the onDestroy method.
        // Utility.getInstance().setListener(null);
    }
}