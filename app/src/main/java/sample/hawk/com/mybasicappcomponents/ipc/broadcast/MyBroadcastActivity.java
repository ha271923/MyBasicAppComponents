package sample.hawk.com.mybasicappcomponents.ipc.broadcast;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;

import sample.hawk.com.mybasicappcomponents.R;

public class MyBroadcastActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mybroadcast_activity);


        // 註冊一個新的廣播訊息
        // Way_A. Dynamic Register in Code
        /*
        IntentFilter ifilter = new IntentFilter();
        ifilter.addAction("i_am_hungry");
        MyBroadcastReceiver myMyBroadcastReceiver = new MyBroadcastReceiver();
        registerReceiver(myMyBroadcastReceiver, ifilter);
        // 但是記得要有註銷的動作
        unregisterReceiver(myMyBroadcastReceiver);
        */

        // Way_B. static Register in AndroidManifest.xml

    }

    public void onSendBroadcastClick(View v) {
        Intent intent = new Intent();
        intent.setAction("i_am_hungry");
        intent.putExtra("HELLO",  "**********************************************************\n"+
                                              "@@@@@@@@@@@@@@@@@@@  HAWK LOVE EVERYONE @@@@@@@@@@@@@@@@@@@\n"+
                                              "***********************************************************");
        sendBroadcast(intent);
    }
}