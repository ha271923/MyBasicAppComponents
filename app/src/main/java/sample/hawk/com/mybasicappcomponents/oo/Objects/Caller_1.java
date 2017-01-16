package sample.hawk.com.mybasicappcomponents.oo.Objects;

import sample.hawk.com.mybasicappcomponents.utils.SMLog;

/**
 * Created by ha271 on 2017/1/12.
 */

public class Caller_1 {

    public int var;
    public static int svar;

    public static void Caller_1_static_API(){
        SMLog.i("");
        Caller_1_1.Caller_1_1_static_API();
        Caller_1_2.Caller_1_2_static_API();
    }

    public void Caller_1_API(){
        SMLog.i("");
        Caller_1_1 c11 = new Caller_1_1();
        c11.Caller_1_1_API();
        Caller_1_2 c12 = new Caller_1_2();
        c12.Caller_1_2_API();
    }

}
