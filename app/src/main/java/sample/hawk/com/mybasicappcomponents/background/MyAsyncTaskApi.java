package sample.hawk.com.mybasicappcomponents.background;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;

import java.lang.ref.WeakReference;
import java.util.concurrent.Executor;

import sample.hawk.com.mybasicappcomponents.utils.logger2.Logger;

/**
 * Created by ha271 on 2017/9/6.
 */

public class MyAsyncTaskApi {
    static private String LOG_TAG = "MyAsyncTaskApi";
    public interface MyAsyncTaskApiCallback {
        public void onAsyncTaskApiCompleted(Bitmap blurredBitmap);
    }

    public static void MyAsyncTaskApi(final Context context, Bitmap bitmap, MyAsyncTaskApiCallback apiCallback, Executor executor) {
        Context contextBAK = context;

        final WeakReference<MyAsyncTaskApiCallback> apiCallbackRef = new WeakReference<>(apiCallback);
        Logger.d(LOG_TAG, "MyAsyncTaskApic+++  TID=%d", Thread.currentThread().getId());
        new AsyncTask<Bitmap, Void, Bitmap>() {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected void onProgressUpdate(Void... values) {
                super.onProgressUpdate(values);
            }

            @Override
            protected void onCancelled() {
                super.onCancelled();
            }

            @Override
            protected Bitmap doInBackground(Bitmap... params) {
                Logger.d(LOG_TAG, "MyAsyncTaskApi::doInBackground+++  TID=%d", Thread.currentThread().getId());
                Bitmap bitmap = params[0];
                // TODO: add task processing at here
                Logger.d(LOG_TAG, "MyAsyncTaskApi::doInBackground---  TID=%d", Thread.currentThread().getId());
                return bitmap;
            }

            @Override
            protected void onPostExecute(Bitmap result) {
                super.onPostExecute(result);
                Logger.d(LOG_TAG, "MyAsyncTaskApi::postExe+++  TID=%d", Thread.currentThread().getId());
                final MyAsyncTaskApiCallback callback = apiCallbackRef.get();
                callback.onAsyncTaskApiCompleted(result);
                Logger.d(LOG_TAG, "MyAsyncTaskApi::postExe---  TID=%d", Thread.currentThread().getId());
            }
        }.executeOnExecutor(executor, bitmap);


        Logger.d(LOG_TAG, "MyAsyncTaskApi---  TID=%d", Thread.currentThread().getId());
    }


}
