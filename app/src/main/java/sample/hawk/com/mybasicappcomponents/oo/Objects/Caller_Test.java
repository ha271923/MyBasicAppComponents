package sample.hawk.com.mybasicappcomponents.oo.Objects;

/**
 * Created by ha271 on 2017/1/16.
 */

public class Caller_Test {

    static public void static_test(){
        // static
        Caller_1.svar=222;
        // Caller_1.var=333; // Non-static field 'var' cannot be referenced from a static context.
        Caller_1.Caller_1_static_API();
    }
    static public void object_test() {
        // Object
        Caller_1 c1 = new Caller_1();
        c1.svar=222;
        c1.var = 333;
        c1.Caller_1_API();
    }
}
