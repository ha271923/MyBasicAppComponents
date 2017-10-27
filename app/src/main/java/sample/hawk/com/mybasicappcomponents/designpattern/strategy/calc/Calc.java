package sample.hawk.com.mybasicappcomponents.designpattern.strategy.calc;

/**
 * Created by ha271 on 2017/10/27.
 */

public class Calc {
    private IAlgorithm mAlgorithm;

    public Calc(IAlgorithm algorithm){
        this.mAlgorithm = algorithm;
    }

    public int executeAlgorithm(int num1, int num2){
        return mAlgorithm.implAlgorithm(num1, num2);
    }
}
