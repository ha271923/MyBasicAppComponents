package sample.hawk.com.mybasicappcomponents.controls;

import android.content.Context;
import android.content.pm.PackageManager;

/**
 * Simple proxy for querying input device properties from C++.
 */
public class TouchDevice {

    /**
     * Static methods only so make constructor private.
     */
    private TouchDevice() { }

    /**
     * @return Maximum supported touch points.
     */
    public static int maxTouchPoints(Context context) {
        // Android only tells us if the device belongs to a "Touchscreen Class" which only
        // guarantees a minimum number of touch points. Be conservative and return the minimum,
        // checking membership from the highest class down.

        if (context.getPackageManager().hasSystemFeature(
                PackageManager.FEATURE_TOUCHSCREEN_MULTITOUCH_JAZZHAND)) {
            return 5;
        } else if (context.getPackageManager().hasSystemFeature(
                PackageManager.FEATURE_TOUCHSCREEN_MULTITOUCH_DISTINCT)) {
            return 4;
        } else if (context.getPackageManager().hasSystemFeature(
                PackageManager.FEATURE_TOUCHSCREEN_MULTITOUCH)) {
            return 2;
        } else if (context.getPackageManager().hasSystemFeature(
                PackageManager.FEATURE_TOUCHSCREEN)) {
            return 1;
        } else {
            return 0;
        }
    }

}
