package sample.hawk.com.mybasicappcomponents.designpattern._Principle;

import sample.hawk.com.mybasicappcomponents.IDemo;

/**
 * 4. 介面隔離原則 (ISP：Interface Segregation Principle)
 *    使用多個interface，比使用單個interface要好。
 *
 *    一個軟件的設計思想，從大型軟件架構出發，為了升級和維護方便。降低依賴，降低耦合。
 *
 * Q: 單一interface的缺點
 *
 *    Class A                       Class C (Q:只用到ABC, 卻要實作A~F)
 *            \                    /
 *             ---> interface X <--
 *            /      method A      \
 *    Class B        method B       Class D (Q:只用到DEF, 卻要實作A~F)
 *                   method C
 *                   method D
 *                   method E
 *                   method F
 *
 * A: 將 interface X拆成多個interface, 以減少耦合度
 *
 */

public class P4_ISP implements IDemo {
    @Override
    public void demo() {

    }
}
