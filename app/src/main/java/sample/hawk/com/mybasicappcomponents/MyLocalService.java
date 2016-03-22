package sample.hawk.com.mybasicappcomponents;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

import java.util.Random;

import sample.hawk.com.mybasicappcomponents.utils.SMLog;


/**
 * Created by Hawk_Wei on 2016/3/16.
 */
public class MyLocalService extends Service { // NOT UI thread
    private static final String TAG = "[MyLocalService]";

    // Binder given to clients
    private final IBinder mBinder = new LocalBinder();
    // Random number generator
    private final Random mGenerator = new Random();

    public MyLocalService() {
        super();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        SMLog.i();
        return mBinder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        SMLog.i();
    }

    @Override
    public void onRebind(Intent intent) {
        super.onRebind(intent);
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        SMLog.i();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        SMLog.i();
        return super.onStartCommand(intent, flags, startId);
    }

    public class LocalBinder extends Binder {
        MyLocalService getService() {
            return MyLocalService.this;
        }
    }
    // Hawk: Call the high loading API at background SERVICE.
    public int getRandomNumber() {
        return mGenerator.nextInt(100);
    }

}
