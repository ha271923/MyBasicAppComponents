package sample.hawk.com.mybasicappcomponents.designpattern.prototype;

import sample.hawk.com.mybasicappcomponents.IDemo;
import sample.hawk.com.mybasicappcomponents.utils.SMLog;

/**
 * Prototype模式:
 *   讓我們不用每次都從無到有重新建立一個物件，而是透過複製一個已經存在的物件來取得一個新物件。
 *
 * 實作上:
 *   Prototype模式使用HashTable/HashMap來存放既有物件，既有物件實作Cloneable介面及clone()方法來複製自身，
 *   在需要新物件時即複製集合中所存放的既有物件來取得新物件。
 *
 * 而會使用Prototype模式來建立新物件的原因如下:
 *   1. 直接建立物件的成本太高，或流程繁複。例如建立物件前必須先從資料庫取得一些資料來設定物件狀態，則每次建立新物件都要存取資料庫，佔用與資料庫連線的資源。而透過複製現有物件來建立物件的成本比較低，效率較高。例如在第一次建立物件時是透過存取資料庫的資料來建立，而在之後若需要新物件時則透過複製該既存物件來建立物件，如此便不用每次都要存取資料庫來建立新的物件。
 *   2. 將物件的建立，設定與系統流程分離來降低耦合度
 *   3. 將建立物件的實作方法從客戶端隱藏起來，使用該物件的客戶端無需知道該物件如何被實作。
 *   4. 所需要的物件特性與既有的物件相同
 *
 * 程式架構:
 *  class Cars <-- 關鍵!! 裡面用 new HashMap<CarData, Car>()來記錄所有的Prototype
 *    put時, 利用  put(key,obj) , 存放物件至HashMap中
 *    get時, 利用 get(key).clone() , 從HashMap中取出物件, 並複製一份後, 才返回
 *
 *  class Car implements Cloneable <-- 實作了要如何clone(), 來產生相同物件
 *    class Wheel implements Cloneable <-- 實作了要如何clone(), 來產生相同物件
 *    class CarData <-- 不想要被clone的物件,不需要implements Cloneable介面
 *
 */

public class Prototype_Pattern implements IDemo {
    @Override
    public void demo() {
        Cars cars = new Cars(); // 此物件儲存了全部的Car資料

        Car bmw = new Car();
        CarData bmwData = bmw.getCarData();
        // 作一些 BMW 複雜的初始、設定、有的沒的, 這部分不要被clone
        bmwData.mBrand = "BMW";
        bmwData.mColor = "寶藍色";
        cars.addPrototype(bmwData, bmw);

        Car benz = new Car();
        CarData benzData = benz.getCarData();
        // 作一些 Benz 複雜的初始、設定、有的沒的, 這部分不要被clone
        benzData.mBrand = "Benz";
        benzData.mColor = "銀色";
        cars.addPrototype(benzData, benz);

        // 取得 BMW 原型複製
        try {
            Car car1 = cars.getPrototype(bmwData); // bmwData==Key to get a clone object back.
            SMLog.i(car1 +"  Brand=" + car1.getCarData().mBrand + " Color="+car1.getCarData().mColor);

            Car car2 = cars.getPrototype(benzData);
            SMLog.i(car2 +"  Brand=" + car2.getCarData().mBrand + " Color="+car2.getCarData().mColor);

            Car car3 = cars.getPrototype(bmwData);
            SMLog.i(car3 +"  Brand=" + car3.getCarData().mBrand + " Color="+car3.getCarData().mColor);


        } catch ( CloneNotSupportedException e ){
            e.printStackTrace();
        }
    }
}
