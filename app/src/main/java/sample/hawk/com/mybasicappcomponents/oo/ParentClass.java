package sample.hawk.com.mybasicappcomponents.oo;

import android.util.Log;

import sample.hawk.com.mybasicappcomponents.utils.SMLog;

public class ParentClass {
    // private static final String TAG = "[ParentClass]";
    private static final String TAG = "[Hawk]";  // static
    static String s = init_var();

    static {  // STEP C0, only first-time <cinit>
        SMLog.i(TAG,"instance ParentClass class static");
    }

    {  // STEP C1, evey new keyword called <init>
        SMLog.i(TAG,"instance ParentClass class");
    }

    public ParentClass(){  // STEP C2, evey new keyword called
        SMLog.i(TAG,"into ParentClass constructor");
    }

    private static String init_var(){
        SMLog.i(TAG,"ParentClass call <cinit> for all static variables");
        return  "ParentClass-----";
    }

    public int strong(){
        return 88;
    }

    public int inteligent(){
        return 55;
    }
}
