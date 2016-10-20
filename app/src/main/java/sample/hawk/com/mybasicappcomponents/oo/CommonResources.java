package sample.hawk.com.mybasicappcomponents.oo;

/**
 * Created by ha271 on 2016/10/11.
 */

public class CommonResources { // by default, java class is static class, expect inner class.
    static int static_var;
    public int var;

    public class InnerClass{ // by default, inner class is NOT static class.
        // static int static_var; // ERROR: Inner classes cannot have static declarations.
        int var;
    }


    static public class StaticInnerClass{
        static int static_var;
        int var;

    }


    public void function(String str){ // This is an API with inner class.

        class InnerClassForThisAPI implements Runnable{
            private String str;

            @Override
            public void run() {

            }
        }
    }

}
