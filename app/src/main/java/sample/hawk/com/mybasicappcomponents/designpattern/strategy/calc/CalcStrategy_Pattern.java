package sample.hawk.com.mybasicappcomponents.designpattern.strategy.calc;

import sample.hawk.com.mybasicappcomponents.IDemo;
import sample.hawk.com.mybasicappcomponents.utils.SMLog;

/**
 * Created by ha271 on 2017/10/27.
 */

public class CalcStrategy_Pattern implements IDemo {
    @Override
    public void demo() {
        SMLog.i("CalcStrategy_Pattern --- ");
        Calc calc = new Calc(new Algorithm_Add());
        SMLog.i("Algorithm A: 10 + 5 = " + calc.executeAlgorithm(10, 5));

        calc = new Calc(new Algorithm_Sub());
        SMLog.i("Algorithm B: 10 - 5 = " + calc.executeAlgorithm(10, 5));

        calc = new Calc(new Algorithm_Mul());
        SMLog.i("Algorithm C: 10 * 5 = " + calc.executeAlgorithm(10, 5));
    }
}
