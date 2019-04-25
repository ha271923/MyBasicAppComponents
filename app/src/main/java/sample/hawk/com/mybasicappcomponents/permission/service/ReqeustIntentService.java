
package sample.hawk.com.mybasicappcomponents.permission.service;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import sample.hawk.com.mybasicappcomponents.permission.activity.permission.RequestPemissionNotification;

public class ReqeustIntentService extends IntentService {
    private final static String TAG = "ReqeustIntentService";
    
    private ReqeustIntentServiceHandler mHandler;
    
    public static final String ACTION_REQUEST_PERMISSION_NOTIFICATION = "sample.hawk.com.mybasicappcomponents.permission.service.ReqeustIntentService.REQUEST_PERMISSION_NOTIFICATION";
    
    private Drawable mAvatar = null;
    
    public ReqeustIntentService() {
        super("MailIntentService");
        setIntentRedelivery(true);
    }
    
    @Override
    public void onCreate() {
        mHandler = new ReqeustIntentServiceHandler(getApplicationContext());
        super.onCreate();
    }
    
    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent == null) return;
        String action = intent.getAction();
        if (action == null) return;
        
        if (ACTION_REQUEST_PERMISSION_NOTIFICATION.equals(action)) {
            RequestPemissionNotification notification = new RequestPemissionNotification(this);
            String[] desiredPermissions = intent.getStringArrayExtra(RequestPemissionNotification.KEY_DESIRED_PERMISSION);
            notification.showNotification(desiredPermissions);
        }
    }
    
    public static class ReqeustIntentServiceHandler extends Handler {
        
        private Context mContext;
        
        private static final int MSG_SHOW_TOAST = 101;
        
        public ReqeustIntentServiceHandler(Context context) {
            mContext = context;
        }
        
        @Override
        public void handleMessage(Message msg) {
            Log.d(TAG, "handleMessage>" + msg.what);
            switch (msg.what) {
                case MSG_SHOW_TOAST:
                    String toastText = (String) msg.obj;
                    Toast.makeText(mContext, toastText, msg.arg1).show();
                    break;
            }
        }
        
        public void showToast(String text, int duration) {
            Message message = obtainMessage(MSG_SHOW_TOAST);
            message.arg1 = duration;
            message.obj = text;
            message.sendToTarget();
        }
    }

}
