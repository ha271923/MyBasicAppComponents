package sample.hawk.com.mybasicappcomponents.basic.Polymorphism;

import sample.hawk.com.mybasicappcomponents.utils.SMLog;

/**
 * Created by ha271 on 2016/10/18.
 */

public class Hand extends Bio implements IHandActions{

    public void HandApi(){
        SMLog.i("HandApi");
    }

    public Hand(String whichHand){


    }

    @Override
    public void getObject(Object obj) {

    }

    @Override
    public void putObject(Object obj) {

    }
}
