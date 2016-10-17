package sample.hawk.com.mybasicappcomponents.oo;
import android.util.Log;

public class MyJavaClass {
    // private static final String TAG = "[MyJavaClass]";
    private static final String TAG = "[Hawk]";
    static ChildClass StaticRef1;
    static ChildClass StaticRef2;
    ChildClass mObj1;
    ChildClass mObj2;

    static MySyncTestThread[]  ThreadArray_extends={null,null,null,null,null,null}; // 6 units
    static Thread[] ThreadArray={null,null,null,null,null,null}; // 6 units

    public MyJavaClass(int param){
        int idx=0;
        Log.i(TAG,"MyJavaClass() constructor +++");
        switch(param){
            case 1: // <init>,<cinit> condition
                MyJavaDynamic();
                MyJavaStaticRef();
                break;
            case 2: // Mistake: NO sychronized(this) keyword , class extends Thread
                for(MySyncTestThread currThread : ThreadArray_extends)
                {
                    currThread= new MySyncTestThread("T["+idx+"]",false);
                    currThread.start();
                    idx++;
                }
                break;
            case 3: // Mistake: sychronized(this) keyword , class extends Thread
                for(MySyncTestThread currThread : ThreadArray_extends)
                {
                    currThread= new MySyncTestThread("T["+idx+"]",true);
                    currThread.start();
                    idx++;
                }
                break;
            case 4: // Mistake: NO sychronized(this) keyword , class implements Runnable for Threads
                MySyncRunnable NoSyncR = new MySyncRunnable("",false);
                for(Thread currThread : ThreadArray)
                {
                    currThread = new Thread(NoSyncR, "T["+idx+"]");
                    currThread.start();
                    idx++;
                }
                break;
            case 5: // Right: sychronized(this) keyword , class implements Runnable for Threads
                MySyncRunnable syncR = new MySyncRunnable("",true);
                for(Thread currThread : ThreadArray)
                {
                    currThread = new Thread(syncR, "T["+idx+"]");
                    currThread.start();
                    idx++;
                }
                break;




            default:
                ;

        }
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


    public void cc_instanceof_keyword(ChildClass obj){
        if( obj instanceof ParentClass ){
            Log.i(TAG,"OBJ is instanceof ParentClass");
        }
        if( obj instanceof MyInterface ){
            Log.i(TAG,"OBJ is instanceof MyInterface"); // ChildClass implements this interface
        }
        if( obj instanceof ChildClass ){
            Log.i(TAG,"OBJ is instanceof ChildClass");
        }
    }

    public void pc_instanceof_keyword(ParentClass obj){
        if( obj instanceof ParentClass ){
            Log.i(TAG,"OBJ is instanceof ParentClass");
        }
        if( obj instanceof MyInterface ){
            Log.i(TAG,"OBJ is instanceof MyInterface"); // ChildClass implements this interface
        }
        if( obj instanceof ChildClass ){
            Log.i(TAG,"OBJ is instanceof ChildClass");
        }
    }



}
