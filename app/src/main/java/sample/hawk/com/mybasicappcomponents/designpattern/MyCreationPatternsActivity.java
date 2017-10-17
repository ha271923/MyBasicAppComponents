package sample.hawk.com.mybasicappcomponents.designpattern;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import sample.hawk.com.mybasicappcomponents.R;
import sample.hawk.com.mybasicappcomponents.designpattern.builder.Builder_Pattern;
import sample.hawk.com.mybasicappcomponents.designpattern.factory.Abstract.AbstractFactory_Pattern;
import sample.hawk.com.mybasicappcomponents.designpattern.factory.FactoryMethod_Pattern;
import sample.hawk.com.mybasicappcomponents.designpattern.factory.Normal.NormalFactory_Pattern;
import sample.hawk.com.mybasicappcomponents.designpattern.factory.Simple.SimpleFactory_Pattern;
import sample.hawk.com.mybasicappcomponents.designpattern.singleton.NoSingleton;
import sample.hawk.com.mybasicappcomponents.designpattern.singleton.Singleton;
import sample.hawk.com.mybasicappcomponents.designpattern.singleton.Singleton_sync1;
import sample.hawk.com.mybasicappcomponents.designpattern.singleton.Singleton_sync2;
import sample.hawk.com.mybasicappcomponents.utils.SMLog;

/**
 * Created by ha271 on 2016/10/6.
 */

public class MyCreationPatternsActivity extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mydesignpatterns_creation);

    }

    public void onClick_MyCreationPatternsClass(View view){
        MyDesignPattern(Integer.parseInt(view.getTag().toString()));
    }

    private void MyDesignPattern(int pattern_type){
        switch(pattern_type){

            case 30: // Simple Factory
                new SimpleFactory_Pattern().demo();
                break;
            case 31: // Normal Factory
                new NormalFactory_Pattern().demo();
                break;
            case 32: // Abstract Factory
                new AbstractFactory_Pattern().demo();
                break;
            case 33: // Factory Method
                new FactoryMethod_Pattern().demo();
                break;
            // 差異
            //  A. Factory模式：創建單個類的模式（關注單個產品）
            //  B. Builder模式：將各種產品集中起來進行管理（關注複合對象）
            case 34: // Builder
                new Builder_Pattern().demo();
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
            case 5: // Prototype
                break;
            default:
                SMLog.e("Not support this pattern yet");
        }
    }

}
