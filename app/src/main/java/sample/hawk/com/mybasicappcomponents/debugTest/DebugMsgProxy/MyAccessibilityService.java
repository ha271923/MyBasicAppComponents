package sample.hawk.com.mybasicappcomponents.debugTest.DebugMsgProxy;

import android.accessibilityservice.AccessibilityService;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.WindowManager;
import android.view.accessibility.AccessibilityEvent;

import sample.hawk.com.mybasicappcomponents.R;

public class MyAccessibilityService extends AccessibilityService {
    private static MyAccessibilityService mMyAccessibilityService;

    static {
        ComponentName componentName = new ComponentName("sample.hawk.com.mybasicappcomponents", MyAccessibilityService.class.getName());
    }

    public static boolean ActionNotification(Context context) {
        return getMyAccessibilityService(context) != null ? mMyAccessibilityService.performGlobalAction(GLOBAL_ACTION_NOTIFICATIONS) : false;
    }

    public static boolean ActionBackKey(Context context) {
        return getMyAccessibilityService(context) != null ? mMyAccessibilityService.performGlobalAction(GLOBAL_ACTION_BACK) : false;
    }

    private static MyAccessibilityService getMyAccessibilityService(Context context) {
        if (mMyAccessibilityService != null) {
            return mMyAccessibilityService;
        }
        Intent intent = new Intent("android.settings.ACCESSIBILITY_SETTINGS");
        intent.addFlags(Intent.FLAG_RECEIVER_FOREGROUND| Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);
        Builder builder = new Builder(context);
        builder.setTitle("MyAccessibilityService is asking!")
               .setIcon(R.drawable.android_robot)
               .setCancelable(false)
               .setMessage("Please switch on APP on this page in order to perform this swipe action")
               .setPositiveButton("I understand",
               new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        dialogInterface.dismiss();
                    }
                });
        AlertDialog create = builder.create();
        create.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
        create.show();
        return null;
    }

    public void onAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
    }

    public void onCreate() {
        super.onCreate();
        mMyAccessibilityService = this;
    }

    public void onDestroy() {
        super.onDestroy();
        mMyAccessibilityService = null;
    }

    public void onInterrupt() {
    }
}
