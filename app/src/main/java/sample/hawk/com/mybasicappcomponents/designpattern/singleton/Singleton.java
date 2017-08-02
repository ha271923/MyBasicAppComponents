package sample.hawk.com.mybasicappcomponents.designpattern.singleton;

import sample.hawk.com.mybasicappcomponents.utils.SMLog;

/**
 * Q: 有哪些物件須不需要大量生產，特別它是在操作的時候，只”需要”一個，而且”只能”有一個？
 *
 * A1: 透過全域變數還是有一些缺點：物件一開始就必須建立好，但是假如程式過程中並沒有使用到這個物件的話，
 *      就形成了一種資源的浪費！這類的物件被大量地宣告其實是非常耗費資源的。
 *
 * A2: 因此！！透過獨體模式，可以讓物件在被需要的時候才建立物件！
 */

public class Singleton extends NoSingleton{

    private static Singleton mInstance; //透用一個靜態變數記錄實體

    private Singleton() { // No way to create instance, the private keyword is MUST.
    }

    public static Singleton getInstance() {
        if (mInstance == null){
            mInstance = new Singleton();
            SMLog.i("Only create the instance at first time!!!");
        }
        return mInstance;
    }

}