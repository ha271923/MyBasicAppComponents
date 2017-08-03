package sample.hawk.com.mybasicappcomponents.designpattern.builder;

import sample.hawk.com.mybasicappcomponents.utils.SMLog;

/**
 * 定義
 *  將一個複雜對象的構建與他的表示分離，使得同樣的構建過程可以創建不同的表示。 一步一步創建一個複雜的對象，它允許
 *  用戶只通過指定複雜對象的類型和內容就可以建構它們，用戶不需要知道內部的具體建構細節。
 *  建造者模式客戶端返回的不是一個簡單的產品，而是一個由多個部件組成的複雜產品。
 *
 * 差異
 *  A. Factory模式：創建單個類的模式（關注單個產品）
 *  B. Builder模式：將各種產品集中起來進行管理（關注複合對象）
 *
 * 優點
 *  1. 在建造者模式中，客戶端不必知道產品內部組成細節。
 *  2. 每一個具體建造者都相對獨立，與其他具體建造者無關，因此可以方便的'替換'具體建造者或增加新的具體建造者。
 *  3. 可以更加精細地控制產品創建的過程。
 * 缺點
 *  1. 建造者模式所創建的產品一般具有'較多的共同點'，其組成部分相似，如果產品之間的差異性很大，則不適用建造者模式，因此使用範圍受到限制。
 *  2. 如果產品的內部變化複雜，可能會導致需要定義很多具體建造者來實現這種變化，導致系統變龐大。
 */

public class MealBuilder {

    public Meal SetNo1(){
        SMLog.i("SetNo1:");
        Meal meal = new Meal();
        meal.buyItem(new VegBurger());
        meal.buyItem(new Coke());
        meal.showItem();
        return meal;
    }

    public Meal SetNo2(){
        SMLog.i("SetNo2:");
        Meal meal = new Meal();
        meal.buyItem(new ChickenBurger());
        meal.buyItem(new LemonTea());
        meal.showItem();
        return meal;
    }

    public Meal SetNo3(){
        SMLog.i("SetNo3:");
        Meal meal = new Meal();
        meal.buyItem(new ChickenBurger());
        meal.buyItem(new Coke());
        meal.showItem();
        return meal;
    }

    public Meal SetNo4(){
        SMLog.i("SetNo4:");
        Meal meal = new Meal();
        meal.buyItem(new VegBurger());
        meal.buyItem(new LemonTea());
        meal.showItem();
        return meal;
    }

    public Meal SetAll(){
        SMLog.i("SetAll:");
        Meal meal = new Meal();
        meal.buyItem(new VegBurger());
        meal.buyItem(new LemonTea());
        meal.buyItem(new ChickenBurger());
        meal.buyItem(new Coke());
        meal.showItem();
        return meal;
    }
}
