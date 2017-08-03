package sample.hawk.com.mybasicappcomponents.designpattern.builder;

/**
 * Created by ha271 on 2017/8/3.
 */

public abstract class ColdDrink implements IItem{
    @Override
    public IPacking packing() {
        return new Bottle();
    }

    @Override
    public abstract float price();
}
