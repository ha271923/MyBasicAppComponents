package sample.hawk.com.mybasicappcomponents.designpattern.strategy.game2.strategies;

import sample.hawk.com.mybasicappcomponents.utils.SMLog;

/**
 * Created by ha271 on 2017/10/27.
 */

public class ItemWizard implements IItemSet {
    @Override
    public void UseItem() {
        SMLog.i("ItemWizard::UseItem() = 3 slots");
    }
}
