package sample.hawk.com.mybasicappcomponents.background;

/**
 * Created by ha271 on 2017/5/18.
 */

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

import sample.hawk.com.mybasicappcomponents.R;
import sample.hawk.com.mybasicappcomponents.utils.SMLog;

public class MyStringLoaderActivity2 extends AppCompatActivity {
    LoaderManager mloaderManager;
    AsyncTaskLoader<String> mAsyncTaskLoader;
    LoaderCallbacks<String> mloaderCallBack;

    public void loadData() {
        if (mAsyncTaskLoader.isStarted()) {
            SMLog.i("mAsyncTaskLoader.reset()");
            mAsyncTaskLoader.reset();
        }
        mAsyncTaskLoader.forceLoad();
        SMLog.i("mAsyncTaskLoader.forceLoad()");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mystringloader_activity2);

        mloaderManager = getSupportLoaderManager();
        mloaderCallBack = new LoaderCallbacks<String>() {
            @Override
            public Loader<String> onCreateLoader(int arg0, Bundle arg1) {
                SMLog.i("  onLoadFinished() TID="+ Thread.currentThread().getId()+"    arg0="+arg0+"    arg1="+arg1);
                mAsyncTaskLoader = new StringDataLoader(MyStringLoaderActivity2.this);
                return mAsyncTaskLoader;
            }
            @Override
            public void onLoaderReset(Loader<String> arg0) {
                SMLog.i("  onLoaderReset() TID="+ Thread.currentThread().getId()+"    arg0="+arg0);
            }
            @Override
            public void onLoadFinished(Loader<String> arg0, String arg1) {
                SMLog.i("  onLoadFinished() TID="+ Thread.currentThread().getId()+"    arg0="+arg0+"    arg1="+arg1);
                ((TextView) findViewById(R.id.tvOutput)).setText(arg1);
            }
        };
        SMLog.i("loaderManager.initLoader()");
        mloaderManager.initLoader(1, null, mloaderCallBack);
        loadData();
    }
    public void onRefreshButton_Click(View view){
        SMLog.i("onRefreshButton_Click()");
        loadData();
    }

    static class StringDataLoader extends AsyncTaskLoader<String> {
        private String mLoadData;
        public StringDataLoader(Context context) {
            super(context);
        }

        @Override
        public void deliverResult(String data) {
            super.deliverResult(data);
            SMLog.i("    deliverResult()  TID="+ Thread.currentThread().getId() + "    data:" + data);
        }

        // Handles a request to cancel a load.
        @Override
        public void onCanceled(String data) {
            super.onCanceled(data);
            SMLog.i("    onCanceled()  TID="+ Thread.currentThread().getId() + "    data:" + data);
            // At this point we can release the resources associated with 'apps'
            // if needed.
            ReleaseResources();
        }

        // Handles a request to completely reset the Loader.
        @Override
        protected void onReset() {
            super.onReset();
            SMLog.i("    onReset()  TID="+ Thread.currentThread().getId());
            // Ensure the loader is stopped
            onStopLoading();
            ReleaseResources();
        }

        @Override
        protected void onStartLoading() {
            SMLog.i("    onStartLoading()  TID=" + Thread.currentThread().getId());
        }

        @Override
        public String loadInBackground() {
            SMLog.i("    loadInBackground()  TID=" + Thread.currentThread().getId());
            return CreateStringData();
        }

        private void ReleaseResources() {
            // At this point we can release the resources associated with 'apps'if needed.
            if (mLoadData != null) {
                mLoadData = null;
            }
        }

        private String CreateStringData(){
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            String returnString  = dateFormat.format(new Date());
            return returnString;
        }
    }

}