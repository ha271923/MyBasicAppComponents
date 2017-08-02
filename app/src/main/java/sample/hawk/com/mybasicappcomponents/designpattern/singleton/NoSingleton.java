package sample.hawk.com.mybasicappcomponents.designpattern.singleton;

import sample.hawk.com.mybasicappcomponents.utils.SMLog;

/**
 * Created by ha271 on 2017/8/2.
 */

public class NoSingleton {
    public int mCount = 100;

    public void printCounter(){
        SMLog.i(this+"  +++ TID="+Thread.currentThread());
        while(mCount>0) {
            SMLog.i(this+"      TID="+Thread.currentThread()+"  " + mCount);
            mCount--;
        }
        SMLog.i(this+"  --- TID="+Thread.currentThread());
    }

}
