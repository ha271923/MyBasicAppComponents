package sample.hawk.com.mybasicappcomponents.basic;

import sample.hawk.com.mybasicappcomponents.utils.SMLog;

/**
 * Created by ha271 on 2016/12/14.
 */

public class MyInterfaceImpl_1 implements MyInterface {

    public void MyInterfaceImpl_RealFunction(){

        MyInterfaceImpl_InterfaceFunction(1111); // JAVA: OO Composition tech

        // Java Anonymous class
        new MyInterface(){
            @Override
            public void MyInterfaceImpl_InterfaceFunction(int i) {
                SMLog.i(TAG, "MyInterfaceImpl_InterfaceFunction = Java Anonymous class" );
            }
        };

        MyInterface ObjIf = new MyInterface(){
            @Override
            public void MyInterfaceImpl_InterfaceFunction(int i) {
                SMLog.i(TAG, "MyInterfaceImpl_InterfaceFunction = Java Anonymous class" );
            }
        };
        ObjIf.MyInterfaceImpl_InterfaceFunction(4444);

        MyInterfaceImpl_InterfaceFunction(9999); // JAVA: OO Composition tech
    }

    @Override
    public void MyInterfaceImpl_InterfaceFunction(int i) {
        SMLog.i(TAG, "MyInterfaceImpl_InterfaceFunction ="+i);
    }
}
