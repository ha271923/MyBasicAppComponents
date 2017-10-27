package sample.hawk.com.mybasicappcomponents.designpattern.strategy.calc;

/**
 * Created by ha271 on 2017/10/27.
 */

public class Algorithm_Sub implements IAlgorithm {
    @Override
    public int implAlgorithm(int num1, int num2) {
        return num1 - num2;
    }
}
