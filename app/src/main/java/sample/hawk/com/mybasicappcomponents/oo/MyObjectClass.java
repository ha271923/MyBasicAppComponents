package sample.hawk.com.mybasicappcomponents.oo;

import sample.hawk.com.mybasicappcomponents.utils.SMLog;

/**
 * Created by ha271 on 2016/8/5.
 */

public class MyObjectClass extends MyAbstractClass { // child MUST implement his abstract class function.
    private static final String TAG = "[MyObjectClass]";
    @Override
    public boolean MyAbstractFunction(int p) {
        SMLog.e(TAG,"MyObjectClass::MyAbstractFunction ="+p);
        return true;
    }
}
