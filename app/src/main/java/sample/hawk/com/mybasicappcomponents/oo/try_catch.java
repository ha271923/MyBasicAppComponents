package sample.hawk.com.mybasicappcomponents.oo;

import sample.hawk.com.mybasicappcomponents.utils.SMLog;

/**
 * Created by ha271 on 2017/3/16.
 */

public class try_catch {

    class MyItem {
        void func(){
            SMLog.i("Call MyItem.func() OK!!");
        }
    }

    MyItem myItem;

    void catch_any(){
        try {
            myItem.func();
        } catch (Exception e){
            SMLog.i("got this exception="+e.toString());
        }
    }

    void catch_right(){
        try {
            myItem.func();
        } catch (NullPointerException e){
            SMLog.i("got this exception="+e.toString()); // NullPointerException exception
        }

    }

    // catch_wrong exception will crash APP.
    void catch_wrong(){
        try {
            myItem.func();
        } catch (OutOfMemoryError e){
            SMLog.i("got this exception="+e.toString()); // OutOfMemoryError exception
        }

    }

    void no_catch(){
        myItem.func();
    }


}
