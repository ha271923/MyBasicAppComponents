package sample.hawk.com.mybasicappcomponents.designpattern.generic;

import sample.hawk.com.mybasicappcomponents.utils.SMLog;

/**
 * Created by ha271 on 2017/10/5.
 */

public class AnyType1<T> {
    private T mValue;

    public AnyType1(T value){
        this.mValue = value;
    }

    public void setValue(T value){
        this.mValue = value;
    }

    public T getValue(){
        return mValue;
    }

    public void show(){
        SMLog.i("T1 mValue= "+mValue);
    }

}
