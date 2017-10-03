package sample.hawk.com.mybasicappcomponents.basic;

import sample.hawk.com.mybasicappcomponents.utils.SMLog;

/**
 * Created by ha271 on 2016/8/5.
 */

public class MyAbstractImpl_3 extends MyAbstractClass2 { // child MUST implement his abstract class static_function.
    private static final String TAG = "[MyAbstractImpl_2]";
    @Override
    public boolean MyAbstractFunction(int p) {
        SMLog.e(TAG,"MyAbstractImpl_2::MyAbstractFunction ="+p);
        return true;
    }

    @Override
    public void MyInterfaceImpl_InterfaceFunction(int i) {

    }
}
