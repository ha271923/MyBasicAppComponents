package sample.hawk.com.mybasicappcomponents.permission.activity.permission;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.service.notification.StatusBarNotification;
import android.util.Log;

import sample.hawk.com.mybasicappcomponents.R;

import java.util.ArrayList;
import java.util.Arrays;

import sample.hawk.com.mybasicappcomponents.permission.notification.BaseNotification;
import sample.hawk.com.mybasicappcomponents.permission.service.ReqeustIntentService;


public class RequestPemissionNotification extends BaseNotification {
    
    private final static String TAG = "RequestPemissionNotification";
    
    public static String KEY_DESIRED_PERMISSION = "desiredPermission";
    
    public static int mNotificationId = -10000;
    
    public RequestPemissionNotification(Context context) {
        mNotifyContext = context;
        mType = NotificationType.REQUEST_PERMISSION_TYPE;
        mIconResource = R.drawable.stat_notify_sync_error;
        mTitle = mNotifyContext.getResources().getString(R.string.request_permission_title);
        mDesc =  mNotifyContext.getResources().getString(R.string.request_permission_content);
    }
    
    public static int showNotificationInLine(final Context context, final String[] desiredPermission) {
        Log.i(TAG, "showNotificationInLine: context>" + context);
        Intent intent = new Intent(ReqeustIntentService.ACTION_REQUEST_PERMISSION_NOTIFICATION);
        intent.setClassName("sample.hawk.com.mybasicappcomponents", "sample.hawk.com.mybasicappcomponents.permission.service.ReqeustIntentService");
        intent.putExtra(RequestPemissionNotification.KEY_DESIRED_PERMISSION, desiredPermission);
        context.startService(intent);
        return 0;
    }
    
    public int showNotification(String[] desiredPermission) {
    	
        // Combine desired permissions of previosly notificaiton 
        NotificationManager notificationManager = (NotificationManager) mNotifyContext.getSystemService(Context.NOTIFICATION_SERVICE);
        StatusBarNotification[] statusBarNotifications =  notificationManager.getActiveNotifications();
        for (int i = 0 ; i < statusBarNotifications.length; i++) {
            StatusBarNotification statusBarNotification = statusBarNotifications[i];
            if (statusBarNotification == null) continue;
            
            if ("sample.hawk.com.mybasicappcomponents".equals(statusBarNotification.getPackageName()) && statusBarNotification.getId() ==  mNotificationId) {
                 Notification pNotification = statusBarNotification.getNotification();
                 if (pNotification != null) {
                     Bundle pBundle = pNotification.extras;
                     if (pBundle != null) {
                         String[] pDesiredPermission = pBundle.getStringArray(KEY_DESIRED_PERMISSION);
                         ArrayList<String> newDesiredPermission = new ArrayList<String>(Arrays.asList(desiredPermission));
                         for (int j = 0 ; j < pDesiredPermission.length ; j++) {
                             String tmp = pDesiredPermission[j];
                             if(!newDesiredPermission.contains(tmp)) {
                                 newDesiredPermission.add(tmp);
                             }
                         }
                         desiredPermission = newDesiredPermission.toArray(new String[newDesiredPermission.size()]);
                     }
                 }
            }
        }
        
        // Prepare intent which is triggered if the notification is selected
        Intent intent = getRequestBkgPermissionsActivity(mNotifyContext, desiredPermission);
        Notification notification = createNotification(intent, desiredPermission);
        
        notificationManager.notify(mNotificationId, notification);
        
        return 0;
    }
    

    public Notification createNotification(Intent intent, String[] desiredPermission) {
        PendingIntent pIntent = PendingIntent.getActivity(mNotifyContext, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        // Build notification
        Notification notification = getBuilder(mNotifyContext)
            .setSmallIcon(mIconResource)
            .setContentTitle(mTitle)
            .setContentText(mDesc)
            .setContentIntent(pIntent)
            .setAutoCancel(true)
            .build();
        
        Bundle b = new Bundle();
        b.putStringArray(KEY_DESIRED_PERMISSION, desiredPermission);
        notification.extras = b;
        
        return notification;
    }
    
    private static Intent getRequestBkgPermissionsActivity(Context context, String[] desiredPermissions) {
        Intent it = new Intent(context, RequestBkgPermissionsActivity.class);
        it.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        it.putExtra(RequestPemissionNotification.KEY_DESIRED_PERMISSION, desiredPermissions);
        return it;
    }
    
    public static int clearNotification(Context context) {
        Log.d(TAG, "clearNotification>");
        
        if (context == null) {
            Log.d(TAG, "clearNotification> context is null");
            return -1;
        }
        
        // Clean Notification
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.cancel(mNotificationId);
        
        return 0;
    }
}
