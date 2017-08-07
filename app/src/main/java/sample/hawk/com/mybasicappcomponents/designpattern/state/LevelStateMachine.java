package sample.hawk.com.mybasicappcomponents.designpattern.state;

/**
 * A. State Machine pattern by LevelStateMachine class
 *   1. LevelStateMachine::setStateContext(state)
 *   2. LevelStateMachine::stateWork()
 *        ConcreteState001::stateWork(state) with if-else
 *          ConcreteState050::stateWork(state) with if-else
 *            ConcreteState090::stateWork(state) with if-else
 *              ConcreteStateMAX::stateWork(state) with if-else
 *
 * B. Condition by if-else
 *   if() {...}
 *   else if() {...}
 *   else if() {...}
 *   else if() {...}
 *   else if() {...}
 *   else {...}
 *
 * State Pattern 優缺點如下, 代替簡單的 if-else並不划算, 適合較複雜的狀態轉移(遞迴/goto...)
 * 優點：
 *  1. 將所有可能的不同行為方法呼叫，包裝成只需要呼叫Context類別的物件所提供的API方法即可達成，client使用
 *     該API時可減少撰寫要選擇採用哪個方法使用的額外邏輯判斷，降低client的程式碼複雜度。
 *  2. 對於在事務邏輯上沒有『記憶性』的狀態，用狀態模式可減少撰寫重複的程式碼。
 *  3. 可因應狀態(State)的不同，而在client呼叫Context提供的公用方法時，進行額外的動作。
 *  4. 可在執行時期動態修改狀態模式的狀態類別之間狀態轉移的邏輯，進而能夠在程式執行時期動態增減狀態模式的
 *     行為動作。
 *  5. 將實際行為明確依照狀態的不同包裝至不同的類別程式碼內，減少日後修改某一個狀態內的行為時，導致全部的
 *     行為都有可能會出錯的機會。
 *  6. 日後有因應需求變化而需要新增，移除行為時，只要新增或移除State類別和修改對應的狀態轉移程式碼即可。
 *     (實際上這需要狀態模式的實作架構設計的夠好才行，所以這實際上是優點也是缺點)

 * 缺點：
 *  1. 一個狀態一個AbstractState子類別(狀態類別)的設計架構會使得類別數量顯著增加，增加程式碼維護難度。
 *  2. 如果沒有整個狀態模式架構的狀態圖(state diagram)，程式碼很容易日後看不懂或遺漏了某些狀態轉移狀況而出錯。
 *  3. 根據採用的實作架構不同，可能會有一些狀態類別之中有不少用來做狀態轉移的方法是不允許在該狀態之中執行該方
 *     法內容的，通常在實作上，會將該方法的內容留空白或是直接撰寫丟出例外的程式碼，造成很多乍看之下無意義的程
 *     式碼充斥在各個狀態類別，並且也使得這個狀態類別繼承結構，明顯違反了物件導向設計原則之中的里氏替換原則
 *  4. 在實作上，可以藉由宣告一些額外『role interface』並且讓狀態物件實作這些interface，來避免充斥額外無意義
 *     程式碼的情形。
 *  5. 由於在執行時須保留很多狀態類別的物件在電腦記憶體內，增加額外的運算資源負擔，如果狀態模式之中的狀態轉移
 *     程式碼沒寫好，還有可能造成執行時發生記憶體洩漏的情形。
 *  6. 在採用狀態模式的程式碼可能會有多執行緒共同執行的情況時，須注意是否有因為想要減少前述缺點4.所描述的情形
 *     ，而使用Singleton設計模式來管理狀態物件，造成每一個Context物件之間會有狀態錯亂的問題，如果有就不應該使
 *     用Singleton設計模式來管理狀態物件。
 *  7. 程式碼採用狀態模式撰寫時，在實際實作上，有幾個需要注意的地方：
 *     a. 建議一定要畫狀態圖，並且附在程式碼的文件之中，否則連自己日後都會看不懂，更別提別人在看code追蹤邏輯
 *        結構，或因需求變化而做程式內容修改時，絕對會搞不懂或是漏掉少寫一兩個狀態以及狀態轉移的程式碼而出錯。
 *     b. 進行單元測試(Unit test)時，一定要確定每一個state類別的方法和狀態轉移的邏輯路徑都有測試過，以免實際
 *        執行時因為某些狀態轉移邏輯路徑沒執行過測試過，而發生問題。
 *     c. 由於原本的狀態模式定義只有說，『讓一個物件有不同的狀態，而且其提供給外部的實質行為模式根據狀態的不
 *        同而有不同』，並沒有明確說明以下幾個必要的設計觀點：
 *        Q1: 每個狀態的實質行為在何處實作?
 *        Q2 狀態與狀態之間的為何會發生轉移，以及轉移情形應如何實作?
 *
 */

public class LevelStateMachine {
    public int level = 1;
    private LevelStateContext state;

    public LevelStateMachine()
    {
        setStateContext(new ConcreteState001());
    }

    public void setStateContext(LevelStateContext state)
    {
        this.state = state;
    }

    public void stateWork()
    {
        state.stateWork(this);
    }

}
