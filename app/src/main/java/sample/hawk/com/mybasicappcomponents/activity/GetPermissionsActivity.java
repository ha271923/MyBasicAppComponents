package sample.hawk.com.mybasicappcomponents.activity;

import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import sample.hawk.com.mybasicappcomponents.R;
import sample.hawk.com.mybasicappcomponents.utils.PermissionUtil;
import sample.hawk.com.mybasicappcomponents.utils.SMLog;

import static android.Manifest.permission.ACCESS_COARSE_LOCATION;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

/**
* refer to Mopub SDK NativeListViewFragment.java
*/

public class GetPermissionsActivity extends FragmentActivity {
    public static final int UNUSED_REQUEST_CODE = 255;
    private static final List<String> REQUIRED_DANGEROUS_PERMISSIONS = new ArrayList<String>();

    static {
        REQUIRED_DANGEROUS_PERMISSIONS.add(ACCESS_COARSE_LOCATION); // 位置
        REQUIRED_DANGEROUS_PERMISSIONS.add(WRITE_EXTERNAL_STORAGE); // 儲存
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.getpermissionactivity);
    }

    public void onClickRequestPermissionButton(View view){ // Click Button
        // Add DANGEROUS permissions to List<>
        List<String> dangerousPermissions = new ArrayList<String>();
        for (String permission : REQUIRED_DANGEROUS_PERMISSIONS) {
            if (!PermissionUtil.isPermissionGranted(this, permission)) {
                dangerousPermissions.add(permission);
            }
        }

        // Request dangerous permissions
        if (!dangerousPermissions.isEmpty()) {
            ActivityCompat.requestPermissions(this, dangerousPermissions.toArray(
                    new String[dangerousPermissions.size()]), UNUSED_REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                           int[] grantResults) {
        if (requestCode == UNUSED_REQUEST_CODE) {
            SMLog.i( "Received response for UNUSED_REQUEST_CODE");
            if (PermissionUtil.verifyPermissions(grantResults)) {
                SMLog.i( "permission has been granted.");
                onNewIntent(getIntent()); // Got the permissions from the dialog
                //Toast.makeText(getContext(), "ALLOW: Help APP can XXXX", Toast.LENGTH_SHORT).show();
            } else {
                SMLog.i( "permission were NOT granted.");
                // Toast.makeText(this, "DENY: Help APP can NOT XXXX", Toast.LENGTH_LONG).show();
                finish();
            }
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }
}
