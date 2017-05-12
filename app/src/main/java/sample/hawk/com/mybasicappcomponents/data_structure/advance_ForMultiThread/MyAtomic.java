package sample.hawk.com.mybasicappcomponents.data_structure.advance_ForMultiThread;

/**
 * Created by ha271 on 2017/5/11.
 */

public class MyAtomic {
    int mVar;
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
    // WITHOUT AtomicInteger ----------
    static int set(int id){
        for(int i=0;i<100;i++)
            mMySharedData.mVar3++;
        return id;
    }
    static int get(){
        return mMySharedData.mVar3;
    }
    // WITH AtomicInteger -------------
    static int atom_set(int id){
        for(int i=0;i<100;i++)
            mMySharedData.mAtomVar.getAndIncrement();
        return id;
    }
    static int atom_get(){
        return mMySharedData.mAtomVar.get();
    }

    synchronized static int increase(){
        return mMySharedData.mVar++;
    }

    static int increase_Atom(){
        return mMySharedData.mAtomVar.incrementAndGet();
    }
}
