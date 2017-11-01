package sample.hawk.com.mybasicappcomponents.designpattern._Principle;

import sample.hawk.com.mybasicappcomponents.IDemo;

/**
 * 6. 迪米特法則 (LoD：Law of Demeter) == 最少知道原則
 *    一個對象應該對其他對象保持最少的了解。
 *
 *    最少知識原則 Principle of Least Knowledge
 *    只和自己眼前的朋友交談 Only talk to your immediate friends
 *
 *    一個實體應當盡量少的與其他實體之間發生相互作用，使得系統功能模塊相對獨立。也就是說一個軟件實體應當盡可能少
 *    的與其他實體發生相互作用。這樣，當一個模塊修改時，就會盡量少的影響其他的模塊，擴展會相對容易，這是對軟件實
 *    體之間通信的限制，它要求限制軟件實體之間通信的寬度和深度。
 *
 *    EX:
 *      郵差送來掛號信，須要蓋收件人印章。
 *      一般人不會叫郵差自己進屋找印章，既浪費時間也不安全。
 *      正常都是自己進屋拿，或是請其他家人幫忙拿。
 *      因為不應該給郵差進屋找東西的權限、郵差也不須要知道印章放在屋內何處。
 */

public class P6_LoD implements IDemo {
    @Override
    public void demo() {

    }
}
