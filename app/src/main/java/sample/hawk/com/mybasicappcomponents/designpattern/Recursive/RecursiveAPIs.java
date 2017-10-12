package sample.hawk.com.mybasicappcomponents.designpattern.Recursive;

import sample.hawk.com.mybasicappcomponents.utils.SMLog;

/**
 * Created by ha271 on 2017/10/12.
 */

public class RecursiveAPIs {
    public int mNum = 9;

    static public void API_table_9x9(int num){
        for(int i=9; i > 1; i--)
        {
            SMLog.i( num+" * "+i+" = "+num*i);
        }
        num -= 1;
        if( num > 1 ) // escape condition is required!
            API_table_9x9(num); // Recursive API
    }

    public void OBJ_table_9x9(RecursiveAPIs obj){

        for(int i=9; i > 1; i--)
        {
            SMLog.i( mNum+" * "+i+" = "+mNum*i);
        }
        mNum -= 1;
        if( mNum > 1 ) // escape condition is required!
            obj.OBJ_table_9x9(obj); // Recursive object
        else
            mNum = 9;
    }

    public void OBJ_table_x9(RecursiveAPIs obj, int num){

        for(int i=9; i > 1; i--)
        {
            SMLog.i( num+" * "+i+" = "+num*i);
        }
        num -= 1;
        if( num > 1 ) // escape condition is required!
            obj.OBJ_table_x9(new RecursiveAPIs(), num); // Recursive NEW instance again!
    }

}
