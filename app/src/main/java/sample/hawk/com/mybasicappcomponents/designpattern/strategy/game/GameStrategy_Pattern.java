package sample.hawk.com.mybasicappcomponents.designpattern.strategy.game;

import java.util.Random;

import sample.hawk.com.mybasicappcomponents.IDemo;
import sample.hawk.com.mybasicappcomponents.utils.SMLog;

/**
 * Created by ha271 on 2017/10/27.
 */

public class GameStrategy_Pattern implements IDemo {
    @Override
    public void demo() {
        SMLog.i("GameStrategy_Pattern --- ");
        Chess computer = null;
        Random rand = new Random();
        switch (rand.nextInt(3)) {
            case 0:
                computer = new Chess(new StrategyEasy());
                break;
            case 1:
                computer = new Chess(new StrategyNormal());
                break;
            case 2:
                computer = new Chess(new StrategyHard());
                break;
        }
        computer.nextHand();
   }
}
