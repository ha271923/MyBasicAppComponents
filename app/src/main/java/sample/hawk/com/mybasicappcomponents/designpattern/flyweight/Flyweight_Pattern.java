package sample.hawk.com.mybasicappcomponents.designpattern.flyweight;

import sample.hawk.com.mybasicappcomponents.IDemo;
import sample.hawk.com.mybasicappcomponents.utils.SMLog;

/**
 *
 * 當物件的結構具有共通性時!!
 *
 * 享元模式 (Flyweight Pattern)
 *    物件之間，若有共同的部分可以共享，則將可共用的部分獨立為共享物件，以節省記憶體
 *    不能共享的部份外部化，使用時再將外部化的部分傳給共享物件。
 *
 *    優點: 減少記憶體使用量。
 *    缺點: 為了使對象可以共享，需要將一些狀態外部化程式邏輯，這使得程序的邏輯複雜化。
 *
 *
 *  以下比較ShareMem, Child<-Parent class, Flyweight的差異:
 *   A. ShareMem只是儲存空間的共享, 並無物件導向的概念, 與 new Object無關
 *      ClassA  \
 *      ClassB  -- ClassShared
 *      ClassC  /
 *
 *   B. 標準的Child<-Parent class, 再每一次new Object後都會產生記憶體消耗
 *       new Child<-Parent class 消耗記憶體 *1 = Object1
 *       new Child<-Parent class 消耗記憶體 *2 = Object2
 *       new Child<-Parent class 消耗記憶體 *3 = Object3
 *
 *   C. Flyweight的class, 再每一次new Object後, 都會產生記憶體消耗
 *       檢查有沒有new過 --> 沒有 --> new Child<-Parent class 消耗記憶體 *1 = Object1 --> put(key1,Object1)
 *       檢查有沒有new過 --> 沒有 --> new Child<-Parent class 消耗記憶體 *2 = Object2 --> put(key2,Object2)
 *       檢查有沒有new過 -->   有 --> 不用new, 直接 Object = get(key)       = 取得現有的Object1  省下記憶體消耗
 *
 */

public class Flyweight_Pattern implements IDemo {

    @Override
    public void demo() {
        ChessFlyweightFactory f = new ChessFlyweightFactory();

        ChessFlyweight a1 = f.GetChessFlyweight("黑棋"); // 取得黑棋共享物件
        SMLog.i("");
        SMLog.i("ChessFlyweight物件數量：");
        a1.Display(1, 1); // 提供座標資料(非共享資料)
        ChessFlyweight a2 = f.GetChessFlyweight("黑棋"); // 取得黑棋共享物件
        a2.Display(1, 2); // 提供座標資料(非共享資料)
        ChessFlyweight a3 = f.GetChessFlyweight("黑棋"); // 取得黑棋共享物件
        a3.Display(1, 3); // 提供座標資料(非共享資料)
        ChessFlyweight b1 = f.GetChessFlyweight("白棋"); // 取得白棋共享物件
        b1.Display(2, 1); // 提供座標資料(非共享資料)
        ChessFlyweight b2 = f.GetChessFlyweight("白棋"); // 取得白棋共享物件
        b1.Display(2, 2); // 提供座標資料(非共享資料)
        ChessFlyweight b3 = f.GetChessFlyweight("白棋"); // 取得白棋共享物件
        b1.Display(2, 3); // 提供座標資料(非共享資料)
        SMLog.i("ChessFlyweight物件數量：" + f.GetChessFlyweightCount());
    }

}