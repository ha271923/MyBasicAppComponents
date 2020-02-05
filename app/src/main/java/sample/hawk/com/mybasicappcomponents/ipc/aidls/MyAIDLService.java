package sample.hawk.com.mybasicappcomponents.ipc.aidls;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteCallbackList;
import android.os.RemoteException;
import sample.hawk.com.mybasicappcomponents.utils.SMLog;
import sample.hawk.com.mybasicappcomponents.utils.Util;

public class MyAIDLService extends Service {

    final RemoteCallbackList<IAppAIDL> mAppAPIs = new RemoteCallbackList<IAppAIDL>();

    @Override
    public void onCreate() {
        SMLog.e("service create");
    }

    @Override
    public void onStart(Intent intent, int startId) {
        SMLog.e("service start id=" + startId); // START SERVICE twice will trigger it.
        SvcCallAIDL_Apis(startId);
    }

    @Override
    public IBinder onBind(Intent t) {
        SMLog.e("service on bind");
        return mSvcAIDL_obj;
    }

    @Override
    public void onDestroy() {
        SMLog.e("service on destroy");
        mAppAPIs.kill();
        super.onDestroy();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        SMLog.e("service on unbind");
        return super.onUnbind(intent);
    }
    @Override
    public void onRebind(Intent intent) {
        SMLog.e("service on rebind");
        super.onRebind(intent);
    }

    void SvcCallAIDL_Apis(int val) {

        // Broadcast to all clients the new value.
        final int N = mAppAPIs.beginBroadcast(); // SVC2APP_2: SVC need to scan APP's AIDL for calling APP's APIs by broadcast.
        SMLog.e("MyAIDLService","mAppAPIs.beginBroadcast()  N="+N);
        for (int i=0; i<N; i++) {
            try {
                IAppAIDL mAppAIDL = mAppAPIs.getBroadcastItem(i); // SVC2APP_3: SVC got APP's AIDL.
                SMLog.e("mAppAIDL="+mAppAIDL);
                if(mAppAIDL != null) {
                    SMLog.e( Util.strPidTid()+ "AppAPI()    anInt="+val+"  aLong="+99+"  aBoolean="+true+"  aFloat="+0.99f+"  aDouble="+99.999+"  aString="+"call APP API of AIDL +++");
                    mAppAIDL.AppAPI(val, 99, true, 0.99f , 99.999, "call APP API of AIDL ..." ); // SVC2APP_1: Service process is calling API of App process.
                    SMLog.e( Util.strPidTid()+ "AppAPI()    anInt="+val+"  aLong="+99+"  aBoolean="+true+"  aFloat="+0.99f+"  aDouble="+99.999+"  aString="+"call APP API of AIDL ---");

                }
            } catch (RemoteException e) {
                // The RemoteCallbackList will take care of removing the dead object for us.
            }
        }
        mAppAPIs.finishBroadcast();
    }

    // AIDL_3、在Java檔中, 實現AIDL中定義的介面. 編譯器會根據AIDL介面, 產生一個JAVA介面。
    //         這個介面有一個名為Stub的內部抽象類別，它繼承擴展了介面並實現了遠端調用需要的幾個方法。接下來就需要自己去實現自訂的幾個介面了.
    private final ISvcAIDL.Stub mSvcAIDL_obj = new ISvcAIDL.Stub() {  // APP2SVC_0: implement SVC AIDL APIs for calling by APP

        @Override   // APP call SVC to do something via AIDL onServiceConnected.
        public void SvcAPI(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {
            SMLog.e(Util.strPidTid()+ "SvcAPI()    anInt="+anInt+"  aLong="+aLong+"  aBoolean="+aBoolean+"  aFloat="+aFloat+"  aDouble="+aDouble+"  aString="+aString+" ...");
        }

        @Override
        public void registerSvc2AppCallback(IAppAIDL cb) {  // SVC2APP_1: if APP need SVC2APP support, APP has register APP's AIDL for SVC callback it.
            SMLog.e(Util.strPidTid()+"registerSvc2AppCallback("+cb +") ...");
            if (cb != null)
                mAppAPIs.register(cb);
        }

    };

}