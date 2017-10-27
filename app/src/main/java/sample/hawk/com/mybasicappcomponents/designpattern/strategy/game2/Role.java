package sample.hawk.com.mybasicappcomponents.designpattern.strategy.game2;

import sample.hawk.com.mybasicappcomponents.designpattern.strategy.game2.strategies.IAttackMode;
import sample.hawk.com.mybasicappcomponents.designpattern.strategy.game2.strategies.IItemSet;
import sample.hawk.com.mybasicappcomponents.designpattern.strategy.game2.strategies.ISpeed;
import sample.hawk.com.mybasicappcomponents.utils.SMLog;

/**

 *
 */

public abstract class Role {
    public void Speed()
    {
        SMLog.i("Role::Speed() = 1");
    }

    public void Attack()
    {
        SMLog.i("Role::Attack() = 1p");
    }

    public void UseItem()
    {
        SMLog.i("Role::UseItem() = 1");
    }

    public void setStrategy(ISpeed strategy_Speed, IItemSet strategy_Item, IAttackMode strategy_AtkMode){

    }
}
