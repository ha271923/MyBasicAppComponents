package sample.hawk.com.mybasicappcomponents.ipc.message;


import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import sample.hawk.com.mybasicappcomponents.R;
import sample.hawk.com.mybasicappcomponents.utils.SMLog;

/**
 * 與 AIDL 比較：
 * 　　當您需要執行 IPC 時，爲您的接口使用 Messenger 要比使用 AIDL 實現更加簡單，因爲 Messenger 會將所有服務調用排入隊列，
 *     而純粹的 AIDL 接口會同時向服務發送多個請求，服務隨後必須應對多線程處理。
 * 　　對於大多數應用，服務不需要執行多線程處理，因此使用 Messenger 可讓服務一次處理一個調用。
 *    如果您的服務必須執行多線程處理，則應使用 AIDL 來定義接口。
 */
public class MyMessageActivity extends AppCompatActivity {
    private static final String TAG = "MyMessageActivity";
    private boolean mBond;
    private Messenger serverMessenger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mymessage_activity);
        //绑定服务
        Intent intent = new Intent();
        intent.setComponent(new ComponentName("sample.hawk.com.mybasicappcomponents", "sample.hawk.com.mybasicappcomponents.ipc.message.MyMessageService"));
        bindService(intent, mConnection, BIND_AUTO_CREATE);
    }

    public void onSendMessageClick(View v) {
        Message toServiceMessage = Message.obtain();
        toServiceMessage.what = 1;
        toServiceMessage.arg1 = 12345;
        try {
            //将客户端的Messenger对象赋给待发送的Message对象的replyTo字段
            toServiceMessage.replyTo = mMessenger;
            serverMessenger.send(toServiceMessage);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }


    @Override
    protected void onDestroy() {
        if (mBond) {
            unbindService(mConnection);
        }
        super.onDestroy();
    }

    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            //连接成功
            serverMessenger = new Messenger(service);
            SMLog.i("MyMessageActivity", "服务连接成功");
            mBond = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            serverMessenger = null;
            mBond = false;
        }
    };


    //在 CLIENT端 中创建一个Handler对象，用于处理 SERVER端 发过来的消息
    private Messenger mMessenger = new Messenger(new Handler(){
        @Override
        public void handleMessage(Message msg) {
            Toast.makeText(getApplicationContext(),"收到 SERVICE 的消息："+msg.arg1,Toast.LENGTH_SHORT).show();
            super.handleMessage(msg);
        }
    });
}
