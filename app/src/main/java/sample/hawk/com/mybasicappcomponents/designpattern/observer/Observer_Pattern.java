package sample.hawk.com.mybasicappcomponents.designpattern.observer;

import sample.hawk.com.mybasicappcomponents.IDemo;
import sample.hawk.com.mybasicappcomponents.utils.SMLog;

/**
 * Created by ha271 on 2017/10/13.
 */

public class Observer_Pattern implements IDemo {
    @Override
    public void demo() {
        Subject subject =  new Subject();
        subject.setTitle("0000");
        Observer observer1 = new Person1();
        Observer observer2 = new Person2();
        subject.setTitle("1111");
        subject.register(observer1);
        subject.register(observer2);
        subject._notifyAll();
        SMLog.i("title= "+ subject.getTitle());
        subject.setTitle("2222");
        subject.unRegister(observer1); //取消觀察者1的註冊
        subject._notifyAll();
        SMLog.i("title= "+ subject.getTitle());
    }
}
