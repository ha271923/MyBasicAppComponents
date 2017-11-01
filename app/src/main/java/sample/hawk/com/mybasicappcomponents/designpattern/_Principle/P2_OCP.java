package sample.hawk.com.mybasicappcomponents.designpattern._Principle;

import sample.hawk.com.mybasicappcomponents.IDemo;

/**
 * 2. 開放、封閉原則 (OCP：Open Closed Principle)
 *    對於擴展是開放的 (open for extension) 對於修改是封閉的 (closed for modification)
 *
 *    在程序需要進行拓展的時候，不能去修改原有的代碼，實現一個熱插拔的效果。所以一句話概括就是：為了使程序的擴展
 *    性好，易於維護和升級。想要達到這樣的效果，我們需要使用接口和抽像類，後面的具體設計中我們會提到這點。
 */

public class P2_OCP implements IDemo {
    @Override
    public void demo() {

    }
}
