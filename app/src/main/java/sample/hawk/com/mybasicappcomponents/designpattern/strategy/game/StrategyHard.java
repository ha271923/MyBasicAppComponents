package sample.hawk.com.mybasicappcomponents.designpattern.strategy.game;

import sample.hawk.com.mybasicappcomponents.utils.SMLog;

/**
 * Created by ha271 on 2017/10/27.
 */

public class StrategyHard implements IGameStrategy {
    public int nextHand() {
        SMLog.i("Strategy C");
        return 3;
    }
}
