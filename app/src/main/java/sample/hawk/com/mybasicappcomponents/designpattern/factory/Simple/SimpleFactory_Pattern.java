package sample.hawk.com.mybasicappcomponents.designpattern.factory.Simple;

import sample.hawk.com.mybasicappcomponents.IDemo;
import sample.hawk.com.mybasicappcomponents.designpattern.factory.TeaType;

/**
 * Created by ha271 on 2017/10/13.
 */

public class SimpleFactory_Pattern implements IDemo {
    @Override
    public void demo() {
        SimpleFactory sf = new SimpleFactory();
        sf.createProduct(TeaType.Cafe);
        sf.createProduct(TeaType.GreenTea);
        sf.createProduct(TeaType.RedTea);
        sf.createProduct(TeaType.BlackTea);
        sf.createProduct(TeaType.MilkTea);
    }
}
