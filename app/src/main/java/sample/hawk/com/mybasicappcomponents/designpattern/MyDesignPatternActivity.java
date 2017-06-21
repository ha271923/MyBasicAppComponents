package sample.hawk.com.mybasicappcomponents.designpattern;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import sample.hawk.com.mybasicappcomponents.R;
import sample.hawk.com.mybasicappcomponents.designpattern.callback.ICallBack1;
import sample.hawk.com.mybasicappcomponents.designpattern.callback.CallBack1;
import sample.hawk.com.mybasicappcomponents.designpattern.callback.Teacher;
import sample.hawk.com.mybasicappcomponents.designpattern.factory.ProductFactory;
import sample.hawk.com.mybasicappcomponents.designpattern.observer.Observer;
import sample.hawk.com.mybasicappcomponents.designpattern.observer.Person1;
import sample.hawk.com.mybasicappcomponents.designpattern.observer.Person2;
import sample.hawk.com.mybasicappcomponents.designpattern.observer.Subject;
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
            case 0:
                CallBack1 mcb = new CallBack1();
                mcb.register(new ICallBack1() {
                    @Override
                    public void onCall_API() {
                        SMLog.i("***  api()'s callback was defined by caller. ***");
                    }
                });
                mcb.api(); // Call scb's call_api() will callback onCall_API.
                break;
            case 1:
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

            case 3:
                ProductFactory pf = new ProductFactory();
                pf.createProduct(ProductFactory.Tea.Cafe);
                pf.createProduct(ProductFactory.Tea.GreenTea);
                pf.createProduct(ProductFactory.Tea.RedTea);
                pf.createProduct(ProductFactory.Tea.BlackTea);
                pf.createProduct(ProductFactory.Tea.MilkTea);
                break;
            default:

        }
    }

}
