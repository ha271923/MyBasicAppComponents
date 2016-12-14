package sample.hawk.com.mybasicappcomponents.oo;

import sample.hawk.com.mybasicappcomponents.utils.SMLog;

/**
 * Created by ha271 on 2016/12/14.
 */

public class MyInterfaceUsage implements MyInterface {

    public void MyInterfaceUsageTest(){

        MyInterfaceAPI(1111); // JAVA: OO Composition tech

        // Java Anonymous class
        new MyInterface(){
            @Override
            public void MyInterfaceAPI(int i) {
                SMLog.i(TAG, "MyInterfaceAPI = Java Anonymous class" );
            }
        };

        MyInterface ObjIf = new MyInterface(){
            @Override
            public void MyInterfaceAPI(int i) {
                SMLog.i(TAG, "MyInterfaceAPI = Java Anonymous class" );
            }
        };
        ObjIf.MyInterfaceAPI(4444);

        MyInterfaceAPI(9999); // JAVA: OO Composition tech
    }

    @Override
    public void MyInterfaceAPI(int i) {
        SMLog.i(TAG, "MyInterfaceAPI ="+i);
    }
}
