package sample.hawk.com.mybasicappcomponents;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import sample.hawk.com.mybasicappcomponents.utils.SMLog;


/**
 * Created by Hawk_Wei on 2016/3/16.
 */
public class MyService extends Service { // NOT UI thread
    final String TAG = "MyService";
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        SMLog.i();
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        SMLog.i();
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
}
