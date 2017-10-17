package sample.hawk.com.mybasicappcomponents.designpattern.factory.Abstract;

import sample.hawk.com.mybasicappcomponents.IDemo;
import sample.hawk.com.mybasicappcomponents.designpattern.factory.TeaType;

/**
 * Created by ha271 on 2017/10/13.
 */

public class AbstractFactory_Pattern implements IDemo {
    @Override
    public void demo() {
        IAbstractFactory abstractFactory1 = new DrinkFactory();
        abstractFactory1.createProduct(TeaType.Cafe);
        IAbstractFactory abstractFactory2 = new FoodFactory();
        abstractFactory2.createProduct(FoodType.Bakery);
    }
}
