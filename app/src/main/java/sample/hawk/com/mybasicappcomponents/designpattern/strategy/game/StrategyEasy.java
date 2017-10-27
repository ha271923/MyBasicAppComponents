package sample.hawk.com.mybasicappcomponents.designpattern.strategy.game;

import sample.hawk.com.mybasicappcomponents.utils.SMLog;

/**
 * Created by ha271 on 2017/10/27.
 */

public class StrategyEasy implements IGameStrategy {

    public int nextHand() {
        SMLog.i("Strategy A");
        return 1;
    }
}
