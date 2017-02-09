package sample.hawk.com.mybasicappcomponents.oo;

import sample.hawk.com.mybasicappcomponents.utils.SMLog;

/**
 * Created by ha271 on 2016/10/7.
 */

public class MySyncTestThread extends Thread{
    static String TAG = "[MySyncTestThread]";
    String mThreadName;
    boolean mSync;
    int var;
    static int static_var;

    MySyncTestThread(String name, boolean sync) {
        mThreadName = name;
        mSync = sync;
        SMLog.i(TAG,mThreadName+"constructor");
    }




    @Override
    public void run() {
        SMLog.i(TAG, "mSync="+mSync+"  run +++    name=" + mThreadName);
        if(mSync) {
            synchronized(this) { // synchronized +++
                super.run();
                for (int i = 0; i < 10; i++) {
                    try {
                        static_var++;var++;
                        CommonResources.static_var++;
                        CommonResources.StaticNestedClass.static_var++;
                        CommonResources.StaticNestedClass.static_function();
                        CommonResources.StaticNestedClass snc = new CommonResources.StaticNestedClass();
                        snc.function();
                        snc.var++;
                        // CommonResources.InnerClass inner = new CommonResources.InnerClass(); // ERROR: This class is not an enclosing class.

                        // CommonResources.var++; CommonResources.InnerClass.var++; CommonResources.StaticNestedClass.var++; // ERROR: Non-static field var 'var' cannot be reference from a static context.
                        sleep(100); // Hawk: simulate context-switch
                        //SMLog.i(TAG, "run        name="+mThreadName+"  var="+ var+"  static_var="+ static_var);
                        SMLog.i(TAG, "run        name="+mThreadName+"  var="+ var+"  static_var="+ static_var+"  CommonResources.static_var="+CommonResources.static_var);
                        //SMLog.i(TAG, "run        name="+mThreadName+"  CommonResources.static_var="+CommonResources.static_var+"  CommonResources.StaticNestedClass.static_var="+CommonResources.StaticNestedClass.static_var);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            } // synchronized ---
        }
        else {
            super.run();
            for (int i = 0; i < 10; i++) {
                try {
                    static_var++;var++;
                    CommonResources.static_var++;
                    // CommonResources.var++; // ERROR: Non-static field var 'var' cannot be reference from a static context.
                    sleep(100); // Hawk: simulate context-switch
                    //SMLog.i(TAG, "run        name="+mThreadName+"  var="+ var+"  static_var="+ static_var);
                    SMLog.i(TAG, "run        name="+mThreadName+"  var="+ var+"  static_var="+ static_var+"  CommonResources.static_var="+CommonResources.static_var);
                    //SMLog.i(TAG, "run        name="+mThreadName+"  CommonResources.static_var="+CommonResources.static_var+"  CommonResources.StaticNestedClass.static_var="+CommonResources.StaticNestedClass.static_var);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        SMLog.i(TAG, "          run    --- name=" + mThreadName);
    }
}