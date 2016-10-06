package sample.hawk.com.mybasicappcomponents.oo;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class MyJavaClass {
    // private static final String TAG = "[MyJavaClass]";
    private static final String TAG = "[Hawk]";
    static ChildClass StaticRef1;
    static ChildClass StaticRef2;
    ChildClass mObj1;
    ChildClass mObj2;

    public MyJavaClass(){
        Log.i(TAG,"MyJavaClass() constructor +++");
        MyJavaDynamic();
        MyJavaStaticRef();
        Log.i(TAG,"MyJavaClass() constructor ---");
    }

    public void MyJavaDynamic(){
        mObj1 = new ChildClass("DynamicObject1");
        mObj2 = new ChildClass("DynamicObject2");
    }

    public void MyJavaStaticRef(){
        StaticRef1 = mObj1;
        StaticRef2 = mObj2;
    }

    public void method_1(){
        Log.i(TAG,"call MyJavaClass::method_1()  API");
    }

}
