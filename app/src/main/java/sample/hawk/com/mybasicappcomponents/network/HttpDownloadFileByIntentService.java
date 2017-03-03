package sample.hawk.com.mybasicappcomponents.network;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.os.ResultReceiver;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import sample.hawk.com.mybasicappcomponents.utils.SMLog;

/**
 * Created by ha271 on 2017/3/3.
 */

public class HttpDownloadFileByIntentService extends IntentService {
    ResultReceiver mReceiver;
    public static final int UPDATE_PROGRESS = 8344;
    int mDownloadedSize = 0;
    int mTotalSize = 0;

    public HttpDownloadFileByIntentService() {
        super("HttpDownloadFileByIntentService");
    }


    String downloadFile(String downloadUrl){
        try {
            // STEP2. set the path where we want to save the file
            File FileDir = Environment.getExternalStorageDirectory();
            final File file = new File(FileDir,"downloaded_file.png");
            FileOutputStream fileOutput = new FileOutputStream(file);
            // STEP3. HTTP download
            URL url = new URL(downloadUrl);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setDoOutput(true);
            urlConnection.connect();
            // expect HTTP 200 OK, so we don't mistakenly save error report
            // instead of the file
            if (urlConnection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                return "Server returned HTTP " + urlConnection.getResponseCode()
                        + " " + urlConnection.getResponseMessage();
            }
            mTotalSize = urlConnection.getContentLength(); // total downloading size
            // STEP3-1. Stream used for reading the data from the internet
            byte[] buffer = new byte[1024]; //create a buffer...
            int bufferLength = 0;
            InputStream inputStream = urlConnection.getInputStream();
            // STEP3-2. Downloading...
            while ( (bufferLength = inputStream.read(buffer)) > 0 ) {
                fileOutput.write(buffer, 0, bufferLength);
                mDownloadedSize += bufferLength;

                Bundle resultData = new Bundle();
                resultData.putInt("progress" ,(int) (mDownloadedSize * 100 / mTotalSize));
                mReceiver.send(UPDATE_PROGRESS, resultData);
            }
            // STEP4. finish download
            SMLog.i("Saved at:\n"+file.toString());
            fileOutput.close(); //close the output stream when complete
            Bundle resultData = new Bundle();
            resultData.putInt("progress" ,100);
            resultData.putString("filePath", file.toString());
            mReceiver.send(UPDATE_PROGRESS, resultData);

        } catch (final MalformedURLException e) {
            SMLog.i("Error : MalformedURLException " + e);
            e.printStackTrace();
        } catch (final IOException e) {
            SMLog.i("Error : IOException " + e);
            e.printStackTrace();
        }
        catch (final Exception e) {
            SMLog.i("Error : Please check your internet connection " + e);
        }
        return null;
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        String urlToDownload = intent.getStringExtra("url");
        mReceiver = (ResultReceiver) intent.getParcelableExtra("receiver");
        downloadFile(urlToDownload);
    }
}