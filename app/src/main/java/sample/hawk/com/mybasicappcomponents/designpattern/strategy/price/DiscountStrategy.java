package sample.hawk.com.mybasicappcomponents.designpattern.strategy.price;

/**
 * Created by ha271 on 2017/10/27.
 */

// // Strategies #2 (50% discount)
public class DiscountStrategy implements IBillingStrategy {

    @Override
    public double getFinalPrice(final double rawPrice) {
        return rawPrice * 0.5;
    }
}