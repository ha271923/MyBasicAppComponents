package sample.hawk.com.mybasicappcomponents.designpattern.builder;

/**
 * Created by ha271 on 2017/8/3.
 */

public abstract class Burger implements IItem{
    @Override
    public IPacking packing() {
        return new Wrapper();
    }

    @Override
    public abstract float price();
}

