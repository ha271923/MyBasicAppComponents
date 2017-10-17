package sample.hawk.com.mybasicappcomponents.designpattern.factory;

import sample.hawk.com.mybasicappcomponents.IDemo;

/**
 * Created by ha271 on 2017/10/13.
 */

public class FactoryMethod_Pattern implements IDemo {
    @Override
    public void demo() {
        IDrinkAction id = null;
        id = new Cafe();
        id.AddMaterial();
        id.Brew();
        id.DeliverCpu();
        id = new GreenTea();
        id.AddMaterial();
        id.Brew();
        id.DeliverCpu();
        id = new RedTea();
        id.AddMaterial();
        id.Brew();
        id.DeliverCpu();
    }
}
