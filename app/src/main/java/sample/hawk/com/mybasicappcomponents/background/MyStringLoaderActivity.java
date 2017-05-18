package sample.hawk.com.mybasicappcomponents.background;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import sample.hawk.com.mybasicappcomponents.R;
import sample.hawk.com.mybasicappcomponents.utils.SMLog;

/* Build Pass! */
// import android.support.v4.app.LoaderManager;
// import android.support.v4.content.Loader;
// import android.support.v4.content.AsyncTaskLoader;
/* Build Error! */
// import android.app.LoaderManager;
// import android.content.Loader;
// import android.content.AsyncTaskLoader;
/**
 *
 */

public class MyStringLoaderActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<String> {
    private TextView tvJsonResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mystringloader_activity);

        tvJsonResult = (TextView) findViewById(R.id.tv_json_result);
        //     initLoader -> onCreateLoader -> loadInBackground -> deliverResult -> onLoadFinished
        SMLog.i(".initLoader()  TID="+Thread.currentThread().getId());
        getSupportLoaderManager().initLoader(0, null, (android.support.v4.app.LoaderManager.LoaderCallbacks<String>)this).forceLoad();
    }

    // defined in Activity for LoaderManager.LoaderCallbacks
    @Override
    public Loader<String> onCreateLoader(int id, Bundle bundle) {
        SMLog.i("  LoaderCallbacks::onCreateLoader()  TID="+Thread.currentThread().getId()+"    bundle="+bundle);
        return new WebDataLoader(this);
    }
    @Override
    public void onLoadFinished(Loader<String> loader, String data) {
        SMLog.i("  LoaderCallbacks::onLoadFinished()  TID="+Thread.currentThread().getId()+"    data="+data);
        tvJsonResult.setText(data);
    }
    @Override
    public void onLoaderReset(Loader<String> loader) {
        SMLog.i("  LoaderCallbacks::onLoaderReset()  TID="+Thread.currentThread().getId());
    }

    private static class WebDataLoader extends AsyncTaskLoader<String> {

        public WebDataLoader(Context context) {
            super(context);
            SMLog.i("    FetchString::constructor()  TID="+Thread.currentThread().getId());
        }

        @Override
        public void waitForLoader() {
            super.waitForLoader();
            SMLog.i("    FetchString::waitForLoader()  TID="+Thread.currentThread().getId());
        }

        @Override
        public String loadInBackground() {
            SMLog.i("    FetchString::loadInBackground() ++++ TID="+Thread.currentThread().getId());
            HttpURLConnection urlConnection = null;
            BufferedReader reader = null;
            String jsonStr = null;
            String line;
            try {
                URL url = new URL("https://itunes.apple.com/search?term=classic");
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                // Read the input stream into a String
                InputStream inputStream = urlConnection.getInputStream();
                StringBuffer buffer = new StringBuffer();
                if (inputStream == null) return null;

                reader = new BufferedReader(new InputStreamReader(inputStream));
                while ((line = reader.readLine()) != null) buffer.append(line);

                if (buffer.length() == 0) return null;
                jsonStr = buffer.toString();

            } catch (IOException e) {
                SMLog.e("    FetchString Error: "+ e);
                return null;
            } finally {
                if (urlConnection != null) urlConnection.disconnect();
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (final IOException e) {
                        SMLog.e("    FetchString Error then closing stream: "+ e);
                    }
                }
            }
            SMLog.i("    FetchString::loadInBackground() ---- TID="+Thread.currentThread().getId());
            return jsonStr;
        }

        @Override
        public void deliverResult(String data) {
            super.deliverResult(data);
            SMLog.i("    FetchString::deliverResult() TID="+Thread.currentThread().getId());
        }
    }

}
