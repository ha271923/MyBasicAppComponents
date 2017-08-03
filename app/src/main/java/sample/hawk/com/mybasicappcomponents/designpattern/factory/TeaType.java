package sample.hawk.com.mybasicappcomponents.designpattern.factory;

/**
 * Created by ha271 on 2017/8/3.
 */

public enum TeaType {
    GreenTea(0),
    BlackTea(1),
    MilkTea(2),
    RedTea(3),
    Cafe(4);

    private final int value;

    private TeaType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
