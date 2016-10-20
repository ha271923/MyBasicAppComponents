package sample.hawk.com.mybasicappcomponents.oo;

import android.util.Log;

import sample.hawk.com.mybasicappcomponents.utils.SMLog;

public class ParentClass2 {
    // private static final String TAG = "[ParentClass2]";
    private static final String TAG = "[Hawk]";  // static
    static String s = init_var();

    static {  // STEP C0, only first-time <cinit>
        SMLog.i(TAG,"instance ParentClass2 class static");
    }

    {  // STEP C1, evey new keyword called <init>
        SMLog.i(TAG,"instance ParentClass2 class");
    }

    public ParentClass2(){  // STEP C2, evey new keyword called
        SMLog.i(TAG,"into ParentClass2 constructor");
    }

    private static String init_var(){
        SMLog.i(TAG,"ParentClass2 call <cinit> for all static variables");
        return  "ParentClass2-----";
    }

    public int strong(){
        return 66;
    }

    public int inteligent(){
        return 77;
    }

}
