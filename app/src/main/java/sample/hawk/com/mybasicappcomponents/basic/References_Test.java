package sample.hawk.com.mybasicappcomponents.basic;
import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

import sample.hawk.com.mybasicappcomponents.utils.SMLog;

import static java.lang.Thread.sleep;

public class References_Test {
    final static String TAG="Hawk";
    class A {
        private byte[] leak_obj_var = new byte[1*1024*1024];
        int inner_svar;
        A(int inner_param) {
            inner_svar = inner_param;
            for(int i=0;i<leak_obj_var.length;i++)
                leak_obj_var[i]|=0x11; // fill this patent
        }
    }
    // 測試一旦Obj設為NULL的情況
    void StrongRef_null_Test() { // 一旦Obj設為NULL就無法再被存取了
        A a = new A(100);       //Strong Reference
        A strongObj = a;
        a = null;               //Now, object to which 'a' is pointing null for garbage collection.
        System.gc();
        strongObj.inner_svar = 1;    // object is still alive, since strongObj is still pointed.
        SMLog.i(TAG,"StrongRef_null_Test: svar="+strongObj.inner_svar);
    }
    void SoftRef_null_Test() { // 一旦Obj設為NULL仍可再被存取, 除非JVM記憶體不足, 適用於cache...隨時會消失的資料
        A a = new A(200);       //Strong Reference
        SoftReference<A> softObj = new SoftReference<A>(a); //Creating Soft Reference to A-type object to which 'a' is also pointing
        a = null;               //Now, A-type object to which 'a' is pointing null for garbage collection.
                                //But, it will be garbage collected ONLY when JVM needs memory.
        System.gc();
        a = softObj.get();      //You can retrieve back the object which has been softly referenced
        a.inner_svar = 2;
        SMLog.i(TAG,"SoftRef_null_Test: svar="+a.inner_svar);
    }
    void WeakRef_null_Test() { // 一旦Obj設為NULL仍可再被存取, 除非記憶體不足, 適用於cache...隨時會消失的資料
        A a = new A(300);       //Strong Reference
        WeakReference<A> weakObj = new WeakReference<A>(a); //Creating Weak Reference to A-type object to which 'a' is also pointing.
        a = null;               //Now, A-type object to which 'a' is pointing null is available for garbage collection.
        System.gc();
        a = weakObj.get();      //You can retrieve back the object which has been weakly referenced.
        a.inner_svar = 3;
        SMLog.i(TAG,"WeakRef_null_Test: svar="+a.inner_svar);
    }
    void PhantomRef_null_Test() { // 一旦Obj設為NULL就無法再被存取了, 用途似乎是系統追蹤記憶體使用變動
        A a = new A(400);      //Strong Reference
        ReferenceQueue<A> refQueue = new ReferenceQueue<A>(); //Creating ReferenceQueue
        PhantomReference<A> phantomA = new PhantomReference<A>(a, refQueue); //Creating Phantom Reference to A-type object to which 'a' is also pointing
        a = null;              //Now, A-type object to which 'a' is pointing null is available for garbage collection.
                               //But, this object is kept in 'refQueue' before removing it from the memory.
        System.gc();
        a = phantomA.get();    // IMPORTANT!! it always returns NULL
        if(a==null)
            SMLog.e(TAG,"PhantomRef_null_Test: ERROR! Ref==null");
    }

    // 測試一旦memory爆量的情況, data loss會發生
    class RAM_eater {
        public int var;
        private byte[] leak_obj_var = new byte[500*1024];

        RAM_eater() {
            for(int i=0;i<leak_obj_var.length;i++)
                leak_obj_var[i]|=0x33; // fill this patent
        }
    }
    void StrongRef_lessRAM_Test() {
        A a = new A(100);       //Strong Reference
        RAM_eater re = new RAM_eater();
        re.var = 11;
        System.gc();
        a.inner_svar = 1;    // object is still alive, since strongObj is still pointed.
        SMLog.i(TAG,"StrongRef_null_Test: svar="+a.inner_svar+"   RAM_eater.var="+re.var);
    }
    void SoftRef_lessRAM_Test() { // JVM OOM, 資料會消失, 適用於cache...隨時會消失的資料
        SoftReference<A> softObj = new SoftReference<A>(new A(200));
        SMLog.i(TAG,"SoftRef_null_Test: (1) svar="+softObj.get().inner_svar);
        RAM_eater[] re=new RAM_eater[100];
        SMLog.i(TAG,"SoftRef_null_Test: (2) svar="+softObj.get().inner_svar);
        System.gc();
        SMLog.i(TAG,"SoftRef_null_Test: (3) svar="+softObj.get().inner_svar);
        for(int i=0;i<100;i++){
            re[i] = new RAM_eater();
            re[i].var = 22;
            //But, it will be garbage collected ONLY when JVM needs memory.
            System.gc();
            if(softObj.get()==null){
                SMLog.e(TAG,"SoftRef_null_Test: (4) found data loss!!   RAM_eater["+i+"].var="+re[i].var);
                break;
            }
        }
    }
    void WeakRef_lessRAM_Test() { // OOM , 資料會消失, 適用於cache...隨時會消失的資料
        WeakReference<A> weakObj = new WeakReference<A>(new A(200));
        SMLog.i(TAG,"WeakRef_lessRAM_Test: (1) svar="+weakObj.get().inner_svar);
        RAM_eater[] re=new RAM_eater[100];
        SMLog.i(TAG,"WeakRef_lessRAM_Test: (2) svar="+weakObj.get().inner_svar);
        System.gc();
        SMLog.i(TAG,"WeakRef_lessRAM_Test: (3) svar="+weakObj.get().inner_svar);
        for(int i=0;i<100;i++){
            re[i] = new RAM_eater();
            re[i].var = 22;
            //But, it will be garbage collected ONLY when needs memory.
            System.gc();
            if(weakObj.get()==null){
                SMLog.e(TAG,"WeakRef_lessRAM_Test: (4) found data loss!!   RAM_eater["+i+"].var="+re[i].var);
                break;
            }
        }
    }
    void PhantomRef_lessRAM_Test() { // 一旦Obj設為NULL就無法再被存取了, 用途似乎是系統追蹤記憶體使用變動
        ReferenceQueue<A> refQueue = new ReferenceQueue<A>(); //Creating ReferenceQueue
        PhantomReference<A> phantomA = new PhantomReference<A>(new A(400), refQueue); //Creating Phantom Reference to A-type object to which 'a' is also pointing
        RAM_eater re = new RAM_eater();
        re.var = 44;
        //But, this object is kept in 'refQueue' before removing it from the memory.
        System.gc();
        if(phantomA.get()==null) // IMPORTANT!! it always returns NULL
            SMLog.e(TAG,"PhantomRef_null_Test: ERROR! Ref==null");
    }

}
