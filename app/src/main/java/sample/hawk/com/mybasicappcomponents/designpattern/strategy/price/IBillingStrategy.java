package sample.hawk.com.mybasicappcomponents.designpattern.strategy.price;

/**
 * Created by ha271 on 2017/10/27.
 */
// Strategy interface
public interface IBillingStrategy {
    double getFinalPrice(final double rawPrice);
}
