package sample.hawk.com.mybasicappcomponents.basic.Polymorphism;

import sample.hawk.com.mybasicappcomponents.utils.SMLog;

/**
 * Created by ha271 on 2016/10/18.
 */

public class Body extends Bio implements IBodyActions {

    public void BodyApi(){
        SMLog.i("BodyApi");
    }
}
