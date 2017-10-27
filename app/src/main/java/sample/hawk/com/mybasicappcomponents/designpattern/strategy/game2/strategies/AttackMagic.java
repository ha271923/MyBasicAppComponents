package sample.hawk.com.mybasicappcomponents.designpattern.strategy.game2.strategies;

import sample.hawk.com.mybasicappcomponents.utils.SMLog;

/**
 * Created by ha271 on 2017/10/27.
 */

public class AttackMagic implements IAttackMode {
    @Override
    public void Attack() {
        SMLog.i("AttackMagic::Attack() = (10*5)p");
    }
}
