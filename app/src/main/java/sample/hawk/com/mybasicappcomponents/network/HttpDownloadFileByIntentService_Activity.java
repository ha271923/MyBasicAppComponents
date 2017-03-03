package sample.hawk.com.mybasicappcomponents.network;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import sample.hawk.com.mybasicappcomponents.R;

import static android.view.View.VISIBLE;

/**
 * Created by ha271 on 2017/3/3.
 */

public class HttpDownloadFileByIntentService_Activity extends Activity {
    Context mContext;
    ProgressBar mProgressBar;
    Dialog mDialog;
    TextView mCurrentProgress;
    final String mDwnload_file_path = "http://coderzheaven.com/sample_folder/sample_file.png";

    private class DownloadReceiver extends ResultReceiver {
        public DownloadReceiver(Handler handler) {
            super(handler);
        }

        @Override
        protected void onReceiveResult(int resultCode, Bundle resultData) {
            super.onReceiveResult(resultCode, resultData);
            if (resultCode == HttpDownloadFileByIntentService.UPDATE_PROGRESS) {
                int progress = resultData.getInt("progress");
                String filePath = resultData.getString("filePath");
                mProgressBar.setProgress(progress);
                if (progress == 100) {
                    // mDialog.dismiss();
                    // if the downloaded file is an Image file, we show it at dialog.
                    ImageView DownloadedImage = (ImageView) mDialog.findViewById(R.id.ivFile);
                    DownloadedImage.setImageBitmap(BitmapFactory.decodeFile(filePath));
                    DownloadedImage.setVisibility(VISIBLE);
                }
            }
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setContentView(R.layout.httpdownloadfile);
        TextView tvURL = (TextView) findViewById(R.id.tvURL);
        tvURL.setText(mDwnload_file_path);
        Button downloadBtn = (Button) findViewById(R.id.downloadBtn);
        downloadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // this is how you fire the downloader
                showProgressDialog(mDwnload_file_path);
                Intent intent = new Intent(mContext, HttpDownloadFileByIntentService.class);
                intent.putExtra("url", mDwnload_file_path);
                intent.putExtra("receiver", new DownloadReceiver(new Handler()));
                startService(intent);
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