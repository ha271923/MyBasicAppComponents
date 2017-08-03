package sample.hawk.com.mybasicappcomponents.designpattern.builder;

/**
 * Created by ha271 on 2017/8/3.
 */

public class Coke extends ColdDrink{
    @Override
    public String name() {
        return "Coke";
    }

    @Override
    public float price() {
        return 18;
    }
}
