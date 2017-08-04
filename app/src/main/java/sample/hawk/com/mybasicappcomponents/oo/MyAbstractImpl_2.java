package sample.hawk.com.mybasicappcomponents.oo;

import sample.hawk.com.mybasicappcomponents.utils.SMLog;

/**
 * Created by ha271 on 2016/8/5.
 */

public class MyAbstractImpl_2 extends MyAbstractClass { // child MUST implement his abstract class static_function.
    private static final String TAG = "[MyAbstractImpl_2]";
    @Override
    public boolean MyAbstractFunction(int p) {
        SMLog.e(TAG,"MyAbstractImpl_2::MyAbstractFunction ="+p);
        return true;
    }
}
