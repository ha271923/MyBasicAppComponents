package sample.hawk.com.mybasicappcomponents.designpattern.builder;

import sample.hawk.com.mybasicappcomponents.IDemo;
import sample.hawk.com.mybasicappcomponents.utils.SMLog;

/**
 * Created by ha271 on 2017/10/13.
 */

public class Builder_Pattern implements IDemo {
    @Override
    public void demo(){
        SMLog.i("----- Packing is FREE!! -----");
        MealDirector mealDirector = new MealDirector();
        SMLog.i("Total Price= " + mealDirector.getOrder(1));
        SMLog.i("Total Price= " + mealDirector.getOrder(2));
        SMLog.i("Total Price= " + mealDirector.getOrder(3));
        SMLog.i("Total Price= " + mealDirector.getOrder(4));
        SMLog.i("Total Price= " + mealDirector.getOrder(123456789));
    }
}
