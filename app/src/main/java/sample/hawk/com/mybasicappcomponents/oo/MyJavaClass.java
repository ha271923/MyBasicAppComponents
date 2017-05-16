package sample.hawk.com.mybasicappcomponents.oo;

import sample.hawk.com.mybasicappcomponents.background.MyThreadGroup;
import sample.hawk.com.mybasicappcomponents.background.MyThreadPoolExecutor;
import sample.hawk.com.mybasicappcomponents.background.Thread_vs_Runnable;
import sample.hawk.com.mybasicappcomponents.data_structure.Json.MyJson;
import sample.hawk.com.mybasicappcomponents.data_structure.advance_ForMultiThread.Collections.MyHashTable;
import sample.hawk.com.mybasicappcomponents.data_structure.advance_ForMultiThread.Collections.MyStack;
import sample.hawk.com.mybasicappcomponents.data_structure.advance_ForMultiThread.Collections.MyVector;
import sample.hawk.com.mybasicappcomponents.data_structure.advance_ForMultiThread.Collections.MysynchronizedCollection;
import sample.hawk.com.mybasicappcomponents.data_structure.advance_ForMultiThread.Collections.MysynchronizedList;
import sample.hawk.com.mybasicappcomponents.data_structure.advance_ForMultiThread.Collections.MysynchronizedMap;
import sample.hawk.com.mybasicappcomponents.data_structure.advance_ForMultiThread.Collections.MysynchronizedSet;
import sample.hawk.com.mybasicappcomponents.data_structure.advance_ForMultiThread.MyMultiThreadTest;
import sample.hawk.com.mybasicappcomponents.data_structure.advance_ForMultiThread.concerent.MyConcurrentHashMap;
import sample.hawk.com.mybasicappcomponents.data_structure.advance_ForMultiThread.concerent.MyConcurrentLinkedQueue;
import sample.hawk.com.mybasicappcomponents.data_structure.advance_ForMultiThread.concerent.MyCopyOnWriteArrayList;
import sample.hawk.com.mybasicappcomponents.data_structure.advance_ForMultiThread.concerent.MyCopyOnWriteArraySet;
import sample.hawk.com.mybasicappcomponents.data_structure.basic_ForSingleThread.MyArray;
import sample.hawk.com.mybasicappcomponents.data_structure.basic_ForSingleThread.MyArrayList;
import sample.hawk.com.mybasicappcomponents.data_structure.basic_ForSingleThread.MyEnum;
import sample.hawk.com.mybasicappcomponents.data_structure.basic_ForSingleThread.MyHashSet1;
import sample.hawk.com.mybasicappcomponents.data_structure.basic_ForSingleThread.MyHashSet2;
import sample.hawk.com.mybasicappcomponents.data_structure.basic_ForSingleThread.MyLinkedList;
import sample.hawk.com.mybasicappcomponents.data_structure.basic_ForSingleThread.MyList;
import sample.hawk.com.mybasicappcomponents.data_structure.basic_ForSingleThread.MyMap;
import sample.hawk.com.mybasicappcomponents.data_structure.basic_ForSingleThread.MySet;
import sample.hawk.com.mybasicappcomponents.data_structure.basic_ForSingleThread.MySparseArray;
import sample.hawk.com.mybasicappcomponents.data_structure.basic_ForSingleThread.MyWeakHashMap;
import sample.hawk.com.mybasicappcomponents.data_structure.basic_ForSingleThread.Tree;
import sample.hawk.com.mybasicappcomponents.debugTest.Exceptions.JavaExceptions;
import sample.hawk.com.mybasicappcomponents.debugTest.MemoryEater;
import sample.hawk.com.mybasicappcomponents.oo.Objects.Caller_Test;
import sample.hawk.com.mybasicappcomponents.oo.Objects.Layer_Test;
import sample.hawk.com.mybasicappcomponents.oo.Objects.Reference_Test;
import sample.hawk.com.mybasicappcomponents.utils.SMLog;

import static sample.hawk.com.mybasicappcomponents.debugTest.Exceptions.JavaExceptions.Exceptions.ConcurrentModificationException;

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
                for(int i=0;i<10;i++){
                    VariableClass vc = new VariableClass();
                    vc.VariableTest();
                }
                for(int i=0;i<10;i++){
                    VariableClass vc = new VariableClass();
                    vc.IncreaseTest();
                }
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
                me.waste_Memory(MemoryEater.TYPE.STATIC_VAR, 10*1024*1024);
                break;
            case 91:
                Reference_Test.ReferenceLeak();
                break;
            case 92:
                Reference_Test.with_WeakReference(true);
                Reference_Test.with_WeakReference(false);
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
            case 13: // Collection structure
                MysynchronizedCollection msc = new MysynchronizedCollection();
                msc.error_case(false);
                //msc.error_case(true);
                break;
            case 131: // List
                MyList ml = new MyList();
                ml.show();
                ml.use_case();
                break;
            case 14: // ArrayList
                MyArrayList mal = new MyArrayList();
                mal.show();
                mal.use_case();
                break;
            case 141:
                MyArray ae = new MyArray();
                ae.show();
                break;
            case 142:
                MysynchronizedList msl = new MysynchronizedList();
                msl.error_case(false);
                msl.error_case(true);
                break;
            case 143:
                MyCopyOnWriteArrayList mcowal = new MyCopyOnWriteArrayList();
                mcowal.error_case(false);
                //mcowal.error_case(true);
                break;
            case 144:
                MyVector mv = new MyVector();
                mv.error_case(false);
                // mv.error_case(true);
                break;
            case 145:
                MyStack mstk = new MyStack();
                mstk.error_case(false);
                // ms.error_case(true);
                break;
            case 15: // LinkedList
                MyLinkedList mll = new MyLinkedList();
                mll.show();
                mll.use_case();
                break;
            case 151:
                MyConcurrentLinkedQueue mcclq = new MyConcurrentLinkedQueue();
                mcclq.error_case(false);
                // mcclq.error_case(true);
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
            case 162:
                MysynchronizedSet mss = new MysynchronizedSet();
                mss.error_case(false);
                //mss.error_case(true);
                break;
            case 163:
                MyCopyOnWriteArraySet mcowas = new MyCopyOnWriteArraySet();
                mcowas.error_case(false);
                //mcowas.error_case(true);
                break;
            case 17: // Map
                MyMap mm = new MyMap();
                mm.show();
                mm.use_case();

                MyWeakHashMap whm = new MyWeakHashMap();
                whm.show();
                whm.use_case();
                whm.test();
                break;
            case 171:
                MysynchronizedMap msm = new MysynchronizedMap();
                msm.error_case(false);
                //msm.error_case(true);
                break;
            case 172:
                MyConcurrentHashMap mcchm = new MyConcurrentHashMap();
                mcchm.error_case(false);
                // mcchm.error_case(true);
                break;
            case 173:
                MyHashTable mht = new MyHashTable();
                mht.error_case(false);
                //mht.error_case(true);
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
            case 22: // Object pointer to its object
                MyInterfaceUsage mifu = new MyInterfaceUsage();
                mifu.MyInterfaceUsageTest();
                mifu.MyInterfaceAPI(55555);
                break;
            case 221: // Interface pointer to its parent object
                MyInterface mif = new MyInterfaceUsage();
                // mif.MyInterfaceUsageTest();  // ERROR: No Way
                mif.MyInterfaceAPI(55555);
                break;
            case 23:
                MyAbstractUsage mau = new MyAbstractUsage();
                mau.MyAbstractTest();
                break;

            case 24: // static, object
                Caller_Test.static_test();
                Caller_Test.object_test();
                break;

            case 25: // Layer
                Layer_Test.API_Test();
                break;

            case 26: // JSON data
                MyJson myjson = new MyJson();
                myjson.show();
                break;
            case 27: // Inner Class
                // A. inner class
                CommonResources.InnerClass incA = new CommonResources().new InnerClass(); // this is right!
                incA.function(27);
                // A. inner class
                CommonResources cs = new CommonResources();
                CommonResources.InnerClass incB = cs.new InnerClass(); // this is right!
                incB.function(27);
                // B. static nested class
                CommonResources.StaticNestedClass cs_snC = new CommonResources.StaticNestedClass();
                cs_snC.static_function(27);
                cs_snC.function(27);

                // CommonResources
                CommonResources cres = new CommonResources();
                cres.function("InnerClass inside API");

                break;
            case 28: // try-catch
                try_catch ty = new try_catch();
                ty.catch_any();
                ty.catch_right();
                ty.catch_wrong();
                ty.no_catch();
                break;
            case 29: // break, continue
                MyBreakContinue mbc = new MyBreakContinue();
                mbc.test_break();
                mbc.test_continue();
                mbc.test_break_continue();
                break;
            case 8001: // ConcurrentModificationException
                JavaExceptions je = new JavaExceptions();
                je.Test(ConcurrentModificationException);
                break;
            case 7015: // WITH volatile, synchronized, Atom
                MyMultiThreadTest mmtt = new MyMultiThreadTest();
                mmtt.Test(true);
                break;

            case 7016: // WITHOUT volatile, synchronized, Atom
                MyMultiThreadTest mmtt2 = new MyMultiThreadTest();
                mmtt2.Test(false);
                break;

            case 7019:
                Thread_vs_Runnable tvr = new Thread_vs_Runnable();
                tvr.UseExtRunnable();
                tvr.UseExtThread();
                break;
            case 7020:
                MyThreadGroup ntg = new MyThreadGroup();
                ntg.test();
                break;
            case 7021:
                MyThreadPoolExecutor mtpe = new MyThreadPoolExecutor();
                mtpe.test();
                break;

/*
            case 9001: // MyLibrary
                // remarked these code, since it's always failed at building the signed release key APP.
                MyLibClass1.static_var = 9001;
                MyLibClass1 mlc = new MyLibClass1();
                mlc.mVar0 = 9001;
                // A. inner class
                MyLibClass1.InnerClass LincA = new MyLibClass1().new InnerClass(); // this is right!
                LincA.function(9001);
                // A. inner class
                MyLibClass1 Lcs = new MyLibClass1();
                MyLibClass1.InnerClass LincB = Lcs.new InnerClass(); // this is right!
                LincB.function(9001);
                // B. static nested class
                MyLibClass1.StaticNestedClass Lcs_snC = new MyLibClass1.StaticNestedClass();
                Lcs_snC.static_function(9001);
                Lcs_snC.function(9001);
                // MyLibClass1
                MyLibClass1 Lcres = new MyLibClass1();
                Lcres.function("MyLibrary's InnerClass inside API");
                break;
*/
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
