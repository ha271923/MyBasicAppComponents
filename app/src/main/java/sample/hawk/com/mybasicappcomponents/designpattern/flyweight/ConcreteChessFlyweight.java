package sample.hawk.com.mybasicappcomponents.designpattern.flyweight;

import sample.hawk.com.mybasicappcomponents.utils.SMLog;

/**
 * Created by ha271 on 2017/10/17.
 */

public class ConcreteChessFlyweight extends ChessFlyweight {

    public ConcreteChessFlyweight(String name)
    {
        super(name);
    }

    // X、Y座標，非共享資料(物件雖是共享, 但部分資料並未, 因為這只是外部傳入的參數, 並未在物件內部儲存)
    @Override
    public  void Display(int x, int y)
    {
        SMLog.i("{"+this.mName+"} ("+x+","+y+")");
    }
}
