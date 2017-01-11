package sample.hawk.com.mybasicappcomponents.oo;

import sample.hawk.com.mybasicappcomponents.data_structure.MyArrayList;
import sample.hawk.com.mybasicappcomponents.data_structure.MyEnum;
import sample.hawk.com.mybasicappcomponents.data_structure.MyHashSet1;
import sample.hawk.com.mybasicappcomponents.data_structure.MyHashSet2;
import sample.hawk.com.mybasicappcomponents.data_structure.MyLinkedList;
import sample.hawk.com.mybasicappcomponents.data_structure.MyList;
import sample.hawk.com.mybasicappcomponents.data_structure.MyMap;
import sample.hawk.com.mybasicappcomponents.data_structure.MySet;
import sample.hawk.com.mybasicappcomponents.data_structure.MySparseArray;
import sample.hawk.com.mybasicappcomponents.data_structure.Tree;
import sample.hawk.com.mybasicappcomponents.debugTest.MemoryEater;
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
                ml.use_case();
                break;
            case 14: // ArrayList
                MyArrayList mal = new MyArrayList();
                mal.show();
                mal.use_case();
                break;
            case 15: // LinkedList
                MyLinkedList mll = new MyLinkedList();
                mll.show();
                mll.use_case();
                break;
            case 16: // Set
                MySet ms = new MySet();
                ms.show();
                ms.use_case();
                break;
            case 161: // HashSet
                MyHashSet1 mhs = new MyHashSet1();
                // String
                mhs.show();
                mhs.use_case();
                // Student <-- String與Student 兩種用法對比重複資料是如何發生的!
                MyHashSet2 mhs2 = new MyHashSet2();
                mhs2.show();
                mhs2.use_case();
                break;
            case 17: // Map
                MyMap mm = new MyMap();
                mm.show();
                mm.use_case();
                break;
            case 18: // SparseArray
                MySparseArray msa = new MySparseArray();
                msa.show();
                msa.use_case();
                break;
            case 19: // Tree
                Tree tr1= new Tree("1");
                    Tree tr11=tr1.addLeaf("1.1");
                        Tree tr111=tr11.addLeaf("1.1.1");
                        Tree tr112=tr11.addLeaf("1.1.2");
                    Tree tr12=tr1.addLeaf("1.2");
                        Tree tr121=tr12.addLeaf("1.2.1");
                        Tree tr122=tr12.addLeaf("1.2.2");
                            Tree tr1221=tr122.addLeaf("1.2.2.1");
                            Tree tr1222=tr122.addLeaf("1.2.2.2");
                        Tree tr123=tr12.addLeaf("1.2.3");
                tr1.show();
                tr1.use_case();
                break;
            case 20: // Enum
                MyEnum myenum = new MyEnum();
                int color = myenum.switch_case(MyEnum.MyEnumList.YELLOW);
                SMLog.i("Color="+color);
                for(MyEnum.MyEnumList myenum_color : MyEnum.MyEnumList.values())
                    SMLog.i("Color List="+myenum_color);
                break;
            case 21: // sleep, wait
                sleep_VS_wait svt = new sleep_VS_wait();
                svt.start_tests();
                break;
            case 22:
                MyInterfaceUsage mifu = new MyInterfaceUsage();
                mifu.MyInterfaceUsageTest();
                mifu.MyInterfaceAPI(55555);
                break;
            case 23:
                MyAbstractUsage mau = new MyAbstractUsage();
                mau.MyAbstractTest();
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
