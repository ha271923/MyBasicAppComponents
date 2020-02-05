package sample.hawk.com.mybasicappcomponents.ipc.aidls;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.View;

import sample.hawk.com.mybasicappcomponents.R;
import sample.hawk.com.mybasicappcomponents.utils.SMLog;
import sample.hawk.com.mybasicappcomponents.utils.Util;

/**
    在Android中, 每個應用程式都可以有自己的進程. 在寫UI應用的時候, 經常要用到Service.
    在不同的進程中, 怎樣傳遞物件呢?  顯然, Java中不允許跨進程記憶體共用. 因此傳遞物件, 只能把物件拆分成作業系統能理解的簡單形式,以達到跨界物件訪問的目的.
    在J2EE中,採用RMI的方式, 可以通過序列化傳遞物件. 在Android中, 則採用AIDL的方式.

    AIDL(Android Interface Definition Language)是一種接口描述語言; 編譯器可以通過aidl檔生成一段代碼，通過預先定義的介面達到兩個進程內部通信進程的目的.
    如果需要在一個Activity中, 訪問另一個Service中的某個物件, 需要先將物件轉化成AIDL可識別的參數(可能是多個參數), 然後使用AIDL來傳遞這些參數, 在消息的接收端, 使用這些參數組裝成自己需要的物件.

    AIDL的跨進程(IPC)的遠距溝通機制和COM或CORBA類似, 是基於介面的，但它是輕量級的。 它使用代理類在用戶端和實現層間傳遞值.

    如果要使用AIDL, 需要完成2件事情:
    1. 引入AIDL的相關類.
    2. 調用aidl產生的class.
*/
public class MyAIDLActivity extends Activity {

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.myaidl_activity);
    }

    public void onSvcAPIClick(View v) {
        if(mMyAIDLService == null)
            return;

        try {
            SMLog.i("MyAIDLActivity", Util.strPidTid()+ "SvcAPI()    anInt="+9+"  aLong="+99+"  aBoolean="+true+"  aFloat="+0.99f+"  aDouble="+99.999+"  aString="+"call SVC API of AIDL +++");
            mMyAIDLService.SvcAPI(9, 99, true, 0.99f, 99.999, "call SVC API of AIDL");  // APP2SVC_3a: call SVC API via AIDL directly.
            SMLog.i("MyAIDLActivity", Util.strPidTid()+ "SvcAPI()    anInt="+9+"  aLong="+99+"  aBoolean="+true+"  aFloat="+0.99f+"  aDouble="+99.999+"  aString="+"call SVC API of AIDL ---");
        } catch (RemoteException e) {

        }
    }

    public void onregisterSvc2AppCallbackClick(View v) {
        if(mMyAIDLService == null)
            return;

        try {
            SMLog.i("MyAIDLActivity", Util.strPidTid()+ "registerSvc2AppCallback("+ mAppAIDL_obj +") +++");
            mMyAIDLService.registerSvc2AppCallback(mAppAIDL_obj);  // APP2SVC_3a: call SVC API via AIDL directly. if APP need SVC2APP support, APP has register APP's AIDL for SVC callback it.
            SMLog.i("MyAIDLActivity", Util.strPidTid()+ "registerSvc2AppCallback("+ mAppAIDL_obj +") ---");
        } catch (RemoteException e) {

        }
    }
    public void onStartServiceClick(View v) {
        Bundle args = new Bundle();

        Intent intent = new Intent(this, MyAIDLService.class);
        intent.putExtras(args);

        // intent.setAction("sample.hawk.com.mybasicappcomponents.ipc.aidls.MyAIDLService");
        // intent.setPackage("sample.hawk.com.mybasicappcomponents");

        //startService(intent);
        boolean bServiceBind= bindService(intent, mConnection, Context.BIND_AUTO_CREATE); // APP2SVC_1: bindService
        ComponentName componentName = startService(intent);
        SMLog.i("MyAIDLActivity","bindService   bServiceBind="+bServiceBind+"    componentName="+componentName);
    }

    public void onStopServiceClick(View v) {
        // send intent to stop
        Intent intent = new Intent(this, MyAIDLService.class);

        unbindService(mConnection);
        //stopService(intent);
    }

    ISvcAIDL mMyAIDLService;
    private ServiceConnection mConnection = new ServiceConnection() {
        public void onServiceConnected(ComponentName className, IBinder service) {
            mMyAIDLService = ISvcAIDL.Stub.asInterface(service); // APP2SVC_2: get Service with AIDL

        }

        public void onServiceDisconnected(ComponentName className) {
            mMyAIDLService = null;
        }
    };

    // AIDL_3、在Java檔中, 實現AIDL中定義的介面. 編譯器會根據AIDL介面, 產生一個JAVA介面。
    //      這個介面有一個名為Stub的內部抽象類別，它繼承擴展了介面並實現了遠端調用需要的幾個方法。接下來就需要自己去實現自訂的幾個介面了.
    private IAppAIDL mAppAIDL_obj = new IAppAIDL.Stub() {  // SVC2APP_0: APP's AIDL implementation

        @Override  // SVC call APP to do something via AIDL
        public void AppAPI(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {
            SMLog.i("MyAIDLActivity",Util.strPidTid()+ "AppAPI()    anInt="+anInt+"  aLong="+aLong+"  aBoolean="+aBoolean+"  aFloat="+aFloat+"  aDouble="+aDouble+"  aString="+aString+" ...");
        }
    };
}