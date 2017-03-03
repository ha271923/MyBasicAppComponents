package sample.hawk.com.mybasicappcomponents.network;

import android.app.Activity;
import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import sample.hawk.com.mybasicappcomponents.R;

/**
 * Created by ha271 on 2017/3/3.
 */

public class DownloadManager_Activity extends Activity {
    Context mContext;
    final String mDwnload_file_path = "http://coderzheaven.com/sample_folder/sample_file.png";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setContentView(R.layout.httpdownloadfile);
        TextView tvURL = (TextView) findViewById(R.id.tvURL);
        tvURL.setText("Check Android Download Manager at Notification Bar");
        Button downloadBtn = (Button) findViewById(R.id.downloadBtn);
        downloadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isDownloadManagerAvailable() == true){
                    DownloadManager.Request request = new DownloadManager.Request(Uri.parse(mDwnload_file_path));
                    request.setDescription("MyBasicAppComponents testing!");
                    request.setTitle("DownloadManager_Activity");
                    // in order for this if to run, you must use the android 3.2 to compile your app
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
                        request.allowScanningByMediaScanner();
                        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                    }
                    request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "sample_file.png");
                    // get download service and enqueue file
                    DownloadManager manager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
                    manager.enqueue(request);
                    /**
                        CAUTION!! Download progress will be shown in the system notification bar.
                    */
                }
            }
        });
    }

    public static boolean isDownloadManagerAvailable() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
            return true;
        }
        return false;
    }
}
