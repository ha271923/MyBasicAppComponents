package sample.hawk.com.mybasicappcomponents.oo.Objects;

import java.lang.ref.WeakReference;

import sample.hawk.com.mybasicappcomponents.utils.MemoryUtils;
import sample.hawk.com.mybasicappcomponents.utils.SMLog;

/**
 * Created by ha271 on 2017/1/16.
 */

public class Reference_Test {

    public static void ReferenceLeak(){
        Reference_1 r1 = new Reference_1();
        r1.MemoryLeak_Next();
    }

/*
with_WeakReference(true) WeakReference ======================================
with_WeakReference(true) (1) Memory Usage = 10018480
generateOOM() Iteration 1 Free Mem: 6602634
generateOOM() Iteration 2 Free Mem: 6573874
generateOOM() Iteration 3 Free Mem: 6558762
generateOOM() Iteration 4 Free Mem: 6525938
generateOOM() Iteration 5 Free Mem: 6483570
generateOOM() Iteration 6 Free Mem: 6420266
generateOOM() Iteration 7 Free Mem: 6130218
generateOOM() Iteration 8 Free Mem: 4854850
generateOOM() Iteration 9 Free Mem: 385026
generateOOM() Iteration 10 Free Mem: 16756896
generateOOM() Iteration 11 Free Mem: 16747520
:: with_WeakReference(true) (2) Memory Usage = 10021128
:: with_WeakReference(true) After OOM, r1=null,  weak_r1.get()=null  <-- IMPORTANT!!!!!

:: with_WeakReference(false) StrongReference =====================================
:: with_WeakReference(false) (1) Memory Usage = 10044142
generateOOM() Iteration 1 Free Mem: 6581066
generateOOM() Iteration 2 Free Mem: 6556466
generateOOM() Iteration 3 Free Mem: 6547394
generateOOM() Iteration 4 Free Mem: 6516778
generateOOM() Iteration 5 Free Mem: 6487938
generateOOM() Iteration 6 Free Mem: 6406138
generateOOM() Iteration 7 Free Mem: 6135554
generateOOM() Iteration 8 Free Mem: 4853554
generateOOM() Iteration 9 Free Mem: 386026
generateOOM() Iteration 10 Free Mem: 16773120
generateOOM() Iteration 11 Free Mem: 16753272
with_WeakReference(false) (2) Memory Usage = 10013896
with_WeakReference(false) After OOM, r1=sample.hawk.com.mybasicappcomponents.oo.Objects.Reference_1@1ee575
*/
    public static void with_WeakReference(boolean withWeakRef){
        Reference_1 r1 = new Reference_1();
        WeakReference<Reference_1> weak_r1 = new WeakReference<Reference_1>(r1);

        if(withWeakRef == true) { // WeakReference
            SMLog.i("WeakReference ======================================");
            SMLog.i("(1) Memory Usage = "+ MemoryUtils.getMemoryUse());
            try{
                r1 = null;
                weak_r1.get().generateOOM();
                //weak_r1.get().ForceOOM();
            }
            catch (OutOfMemoryError e){
                e.printStackTrace();
            }
            SMLog.i("(2) Memory Usage = "+ MemoryUtils.getMemoryUse());
            SMLog.i("After OOM, r1=" + r1 + ",  weak_r1.get()=" + weak_r1.get()); // IMPORTANT! weak_r1.get() is NULL if OOM occurred!
        }
        else { // StrongReference
            SMLog.i("StrongReference =====================================");
            SMLog.i("(1) Memory Usage = "+ MemoryUtils.getMemoryUse());

            try{
                r1.generateOOM();
                //r1.ForceOOM();
            }
            catch (OutOfMemoryError e){
                e.printStackTrace();
            }
            SMLog.i("(2) Memory Usage = "+ MemoryUtils.getMemoryUse());
            SMLog.i("After OOM, r1=" + r1);
        }
    }



}
