package sample.hawk.com.mybasicappcomponents.designpattern.strategy;

import sample.hawk.com.mybasicappcomponents.IDemo;
import sample.hawk.com.mybasicappcomponents.designpattern.strategy.calc.CalcStrategy_Pattern;
import sample.hawk.com.mybasicappcomponents.designpattern.strategy.game.GameStrategy_Pattern;
import sample.hawk.com.mybasicappcomponents.designpattern.strategy.game2.MultiStrategy_Pattern;
import sample.hawk.com.mybasicappcomponents.designpattern.strategy.price.PriceStrategy_Pattern;

/**
 *  策略模式
 *
 *  定義:
 *    定義了演算法家族，個別封裝起來，讓他們之間可以互相替換，此模式讓演算法的變動獨立於使用操作的物件之外
 *
 *  原則:
 *    1. 將演算法中可能的變化(演算法)獨立出來
 *    2. 針對interface寫程式而非針對實踐去編程
 *    3. 多用合成少用繼承
 *
 *   優點
 *      1. 將原本複雜的分支流程結構簡化，原本要巢狀階層式的循序判斷邏輯，轉化成同等地位的Strategy物件和其各自對應的確認是否為所屬情況的規則。
 *      2. 若日後有新增的情況時，也只需要增加新的實體Strategy類別和修改Context類別的constructor程式碼上加入新增的Strategy物件即可。
 *      3. 修改某一實體Strategy的類別時，因為已經拆分開了，不會影響到原本其他Strategy類別的功能。
 *      4. 易於分配工作：原本只寫在同一個類別內同一個方法的情況拆成Context類別和眾多個實體Strategy類別，可以很容易將工作分派給多個developer共同開發以加快進度，且因為是一個個分開來的Strategy類別，減少原始碼控管時需要合併多人工作成果時所帶來的風險。
 *      5. 易於測試：
 *         a. 可對每個Strategy類別提供的方法個別進行單元測試以確保每個Strategy類別的運作結果是正確的，減少單一測試案例寫很龐大的可能。
 *         b. 針對Context類別，可撰寫測試用的”假(Mock)”Strategy類別載入進Context以進行測試，不會造成實際的實體Strategy類別所執行的演算法中，影響到不可復原資料或是難以重複測試的情形；也不必得等到所有的實體Strategy類別都完成沒問題了，才能對Context類別進行單元測試。
 *         c. 在只有產生exception stack trace而沒有印出偵錯版才會有程式碼行號的log訊息時，會比較容易找出有問題的程式碼位置。
 *
 *  缺點
 *      1. 程式碼檔案的數量和類別的數量增多，如果沒有在專案層級組織好這些檔案，很容易掉東掉西。
 *      2. 需知道策略模式這種Design Pattern的開發者才會看懂這樣組織的程式結構。
 *      3. 增加了執行環境內額外的運算資源消耗：
 *         a. 額外的Strategy類別載入和物件生成需要消耗額外CPU運算和記憶體資源。
 *         b. Strategy類別的物件和Context類別的物件需要額外的一些程式邏輯和資料成員變數來傳遞最後運算結果，一樣也是導致需要消耗額外CPU運算和記憶體資源。
 *           （注：由於本例只是回傳字串故影響不大，倘若回傳的結果是需要儲存大量資料的Value Object，就有可能會因為這樣而比原本的更吃CPU和記憶體資源）
 *         c. 目前Context類別程式碼內，在根據輸入來判斷使用哪種Strategy以便呼叫WelcomeStr()方法產生結果的邏輯，在Strategy數量如果非常多的情況下，效能會變的比直接用一堆condition statement (if else, switch, goto…) hard code的硬幹方法來得慢很多。
 *            此缺點的彌補方法有幾個：
 *              i. 將這群Strategy物件存在Hashmap內改寫成用Hash key查找的方式。
 *             ii. 將判斷是否採用某Strategy的邏輯拆開到別的類別之中。
 *                 (例如使用Factory Pattern來產生Strategy物件的Factory類別內)
 *            iii. 增加幾個前置判斷條件來預先篩選需要進行情況判斷的Strategy物件數目。
 *
 *  參考: https://goo.gl/WDpHBe
 */

// Client
public class Strategy_Pattern implements IDemo {
    @Override
    public void demo() {
        new PriceStrategy_Pattern().demo();
        new CalcStrategy_Pattern().demo();
        new GameStrategy_Pattern().demo();
        new MultiStrategy_Pattern().demo();
    }
}
