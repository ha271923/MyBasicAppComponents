package sample.hawk.com.mybasicappcomponents.oo;

/**
 * Created by ha271 on 2016/12/14.
 */

public class MyAbstractUsage {

    public void MyAbstractTest(){
        // Test abstract class and interface class
        // MyAbstractClass AbObj = new MyAbstractClass(); // JAVA: you can't new a abstract class obj directly.
        MyObjectClass Obj = new MyObjectClass(); // JAVA: OO Inheritance tech, MyAbstractClass->MyObjectClass
        Obj.MyAbstractFunction(333);
        Obj.MyRealFunction(55555);
    }


}
