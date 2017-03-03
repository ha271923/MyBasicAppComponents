package sample.hawk.com.mybasicappcomponents.network;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import sample.hawk.com.mybasicappcomponents.R;
import sample.hawk.com.mybasicappcomponents.utils.SMLog;

import static android.view.View.VISIBLE;

/**
 * Copy from StackOverflow at:
 * http://stackoverflow.com/questions/3028306/download-a-file-with-android-and-showing-the-progress-in-a-progressdialog
 * */

public class HttpDownloadFileByThread_Activity extends Activity {
    Context mContext;
    ProgressBar mProgressBar;
    Dialog mDialog;
    int mDownloadedSize = 0;
    int mTotalSize = 0;
    TextView mCurrentProgress;
    final String mDwnload_file_path = "http://coderzheaven.com/sample_folder/sample_file.png";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setContentView(R.layout.httpdownloadfile);
        TextView tvURL = (TextView) findViewById(R.id.tvURL);
        tvURL.setText(mDwnload_file_path);
        Button downloadBtn = (Button) findViewById(R.id.downloadBtn);
        downloadBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                showProgressDialog(mDwnload_file_path);
                new Thread(new Runnable() {
                    public void run() {
                        downloadFile(); // STEP1. download it at workerThread.
                    }
                }).start();
            }
        });
    }

    void downloadFile(){
        try {
            // STEP2. set the path where we want to save the file
            File FileDir = Environment.getExternalStorageDirectory();
            final File file = new File(FileDir,"downloaded_file.png");
            FileOutputStream fileOutput = new FileOutputStream(file);
            // STEP3. HTTP download
            URL url = new URL(mDwnload_file_path);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setDoOutput(true);
            urlConnection.connect();
            mTotalSize = urlConnection.getContentLength(); // total downloading size
            runOnUiThread(new Runnable() {
                public void run() {
                    mProgressBar.setMax(mTotalSize);
                }
            });
            // STEP3-1. Stream used for reading the data from the internet
            byte[] buffer = new byte[1024]; //create a buffer...
            int bufferLength = 0;
            InputStream inputStream = urlConnection.getInputStream();
            // STEP3-2. Downloading...
            while ( (bufferLength = inputStream.read(buffer)) > 0 ) {
                fileOutput.write(buffer, 0, bufferLength);
                mDownloadedSize += bufferLength;
                // STEP3-2-1. update the progressbar
                runOnUiThread(new Runnable() {
                    public void run() {
                        mProgressBar.setProgress(mDownloadedSize);
                        float per = ((float) mDownloadedSize / mTotalSize) * 100;
                        mCurrentProgress.setText("Downloaded " + mDownloadedSize + "KB / " + mTotalSize + "KB (" + (int)per + "%)" );
                    }
                });
            }
            // STEP4. finish download
            SMLog.i("Saved at:\n"+file.toString());
            fileOutput.close(); //close the output stream when complete
            runOnUiThread(new Runnable() {
                public void run() {
                    // STEP4-1. show the result
                    // mProgressBar.dismiss(); // if you want close it..
                    // if the downloaded file is an Image file, we show it at dialog.
                    ImageView DownloadedImage = (ImageView) mDialog.findViewById(R.id.ivFile);
                    DownloadedImage.setImageBitmap(BitmapFactory.decodeFile(file.toString()));
                    DownloadedImage.setVisibility(VISIBLE);
                }
            });
        } catch (final MalformedURLException e) {
            showError("Error : MalformedURLException " + e);
            e.printStackTrace();
        } catch (final IOException e) {
            showError("Error : IOException " + e);
            e.printStackTrace();
        }
        catch (final Exception e) {
            showError("Error : Please check your internet connection " + e);
        }
    }

    void showError(final String err){
        runOnUiThread(new Runnable() {
            public void run() {
                Toast.makeText(mContext, err, Toast.LENGTH_LONG).show();
            }
        });
    }

    void showProgressDialog(String file_path){
        // init the dialog
        mDialog = new Dialog(mContext);
        mDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        mDialog.setContentView(R.layout.myprogressdialog);
        mDialog.setTitle("Download Progress");
        TextView tvPrompt = (TextView) mDialog.findViewById(R.id.tvPrompt);
        tvPrompt.setText("Downloading file from ... " + file_path);
        mCurrentProgress = (TextView) mDialog.findViewById(R.id.tvCurrentProgress);
        mCurrentProgress.setText("Starting download...");
        mDialog.show();
        mProgressBar = (ProgressBar) mDialog.findViewById(R.id.progress_bar);
        mProgressBar.setProgress(0);
        // mProgressBar.setProgressDrawable(getResources().getDrawable(R.drawable.green_progress));
    }
}