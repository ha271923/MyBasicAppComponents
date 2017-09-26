package sample.hawk.com.mybasicappcomponents.debugTest.MemoryLeak;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Date;

import sample.hawk.com.mybasicappcomponents.R;
import sample.hawk.com.mybasicappcomponents.utils.SMLog;

/**

 http://www.codexpedia.com/android/memory-leak-examples-and-solutions-in-android/

 * This example will have memory leaks when you rotate the device within 10 seconds after it’s created.
 * The activity is destroyed on screen rotation, but since the AsyncTask is declared as non-static class,
 * the background task is still holding a reference of the activity which made the activty not eligible
 * for garbage collection, thus it becomes a memory leak.
 */
public class MemLeak_AsyncTask_Activity extends Activity {
    private TextView tvText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.memleaksample_activity);
        tvText = (TextView) findViewById(R.id.tv_async);

        TestAsyncTask();
    }


    private void TestAsyncTask(){
        SMLog.i("STEP_1: ");
        new SampleTask().execute(); // Code sequence: STEP_1,2,3-1,3-2....
        SMLog.i("STEP_2: ");

    }


    private class SampleTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            try {
                SMLog.i("STEP_3: Async_3-1: ");
                Thread.sleep(1000 * 10);
                SMLog.i("STEP_3: Async_3-2: ");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            SMLog.i("STEP_3: Async_3-3: ");
            tvText.setText("Done " + new Date().getTime());
        }
    }
}

/*
* The solution to the above memory leak in AsyncTask is to declare the AsyncTask as a static inner
* class and use a WeakReference to get a hold of the parent activity.
*/
/*
public class MemLeak_AsyncTask_Activity extends Activity {

    private TextView tvText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_samples);
        tvText = (TextView) findViewById(R.id.tv_sample);

        new SampleTask(this).execute();
    }

    public void updateText(String text) {
        tvText.setText(text);
    }

    private static class SampleTask extends AsyncTask<Void, Void, Void> {
        private WeakReference<MemLeak_AsyncTask_Activity> mRef;

        public SampleTask(MemLeak_AsyncTask_Activity activity) {
            mRef = new WeakReference<MemLeak_AsyncTask_Activity>(activity);
        }

        @Override
        protected Void doInBackground(Void... params) {
            try {
                Thread.sleep(1000 * 10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            MemLeak_AsyncTask_Activity asyncTaskLeak = mRef.get();
            // Make sure the activity is not null before doing anything on it because the destroyed activity could be null.
            if (asyncTaskLeak != null)
                asyncTaskLeak.updateText("Done " + new Date().getTime());
        }
    }
}
*/