package sample.hawk.com.mybasicappcomponents.designpattern.generic;

import sample.hawk.com.mybasicappcomponents.utils.SMLog;

/**
 * GenericChild<T1, T2, T3>
 *     extends GenericParent<T1, T2>
 *         implements IGenericIF<T1, T2>
 */

public class GenericParent<T1, T2> implements IGenericIF<T1, T2>  {
    private T1 t1;
    private T2 t2;

    public void setT1(T1 foo1) {
        this.t1 = foo1;
    }

    public T1 getT1() {
        return t1;
    }

    public void setT2(T2 t2) {
        this.t2 = t2;
    }

    public T2 getT2() {
        return t2;
    }

    public void show(){
        SMLog.i("t1="+t1);
        SMLog.i("t2="+t2);
    }

}
