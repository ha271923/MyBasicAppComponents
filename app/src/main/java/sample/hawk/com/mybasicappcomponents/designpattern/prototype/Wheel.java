package sample.hawk.com.mybasicappcomponents.designpattern.prototype;

/**
 * Created by ha271 on 2017/10/18.
 */

public class Wheel implements Cloneable {
    // 也許還有一些複雜的設定
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}