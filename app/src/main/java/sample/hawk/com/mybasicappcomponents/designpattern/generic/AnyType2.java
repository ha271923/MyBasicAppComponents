package sample.hawk.com.mybasicappcomponents.designpattern.generic;

import sample.hawk.com.mybasicappcomponents.utils.SMLog;

/**
 * Created by ha271 on 2017/10/5.
 */

public class AnyType2<T1, T2> {
    private T1 mValue1;
    private T2 mValue2;

    public AnyType2(T1 value1, T2 value2){
        this.mValue1 = value1;
        this.mValue2 = value2;
    }

    public void setValue1(T1 value){
        this.mValue1 = value;
    }

    public T1 getValue1(){
        return mValue1;
    }

    public void setValue2(T2 value){
        this.mValue2 = value;
    }

    public T2 getValue2(){
        return mValue2;
    }

    public void show(){
        SMLog.i("T1 mValue1= "+mValue1);
        SMLog.i("T2 mValue2= "+mValue2);
    }

}

