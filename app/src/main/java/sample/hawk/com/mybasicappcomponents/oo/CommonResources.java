package sample.hawk.com.mybasicappcomponents.oo;

import sample.hawk.com.mybasicappcomponents.designpattern.callback.ICallBack1;
import sample.hawk.com.mybasicappcomponents.designpattern.callback.CallBack1;
import sample.hawk.com.mybasicappcomponents.utils.SMLog;

/**
 *  InnerClass有 3種狀態，依所存在的位置而決定
 *      (1) Local inner class: by default, inner class is NOT static class.
 *      (2) Member inner class: This is an API with inner class.
 *      (3) Anonymous inner class(AIC) == (1) Local inner class
 *
 *  InnerClass有 2種生命類型，依 static 關鍵字
 *      A. Non-static nested classes are called inner classes.
 *      B. Nested classes that are declared static are simply called static nested classes.
 *
 *  InnerClass的ERROR類型:
 *      ERROR: Variable 'X' is accessed from within inner class, need to be declared final.
 *      ERROR: non-static field 'X' cannot be referenced from a static context.
 *      ERROR: Inner classes cannot have static declarations.
 *      ERROR: cannot assign a value to final variable 'X'.
 */

public class CommonResources { // by default, java class is static class, expect inner class.
    static int static_var;
    public int mVar0;

    // A. Non-static nested classes are called inner classes.
    public class InnerClass{ // (1) Local inner class: by default, inner class is NOT static class.
        // static int static_var; // ERROR: Inner classes cannot have static declarations.
        int mVar1;
        int function(int param){
            int inn_var = param;
            mVar0++;
            mVar1++;
            static_var++;
            SMLog.i("InnerClass.function() param=" + param+"  mVar1="+ mVar1 +"  inn_var="+ inn_var);
            return mVar1;
        }
    }

    // B. Nested classes that are declared static are simply called static nested classes.
    static public class StaticNestedClass {
        static int static_var;
        int var5;
        static int static_function(int param){
            int inn_var = param;
            // var5++; // ERROR: non-static field 'var' cannot be referenced from a static context.
            SMLog.i("StaticNestedClass.static_function()  param=" + param+"  inn_var="+ inn_var);
            return inn_var;
        }

        static int function(int param) {
            int inn_var = param;
            SMLog.i("StaticNestedClass.function()  param=" + param+"  inn_var="+ inn_var);
            return inn_var;
        }
    }


    public int function(String str){
        final int var0=0;
        int var1=1;
        mVar0++;
        new ICallBack1(){ // (3) Anonymous inner class(AIC) == (1) Local inner class
            // static int svar; // ERROR: Inner classes cannot have static declarations.
            int var3=3;
            @Override
            public void onCall_API() {
                // var0++; // ERROR: cannot assign a value to final variable 'var0'.
                // var1++; // ERROR: Variable 'var1' is accessed from within inner class, need to be declared final.
                var3++;
                int cb_var=5;cb_var++;
                mVar0++;
                SMLog.i("AnonymousInnerClass.function().onCall_API() "+"    mVar0="+mVar0+"    cb_var="+cb_var );
            }
        };

        class InnerClassWithinAPI implements Runnable{ // (2) Member inner class: This is an API with inner class.
            private String str;
            int var2=2;
            public InnerClassWithinAPI(String str){
                // var0++; // ERROR: cannot assign a value to final variable 'var0'.
                // var1++; // ERROR: Variable 'var1' is accessed from within inner class, need to be declared final.
                var2++;
                this.str = str;
                SMLog.i("InnerClassWithinAPI "+"    this.str="+this.str+"    var2="+var2 );
            }

            int function(int param){
                new CallBack1().register(new ICallBack1(){ // (3) Anonymous inner class(AIC) == (1) Local inner class
                    // static int svar; // ERROR: Inner classes cannot have static declarations.
                    int var3=3;
                    @Override
                    public void onCall_API() {
                        var3++;
                        int cb_var=5;cb_var++;
                        mVar0++;
                        SMLog.i("AnonymousInnerClass.function().onCall_API() "+"    mVar0="+mVar0+"    cb_var="+cb_var );
                    }
                });
                return param;
            }

            @Override
            public void run() {
                // var0++; // ERROR: cannot assign a value to final variable 'var0'.
                // var1++; // ERROR: Variable 'var1' is accessed from within inner class, need to be declared final.
                int var4=4;var4++;
                int run_var=4;run_var++;
                mVar0++;
                StaticNestedClass with_new = new StaticNestedClass();
                with_new.static_var = 1;
                StaticNestedClass without_new;
                // without_new.static_var =2;  // Compile ERROR!
                SMLog.i("run() "+"    mVar0="+ mVar0 +"    run_var="+run_var );
            }
        }

        InnerClassWithinAPI icwapi = new InnerClassWithinAPI("HELLO");
        icwapi.function(333);
        return mVar0;
    }

}
