package sample.hawk.com.mybasicappcomponents.provider;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.content.FileProvider;
import android.view.View;
import android.widget.Button;

import java.io.File;

import sample.hawk.com.mybasicappcomponents.R;
import sample.hawk.com.mybasicappcomponents.utils.Util;

/**
 * Example of sharing content from a private content provider.
 */

public class ShareFile_Activity extends Activity {
    private static final String AUTHORITY = "sample.hawk.com.mybasicappcomponents.provider.share_files";
    Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setContentView(R.layout.buttons_activity);
        Button shareButton = (Button)findViewById(R.id.Button1);
        shareButton.setVisibility(View.VISIBLE);
        shareButton.setText("Share a File to the registered APPs");
        shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Save a thumbnail to file
                final File thumbsDir = new File(getFilesDir(), "thumbs");
                thumbsDir.mkdirs();
                final File file = new File(thumbsDir, "private.png");
                // Now share that private file to other APPs via Android FileProvider
                Util.saveThumbnailFile(v, file);
                Uri contentUri = FileProvider.getUriForFile(mContext, AUTHORITY, file);
                Intent shareIntent = new Intent();
                shareIntent.setAction(Intent.ACTION_SEND);
                shareIntent.setType("image/jpeg");
                shareIntent.putExtra(Intent.EXTRA_STREAM, contentUri);
                shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                startActivity(Intent.createChooser(shareIntent, "Share with"));
            }
        });


    }

}
