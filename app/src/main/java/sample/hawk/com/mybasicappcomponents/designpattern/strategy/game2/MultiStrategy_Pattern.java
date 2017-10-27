package sample.hawk.com.mybasicappcomponents.designpattern.strategy.game2;

import sample.hawk.com.mybasicappcomponents.IDemo;
import sample.hawk.com.mybasicappcomponents.designpattern.strategy.game2.strategies.AttackMagic;
import sample.hawk.com.mybasicappcomponents.designpattern.strategy.game2.strategies.AttackPhysical;
import sample.hawk.com.mybasicappcomponents.designpattern.strategy.game2.strategies.ItemKnight;
import sample.hawk.com.mybasicappcomponents.designpattern.strategy.game2.strategies.ItemWizard;
import sample.hawk.com.mybasicappcomponents.designpattern.strategy.game2.strategies.Speed;
import sample.hawk.com.mybasicappcomponents.utils.SMLog;

/**
 * Q: 想把Car增加Attack, 除了AmbulanceCar以外
 * A1: 一般我們可以讓除了AmbulanceCar Override Car別的Atk()方法並放空即可，但是這並不是一種最好的解決方法，因
 *     為當不需要攻擊方法的車種有很多款甚至以後也會陸續加入新車種時，是不是每次都要override這個攻擊方法
 * A2: abstract改成interface，這樣的做法其實也不恰當，因為透過介面每個類別也必須要去實作方法，一樣當加入新的
 *     行為時還是要去一一增加實作此介面的類別，當車款類別一多之後將會變得非常麻煩。
 * A3: 採用Strategy，如此即可達成不用管這些方法到底要做什麼，行為的實作由實作該行為介面的類別去做而PorscheCar
 *     只需要知道攻擊就是Atk()管你怎麼實作方法的內容，這樣就切開兩個類別的相依了
 *
 */

public class MultiStrategy_Pattern implements IDemo {
    @Override
    public void demo() {
        SMLog.i("MultiStrategy_Pattern --- ");

        // 原本是普通騎士
        SMLog.i("普通騎士");
        Role player1 = new RoleKnight(new Speed(30));
        player1.Speed();
        player1.UseItem();
        player1.Attack();
        // 轉職成巫師
        SMLog.i("轉職 成巫師");
        Speed highSpeed   = new Speed(100);
        ItemWizard itemWizard = new ItemWizard();
        AttackMagic magicAtk  = new AttackMagic();
        player1.setStrategy(highSpeed,itemWizard,magicAtk);
        player1.Speed();
        player1.UseItem();
        player1.Attack();
        // 在轉職回重裝騎士
        SMLog.i("轉職 重裝騎士");
        Speed lowSpeed    = new Speed(5);
        ItemKnight itemKnight = new ItemKnight();
        AttackPhysical physicalAtk = new AttackPhysical();
        player1.setStrategy(lowSpeed,itemKnight,physicalAtk);
        player1.Speed();
        player1.UseItem();
        player1.Attack();
    }
}
