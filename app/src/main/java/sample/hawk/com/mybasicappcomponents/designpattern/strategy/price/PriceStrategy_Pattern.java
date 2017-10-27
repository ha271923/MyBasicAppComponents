package sample.hawk.com.mybasicappcomponents.designpattern.strategy.price;

import sample.hawk.com.mybasicappcomponents.IDemo;
import sample.hawk.com.mybasicappcomponents.utils.SMLog;

/**
 * Created by ha271 on 2017/10/27.
 */

// Client
public class PriceStrategy_Pattern implements IDemo {
    @Override
    public void demo() {
        SMLog.i("PriceStrategy_Pattern --- ");
        Customer customer1 = new Customer(new NormalStrategy());

        // Normal billing
        customer1.add(1.0, 1);

        // Discount day START ++++++
        customer1.setStrategy(new DiscountStrategy());
        customer1.add(1.0, 2);

        // New Customer
        Customer customer2 = new Customer(new DiscountStrategy());
        customer2.add(0.8, 1);
        // The Customer pays
        customer1.printBill();

        // Discount day END ------
        customer2.setStrategy(new NormalStrategy());
        customer2.add(1.3, 2);
        customer2.add(2.5, 1);
        customer2.printBill();
    }
}
