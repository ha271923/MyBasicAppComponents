package sample.hawk.com.mybasicappcomponents.basic;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.sample.hawk.mylibrary.MyLibClass1;

import sample.hawk.com.mybasicappcomponents.R;
import sample.hawk.com.mybasicappcomponents.background.MyScheduledThreadPoolExecutor;
import sample.hawk.com.mybasicappcomponents.background.MyThreadGroup;
import sample.hawk.com.mybasicappcomponents.background.MyThreadPoolExecutor;
import sample.hawk.com.mybasicappcomponents.background.Thread_vs_Runnable;
import sample.hawk.com.mybasicappcomponents.data_structure.basic_ForSingleThread.MyEnum;
import sample.hawk.com.mybasicappcomponents.debugTest.Exceptions.JavaExceptions;
import sample.hawk.com.mybasicappcomponents.debugTest.MemoryLeak.MemoryEater;
import sample.hawk.com.mybasicappcomponents.basic.Objects.Caller_Test;
import sample.hawk.com.mybasicappcomponents.basic.Objects.Layer_Test;
import sample.hawk.com.mybasicappcomponents.basic.Objects.Reference_Test;
import sample.hawk.com.mybasicappcomponents.utils.SMLog;

import static sample.hawk.com.mybasicappcomponents.debugTest.Exceptions.JavaExceptions.Exceptions.ConcurrentModificationException;

/**
 * Created by ha271 on 2016/10/6.
 */

public class MyJavaActivity extends Activity{

    static Context m_Context;
    private static final String TAG = "[Hawk]";
    static ChildClass StaticRef1;
    static ChildClass StaticRef2;
    ChildClass mObj1;
    ChildClass mObj2;

    static MySyncTestThread[]  ThreadArray_extends={null,null,null,null,null,null}; // 6 units
    static Thread[] ThreadArray={null,null,null,null,null,null}; // 6 units

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myjavaactivity);
        m_Context = this;
    }

    public void onClick_MyJavaTests(View view){
        SMLog.e("JAVA class: "+((Button)view).getText());
        String Tag = view.getTag().toString();
        int tag = Integer.parseInt(Tag);
        MyJavaTests(m_Context, tag);
    }

    public void MyJavaTests(Context context, int param){
        int idx=0;
        switch(param){ // Switch(exp)，exp為整數運算式，故僅有int,short,char,byte,enum

            case 10000: // <init>,<cinit> condition
                mObj1 = new ChildClass("DynamicObject1");
                mObj2 = new ChildClass("DynamicObject2");
                StaticRef1 = mObj1;
                StaticRef2 = mObj2;
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
                // 字串可以用加(+) 來合併, 數字、字元、物件等等與字串做運算時，都會自動轉為字串，物件會呼叫toString() 方法
                String str = "420";
                str += 42;
                SMLog.i("str= "+str);
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
            case 10009: // ++,-- 前置 後置 , 寫在迴圈的判斷式中，或是指派數值指令上要注意
                // Answer: a=7，b=1，x=20，y=33，z=44
                int  a=7, b=3;
                int x=0, y=0, z=0;
                x = ((--a)*3)+2; // x=(7-1)*3+2=20, a=6     前置 先運算
                y = ((b--)+8)*3; // y=(3+8)*3=33, b=(3-1)=2 後置 後運算
                z = 6*(a++)+8*(--b); // z=6*6+8*(2-1)=44, a=(6+1)=7  (前置 先運算&後置 後運算)
                SMLog.i("a=" + a + ",b=" + b + ",x=" + x+ ",y=" + y+ ",z=" + z );
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

                MyAbstractClass2 mac2_impl3 = new MyAbstractImpl_3();
                mac2_impl3.MyAbstractFunction(5);
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
            case 40012: // try-catch
                try {
                    SMLog.i("In try {...}");
                    return; // always run finally before return
                }
                catch(Exception e){
                    SMLog.i("In catch {...}");
                }
                finally{
                    SMLog.i("In finally {...}");
                }
                break;
            case 40002: // ConcurrentModificationException
                JavaExceptions je = new JavaExceptions();
                je.Test(ConcurrentModificationException);
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
    }

    public void method_1(){
        SMLog.i(TAG,"call MyJavaActivity::method_1()  API");
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
