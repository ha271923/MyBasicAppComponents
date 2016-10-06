package sample.hawk.com.mybasicappcomponents.oo;

import android.util.Log;

public class ParentClass {
    // private static final String TAG = "[ParentClass]";
    private static final String TAG = "[Hawk]";  // static
    static String s = init_var();

    static {  // STEP C0, only first-time <cinit>
        Log.i(TAG,"instance ParentClass class static");
    }

    {  // STEP C1, evey new keyword called <init>
        Log.i(TAG,"instance ParentClass class");
    }

    public ParentClass(){  // STEP C2, evey new keyword called
        Log.i(TAG,"into ParentClass constructor");
    }

    private static String init_var(){
        Log.i(TAG,"ParentClass call <cinit> for all static variables");
        return  "ParentClass-----";
    }
}
