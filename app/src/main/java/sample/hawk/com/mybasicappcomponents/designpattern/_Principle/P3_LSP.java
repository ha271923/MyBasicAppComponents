package sample.hawk.com.mybasicappcomponents.designpattern._Principle;

import sample.hawk.com.mybasicappcomponents.IDemo;

/**
 * 3. 里氏(Liskov)代換原則 (LSP：Liskov Substitution Principle)
 *    里氏替換原則通俗的來講就是：子類可以擴展父類的功能，但不能改變父類原有的功能。
 *
 *    它包含以下4層含義：
 *     1. 子類可以實現父類的抽象方法，但不能覆蓋父類的非抽象方法。
 *     2. 子類中可以增加自己特有的方法。
 *     3. 當子類的方法重載(@Overload)父類的方法時，方法的前置條件（即方法的輸入參數）要比父類方法的輸入參數更寬鬆。
 *     4. 當子類的方法實現(implements)父類的抽象方法時，方法的後置條件（即方法的輸出返回值）要比父類更嚴格。
 *
 *	    看上去很不可思議，因為我們會發現在自己編程中常常會違反里氏替換原則，程序照樣跑的好好的。所以大家都會產生這樣
 *	    的疑問，假如我非要不遵循里氏替換原則會有什麼後果？
 *    後果就是：你寫的代碼出問題的機率將會大大增加。
 *
 */

public class P3_LSP implements IDemo {
    @Override
    public void demo() {

    }
}
