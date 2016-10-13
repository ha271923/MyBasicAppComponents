package sample.hawk.com.mybasicappcomponents.oo;

import sample.hawk.com.mybasicappcomponents.utils.SMLog;

/**
 * Created by ha271 on 2016/10/7.
 */

public class MySyncTestThread2 implements Runnable{
    static String TAG = "[MySyncTestThread]";
    String mThreadName="";
    boolean mSync=false;
    int var=0; // object_var
    static volatile int static_var;

    MySyncTestThread2(String name, boolean sync) {
        // mThreadName = Thread.currentThread().getName();
        mSync = sync;
        SMLog.i(TAG,mThreadName+"constructor");
    }

    public void run() {
        int var=0; // stack_var
        mThreadName = Thread.currentThread().getName();
        SMLog.i(TAG, "mSync="+mSync+"  run +++    name=" + mThreadName);
        if(mSync) {
            synchronized(this) { // synchronized +++
                for (int i = 0; i < 10; i++) {
                    try {
                        static_var++;var++;CommonResources.static_var++;CommonResources.StaticInnerClass.static_var++;
                        // CommonResources.var++; CommonResources.InnerClass.var++; CommonResources.StaticInnerClass.var++; // ERROR: Non-static field var 'var' cannot be reference from a static context.
                        Thread.sleep(100); // Hawk: simulate context-switch
                        //SMLog.i(TAG, "run        name="+mThreadName+"  var="+ var+"  static_var="+ static_var);
                        SMLog.i(TAG, "run        name="+mThreadName+"  var="+ var+"  static_var="+ static_var+"  CommonResources.static_var="+CommonResources.static_var);
                        //SMLog.i(TAG, "run        name="+mThreadName+"  CommonResources.static_var="+CommonResources.static_var+"  CommonResources.StaticInnerClass.static_var="+CommonResources.StaticInnerClass.static_var);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            } // synchronized ---
        }
        else {
            for (int i = 0; i < 10; i++) {
                try {
                    static_var++;var++;CommonResources.static_var++;
                    // CommonResources.var++; // ERROR: Non-static field var 'var' cannot be reference from a static context.
                    Thread.sleep(100); // Hawk: simulate context-switch
                    //SMLog.i(TAG, "run        name="+mThreadName+"  var="+ var+"  static_var="+ static_var);
                    SMLog.i(TAG, "run        name="+mThreadName+"  var="+ var+"  static_var="+ static_var+"  CommonResources.static_var="+CommonResources.static_var);
                    //SMLog.i(TAG, "run        name="+mThreadName+"  CommonResources.static_var="+CommonResources.static_var+"  CommonResources.StaticInnerClass.static_var="+CommonResources.StaticInnerClass.static_var);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        SMLog.i(TAG, "          run    --- name=" + mThreadName);
    }
}
