package sample.hawk.com.mybasicappcomponents.designpattern.builder;

/**
 * Created by ha271 on 2017/8/3.
 */

public class ChickenBurger extends Burger{
    @Override
    public String name() {
        return "ChickenBurger";
    }

    @Override
    public float price() {
        return 35;
    }
}
