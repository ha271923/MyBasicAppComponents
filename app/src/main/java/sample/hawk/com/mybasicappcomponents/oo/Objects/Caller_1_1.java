package sample.hawk.com.mybasicappcomponents.oo.Objects;

import sample.hawk.com.mybasicappcomponents.utils.SMLog;

/**
 * Created by ha271 on 2017/1/12.
 */

public class Caller_1_1 {

    public int var;
    public static int svar;

    public static void Caller_1_1_static_API(){
        SMLog.i("");
        Caller_1_1_1.Caller_1_1_1_static_API();
    }

    public void Caller_1_1_API(){
        SMLog.i("");
        Caller_1_1_1 c111 = new Caller_1_1_1();
        c111.Caller_1_1_1_API();
    }
}
