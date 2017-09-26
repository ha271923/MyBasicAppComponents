package sample.hawk.com.mybasicappcomponents.debugTest.MemoryLeak;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.widget.TextView;

import sample.hawk.com.mybasicappcomponents.R;


/**

 http://www.codexpedia.com/android/memory-leak-examples-and-solutions-in-android/

 * This class will have memory leak, because the anonymous Runnable is holding an implicit reference of
 * the Activity. When the Activity is destroyed within 10 seconds after it’s created, it will have a memory
 * leak becasuse the anonymous runnable is still holding a reference of the activity which caused the
 * activity not being garbage collected.
 */

public class MemLeak_handler_anonymous_runnable_Activity extends Activity {
    private Handler mLeakyHandler = new Handler();
    private TextView myTextBox;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.memleaksample_activity);
        myTextBox = (TextView) findViewById(R.id.tv_handler);

        // Post a message and delay its execution for 10 seconds.
        mLeakyHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                myTextBox.setText("Done");
            }
        }, 1000 * 10);
    }


    /*
    * Fix(1): for the above memory leak by calling the method removeCallbacksAndMessages
    * on the handler in the onDestroy method of the activity.
    */
    /*
    @Override
    protected void onDestroy() {
        super.onDestroy();
        //This resolves the memory leak by removing the handler references.
        mLeakyHandler.removeCallbacksAndMessages(null);
    }
    */
    /*
    * Fix(2): for the memory leak from the handler by using static inner class and WeakReference.
    */
    /*
    public class HandlerExample extends AppCompatActivity {
        private Handler mLeakyHandler = new Handler();
        private TextView tvText;
        @Override
        protected void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_samples);
            tvText = (TextView) findViewById(R.id.tv_handler);

            // Post a message and delay its execution for 10 seconds.
            mLeakyHandler.postDelayed(new MyRunnable(tvText), 1000 * 10);
        }
        private static class MyRunnable implements Runnable {
            WeakReference<TextView> tvText;
            public MyRunnable(TextView tvText) {
                this.tvText = new WeakReference<TextView>(tvText);
            }

            @Override
            public void run() {
                //Save the TextView to a local variable because the weak referenced object could become empty at any time
                TextView mText = tvText.get();
                if (mText != null) {
                    mText.setText("Done");
                }
            }
        }
    }
    */


}
