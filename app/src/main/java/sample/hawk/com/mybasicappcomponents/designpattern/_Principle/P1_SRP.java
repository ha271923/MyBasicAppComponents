package sample.hawk.com.mybasicappcomponents.designpattern._Principle;

import sample.hawk.com.mybasicappcomponents.IDemo;

/**
 * 1. 單一職責原則 (SRP：Single Responsibility Principle)
 *    一個類別，應該只有一個引起它變化的原因。
 *
 *    應該只有一個職責。每一個職責都是變化的一個軸線，如果一個類有一個以上的職責，這些職責就耦合在了一起。
 *    這會導致脆弱的設計。當一個職責發生變化時，可能會影響其它的職責。另外，多個職責耦合在一起，會影響復用性。
 *    例如：要實現邏輯和界面的分離。
 *
 *  遵循單一職責原的優點有：
 *   A. 可以降低類的複雜度，一個類只負責一項職責，其邏輯肯定要比負責多項職責簡單的多；
 *   B. 提高類的可讀性，提高系統的可維護性；
 *     變更引起的風險降低，變更是必然的，如果單一職責原則遵守的好，當修改一個功能時，可以顯著降低對其他功能的影響。
 *
 *  不得已違反時的原則:
 *    只有邏輯足夠簡單，才可以在代碼級別上違反SRP；
 *    只有類中method數量很少，才可以在方法級別上違反SRP；
 *
 */

public class P1_SRP implements IDemo {
    @Override
    public void demo() {

    }
}
