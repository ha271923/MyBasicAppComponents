package sample.hawk.com.mybasicappcomponents.oo;

import sample.hawk.com.mybasicappcomponents.utils.SMLog;

/**
 * Created by ha271 on 2016/10/11.
 */

public class CommonResources { // by default, java class is static class, expect inner class.
    static int static_var;
    public int var;

    // A. Non-static nested classes are called inner classes.
    public class InnerClass{ // by default, inner class is NOT static class.
        // static int static_var; // ERROR: Inner classes cannot have static declarations.
        int var;
        void function(){
            SMLog.i("InnerClass.function() ");
        }
    }

    // B. Nested classes that are declared static are simply called static nested classes.
    static public class StaticNestedClass {
        static int static_var;
        int var;
        static void static_function(){
            SMLog.i("StaticNestedClass.static_function() ");
        }

        static void function(){
            SMLog.i("StaticNestedClass.function() ");
        }

    }


    public void function(String str){ // This is an API with inner class.

        class InnerClassForThisAPI implements Runnable{
            private String str;

            @Override
            public void run() {
                StaticNestedClass with_new = new StaticNestedClass();
                with_new.static_var = 1;
                StaticNestedClass without_new;
                // without_new.static_var =2;  // Compile ERROR!
            }
        }
    }

}
