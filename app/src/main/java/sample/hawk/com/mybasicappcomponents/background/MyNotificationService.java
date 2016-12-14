package sample.hawk.com.mybasicappcomponents.background;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import android.widget.Toast;

import sample.hawk.com.mybasicappcomponents.MainActivity;
import sample.hawk.com.mybasicappcomponents.R;

/**
 * Created by ha271 on 2016/7/13.
 */

public class MyNotificationService extends Service {
    private static final int MY_NOTIFICATION_ID= AlarmManagerActivity.MY_REQUEST_CODE;
    private static int count;
    NotificationManager mNM;

    @Override
    public void onCreate() {
        mNM = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);

        // show the icon in the status bar
        showNotification();

        // Start up the thread running the service.  Note that we create a
        // separate thread because the service normally runs in the process's
        // main thread, which we don't want to block.
        Thread thr = new Thread(null, mTask, "AlarmService_Service");
        thr.start();
    }

    @Override
    public void onDestroy() {
        // Cancel the notification -- we use the same ID that we had used to start it
        mNM.cancel(MY_NOTIFICATION_ID);

        // Tell the user we stopped.
        Toast.makeText(this, "MyNotificationService FINISH", Toast.LENGTH_SHORT).show();
    }

    /**
     * The function that runs in our worker thread
     */
    Runnable mTask = new Runnable() {
        public void run() {
            // Notification on status bar will show ON_15sec, OFF_nextAlarmManager trigger.

            // TODO: put background work here!!
            long endTime = System.currentTimeMillis() + 15*1000;
            while (System.currentTimeMillis() < endTime) {
                synchronized (mBinder) {
                    try {
                        mBinder.wait(endTime - System.currentTimeMillis());
                    }
                    catch (Exception e) {

                    }
                }
            }

            // Done with our work...  stop the service!
            MyNotificationService.this.stopSelf();
        }
    };

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    /**
     * Show a notification while this service is running.
     */
    private void showNotification() {
        // The PendingIntent to launch our activity if the user selects this notification
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0,
                new Intent(this, MainActivity.class), 0);


        long[] tVibrate = {0,100,200,300}; // vib freq

        // Set the info for the views that show in the notification panel.
        Notification notification = new Notification.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)  // the status icon
                .setTicker("MyNotificationService: statusText")  // the status text
                .setWhen(System.currentTimeMillis())  // the time stamp
                .setContentTitle("MyNotificationService: TitleText")  // the label of the entry
                .setContentText("MyNotificationService: count="+(count++))  // the contents of the entry
                .setContentIntent(contentIntent)  // The intent to send when the entry is clicked
                .setVibrate(tVibrate) // Vibrator
                .setLights(0xff00ff00,1000,3000) // LED
                .build();

        mNM.notify(MY_NOTIFICATION_ID, notification);
    }

    /**
     * This is the object that receives interactions from clients.  See RemoteService
     * for a more complete example.
     */
    private final IBinder mBinder = new Binder() {
        @Override
        protected boolean onTransact(int code, Parcel data, Parcel reply,
                                     int flags) throws RemoteException {
            return super.onTransact(code, data, reply, flags);
        }
    };
}
