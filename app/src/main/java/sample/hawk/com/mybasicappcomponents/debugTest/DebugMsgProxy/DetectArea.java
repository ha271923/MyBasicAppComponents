package sample.hawk.com.mybasicappcomponents.debugTest.DebugMsgProxy;

import android.content.Context;
import android.content.SharedPreferences.Editor;
import android.os.Build.VERSION;
import android.preference.PreferenceManager;

import sample.hawk.com.mybasicappcomponents.utils.SMLog;

public class DetectArea {
    public int DetectAreaProgress;
    public int DetectAreaTransparent;
    public String strAreaHeight;
    public int AreaHeightDP;

    public static String perfNameToID(String str) {
        return str.equals("prefSwipeup") ? "1" : str.equals("prefSwipeupdown") ? "2" : str.equals("prefSwipeupleft") ? "3" : str.equals("prefSwipeupright") ? "3" : str.equals("prefSwipefarup") ? "1" : str.equals("prefSwipelowleft") ? "2" : str.equals("prefSwipelowright") ? "2" : "0";
    }

    public static boolean MyActionNotification(Context context, boolean bUseAccessibility) {
        if (bUseAccessibility && VERSION.SDK_INT >= 16) {
            return MyAccessibilityService.ActionNotification(context);
        }
        try {
            Object systemService = context.getSystemService("statusbar");
            Class cls = Class.forName("android.app.StatusBarManager");
            (VERSION.SDK_INT >= 17 ? cls.getMethod("expandNotificationsPanel", new Class[0]) : cls.getMethod("expand", new Class[0])).invoke(systemService, new Object[0]);
            return true;
        } catch (Exception e) {
            SMLog.e("pull down notification exception");
            if (!(e.getCause() instanceof SecurityException) || VERSION.SDK_INT < 16) {
                return false;
            }
            SMLog.e("change pref to use accessibility for pulling down notification");
            Editor edit = PreferenceManager.getDefaultSharedPreferences(context).edit();
            edit.putBoolean("prefUseAccessibility", true);
            edit.commit();
            return MyAccessibilityService.ActionNotification(context);
        }
    }

    public final void DetectAreaHeightProgressToDP(int progress, Context context) {
        this.DetectAreaProgress = progress;
        switch (progress) {
            case 0:
                this.AreaHeightDP = 11;
                this.strAreaHeight = "pref_areaheight_small";
                break;
            case 1:
                this.AreaHeightDP = 13;
                this.strAreaHeight = "pref_areaheight_smaller";
                break;
            case 2:
                this.AreaHeightDP = 17;
                this.strAreaHeight = "pref_areaheight_normal";
                break;
            case 3:
                this.AreaHeightDP = 20;
                this.strAreaHeight = "pref_areaheight_larger";
                break;
            case 4:
                this.AreaHeightDP = 22;
                this.strAreaHeight = "pref_areaheight_large";
                break;
            default:
        }
    }
}
