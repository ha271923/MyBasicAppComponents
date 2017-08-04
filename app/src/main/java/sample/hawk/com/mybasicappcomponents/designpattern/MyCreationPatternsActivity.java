package sample.hawk.com.mybasicappcomponents.designpattern;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import sample.hawk.com.mybasicappcomponents.R;
import sample.hawk.com.mybasicappcomponents.designpattern.builder.MealDirector;
import sample.hawk.com.mybasicappcomponents.designpattern.factory.Abstract.DrinkFactory;
import sample.hawk.com.mybasicappcomponents.designpattern.factory.Abstract.FoodFactory;
import sample.hawk.com.mybasicappcomponents.designpattern.factory.Abstract.FoodType;
import sample.hawk.com.mybasicappcomponents.designpattern.factory.Abstract.IAbstractFactory;
import sample.hawk.com.mybasicappcomponents.designpattern.factory.Cafe;
import sample.hawk.com.mybasicappcomponents.designpattern.factory.GreenTea;
import sample.hawk.com.mybasicappcomponents.designpattern.factory.IDrinkAction;
import sample.hawk.com.mybasicappcomponents.designpattern.factory.Normal.BlackTeaFactory;
import sample.hawk.com.mybasicappcomponents.designpattern.factory.Normal.CafeFactory;
import sample.hawk.com.mybasicappcomponents.designpattern.factory.Normal.GreenTeaFactory;
import sample.hawk.com.mybasicappcomponents.designpattern.factory.Normal.MilkTeaFactory;
import sample.hawk.com.mybasicappcomponents.designpattern.factory.Normal.NormalFactory;
import sample.hawk.com.mybasicappcomponents.designpattern.factory.Normal.RedTeaFactory;
import sample.hawk.com.mybasicappcomponents.designpattern.factory.RedTea;
import sample.hawk.com.mybasicappcomponents.designpattern.factory.Simple.SimpleFactory;
import sample.hawk.com.mybasicappcomponents.designpattern.factory.TeaType;
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
                SimpleFactory sf = new SimpleFactory();
                sf.createProduct(TeaType.Cafe);
                sf.createProduct(TeaType.GreenTea);
                sf.createProduct(TeaType.RedTea);
                sf.createProduct(TeaType.BlackTea);
                sf.createProduct(TeaType.MilkTea);
                break;
            case 31: // Normal Factory
                NormalFactory nf_product1 = new CafeFactory();
                nf_product1.createProduct();
                NormalFactory nf_product2 = new GreenTeaFactory();
                nf_product2.createProduct();
                NormalFactory nf_product3 = new RedTeaFactory();
                nf_product3.createProduct();
                NormalFactory nf_product4 = new BlackTeaFactory();
                nf_product4.createProduct();
                NormalFactory nf_product5 = new MilkTeaFactory();
                nf_product5.createProduct();
                break;
            case 32: // Abstract Factory
                IAbstractFactory abstractFactory1 = new DrinkFactory();
                abstractFactory1.createProduct(TeaType.Cafe);
                IAbstractFactory abstractFactory2 = new FoodFactory();
                abstractFactory2.createProduct(FoodType.Bakery);
                break;
            case 33: // Factory Method
                IDrinkAction id = null;
                id = new Cafe();
                    id.AddMaterial();
                    id.Brew();
                    id.DeliverCpu();
                id = new GreenTea();
                    id.AddMaterial();
                    id.Brew();
                    id.DeliverCpu();
                id = new RedTea();
                    id.AddMaterial();
                    id.Brew();
                    id.DeliverCpu();
                break;
            // 差異
            //  A. Factory模式：創建單個類的模式（關注單個產品）
            //  B. Builder模式：將各種產品集中起來進行管理（關注複合對象）
            case 34: // Builder
                SMLog.i("----- Packing is FREE!! -----");
                MealDirector mealDirector = new MealDirector();
                SMLog.i("Total Price= " + mealDirector.getOrder(1));
                SMLog.i("Total Price= " + mealDirector.getOrder(2));
                SMLog.i("Total Price= " + mealDirector.getOrder(3));
                SMLog.i("Total Price= " + mealDirector.getOrder(4));
                SMLog.i("Total Price= " + mealDirector.getOrder(123456789));
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
