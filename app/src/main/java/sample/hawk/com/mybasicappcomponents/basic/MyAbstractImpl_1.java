package sample.hawk.com.mybasicappcomponents.basic;

import sample.hawk.com.mybasicappcomponents.utils.SMLog;

/**
 * Created by ha271 on 2016/8/5.
 */

public class MyAbstractImpl_1 extends MyAbstractClass { // child MUST implement his abstract class static_function.
    private static final String TAG = "[MyAbstractImpl_1]";

    public boolean MyAbstractImplFunction(int p) {
        SMLog.e(TAG,"MyAbstractImpl_1::MyAbstractImplFunction ="+p);
        return true;
    }

    @Override
    public boolean MyAbstractFunction(int p) {
        SMLog.e(TAG,"MyAbstractImpl_1::MyAbstractFunction ="+p);
        return true;
    }
}
