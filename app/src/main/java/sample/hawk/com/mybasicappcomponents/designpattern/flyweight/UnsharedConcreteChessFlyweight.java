package sample.hawk.com.mybasicappcomponents.designpattern.flyweight;

import sample.hawk.com.mybasicappcomponents.utils.SMLog;

/**
 * Created by ha271 on 2017/10/17.
 */

public class UnsharedConcreteChessFlyweight extends ChessFlyweight {

    public UnsharedConcreteChessFlyweight(String name)
    {
        super(name);
    }

    @Override
    public void Display(int x, int y)
    {
        SMLog.i("不共用的物件: {"+this.mName+"} ("+x+","+y+")");
    }
}
