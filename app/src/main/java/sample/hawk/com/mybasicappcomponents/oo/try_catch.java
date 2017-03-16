package sample.hawk.com.mybasicappcomponents.oo;

import sample.hawk.com.mybasicappcomponents.utils.SMLog;

/**
 * try_catch class,  Layer1 class   , Layer2 class
 *                   L1_obj         , L2_obj
 *                  <Exception e>
 * <try-catch{..}>
 * APP won't crash if catch_right!
 */

public class try_catch {

    class Layer1 {
        Layer2 L2_obj;
        void func(){
            L2_obj.func();
            SMLog.i("Call Layer1.func() OK!!");
        }
    }

    class Layer2 {
        void func(){
            SMLog.i("Call Layer2.func() OK!!");
        }
    }
    Layer1 L1_obj = new Layer1();

    void catch_any(){
        try {
            L1_obj.func();
        } catch (Exception e){
            SMLog.i("got this exception="+e.toString());
        }
    }

    void catch_right(){
        try {
            L1_obj.func();
        } catch (NullPointerException e){
            SMLog.i("got this exception="+e.toString()); // NullPointerException exception
        }

    }

    // catch_wrong exception will crash APP.
    void catch_wrong(){
        try {
            L1_obj.func();
        } catch (OutOfMemoryError e){
            SMLog.i("got this exception="+e.toString()); // OutOfMemoryError exception
        }

    }

    void no_catch(){
        L1_obj.func();
    }


}
