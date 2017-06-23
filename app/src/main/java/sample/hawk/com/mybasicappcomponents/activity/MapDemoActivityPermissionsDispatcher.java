package sample.hawk.com.mybasicappcomponents.activity;

import android.support.v4.app.ActivityCompat;
import java.lang.String;
import permissions.dispatcher.PermissionUtils;

final class MapDemoActivityPermissionsDispatcher {
    private static final int REQUEST_GETMYLOCATION = 0;

    private static final String[] PERMISSION_GETMYLOCATION = new String[] {"android.permission.ACCESS_FINE_LOCATION","android.permission.ACCESS_COARSE_LOCATION"};

    private MapDemoActivityPermissionsDispatcher() {
    }

    static void getMyLocationWithCheck(GoogleMapActivity target) {
        if (PermissionUtils.hasSelfPermissions(target, PERMISSION_GETMYLOCATION)) {
            target.getMyLocation();
        } else {
            ActivityCompat.requestPermissions(target, PERMISSION_GETMYLOCATION, REQUEST_GETMYLOCATION);
        }
    }

    static void onRequestPermissionsResult(GoogleMapActivity target, int requestCode, int[] grantResults) {
        switch (requestCode) {
            case REQUEST_GETMYLOCATION:
                if (PermissionUtils.getTargetSdkVersion(target) < 23 && !PermissionUtils.hasSelfPermissions(target, PERMISSION_GETMYLOCATION)) {
                    return;
                }
                if (PermissionUtils.verifyPermissions(grantResults)) {
                    target.getMyLocation();
                }
                break;
            default:
                break;
        }
    }
}
