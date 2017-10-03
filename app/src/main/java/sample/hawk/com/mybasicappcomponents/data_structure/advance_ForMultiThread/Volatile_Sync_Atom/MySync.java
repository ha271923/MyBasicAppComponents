package sample.hawk.com.mybasicappcomponents.data_structure.advance_ForMultiThread.Volatile_Sync_Atom;

/**
 *
 *
 */

public class MySync {
    static int mVar;
    static MySharedData mMySharedData;

    static public void init(){
        // WITHOUT
        mMySharedData.mVar1 = 0; // Sync
        mMySharedData.mVar2 = 0; // Volatile
        mMySharedData.mVar3 = 0; // Atom
        // WITH
        mMySharedData.mVar  = 0;
        mMySharedData.mVolatileVar = 0;
        mMySharedData.mAtomVar.set(0);

    }
    // WITHOUT synchronized keyword ----------
    static int set(int id){
        for(int i=0;i<100;i++)
            mMySharedData.mVar1++;
        return id;
    }
    static int get(){
        return mMySharedData.mVar1;
    }
    // WITH synchronized keyword -------------
    static synchronized int sync_set(int id){
        for(int i=0;i<100;i++)
            mMySharedData.mVar++;
        return id;
    }
    static synchronized int sync_get(){
        return mMySharedData.mVar;
    }

}
