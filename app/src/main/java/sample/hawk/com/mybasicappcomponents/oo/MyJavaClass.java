package sample.hawk.com.mybasicappcomponents.oo;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;

import com.google.gson.Gson;
import com.sample.hawk.mylibrary.MyLibClass1;

import java.io.Serializable;

import sample.hawk.com.mybasicappcomponents.background.MyScheduledThreadPoolExecutor;
import sample.hawk.com.mybasicappcomponents.background.MyThreadGroup;
import sample.hawk.com.mybasicappcomponents.background.MyThreadPoolExecutor;
import sample.hawk.com.mybasicappcomponents.background.Thread_vs_Runnable;
import sample.hawk.com.mybasicappcomponents.data_structure.Json.MyJsonObj;
import sample.hawk.com.mybasicappcomponents.data_structure.Serial_Json_Parcel.MyJson;
import sample.hawk.com.mybasicappcomponents.data_structure.Serial_Json_Parcel.MyParcel_Author;
import sample.hawk.com.mybasicappcomponents.data_structure.Serial_Json_Parcel.MyParcel_Book;
import sample.hawk.com.mybasicappcomponents.data_structure.Serial_Json_Parcel.MySerializable;
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
    Intent intent;
    static MySyncTestThread[]  ThreadArray_extends={null,null,null,null,null,null}; // 6 units
    static Thread[] ThreadArray={null,null,null,null,null,null}; // 6 units

    public MyJavaClass(Context context, int param){
        int idx=0;
        // SMLog.i(TAG,"MyJavaClass() constructor +++");
        switch(param){

            case 10000: // <init>,<cinit> condition
                MyJavaDynamic();
                MyJavaStaticRef();
                break;
            case 10001:
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
            case 10002:
                CallByValueOrRef cbv = new CallByValueOrRef();
                cbv.P1();
                break;
            case 10003:
                String[] testStringA = new String[3];
                testStringA[0] = "a";
                testStringA[1] = "b";
                testStringA[2] = "c";

                for (int x = 1; x < testStringA.length; x++) {
                    SMLog.i(testStringA[x] + " ");
                }
                break;
            case 10004: // static, object
                Caller_Test.static_test();
                Caller_Test.object_test();
                break;
            case 10005: // Layer
                Layer_Test.API_Test();
                break;
            case 10006: // Inner Class
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
            case 10007: // Enum
                MyEnum myenum = new MyEnum();
                int color = myenum.switch_case(MyEnum.MyEnumList.YELLOW);
                SMLog.i("Color="+color);
                for(MyEnum.MyEnumList myenum_color : MyEnum.MyEnumList.values())
                    SMLog.i("Color List="+myenum_color);
                break;

            case 10008: // break, continue
                MyBreakContinue mbc = new MyBreakContinue();
                mbc.test_break();
                mbc.test_continue();
                mbc.test_break_continue();
                break;
            case 10111:
                method_1();
                cc_instanceof_keyword(new ChildClass("testObj"));
                pc_instanceof_keyword(new ParentClass());
                break;
            case 10112:
                // String #name = "Jane Doe";  // # will not compile
                int $age = 24;
                Double _height = 123.5;
                // double ~temp = 37.5;  // ~ will not compile
                SMLog.i("$age="+$age);
                SMLog.i("_height="+_height);
                break;
            case 20000: // Mistake: NO sychronized(this) keyword , class extends Thread
                for(MySyncTestThread currThread : ThreadArray_extends)
                {
                    currThread= new MySyncTestThread("T["+idx+"]",false);
                    currThread.start();
                    idx++;
                }
                break;
            case 20001: // Mistake: sychronized(this) keyword , class extends Thread
                for(MySyncTestThread currThread : ThreadArray_extends)
                {
                    currThread= new MySyncTestThread("T["+idx+"]",true);
                    currThread.start();
                    idx++;
                }
                break;
            case 20002: // Mistake: NO sychronized(this) keyword , class implements Runnable for Threads
                MySyncRunnable NoSyncR = new MySyncRunnable("",false);
                for(Thread currThread : ThreadArray)
                {
                    currThread = new Thread(NoSyncR, "T["+idx+"]");
                    currThread.start();
                    idx++;
                }
                break;
            case 20003: // Right: sychronized(this) keyword , class implements Runnable for Threads
                MySyncRunnable syncR = new MySyncRunnable("",true);
                for(Thread currThread : ThreadArray)
                {
                    currThread = new Thread(syncR, "T["+idx+"]");
                    currThread.start();
                    idx++;
                }
                break;
            case 20004: // sleep, wait
                sleep_VS_wait svt = new sleep_VS_wait();
                svt.start_tests();
                break;
            case 20005: // WITH volatile, synchronized, Atom
                MyMultiThreadTest mmtt = new MyMultiThreadTest();
                mmtt.Test(true);
                break;

            case 20006: // WITHOUT volatile, synchronized, Atom
                MyMultiThreadTest mmtt2 = new MyMultiThreadTest();
                mmtt2.Test(false);
                break;

            case 20007:
                Thread_vs_Runnable tvr = new Thread_vs_Runnable();
                tvr.UseExtRunnable();
                tvr.UseExtThread();
                break;
            case 20008:
                MyThreadGroup ntg = new MyThreadGroup();
                ntg.test();
                break;
            case 20009:
                MyThreadPoolExecutor mtpe = new MyThreadPoolExecutor();
                mtpe.test();
                break;
            case 20010:
                MyScheduledThreadPoolExecutor mstpe = new MyScheduledThreadPoolExecutor();
                mstpe.test();
                break;

            case 30001:
                References_Test rft = new References_Test();
                rft.StrongRef_null_Test();
                rft.SoftRef_null_Test();
                rft.WeakRef_null_Test();
                rft.PhantomRef_null_Test();
                break;
            case 30002:
                References_Test rft2 = new References_Test();
                rft2.StrongRef_lessRAM_Test();
                rft2.SoftRef_lessRAM_Test();
                rft2.WeakRef_lessRAM_Test();
                rft2.PhantomRef_lessRAM_Test();
                break;

            case 30003: // Class reference
                MyInterfaceImpl_1 mifi1 = new MyInterfaceImpl_1();
                mifi1.MyInterfaceImpl_RealFunction(); // The DIFF between abstract with interface is abstract can NOT access non-interface API.
                mifi1.MyInterfaceImpl_InterfaceFunction(11111);

                MyInterfaceImpl_2 mifi2 = new MyInterfaceImpl_2();
                mifi2.MyInterfaceImpl_RealFunction(); // The DIFF between abstract with interface is abstract can NOT access non-interface API.
                mifi2.MyInterfaceImpl_InterfaceFunction(22222);
                break;
            case 30004: // abstract reference
                // MyAbstractClass AbObj = new MyAbstractClass();  // ERROR: MyAbstractClass is abstract, cannot be init.
                MyAbstractClass mac1 = new MyAbstractImpl_1();
                mac1.MyAbstractFunction(33333);
                mac1.MyFunctionInAbstrace(33333); // The DIFF between abstract with interface is abstract can NOT access non-interface API.
                // mac1.MyAbstractImplFunction(1);  // ERROR: cannot resolve function

                MyAbstractClass mac2 = new MyAbstractImpl_2();
                mac2.MyAbstractFunction(44444);
                mac2.MyFunctionInAbstrace(44444);  // The DIFF between abstract with interface is abstract can NOT access non-interface API.
                // mac2.MyAbstractImplFunction(2);  // ERROR: cannot resolve function
                break;
            case 30005: // interface reference
                // MyInterface mifa = new MyInterface(); // ERROR: MyInterface is abstract, cannot be init.
                MyInterface mif1 = new MyInterfaceImpl_1();
                mif1.MyInterfaceImpl_InterfaceFunction(55555);
                // mif1.MyInterfaceImpl_RealFunction();  // ERROR: cannot resolve function

                MyInterface mif2 = new MyInterfaceImpl_2();
                mif2.MyInterfaceImpl_InterfaceFunction(66666);
                // mif2.MyInterfaceImpl_RealFunction();  // ERROR: cannot resolve function
                break;

            case 40001: // try-catch
                try_catch ty = new try_catch();
                ty.catch_any();
                ty.catch_right();
                ty.catch_wrong();
                ty.no_catch();
                break;

            case 40002: // ConcurrentModificationException
                JavaExceptions je = new JavaExceptions();
                je.Test(ConcurrentModificationException);
                break;

            case 50001: // Collection structure
                MysynchronizedCollection msc = new MysynchronizedCollection();
                msc.error_case(false);
                //msc.error_case(true);
                break;
            case 50002: // List
                MyList ml = new MyList();
                ml.show();
                ml.use_case();
                break;
            case 50003: // ArrayList
                MyArrayList mal = new MyArrayList();
                mal.show();
                mal.use_case();
                break;
            case 50004:
                MyArray ae = new MyArray();
                ae.show();
                break;
            case 50005:
                MysynchronizedList msl = new MysynchronizedList();
                msl.error_case(false);
                msl.error_case(true);
                break;
            case 50006:
                MyCopyOnWriteArrayList mcowal = new MyCopyOnWriteArrayList();
                mcowal.error_case(false);
                //mcowal.error_case(true);
                break;
            case 50007:
                MyVector mv = new MyVector();
                mv.error_case(false);
                // mv.error_case(true);
                break;
            case 50008:
                MyStack mstk = new MyStack();
                mstk.error_case(false);
                // ms.error_case(true);
                break;
            case 50009: // LinkedList
                MyLinkedList mll = new MyLinkedList();
                mll.show();
                mll.use_case();
                break;
            case 50010:
                MyConcurrentLinkedQueue mcclq = new MyConcurrentLinkedQueue();
                mcclq.error_case(false);
                // mcclq.error_case(true);
                break;
            case 50011: // Set
                MySet ms = new MySet();
                ms.show();
                ms.use_case();
                break;
            case 50012: // HashSet
                MyHashSet1 mhs = new MyHashSet1();
                // String
                mhs.show();
                mhs.use_case();
                // Student <-- String與Student 兩種用法對比重複資料是如何發生的!
                MyHashSet2 mhs2 = new MyHashSet2();
                mhs2.show();
                mhs2.use_case();
                break;
            case 50013:
                MysynchronizedSet mss = new MysynchronizedSet();
                mss.error_case(false);
                //mss.error_case(true);
                break;
            case 50014:
                MyCopyOnWriteArraySet mcowas = new MyCopyOnWriteArraySet();
                mcowas.error_case(false);
                //mcowas.error_case(true);
                break;
            case 50015: // Map
                MyMap mm = new MyMap();
                mm.show();
                mm.use_case();

                MyWeakHashMap whm = new MyWeakHashMap();
                whm.show();
                whm.use_case();
                whm.test();
                break;
            case 50016:
                MysynchronizedMap msm = new MysynchronizedMap();
                msm.error_case(false);
                //msm.error_case(true);
                break;
            case 50017:
                MyConcurrentHashMap mcchm = new MyConcurrentHashMap();
                mcchm.error_case(false);
                // mcchm.error_case(true);
                break;
            case 50018:
                MyHashTable mht = new MyHashTable();
                mht.error_case(false);
                //mht.error_case(true);
                break;

            case 50019: // SparseArray
                MySparseArray msa = new MySparseArray();
                msa.show();
                msa.use_case();
                break;
            case 50020: // Tree
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

            case 60001:
                MyPolymorphism.test();

                MyPolymorphism mp2 = new MyPolymorphism();
                mp2.StaticPolymorphism();
                break;
            case 60002:
                MyPolymorphism mp1 = new MyPolymorphism();
                mp1.DynamicPolymorphism();
                break;
            case 60003:
                MyPolymorphism mp3 = new MyPolymorphism();
                mp3.MethodOverriding();
                break;
            case 60004:
                ParentClass pc = new ParentClass();
                pc.ParentFunction(0000);

                ChildClass cc1 = new ChildClass("cc1");
                cc1.ParentFunction(111);
                cc1.ChildFunction(11111);
                ChildClass cc2 = new ChildClass("cc2");
                cc2.ParentFunction(222);
                cc2.ChildFunction(22222);

                ParentClass p2c = new ChildClass("cc3");
                p2c.ParentFunction(333);
                // p2c.ChildFunction(33333);  // ERROR: cannot resolve function

                // ChildClass c2p = new ParentClass(); // ERROR: incompatible type
                break;
            case 70001: // Serializable
                MySerializable myserial = new MySerializable();
                myserial.mBook.setTitle("Java編程思想");
                myserial.mBook.getAuthor().setId(7777);
                myserial.mBook.getAuthor().setName("Hawk Wei");
                intent = new Intent(context,MyJavaActivity2.class);
                intent.putExtra("from","MySerializable");
                intent.putExtra("book",(Serializable)myserial.mBook);
                context.startActivity(intent);
                break;

            case 70002: // Json
                MyJson myjson = new MyJson();
                myjson.mBook.setTitle("Java編程思想");
                myjson.mBook.getAuthor().setId(1);
                myjson.mBook.getAuthor().setName("Hawk Wei");
                myjson.mBook.setAuthor(myjson.mBook.getAuthor());
                intent = new Intent(context,MyJavaActivity2.class);
                intent.putExtra("from","MyJson");
                intent.putExtra("book",new Gson().toJson(myjson.mBook));
                context.startActivity(intent);
                break;
            case 70003: // Parcel
                MyParcel_Book book = new MyParcel_Book();
                book.setTitle("Java編程思想");
                MyParcel_Author author = new MyParcel_Author();
                author.setId(9999);
                author.setName("Hawk Wei");
                book.setAuthor(author);
                intent = new Intent(context,MyJavaActivity2.class);
                intent.putExtra("from","MyParcel");
                intent.putExtra("book",(Parcelable)book);
                context.startActivity(intent);

                /* // BUG: this inner class parcel type will exception.
                MyParcel myparcel = new MyParcel();
                myparcel.mBook.setTitle("Java編程思想");
                myparcel.mBook.getAuthor().setId(9999);
                myparcel.mBook.getAuthor().setName("Hawk Wei");
                intent = new Intent(this,MyJavaActivity2.class);
                intent.putExtra("from","MyParcel");
                intent.putExtra("book",(Parcelable)myparcel.mBook);
                startActivity(intent);
                */
                break;

            case 70012: // JSON data
                MyJsonObj myjsonObj = new MyJsonObj();
                myjsonObj.show();
                break;

            case 80001: // MyLibrary
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

            case 90001:
                MemoryEater me = new MemoryEater();
                me.waste_Memory(MemoryEater.TYPE.STATIC_VAR, 10*1024*1024);
                break;
            case 90002:
                Reference_Test.ReferenceLeak();
                break;
            case 90003:
                Reference_Test.with_WeakReference(true);
                Reference_Test.with_WeakReference(false);
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
