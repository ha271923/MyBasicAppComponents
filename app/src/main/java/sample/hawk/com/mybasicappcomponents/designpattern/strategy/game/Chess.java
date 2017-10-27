package sample.hawk.com.mybasicappcomponents.designpattern.strategy.game;

/**
 * Created by ha271 on 2017/10/27.
 */

public class Chess {
    private IGameStrategy IGameStrategy;

    public Chess(IGameStrategy IGameStrategy) {
        this.IGameStrategy = IGameStrategy;
    }

    public int nextHand() {
        return IGameStrategy.nextHand();
    }
}
