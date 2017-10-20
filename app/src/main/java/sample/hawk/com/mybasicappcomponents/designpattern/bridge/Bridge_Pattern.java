package sample.hawk.com.mybasicappcomponents.designpattern.bridge;

import sample.hawk.com.mybasicappcomponents.IDemo;

/**
 * Prism proj中BlinkFeed與Launcher的Divorce過程期間, Jason_Lai就是採用此方法來降低耦合
 *
 * 橋接模式 (Bridge Pattern) == 柄体模式(Handle and Body) == 接口模式(Interface)
 *   將一個物件的具體行為(實作)抽出來，成為一個獨立的物件。
 *   也就是原本的一個物件，變成兩個物件： 抽像物件 + 實作物件。
 *   優點是抽像物件與實作物件可以"解耦合"，各自獨立變化。
 *   橋接模式將繼承關係轉換為關聯關係，從而降低了類與類之間的耦合(coupling)，減少了代碼編寫量。
 * Bridge Pattern相對的比較複雜，使用時要注意。
 *
 * 優點:
 *   1. 橋接模式把抽象和實作分開，如此他們兩個可以互相獨立,其精神在於降低abstraction和implementor之間的coupling（耦合度）
 *   2. 提高了系統的可擴充性, 就算增加一個維度的擴充, 也不需要修改原有系統
 *   3. 實現細節對客戶透明, 可以對用戶隱藏是做細節資訊
 *   4. 讓abstraction在延伸子類別時，不用擔心因為implementor的改變，而須修改子類別的程式碼。
 *   5. 繼承是一種強耦合的結構，父變子變，多次繼承很可能照成"Class數量爆炸"。
 *
 * 缺點:
 *   1. 增加系統理解的複雜度與設計難度
 *   2. 由豫劇合的關鍵建立在抽象層, 要求開發者針對抽象層進行設計與規劃
 *
 *
 *   問題: 不使用Bridge Pattern, 我們需要創造 (11個class), 數量爆炸!:
 *     Parent0(Animal) <-- Child00(LandAnimal) <-- GrandSon000(VegetableAnimal)
 *                                             <-- GrandSon001(MealAnimal)
 *                     <-- Child01(SeaAnimal)  <-- GrandSon010(VegetableAnimal)
 *                                             <-- GrandSon011(MealAnimal)
 *                                             <-- GrandSon012(jellyfish)
 *                     <-- Child02(SkyAnimal)  <-- GrandSon020(VegetableAnimal)
 *                                             <-- GrandSon021(MealAnimal)
 *
 *  解答: 使用Bridge Pattern, 我們只需要創造 (6個class + 1個interface):
 *    EatVegetable --> IAnimalEater -[Bridge]-> Parent0(Animal) <-- Child00(LandAnimal)
 *    EatMeal      -->                                          <-- Child01(SeaAnimal)
 *                                                              <-- Child02(SkyAnimal)
 *
 * 適用症狀:類別中的抽象和實作部分耦合在一起，而且抽象或實作部分需要經常擴充，因此造成類別激增問題
 * 使用後成效:類別擴充性佳, 很適合用在需要跨多個平台的圖形和視窗系統
 *
 * 使用步驟:
 * STEP1. < 呼叫端 > 先在 Abstract class 要求 ReAbstract class 創建呼叫功能模組的 implement API == Draw_OverrideInterfaceAPI
 * STEP2. <被呼叫端> 既有 ConcretelImpl class 要求支援 Implementor中所列的 methods
 * STEP3. <橋接兩端> 在Abstract class中, 取得Implementor的Object Reference
 * STEP4. <開始使用> 在ReAbstract class中, 開始使用Implementor根據interface所提供的methods
 *
 *
 * 本範例:
 *    <供功能模組>  <    BridgePattern:建立橋接過程     >     <使用功能模組>
 *    DrawAPI_A --> IDrawAPI -[Bridge]-> Parent0(Shape) <-- Child00(ShapeCircle)
 *    DrawAPI_B -->                                     <-- Child01(ShapeSquare)
 *
 */

public class Bridge_Pattern implements IDemo {
    @Override
    public void demo() {
        ShapeCircle circle_A = new ShapeCircle(new DrawAPI_A()); // ex: DrawAPI_A = OpenGL
        ShapeCircle circle_B = new ShapeCircle(new DrawAPI_B()); // ex: DrawAPI_B = DirectX

        ShapeSquare square_C = new ShapeSquare(new DrawAPI_A());
        ShapeSquare square_D = new ShapeSquare(new DrawAPI_B());

        // Override interface API
        circle_A.Draw_OverrideInterfaceAPI(); // call OpenGL
        circle_B.Draw_OverrideInterfaceAPI(); // call DirectX
        square_C.Draw_OverrideInterfaceAPI();
        square_D.Draw_OverrideInterfaceAPI();

        // Override abstract API
        circle_A.Draw_OverrideAbstractAPI(); // call Circle's API
        square_D.Draw_OverrideAbstractAPI(); // call Square's API
    }
}
