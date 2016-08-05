package sample.hawk.com.mybasicappcomponents.oo;

import sample.hawk.com.mybasicappcomponents.utils.SMLog;

/**
 * Created by ha271 on 2016/8/5.
 */

public abstract class MyAbstractClass { // JAVA: you can't new a abstract class obj directly.
    private static final String TAG = "[MyAbstractClass]";

    public boolean MyRealFunction(int param) {
        SMLog.i(TAG, "MyAbstractClass::MyRealFunction = "+ param);
        return true;
    }

    public abstract boolean MyAbstractFunction(int p); // A class with ANY abstract method which is abstract class, you also can create a abstract without ANY abstract method.
}