package sample.hawk.com.mybasicappcomponents.designpattern.factory.Normal;

import sample.hawk.com.mybasicappcomponents.IDemo;

/**
 * Created by ha271 on 2017/10/13.
 */

public class NormalFactory_Pattern implements IDemo {
    @Override
    public void demo() {
        NormalFactory nf_product1 = new CafeFactory();
        nf_product1.createProduct();
        NormalFactory nf_product2 = new GreenTeaFactory();
        nf_product2.createProduct();
        NormalFactory nf_product3 = new RedTeaFactory();
        nf_product3.createProduct();
        NormalFactory nf_product4 = new BlackTeaFactory();
        nf_product4.createProduct();
        NormalFactory nf_product5 = new MilkTeaFactory();
        nf_product5.createProduct();
    }
}
