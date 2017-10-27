package sample.hawk.com.mybasicappcomponents.designpattern.strategy.game2.strategies;

import sample.hawk.com.mybasicappcomponents.utils.SMLog;

/**
 * Created by ha271 on 2017/10/27.
 */

public class ItemKnight implements IItemSet {
    @Override
    public void UseItem() {
        SMLog.i("ItemKnight::UseItem() = 6 slots");
    }
}
