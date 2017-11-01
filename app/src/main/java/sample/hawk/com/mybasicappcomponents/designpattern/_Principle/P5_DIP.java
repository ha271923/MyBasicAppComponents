package sample.hawk.com.mybasicappcomponents.designpattern._Principle;

import sample.hawk.com.mybasicappcomponents.IDemo;

/**
 * 5. 依賴倒轉原則 (DIP：Dependency Inversion Principle)
 *    對抽象(abstract/interface)進行編程，不要對實現進行編程，這樣就降低了客戶與實現模塊間的耦合。
 *
 *    高層模組不應該依賴低層模組，兩個都應該依賴抽像(abstract/interface)。因為抽像相對較穩定。
 *    針對接口編寫程式，不要對具體實現的東西編寫程式。
 *    所謂DIP就是要依賴於抽象，不要依賴於具體。
 *    實現開閉原則的關鍵是抽象化，並且從抽象化導出具體化實現，如果說開閉原則是面向對象設計的"目標"的話，
 *    那麼依賴倒轉原則就是面向對象設計的主要"手段"。
 */

public class P5_DIP implements IDemo {
    @Override
    public void demo() {

    }
}
