package sample.hawk.com.mybasicappcomponents.designpattern.strategy.price;

/**
 * Created by ha271 on 2017/10/27.
 */

// Strategies #1 (unchanged price)
class NormalStrategy implements IBillingStrategy {

    @Override
    public double getFinalPrice(final double rawPrice) {
        return rawPrice;
    }

}