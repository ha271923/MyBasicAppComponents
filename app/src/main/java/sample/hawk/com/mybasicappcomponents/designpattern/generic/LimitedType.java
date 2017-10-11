package sample.hawk.com.mybasicappcomponents.designpattern.generic;

import java.util.List;

import sample.hawk.com.mybasicappcomponents.utils.SMLog;

/**
 *  Limited T can only be the type which extended from List.
 */

public class LimitedType<T extends List>{
    private T mList;

    public LimitedType(T ref ){
        mList = ref;
    }

    public void setList(T mArray) {
        this.mList = mArray;
    }

    public T getList() {
        return mList;
    }

    public void show(){
        for(int idx=0; idx < mList.size(); idx++)
            SMLog.i("mList["+idx+"]= "+mList.get(idx));
    }
}
