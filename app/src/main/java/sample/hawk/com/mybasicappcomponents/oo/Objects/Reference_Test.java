package sample.hawk.com.mybasicappcomponents.oo.Objects;

/**
 * Created by ha271 on 2017/1/16.
 */

public class Reference_Test {

    public static void ReferenceLeak(){
        Reference_1 r1 = new Reference_1();
        r1.MemoryLeak_Next();
    }
}
