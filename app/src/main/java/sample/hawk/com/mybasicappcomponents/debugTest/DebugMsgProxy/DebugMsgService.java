package sample.hawk.com.mybasicappcomponents.debugTest.DebugMsgProxy;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Build;
import android.os.IBinder;
import android.os.Vibrator;
import android.preference.PreferenceManager;
import android.util.TypedValue;
import android.view.Display;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import java.util.Date;

import sample.hawk.com.mybasicappcomponents.R;

import static android.app.Notification.PRIORITY_MIN;
import static android.app.PendingIntent.FLAG_CANCEL_CURRENT;

/**
 * Created by ha271 on 2017/3/22.
 */

public class DebugMsgService extends Service implements GestureDetector.OnGestureListener {
    public SharedPreferences AppPref;
    public GestureDetector GestureDectorObj;
    public Boolean bTapNotice;
    private Button GestureDectorLineButton;
    private TextView GestureDectorTextView;
    private WindowManager WindowManagerService;
    private Boolean bForceNotification;
    private int AreaHeightDP;
    private int pointY;
    private Boolean bShowNotificationIcon;
    private Date date;
    private Vibrator VibratorService;
    private Boolean bSwipeVibrate;
    private Boolean bTapVibrate;
    private boolean bUseAccessibility;
    private boolean bExtendAreaWidth;
    private int color;
    private int bb=0;
    private int gg=0;
    private int rr=0xFF;
    private DetectArea detectAreaObj;
    private DebugMsgReceiver mDbg_receiver;

    public DebugMsgService() {
        this.bShowNotificationIcon = true;
        this.date = new Date();
    }

    private int DipToPixel(int i) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, (float) i, getResources().getDisplayMetrics());
    }

    private void StartGestureActions(String str) {
        if (this.bSwipeVibrate) {
            this.VibratorService.vibrate(10);
        }
        if (str.equals("ActionForLongPress")) { // Long Press
            // TODO: add your custom action here!
        }
    }

    public IBinder onBind(Intent intent) {
        return null;
    }

    public void onCreate() {
        // store at: /data/data/sample.hawk.com.mybasicappcomponents/shared_prefs/sample.hawk.com.mybasicappcomponents_preferences.xml
        this.AppPref = PreferenceManager.getDefaultSharedPreferences(this);
        this.bForceNotification = this.AppPref.getBoolean("prefForceNotification", false);
        this.bTapNotice = this.AppPref.getBoolean("prefTapNotice", true);
        this.bSwipeVibrate = this.AppPref.getBoolean("prefVibrate", true);
        this.bTapVibrate = this.AppPref.getBoolean("prefTapVibrate", true);
        this.bUseAccessibility = this.AppPref.getBoolean("prefUseAccessibility", false);
        Boolean bTransparentNotifyIcon = this.AppPref.getBoolean("prefTransparentIcon", true);
        int DetectAreaHeightProgress = this.AppPref.getInt("prefDetectAreaHeight", 2); // default is 2=> (this.AreaHeightDP = 17; strAreaHeight="Normal";)
        int DetectAreaTransparent = this.AppPref.getInt("prefDetectAreaTransparent", 1); // default is 1%
        this.bExtendAreaWidth = this.AppPref.getBoolean("prefExtendWidth", false);
        detectAreaObj = new DetectArea();
        detectAreaObj.DetectAreaHeightProgressToDP(DetectAreaHeightProgress, (Context) this);
        detectAreaObj.DetectAreaTransparent = DetectAreaTransparent;
        this.AreaHeightDP = detectAreaObj.AreaHeightDP;
        this.VibratorService = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        ShowAppNotification(bTransparentNotifyIcon); // Hawk: collect them to my AppNotification API

        Display defaultDisplay = ((WindowManager) getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        Point point = new Point();
        defaultDisplay.getSize(point);
        this.pointY = point.y;
        this.GestureDectorLineButton = new Button(this);
        this.GestureDectorTextView =  new TextView(this);
        ApplicationInfo applicationInfo = getApplicationInfo();
        int i2 = applicationInfo.flags & ApplicationInfo.FLAG_DEBUGGABLE;
        applicationInfo.flags = i2;
        if (!(i2 != 0)) {
            this.GestureDectorLineButton.setVisibility(View.VISIBLE);
            this.GestureDectorLineButton.setBackgroundColor(Color.argb(getAlpha(detectAreaObj.DetectAreaTransparent),rr,gg,bb)); // Hawk: transparent the GestureDectorLineButton
            this.GestureDectorTextView.setVisibility(View.VISIBLE);
            this.GestureDectorTextView.setBackgroundColor(Color.argb(getAlpha(detectAreaObj.DetectAreaTransparent),rr,gg,bb)); // Hawk: transparent the GestureDectorLineButton
        }

        IntentFilter intentFilterForReceiver = new IntentFilter("sample.hawk.com.mybasicappcomponents.debugmsg");
        DebugMsgService_Listener dbg_listener = new DebugMsgService_Listener();
        mDbg_receiver = new DebugMsgReceiver(dbg_listener);
        registerReceiver(mDbg_receiver, intentFilterForReceiver);
    }

    private int getAlpha(int MAX100){
        if(MAX100==0)
        {
            return 0;
        }
        else if(MAX100==100)
        {
            return 0xff;
        }
        else
        {
            return (int)(MAX100*2.55);
        }
    }
    // https://developer.android.com/guide/topics/ui/accessibility/services.html

    void ShowAppNotification(boolean bEnableTransparent){
        // Hawk: two line Test code, return it directly.
        //if (bEnableTransparent) // Hawk: 僅可以在Android 4.1+的系統中通過反勾選應用程序管理器中滑動解鎖的“顯示通知”來去掉下拉通知欄内的圖標。否則則需要下拉通知欄圖標保持程序運行。
        //   return;
        PendingIntent activity = PendingIntent.getActivity(this, -1, new Intent(this, DebugMsgActivity.class), FLAG_CANCEL_CURRENT);
        Notification.Builder builder = new Notification.Builder(this);
        builder.setContentIntent(activity).setWhen(0).setAutoCancel(false).setContentTitle("DebugMsg Service").setContentText("DebugMsg Service is running.");
        if (bEnableTransparent) {
            builder.setSmallIcon(R.drawable.android_robot).setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.android_robot));
        } else {
            builder.setSmallIcon(R.drawable.android_robot);
        }
        Notification notification = builder.getNotification();
        if (bEnableTransparent && Build.VERSION.SDK_INT >= 16) {
            notification.priority = PRIORITY_MIN;
        }
        notification.when = 2147483647L;
        startForeground(317, notification);

    }


    public void onDestroy() {
        this.WindowManagerService.removeView(this.GestureDectorLineButton);
        this.WindowManagerService.removeView(this.GestureDectorTextView);
    }

    public boolean onDown(MotionEvent motionEvent) { // detected ANY touch event.
        return false;
    }

    public void onLongPress(MotionEvent motionEvent) {
        StartGestureActions("ActionForLongPress");
    }

    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
        return false;
    }

    public void onShowPress(MotionEvent motionEvent) {
    }

    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        return false;
    }

    public boolean onSingleTapUp(MotionEvent motionEvent) {
        return false;
    }

    public int onStartCommand(Intent intent, int i, int i2) {
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams(DipToPixel(!this.bExtendAreaWidth ? 140 : 10000), DipToPixel(this.AreaHeightDP), WindowManager.LayoutParams.TYPE_SYSTEM_ALERT, 262440, -3);
        //layoutParams.gravity = 81; // origin:
        layoutParams.gravity = Gravity.CENTER|Gravity.BOTTOM; // Hawk: decoded, 80+17!=81
        this.GestureDectorObj = new GestureDetector(this.GestureDectorLineButton.getContext(), this);
        this.GestureDectorLineButton.setOnTouchListener(new DebugMsgServiceLine_Listener(this)); // IMPORTANT: connect Service+Gesture with Button
        this.GestureDectorLineButton.setBackgroundColor(Color.argb(getAlpha(detectAreaObj.DetectAreaTransparent),rr,gg,bb)); // Hawk: transparent the GestureDectorLineButton

        // this.GestureDectorLineButton.setBackgroundResource(R.drawable.android_robot); // test OK!

        this.GestureDectorTextView.setText("------------");
        this.GestureDectorTextView.setTextSize(10);
        this.GestureDectorTextView.setTextColor(0xFF111111);


        this.WindowManagerService = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        this.WindowManagerService.addView(this.GestureDectorLineButton, layoutParams); // Hawk: the user MUST enable the overlap permission at Settings\Apps\<Setting Icon>\Configure apps\Special access\Draw over other apps
        this.WindowManagerService.addView(this.GestureDectorTextView, layoutParams); // Hawk: the user MUST enable the overlap permission at Settings\Apps\<Setting Icon>\Configure apps\Special access\Draw over other apps
        return 1;
    }

    public class DebugMsgServiceLine_Listener implements View.OnTouchListener {
        private DebugMsgService debugMsgService;

        public DebugMsgServiceLine_Listener(DebugMsgService debugMsgService) {
            this.debugMsgService = debugMsgService;
        }

        public final boolean onTouch(View view, MotionEvent motionEvent) {
            this.debugMsgService.GestureDectorObj.onTouchEvent(motionEvent);
            return true;
        }
    }


    public class DebugMsgService_Listener {
        public void onDebugMsgUpdate(String className){
            String outString = className.substring(className.lastIndexOf(".") + 1);
            GestureDectorTextView.setText(outString);
        }
    }

}
