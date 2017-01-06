package sample.hawk.com.mybasicappcomponents.designpattern.observer;

import sample.hawk.com.mybasicappcomponents.utils.SMLog;

/**
 * Created by ha271 on 2017/1/6.
 */

public class Person2 implements Observer{

    public void  update() {
        SMLog.i("Person2  獲得更新");
    }
}
