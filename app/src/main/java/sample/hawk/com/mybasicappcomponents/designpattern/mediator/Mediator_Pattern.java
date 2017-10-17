package sample.hawk.com.mybasicappcomponents.designpattern.mediator;

import sample.hawk.com.mybasicappcomponents.IDemo;

/**
 * Mediator (調解人)
 * 當物件和物件之間或有錯綜複雜的交互作用，可將這些關係交由另一物件(中介者)來處理，
 * 以減少這些物件間的耦合。
 *
 *  1. 原本複雜的對應關希:
 *     A <---> B
 *     A <---> C
 *     A <---> D
 *     B <---> C
 *     B <---> D
 *     C <---> D
 *  2. 變成統一對Mediator
 *     A --->
 *     B ---> Mediator --> A/B/C/D
 *     C --->
 *     D --->
 *
 */

public class Mediator_Pattern implements IDemo {
    @Override
    public void demo() {
        SFM_Mediator sfmMediator = new SFM_Mediator(); // 中介者

        APP_RD appRd = new APP_RD("APP rd", sfmMediator);
        SSD_RD ssdRd = new SSD_RD("SSD rd", sfmMediator);

        appRd.Send("Normal", "想拉肚子");
        ssdRd.Send("Normal", "同事揪吃飯");
        appRd.Send("DriverBug", "發現USB無反應");
        ssdRd.Send("AppBug", "反應APP效能太慢");


    }
}
