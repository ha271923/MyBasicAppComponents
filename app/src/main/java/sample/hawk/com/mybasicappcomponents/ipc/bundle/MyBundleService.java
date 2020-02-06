package sample.hawk.com.mybasicappcomponents.ipc.bundle;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;

import sample.hawk.com.mybasicappcomponents.utils.SMLog;

public class MyBundleService extends Service {

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public int onStartCommand(Intent intent, int flags, int startId) {
        // receive
        Bundle bundle2 = intent.getExtras();
        String sex = bundle2.getString("sex");
        double height = bundle2.getDouble("height");

        // processing
        String weight = tempUtils.calcWeight(sex,height);

        // output
        SMLog.e("您是一位"+sex+"\n您的身高是"+height+"cm\n您的標準體重是"+weight+"kg");


        return START_STICKY;

    }
}
