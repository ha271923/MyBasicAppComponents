package sample.hawk.com.mybasicappcomponents.oo;

import sample.hawk.com.mybasicappcomponents.utils.SMLog;

/**
 * Created by ha271 on 2016/10/25.
 */

public class CallByValueOrRef {
    final static String TAG="Hawk";

    private static int sInt;
    private static int[] sArray;

    class inner {
        int inner_svar;
        inner(int inner_param){
            inner_svar = inner_param;
        }
    }
    /*
    Test Result:
        oo.CallByValueOrRef :: P3() P3:  x=3  y=30       z[0]=300        obj.inner_svar=3000
        oo.CallByValueOrRef :: P2() P2:  a=2  b=20       c[0]=300        d.inner_svar=3000
        oo.CallByValueOrRef :: P1() P1:  i=1  sInt=10    sArray[0]=300   in.inner_svar=3000
                                         ^^ CallByValue  ^ CallByRef     ^ CallByRef
    */
    void P1(){
        int i;
        i=1;
        sInt = 10;
        sArray = new int[1];
        sArray[0] = 100;
        inner in = new inner(1000);
        P2(i,sInt,sArray, in); // next layer
        SMLog.i(TAG,"P1:  i="+i+"  sInt="+sInt+"  sArray[0]="+sArray[0]+"   in.inner_svar="+in.inner_svar);
    }

    void P2(int a,int b, int[] c, inner d){
        a = 2;
        b = 20;
        c[0] = 200;
        d.inner_svar = 2000;
        P3(a,b,c,d); // next layer
        SMLog.i(TAG,"P2:  a="+a+"  b="+b+"  c[0]="+c[0]+"   d.inner_svar="+d.inner_svar);
    }

    void P3(int x, int y, int[] z, inner obj){
        x = 3;
        y = 30;
        z[0] = 300;
        obj.inner_svar = 3000;
        SMLog.i(TAG,"P3:  x="+x+"  y="+y+"  z[0]="+z[0]+"   obj.inner_svar="+obj.inner_svar);
    }

}
