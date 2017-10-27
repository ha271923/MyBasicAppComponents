package sample.hawk.com.mybasicappcomponents.designpattern.strategy.game2.strategies;

import sample.hawk.com.mybasicappcomponents.utils.SMLog;

/**
 * Created by ha271 on 2017/10/27.
 */

public class Speed implements ISpeed {
    int mSpeed = 10;

    public Speed(int speed){
        mSpeed = speed;
    }

    @Override
    public void Speed() {
        SMLog.i("Speed::Speed() = " + mSpeed);
    }
}
