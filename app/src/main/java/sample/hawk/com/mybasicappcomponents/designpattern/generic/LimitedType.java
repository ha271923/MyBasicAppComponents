package sample.hawk.com.mybasicappcomponents.designpattern.generic;

import java.util.List;

import sample.hawk.com.mybasicappcomponents.utils.SMLog;

/**
 *  Limited T can only be the type which extended from List.
 */

public class LimitedType<T extends List>{
    private T[] mList;

    public LimitedType(T[] ref ){
        mList = ref;
    }

    public void setArrayAsList(T[] mArray) {
        this.mList = mArray;
    }

    public T[] getArrayFromList() {
        return mList;
    }

    public void show(){
        for(int idx=0; idx < mList.length; idx++)
            SMLog.i("mList["+idx+"]= "+mList[idx]);
    }
}
