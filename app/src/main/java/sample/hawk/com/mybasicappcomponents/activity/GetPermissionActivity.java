package sample.hawk.com.mybasicappcomponents.activity;

import android.Manifest;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import sample.hawk.com.mybasicappcomponents.R;
import sample.hawk.com.mybasicappcomponents.utils.PermissionUtil;
import sample.hawk.com.mybasicappcomponents.utils.SMLog;

/**
 * Created by ha271 on 2016/12/14.
 */

public class GetPermissionActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.getpermissionactivity);
    }

    public void onClickRequestPermissionButton(View view){ // Click Button
        if (!PermissionUtil.hasSelfPermission(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)){
            // The permission is NOT already granted.
            // Check if the user has been asked about this permission already and denied
            // it. If so, we want to give more explanation about why the permission is needed.
            if (shouldShowRequestPermissionRationale(
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                // Show our own UI to explain to the user why we need to read the contacts
                // before actually requesting the permission and showing the default UI
            }

            // Fire off an async request to actually get the permission
            // This will show the standard permission request dialog UI
            requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    WRITE_EXTERNAL_STORAGE_REQUEST_CODE);
        }
    }

    public static final int WRITE_EXTERNAL_STORAGE_REQUEST_CODE = 234566;
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                           int[] grantResults) {
        if (requestCode == WRITE_EXTERNAL_STORAGE_REQUEST_CODE) {
            SMLog.i( "Received response for REQUEST_WRITE_EXTERNAL_STORAGE");
            // We have requested multiple permissions for WRITE_EXTERNAL_STORAGE, so all of them need to be checked.
            if (PermissionUtil.verifyPermissions(grantResults)) {
                SMLog.i( "WRITE_EXTERNAL_STORAGE has been granted.");
                onNewIntent(getIntent()); // Got the permissions from the dialog
                //Toast.makeText(getContext(), "ALLOW: Help APP can access the database on the phone.", Toast.LENGTH_SHORT).show();
            } else {
                SMLog.i( "WRITE_EXTERNAL_STORAGE were NOT granted.");
                // Toast.makeText(this, "DENY: Help APP can NOT access the database on the phone.", Toast.LENGTH_LONG).show();
                //Utils.showGuideHome(getContext());    //
                finish();
            }
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

}
