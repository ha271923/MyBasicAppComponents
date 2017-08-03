package sample.hawk.com.mybasicappcomponents.designpattern.builder;

/**
 * Created by ha271 on 2017/8/3.
 */

public class Bottle implements IPacking{
    @Override
    public String pack() {
        return "PaperCup";
    }
}
