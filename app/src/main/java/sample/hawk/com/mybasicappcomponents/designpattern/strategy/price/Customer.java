package sample.hawk.com.mybasicappcomponents.designpattern.strategy.price;

import java.util.ArrayList;
import java.util.List;

import sample.hawk.com.mybasicappcomponents.utils.SMLog;

/**
 * Created by ha271 on 2017/10/27.
 */

public class Customer {
    private List<Double> drinks;
    private IBillingStrategy strategy;

    public Customer(final IBillingStrategy strategy) {
        this.drinks = new ArrayList<Double>();
        this.strategy = strategy;
    }

    public void add(final double price, final int quantity) {
        drinks.add(strategy.getFinalPrice(price*quantity));
    }

    // Payment of bill
    public void printBill() {
        double sum = 0;
        for (Double i : drinks) {
            sum += i;
        }
        SMLog.i("Total due: " + sum);
        drinks.clear();
    }

    // Set Strategy
    public void setStrategy(final IBillingStrategy strategy) {
        this.strategy = strategy;
    }

}
