package sample.hawk.com.mybasicappcomponents.oo;

import sample.hawk.com.mybasicappcomponents.data_structure.MyArrayList;
import sample.hawk.com.mybasicappcomponents.data_structure.MyList;
import sample.hawk.com.mybasicappcomponents.debug.MemoryEater;
import sample.hawk.com.mybasicappcomponents.utils.SMLog;

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
        // SMLog.i(TAG,"MyJavaClass() constructor +++");
        switch(param){
            case 0:
                VariableClass vc = new VariableClass();
                vc.ArrayObjTest();
                break;
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
            case 6:
                MyPolymorphism mp2 = new MyPolymorphism();
                mp2.StaticPolymorphism();
                break;

            case 7:
                MyPolymorphism mp1 = new MyPolymorphism();
                mp1.DynamicPolymorphism();
                break;

            case 8:
                MyPolymorphism mp3 = new MyPolymorphism();
                mp3.MethodOverriding();
                break;

            case 9:
                MemoryEater me = new MemoryEater();
                me.waste_Variable(); // No leakage: variables for local_var, object_var, static_obj_var
                // me.waste_Object();   // No leakage: class objects will be released if the caller Object is END.
                // me.waste_Thread();      // No leakage: Thread objects will be released if its thread run() is END.
                break;
            case 10:
                CallByValueOrRef cbv = new CallByValueOrRef();
                cbv.P1();
                break;
            case 11:
                References_Test rft = new References_Test();
                rft.StrongRef_null_Test();
                rft.SoftRef_null_Test();
                rft.WeakRef_null_Test();
                rft.PhantomRef_null_Test();
                break;
            case 12:
                References_Test rft2 = new References_Test();
                rft2.StrongRef_lessRAM_Test();
                rft2.SoftRef_lessRAM_Test();
                rft2.WeakRef_lessRAM_Test();
                rft2.PhantomRef_lessRAM_Test();
                break;
            case 13: // List structure
                MyList ml = new MyList();
                ml.show();
                break;
            case 14: // ArrayList
                MyArrayList mal = new MyArrayList();
                mal.show_by_listIF();
                mal.show_by_foreach();
                mal.show_by_iterator();
                break;


            default:

        }
        // SMLog.i(TAG,"MyJavaClass() constructor ---");
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
        SMLog.i(TAG,"call MyJavaClass::method_1()  API");
    }


    public void cc_instanceof_keyword(ChildClass obj){
        if( obj instanceof ParentClass ){
            SMLog.i(TAG,"OBJ is instanceof ParentClass");
        }
        if( obj instanceof MyInterface ){
            SMLog.i(TAG,"OBJ is instanceof MyInterface"); // ChildClass implements this interface
        }
        if( obj instanceof ChildClass ){
            SMLog.i(TAG,"OBJ is instanceof ChildClass");
        }
    }

    public void pc_instanceof_keyword(ParentClass obj){
        if( obj instanceof ParentClass ){
            SMLog.i(TAG,"OBJ is instanceof ParentClass");
        }
        if( obj instanceof MyInterface ){
            SMLog.i(TAG,"OBJ is instanceof MyInterface"); // ChildClass implements this interface
        }
        if( obj instanceof ChildClass ){
            SMLog.i(TAG,"OBJ is instanceof ChildClass");
        }
    }



}
