package sample.hawk.com.mybasicappcomponents.data_structure.advance_ForMultiThread.Volatile_Sync_Atom;

/**
 * volatile會阻止Compiler進行某種"Code Optimization" 以保證 variable 在Thread Memory 和 CPU register之間一致。
 *
 * 假設我們創立了兩個Thread去存取物件，且進行了以下的event sequence：
 * 1. Thread 1 呼叫 setID(5);
 * 2. Thread 2 呼叫 setID(10);
 * 3. Thread 1 呼叫 getID();  ERROR: "我們預期是10, 結果程式回傳數值5"
 * 在步驟3呼叫getID時，我們預期得到的是最新的ID數值，也就是被Thread2寫入的10。
 * Q: 但由於Thread1是從自己的working copy中讀取數值，所以我們得到5。
 * A: 如果我們將mID宣告為volatile，就能保證變數在main memory與working copy的數值一致。且Thread1和Thread2可以同時執行。
 *    https://read01.com/648P2.html
 */

public class MyVolatile {
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
    // WITHOUT volatile keyword ----------
    static int set(int id){
        for(int i=0;i<100;i++)
            mMySharedData.mVar2++;
        return id;
    }
    static int get(){
        return mMySharedData.mVar2;
    }
    // WITH volatile keyword -------------
    static int volatile_set(int id){
        for(int i=0;i<100;i++)
            mMySharedData.mVolatileVar++;
        return id;
    }
    static int volatile_get(){
        return mMySharedData.mVolatileVar;
    }

    static void setID(int id){
        mVar = id;
    }
    static int getID(){
        return mVar;
    }

    static void volatile_setID(int id){
        mMySharedData.mVolatileVar = id;
    }
    static int volatile_getID(){
        return mMySharedData.mVolatileVar;
    }


}
