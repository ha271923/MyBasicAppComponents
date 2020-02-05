package sample.hawk.com.mybasicappcomponents.ipc.message;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;
import android.widget.Toast;

import sample.hawk.com.mybasicappcomponents.utils.SMLog;

public class MyMessageService extends Service {
    private static final String TAG = "MyMessageService";
    private static final int CODE = 1;
    public MyMessageService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
//        throw new UnsupportedOperationException("Not yet implemented");
        SMLog.d(TAG, "onBind: ");
        return mMessenger.getBinder();
    }
    //创建一个送信人，封装handler
    private Messenger mMessenger = new Messenger(new serviceHandler());


    //在 SERVER端 中创建一个Handler对象，用于处理 CLIENT端 发过来的消息
    public class serviceHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            Message toClientMessage = Message.obtain();
            switch (msg.what) {
                case CODE:
                    //接收来自客户端的消息，并作处理
                    int arg = msg.arg1;
                    SMLog.d(TAG, "handleMessage: msg.arg1="+msg.arg1);
                    Toast.makeText(getApplicationContext(),"收到 ACTIVITY 的消息："+arg+"" , Toast.LENGTH_SHORT).show();
                    //在服务端的Handler处理Message时将客户端的Messenger解析出来，并使用客户端的Messenger对象给客户端发送消息
                    toClientMessage.arg1 = 1111111111;
                    try {
                        //回复客户端消息
                        msg.replyTo.send(toClientMessage);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
            }
            super.handleMessage(msg);
        }
    }
}
