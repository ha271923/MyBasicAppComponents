package sample.hawk.com.mybasicappcomponents.designpattern.factory.Abstract;

/**
 * Created by ha271 on 2017/8/3.
 */

public enum FoodType {
    Bakery(0),
    Cookie(1);

    private final int value;

    private FoodType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}

