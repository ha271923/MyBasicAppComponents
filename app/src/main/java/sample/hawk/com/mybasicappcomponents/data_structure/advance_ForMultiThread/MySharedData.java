package sample.hawk.com.mybasicappcomponents.data_structure.advance_ForMultiThread;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by ha271 on 2017/5/11.
 */

public class MySharedData {
    // WITHOUT
    static int mVar1;
    static int mVar2;
    static int mVar3;

    // WITH
    static int mVar;
    static volatile int mVolatileVar;
    static AtomicInteger mAtomVar = new AtomicInteger();
}
