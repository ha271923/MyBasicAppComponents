package sample.hawk.com.mybasicappcomponents.designpattern;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import sample.hawk.com.mybasicappcomponents.R;
import sample.hawk.com.mybasicappcomponents.designpattern.callback.CallBack1;
import sample.hawk.com.mybasicappcomponents.designpattern.callback.ICallBack1;
import sample.hawk.com.mybasicappcomponents.designpattern.callback.Teacher;
import sample.hawk.com.mybasicappcomponents.designpattern.factory.ProductFactory;
import sample.hawk.com.mybasicappcomponents.designpattern.observer.Observer;
import sample.hawk.com.mybasicappcomponents.designpattern.observer.Person1;
import sample.hawk.com.mybasicappcomponents.designpattern.observer.Person2;
import sample.hawk.com.mybasicappcomponents.designpattern.observer.Subject;
import sample.hawk.com.mybasicappcomponents.designpattern.singleton.NoSingleton;
import sample.hawk.com.mybasicappcomponents.designpattern.singleton.Singleton;
import sample.hawk.com.mybasicappcomponents.designpattern.singleton.Singleton_sync1;
import sample.hawk.com.mybasicappcomponents.designpattern.singleton.Singleton_sync2;
import sample.hawk.com.mybasicappcomponents.utils.SMLog;

/**
 * Created by ha271 on 2016/10/6.
 */

public class MyDesignPatternActivity extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mydesignpattern);

    }

    public void onClick_MyDesignPatternClass(View view){
        MyDesignPattern(Integer.parseInt(view.getTag().toString()));
    }

    public void MyDesignPattern(int pattern_type){
        switch(pattern_type){
            case 0: // callback
                CallBack1 mcb = new CallBack1();
                mcb.register(new ICallBack1() {
                    @Override
                    public void onCall_API() {
                        SMLog.i("***  api()'s callback was defined by caller. ***");
                    }
                });
                mcb.api(); // Call scb's call_api() will callback onCall_API.
                break;
            case 1: // callback
                Teacher teacher = new Teacher(); // 有位老師
                teacher.onClass(); // 這位老師正在上課
                break;

            case 2: // Observer: 定義對象間的一對多的依賴關係，當一個對象狀態發生改變時，所有依賴他的對像都得到通知並被自動更新。
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
                break;

            case 3: // factory
                ProductFactory pf = new ProductFactory();
                pf.createProduct(ProductFactory.Tea.Cafe);
                pf.createProduct(ProductFactory.Tea.GreenTea);
                pf.createProduct(ProductFactory.Tea.RedTea);
                pf.createProduct(ProductFactory.Tea.BlackTea);
                pf.createProduct(ProductFactory.Tea.MilkTea);
                break;
            case 40: // No singleton ( MEMORY is double than singleton. )
                NoSingleton nosingleton0_1 = new NoSingleton();
                nosingleton0_1.printCounter();
                NoSingleton nosingleton0_2 = new NoSingleton();
                nosingleton0_2.printCounter();
                break;
            case 41: // singleton for single Thread
                Singleton singleton1_1 = Singleton.getInstance();
                singleton1_1.printCounter();
                Singleton singleton1_2 = Singleton.getInstance();
                singleton1_2.printCounter();
                break;
            case 42: // singleton on multi Thread(ERROR)
                Singleton singleton1_1M = Singleton.getInstance();
                singleton1_1M.printCounter();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Singleton singleton1_2M = Singleton.getInstance();
                        singleton1_2M.printCounter();
                    }
                }).start();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Singleton singleton1_3M = Singleton.getInstance();
                        singleton1_3M.printCounter();
                    }
                }).start();
                break;
            case 43: // singleton for multi Thread(slow)
                Singleton_sync1 singleton_sync1_1 = Singleton_sync1.getInstance();
                singleton_sync1_1.printCounter();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Singleton_sync1 singleton_sync1_2 = Singleton_sync1.getInstance();
                        singleton_sync1_2.printCounter();
                    }
                }).start();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Singleton_sync1 singleton_sync1_3 = Singleton_sync1.getInstance();
                        singleton_sync1_3.printCounter();
                    }
                }).start();
                break;

            case 44: // singleton for multi Thread(fast)
                Singleton_sync2 singleton_sync2_1 = Singleton_sync2.getInstance();
                singleton_sync2_1.printCounter();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Singleton_sync2 singleton_sync2_2 = Singleton_sync2.getInstance();
                        singleton_sync2_2.printCounter();
                    }
                }).start();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Singleton_sync2 singleton_sync2_3 = Singleton_sync2.getInstance();
                        singleton_sync2_3.printCounter();
                    }
                }).start();
                break;


            default:

        }
    }

}
