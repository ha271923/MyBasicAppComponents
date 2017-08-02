package sample.hawk.com.mybasicappcomponents.designpattern.singleton;

/**
 * Singleton class 的實作適用於單執行緒的程式，在多執行緒的程式下，以上的寫法在多個執行緒的競爭資源下，將仍有可能產生兩個以上的實例，
 * 例如下面的情況：
 *      Thread1: if(instance == null) // true
 *      Thread2: if(instance == null) // true
 *
 *      Thread1: instance = new Singleton(); // 產生一個實例
 *      Thread2: instance = new Singleton(); // 又產生一個實例
 *
 *      Thread1: return instance; // 回傳一個實例
 *      Thread2: return instance; // 又回傳一個實例
 *
 *  ANS: 在多執行緒的環境下，為了避免資源同時競爭而導致如上產生多個實例的情況，所以加上同步（synchronized）機制：
 */

public class Singleton_sync1 extends NoSingleton{

    private static Singleton_sync1 mInstance; //透用一個靜態變數記錄實體

    private Singleton_sync1() { // private keyword is MUST.
    }

    synchronized public static Singleton_sync1 getInstance() { // 同步機制會造成相當的效能低落
        if (mInstance == null)
            mInstance = new Singleton_sync1();
        return mInstance;
    }




}